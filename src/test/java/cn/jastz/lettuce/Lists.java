package cn.jastz.lettuce;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhiwen
 */
public class Lists extends AbstractTest {

    @Test
    public void lpush() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "lpush-key";
        long count = commands.lpush(key, "hello", "world");
        Assert.assertTrue(count == 2);
    }

    @Test
    public void lpushx() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "lpushx-key";
        commands.lpush(key, "hello");
        long count = commands.lpushx(key, "world");
        System.out.println(count);
        Assert.assertTrue(count == 2);
    }

    @Test
    public void rpush() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "rpush-key";
        long count = commands.rpush(key, "hello", "world");
        Assert.assertTrue(count == 2);
    }

    @Test
    public void rpushx() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "rpushx-key";
        commands.del(key);
        commands.lpush(key, "hello", "world");
        long count = commands.rpushx(key, "hello");
        Assert.assertTrue(count == 3);
    }
}
