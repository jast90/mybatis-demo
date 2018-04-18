package cn.jastz.redis.lettuce.pubsub;

import cn.jastz.redis.lettuce.AbstractTest;
import org.junit.Test;

/**
 * @author zhiwen
 */
public class RedisPubSub extends AbstractTest {


    @Test
    public void publish() {
        System.out.println(commands.publish("newOrder", "下单成功"));
    }

    @Test
    public void subscribe() {
        sysc.subscribe("newOrder");
        while (true) {

        }
    }

}
