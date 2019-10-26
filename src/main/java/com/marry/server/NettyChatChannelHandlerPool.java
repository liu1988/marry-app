package com.marry.server;

import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyChatChannelHandlerPool {
    public NettyChatChannelHandlerPool() {}
    public static Map<String, ChannelId> channelIdMap = new ConcurrentHashMap<>();
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
