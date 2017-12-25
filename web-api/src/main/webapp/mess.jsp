<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE htmlPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type"content="text/html; charset=ISO-8859-1">
    <title>WebSocket/SockJS Echo Sample (Adapted from Tomcat's echo sample)</title>
    <style type="text/css"></style>
    <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
    <script>
        var path = '<%=basePath%>';
        alert(path);
        var userId = 'lys';
        if(userId==-1){
            window.location.href="<%=basePath2%>";
        }
        var jspCode = userId+"_AAA";
        var websocket;
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://" + path + "wsMy?jspCode=" + jspCode);
        } else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://" + path + "wsMy?jspCode=" + jspCode);
        } else {
            websocket = new SockJS("http://" + path + "wsMy/sockjs?jspCode=" + jspCode);
        }
        websocket.onopen = function(event) {
            console.log("WebSocket:???");
            console.log(event);
        };
        websocket.onmessage = function(event) {
            var data = JSON.parse(event.data);
            console.log("WebSocket:??????-norm", data);
            alert("WebSocket:??????");
        };
        websocket.onerror = function(event) {
            console.log("WebSocket:???? ");
            console.log(event);
        };
        websocket.onclose = function(event) {
            console.log("WebSocket:???");
            console.log(event);
        }
    </script>
</head>
<body>

</body>
</html>