package com.khh.demo.sockJS.web.mvc;

import com.khh.demo.sockJS.handler.MyHandler;
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
     * 注册webSocketHandler,并添加sockJS支持
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        /**
         * 访问sockJS不允许带有后缀
         * setAllowedOrigins("*")，也是为了减少404错误
         */
        registry.addHandler(myHandler(),"/sockJS")
                .addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*").withSockJS();
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
