package com.ourway.base.websocket;

import com.ourway.base.model.BaseUser;
import com.ourway.base.utils.JsonUtil;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhuqiang on 2015/6/28 0028.
 * Session管理中心
 */
public class SessionManager {
    /**
     * session中的用户信息，包括游客的都是该属性
     */
    public static final String USER_SESSION = "websocketuser_session";

    private SessionManager() {
    }

    public static SessionManager instance() {
        return SingletonHolder.sessionManager;
    }

    private static class SingletonHolder {
        private static SessionManager sessionManager = new SessionManager();
    }


    //根据用户编号，存储的websocketsession
    public static HashMap<String, WebSocketSession> userMap = new HashMap<>();


    public void put(String userId, WebSocketSession webSocketSession) {
        userMap.put(userId, webSocketSession);
//
    }

    /**
     * sss
     *
     * @param webSocketSession
     */
    public void remove(WebSocketSession webSocketSession) {
        Map<String, Object> attributes = webSocketSession.getAttributes();
        BaseUser user = (BaseUser) attributes.get(SessionManager.USER_SESSION);
        userMap.remove(user.getEmpId());
    }

    /**
     * 给某个用户发送消息
     *
     * @param temp
     * @param message
     */
    public void sendMessageToUser(Object temp, String message) {
        TextMessage textMess = new TextMessage(message);
        if (temp instanceof BaseUser) {
            BaseUser user = (BaseUser) temp;
            this.sendMessageToUser(user.getEmpId(), textMess);
        } else {
            this.sendMessageToUser(temp.toString(), textMess);
        }
    }

    /**
     * 给某个游客发送消息
     *
     * @param touristName
     * @param message
     */
    private void sendMessageToUser(String touristName, TextMessage message) {
        WebSocketSession session = this.userMap.get(touristName);
        if (session != null) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean sendMessageToUser(String touristName, BaseSocketMess message) {
        WebSocketSession session = this.userMap.get(touristName);
        String mess = JsonUtil.toJson(message);
        TextMessage textMess = new TextMessage(mess);
        if (session != null) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(textMess);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public int getOnlineUser() {
        Set<String> set = userMap.keySet();
        return set.size();
    }


}
