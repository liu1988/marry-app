package com.marry.server;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.yeauty.pojo.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketChannelHandlerPool {
    public WebSocketChannelHandlerPool() {}

    public static Map<String, Session> channelIdMap = new ConcurrentHashMap<>();
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
