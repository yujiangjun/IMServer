package cn.com.yujiangjun.myim.netty.component;

import cn.com.yujiangjun.myim.constants.ChatType;
import cn.com.yujiangjun.myim.constants.MessageType;
import cn.com.yujiangjun.myim.constants.MsgDirect;
import cn.com.yujiangjun.myim.netty.proto.MessageBuilder;
import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;
import cn.com.yujiangjun.myim.rest.my.service.MyService;
import org.springframework.stereotype.Component;

/**
 * 消息创建器
 */
@Component
public class UserMessageBuilder {

    final MyService myService;

    public UserMessageBuilder(MyService myService) {
        this.myService = myService;
    }

    /**
     * 构建文本消息
     * @param msg
     * @param myId
     * @param friendId
     * @return
     */
    public MessageBuilder.Message buildTextMsg(MessageBuilder.Message msg, Integer myId, Integer friendId){

        ImUserEntity my = myService.getDetail(myId);
        if (my==null){
            throw new RuntimeException("找不到发送人");
        }
        ImUserEntity friend = myService.getDetail(friendId);
        if (friend==null){
            throw new RuntimeException("找不到好友");
        }
        if (msg!=null){
            return MessageBuilder.Message.newBuilder(msg).setMsgDirect(MsgDirect.RECEIVE.getCode()).setMyName(my.getNickName()).setFriendName(friend.getNickName()).build();
        }
        return MessageBuilder.Message.newBuilder().setMyId(myId).setFriendId(friendId).setMyName(my.getNickName()).setFriendName(friend.getNickName())
                .setMsgDirect(MsgDirect.RECEIVE.getCode()).setChatType(ChatType.SINGLE.getCode()).setMsgType(MessageType.TEXT.getCode()).build();
    }
}
