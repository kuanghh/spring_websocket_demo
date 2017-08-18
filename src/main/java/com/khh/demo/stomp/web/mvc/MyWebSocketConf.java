package com.khh.demo.stomp.web.mvc;

import com.khh.demo.stomp.handler.MyHandler;
import com.khh.demo.stomp.handler.StompMessageHandshakeHandler;
import com.khh.demo.stomp.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by 951087952@qq.com on 2017/8/17.
 * 这个spring-webSocket的java配置文件
 */
@Configuration
@EnableWebSocketMessageBroker//启用STOMP消息
public class MyWebSocketConf extends AbstractWebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //为"/stomp"路径启用SockJS功能
        registry.addEndpoint("/stomp")
                .setHandshakeHandler(new StompMessageHandshakeHandler())
                .addInterceptors(new WebSocketHandshakeInterceptor())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //消息代理将会处理前缀为"/queue"和"/topic"的消息，除此之外，发往应用程序的消息将会带有"/app"前缀

        //理解为：在客户端上主题和个人的消息订阅,在topic和queue这两个域上可以向客户端发消息；
        registry.enableSimpleBroker("/queue","/topic");
        //理解为：客户端向系统发送消息
        registry.setApplicationDestinationPrefixes("/app");
        //这句表示给指定用户发送（一对一）的主题前缀是“/user”;
        registry.setUserDestinationPrefix("/user");
    }
}
