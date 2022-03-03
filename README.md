# middleware-redis
redis的相关面试问题实际代码操作

#### redis面试中可能遇到的问题

1. redis的数据结构的基本应用场景
2. redis如何解决 缓存和数据库不一致的问题
   1. 可以采用延时双删的方式解决，主要操作是：每当更新数据时，先删除redis缓存数据，再更新数据库，在更新数据库的时间范围内，会直接请求数据库拿到旧数据并存入缓存，此时设置一个毫秒级别的休眠，再进行删除缓存操作
   2. 也可以采用异步更新缓存的方式，主要操作是：基本数据库的bin log +消息队列+更新redis。监听bin log的insert update delete事件，对redis进行更新
3. redis搭建集群
4. redis锁是如何实现的，Lua和redisson
   1. Lua脚本进行redis的加锁时，使用set命令代替 setnx 和 expire 命令，保证原子性，同时为了保证删除的是自己加的锁，一般再设置锁的时候添加唯一标识，当删除锁时，先根据key查询value再根据自己的value比较redis中的value 是否一致，一致则解锁，否则跳过。如果解锁未成功或者出现其他状况导致无法解锁，redis 会根据过期时间删除key，从而达到不会出现死锁的情况。
5. 缓存雪崩
   1. 什么是缓存雪崩？缓存雪崩是指大量的缓存数据在同一个时间段全部过期，导致大量请求落到数据库上，给数据库带来了压力，可能造成数据库宕机。
   2. 出现缓存雪崩的场景：1.redis宕机  2.缓存过期
   3. 解决方式：可以在原有的过期时间上加上一个随机值，将过期时间分散
   4. 使用熔断机制
6. 缓存穿透
   1. 什么是缓存穿透？如果在请求数据时，在数据库和缓存中都没有查询到符合的数据，这种情况就叫做缓存穿透。最主要的原因则是：查询某个key时，因为缓存中没有数据，导致每次查询都需要去请求数据库，数据库返回的是null，而redis不会缓存这个结果。这样就造成了每次通过这样的key去查询数据都会直接到数据库中查询，redis不会缓存空数据。
   2. 解决方式：1. 将空对象缓存在redis中，并设置合适的过期时间 2. 使用布隆过滤器，布隆过滤器的实现原理，采用了bitmap作为底层数据结构，采用多个映射函数对key值进行映射hash分布到bitmap上，这样新查询的key如果根据映射函数映射到bitmap结构上的结果都存在，那么他在数据库中是可能存在的，如果在bitmap上不存在，那么他一定是不存在的。为什么说他是可能存在的，因为bitmap是由多个key生成的，会存在，key1 被三个映射函数映射到123位置，key2被三个映射函数映射到456位置，此时key3通过映射函数映射到的位置是345，则通过了布隆过滤器的过滤，从而进行查询缓存，查询数据库操作，但是其实这个key3不一定存在，这其实就是布隆过滤器的误判率。布隆过滤器的特性就是一个元素如果判断结果为存在时并不一定存在，如果判断一个元素不存在时，那他一定不存在。布隆过滤器可以添加元素，但是不能删除元素，因为删除元素的bitmap的点可能是其他多个key共同映射生成的。
7. 缓存击穿
   1. 什么是缓存击穿？和缓存雪崩有点类似，但是不同的则是缓存击穿是一个热点的key，有大量的并发对其进行访问，突然这个key失效了，导致大量的请求打在了数据库上，导致数据库压力剧增
   2. 解决方式：使用互斥锁，如果是单机系统下可以使用Lock类型就可以了，如果是集群模式可以采用redis的分布式锁，当根据key请求获得的value值为空时，先锁上，再从数据库加载，加载完毕释放锁，如果其他线程发现获取锁失败，则可以采用睡眠重试获取锁机制



#### RabbitMQ面试中可能遇到的问题  [RabbitMQ如何保证消息不丢失 - 简书 (jianshu.com)](https://www.jianshu.com/p/d7dd40a15798)

1. 如何保证消息不丢失
   1. 可能发生消息丢失的场景：1.因为网络原因导致发送到exchange失败了  2.路由失败了  3. 客户端处理消息时失败了 4.消息未持久化服务重启消息丢失
   2. 解决方式  
      1. 解决1场景：如果是生产者发送消息到exchange失败，则可以采用事务机制实现，或者采用channel的confirm模式。confirm机制和事务机制是互斥的，confirm机制的实现在于添加一个confirmListener，confirm模式在开启时，每次向RabbitMQ发送消息时都会生成一个唯一的id，如果消息发送成功了，则RabbitMQ会发送给客户端一个ack的确认，通过这个唯一的id就可以确认哪个消息发送成功了
      2. 解决2场景：路由失败了，可以开启 mandatory 参数，如果mandatory 参数为true，exchange无法根据自身类型和RountingKey找到对于的queue，RabbitMQ将不会丢掉该消息，而是将消息返回给生产者。
      3. 解决场景3：生产者有发送消息的ack和nack，而消费者也可手动开启ack模式，当消费者接收到消息时error是，将requeue设置成true，这将使得这个消息重新回到队列
      4. 解决场景4：可以通过durable 参数实现消息持久化
2. 如果需要消息顺序处理，应该如何处理
   1. 如果一个queue对于一个消费者的话，是不会出现消息错乱的情况。
   2. 如果是一个queue需要多个消费者处理的话，拿一个订单的例子来说，一个订单和其他订单的操作关系其实没有影响，这样就可以根据订单的唯一键进行对队列的取模来保证先后顺序的同一类数据
   3. 同理，也可以只用一个消费者，消费者内部维护多个内存队列，同样取模操作保证顺序
3. 如何保证**消息的幂等性** 这个词，实际使用场景列举
   1. 消息的幂等性表示保证一条消息不会重复消费或者即使重复消费了也不会导致数据异常
   2. 出现重复消费的情况，消费者收到了队列的消息并且成功消费了，当准备回执ack时，mq挂掉了，导致mq不知道这条数据是否被成功消费了，当再次启动的时候会重新发送这条消息，导致了重复消费
   3. 保证消费的幂等性
      1. 可以用redis作为缓存，生成一个唯一的id，存入redis，每次消费之前查询redis的结果是否被消费



#### Zookeeper面试中可能遇到的问题

1. Zookeeper 是如何进行选举的
   1. 首先Zookeeper遵循CAP原则的CP，保证了一致性而牺牲了部分的高可用，这就意味着在选举的过程中，是不可用的，客户端会一直阻塞
   2. Zookeeper 默认采用的fastLeaderElection的方式进行选举，其中有四个参数影响投票方式，serverId 服务器id，zxId 数据id，epoch 逻辑时钟，server状态，一般来说值越大，权重越高。每台服务器都会给自己投票，比如现在有5台服务器，服务器1启动，给自己投票，因为没有得到其他服务器的反馈，所以这个服务器处于looking状态，此时服务器2启动，因为服务器2的数值大于服务器1，所以服务器2胜出，但是由于大于半数选举原则，所以服务器1和2都是在looking状态，此时服务器3启动，因为服务器3编号较大，所以胜出，成为了领导；此时服务器4启动，尽管服务器4的编号大，但是已经有了leader，所以服务器4也是小弟状态，后续的服务器5同理。节点卡在第三个数



#### MySQL

1. 了解事务的隔离级别吗
   1. 数据库的隔离级别有四种，分别是 读未提交、读已提交、可重复读、串行化。这几种隔离级别的出现都是为了部分解决事务并发所导致的问题，如：脏读、不可重复读、幻读，其中innodb默认使用的是可重复读的隔离级别，解决了脏读和不可重复读的问题，但是部分场景下的幻读还是会存在
   2. 事务：事务是一堆操作的集合，一个完美的事务应该具有四个特性：A 原子性：一个事务的操作要么全部成功要么全部失败 C 一致性：事务开始和结束的数据应该保持一直 I 隔离性：事务之间是不可见的 D 持久性：事务产生的变化是永久的
   3. 脏读：A事务读取了B事务未提交数据，导致了脏读，破坏了事务的隔离性； 不可重复读：A事务分别读取了两次数据，B事务在**A事务第一次读取之后，第二次读取之前**更新了数据并提交了事务，第一次读取的数据和第二次读取的数据不一致，破坏了事务的一致性，主要存在于（update 和 delete操作）；幻读：A事务读取操作时，开始和结束读取的条数不同，主要存在于（delete操作），破坏了事务的一致性
2. innodb默认的隔离级别是哪个，能否解决幻读的问题
   1. innodb默认隔离级别是可重复读，不能够完全解决幻读的问题。一般来说幻读的问题普遍是A事务查询出数据的条数是0条，B事务insert一条数据，此时A事务继续查询，查询出一条数据，导致幻读，但是因为MVCC机制，这种情况下其实是可以被避免的
   2. 第二种情况下的幻读不能够完全解决，例如：A事务查询数据是1条，此时B事务insert一条数据并提交，此时A事务执行update操作，最终两条数据都被update修改了
3. SQL中如果查询一个字段不等于某个值，有一条数据如果是null的话，会被查询出来吗
   1. 不会被查询出来， 隐式的包含了一个 is not null 的条件
4. 了解SQL优化吗
   1. 避免不走索引的场景，比如like模糊查询，出现全模糊查询和左模糊查询则不会走索引，in 和 not in 这种也不会走索引，!= 或者<> 也不会走索引，等等
   2. select 语句优化。不要使用select * ; 连表查询的时候，小表在前，大表在后
   3. insert 操作可以采用批量插入的方式 等等
5. inner join  、left join 、 right join 的区别
   1. inner join 会根据联查条件中左表和右表的数据一一对应返回
   2. left join 会以左表为中心，查询出左表的全部符合条件的数据，右表查询相对应的数据，如果没有则为null，right join 同理
6. 联合索引一般怎么建立（最左前缀法则）
   1. 遵循最左前缀法则
7. MVCC了解吗
8. 项目中有没有优化sql的经历
9. bin log , redo log , undo log 分别是啥
   1. bin log 主要是记录数据库发生insert update delete 语句
   2. redo log 主要是为了在数据库宕机之后恢复数据使用的log
   3. undo log 主要是针对数据库的事务操作的日志，比如事务中有一条insert 语句，此时会记录一条delete语句，对于每条update语句，对应一条相反的update语句，这样在程序发生错误的时候就能回滚到事务之前的数据状态
10. MySQL中 ${} 和 #{} 的区别是什么？
    1. ${} 相较于#{}，#{}在sql的预编译阶段会被解析成一个参数占位符 ？，而${}在sql动态解析阶段会进行变量替换
    2. ${} 插入值，sql解析时，不带引号，#{}插入值是，在sql解析时是带引号的
    3. 平常使用建议都使用#{}, ${}在order by 的这种动态sql中可以使用。
    4. ’#‘ 很大程度可以避免sql注入
    5. eg: 
       1. select * from student where name = #{name}  --- name= 'cy'
       2. select * from student where name = #{name}  --- name= cy
11. select for update 是干啥的
    1. select for update  的本意是为其数据添加排他锁， 并且select for update 的语句必须声明在事务中才有效
    2. 如果where里的条件是走索引的，则添加的是行锁，只会锁查询结果范围内的数据；否则添加的表锁
    3. select for update 仅针对 innodb 可用。
    4. 并且如果没有查询到数据不加锁
12. 描述一下悲观锁和乐观锁
    1. 乐观锁：乐观的认为数据不会被更改，所以查询的时候不会加锁，只在数据提交的时候，判断一下数据是否被更改
    2. 悲观锁：悲观的认为数据都可能会被更改，所以查询的时候会上表锁或者行锁，其他的流程不允许访问数据。



#### java

1. 说一下HashMap
2. 了解ConcurrentHashMap吗



#### 项目

1. 说说你在开发过程中解决过什么棘手的问题



