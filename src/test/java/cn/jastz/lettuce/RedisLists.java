package cn.jastz.lettuce;

import io.lettuce.core.KeyValue;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * push是将元素插入队列
 * pop是将元素从队列一头删除并返回
 *
 * @author zhiwen
 */
public class RedisLists extends AbstractTest {

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
        long count = commands.lpushx(key, "world");//这个也不能设置多个值
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
        long count = commands.rpushx(key, "hello");//这个不能设置多个值
        Assert.assertTrue(count == 3);
    }

    @Test
    public void lpop() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "lpop-key", value = "hello";
        commands.del(key);
        commands.lpush(key, value);
        commands.rpush(key, value + 1);
        String va = commands.lpop(key);
        Assert.assertTrue(Objects.equals(va, "hello"));
    }

    @Test
    public void rpop() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "rpop-key", value = "hello";
        commands.del(key);
        commands.lpush(key, value);
        commands.rpush(key, value + 1);
        String va = commands.rpop(key);
        Assert.assertTrue(Objects.equals(va, "hello1"));
    }

    @Test
    public void rpoplpush() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String resourceKey = "rpoplpush-source-key", destinationKey = "rpoplpush-destination-key", value = "hello";
        commands.del(resourceKey);
        commands.del(destinationKey);
        commands.lpush(resourceKey, value);
        commands.rpush(resourceKey, value + 1);
        String va = commands.rpoplpush(resourceKey, destinationKey);
        Assert.assertTrue(Objects.equals(va, "hello1"));
        Assert.assertTrue(commands.llen(destinationKey) > 0);
    }

    @Test
    public void llen() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "llen-key", value = "hello";
        commands.del(key);
        commands.lpush(key, value, value + "1", value + "2");
        Assert.assertTrue(commands.llen(key) == 3);
    }

    @Test
    public void linsert() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "linsert-key", value = "hello";
        commands.del(key);
        commands.lpush(key, value);
        commands.linsert(key, false, value, "world");
        Assert.assertTrue(commands.llen(key) == 2);
    }

    @Test
    public void lindex() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "lindex-key", value = "hello";
        commands.del(key);
        commands.lpush(key, value, value + "1");
        Assert.assertTrue(Objects.equals(commands.lindex(key, 0), "hello1"));
    }

    @Test
    public void lrange() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "lrange-key", value = "hello";
        commands.del(key);
        commands.lpush(key, value);
        for (int i = 0; i < 10; i++) {
            commands.lpush(key, value + i);
        }
        System.out.println(commands.lrange(key, 0, -1));
    }

    @Test
    public void lrem() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "lrem-key", value = "hello";
        commands.del(key);
        commands.lpush(key, value);
        for (int i = 0; i < 10; i++) {
            commands.lpush(key, value);
        }
        Assert.assertTrue(commands.lrem(key, -2, "hello") == 2); //移除最后2个出现的"hello"
//        Assert.assertTrue(commands.lrem(key, 0, "hello") == 11);//移除所有出现的"hello"
//        Assert.assertTrue(commands.lrem(key, 2, "hello") == 2);//移除最前2个出现的"hello"
    }

    @Test
    public void lset() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "lset-key", value = "hello";
        commands.del(key);
        commands.rpush(key, value, "world");
        commands.lset(key, 1, "Redis");
        Assert.assertTrue(Objects.equals(commands.lindex(key, 1), "Redis"));
    }

    @Test
    public void ltrim() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "ltrim-key", value = "hello";
        commands.del(key);
        commands.rpush(key, value);
        commands.ltrim(key, 0, 1);
        Assert.assertTrue(commands.llen(key) == 1);
    }

    /**
     * 当队列中不存在任何元素时会一直阻塞到超时，然后返回null
     *
     * @see RedisLists#blpop1() 运行该方法放入元素
     */
    @Test
    public void blpop() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "blpop-key", value = "hello";
        KeyValue<String, String> keyValue = commands.blpop(10 * 3, key);
        if (keyValue != null) {
            System.out.println(keyValue.getValue());
        }
        Assert.assertTrue(keyValue != null);

    }

    @Test
    public void blpop1() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "blpop-key", value = "hello";
        commands.rpush(key, value);

    }

    @Test
    public void brpop() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "brpop-key", value = "hello";
        KeyValue<String, String> keyValue = commands.brpop(10 * 3, key);
        if (keyValue != null) {
            System.out.println(keyValue.getValue());
        }
        Assert.assertTrue(keyValue != null);

    }

    @Test
    public void brpop1() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "brpop-key", value = "hello";
        commands.rpush(key, value);
    }


    @Test
    public void brpoplpush() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "brpoplpush-source-key", destinationKey = "brpoplpush-destination-key", value = "hello";
        /**
         * 如果brpoplpush-source-key没有任何元素brpoplpush将会阻塞直到队列有元素，如果超过设置超时时间将会报超时或操作失败。
         */
        commands.brpoplpush(10 * 3, key, destinationKey);
    }

    @Test
    public void brpoplpush1() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "brpoplpush-source-key", value = "hello";
        commands.rpush(key, value);
    }
}
