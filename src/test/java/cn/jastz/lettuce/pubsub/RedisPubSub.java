package cn.jastz.lettuce.pubsub;

import cn.jastz.lettuce.AbstractTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhiwen
 */
public class RedisPubSub extends AbstractTest {


    @Test
    public void publish() {
        sysc.subscribe("newOrder");
        Assert.assertTrue(sysc.publish("newOrder", "下单成功") == 1);
    }

    @Test
    public void subscribe() {
        sysc.subscribe("newOrder");
        sysc.clientGetname();
    }

}
