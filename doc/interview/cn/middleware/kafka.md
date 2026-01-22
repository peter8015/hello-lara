[toc]


> 说明：本文包含kafka 待解决的问题、kafka原理部分、kafka面试题、kafka案例、kafka实践

# todo

1.  kafka 是什么？主要应用场景是什么？

2. 与其他的消息中间件对比，kafka 的优势是什么？

3. 消息是如何存储的？

4. 消费端如何提高消息的吞吐能力？

5. 什么情况下会产生消息的丢失？如何处理？

6. 什么情况下会产生消息的重复？如何处理？

7. 如何保证可用性？

8. 如何保证消息的有序性？

9. 如何保证事务？

10. 如何实现全链路日志追踪？

11. 队列模型了解吗？kafka 的消息模型是什么？

12. kafka 的多副本机制了解吗？ 有什么好处？

    ​	

# 原理篇

# kafka 是什么？

Kafka是一种高吞吐量、分布式、基于发布/订阅的消息系统，最初由 LinkedIn 公司开发，使用Scala 语言编写，目前是 Apache 的开源项目。

- broker: Kafka 服务器，负责消息存储和转发
- topic: 消息类别，Kafka 按照 topic 来分类消息
- partition:topic的分区，一个 topic 可以包含多个 partition，topic 消息保存在各个
  partition 

offset:消息在日志中的位置，可以理解是消息在 partition 上的偏移量，也是代表该消息的
唯一序号

- Producer:消息生产者
- Consumer:消息消费者
- Consumer Group:消费者分组，每个 Consumer 必须属于一个 group
- Zookeeper:保存着集群 broker、topic、partition 等 meta 数据;另外，还负责 broker 故障发现，partition leader 选举，负载均衡等功能
- 

![0584c0e3a3fc80e781f7e93723d0a774](/Users/haibingzhang/d/weiyundata/typoradoc/images/E438F10F-B8B4-4F17-AB90-3DEAFF97B387.png) //todo



kafka生产端的整体架构？  //-todo

生产者--拦截器--序列化--分区--消息累加--





# 如何保证消息的有序性

有序性分两种：一种是全局有序，另外一种是局部有序。

- `全局有序:` 一个topic下的所有消息都需要按照生产顺序进行消费。例如订单中按orderId有序进行消费。要保证全局有序，只需要一个topic对应一个patition，另外消费端要用单线程或用保证消费顺序的线程模式。但这样会大大降低consumer的消费能力。

![image-20220508160708472](https://lz.sinaimg.cn/large/e6c9d24ely1h2ck1hn0prj20oe05yweq.jpg)

- `局部有序:` 保证局部有序，需要在发送消息时指定patition key， kafka对其进行hash计算，根据计算结果放入指定的patition。
- `保证局部有序并提高消费端的吞吐量:` 在不考虑增加patition数量的情况下，可以考虑再次hash唯一标识（例如对orderid进行hash）到不同的线程上，消费者利用多线程处理消息。（依旧可以保证消息的局部有序性。）

![image-20220508161056909](https://lz.sinaimg.cn/large/e6c9d24ely1h2ck1on6gpj224s0cujtu.jpg)


扩展：

> 1. 消息为什么会乱序？
> 2. rabbitmq,rocketmq、kafka如何保证消息有序？
> 3. 如何保证全局有序？如何保证局部有序？
> 4. 应用场景案例？
>    案例：大数据系统同步订单系统操作，如果通过中间件进行设计。在数据库发生变动时，将binlog日志发送给消息中间件，必须要保持消息的有序性。例如：生产端发送了add、update、del三条操作，到消费端变成del、update、add,必然会引起逻辑上的问题。
>    参考：https://mp.weixin.qq.com/s？__biz=MzUyNzgyNzAwNg%3D%3D&idx=1&mid=2247484318&scene=21&sn=dddad2258bb0ba6e8de79e471733461a#wechat_redirect



# kafka如何提高消费端的能力？

> 生产者生产消息的速度大于消费者的消费速度如何解决？kafka有消息保留机制，有些消息在消息被消费之前就有可能被清理掉了，从而导致消息的丢失。

当生产者发送消息的速度大于消费者消费消息的速度时，可以采用多线程的方式实现消息的消费，提高整体的消费能力。多线程的实现方式有多种：

- 第一种也是最常见的方式：==线程封闭==，即为每个线程实例化一个kafkaconsumer对象，这种线程称为消费线程。每个消费线程可以消费一个或多个分区的消息。这种实现方式的并发度受限于分区的实际个数，当==消费线程的个数==大于分区时，就会有部分消费线程一直处于空闲状态。==优点==是可以顺序的消费各个分区的消息。==缺点==也很明显，每个消费线程都要维护一个独立的tcp连接，如果分区数和消费线程的值都比较大，那么会造成不小的系统开销。

- 第二种与第一种对应，==多个消费线程同时消费一个分区==，这个通过assign(),seek()方法实现。==优点==是可以打破原有的消费线程个数不能超过分区数的限制，进一步提高消费能力。但这种方式对于==位移提交和顺序控制==的处理就会变得异常复杂，实际使用极少。

- 第三种：==利用多线程提高消息处理的能力==。因为如果消息处理的非常快，那么poll()拉取的频次也会更高，进而提高整体消费的性能。一般而言，poll()拉取消息的速度是相当快的，而整体消费的瓶颈也正在消息的逻辑处理上。所以将处理消息模块改为多线程的实现方式，能带动整体消费性能的提升。

  -  注意kafkaConsumerThread（消费线程）可以横向扩展来提高整体的消费能力。消费线程中线程池（ThreadPoolExecutor）的最后一个参数拒绝策略设置为callerRunsPolicy，可以有效的防止==消费能力不及==poll()拉取能力时导致的异常。

  - 缺点就是对于==消息的顺序处理==比较困难。这里引入一个共享的offsets来参与提交（Map\<TopicPatition, OffsetAndMetadata\> offsets） ，每一个消息处理完之后都会把位移保存到共享变量offsets中，kafkaConsumerThread在每一次poll()方法之后都读取Offsets中的内容并进行位称提交。 

  - 写入offsets时问题：==如何解决并发问题？==注意在实现过程中需要对offsets读写需要进行加锁处理，防止出现并发问题。

    ``` java
    for(TopicPartition tp : records.partitions()) {
      List<ConsumerRecord<String, String>> tpRecords = records.records(tp);
      long lastConsumedOffset = tpRecords.get(tpRecords.size() - 1).offset();
      sychronized(offsets) {
        if(!offsets.containsKey(tp)) {
          offsets.put(tp, new OffsetAndMetadata(lastConsumedOffset + 1));
        } else {
        	long position = offsets.get(tp).offset();
          if(position < lastConsumeOffset - 1) {
            offsets.put(tp, new OffsetMetaData(lastConsumedOffset + 1)) {     
          }
        }
      }
    }
    ```

    

  - 如何解决位移覆盖问题？

    ``` java
    synchronized(offsets) {
      if(!offsets.isEmpty()) {
        kafkaConsumer.commitAndSync(offsets);
        offsets.clear();
      }
    }
    ```

  - 这种移位提交的方式会有数据丢失的风险，什么情况下产生，如何解决？（扩展 滑动窗口解决方案//---todo）
  
  - 

![image-20220519100810267](https://lz.sinaimg.cn/large/e6c9d24ely1h2dhnar0tnj216s0kk0ul.jpg)



# kafka内部是如何存储的？

`topic`：kafka中的消息是以topic为基本单位进行归类的，多个topic在逻辑上是相互独立的。

`patition`：每个topic包含一个或多个分区，分区的数量可以在创建主题时指定，也可以在之后进行修改。每条`消息发送时`都会根据分区的规则追回到指定的分区中，分区中的每条件消息都会被分配一个唯一的序列号，也就是通常所说的offset。

`Log`：在不考虑分区多副本的情况下，每个分区对应一个日志。为了避免日志过大，kafka引入了日志分段的概念，将log切分为多个``logsegment`，便于消息的维护和清理。 Log在物理上是以文件夹的形式存储 ，而每个logsegment对应磁盘上的一个日志文件和两个索引文件，以及可能的其他文件，如.txindex事务文件。

`消息追加`：向log中追加消息是顺序写入的，只有最后一个logsegment可以执行写入操作，在此之前的logsegment都不能写入数据。最后一个logsegment也称为活跃分段activesegment。随着消息的写入，当activesegment符合一定的条件时，就需要创建新的activesegment，之后追回的消息将会写入到新的activesegment。

`消息检索`：为了便于消息检索，每个logsegment中的日志文件都有对应的两个索引文件：`偏移量索引和时间戳索引`。每个logsegment都有一个基准偏移量baseoffset，用来表示当前logsegment中的第一条消息的offset。偏移量是一个64位长整数，日志文件和两个索引文件都是以baseoffset进行命名的，固定为20位数字。



![image-20220521115155139](https://lz.sinaimg.cn/large/e6c9d24ely1h2fvvwtugaj210m0qcq4k.jpg)





# kafka如何保证幂等性？

消息传输保障有三种方式：
At most once：最多一次，有可能产生消息的丢失，但消息不可能重复。
At least once: 最少一次，消息不会丢失，但消息有可能重复。
Exactly once：恰好一次。每条消息肯定会被传输一次且仅传输一次。

`kafka可靠性保障：` 
`生产者`：kafka的消息保障机制非常直观。当生产者发送消息时，消息一旦被提交到日志，由于副本机制的存在，消息不会丢失。所以这里消息提供的保障机制是at least once。
`消费者`: 对于消费者而言，消费者处理消息和提交消费位移的顺序很大程度上决定了消费者提供哪一种消息保障传输。

- 如果消费者在拉取完消息之后，应用程序先处理消息后提交消费位移，那么在消息处理之后且在位移提交之前消费者宕机了，待它重新上线之后，会从上一次位移提交的位置重新拉取，这样就出现了重复消费，因为部分消息已经处理过了还未来得及提交消费位移，此时就对应at least once。
- 如果消费者在拉完消息之后，应用逻辑先提交消费位移后进行消息处理，那么在位移提交之后且在消息处理完之前消费宕机了，待它重新上线之后，会从已经提交的位移处开始重新消费，但之前尚有部分消息未进行消费，此时就会发生消息丢失，此时就对应at most once。

`kafka幂等性：`
kafka的幂等性只能保证单生产者会话中的单分区的幂等。跨分区的幂等不能保证，但事务弥补了这个缺陷。
幂等实现原理：幂等性简单的说是指对接口的一次调用和多次调用产生的结果是一致的。
为了实现`生产者幂等性`，kafka引入了`producer id和序列号`的概念。每个生产者在实例化的时候都会被分配一个PID, 这个PID对用户而言是透明的。对于每个PID，消息发送到每个分区都有对应的序列号，这些序列号从0开始单调递增。生产者每发送一条消息就会将<pid, 分区>对应的序列号的值加1.
broker端会在内存中为`每一对<pid, 分区>`维护一个序列号，对于每收到的一条消息，它的序列号与broker端维护的序列号进行对比：
sn\_new = sn\_old+1 服务器接收新的消息
sn\_new < sn\_old + 1 说明消息重复情况
sn\_new>sn\_old + 1 说明有消息丢失情况

# kafka如何保证事务？

幂等性不能跨多个分区进行动作，而事务可以保证对多个分区写入操作的原子性。操作的原子性是指多个操作要么全部成功，要么全部失败，不存在部分成功，部分失败的可能。
`consume-transform-produce`：对于流式应用而言，一个典型的应用模式为consume-transform-produce。在这种模式下消费和生产并存：应用程序从主题中消费消息，然后经由一系列的转换后写入到另外一个主题，消费者可能在提交消费位移的过程中出现问题而导致消息重复消费，或是生产者重复生产消息。kafka中的事务可以保证 消费消息、生产消息、提交消费位移当作原子操作来处理，同时成功或失败。
生产者：对于生产者而言，通过事务，kafka可以保证跨生产者会话的消息幂等发送，以及跨生产者会话的事务恢复。

![image-20220522113949204](https://lz.sinaimg.cn/large/e6c9d24ely1h2h15ndbnfj20ss0qygnt.jpg)消费者：对于消息者而方，事务能保证的语义相对偏弱。
`事务相关的概念：`

- 为了实现事务，应用程序必须提供唯一的transactionid，通过客户端参数进行显式设置。transaction\_id与PID一一对应。
- 事务要求生产者必须开启幂等特性。
- 为了保证新的生产者启动后，具有相同 的transactionid的旧的生产者能够立即失效，每个生产者通过trsantionid获得PID的同时，还会获得一个单调递增的producer\_epoch。
- 消费端可以设置isolation\_level参数，用来决定消费端是否可以看到未提交的事务。可以设置read\_uncommitted和 read\_committed。
- 事务协调器transactionCoordinator：用来负责处理事务，可以类比组协调器GroupCoordinator。每一个生产者者会指派一个特定的事务协调器，所以有事务逻辑包括分派pid都是由事务协调器完成。TC会将事务状态持久化到主题 \_trsaction\_state中。
事务应用：

`事务原理：` //-todo

1. 查找TC
2. 获取PID
3. 开启事务
4. consume-transform-produce
5. 提交事务

# 消费任务挂掉导致的消费积压的解决方法?

- 任务重启后消费最新的消息，对于滞后的历史数据利用****离线程序****进行补漏。
  增加新的 topic 并配置更多的分区，把原有消费逻辑写入新的消费者逻辑中。将原有积压消息的消费者逻辑修改为打入新的 topic。
  ![](https://lz.sinaimg.cn/large/e6c9d24ely1h2bd86xwvwj20oe0hamy5.jpg)

- Kafka 分区数设置的不合理或消费者"消费能力"不足的优化**

  kafka分区是kafka并行度调优的最小单元，如果设置太少会影响吞吐量。如果设置很大，可以考虑增加patition的数量，同时提升消费端的处理能力。

- Kafka 消息 key 设置的优化: 根据业务设置合理的设置producer消息的key，避免数据倾斜。




# 消息队列是如何保证消息的可靠性，避免消息的丢失的？
- 如何验证？可以通过全局有序id或局部有序id和拦截器来验证消息是否有丢失。 //todo

- 如何保证可靠？
  一条消息从发送到消费整个流程可以分为三个阶段：生产阶段、存储阶段、消费阶段
    - 生产阶段：需要捕获消息发送的错误信息，重发消息。是通过请求和确认机制来保证可靠性的。消息的发送有同步和异步两种模式。
      同步: 通过返回信息确认消息是否发送成功，如果失败进行重试。
      异步: 通过回调接口判断是否发送成功。
    - 存储阶段：可以配置刷盘或复制相关的参数，让消息写入多个副本，来确保消息不会因为某个broker宕机或磁盘损坏而丢失。  可以通过设置参数来保证消息的可靠性。可以设置是否刷盘或副本数量不小于两个副本才返回确认信息来保证。
    - 消费阶段：需要在处理完所有的业务逻辑后，再发送确认消息。 每个broker都会对应一个consumer，consumer拉取broker的信息后进行业务
      逻辑处理并存入磁盘，成功后再提交偏移量返回确认信息。
- 发生重复怎么处理：如果发送方收不到确认回复（无论是异常或是网络问题），就会重复发送消息，那broker或consumer都有可能收到重复消息，如何处理？如何确保消息不丢失：
    - 通过WAL（write ahead log），参考myql的redolog的实现，在发送消息前进行日志记
    录处理。
    - 如果消息出现重复，就要进行去重处理。一是业务逻辑处理天然支持幂等。二是根据业务id
    （唯一标识）先去查询验证是否重复。



# **消息重试对顺序消息的影响**

>***消费者消费时间过长**对于一个有着先后顺序的消息A、B，正常情况下应该是A先发送完成后再发送B，但是在异常情况下，在A发送失败的情况下，B发送成功，而A由于重试机制在B发送完成之后重试发送成功了。这时对于本身顺序为AB的消息顺序变成了BA。*

* **严格保证顺序消费**：需要设置 max.in.flight.requests.per.connection参数的支持。该参数指定了生产者能发送消息的最大量，它的值越高，占用内存就会越大，吞吐量也会提高。设置为1，可以消息是按发送顺序写入服务器的。
* **其他方案**：该参数设置为1会严重降低吞吐量，如果放弃这种重试同步机制，可以考虑失败重试方案。对失败的消费进行标识，异步定时任务进行重试，并做好监控报警。

# 如何避免重复消费

重复消费原因：https://zhuanlan.zhihu.com/p/112745985

- **Consumer 在消费过程中，应用进程被强制kill掉或发生异常退出。**

例如在一次poll500条消息后，消费到200条时，进程被强制kill消费导致offset 未提交，或出现异常退出导致消费到offset未提交。下次重启时，依然会重新拉取这500消息，这样就造成之前消费到200条消息重复消费了两次。因此在有消费者线程的应用中，应尽量避免使用kill -9这样强制杀进程的命令。

- **消费者消费时间过长**

max.poll.interval.ms参数定义了两次poll的最大间隔，它的默认值是 5 分钟，表示你的 Consumer 程序如果在 5 分钟之内无法消费完 poll 方法返回的消息，那么 Consumer 会主动发起“离开组”的请求，Coordinator 也会开启新一轮 Rebalance。若消费者消费的消息比较耗时，那么这种情况可能就会出现。可以看到在消费完第11条消息后，因为消费时间超出max.poll.interval.ms 默认值5分钟，这时consumer已经离开消费组了，开始rebalance，因此提交offset失败。之后重新rebalance，消费者再次分配partition后，再次poll拉取消息依然从之前消费过的消息处开始消费，这样就造成重复消费。而且若不解决消费单次消费时间过长的问题，这部分消息可能会一直重复消费。

**解决方法：**

- 第一种思路是**提高消费能力**，提高单条消息的处理速度，例如对消息处理中比 较耗时的步骤可通过异步的方式进行处理、利用多线程处理等。在缩短单条消息消费时常的同时，根据实际场景可将max.poll.interval.ms值设置大一点，避免不 必要的rebalance，此外可适当减小max.poll.records的值，默认值是500，可根 据实际消息速率适当调小。这种思路可解决因消费时间过长导致的重复消费问题， 对代码改动较小，但无法绝对避免重复消费问题。

- 第二种思路是引入**单独去重机制**，例如生成消息时，在消息中加入唯一标识符如消息id等。在消费端，我们可以保存最近的1000条消息id到redis或mysql表中，配置max.poll.records的值小于1000。在消费消息时先通过前置表去重后再进行消息的处理。

# kafka安装启动

> https://kafka.apache.org/quickstart
> kafka 安装启动
> springboot + kafka
> https://github.com/spring-projects/spring-kafka

1. 在官网下载kafka安装包
   https://kafka.apache.org/downloads

     解压安装包，进入kafka目录

     ``` shell
   $ tar -xzf kafka_2.13-3.1.0.tgz
   $ cd kafka__2.13-3.1.0
     ```

2. 运行以下脚本，启动zookeeper

   ``` shell
   # start zookeeper service
   $ bin/zookeeper-server-start.sh config/zookeeper.properties
   
   # start kafka service
   $ bin/kafka-server-start.sh config/server.properties
   ```

   ps: 官方接下来的版本估计去除对zookeeper的依赖

3. 创建一个主题来存储你的事件
   kafka 是一个分布式的事件流平台，允许你跨多台机器进行读、写、存储和处理事件流（在文档中也被称为记录和消息）
   这些事件被组织并存储到主题中，主题类似文件系统中的文件夹，而其中的文件就像文件夹中的文件。

     ``` shell
   $ bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
     ```

     如果想查看命令的额外参数信息时，可以输入不加参数的命令：`bin/kafka-topic.sh`

     ``` shell
     @bogon bin % ./kafka-topics.sh
     Create, delete, describe, or change a topic.
     Option                                   Description
     ------                                   -----------
     --alter                                  Alter the number of partitions,
                                                replica assignment, and/or
                                                configuration for the topic.
     --at-min-isr-partitions                  if set when describing topics, only
                                                show partitions whose isr count is
                                                equal to the configured minimum. Not
                                                supported with the --zookeeper
                                                option.
     --bootstrap-server <String: server to    REQUIRED: The Kafka server to connect
       connect to>                              to. In case of providing this, a
                                                direct Zookeeper connection won\'t be
   
     ```

     如果想查看分区信息，可以使用`--describe`参数

     ``` shell
     bin % ./kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092 
     Topic: quickstart-events   PartitionCount: 1   ReplicationFactor: 1    Configs: segment.bytes=1073741824
   Topic: quickstart-events Partition: 0    Leader: 0   Replicas: 0 Isr: 0
     ```

4. 向主题写入事件信息
   运行控制台生产者客户端`kafka-console-produer`写入一些事件信息到你的主题。

     ``` shell
    bin % ./kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
   >this is my first event
   >this is my second event
   >this is my third event
     ```

     你可以随时用`ctrl-c`停止生产者客户端。

5. 读取事件信息
   打开另外一个会话，并运行控制台客户端读取刚创建的事件信息

     ``` shell
   bin % ./kafka-console-consumer.sh --topic quickstart-events --from-beginning  --bootstrap-server localhost:9092
   this is my first event
   this is my second event
     ```



# spring-boot集成 kafka

1. 创建topic
2. 新建Spring-boot项目

- 引入pom依赖

  ``` java
  <dependency>
      <groupId>org.springframework.kafka</groupId>
      <artifactId>spring-kafka</artifactId>
  </dependency>
  ```

    

- Application.properties

  ``` properties
  ###########【Kafka集群】###########
  spring.kafka.bootstrap-servers=112.126.74.249:9092,112.126.74.249:9093
  ###########【初始化生产者配置】###########
  # 重试次数
  spring.kafka.producer.retries=0
  # 应答级别:多少个分区副本备份完成时向生产者发送ack确认(可选0、1、all/-1)
  spring.kafka.producer.acks=1
  # 批量大小
  spring.kafka.producer.batch-size=16384
  # 提交延时
  spring.kafka.producer.properties.linger.ms=0
  # 当生产端积累的消息达到batch-size或接收到消息linger.ms后,生产者就会将消息提交给kafka
  # linger.ms为0表示每接收到一条消息就提交给kafka,这时候batch-size其实就没用了
  ​
  # 生产端缓冲区大小
  spring.kafka.producer.buffer-memory = 33554432
  # Kafka提供的序列化和反序列化类
  spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
  spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
  # 自定义分区器
  # spring.kafka.producer.properties.partitioner.class=com.felix.kafka.producer.CustomizePartitioner
  ​
  ###########【初始化消费者配置】###########
  # 默认的消费组ID
  spring.kafka.consumer.properties.group.id=defaultConsumerGroup
  # 是否自动提交offset
  spring.kafka.consumer.enable-auto-commit=true
  # 提交offset延时(接收到消息后多久提交offset)
  spring.kafka.consumer.auto.commit.interval.ms=1000
  # 当kafka中没有初始offset或offset超出范围时将自动重置offset
  # earliest:重置为分区中最小的offset;
  # latest:重置为分区中最新的offset(消费分区中新产生的数据);
  # none:只要有一个分区不存在已提交的offset,就抛出异常;
  spring.kafka.consumer.auto-offset-reset=latest
  # 消费会话超时时间(超过这个时间consumer没有发送心跳,就会触发rebalance操作)
  spring.kafka.consumer.properties.session.timeout.ms=120000
  # 消费请求超时时间
  spring.kafka.consumer.properties.request.timeout.ms=180000
  # Kafka提供的序列化和反序列化类
  spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
  spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
  # 消费端监听的topic不存在时，项目启动会报错(关掉)
  spring.kafka.listener.missing-topics-fatal=false
  # 设置批量消费
  # spring.kafka.listener.type=batch
  # 批量消费每次最多消费多少条消息
  # spring.kafka.consumer.max-poll-records=50
  ```



3. hello kafka

   ``` java
   @RestController
   public class KafkaProducer {
       @Autowired
       private KafkaTemplate<String, Object> kafkaTemplate;
   ​
       // 发送消息
       @GetMapping("/kafka/normal/{message}")
       public void sendMessage1(@PathVariable("message") String normalMessage) {
           kafkaTemplate.send("topic1", normalMessage);
       }
   }
   ```

4. 消费者

   ``` java
   @Component
   public class KafkaConsumer {
       // 消费监听
       @KafkaListener(topics = {"topic1"})
       public void onMessage1(ConsumerRecord<?, ?> record){
           // 消费的哪个topic、partition的消息,打印出消息内容
           System.out.println("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
       }
   }
   ```

5. 上面示例创建了一个生产者，发送消息到topic1，消费者监听topic1消费消息。监听器用@KafkaListener注解，topics表示监听的topic，支持同时监听多个，用英文逗号分隔。启动项目，postman调接口触发生产者发送消息，

   postman发送消息，消费端接收成功

   ![image-20220308105305440](/Users/haibingzhang/d/weiyundata/typoradoc/images/image-20220308105305440.png)



# 如何平衡消息

在Apache Kafka中，当消费者组中的消费者数量发生变化时（例如有新的消费者加入、已有消费者退出或宕机），Kafka通过一个称为**重平衡（Rebalance）**的机制来确保消息的均衡消费。

以下是重平衡的基本步骤：

- 触发重平衡：
当消费者组成员数量发生变化时，比如新消费者实例启动并加入到消费者组、已有消费者实例关闭或者网络连接断开导致与Kafka集群失去联系，都会触发一次重平衡操作。
- 停止消费：
在重平衡开始时，所有消费者实例都会被暂停消费，释放之前分配给它们的所有分区。
- 协调器介入：
消费者组的每个成员都会向其所属的Group Coordinator（通常是Kafka Broker的一个角色）**发送心跳和更新组成员**状态信息。Group Coordinator根据收到的信息判断是否需要进行重平衡，并发起重平衡过程。
- 重新分配分区：
Group Coordinator负责将主题的所有分区按照一定的策略（如默认的Range或用户自定义的策略）重新分配给当前在线的消费者实例。分区分配的目标是尽量均匀地分散负载，确保各个消费者的工作量大致相同。
- 消费者订阅分区：
一旦分区分配完毕，消费者实例会接收到新的分区分配列表，并开始订阅分配给它们的新分区。
每个消费者实例根据分配结果开始从指定分区读取消息，并更新各自的消费偏移量。
- 恢复消费：
所有消费者实例都完成分区订阅后，就开始继续处理各自分区内未消费的消息，从而实现整个消费者组内消息的均衡消费。

在整个过程中，Kafka的设计能够保证即使消费者数量动态变化，也能自动调整消费者的分区分配，以达到消息均衡消费的目的。同时，为了减少不必要的重平衡对系统的影响，客户端通常可以配置一些参数来优化重平衡行为，例如设置合理的session.timeout.ms和heartbeat.interval.ms等。



# kafka消息为什么 这么快？

- 顺序读写
- 零拷贝
- 消息压缩
- 分批发送

# 参考

1. [https://blog.csdn.net/y277an/article/details/117828798?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&utm_relevant_index=2](https://blog.csdn.net/y277an/article/details/117828798?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&utm_relevant_index=2)
2. [https://www.iteblog.com/archives/1619.html](https://www.iteblog.com/archives/1619.html)
3. [https://blog.csdn.net/fgszdgbzdb/article/details/112787089](https://blog.csdn.net/fgszdgbzdb/article/details/112787089)
4. [指定patition](https://www.cnblogs.com/gnivor/p/5318319.html)

[如何提高消费端的能力](https://blog.csdn.net/y277an/article/details/117828798?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&utm_relevant_index=2) 

[消费端多线程处理](https://blog.csdn.net/weixin_30337251/article/details/99060165)

官方文档

https://kafka.apache.org/documentation/#ecosystem
springboot+kafka
https://docs.spring.io/spring-boot/docs/current/reference/html/messaging.html#messaging.kafka

kafka集群搭建  //todo
