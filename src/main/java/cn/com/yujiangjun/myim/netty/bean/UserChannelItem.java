package cn.com.yujiangjun.myim.netty.bean;

import io.netty.channel.Channel;
import lombok.Builder;
import lombok.Data;

@Data
public class UserChannelItem {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户通道
     */
    private Channel channel;
}
