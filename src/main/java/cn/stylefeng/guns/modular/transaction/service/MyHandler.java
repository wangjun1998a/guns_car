package cn.stylefeng.guns.modular.transaction.service;

import cn.stylefeng.guns.modular.transaction.model.EmpInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 消息处理类
 *
 * @author alin
 */
@Service
public class MyHandler extends TextWebSocketHandler {
    // 在线用户列表
    public static final Map<Integer, WebSocketSession> userSocketSessionMap = new HashMap<Integer, WebSocketSession>();

    // 用户连接成功 就被调用
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //获取传来的登录用户的id
        String idstr = session.getUri().toString().split("=")[1];
        int id = Integer.parseInt(idstr);
        System.err.println("用户连接成功");
        //保存对应的WebSocketSession
        userSocketSessionMap.put(id, session);
    }

    // 消息处理方法
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EmpInfo friendInfo = objectMapper.readValue(message.getPayload(), EmpInfo.class);
            //session.sendMessage(message);//发送给当前人
            if (friendInfo.getType().equals("friend")) {
                //给用户发送消息
                sendMessageToUser(message, friendInfo);
            } else if (friendInfo.getType().equals("group")) {
                //给所有的用户发送消息
                sendMessagesToUsers(message, friendInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给所有的用户发送消息
     */
    public void sendMessagesToUsers(TextMessage message, EmpInfo friendInfo) {

        Set<Integer> clientIds = userSocketSessionMap.keySet();
        WebSocketSession session = null;
        for (Integer clientId : clientIds) {
            session = userSocketSessionMap.get(clientId);
            System.out.println(clientId);
            try {
                if (session.isOpen() && !friendInfo.getId().equals(clientId)) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给所有的用户发送消息
     */
    public void sendMessageToUser(TextMessage message, EmpInfo friendInfo) throws IOException {

        Set<Integer> clientIds = userSocketSessionMap.keySet();
        WebSocketSession session = null;
        for (Integer clientId : clientIds) {
            session = userSocketSessionMap.get(clientId);
            System.out.println(session);
            try {
                if (session.isOpen() && friendInfo.getToId().equals(clientId)) {

                    System.out.println("回复消息");
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //用户退出后的处理，退出之后，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        try {
            if (session.isOpen()) {
                session.close();
            }
            userSocketSessionMap.remove(session.getId());
            System.out.println("退出系统");
        } catch (Exception e) {
            System.out.println("用户非正常关闭");
        }


    }

}