var index="";
var sortType=0;
var tankTypeId="";
var tankId="";
var syncTime=0;
var t="";
var boxt="";
var activeL="";
var activeI="";
$(document).ready(function () {
	window.parent.$(".z-tabpanel").css("padding","0px");
    window.parent.$(".z-window-content").css("padding","0px");
    getLabel();
    $("ul.dropdown-menu>li").click(function(){
        sortType=$(this).attr("lb");
        $("#sortType").html($(this).find("a").html());
        listTankMonitor();
    })
    $(".input-group-addon").click(function(){
        tankId=$("#tankId").val();
        listTankMonitor();
    })
 	$(".my-top").delegate("a","click", function(){
 		$(this).parent().find("a").removeClass("active");
 		$(this).addClass("active");
        tankTypeId=$(this).attr("tankTypeId");
        listTankMonitor();
 	});
 	$(".my-content_body").delegate(".box","click", function(){
        clearInterval(boxt);
		index = $(this).closest('.box').index();
		var itIndex=$(this).index(".box");
		if($(this).hasClass("active")){//已点中
			$(this).parents(".my-content-box").find(".box").removeClass("active");
			$(this).parents(".my-content-box").next(".box-expand-div").slideUp(800);
		}else{
			if($(this).parents(".my-content_body").find(".my-content-box").find(".box").hasClass("active")){//已有展开项
				if($(this).parent(".my-content-box").find(".box").hasClass("active")){//同级展开
					changebg();
				}else{
					$(".box-expand-div").slideUp(800,changebg);
				}

				$(".box").removeClass("active");
				$(this).addClass("active");
                var obj=$(this).parents(".my-content-box").next(".box-expand-div");
                obj.slideDown(800);
                activeL =parseInt(itIndex/3);
				activeI=$(this).attr("tankId");
                tankState(obj,activeI);
				detailTank(obj,activeI);
                var myChart = echarts.init(document.getElementById("placeholder"+activeL));
                myChart.clear();
                drawChart(activeL,activeI);
                boxt=setInterval("drawChart(activeL,activeI)",2*60*1000);
			}else{
				$(".box").removeClass("active");
				$(this).addClass("active");
				changebg();
				var obj=$(this).parents(".my-content-box").next(".box-expand-div");
				obj.slideDown(800);
                activeL =parseInt(itIndex/3);
                activeI=$(this).attr("tankId");
                tankState(obj,activeI);
				detailTank(obj,activeI);
                var myChart = echarts.init(document.getElementById("placeholder"+activeL));
                myChart.clear();
                drawChart(activeL,activeI);
                boxt=setInterval("drawChart(activeL,activeI)",2*60*1000);
                // drawChart(line,tankId,[1,2,3,4,5,6],[110,112,110,111,114,112],[15,17,18,19,20,18],[23,24,24,25,23,23],[130,150,137,147,135,140],[222,275,235,245,251,251]);
			}
		}

 	});

});
function changebg(){
    var width=$(".my-content-box").width();
    if(index==0){//一项
        $(".box-expand-div").css("background","url('img/bg1.png') 0% 0% / "+width+"px 377px");
    }else if(index==1){
        $(".box-expand-div").css("background","url('img/bg2.png') 0% 0% / "+width+"px 377px");
    }else if(index==2){
        $(".box-expand-div").css("background","url('img/bg.png') 0% 0% / "+width+"px 377px");
    }
}
function listErpTankType(){
    var dataobj=""
    window.parent.ajax("monitor/listErpTankType",dataobj,function(data) {
        if (data.backCode == 0) {
            if(data.bean.records.length>0) {
                 var str="";
                 str +='<span>'+getCookie("classification")+'：</span><a class="active" tankTypeId="">'+getCookie("ALL")+'</a>';
                 $.each(data.bean.records, function (k, p) {
                    str+='<a tankTypeId="'+p.tankTypeId+'" id="TypeId'+p.tankTypeId+'">'+getDic(1039,p.tankTypeId,"TypeId"+p.tankTypeId)+'</a>';
                 });
                 $(".my-top").html(str);
                 // alert(getCookie("sort"));
                // $("#sortLabel").html(getCookie("sort"));
                // $("#sortByGeneral").html(getCookie("sortByGeneral"));
                // $("#sortByGeneral1").html(getCookie("sortByGeneral"));
                // $("#sortByNumber").html(getCookie("sortByNumber"));
                // $("#sortByTask").html(getCookie("sortByTask"));
                listTankMonitor();
            }
        } else {
            walert(data.errorMess);
        }
    });
}
function listTankMonitor() {
    clearInterval(boxt);
    var dataobj={
        "tankTypeId":tankTypeId,
        "tankId":tankId,
        "sortType":sortType,
    }
    window.parent.ajax("monitor/listTankMonitorByParam", dataobj, function (data) {
        $(".my-content_body").html("");
        if (data.backCode == 0) {
            var currentTime=GetDateStr(0);
            $(".top-tip_time").html("上次更新时间："+currentTime);
            syncTime=0;
            $(".top-tip_gx").hide();
            clearTimeout(t);
            countsyncTime();
            if(data.bean.length>0) {
                var str="";
                $.each(data.bean, function (k, p) {
                    var pobj=p[0];
                    if(k%3==0){
                        str +='<div class="my-content-box">';
                    }
                    str +='<div class="box" tankId="'+pobj.tankId+'"><div class="box-top">';
                    if(pobj.ver==0){//锁住
                        str +='<div class="lock-div"><img src="img/lock.png" /></div>';
                    }
                    str +='<div class="numberbg"><p>'+pobj.tankId+'</p></div>';
                    str +='<div class="namebg"><p>'+pobj.goodsName+'</p></div>';
                    if(pobj.remark==0){
                        str +='<div class="alarm-div"><img src="img/alarm.png" /></div>';
                    }
                    str +='</div><div class="box-level">';
                    str +='<span class="kd-after">20m&nbsp;&nbsp;&nbsp;-</span>';
                    str +='<div class="progress"><div class="progress-bar" role="progressbar"aria-valuemin="0" aria-valuemax="100"style="width: '+pobj.level+'%;"></div></div>';
                    str +='<span class="kd-before">1m&nbsp;&nbsp;&nbsp;-</span>';
                    if(pobj.isSphere==2){
                        str +='<div class="box-waterColumn"><canvas id="'+pobj.tankId+'"></canvas></div>';
                    }else if(pobj.isSphere==1){
                        str +='<div class="box-waterball"><canvas id="'+pobj.tankId+'"></canvas></div>';
                    }
                    str +='</div><div class="box-detail">';
                    str +='<p>'+getCookie("temperature")+'：'+pobj.temperature+'℃</p>';
                    str +='<p>'+getCookie("level")+'：'+pobj.level+'m';
                    if(pobj.abnormalLevel&&pobj.abnormalLevel!="1"){
                        str +='<span class="warn-div"><span class="flowft" id="'+pobj.tankId+'1035">'+getDic(1035,pobj.abnormalLevel,pobj.tankId+"1035")+'</span></span>';
                    }
                    str +='</p>';
                    str +='<p>'+getCookie("realVolume")+'：'+pobj.realVolume+'L';
                    if(pobj.abnormalvolume&&pobj.abnormalvolume!=1){
                        str +='<span class="warn-div"><span class="flowft" id="'+pobj.tankId+'1036">'+getDic(1036,pobj.abnormalvolume,pobj.tankId+"1036")+'</span></span>';
                    }
                    str +='</p>';
                    str +='<p>'+getCookie("flowSpeed")+'：'+pobj.flowSpeed+'m³/h';
                    if(pobj.tankState&&pobj.tankState==1){
                        str +='<span class="inflow-div"><span class="flowft" id="'+pobj.tankId+'1037">'+getDic(1037,pobj.tankState,pobj.tankId+"1037")+'</span></span>';
                    }else if(pobj.tankState&&pobj.tankState==2){
                        str +='<span class="outflow-div"><span class="flowft" id="'+pobj.tankId+'1037">'+getDic(1037,pobj.tankState,pobj.tankId+"1037")+'</span></span>';
                    }
                    str +='</p>';
                    str +='<p>'+getCookie("theoreticalWeight")+'：'+pobj.theoreticalWeight+'t</p>';
                    str +='<p>'+getCookie("realWeight")+'<span class="red">*</span>：'+pobj.realWeight+'t</p></div></div>';
                    if((k%3==2)||(k==data.bean.length-1)){
                        str +='</div>';
                        str +='<div class="box-expand-div">';
                        str += '<div class="box-expand_part1"><label class="box-expand_label tankState"></label>';
                        str += '<div style="border-right: 1px dashed #c8c8c8;height: 280px;">';
                        str += '<div class="box-expand_item1"><p class="pressure"></p>';
                        str += '<p class="density"></p><p class="etc"></p>';
                        str += '<p class="diffWeight"></p></div><label class="box-expand_label" style="margin-top: 9px;">'+getCookie("currentCustomers")+'</label>';
                        str += '<div class="box-expand_item2"></div>';
                        str +='</div></div>';
                        str +='<div class="box-expand_part2"><label class="box-expand_label">'+getCookie("operationInformation")+'</label>';
                        str +='<div style="border-right: 1px dashed #c8c8c8;height: 280px;">';
                        str +='<p class="box-expand_line">'+getCookie("recentOperation")+'</p>';
                        str +='<table class="table-striped">';
                        str +='<thead><tr><th>'+getCookie("date")+'</th><th>'+getCookie("operationContent")+'</th><th>'+getCookie("number")+'</th></tr></thead>';
                        str +='<tbody class="operate"></tbody>';
                        str +='</table>';
                        str +='<p class="box-expand_line">'+getCookie("expectedOperation")+'</p>';
                        str +='<table class="table-striped">';
                        str +='<thead><tr><th>'+getCookie("date")+'</th><th>'+getCookie("operationContent")+'</th><th>'+getCookie("number")+'</th></tr></thead>';
                        str +='<tbody class="expectOpeate"></tbody>';
                        str +='</table>';
                        str +='</div></div>';
                        str +='<div class="box-expand_part3">';
                        str +='<label class="box-expand_label">'+getCookie("historyM")+'</label>';
                        str +='<div class="box-expand_item3"><p><img src="img/level.png" /><span>'+getCookie("level")+'</span> <img src="img/temperature.png" /><span>'+getCookie("temperature")+'</span>';
                        str +='<img src="img/volume.png" /><span>'+getCookie("realVolume")+'</span> <img src="img/weight.png" /><span>'+getCookie("weight")+'</span> <img src="img/velocity.png" /><span>'+getCookie("flowSpeed")+'</span></p></div>';
                        str +='<div class="box-lineChart"><div id="placeholder'+parseInt(k/3)+'" class="graph"></div></div>';
                        str +='</div></div>';
                    }
                });
                $(".my-content_body").html(str);
                $.each(data.bean, function (k, p) {
                    var pobj=p[0];
                    if (pobj.isSphere == 2) {
                        drawWaterBall(pobj.level, pobj.tankId, true);
                    } else if (pobj.isSphere == 1) {
                        drawWaterBall(pobj.level, pobj.tankId, false);
                    }
                });
            }
        } else {
            walert(data.errorMess);
        }
    });
}
function countsyncTime() {//计算同步时间
    syncTime++;
    t=setTimeout("countsyncTime()",60*1000);
    if(syncTime>1){//1分钟后
        if(syncTime>10){//10分钟后
            // listTankMonitor();
            $(".top-tip_gx").html('同步时间已超过10分钟，请注意！').show();
        }else{
            // $(".top-tip_gx").html('同步时间已超过'+(syncTime-1)+'分钟，请注意！').show();
        }
    }
}
function detailTank(obj,activeI) {
    var dataobj={
     "tankId":activeI
     }
    window.parent.ajax("monitor/detailTankGuestAndOperate", dataobj, function (data) {
        var str="";
        if (data.backCode == 0) {
            var str1="";
            var str2="";
            var str3="";
            $(obj).find(".box-expand_item2").html("");
            $(obj).find(".operate").html("");
            $(obj).find(".expectOpeate").html("");
            if(data.bean.length>0) {
                 $.each(data.bean, function (k, p) {
                     if(emptyCheck(p.guestName)){
                         str1 = '<div><p>'+p.guestName+'</p>';
                         str1 +='<div class="progress"><div class="progress-bar" role="progressbar"aria-valuemin="0" aria-valuemax="100"style="width: '+p.numPercent+'%;"></div></div>';
                         str1 += '<span>'+p.number+'t</span></div>'
                         $(obj).find(".box-expand_item2").append(str1);
                     }
                     if(emptyCheck(p.operateDate)){
                         str2 = '<tr><td>'+p.operateDate+'</td><td id="operate'+k+'">'+getDic("1038",p.operatewayName,'operate'+k)+'</td><td>'+p.operateNum+'m³</td></tr>';
                         $(obj).find(".operate").append(str2);
                     }
                     if(emptyCheck(p.expectOpeateDate)){
                         str3 = '<tr><td>'+p.expectOpeateDate+'</td><td id="expectOperate'+k+'">'+getDic("1038",p.expectOpeatewayName,'expectOperate'+k)+'</td><td>'+p.expectOpeateNum+'m³</td></tr>';
                         $(obj).find(".expectOpeate").append(str3);
                     }

                 });
             }
        }
    });
}
function tankState(obj,activeI) {//某罐状态
    var dataobj={
        "tankTypeId":"",
        "tankId":activeI,
        "sortType":"0",
    }
    window.parent.ajax("monitor/listTankMonitorByParam", dataobj, function (data) {
        var str="";
        if (data.backCode == 0) {
            if(data.bean.length>0) {
                var tankObj=data.bean[0][0];
                $(obj).find(".tankState").html(getCookie("currentState")+"【<span id='"+tankObj.tankId+"1037tankState'>"+getDic(1037,tankObj.tankState,tankObj.tankId+"1037tankState")+"</span>】");
                $(obj).find(".pressure").html(getCookie("pressure")+"："+tankObj.pressure+"kPa℃");
                $(obj).find(".density").html(getCookie("density")+"："+tankObj.density+"t/m³");
                $(obj).find(".etc").html("E&nbsp;&nbsp;&nbsp;T&nbsp;&nbsp;&nbsp;C："+tankObj.etc+"h");
                $(obj).find(".diffWeight").html(getCookie("diffWeight")+"："+tankObj.diffWeight+"t<span>["+getCookie("T_R")+"]</span>");

            }
        }
    });
}
function getDic(type,code,obj) {
    var dataobj={
        "type":type,
        "code":code
    }
    window.parent.ajax("monitor/getDic", dataobj, function (data) {
        var str="";
        if (data.backCode == 0) {
            var p=data.bean;
             // alert(p[0].dicVal1);
            $("#"+obj).html(p[0].dicVal1);
        }
    });
}
function getLabel(){
    window.parent.getRealLanguage("UI.LBL.Monitor.classification.01",function(data) {addCookie("classification",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.ALL.01",function(data) {addCookie("ALL",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.sort.01",function(data) {addCookie("sort",data.bean.labelContent);$("#sortLabel").html(data.bean.labelContent);});
    window.parent.getRealLanguage("UI.LBL.Monitor.sortByGeneral.01",function(data) {addCookie("sortByGeneral",data.bean.labelContent); $("#sortByGeneral").html(data.bean.labelContent); $("#sortByGeneral1").html(data.bean.labelContent);});
    window.parent.getRealLanguage("UI.LBL.Monitor.sortByNumber.01",function(data) {addCookie("sortByNumber",data.bean.labelContent);$("#sortByNumber").html(data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.sortByTask.01",function(data) {addCookie("sortByTask",data.bean.labelContent);$("#sortByTask").html(data.bean.labelContent);});

    window.parent.getRealLanguage("UI.LBL.Monitor.temperature.01",function(data) {addCookie("temperature",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.realVolume.01",function(data) {addCookie("realVolume",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.level.01",function(data) {addCookie("level",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.flowSpeed.01",function(data) {addCookie("flowSpeed",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.theoreticalWeight.01",function(data) {addCookie("theoreticalWeight",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.realWeight.01",function(data) {addCookie("realWeight",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.weight.01",function(data) {addCookie("weight",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.currentState.01",function(data) {addCookie("currentState",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.currentCustomers.01",function(data) {addCookie("currentCustomers",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.operationInformation.01",function(data) {addCookie("operationInformation",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.recentOperation.01",function(data) {addCookie("recentOperation",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.expectedOperation.01",function(data) {addCookie("expectedOperation",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.date.01",function(data) {addCookie("date",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.operationContent.01",function(data) {addCookie("operationContent",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.number.01",function(data) {addCookie("number",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.pressure.01",function(data) {addCookie("pressure",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.density.01",function(data) {addCookie("density",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.diffWeight.01",function(data) {addCookie("diffWeight",data.bean.labelContent)});

    window.parent.getRealLanguage("UI.LBL.Monitor.historyM.01",function(data) {addCookie("historyM",data.bean.labelContent)});
    window.parent.getRealLanguage("UI.LBL.Monitor.T_R.01",function(data) {addCookie("T_R",data.bean.labelContent)});
    listErpTankType();

}