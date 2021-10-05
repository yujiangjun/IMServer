package cn.com.yujiangjun.myim.netty.handler;

import cn.com.yujiangjun.myim.constants.ChatType;
import cn.com.yujiangjun.myim.netty.component.UserChannelRegister;
import cn.com.yujiangjun.myim.netty.component.UserMessageBuilder;
import cn.com.yujiangjun.myim.netty.proto.MessageBuilder;
import cn.com.yujiangjun.myim.rest.my.service.MyService;
import cn.com.yujiangjun.myim.util.NettyUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 单聊
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class SingleChatHandler extends SimpleChannelInboundHandler<MessageBuilder.Message> {

    final UserChannelRegister userChannelRegister;
    final MyService myService;
    final UserMessageBuilder userMessageBuilder;

    public SingleChatHandler(UserChannelRegister userChannelRegister, MyService myService, UserMessageBuilder userMessageBuilder) {
        this.userChannelRegister = userChannelRegister;
        this.myService = myService;
        this.userMessageBuilder=userMessageBuilder;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBuilder.Message msg) throws Exception {
        if (Objects.equals(msg.getChatType(), ChatType.SINGLE.getCode())){
            Channel channel = userChannelRegister.queryChannelById(msg.getFriendId());
            if (channel==null){
                ctx.writeAndFlush(msg);
                log.info("好友没有上线");
                return;
            }
            ctx.writeAndFlush(msg);
            MessageBuilder.Message forwardMsg = userMessageBuilder.buildTextMsg(msg, msg.getMyId(), msg.getFriendId());
            NettyUtil.forwardMsg(channel,forwardMsg);
        }else {
            ctx.fireChannelRead(ctx);
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
