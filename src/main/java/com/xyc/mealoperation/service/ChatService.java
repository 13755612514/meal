package com.xyc.mealoperation.service;

import com.xyc.mealoperation.util.CacheLoader;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

/**
 * @Author xiongyancong
 * @createTime 2019/12/11 16:39
 * @Description
 **/
public class ChatService {

    public void register(ChannelHandlerContext channelHandlerContext, Map<String, Object> msg) {
        CacheLoader.channelMap.put(Double.parseDouble(msg.get("userId").toString()),channelHandlerContext.channel());
    }

    public void singleSend(ChannelHandlerContext channelHandlerContext, Map<String, Object> msg) {
        Double to = Double.parseDouble(msg.get("to").toString());
        msg.remove("to");
        msg.remove("type");
//        CacheLoader.channelMap.get(to).writeAndFlush(new TextWebSocketFrame(GsonUtils.toJson(msg)));
    }
}
