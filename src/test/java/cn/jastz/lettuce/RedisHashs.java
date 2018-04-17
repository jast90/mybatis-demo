package cn.jastz.lettuce;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.lettuce.core.ScanArgs;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhiwen
 */
public class RedisHashs extends AbstractTest {

    @Test
    public void hset() {
        String key = "hset-key";
        commands.del(key);
        Assert.assertTrue(commands.hset(key, "firstName", "Jast"));
    }

    @Test
    public void hsetnx() {
        String key = "hsetnx-key";
        commands.del(key);
        commands.hsetnx(key, "firstName", "Jast");
        Assert.assertTrue(commands.hsetnx(key, "firstName", "Jast") == false);
    }

    @Test
    public void hdel() {
        String key = "hdel-key";
        commands.del(key);
        commands.hset(key, "name", "hello");
        Assert.assertTrue(commands.hdel(key, "age") == 0);
    }

    @Test
    public void hexists() {
        String key = "hexists-key";
        commands.del(key);
        commands.hset(key, "name", "hello");
        Assert.assertTrue(commands.hexists(key, "name"));
    }

    @Test
    public void hget() {
        String key = "hget-key";
        commands.del(key);
        commands.hset(key, "name", "hello");
        Assert.assertTrue(Objects.equals(commands.hget(key, "name"), "hello"));
    }

    @Test
    public void hgetall() {
        String key = "hgetall-key";
        commands.del(key);
        commands.hset(key, "name", "hello");
        commands.hset(key, "age", "21");
        System.out.println(commands.hgetall(key));
        Assert.assertTrue(commands.hgetall(key).keySet().contains("name"));
    }

    @Test
    public void hincrby() {
        String key = "hincrby-key";
        commands.del(key);
        commands.hset(key, "age", "21");
        Assert.assertTrue(commands.hincrby(key, "age", 1) == 22);
    }

    @Test
    public void hincrbyfloat() {
        String key = "hincrbyfloat-key";
        commands.del(key);
        commands.hset(key, "age", "21");
        Assert.assertTrue(commands.hincrbyfloat(key, "age", 1.0) == 22.0);
    }

    @Test
    public void hkeys() {
        String key = "hkeys-key";
        commands.del(key);
        commands.hset(key, "name", "san");
        commands.hset(key, "age", "21");
        Assert.assertTrue(commands.hkeys(key).containsAll(Lists.newArrayList("name", "age")));
    }

    @Test
    public void hlen() {
        String key = "hlen-key";
        commands.del(key);
        commands.hset(key, "name", "san");
        commands.hset(key, "age", "21");
        Assert.assertTrue(commands.hlen(key) == 2);
    }

    @Test
    public void hmset() {
        String key = "hmset-key";
        commands.del(key);
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "jast");
        map.put("age", "21");
        Assert.assertTrue(Objects.equals(commands.hmset(key, map), "OK"));
    }

    @Test
    public void hmget() {
        String key = "hmget-key";
        commands.del(key);
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "jast");
        map.put("age", "21");
        commands.hmset(key, map);
        Assert.assertTrue(commands.hmget(key, "name", "age").size() == 2);
    }

    @Test
    public void hstrlen() {
        String key = "hstrlen-key";
        commands.del(key);
        commands.hset(key, "name", "jast");
        Assert.assertTrue(commands.hstrlen(key, "name") == 4);
    }

    @Test
    public void hvals() {
        String key = "hvals-key";
        commands.del(key);
        Map<String, String> values = Maps.newHashMap();
        values.put("name", "san");
        values.put("age", "21");
        values.put("add", "shenzhen");
        commands.hmset(key, values);
        Assert.assertTrue(commands.hvals(key).size() == 3);
    }

    @Test
    public void hsacn() {
        String key = "hsacn-key";
        commands.del(key);
        Map<String, String> values = Maps.newHashMap();
        values.put("name", "san");
        values.put("age", "21");
        values.put("add", "shenzhen");
        commands.hmset(key, values);
        Assert.assertTrue(commands.hscan(key, ScanArgs.Builder.matches("name")).getMap().keySet().size() == 1);

    }
}
