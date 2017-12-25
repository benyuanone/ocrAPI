package com.ourway.base.websocket;

import com.ourway.base.model.BaseUser;
import com.ourway.base.utils.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

/**
 * Created by zhuqiang on 2015/6/22 0022.
 */
public class WebSocketHander implements WebSocketHandler {
    private static final Logger logger = Logger.getLogger(WebSocketHander.class);
    public SessionManager sessionManager = SessionManager.instance();
    //    当前接入的websocket客户端列表
    public final static List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<WebSocketSession>());

    //初次链接成功执行
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("链接成功......");
        //首次链接进入的时候，某些时候可以记录有多少人访问。这里可以产生二维码进行处理。
//        Object temp = session.getAttributes().get(SessionManager.USER_SESSION);
//        if (null != temp) {
//            BaseUser user = (BaseUser) temp;
//            System.out.println(user.getEmpId());
//            sessionManager.put(user.getEmpId(), session);
//        }
//        sessions.add(session);
    }

    //接受消息处理消息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        Map<String, Object> attributes = webSocketSession.getAttributes();
        String mess = "";
        if (null == webSocketMessage.getPayload())
            return;
        try {
            mess = webSocketMessage.getPayload().toString();
            BaseSocketMess socketMess = JsonUtil.fromJson(mess, BaseSocketMess.class);
            switch (socketMess.getMsgType()) {
                case 0://登陆
                    BaseUser user = new BaseUser();
                    user.setEmpId(socketMess.getEmpId());
                    user.setInTime(new Date());
                    attributes.put(SessionManager.USER_SESSION, user);
                    sessions.add(webSocketSession);
                    sessionManager.put(user.getEmpId(), webSocketSession);
                    break;
                case 1://收到消息回复
                    if (null != attributes.get(SessionManager.USER_SESSION)) {
                        BaseUser baseUser = (BaseUser) attributes.get(SessionManager.USER_SESSION);
                        baseUser.setUpdateTime(new Date());
                    }
                    break;
            }
        } catch (Exception e) {

        }
//        sessionManager.sendMessageToUser(user, "成功收到消息");
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("handleTransportError=====================");
       // if (webSocketSession.isOpen()) {
        //    webSocketSession.close();
       // }
        logger.debug("链接出错，关闭链接......");
        //sessions.remove(webSocketSession);
        //sessionManager.remove(webSocketSession);
        //推送在线人数和在线列表
//        sessionManager.sendMessage(sys,JSONObject.toJSONString(mdsr),Code.MSG_ORIGIN_SERVICE);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.debug("链接关闭......" + closeStatus.toString());
        System.out.println("afterConnectionClosed=====================");
        //sessionManager.remove(webSocketSession);
        //sessions.remove(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
