package cn.com.yujiangjun.myim.netty.handler;

import cn.com.yujiangjun.myim.constants.MessageType;
import cn.com.yujiangjun.myim.netty.component.UserChannelRegister;
import cn.com.yujiangjun.myim.netty.proto.MessageBuilder;
import cn.com.yujiangjun.myim.util.NettyUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ChannelHandler.Sharable
@Slf4j
public class UserChannelRegisterHandler extends SimpleChannelInboundHandler<MessageBuilder.Message> {

    final UserChannelRegister userChannelRegister;

    public UserChannelRegisterHandler(UserChannelRegister userChannelRegister) {
        this.userChannelRegister = userChannelRegister;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBuilder.Message msg) throws Exception {
        log.info("接收消息:{}",msg);
//        ctx.writeAndFlush(msg);
        if (Objects.equals(msg.getMsgType(), MessageType.REGISTER.getCode())){
            userChannelRegister.register(ctx.channel(),msg.getMyId());
            MessageBuilder.Message forwardMsg = MessageBuilder.Message.newBuilder(msg).setContent("加入成功").build();
            log.info("发送消息:{}",forwardMsg);
            ctx.channel().writeAndFlush(forwardMsg);
        }else {
            ctx.fireChannelRead(msg);
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
