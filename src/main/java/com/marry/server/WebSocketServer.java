package com.marry.server;



import com.alibaba.fastjson.JSONObject;
import com.marry.common.utils.IdWorker;
import com.marry.member.service.SysMemberChatService;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;

import java.io.IOException;

@ServerEndpoint(prefix = "netty-websocket")
@Component
@Slf4j
public class WebSocketServer {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SysMemberChatService sysMemberChatService;

    private ParameterMap parameterMap;

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, ParameterMap parameterMap) throws IOException {
        //添加到channelGroup通道组
        WebSocketChannelHandlerPool.channelGroup.add(session.channel());
        String userId = parameterMap.getParameter("userId");
        System.out.println("与客户端建立连接，通道开启！"+userId);
        WebSocketChannelHandlerPool.channelIdMap.put(userId,session);
        this.parameterMap=parameterMap;
        System.out.println( "size####################"+ WebSocketChannelHandlerPool.channelIdMap.size());
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("与客户端断开连接，通道关闭！");
        //从通道组channelGroup 移除通道
        String userId = parameterMap.getParameter("userId");
        WebSocketChannelHandlerPool.channelGroup.remove(session.channel());
        WebSocketChannelHandlerPool.channelIdMap.remove(userId);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session,String message) {
        System.out.println(message);
        JSONObject jsonObject = JSONObject.parseObject(message);
        String receiveUserId = jsonObject.get("receiveUserId").toString();
        String msg = jsonObject.getString("msg");

        //生成消息的id
        long id = idWorker.nextId();
        jsonObject.put("id",id);

        JSONObject jsonMsg = new JSONObject();
        jsonMsg.put("msg",msg);
        jsonMsg.put("id",id);

        Session toSession = WebSocketChannelHandlerPool.channelIdMap.get(receiveUserId);
        //用户是否属于登录状态
        String userId = redisTemplate.opsForValue().get("login_" + receiveUserId).toString();
        if(toSession!=null&& StringUtils.isNotEmpty(userId)){
            //发送消息
            toSession.sendText(jsonMsg.toJSONString());
            //对方在线状态
            jsonObject.put("online",1);
        }
        //持久化消息
        jsonObject.put("online",0);
        //发送过程中的消息都标识为未读，当打开窗口，或者每次发送消息时，更改消息状态为已读
        jsonObject.put("status",0);
        sysMemberChatService.save(jsonObject);
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    //所有的都空闲，将通道关闭
                    session.close();
                    break;
                default:
                    break;
            }
        }
    }

}
