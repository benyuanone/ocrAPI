<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   String path = request.getContextPath();

%>
<html>
<head>
<title>WebSocket/SockJS  </title>

    <script src="<%=path%>/assets/common/app.js"></script>
    <script src="<%=path%>/assets/common/SocketClient.js"></script>
    <script src="<%=path%>/assets/lib/sockjs/sockjs.js"></script>
    <script src="<%=path%>/assets/lib/jquery/jquery.min.js"></script>
    <script type="text/javascript">
      // connection("localhost:8080/webApi/echo.do", "localhost:8080/webApi/sockjs.do");
        function showNotice(title,msg,link){
           var Notification = window.Notification || window.mozNotification || window.webkitNotification;
           if(Notification){
               Notification.requestPermission(function(status){
                   //status默认值'default'等同于拒绝 'denied' 意味着用户不想要通知 'granted' 意味着用户同意启用通知
                   if("granted" != status){
                       return;
                   }else{
                       var tag = "sds"+Math.random();
                       var notify = new Notification(
                           title,
                           {
                               dir:'auto',
                               lang:'zh-CN',
                               tag:tag,//实例化的notification的id
                               icon:'http://localhost:8080/webZk/charisma/img/img_logo.png',//通知的缩略图,//icon 支持ico、png、jpg、jpeg格式
                               body:msg //通知的具体内容
                           }
                       );
                       notify.onclick=function(){
                           //如果通知消息被点击,通知窗口将被激活
                           window.focus();
                           window.open(link);

                       }, notify.onerror = function () {
                               console.log("HTML5桌面消息出错！！！");
                       };
                       notify.onshow = function () {
                           setTimeout(function(){
                               notify.close();
                           },2000*10)
                       };
                       notify.onclose = function () {
                           console.log("HTML5桌面消息关闭！！！");
                       };
                   }
               });
           }else{
               console.log("您的浏览器不支持桌面消息");
           }
       };
       showNotice("test","<div><b>HTML5桌面消息</b></div>","http://localhost:8080/webZk/applications/index.do?1507649716178")
    </script>
</head>
<body>

</body>

</html>