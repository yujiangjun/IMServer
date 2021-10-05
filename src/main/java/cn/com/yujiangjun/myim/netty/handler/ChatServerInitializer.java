package cn.com.yujiangjun.myim.netty.handler;

import cn.com.yujiangjun.myim.netty.proto.MessageBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatServerInitializer extends ChannelInitializer<Channel> {

    @Autowired
    GroupChatHandler groupChatHandler;
    @Autowired
    UserChannelRegisterHandler userChannelRegisterHandler;
    @Autowired
    SingleChatHandler singleChatHandler;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new HttpRequestHandler("/ws"));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //解码
        pipeline.addLast(new MessageToMessageDecoder<WebSocketFrame>() {
            @Override
            protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> out) throws Exception {
                ByteBuf content = frame.content();
                out.add(content);
                content.retain();
            }
        });
        pipeline.addLast(new MessageToMessageEncoder<MessageBuilder.Message>() {
            @Override
            protected void encode(ChannelHandlerContext ctx, MessageBuilder.Message message, List<Object> out) throws Exception {
                ByteBuf buf=null;
                buf=Unpooled.wrappedBuffer(message.toByteArray());
                WebSocketFrame frame=new BinaryWebSocketFrame(buf);
                out.add(frame);
            }
        });
        pipeline.addLast(new ProtobufDecoder(MessageBuilder.Message.getDefaultInstance()));
        pipeline.addLast(userChannelRegisterHandler);
        pipeline.addLast(singleChatHandler);
        pipeline.addLast(groupChatHandler);
//        pipeline.addLast(new TextWebSocketFrameHandler(group));
    }
}
