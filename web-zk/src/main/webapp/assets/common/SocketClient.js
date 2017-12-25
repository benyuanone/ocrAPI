/**
 * Created by win on 2015/6/25.
 */
var websocket = null;
function connection(websocketUrl,sockJSUrl,empId) {
    if (!websocketUrl || !sockJSUrl) {
        alert("链接地址不能为空");
    }
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + websocketUrl);
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://" + websocketUrl);
    } else {
        websocket = new SockJS("ws://" + sockJSUrl);
    }
    websocket.onopen = function (evnt) {
        //登陆成功后，用员工编号进行登陆
        sendWithEmpid(empId);
    };
    websocket.onmessage = function (evnt) {
        console.log(evnt);
        var data = evnt.data;
        data = jQuery.parseJSON(data);
        sendMessZK("newMess");
        getRealLanguage(data.msgTitle,function(result){
            var title = "";
            if(result.backCode==0)
                title = result.bean.labelContent;
            else
                title = data.msgTitle;
            showNotice(title,data.msgBody,data.msgUrl);
        });

    };
    websocket.onerror = function (evnt) {
        sendMessZK("alert您的浏览器不支持WebSocket,请选用Chrome浏览器访问");
    };
    websocket.onclose = function (evnt) {
        sendMessZK("alert您的浏览器不支持WebSocket,请选用Chrome浏览器访问");
    }

}
//发送身份信息
function sendWithEmpid(empId) {
    if (websocket) {
        var msgClient = {
            "targetId":"0",
            "msgType": 0,
            "dataType":0,
            "msgBody": "",
            "msgUrl":"",
            "msgIcon":"",
            "empId":empId
        };
        websocket.send(JSON.stringify(msgClient));
    } else {

    }
}

function send(message, msgType,linkUrl,icon) {
    if (websocket) {
        var msgClient = {
            "targetId": "0",
            "msgType": msgType,
            "msgBody": message,
            "msgUrl":linkUrl,
            "msgIcon":icon
        };
        websocket.send(JSON.stringify(msgClient));
    } else {

    }
}

//发送消息给zk后台
function sendMessZK(mess) {
    var jq = window.jq;
    var zk = window.zk;
    var hiddenMess = zk.Widget.$(jq("$hiddenMessage"), window.document);
    hiddenMess.setValue(mess);
    hiddenMess.smartUpdate('value', mess);
    var btnLogin = zk.Widget.$(jq("$commandBtn"), window.document);
    window.zAu.send(new zk.Event(btnLogin, "onClick", mess));
}


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
                    if(null!=link&&link!=''){
                        window.focus();
                        sendMessZK("open"+title+"$"+link);
                        // window.open(link);
                    }
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
}