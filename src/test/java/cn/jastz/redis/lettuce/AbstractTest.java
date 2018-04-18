package cn.jastz.redis.lettuce;

import cn.jastz.redis.lettuce.pubsub.MyRedisPubSubAdapter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.junit.After;
import org.junit.Before;

/**
 * @author zhiwen
 */
public class AbstractTest extends RedisPubSubAdapter {

    protected RedisClient redisClient;
    protected RedisCommands<String, String> commands;
    protected RedisPubSubCommands<String, String> sysc;
    protected RedisPubSubAsyncCommands<String, String> asysc;
    protected StatefulRedisPubSubConnection statefulRedisPubSubConnection;


    @Before
    public void before() {
        System.out.println("初始化客户端");
        redisClient = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        commands = connection.sync();
        statefulRedisPubSubConnection = redisClient.connectPubSub();
        statefulRedisPubSubConnection.addListener(new MyRedisPubSubAdapter());
        sysc = statefulRedisPubSubConnection.sync();
        asysc = statefulRedisPubSubConnection.async();
    }

    @After
    public void after() {
        System.out.println("关闭客户端");
        redisClient.shutdown();
        redisClient = null;
    }
}
