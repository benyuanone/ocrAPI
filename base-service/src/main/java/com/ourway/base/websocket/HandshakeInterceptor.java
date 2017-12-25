package com.ourway.base.websocket;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;

import java.util.Map;
import java.util.Random;

/**<p>方法 HandshakeInterceptor : <p>
*<p>说明:websocket的处理程序，新用户进入，首先调用beforeHandshake，记录用户信息</p>
*<pre>
*@author JackZhou
*@date 2017/4/21 14:47
</pre>
*/
public class HandshakeInterceptor implements org.springframework.web.socket.server.HandshakeInterceptor {
    private Random random = new Random();
    //初次握手访问前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("beforeHandshake:++++++++++++++++++++++++++++++");
//        if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//            HttpSession session = servletRequest.getServletRequest().getSession(true);
//            if (session != null) {
//                //使用userName区分WebSocketHandler，以便定向发送消息
//                Object temp = session.getAttribute(SessionManager.USER_SESSION);
//                if(temp!= null){
//                    if(temp instanceof BaseUser){
//                        BaseUser user = (BaseUser)temp;
//                        map.put(SessionManager.USER_SESSION,user);  //存入数据，方便在hander中获取
//                    }
//                }else{ //有session ，但是又没有user信息。就是游客
//                    BaseUser user = new BaseUser();
//                    user.setOwid(TextUtils.getUUID());
//                    user.setEmpName("游客" + random.nextInt(100000));
//                    map.put(SessionManager.USER_SESSION,user);
//                    session.setAttribute(SessionManager.USER_SESSION,user);
//                }
//            }else{
//                BaseUser user = new BaseUser();
//                user.setOwid(TextUtils.getUUID());
//                map.put(SessionManager.USER_SESSION,user);
//                session.setAttribute(SessionManager.USER_SESSION,user);
//            }
//        }
        return true;
    }

    //初次握手访问后
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.err.println("有人访问了：" + serverHttpRequest.getRemoteAddress());
        //如果自动登陆，这里可以发送二维码等一些访问前的信息，目前这里没有。
    }
}