package com.xyc.mealoperation.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author xiongyancong
 * @createTime 2019/12/11 16:27
 * @Description
 **/
public class CacheLoader {
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static Map<Double, Channel> channelMap = new ConcurrentHashMap<>();
}
