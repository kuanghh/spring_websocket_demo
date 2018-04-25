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

    stompClient.connect({name : 'guest'},function (frame) {//连接stomp端点

        stompClient.subscribe('/user/queue/search', function(message){   //客户订阅
            // message就是服务端返回过来的消息
            alert(message.body);
            var json = JSON.parse(message.body);

        });

        stompClient.send("/app/message",{},payload)
    })

</script>
stomp44444
</body>
</html>
