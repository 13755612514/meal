package com.xyc.mealoperation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author xiongyancong
 * @createTime 2019/12/6 11:38
 * @Description
 **/
@Service
@Component
@ServerEndpoint("/websocket/{sid}")
@Slf4j
public class WebSocketServer {
    private static  int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet  = new CopyOnWriteArraySet<>();

    private Session session;

    private  String sid = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("sid")String sid){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        log.info("有新窗口开始监听：" + sid + ",当前在线人数为" + getOnlineCount());
        this.sid = sid;
        try {
            sendMessage("连接成功");
        }catch (IOException e){
            log.error("webSocked IO异常{}",e.getStackTrace());
        }
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("有连接关闭！当前人数为" + getOnlineCount());
    }
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("收到来自窗口{}的信息{}",sid,message);
        for (WebSocketServer item : webSocketSet){
            try{
                item.sendMessage(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 服务器发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message,@PathParam("sid") String sid){
        log.info("推送消息到窗口{}，推送内容：{}",sid,message);
        for (WebSocketServer item : webSocketSet){
            try {
                if(sid == null){
                    item.sendMessage(message);
                }else if (item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            }catch (IOException e){
                continue;
            }
        }
    }
    public static synchronized int getOnlineCount(){
        return onlineCount;
    }
    public static synchronized  void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        WebSocketServer.onlineCount--;
    }
}
