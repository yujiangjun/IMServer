package cn.com.yujiangjun.myim.netty;

import cn.com.yujiangjun.myim.netty.handler.ChatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
@Slf4j
public class Server {


//    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private final EventLoopGroup group = new NioEventLoopGroup();
    private Channel channel;

    @Autowired
    ChatServerInitializer chatServerInitializer;

    public void start(InetSocketAddress address) throws Exception{
        ChannelFuture future = null;
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(createInitializer());
            future = bootstrap.bind(address);
            future.addListener((ChannelFutureListener) listener->{
                if (listener.isSuccess()){
                    log.info("IM server start success");
                }
            });
//            future.syncUninterruptibly();
            channel = future.channel();
            channel.closeFuture().sync();
        }  finally {
            group.shutdownGracefully();
        }

    }
    protected ChannelInitializer<Channel> createInitializer() {
        return chatServerInitializer;
    }

//    public void destroy() {
//        if (channel != null) {
//            channel.close();
//        }
//        channelGroup.close();
//        group.shutdownGracefully();
//    }

//    public static void main(String[] args) throws Exception {
//
//        final Server endpoint = new Server();
//        ChannelFuture future = endpoint.start(new InetSocketAddress(9999));
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> endpoint.destroy()));
//        future.channel().closeFuture().syncUninterruptibly();
//    }
}
