package com.khh.demo.stomp.web.controller;

import com.khh.demo.stomp.entity.Shout;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 951087952@qq.com on 2017/8/17.
 *
 */
@Controller
@RequestMapping(value = "/webSocket")
public class WebController {

    @RequestMapping(value = "/index")
    public String index() throws Exception{
        return "stomp";
    }


    /**
     * 跳转到  用来检测客户端发送消息到路径为/topic/myShout  的jsp，是否成功，结果：否
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index2")
    public String index2() throws Exception{
        return "stomp2";
    }

    /**
     * 跳转到  用来检验SimpMessagingTemplate实现动态发送消息给客户端  的jsp页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index3")
    public String index3() throws Exception{
        return "stomp3";
    }

    /**
     * 跳转到  用来一对一发送消息 的jsp页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index4")
    public String index4() throws Exception{
        return "stomp4";
    }
}
