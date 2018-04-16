package cn.jastz.lettuce;

import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.ScoredValue;
import io.lettuce.core.ZStoreArgs;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
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

    /**
     * 对索引进行操作
     */
    @Test
    public void zrange() {
        String key = "zrange-key";
        commands.del(key);
        commands.zadd(key, 80, "Aa1");
        commands.zadd(key, 81, "Aa2");
        commands.zadd(key, 82, "Aa3");
        commands.zadd(key, 90, "Ab");
        commands.zadd(key, 30, "Cc");
        System.out.println(commands.zrange(key, 0, -1));
        Assert.assertTrue(commands.zrange(key, 0, -1).size() == 5);
    }

    /**
     * zrangebylex 应用：1. 根据需要来获取号段 2. 姓名排序(2.1 获取名字中大写字母A开头的所有人 2.2 获取名字中大写字母C到Z的所有人(不支持中文))
     */
    @Test
    public void zrangebylex() {
        String key = "zrangebylex-key";
        commands.del(key);
        commands.zadd(key, 0, "13265642367");
        commands.zadd(key, 0, "13265642366");
        commands.zadd(key, 0, "13265642368");
        commands.zadd(key, 0, "13565642367");
        commands.zadd(key, 0, "13565642367");
        commands.zadd(key, 0, "13765642367");
        System.out.println(commands.zrangebylex(key, Range.from(Range.Boundary.including("132"), Range.Boundary.excluding("133"))));//获取132号段
        Assert.assertTrue(commands.zrangebylex(key, Range.from(Range.Boundary.including("132"), Range.Boundary.excluding("133"))).size() == 3);
    }

    @Test
    public void zrangebylex1() {
        String key = "zrangebylex-name-sort-key";
        commands.del(key);
        commands.zadd(key, 0, "张三");
        commands.zadd(key, 0, "张四");
        commands.zadd(key, 0, "李而");
        commands.zadd(key, 0, "张天");
        commands.zadd(key, 0, "李白");
        commands.zadd(key, 0, "刘禅");
        System.out.println(commands.zrangebylex(key, Range.from(Range.Boundary.including("张"), Range.Boundary.including("张"))));//不支持中文
        Assert.assertTrue(commands.zrangebylex(key, Range.from(Range.Boundary.including("张"), Range.Boundary.including("张"))).size() == 3);
    }

    @Test
    public void zrevrangebylex() {
        String key = "zrevrangebylex-key";
        commands.del(key);
        commands.zadd(key, 0, "13265642367");
        commands.zadd(key, 0, "13265642366");
        commands.zadd(key, 0, "13265642368");
        commands.zadd(key, 0, "13565642367");
        commands.zadd(key, 0, "13565642368");
        commands.zadd(key, 0, "13765642367");
        List<String> list = commands.zrevrangebylex(key, Range.unbounded());
        List<String> list1 = commands.zrangebylex(key, Range.unbounded());
        System.out.println(list);
        System.out.println(list1);
        Assert.assertTrue(Objects.equals("13765642367", list.get(0)));
    }

    @Test
    public void zrangebyscore() {
        String key = "zrangebyscore-key";
        commands.del(key);
        commands.zadd(key, 1, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 3, "C");
        commands.zadd(key, 5, "D");
        commands.zadd(key, 0, "E");
        commands.zadd(key, 9, "F");
        List<String> list = commands.zrangebyscore(key, Range.create(2, 8));
        System.out.println(list);
        Assert.assertTrue(Objects.equals("B", list.get(0)));
    }

    @Test
    public void zrank() {
        String key = "zrank-key";
        commands.del(key);
        commands.zadd(key, 1, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 3, "C");
        commands.zadd(key, 5, "D");
        commands.zadd(key, 0, "E");
        commands.zadd(key, 9, "F");
        Assert.assertTrue(commands.zrank(key, "F") == 5);
    }

    @Test
    public void zrem() {
        String key = "zrem-key";
        commands.del(key);
        commands.zadd(key, 1, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 3, "C");
        commands.zadd(key, 5, "D");
        commands.zadd(key, 0, "E");
        commands.zadd(key, 9, "F");
        Assert.assertTrue(commands.zrem(key, "F", "E") == 2);
    }

    /**
     * 确保分值一致
     */
    @Test
    public void zremrangebylex() {
        String key = "zremrangebylex-key";
        commands.del(key);
        commands.zadd(key, 0, "A");
        commands.zadd(key, 0, "B");
        commands.zadd(key, 0, "C");
        commands.zadd(key, 0, "D");
        commands.zadd(key, 0, "E");
        commands.zadd(key, 0, "F");
        Assert.assertTrue(commands.zremrangebylex(key, Range.create("A", "D")) == 4);
    }

    @Test
    public void zremrangebyrank() {
        String key = "zremrangebyrank-key";
        commands.del(key);
        commands.zadd(key, 0, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 0, "C");
        commands.zadd(key, 0, "D");
        commands.zadd(key, 3, "E");
        commands.zadd(key, 0, "F");
        Assert.assertTrue(commands.zremrangebyrank(key, 0, 2) == 3);
    }

    @Test
    public void zremrangebyscore() {
        String key = "zremrangebyscore-key";
        commands.del(key);
        commands.zadd(key, 0, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 0, "C");
        commands.zadd(key, 0, "D");
        commands.zadd(key, 3, "E");
        commands.zadd(key, 0, "F");
        Assert.assertTrue(commands.zremrangebyscore(key, Range.create(0, 1)) == 4);
    }

    @Test
    public void zrevrange() {
        String key = "zrevrange-key";
        commands.del(key);
        commands.zadd(key, 0, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 0, "C");
        commands.zadd(key, 0, "D");
        commands.zadd(key, 3, "E");
        commands.zadd(key, 0, "F");
        System.out.println(commands.zrevrange(key, 0, 3));
        Assert.assertTrue(Objects.equals(commands.zrevrange(key, 0, 3).get(0), "E"));
    }

    @Test
    public void zrevrangebyscore() {
        String key = "zrevrangebyscore-key";
        commands.del(key);
        commands.zadd(key, 0, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 0, "C");
        commands.zadd(key, 0, "D");
        commands.zadd(key, 3, "E");
        commands.zadd(key, 0, "F");
        System.out.println(commands.zrevrangebyscore(key, Range.create(2, 3)));
        Assert.assertTrue(Objects.equals(commands.zrevrangebyscore(key, Range.create(2, 3)).get(0), "E"));
    }

    @Test
    public void zrevrank() {
        String key = "zrevrank-key";
        commands.del(key);
        commands.zadd(key, 0, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 0, "C");
        commands.zadd(key, 0, "D");
        commands.zadd(key, 3, "E");
        commands.zadd(key, 0, "F");
        System.out.println(commands.zrevrank(key, "F"));
        Assert.assertTrue(commands.zrevrank(key, "F") == 2);
    }

    @Test
    public void zscore() {
        String key = "zscore-key";
        commands.del(key);
        commands.zadd(key, 0, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 0, "C");
        commands.zadd(key, 0, "D");
        commands.zadd(key, 3, "E");
        commands.zadd(key, 0, "F");
        System.out.println(commands.zscore(key, "E"));
        Assert.assertTrue(commands.zscore(key, "E") == 3);
    }

    @Test
    public void zunionstore() {
        String key = "zunionstore-key";
        commands.del(key, key + "-1", key + "-2");
        commands.zadd(key + "-1", 1, "A");
        commands.zadd(key + "-1", 1, "B");
        commands.zadd(key + "-1", 2, "C");
        commands.zadd(key + "-2", 1, "A");
        commands.zadd(key + "-2", 2, "B");
        commands.zadd(key + "-2", 3, "C");
        commands.zunionstore(key, ZStoreArgs.Builder.weights(2, 3).sum(), key + "-1", key + "-2");
        Assert.assertTrue(commands.zscore(key, "C") == 13);
    }

    @Test
    public void zscan() {
        String key = "zscan-key";
        commands.del(key);
        commands.zadd(key, 0, "A");
        commands.zadd(key, 2, "B");
        commands.zadd(key, 0, "C");
        commands.zadd(key, 0, "D");
        commands.zadd(key, 3, "E");
        commands.zadd(key, 0, "F");
        Assert.assertTrue(commands.zscan(key).getValues().stream().count() == 6);
    }
}
