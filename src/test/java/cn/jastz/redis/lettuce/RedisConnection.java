package cn.jastz.redis.lettuce;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * @author zhiwen
 */
public class RedisConnection extends AbstractTest {

    @Test
    public void auth() {
        Assert.assertTrue(Objects.equals(commands.auth(" "), "OK"));
    }

    @Test
    public void echo() {
        Assert.assertTrue(Objects.equals(commands.echo("Hello"), "Hello"));
    }

    @Test
    public void ping() {
        Assert.assertTrue(Objects.equals(commands.ping(), "PONG "));
    }

    @Test
    public void quit() {
        String va = commands.quit();
        System.out.println(va);
        Assert.assertTrue(Objects.equals(va, "OK"));
    }

    @Test
    public void select() {
        String va = commands.select(2);
        System.out.println(va);
        commands.del("select-key");
        Assert.assertTrue(Objects.equals(commands.set("select-key", "db2"), "OK"));
    }
}
