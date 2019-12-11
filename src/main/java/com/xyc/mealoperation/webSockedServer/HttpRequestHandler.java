package com.xyc.mealoperation.webSockedServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Author xiongyancong
 * @createTime 2019/12/11 16:30
 * @Description
 **/
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    // webSocket标识
    private final String wsUri;

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        // 如果是webSocket请求，请求地址uri等于wsUri
        if (wsUri.equalsIgnoreCase(fullHttpRequest.uri())) {
            // 将消息发送到下一个channelHandler
            ctx.fireChannelRead(fullHttpRequest.retain());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
