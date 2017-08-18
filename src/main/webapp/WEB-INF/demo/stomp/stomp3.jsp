<%--
  Created by IntelliJ IDEA.
  User: FSTMP
  Date: 2017/8/17
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
    <script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.js"></script>
    <script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<script type="text/javascript">
    var socket = new SockJS("http://localhost:8080/spring_websocket_demo/sockJS/stomp");
    var stompClient = Stomp.over(socket);

    var payload = JSON.stringify({'message':'this is my stomp message'});

    stompClient.connect('guest','guest',function (frame) {//连接stomp端点


        stompClient.subscribe('/topic/myShout2', function(message){   //客户订阅/app/myShout2
            // message就是服务端返回过来的消息
            alert(message);
            var json = JSON.parse(message.body);

        });
    })

    //在浏览器多开一个窗口，并且访问"http://localhost:8080/spring_websocket_demo/dynamicMsg.action"
    //检测是否会受到消息，结果：是

    //但是，当订阅的是/app/myShout2的时候，即使服务器动态发送消息给订阅了/app/myShout2的客户，客户依然没有收到，未解决

</script>
stomp3333333333
</body>
</html>
