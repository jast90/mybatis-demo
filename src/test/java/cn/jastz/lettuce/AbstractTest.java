package cn.jastz.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.After;
import org.junit.Before;

/**
 * @author zhiwen
 */
public class AbstractTest {

    protected RedisClient redisClient;
    protected RedisCommands<String, String> commands;

    @Before
    public void before() {
        System.out.println("初始化客户端");
        redisClient = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        commands = connection.sync();
    }

    @After
    public void after() {
        System.out.println("关闭客户端");
        redisClient.shutdown();
        redisClient = null;
    }
}
