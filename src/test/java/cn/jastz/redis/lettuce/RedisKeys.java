package cn.jastz.redis.lettuce;

import io.lettuce.core.SortArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * @author zhiwen
 */
public class RedisKeys extends AbstractTest {

    @Test
    public void del() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "del-key", value = "Hello";
        commands.set(key, value);
        try {
            Thread.sleep(1000 * 10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(commands.del(key) == 1);
    }

    @Test
    public void unlink() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "unlink-key", value = "Hello";
        commands.set(key, value);
        try {
            Thread.sleep(1000 * 10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(commands.unlink(key) == 1);
    }

    @Test
    public void dump() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "dump-key", value = "Hello";
        commands.set(key, value);
        try {
            Thread.sleep(1000 * 10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new String(commands.dump(key)));
    }

    @Test
    public void exists() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "exists-key", value = "Hello";
        commands.set(key, value);
        Assert.assertTrue(commands.exists(key, "incr-key") == 2);
    }

    @Test
    public void expire() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "expire-key", value = "Hello";
        commands.set(key, value);
        commands.expire(key, 10);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(commands.exists(key) == 0);
    }

    @Test
    public void pexpire() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "pexpire-key", value = "Hello";
        commands.set(key, value);
        commands.pexpire(key, 10 * 1000);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(commands.exists(key) == 0);
    }

    @Test
    public void expireat() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "expireat-key", value = "Hello";
        commands.set(key, value);
        commands.expireat(key, new Date().getTime() / 1000 + 10);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(commands.exists(key) == 0);
    }

    /**
     * 按毫秒设置过期时间
     */
    @Test
    public void pexpireat() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "pexpireat-key", value = "Hello";
        commands.set(key, value);
        commands.pexpireat(key, new Date().getTime() + 10 * 1000);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(commands.exists(key) == 0);
    }

    @Test
    public void keys() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "keys-key", value = "Hello";
        commands.set(key + ":1", value);
        commands.set(key + ":2", value);
        Assert.assertTrue(commands.keys("keys-key*").size() > 0);
    }

    @Test
    public void move() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "move-key", value = "Hello";
        commands.set(key, value);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        commands.move(key, 2);
    }

    /**
     * 键所关联的值引用的对象个数
     */
    @Test
    public void objectRefCount() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "objectRefCount-key", value = "Hello";
        commands.set(key, value);
        System.out.println(commands.objectRefcount(key));
    }

    /**
     * 返回对象的编码
     * embstr int ...
     */
    @Test
    public void objectEncoding() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "objectEncoding-key", value = "1";
        commands.set(key, value);
        System.out.println(commands.objectEncoding(key));
    }

    @Test
    public void objectIdletime() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "objectIdletime-key", value = "1";
        commands.set(key, value);
        System.out.println(commands.objectIdletime(key));
    }

    /**
     * 如果key没有设置过期时间则返回-1，否则返回有效期时间毫秒
     */
    @Test
    public void pttl() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "pttl-key", value = "1";
//        commands.set(key,  value);//输出-1
        commands.setex(key, 10, value);//输出有效毫秒数
        System.out.println(commands.pttl(key));
    }

    /**
     * 如果key没有设置过期时间则返回-1，否则返回有效期时间秒
     */
    @Test
    public void ttl() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "ttl-key", value = "Hello ";
//        commands.set(key,  value);//输出-1
        commands.setex(key, 10, value);//输出有效秒数
        System.out.println(commands.ttl(key));
    }

    /**
     * 如果key没有设置有效期persist返回false,设置了则返回true
     */
    @Test
    public void persist() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "persist-key", value = "1";
        commands.setex(key, 10, value);
        System.out.println(commands.pttl(key));
        System.out.println(commands.persist(key));//输出true
        System.out.println(commands.pttl(key));//输出-1
    }

    /**
     * 返回redis中存在的key中的任意一个key
     */
    @Test
    public void randomkey() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        System.out.println(commands.randomkey());
    }

    /**
     * 返回OK
     */
    @Test
    public void rename() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "rename-key", value = "1";
        commands.set(key, value);
        System.out.println(commands.rename(key, key + "-1"));
    }

    /**
     * 新key存在时，返回false,成功时返回 true
     */
    @Test
    public void renamenx() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "rename-key", value = "1";
        commands.set(key, value);
        System.out.println(commands.renamenx(key, key + "-2"));
    }

    @Test
    public void type() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "type-key", value = "1";
        commands.set(key, value);
        System.out.println(commands.type(key));
    }

    @Test
    public void sort() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        String key = "sort-key", value = "1";
        commands.del(key);
        commands.rpush(key, "a", "e", "c", "d");
        System.out.println(commands.sort(key, SortArgs.Builder.alpha()));
    }
}
