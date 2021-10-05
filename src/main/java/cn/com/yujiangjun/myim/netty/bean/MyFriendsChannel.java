package cn.com.yujiangjun.myim.netty.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MyFriendsChannel {

    /**
     * 当前发送数据用户id
     */
    private Integer myId;
    /**
     * 用户名称
     */
    private String myName;

    /**
     * 我的好友通道映射列表
     */
    private List<UserChannelItem> friendsChannel;
}
