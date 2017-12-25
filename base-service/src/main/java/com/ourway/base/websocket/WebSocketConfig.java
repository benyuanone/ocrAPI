package com.ourway.base.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
* Created by zhuqiang on 2015/6/23 0023.
        */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHander(),"/echo.do").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(new WebSocketHander(),"/sockjs/echo.do").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }

}
