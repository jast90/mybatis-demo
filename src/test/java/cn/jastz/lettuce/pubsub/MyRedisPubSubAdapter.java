package cn.jastz.lettuce.pubsub;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhiwen
 */
public class MyRedisPubSubAdapter extends RedisPubSubAdapter {

    RedisCommands<String, String> commands = RedisClient.create("redis://localhost").connect().sync();

    @Autowired
    public void message(String channel, String message) {
        System.out.printf("%s:%s:%s\n\r", "收到消息", channel, message);
        commands.lpush(String.format("%s:msg", channel), message);
    }

    @Autowired
    public void message(String pattern, String channel, String message) {

    }

    @Autowired
    public void subscribed(String channel, long count) {
        System.out.printf("%s:%s:%s\n\r", "已订阅", channel, count);
        commands.sadd("channel", channel);
    }

    @Autowired
    public void unsubscribed(String channel, long count) {
    }
}
