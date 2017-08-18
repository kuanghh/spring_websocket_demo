package com.khh.demo.websocket.web.mvc;

import com.khh.demo.websocket.handler.MyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * Created by 951087952@qq.com on 2017/8/17.
 * 这个spring-webSocket的java配置文件
 */
@Configuration
@EnableWebSocket
public class MyWebSocketConf implements WebSocketConfigurer {
    /**
     * 注册webSocketHandler的方法
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //配置handler以及访问它的路径,并配置一个基于HttpSession的握手拦截器
        /*
         * 这里一定要加上.action，因为在web.xml中，配置的DispatcherServlet的处理路径时有.action 为结尾的
         * 如果不加上.action,,那么生成的webSocket路径时不带上.action的，以/webSocket路径访问webSocket,DispatcherServlet会自动过滤掉
         * ，导致无法连接上webSocket服务
         */
        registry.addHandler(myHandler(),"/myHandler.action").addInterceptors(new HttpSessionHandshakeInterceptor());
    }

    @Bean
    public WebSocketHandler myHandler(){
        return new MyHandler();
    }

    /**
     * 设置webSocket传入的信息大小
     * @return
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }
}
