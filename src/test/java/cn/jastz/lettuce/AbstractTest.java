package cn.jastz.lettuce;

import io.lettuce.core.RedisClient;
import org.junit.After;
import org.junit.Before;

/**
 * @author zhiwen
 */
public class AbstractTest {

    protected RedisClient redisClient;

    @Before
    public void before() {
        System.out.println("初始化客户端");
        redisClient = RedisClient.create("redis://localhost");
    }

    @After
    public void after() {
        System.out.println("关闭客户端");
        redisClient.shutdown();
        redisClient = null;
    }
}
