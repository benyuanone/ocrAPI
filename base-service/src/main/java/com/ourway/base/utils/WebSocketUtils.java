package com.ourway.base.utils;

import com.ourway.base.websocket.BaseSocketMess;
import com.ourway.base.websocket.SessionManager;
import com.ourway.base.websocket.WebSocketHander;

/**
 * websocket 的工具类
 * Created by Administrator on 2017/10/20.
 */
public class WebSocketUtils {

    //返回当前在线数
    public static int getOnlineNum(){
        return WebSocketHander.sessions.size();
    }

    //发送消息
    public static boolean sendMess(String empId, BaseSocketMess mess){
        return SessionManager.instance().sendMessageToUser(empId,mess);
    }
}
