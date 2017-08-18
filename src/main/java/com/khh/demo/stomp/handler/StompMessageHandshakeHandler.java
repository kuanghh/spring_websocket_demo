package com.khh.demo.stomp.handler;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

/**
 * Created by 951087952@qq.com on 2017/8/18.
 */
public class StompMessageHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        Set<Map.Entry<String, Object>> entries = attributes.entrySet();
        for(Map.Entry<String,Object> entry : entries){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }


        return super.determineUser(request,wsHandler,attributes);
    }
}
