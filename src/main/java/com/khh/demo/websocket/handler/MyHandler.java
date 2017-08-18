package com.khh.demo.websocket.handler;

import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by 951087952@qq.com on 2017/8/17.
 * 自定义一个消息处理器，专门用来处理文本内容的，如果上传的是二进制内容的，那么就会断开连接
 */
public class MyHandler extends TextWebSocketHandler{



    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        System.out.println("myHandler 接收到来自客户端的信息了 : " + message.toString());

    }
}
