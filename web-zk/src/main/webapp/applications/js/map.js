$(document).ready(function() {
    map_load();
});

function js1() {

    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.onload = script.onreadystatechange = function () {
        if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {
            js2();
            // Handle memory leak in IE
            script.onload = script.onreadystatechange = null;
        }
    };
    script.src = 'http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js';
    document.body.appendChild(script);
}
function  js2(){
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.onload = script.onreadystatechange = function () {
        if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {
            map_init();
            // Handle memory leak in IE
            script.onload = script.onreadystatechange = null;
        }
    };
    script.src = 'http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js';
    document.body.appendChild(script);
}

function map_init() {

    // 百度地图API功能
    var map = new BMap.Map("map");
    map.centerAndZoom(new BMap.Point(116.404, 39.915),4);
    map.enableScrollWheelZoom();

    // 编写自定义函数,创建标注
    function addMarker(point){
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
    }

    for (var i = 0; i < 25; i ++) {
        var point = new BMap.Point(116.404, 39.915+2*i);
        addMarker(point);
    }
}

//异步调用百度js
function map_load() {
    var load = document.createElement("script");
    load.src = "http://api.map.baidu.com/api?v=2.0&ak=DDd05fcba9fea5b83bf648515e04fc4c&callback=js1";
    document.body.appendChild(load);
}
