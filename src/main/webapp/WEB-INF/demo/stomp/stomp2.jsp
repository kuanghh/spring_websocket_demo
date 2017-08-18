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
</head>
<body>
<script type="text/javascript">
    var socket = new SockJS("http://localhost:8080/spring_websocket_demo/sockJS/stomp");
    var stompClient = Stomp.over(socket);

    var payload = JSON.stringify({'message':'this is my stomp message'});

    stompClient.connect('guest','guest',function (frame) {//连接stomp端点
        stompClient.send("/topic/myShout2",{},payload);

    })

</script>

2222222222
</body>
</html>
