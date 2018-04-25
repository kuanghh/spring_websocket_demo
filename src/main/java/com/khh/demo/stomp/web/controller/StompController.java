package com.khh.demo.stomp.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.khh.demo.stomp.entity.Shout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

/**
 * Created by 951087952@qq.com on 2017/8/17.
 */
@Controller
public class StompController {


    /**
     * 这个用来动态发送消息
     *
     * 例子：
     *  webSocketController.template.convertAndSend("/topic/myShout2",shout) //广播
     *  webSocketController.template.convertAndSendToUser(userId, "/message",userMessage) //一对一发送，发送特定的客户端
     */
    @Autowired
    public SimpMessagingTemplate template;

    @Autowired
    private SimpUserRegistry userRegistry;

    //这里访问的时候应该用/app/myShout
    @MessageMapping("/myShout")
    public void handlerShout(Shout incoming){
        System.out.println("Received message: " + incoming.getMessage());
    }


    /**
     *
     * 这个方法表示服务端可以接收客户端通过主题“/app/myShout2”发送过来的消息
     * ，客户端需要在主题"/topic/myShout2"上订阅并接收服务端发回的消息
     * (也就是需要客户在js中订阅/topic/myShout2
     *      stompClient.subscribe('/topic/myShout2', function(message){
     *           alert(message);
     *           // message就是服务端返回过来的消息
     *           var json = JSON.parse(message.body);
     *      });
     * )
     */
    //这里访问的时候应该用/app/myShout
    @MessageMapping("/myShout2")
    @SendTo("/topic/myShout2")//发送消息给 订阅了"/topic/myShout2" 的客户,如果不加@SendTo，默认返回给 "/topic" + @MessageMapping里的路径
    public Shout handlerShout2(Shout incoming){
        System.out.println("Received message: " + incoming.getMessage());
        Shout outgoing = new Shout();
        outgoing.setMessage("message from server");
        System.out.println("send message");
        return outgoing;
    }

    /**
     * @SubscribeMapping 的区别在于这里的Shout消息将会直接发送给客户端，而不必经过消息代理。如果你为方法
     * 添加@SendTo注解的话，那么消息将会经过代理，最后发送到指定的目的地
     *
     * 这个用来处理 客户端订阅了/app/myShout2  事件,因为它会自动加上/app前缀
     *
     * 但如果写成@SubscribeMapping({"/myShout2","/topic/myShout2"}),客户即使订阅了/topic/myShout2，此类方法也不会调用
     * @return
     */
    @SubscribeMapping({"/myShout2"})
    public Shout handlerSubscription(){
        System.out.println("i accept one subscription");
        Shout shout = new Shout();
        shout.setMessage("i accept your subscription");
        return shout;
    }


    /**
     * 无作用，即使客户端订阅了/topic/myShout2，也不会触发该方法
     * @return
     */
    @SubscribeMapping("/topic/myShout2")
    public Shout handlerSubscription2(){
        System.out.println("i accept one subscription2");
        Shout shout = new Shout();
        shout.setMessage("i accept your subscription2");
        return shout;
    }

    /**
     * 发送给 订阅了“/user/queue/search” 的客户,只会返回给当前用户，并不是广播发送
     * 例如：因为registry.enableSimpleBroker("/queue","/topic")，所以能识别/queue
     * @return
     */
    @MessageMapping("/message")
    @SendToUser(value = "/queue/search",broadcast = false)//broadcast = false 避免推送到所有session中    //成功
    public Shout handlerShout4(Shout income){
        System.out.println(income.getMessage());
        Shout shout = new Shout();
        shout.setMessage(" server send message to user");
        return shout;
    }

    /**
     * 使用simpMessageTemplate发送到指定主题, 成功
     * @param greeting
     */
    @MessageMapping(value="/greetings")
    public void greet(String greeting) {
        String text = "id:" + greeting;
        this.template.convertAndSend("/topic/myShout2", text);
    }

    @MessageMapping("/toUser")
//  @SendToUser(destinations="/queue/errors", broadcast=false)，
//  broadcast的作用是，如果一个用户有多个session，当消息想发送到一个原session的时候，可以设置broadcast=false
    public void sentToUser(String message){
        int i = 1;
        for (SimpUser user : userRegistry.getUsers()) {
            System.out.println("用户" + i++ + "---" + user);
        }

        JSONObject obj = JSONObject.parseObject(message);
        String userId = obj.getString("userId");
        String message_info = obj.getString("message");
        System.out.println("message_info = " + message_info +",userId = " + userId);
        this.template.convertAndSendToUser(userId, "/queue/abc", message_info);
    }

}
