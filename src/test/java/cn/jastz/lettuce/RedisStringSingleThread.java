package cn.jastz.lettuce;

import com.google.common.collect.Maps;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author zhiwen
 */
public class RedisStringSingleThread extends AbstractTest {

    @Test
    public void append() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        if (commands.del("append-key") > 0) {
            System.out.println("删除存在的Key");
        }
        commands.set("append-key", "Hello ");
        commands.append("append-key", "World");
        Assert.assertTrue("Hello World".equals(commands.get("append-key")));
    }

    @Test
    public void bitCount() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisStringCommands<String, String> commands = connection.sync();
        System.out.println(commands.bitcount("string-key"));
        Assert.assertTrue(commands.bitcount("string-key") > 0);
    }

    @Test
    public void setAndGet() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisStringCommands<String, String> commands = connection.sync();
        commands.set("string-key", "Hello ");
        Assert.assertTrue("Hello".equals(commands.get("string-key")));
    }

    @Test
    public void strLen() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisStringCommands<String, String> commands = connection.sync();
        String key = "strLen-key", value = "Hello World";
        commands.set(key, value);
        long len = commands.strlen(key);
        Assert.assertTrue(len == value.length());
    }

    @Test
    public void setRange() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisStringCommands<String, String> commands = connection.sync();
        String originKey = "setRange-origin-key", key = "setRange-key", value = "Hello World";
        Map<String, String> map = Maps.newHashMap();
        map.put(originKey, value);
        map.put(key, value);
        commands.mset(map);
        commands.setrange(key, 6, "world");
        Assert.assertTrue("Hello world".equals(commands.get(key)));
    }

    @Test
    public void setNX() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisStringCommands<String, String> commands = connection.sync();
        String key = "setnx-key", value = "Hello World";
        commands.setnx(key, value);
        Assert.assertTrue("key已经存在了", commands.setnx(key, value) == false);
    }

    @Test
    public void setEx() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "setEx-key", value = "Hello World";
        commands.setex(key, 1 * 10, value);
        try {
            Thread.sleep(1000 * 10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue("key已经存在了", commands.exists(key) == 0);
    }

    @Test
    public void incrby() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "incrby-key", value = "1";
        commands.set(key, value);
        Assert.assertTrue(commands.incrby(key, 1) == 2);
    }

    @Test
    public void incrbyfloat() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "incrbyfloat-key", value = "1";
        commands.set(key, value);
        Assert.assertTrue(commands.incrbyfloat(key, -1.2) == -.2);
    }

    @Test
    public void incr() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "incr-key", value = "1";
        commands.set(key, value);
        Assert.assertTrue(commands.incr(key) == 2);
    }

    @Test
    public void decrby() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "decrby-key", value = "1";
        commands.set(key, value);
        Assert.assertTrue(commands.decrby(key, 1) == 0);
    }

    @Test
    public void decr() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "decr-key", value = "1";
        commands.set(key, value);
        Assert.assertTrue(commands.decr(key) == 0);
    }

    @Test
    public void mset() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        Map<String, String> map = Maps.newHashMap();
        map.put("mset-key:1", "Hello");
        map.put("mset-key:2", "World");
        commands.mset(map);
        Assert.assertTrue("Hello".equals(commands.get("mset-key:1")));
    }

    @Test
    public void mget() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        commands.set("mget-key:1", "Hello");
        commands.set("mget-key:2", "World");
        Assert.assertTrue(commands.mget("mset-key:1", "mget-key:2").size() == 2);
    }
}
