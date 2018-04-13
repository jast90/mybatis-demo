package cn.jastz.lettuce;

import io.lettuce.core.Range;
import io.lettuce.core.ScoredValue;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * @author zhiwen
 */
public class RedisSortedSets extends AbstractTest {

    @Test
    public void zadd() {
        String key = "zadd-key";
        commands.zadd(key, 3, "hello");
        commands.zadd(key, ScoredValue.from(2, Optional.of("World")));
        Assert.assertTrue(commands.zcard(key) == 2);
    }

    @Test
    public void zscard() {
        String key = "zscard-key";
        commands.zadd(key, 1, "hello");
        commands.zadd(key, 2, "hello");
        Assert.assertTrue(commands.zcard(key) == 1);
    }

    @Test
    public void zcount() {
        String key = "zcount-key";
        commands.zadd(key, 88, "A");
        commands.zadd(key, 88, "a");
        commands.zadd(key, 87, "B");
        commands.zadd(key, 88, "C");
        commands.zadd(key, 96, "D");
        commands.zadd(key, 77, "E");
        Assert.assertTrue(commands.zcount(key, Range.create(88, 90)) == 3);
    }

    @Test
    public void zincrby() {
        String key = "zincrby-key";
        commands.zadd(key, 88, "A");
        commands.zadd(key, 88, "B");
        commands.zincrby(key, 1, "A");
    }

    /**
     * 多个集合的交集分值相加存入新的key
     */
    @Test
    public void zinterstore() {
        String key = "zinterstore-key-";
        commands.zadd(key + 1, 88, "A");
        commands.zadd(key + 1, 88, "B");
        commands.zadd(key + 2, 88, "B");
        commands.zadd(key + 2, 88, "C");
        commands.zinterstore(key + "copy", key + 1, key + 2);
        Assert.assertTrue(commands.zcard(key + "copy") == 1);
    }

    @Test
    public void zlexcount() {
        String key = "zlexcount-key";
        commands.del(key);
        commands.zadd(key, 80, "Aa1");
        commands.zadd(key, 81, "Aa2");
        commands.zadd(key, 82, "Aa3");
        commands.zadd(key, 90, "Ab");
        commands.zadd(key, 30, "Cc");
        System.out.println(commands.zlexcount(key, Range.create("Ab", "Cc")));
        Assert.assertTrue(commands.zlexcount(key, Range.create("Ab", "Cc")) == 5);
    }
}
