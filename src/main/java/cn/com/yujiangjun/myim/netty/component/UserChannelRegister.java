package cn.com.yujiangjun.myim.netty.component;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户通道注册
 */
@Component("channelRegister")
public class UserChannelRegister {

    /**
     * channel 池
     */
    Map<Integer, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 注册
     * @param channel
     * @param userId
     */
    public synchronized void register(Channel channel,Integer userId){
        channelMap.put(userId,channel);
    }

    /**
     * 退出
     * @param userId
     */
    public synchronized void unRegister(Integer userId){
        channelMap.remove(userId);
    }


    public Channel queryChannelById(Integer userId){
        return channelMap.get(userId);
    }
}
