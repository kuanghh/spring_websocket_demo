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
    stomp666666666
    <br/>
    <textarea id="textA"></textarea>

</body>

<script type="text/javascript">
    var socket = new SockJS("http://localhost:8080/spring_websocket_demo/sockJS/stomp");
    var stompClient = Stomp.over(socket);

    var userId = "s6";
    var targetUserId = "s7";
    var payload = JSON.stringify({'message':'this is my stomp message','userId': targetUserId});

    stompClient.connect({name:userId},function (frame) {//连接stomp端点

        stompClient.subscribe("/user/queue/abc", function(message){   //客户订阅

            alert(message.body);
//            var json = JSON.parse(message.body);
            $("#textA").val(message.body);
        });

        stompClient.send("/app/toUser",{},payload)
    })

</script>
</html>
