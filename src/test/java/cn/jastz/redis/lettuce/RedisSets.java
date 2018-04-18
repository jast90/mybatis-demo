package cn.jastz.redis.lettuce;

import io.lettuce.core.ScanArgs;
import io.lettuce.core.ValueScanCursor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhiwen
 */
public class RedisSets extends AbstractTest {

    @Test
    public void sadd() {
        String key = "sadd-key";
        commands.sadd(key, "hello", "hello", "world");
        Assert.assertTrue(commands.scard(key) == 2);
    }

    @Test
    public void scard() {
        String key = "scard-key";
        commands.sadd(key, "hello", "hello", "world");
        Assert.assertTrue(commands.scard(key) == 2);
    }

    /**
     * 返回前面存在但后面不存在的元素
     */
    @Test
    public void sdiff() {
        String key = "sdiff-key-";
        commands.sadd(key + 1, "hello", "hello", "world");
        commands.sadd(key + 2, "reids", "hello", "world", "java");
        System.out.println(commands.sdiff(key + 2, key + 1));
        Assert.assertTrue(commands.sdiff(key + 2, key + 1).size() == 2);
    }

    /**
     * 返回前面存在但后面不存在的元素,并将这些元素存到指定的key中
     */
    @Test
    public void sdiffstore() {
        String key = "sdiffstore-key-";
        commands.sadd(key + 1, "hello", "world");
        commands.sadd(key + 2, "reids", "hello", "world", "java");
        commands.sdiffstore(key + 0, key + 2, key + 1);
        Assert.assertTrue(commands.scard(key + 0) == 2);
    }

    /**
     * 返回两个set中的交集
     */
    @Test
    public void sinter() {
        String key = "sinter-key-";
        commands.sadd(key + 1, "hello", "world");
        commands.sadd(key + 2, "reids", "hello", "world", "java");
        System.out.println(commands.sinter(key + 1, key + 2));
        Assert.assertTrue(commands.sinter(key + 1, key + 2).size() == 2);
    }

    /**
     * 返回两个set中的交集并存储到另外的set
     */
    @Test
    public void sinterstore() {
        String key = "sinterstores-key-";
        commands.sadd(key + 1, "hello", "world");
        commands.sadd(key + 2, "reids", "hello", "world", "java");
        commands.sinterstore(key + 0, key + 1, key + 2);
        Assert.assertTrue(commands.scard(key + 0) == 2);
    }

    @Test
    public void sismember() {
        String key = "sismember-key";
        commands.sadd(key, "hello", "world");
        Assert.assertTrue(commands.sismember(key, "hello"));
    }

    @Test
    public void smembers() {
        String key = "smembers-key";
        commands.sadd(key, "hello", "world");
        System.out.println(commands.smembers(key));
        Assert.assertTrue(commands.scard(key) == 2);
    }

    @Test
    public void smove() {
        String key = "smove-key";
        commands.sadd(key, "hello", "world");
        commands.smove(key, key + "-copy", "hello");
        Assert.assertTrue(commands.scard(key + "-copy") == 1);
    }

    /**
     * 随机删除并返回一个或多个元素
     */
    @Test
    public void spop() {
        String key = "spop-key";
        commands.del(key);
        commands.sadd(key, "hello", "world", "redis");
        System.out.println(commands.spop(key, 2));//没有设置count返回一个，设置了返回多个
        Assert.assertTrue(commands.scard(key) == 1);
    }

    /**
     * 从set中随机获取一个元素但不删除
     */
    @Test
    public void srandmember() {
        String key = "srandmember-key";
        commands.del(key);
        commands.sadd(key, "hello", "world", "redis");
        System.out.println(commands.srandmember(key, 2));//
        Assert.assertTrue(commands.scard(key) == 3);
    }

    @Test
    public void srem() {
        String key = "srem-key";
        commands.del(key);
        commands.sadd(key, "hello", "world", "redis");
        System.out.println(commands.srem(key, "redis"));//
        Assert.assertTrue(commands.scard(key) == 2);
    }

    @Test
    public void sunion() {
        String key = "sunion-key";
        commands.del(key + 1, key + 2, key);
        commands.sadd(key + 1, "hello", "world", "redis");
        commands.sadd(key + 2, "java", "css", "redis");
        Assert.assertTrue(commands.sunion(key + 1, key + 2).size() == 5);
    }

    @Test
    public void sunionstore() {
        String key = "sunionstore-key";
        commands.del(key + 1, key + 2, key);
        commands.sadd(key + 1, "hello", "world", "redis");
        commands.sadd(key + 2, "java", "css", "redis");
        commands.sunionstore(key, key + 1, key + 2);
        Assert.assertTrue(commands.scard(key) == 5);
    }

    @Test
    public void sscan() {
        String key = "sscan-key";
        commands.del(key);
        commands.sadd(key, "hello", "world", "redis");
        ValueScanCursor scanCursor = commands.sscan(key, ScanArgs.Builder.matches("redis*"));
        System.out.println(scanCursor.getValues());
        Assert.assertTrue(scanCursor.getValues().size() == 1);
    }
}
