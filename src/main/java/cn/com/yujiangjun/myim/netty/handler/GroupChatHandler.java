package cn.com.yujiangjun.myim.netty.handler;

import cn.com.yujiangjun.myim.constants.MessageType;
import cn.com.yujiangjun.myim.netty.bean.MyFriendsChannel;
import cn.com.yujiangjun.myim.netty.bean.UserChannelItem;
import cn.com.yujiangjun.myim.netty.proto.MessageBuilder;
import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;
import cn.com.yujiangjun.myim.rest.my.service.MyService;
import cn.com.yujiangjun.myim.util.ConvertUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@ChannelHandler.Sharable
@Component
public class GroupChatHandler extends SimpleChannelInboundHandler<MessageBuilder.Message> {


    @Autowired
    MyService myService;



    private List<MyFriendsChannel> myFriendsChannels = new ArrayList<>();

    private Map<Integer, Channel> channelMap = new ConcurrentHashMap<>();




    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("激活");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBuilder.Message msg) throws Exception {
        log.info("接收信息:{}",msg);
        Integer myId=msg.getMyId();
        channelMap.put(myId,ctx.channel());
        if (Objects.equals(msg.getMsgType(), MessageType.REGISTER.getCode())){
            registerUserChannel(msg);
            MessageBuilder.Message resp = MessageBuilder.Message.newBuilder(msg).setContent("加入聊天成功").build();
            ctx.writeAndFlush(resp);
        }else {
            MyFriendsChannel myFriendsChannel = myFriendsChannels.stream().filter(item -> Objects.equals(item.getMyId(), myId)).findFirst().get();
            MessageBuilder.Message forwardMsg = MessageBuilder.Message.newBuilder(msg).setMsgDirect(2).build();
            myFriendsChannel.getFriendsChannel().forEach(item->sendMessage(channelMap.get(item.getUserId()),forwardMsg));
            ctx.writeAndFlush(msg);
        }
    }

    private void sendMessage(Channel channel, MessageBuilder.Message msg){
        /**
         * channel ！=null  好友在线
         *
         */
        if (channel !=null && channel.isActive()){
            channel.writeAndFlush(msg);
        }
    }

    private void registerUserChannel(MessageBuilder.Message msg){

        List<ImUserEntity> myFriends = myService.getMyFriends(msg.getMyId());
        List<UserChannelItem> userChannelItemList = myFriends.stream().map(item -> {
            UserChannelItem convert = ConvertUtil.convert(item, UserChannelItem.class);
            convert.setChannel(channelMap.get(item.getUserId()));
            return convert;
        }).collect(Collectors.toList());

        if (myFriendsChannels.stream().anyMatch(item-> Objects.equals(item.getMyId(),msg.getMyId()))){
            MyFriendsChannel myFriendsChannel = myFriendsChannels.stream().filter(item -> Objects.equals(item.getMyId(), msg.getMyId())).findFirst().get();

            myFriendsChannel.getFriendsChannel().clear();
            myFriendsChannel.getFriendsChannel().addAll(userChannelItemList);
        }else {
            MyFriendsChannel myFriendsChannel = MyFriendsChannel.builder()
                    .myId(msg.getMyId()).myName(msg.getMyName()).build();
            myFriendsChannel.setFriendsChannel(userChannelItemList);
            myFriendsChannels.add(myFriendsChannel);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("退出");
        log.info("class:{},method:{}",UserChannelRegisterHandler.class.getName(),"channelInactive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("发生异常，cause{}",cause);
    }
}
