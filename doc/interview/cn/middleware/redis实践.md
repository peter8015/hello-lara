[toc]



# springboot与redis结合Redission

Redisson是一个基于Redis的Java驻内存数据网格（In-Memory Data Grid）和用于构建分布式系统的Java对象存储。它提供了分布式锁、分布式集合、分布式对象以及分布式服务等功能，同时提供了对Redis协议的支持。Redisson使得在Java中使用Redis变得更加容易，提供了丰富的功能和性能优化，能够极大地简化分布式系统的开发和维护。

Redisson主要提供以下功能：

1. 分布式对象：Redisson提供了分布式Map、Set、List等数据结构，这些数据结构均存储在Redis中，能够很好地支持分布式程序的数据共享和协同处理。
2. 分布式锁：Redisson提供了基于Redis的分布式锁实现，包括了可重入锁、公平锁、红锁等多种类型的分布式锁，并提供了高可用的分布式锁服务。
3. 分布式服务：Redisson提供了分布式并发服务、计数器服务、队列服务等，方便了在分布式环境下的并发控制和任务分发。
4. 事件通知：Redisson还支持对数据变化的事件通知功能，能够实时感知Redis中数据的变化，方便实现一些实时消息推送等功能。

总的来说，Redisson提供了丰富的基于Redis的Java分布式对象服务，使得开发人员可以更轻松地构建分布式系统，并提供了监控和管理功能，使得对分布式系统的维护和优化变得更加简便。v

# redis的有多少数据类型？在电商和社交系统对应具体的应用场景是什么？分别给出具体实例，

Redis支持多种数据类型，包括字符串（String）、哈希（Hash）、列表（List）、集合（Set）、有序集合（Sorted Set）和其他特殊类型（HyperLogLog、GEO等）。

在电商和社交系统中，这些数据类型可以应用于不同的场景：

- 字符串（String）：用于存储用户信息、商品信息等简单的键值数据。在电商系统中，可以用于存储商品信息的缓存、用户登录信息等。在社交系统中，可以用于存储用户个人资料、帖子内容等。

```redis
SET user:1234:username "Alice"
SET product:789:stock 100
```

- 哈希（Hash）：用于存储对象的属性和值，能够快速地访问单个属性。在电商系统中，可以用于存储商品详情、订单信息等。在社交系统中，可以用于存储用户个人资料、帖子的详细信息。

```redis
HSET product:789 name "Smartphone" price 599.99 stock 100
HSET user:1234 name "Alice" email "alice@example.com"
HGET user:1234 name
HSET key field1 value1 field2 value2
```

- 列表（List）：用于存储有序的数据，如商品列表、消息队列等。在电商系统中，可以用于存储商品搜索结果、订单处理队列等。在社交系统中，可以用于存储用户的时间线、消息队列等。

```redis
LPUSH product:789:comments "Good product, recommended!"
RPUSH user:1234:newsfeed "New post from friend"
```

- 集合（Set）：用于存储无序的、不重复的数据，如用户标签、商品标签等。在电商系统中，可以用于存储商品标签、用户兴趣标签等。在社交系统中，可以用于存储用户的关注列表、粉丝列表等。

```redis
SADD product:789:tags "electronics" "smartphone" "bestseller"
SADD user:1234:following "user:5678" "user:7890"
```

- 有序集合（Sorted Set）：类似集合，但每个成员都会关联一个分数，以便按照分数进行排序。在电商系统中，可以用于存储商品排行榜、评分排行榜等。在社交系统中，可以用于存储用户的粉丝排行榜、文章的热门排行榜等。

```redis
ZADD product:rankings 8.7 "product:789"
ZADD user:followers 1500 "user:5678"
```

这些数据类型在电商和社交系统中有着广泛的应用，能够满足各种不同的数据存储和处理需求。通过合理地选择不同的数据类型，并结合Redis的强大功能，能够为电商和社交系统提供高效的数据处理和存储解决方案。



# redis集群的搭建





# redis哨兵模式搭建



# redis分布式锁的实现

在一个典型的电子商务系统中，下单操作是一个适合使用分布式锁的场景。当多个客户端同时尝试下单时，为了确保订单的一致性和避免超卖，可以使用分布式锁来保护下单操作。在这种情况下，Redis可以作为一个分布式锁的存储介质，而Spring Boot则提供了方便的集成和管理Redis的方法。

## 第一种实现 redisTemplate

下面是一个基于Spring Boot和Redis的分布式锁示例，首先是Maven依赖配置：下面是一个基于Spring Boot和Redis的分布式锁示例，首先是Maven依赖配置：

``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

```



然后是在`application.properties`（或`application.yml`）中配置Redis连接信息：

``` properties
spring.redis.host=your_redis_host
spring.redis.port=your_redis_port
spring.redis.password=your_redis_password
```

接下来我们将编写一个服务类，其中包含了下单操作的代码以及使用Redis作为分布式锁的示例代码：

``` java
@Service
public class OrderService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean placeOrderWithLock(String orderId) {
        boolean lockAcquired = false;
        try {
            String lockKey = "order_lock_" + orderId;
            String lockValue = UUID.randomUUID().toString();
            // 尝试获取锁，设置超时时间为5秒
            lockAcquired = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 5, TimeUnit.SECONDS);
            if (lockAcquired) {
                // 获取到锁，执行下单操作
                // ...
                return true;
            } else {
                // 未获得锁，可能是其他线程正在处理相同的订单
                return false;
            }
        } finally {
            if (lockAcquired) {
                // 释放锁
                String lockKey = "order_lock_" + orderId;
                String lockValue = redisTemplate.opsForValue().get(lockKey);
                if (lockValue != null && lockValue.equals(lockValue)) {
                    redisTemplate.delete(lockKey);
                }
            }
        }
    }
}

```

现在让我们来对上面的代码进行讲解：

1. 我们首先使用`StringRedisTemplate`作为对Redis进行操作的工具类，Spring Boot会自动配置这个类以连接到Redis服务器。
2. 在`placeOrderWithLock`方法中，我们首先生成一个唯一的锁值作为标识。我们使用`setIfAbsent`方法来尝试设置锁，并通过设置超时时间来避免锁永远不被释放。
3. 如果成功设置了锁，表示获取到了锁，就可以执行下单操作。否则，可能是其他线程正在处理相同的订单，则直接返回false。
4. 在`finally`块中，无论是否成功获取锁，最终都会尝试去释放锁。

这个示例演示了如何在Spring Boot中使用Redis实现分布式锁来保护下单操作。当多个客户端尝试下单时，只有一个客户端能够获取到锁并执行下单操作，从而确保了订单的一致性和避免了超卖。在上面的代码中，我们使用了Redis的`setIfAbsent`方法来尝试设置锁，从而确保了设置分布式锁的原子性。在上面的代码中，我们使用了Redis的`setIfAbsent`方法来尝试设置锁，从而确保了设置分布式锁的原子性。

风险及优化：

在电商高并发场景下，上述代码可能会遇到如下问题：

1. 高并发下的热点问题：当大量请求同时尝试设置锁时，可能会造成Redis服务器的瓶颈，导致性能问题或者Redis连接池耗尽。
2. 潜在的死锁问题：如果设置锁成功但在后续业务逻辑执行失败，可能导致锁无法释放，产生死锁情况。

针对这些问题，有以下更好的解决方案：

1. 使用Redlock算法：Redlock是一个多实例的分布式锁算法，可以在多个Redis实例上协同工作，以减少单点故障和提高可用性。通过在多个独立的Redis实例上设置锁，即使某个实例发生故障，也不会影响整体的锁机制。可以使用Redlock的Java库来实现这一点。（官方已废弃）
2. 引入分布式锁框架：有一些开源的分布式锁框架（例如ZooKeeper、Curator等）可以在分布式系统中提供高性能和更可靠的分布式锁服务。这些框架提供了更多高级的锁功能，例如可重入锁、公平锁、读写锁等，以及更好的解决死锁问题的方案。（zookeeper性能不如redis）
3. 使用分布式锁中间件：一些专门的分布式锁中间件（如Redisson、Spring Cloud的分布式锁等）提供了成熟的分布式锁解决方案，并提供了对分布式锁的管理和监控等功能，能够更好地适应高并发场景。

这些更好的解决方案可以有效地解决上述问题，并提供更高性能、更可靠的分布式锁服务，适应电商高并发场景的要求。



## Redission

Redisson 是一个基于 Redis 的 Java 驻内存数据网格（In-Memory Data Grid）和基于 Java 的分布式锁服务。它提供了许多优势，其中包括：

1. **高性能**：Redisson 提供了分布式锁的高性能实现，利用了 Redis 的单线程模型和高效的内存存储，能够在高并发情况下快速响应。
2. **多种锁类型**：除了简单的分布式锁外，Redisson 还提供了诸如可重入锁、公平锁、读写锁等各种类型的分布式锁，满足不同场景下的需求。
3. **监控和管理**：Redisson 提供了监控和管理工具，可以监视分布式锁的使用情况、锁的持有者等信息，并支持动态调整锁的超时时间和有效期。
4. **Spring Boot 集成**：Redisson 提供了与 Spring Boot 集成的支持，可以方便地在 Spring Boot 项目中使用分布式锁服务。

以下是一个简单的示例代码，演示了如何在 Spring Boot 项目中使用 Redisson 实现分布式锁：

接下来，我们编写一个服务类，使用 Redisson 提供的分布式锁功能：

``` java
@Service
public class OrderService {
    @Autowired
    private RedissonClient redisson;

    public boolean placeOrderWithLock(String orderId) {
        RLock lock = redisson.getLock("order_lock_" + orderId);
        try {
            // 尝试获取锁，等待时间为5秒，锁的超时时间为10秒
            boolean lockAcquired = lock.tryLock(5, 10, TimeUnit.SECONDS);
            if (lockAcquired) {
                // 获取到锁，执行下单操作
                // ...
                return true;
            } else {
                // 未获得锁，可能是其他线程正在处理相同的订单
                return false;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            lock.unlock();
        }
    }
}

```

在上述代码中，我们通过注入 `RedissonClient` 实例来获取 Redisson 锁对象 `RLock`，然后使用 `tryLock` 方法来尝试获取锁。其中，第一个参数表示等待时间，第二个参数表示锁的超时时间。在锁获取成功后，执行完业务逻辑后，使用 `unlock` 方法来释放锁。

总的来说，Redisson 提供了更加灵活和高性能的分布式锁解决方案，在 Spring Boot 项目中能够方便地集成和使用。

## 集成 Lua脚本

集成Lua脚本实现分布式锁具有以下优势：

1. **原子性操作**：通过Lua脚本可以将多个命令打包成一个原子操作，从而在Redis服务器端以原子方式执行多个命令，确保了分布式锁设置的原子性。
2. **减少网络开销**：Lua脚本可以在Redis服务器端执行，可以减少客户端与服务器之间的通信开销，提高性能。
3. **避免竞态条件**：通过Lua脚本执行，可以避免分布式锁设置的竞态条件，即多个客户端同时尝试设置锁时发生的问题。
4. **性能优化**：Lua脚本在Redis服务器端预编译和缓存，可以提高执行效率，并且能够复用已经存在的脚本缓存，从而进一步提升性能。
5. **灵活性**：Lua脚本天生具有逻辑判断、循环等编程特性，可以更方便地编写一些复杂的锁逻辑，扩展性较高。

综上所述，通过Lua脚本实现分布式锁能够保证原子性、减少网络开销、避免竞态条件，提高性能，并且具有较高的灵活性，是一种较为优秀的分布式锁实现方式。

# redis集群扩展节点！！！复杂



# Jedis与Redisson对比

Jedis和Redisson都是基于Java语言的Redis客户端库，它们都能够方便地实现与Redis服务器交互的功能，但是它们在很多方面有所不同。

1. **同步/异步**：Jedis是一款同步的、阻塞式的连接客户端，而Redisson是一款支持异步操作以及响应式编程的连接客户端。
2. **API风格**：Redisson提供了丰富且易用的高级功能，比如分布式对象、分布式锁、分布式集合等，这使得它在应对复杂的分布式场景时更为方便。而Jedis相对更为底层，需要用户自行处理很多细节。
3. **功能扩展**：Redisson内置了丰富的高级功能，以促进对于Redis支持的高级功能的使用。而在Jedis中，一些比较高级的功能需要用户自己来处理。
4. **性能**：在一些基准测试中表明，Redisson在一些场景下可能会比Jedis略慢，因为Redisson对Redis协议进行了封装和增强，使得在一些场景下可能存在一定的性能损耗。不过在大部分情况下，这些性能损耗并不明显，并且通过使用异步操作，Redisson能够在高并发场景下表现得更为出色。

综上所述，Redisson提供了更为丰富和便捷的功能，并且能够很好地支持复杂的分布式场景。相对来说，Jedis则更适合于基础的、简单的 Redis 操作需求。选择使用哪种客户端库取决于具体的业务场景和需求。



# Redisson分布式锁与RedLock区别

Redisson与Redlock在实现分布式锁方面有一些区别和优劣势，主要体现在以下几个方面：

1. **实现复杂度：**
   - Redisson：Redisson封装了使用Redis的分布式锁，并提供了简单易用的API和丰富的功能，开发者可以更容易地使用分布式锁功能。
   - Redlock：Redlock是一个基于多个独立的Redis实例实现的算法，实现起来相对复杂，需要手动处理分布式系统可能出现的网络分区等问题。
2. **性能：**
   - Redisson：Redisson使用了Redis的<u>原生命令</u>来实现分布式锁，具有较高的性能。
   - Redlock：Redlock的性能较为复杂，因为它需要与多个Redis实例进行交互，并且需要考虑到时钟漂移等因素影响分布式锁的准确性。
3. **可靠性：**
   - Redisson：由于使用了Redis的<u>原生命令</u>，并且Redis本身具有较高的可靠性，因此Redisson实现的分布式锁具有较高的可靠性。
   - Redlock：Redlock需要在算法层面<u>解决分布式系统环境</u>中可能出现的问题（如时钟漂移、网络分区），因此相对来说更容易受到环境影响，可靠性相对较低。

总的来说，Redisson相对于Redlock来说更易用，性能更好，并且在可靠性方面更加稳定。但是需要注意的是，对于很高要求一致性的分布式锁场景，仍需要慎重考虑使用Redlock或类似的严格分布式锁算法。



#  使用redis bitmap进行一亿个keys的统计，用java来实现，给出完整示例，并分析时间复杂度

1. 首先，确保已安装并导入 Jedis 依赖：

```xml
<!-- Maven -->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.7.0</version>
</dependency>
```

Copy

1. 接下来使用Jedis实现Redis Bitmaps的java示例：

   ```java
   import redis.clients.jedis.Jedis;
   
   public class RedisBitmapExample {
       private static final String BITMAP_KEY = "bitmap:keys";
       private static final int MAX_KEYS = 1_000_000_000; // One billion keys
   
       public static void main(String[] args) {
           try (Jedis jedis = new Jedis("localhost")) {
               // Set up the bitmap with one billion bits initialized to 0
               for (int i = 0; i < MAX_KEYS; i++) {
                   jedis.setbit(BITMAP_KEY, i, false);
               }
   
               // Perform some operations on the bitmap
               long keyToMark = 123456789;
               jedis.setbit(BITMAP_KEY, keyToMark, true); // Mark a specific key as present
   
               boolean isKeyPresent = jedis.getbit(BITMAP_KEY, keyToMark); // Check if key is present
               System.out.println("Is key " + keyToMark + " present? " + isKeyPresent);
   
               long uniqueKeysCount = jedis.bitcount(BITMAP_KEY); // Count the number of set bits (present keys)
               System.out.println("Number of unique keys: " + uniqueKeysCount);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
   ```

   Copy

2. 时间复杂度分析：

   - 初始化 Bitmap：
     使用 jedis.setbit(key, offset, value) 进行循环设置。
     时间复杂度为 O(n)，其中 n 是 Bitmap 的大小（在这里为一亿个 bits）。由于 Redis 在内部对 Bitmaps 进行优化，实际操作速度可能会比理论时间复杂度更快。
   - 标记 key 为存在：
     使用 jedis.setbit(key, offset, value) 设置特定位置的 bit。
     时间复杂度为 O(1)，因为 Redis 可以直接通过偏移量定位到对应的 bit 并进行修改。
   - 检查 key 是否存在：
     使用 jedis.getbit(key, offset) 获取特定位置的 bit 值。
     时间复杂度为 O(1)，同样是因为直接通过偏移量访问，无需遍历整个 Bitmap。
   - 计算 unique keys 的数量：
     使用 jedis.bitcount(key) 计算 Bitmap 中值为 1 的 bit 数量。
     时间复杂度为 O(n)，但在实际应用中，Redis 采用了优化算法（如广义布隆过滤器），对于稀疏 Bitmap（大部分 bits 为 0），该操作的平均时间复杂度接近 O(1)。
   - 总结：使用 Redis Bitmaps 和 Java（Jedis 客户端）进行一亿个 keys 的统计，主要操作（标记、查询、计数）具有很好的时间效率，大部分操作的时间复杂度为 O(1) 或接近 O(1)。这种数据结构特别适合于大规模数据的二值状态统计场景。

# start redis

brew install redis
brew services start redis 
brew services restart redis

