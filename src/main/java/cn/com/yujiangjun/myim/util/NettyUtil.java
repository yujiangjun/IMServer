package cn.com.yujiangjun.myim.util;

import io.netty.channel.Channel;

public class NettyUtil {


    /**
     * 转发消息
     * @param channel
     * @param msg
     */
    public static void forwardMsg(Channel channel,Object msg){
        if (channel.isActive()){
            channel.writeAndFlush(msg);
        }
    }
}
