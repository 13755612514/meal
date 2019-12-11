package com.xyc.mealoperation.webSockedServer;

import com.xyc.mealoperation.service.ChatService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Author xiongyancong
 * @createTime 2019/12/11 16:37
 * @Description
 **/
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final ChannelGroup channelGroup;

    @Autowired
    private ChatService chatService;


    public TextWebSocketFrameHandler(ChannelGroup channelGroup) {
        this.channelGroup = channelGroup;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 如果ws握手完成
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
            // 删除pipeLine中处理http请求的handler
            ctx.pipeline().remove(HttpRequestHandler.class);
            // 写一个消息广播到所有的客户端channel
            //channelGroup.writeAndFlush(new TextWebSocketFrame("Client " + ctx.channel() + " joined!"));
            // 将当前客户端channel添加进group
            channelGroup.add(ctx.channel());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 将接收的消息通过ChannelGroup转发到所有连接的客户端
        //channelGroup.writeAndFlush(textWebSocketFrame.retain());
        // 前端组装的消息格式是 {"message":{"text":"项目地址","date":"2018-11-28T02:13:52.437Z"},"to":2,"from":1}
//        Map<String,Object> msg = GsonUtils.fromJson(textWebSocketFrame.text().toString(),new TypeToken<Map<String,Object>>(){});
//        String type = (String) msg.get("type");
//        switch (type) {
//            case "REGISTER":
//                chatService.register(channelHandlerContext,msg);
//                break;
//            case "SINGLE_SENDING":
//                chatService.singleSend(channelHandlerContext,msg);
//                break;
//        }
    }
}
