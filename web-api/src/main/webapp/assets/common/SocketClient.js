/**
 * Created by win on 2015/6/25.
 */
var websocket = null;
function connection(websocketUrl, sockJSUrl) {
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
        alert("链接服务器成功");
        send("test12345","test");
    };
    websocket.onmessage = function (evnt) {
        console.log(evnt);
        var data = evnt.data;
       // data = jQuery.parseJSON(data);
        alert("接收数据"+data);
        console.log(data);

    };
    websocket.onerror = function (evnt) {
        alert("出现了一个错误" + evnt);
    };
    websocket.onclose = function (evnt) {
        alert("链接服务器失败");
    }

}

function send(message, msgType) {
    if (websocket) {
        var msgClient = {
            "targetId": 0,
            "msgType": msgType,
            "msgBody": message
        };
        websocket.send(JSON.stringify(msgClient));
        alert("发送成功");
    } else {
        alert("发送失败");
    }
}