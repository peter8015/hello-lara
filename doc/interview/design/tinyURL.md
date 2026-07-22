**题目背景：** 作为一个高级系统架构师，你需要为公司设计一个高并发、低延迟的短网址（TinyURL）生成与重定向系统。系统需要支持日均 **1 亿（100M）** 的短网址生成，以及 **10 亿（1B）** 的重定向跳转请求。




## 第一阶段：Architecture-First —— 边界与容量估算

### 1. 核心需求（Requirements）

* **Functional（功能）：**
  * 长链接换短链接（长变短：`https://long-url.com/...` \$\\rightarrow\$ `https://tiny.url/abc123`）
  * 短链接重定向到长链接（短变长，高频只读：302 状态码跳转）
  * **2026 现代特性：** 支持基于 AI 情感/内容分析的自定义短链接后缀（如电商大促推荐智能生成后缀）。
* **Non-Functional（非功能）：**
  * **高可用（High Availability）** 与 **低延迟（Low Latency）**：重定向跳转必须控制在 **50ms** 以内。
  * **高并发（High Throughput）**：日均 1 亿写，10 亿读，读写比为 \$10:1\$。

### 2. 容量与吞吐量估算（Back-of-the-Envelope Estimates）

* **写入 QPS (生成短链)：**
  \$\$\\text{Write QPS} = \\frac{100,000,000 \\text{ 条}}{86400 \\text{ 秒}} \\approx 1,160 \\text{ QPS}\$\$
  *峰值按 3 倍算，约为 **3,500 QPS**。*
* **读取 QPS (重定向跳转)：**
  \$\$\\text{Read QPS} = \\frac{1,000,000,000 \\text{ 条}}{86400 \\text{ 秒}} \\approx 11,600 \\text{ QPS}\$\$
  *峰值按 3 倍算，约为 **35,000 QPS**。*

## 🧠 第二阶段：核心域划分（DDD）与技术蓝图

为了防止 AI 编码时写出“大泥球”，我们用 **DDD（领域驱动设计）** 划分出两个极其清晰、完全解耦的**限界上下文（Bounded Context）**：

### 1. `ShortenContext` (生成域 - 写密集型)

* **核心职责：** 接收长链接，通过分布式 Snowflake 发号器生成唯一 Long ID，**通过可逆混淆算法阻断连续性**，再通过 Base62 算法计算出唯一的 7 位短码，写入数据库。
* \*\*数据模型 (Schema)：\*\*SQL
  ```
  CREATE TABLE url_mapping (
      short_code VARCHAR(10) PRIMARY KEY,       -- 7位独立混淆Base62字符
      long_url TEXT NOT NULL,                  -- 原始长链接
      user_id BIGINT NOT NULL,                 -- 创建者ID
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      expired_at TIMESTAMP,                    -- 过期时间，用于动态联动清理
      INDEX idx_user_created (user_id, created_at)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
  ```

### 2. `RedirectContext` (跳转域 - 读密集型，QPS 35K+)

* **核心职责：** 极速读取短码，拿到长链接，返回 `302 Found` 状态码。
* **异步旁路防腐层 (ACL) 设计：** 为了死守 50ms 延迟底线，系统**绝不采用同步调用大模型**的方式。我们在进入核心域之前建立一个 **AI 旁路风控防腐层（AI-Security ACL）**。前线跳转仅进行极快的硬件级流控，并将访问流异步打入 Kafka，由白天的 AI 智能体离线建模分析，捕获恶意爬虫和钓鱼网站后，反向异步刷新前线黑名单，实现“用传统的严密架构防御 AI 的不确定性”。

## 🛠️ 第三阶段：核心算法与系统拓扑

### 1. 核心算法：Snowflake + Feistel 混淆网络 + Base62

* **发号器抉择：** 摒弃高网络 IO 开销且存在主从同步不一致风险的 Redis 计数器，采用 **Twitter 改进版 Snowflake（雪花算法）**。纯本地内存与时钟计算，单机轻松突破数万 QPS。
* **混淆层机制：** 针对 Snowflake 趋势自增容易被黑客顺序遍历（如 `aaaaaa1` \$\\rightarrow\$ `aaaaaa2`）导致资产泄露的红线，ID 必须先通过 **Feistel 可逆加密网络（或位反转算法 Bit-Shuffling）**，在 CPU 内存中瞬间离散映射为一个无规律的新 Long ID，再转为 Base62。生成的短码完全不可预测。

### 2. 读写分离与 AI 旁路异步拓扑图

```
                       `[ 客户端 / 浏览器 ]
                             │
                             ▼ (35K QPS 极速跳转通道，目标延迟 < 50ms)
                       [ 负载均衡器 Nginx ]
                             │
              ┌──────────────┴──────────────┐
              ▼                             ▼
     [ 域 1: ShortenContext ]       [ 域 2: RedirectContext ]
              │                             │
              │                             ├─► [ 布谷鸟过滤器 (Cuckoo Filter) ] (100%拦截非关联短链)
              ▼                             │
     [ 分布式发号器: Snowflake ]            ├─► [ 多级缓存 Redis集群 ] (极速302跳转)
              │                             │
              ▼                             │ (异步非阻塞打点)
 [ ID混淆层: Feistel/Random ]               ▼
              │                    [ Kafka 消息队列集群 ]
              ▼                             │
     [ MySQL 主库集群 ]                      ▼
              ▲                    [ AI 安全分析智能体 (Offline/Stream) ]
              │                             │ (大数据流式计算，识别黑产/作弊IP)
              └─────────────────────────────┴─► [ 反向动态刷新黑名单 & 过滤器 ]`
```

### 3. 高并发优化密码：

* **多级缓存策略：** 读请求采用 Cache Aside 模式。Redis 存储 `short_code -> long_url`，过期时间设为 24 小时，满足 90% 的热点本地穿透。
* **防穿透卫士（Cuckoo Filter）：** 彻底放弃不支持删除操作的传统布隆过滤器，升级为 **布谷鸟过滤器（Cuckoo Filter）**。当用户手动删除或 AI 自动下线某条过期短链时，系统发出 `UrlDeletedEvent` 事件，`RedirectContext` 异步监听该事件并调用 `CuckooFilter.delete(short_code)`，完美支持业务动态伸缩并 100% 拦截空缓存穿透攻击。

## 跑 🏃‍♂️ 第四阶段：Code-Second —— 让 AI 执行细节

我们将上述重新审计后的系统架构规范写入 `.cursorrules` 文件中。此时，AI 编码 Agent 就会在限定好的安全沙盒（Sandbox）内部去 5 倍速填充代码，绝对无法越界：

Markdown

```
# .cursorrules - TinyURL Project Global Rules

1. [DOMAIN BOUNDARY & READ/WRITE ISOLATION]
   - Code inside `RedirectContext` MUST NOT perform ANY direct write or query operations on the Master MySQL database.
   - All routing read traffic must strictly fall back from Cuckoo Filter -> Redis Cluster -> MySQL Slave Replica.

2. [SECURITY & COMPLIANCE]
   - NEVER convert standard sequential/incremental Snowflake IDs into Base62 strings directly.
   - You MUST pass the generated ID through `FeistelCipher.obfuscate(long id)` to randomize bit distribution before encoding.

3. [AI & HIGH-THROUGHPUT TELEMETRY]
   - DO NOT invoke any LLM/AI reasoning APIs synchronously inside the HTTP request-response thread pool.
   - All behavior telemetry and access logs for anti-bot analysis MUST be emitted asynchronously via the Kafka Producer pipeline.
```

### 给 AI 的最新 Prompt 示例：

> “请在 `RedirectContext` 目录下，严格按照 `.cursorrules` 定义的缓存和布谷鸟过滤器设计规范，用 Java/Golang 填充短链跳转的业务逻辑。
>
> **要求：** 优先判定布谷鸟过滤器，未击中直接返回 404；击中则查 Redis，Redis 没击中再查 MySQL 从库并回写 Redis。整个链路通过 Kafka 异步客户端发送点击流日志，绝对禁止同步调用任何 AI 风控接口。不要修改任何目录结构和 API 契约。”

## 💡 架构师面试加分项（Killer Answers）

* **301 还是 302 跳转？**
  **答案：** 坚决使用 **302（临时重定向）**。虽然 301（永久重定向）能在客户端缓存从而减轻服务器压力，但由于我们系统依赖 **AI 旁路分析机制** 动态抓取黑产、清洗流量日志，并需要实时统计大变动的点击量和地理用户画像。如果选择 301，浏览器将不再向我们的服务器发送请求，我们的 AI 旁路分析就会彻底变成“瞎子”。
* **AI 时代的冷热数据降本增效：**
  **答案：** 日均 1 亿条数据意味着年产 365 亿条记录，全量常驻分布式主库的存储成本无法接受。我们利用 AI 预测模型在后台跑定时任务：将超过 90 天无人点击的“僵尸短链”自动从 MySQL 主库中剥离，归档到成本仅为传统数据库 1/10 的分布式对象存储（如 AWS S3 / 阿里云 OSS）或 BigTable 中，MySQL 仅保留高频热数据。
