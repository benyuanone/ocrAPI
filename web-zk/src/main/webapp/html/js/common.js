var loading; // 加载中
var openId="";// 当前的微信号openid
var sessionId = "";// 当前的sessionID
var ver = "1.0";// 当前版本号
var bnetAccount;
var loginType = "1";
var currlatitude;
var currlongitude;
var currspeed;
var curraccuracy;
var imagePath = "../files/imgs/zybx/";
// ajax调用接口序号编号
var ajaxIndex = 0;
function getAjaxIndex() {
	ajaxIndex++;
	return ajaxIndex;
}

// 获取指定APPid的微信的配置，传回ajax
function loadWxConfig() {
	var url = "wxApiAction!signWx.htm";
	var params = {
		"urlStr" : location.href,
		timestamp : new Date().getTime()
	};
	$.post(url, params, function(data) {
		if (data.backCode == 0) {
			wx.config({
				debug : false,
				appId : data.bean.appId,
				timestamp : data.bean.timestamp,
				nonceStr : data.bean.nonceStr,
				signature : data.bean.signature,
				jsApiList : [ 'getLocation','chooseImage', 'uploadImage'	]
			});
		}
	});
}



// 获得登陆用户信息
function getLogin() {
	// 获取用户信息
	openId = getCookie("openId");
	sessionId = getCookie("sessionId");
	
}

function toRad(d) {
	return d * Math.PI / 180;
}
function getDisance(lat1, lng1, lat2, lng2) { 
	// lat为纬度, lng为经度, 一定不要弄错
    var dis = 0;
    var radLat1 = toRad(lat1);
    var radLat2 = toRad(lat2);
    var deltaLat = radLat1 - radLat2;
    var deltaLng = toRad(lng1) - toRad(lng2);
    var dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng / 2), 2)));
    return dis * 6378137;
} 

// 从url中获取参数值
function GetRequest() {
	var url = location.search; // 获取url中"?"符后的字串
	if (url.indexOf("?") != -1) { // 判断是否有参数
		var str = url.substr(1); // 从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
		strs = str.split("="); // 用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔
		return strs[1]; // 返回第一个参数 （如果有多个参数 还要进行循环的）
	}
}

function lrtrim(v) {
		return v.replace(/(^\s*)|(\s*$)/g, '');
	}
	// 错误提示，alert框

function walert(msg) {
	layer.msg(msg);
}

// 错误tip提示
function tip(msg, obj) {
	layer.tips(msg, obj, {
        tips: [1, '#2db4d3'],
        time: 4000});
}


// 取消null显示
function convertStr(val) {
	if ("" == val || null == val || 'null' == val || 'undefined' == val) {
		return "";
	} else
		return val;
}

// 开始加载
function beginLoad() {
	loading = layer.load(0, {shade: false});
}
	// 停止加载

function finishLoad() {
	if (loading != null)
		layer.close(loading);
}

// 从url中分离所有的参数，返回数组
function getRequests() {
	var url = location.search;
	var params = new Array();
	var strs;
	var _strs;
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		if (str.indexOf("&") != -1) {
			strs = str.split("&");
			for (var i = 0; i < strs.length; i++) {
				_strs = strs[i].split("=");
				params[i] = _strs[1];
			}

		} else {
			strs = str.split("=");
			params[0] = strs[1];
		}
	}
	return params;
}

// js产生uuid
function uuid() {
	var s = [];
	var hexDigits = "0123456789abcdef";
	for (var i = 0; i < 36; i++) {
		s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	}
	s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
	s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the
	// clock_seq_hi_and_reserved
	// to 01
	s[8] = s[13] = s[18] = s[23] = "-";
	var uuid = s.join("");
	uuid = uuid.replace(/-/g, "");
	return uuid;
}

// 检查邮箱格式
function emailCheck(val) {
		var reyx = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		return (reyx.test(val));
	}
	// 判断空

function emptyCheck(val) {
	if (val !== null && val !== undefined && val !== '') {
		return true;
	} else
		return false;
}

function numCheck(val) {
	if (!isNaN(val)) {
		return true;
	}
	return false;
}

function convertDate(lng) {
	if (null == lng || lng == "" || lng == 0)
		return "";
	var dt = new Date(lng);
	var _sj = "";
	_sj = dt.getFullYear() + "-" + fixZero(dt.getMonth() + 1, 2) + "-" + fixZero(dt.getDate(), 2) + " " + fixZero(dt.getHours(), 2) + ":" + fixZero(dt.getMinutes(), 2) + ":" + fixZero(dt.getSeconds(), 2);
	return _sj;
}

function fixZero(num, length) {
	var str = "" + num;
	var len = str.length;
	var s = "";
	for (var i = length; i-- > len;) {
		s += "0";
	}
	return s + str;
}

// 以下是base64加密
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1,
	63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1,
	0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
	20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31,
	32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
	50, 51, -1, -1, -1, -1, -1);

function base64encode(str) {
	var out, i, len;
	var c1, c2, c3;

	len = str.length;
	i = 0;
	out = "";
	while (i < len) {
		c1 = str.charCodeAt(i++) & 0xff;
		if (i == len) {
			out += base64EncodeChars.charAt(c1 >> 2);
			out += base64EncodeChars.charAt((c1 & 0x3) << 4);
			out += "==";
			break;
		}
		c2 = str.charCodeAt(i++);
		if (i == len) {
			out += base64EncodeChars.charAt(c1 >> 2);
			out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
			out += base64EncodeChars.charAt((c2 & 0xF) << 2);
			out += "=";
			break;
		}
		c3 = str.charCodeAt(i++);
		out += base64EncodeChars.charAt(c1 >> 2);
		out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
		out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
		out += base64EncodeChars.charAt(c3 & 0x3F);
	}
	return out;
}

function base64decode(str) {
	var c1, c2, c3, c4;
	var i, len, out;

	len = str.length;
	i = 0;
	out = "";
	while (i < len) {
		/* c1 */
		do {
			c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
		} while (i < len && c1 == -1);
		if (c1 == -1)
			break;

		/* c2 */
		do {
			c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
		} while (i < len && c2 == -1);
		if (c2 == -1)
			break;

		out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

		/* c3 */
		do {
			c3 = str.charCodeAt(i++) & 0xff;
			if (c3 == 61)
				return out;
			c3 = base64DecodeChars[c3];
		} while (i < len && c3 == -1);
		if (c3 == -1)
			break;

		out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

		/* c4 */
		do {
			c4 = str.charCodeAt(i++) & 0xff;
			if (c4 == 61)
				return out;
			c4 = base64DecodeChars[c4];
		} while (i < len && c4 == -1);
		if (c4 == -1)
			break;
		out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
	}
	return out;
}

function utf16to8(str) {
	var out, i, len, c;

	out = "";
	len = str.length;
	for (i = 0; i < len; i++) {
		c = str.charCodeAt(i);
		if ((c >= 0x0001) && (c <= 0x007F)) {
			out += str.charAt(i);
		} else if (c > 0x07FF) {
			out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
			out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		} else {
			out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		}
	}
	return out;
}

function utf8to16(str) {
	var out, i, len, c;
	var char2, char3;

	out = "";
	len = str.length;
	i = 0;
	while (i < len) {
		c = str.charCodeAt(i++);
		switch (c >> 4) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				// 0xxxxxxx
				out += str.charAt(i - 1);
				break;
			case 12:
			case 13:
				// 110x xxxx 10xx xxxx
				char2 = str.charCodeAt(i++);
				out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
				break;
			case 14:
				// 1110 xxxx 10xx xxxx 10xx xxxx
				char2 = str.charCodeAt(i++);
				char3 = str.charCodeAt(i++);
				out += String.fromCharCode(((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
				break;
		}
	}

	return out;
}

function CharToHex(str) {
	var out, i, len, c, h;

	out = "";
	len = str.length;
	i = 0;
	while (i < len) {
		c = str.charCodeAt(i++);
		h = c.toString(16);
		if (h.length < 2)
			h = "0" + h;

		out += "\\x" + h + " ";
		if (i > 0 && i % 8 == 0)
			out += "\r\n";
	}

	return out;
}

function doEncode() {
	var src = document.getElementById('src').value;
	document.getElementById('dest').value = base64encode(utf16to8(src));
}

function doDecode() {
		var src = document.getElementById('src').value;
		var opts = document.getElementById('opt');

		if (opts.checked) {
			document.getElementById('dest').value = CharToHex(base64decode(src));
		} else {
			document.getElementById('dest').value = utf8to16(base64decode(src));
		}
	}
	// 当页面无信息时显示提示
document.write("<div id='noMessDiv' style='display:none;margin-top:50%;position: absolute;width:100%;z-index: 100;'  class='tip'>");
//document.writeln("<div class='tip_title'>");
//document.writeln("<a id='icon-info'>");
//document.writeln("<span class='mui-icon mui-icon-info'></span>");
//document.writeln("</a>");
//document.writeln("<font>提示</font>");
//document.writeln("</div>");
document.writeln("<div  style='color:#E66B14;text-align:center;font-size:14px;'>");
document.writeln("<font id='noMessText'>暂无信息</font>");
document.writeln("</div>");
document.writeln("</div>");

// 获取时间，格式为XXXX-XX-XX XX：XX：XX
function getFormatDate()
{
   var day=new Date();
   var Year=0;
   var Month=0;
   var Day=0;
   var Hour = 0;
   var Minute = 0;
   var Second = 0;
   var CurrentDate="";
   // 初始化时间
   Year       = day.getFullYear();
   Month      = day.getMonth()+1;
   Day        = day.getDate();
   Hour       = day.getHours();
   Minute     = day.getMinutes();
   Second     = day.getSeconds();
  
   CurrentDate = Year + "-";
   if (Month >= 10 )
   {
    CurrentDate = CurrentDate + Month + "-";
   }
   else
   {
    CurrentDate = CurrentDate + "0" + Month + "-";
   }
   if (Day >= 10 )
   {
    CurrentDate = CurrentDate + Day ;
   }
   else
   {
    CurrentDate = CurrentDate + "0" + Day ;
   }
  
   if(Hour >=10)
   {
    CurrentDate = CurrentDate +" "+ Hour ;
   }
   else
   {
    CurrentDate = "0" + Hour ;
   }
   if(Minute >=10)
   {
    CurrentDate = CurrentDate + ":" + Minute ;
   }
   else
   {
    CurrentDate = CurrentDate + ":0" + Minute ;
   }     
   if(Second>=10)
   {
    CurrentDate = CurrentDate + ":" + Second;
   }
   else
   {
    CurrentDate = CurrentDate + ":0" + Second;
   }
   return CurrentDate;
} 


function getCurrentMonth()
{
   var day=new Date();
   var Year=0;
   var Month=0;
   var CurrentDate="";
   // 初始化时间
   Year       = day.getFullYear();
   Month      = day.getMonth()+1;
   CurrentDate = Year + "-";
   if (Month >= 10 )
   {
    CurrentDate = CurrentDate + Month;
   }
   else
   {
    CurrentDate = CurrentDate + "0" + Month;
   }
   return CurrentDate;
} 

// 比较时间
function comptime(beginTime,endTime) {
    var beginTimes = beginTime.substring(0, 10).split('-');
    var endTimes = endTime.substring(0, 10).split('-');
    beginTimes = beginTime.replace(new RegExp('-', 'g'),"");
    beginTimes = beginTimes.replace(new RegExp(' ', 'g'),"");
    beginTimes = beginTimes.replace(new RegExp(':', 'g'),"");
    endTimes = endTime.replace(new RegExp('-', 'g'),"");
    endTimes = endTimes.replace(new RegExp(' ', 'g'),"");
    endTimes = endTimes.replace(new RegExp(':', 'g'),"");
    return (parseFloat(endTimes)-parseFloat(beginTimes));
   
};


// 获取时间，格式为XXXX-XX-XX XX：XX：XX
function getDateYMD()
{
var day=new Date();
var Year=0;
var Month=0;
var Day=0;
var CurrentDate="";
// 初始化时间
Year       = day.getFullYear();
Month      = day.getMonth()+1;
Day        = day.getDate();

CurrentDate = Year + "-";
if (Month >= 10 )
{
 CurrentDate = CurrentDate + Month + "-";
}
else
{
 CurrentDate = CurrentDate + "0" + Month + "-";
}
if (Day >= 10 )
{
 CurrentDate = CurrentDate + Day ;
}
else
{
 CurrentDate = CurrentDate + "0" + Day ;
}
return CurrentDate;
} 

//添加Cookie 时间以小时计
function addCookie(name, value, expires, path, domain) {
	var str = name + "=" + escape(value);
	if (expires !== "" && expires !== null && expires !== undefined ) {
		var date = new Date();
		date.setTime(date.getTime() + expires * 3600 * 1000);// expires单位为小时
		str += ";expires=" + date.toGMTString();
	} else {
		var date = new Date();
		date.setTime(date.getTime() + 365 * 24 * 3600 * 1000);//若不输入时间，默认保存1年
		str += ";expires=" + date.toGMTString();
	}
	if (path !== "" && path !== null && path !== undefined ) {
		str += ";path=" + path;// 指定可访问cookie的目录
	}
	if (domain !== "" && domain !== null && domain !== undefined ) {
		str += ";domain=" + domain;// 指定可访问cookie的域
	}
	document.cookie = str;
}

// 获取Cookie
function getCookie(cookie_name) {
	var allcookies = document.cookie;
	var cookie_pos = allcookies.indexOf(cookie_name);
	// 如果找到了索引，就代表cookie存在，返回value值。
	// 反之，就说明不存在，返回null值。
	var value = null;
	if (cookie_pos != -1) {
		// 把cookie_pos放在值的开始，只要给值加1即可。
		cookie_pos += cookie_name.length + 1;
		var cookie_end = allcookies.indexOf(";", cookie_pos);
		if (cookie_end == -1) {
			cookie_end = allcookies.length;
		}
		value = unescape(allcookies.substring(cookie_pos, cookie_end));
	}
	return value;
}

// 删除Cookie
function delCookie(name) {// 为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间
	var date = new Date();
	date.setTime(date.getTime() - 10000);
	document.cookie = name + "=a; expires=" + date.toGMTString();
}

//删除所有Cookie
function clearCookie() {
	var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
	if (keys) { 
		for (var i = keys.length; i--;)
			document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString();
	}
}


//模拟map

function HashMap(){  
    //定义长度  
    var length = 0;  
    //创建一个对象  
    var obj = new Object();  
    /** 
    * 判断Map是否为空 
    */  
    this.isEmpty = function(){  
        return length == 0;  
    };  
    /** 
    * 判断对象中是否包含给定Key 
    */  
    this.containsKey=function(key){  
        return (key in obj);  
    };  
    /** 
    * 判断对象中是否包含给定的Value 
    */  
    this.containsValue=function(value){  
        for(var key in obj){  
            if(obj[key] == value){  
                return true;  
            }  
        }  
        return false;  
    };  
    /** 
    *向map中添加数据 
    */  
    this.put=function(key,value){  
        if(!this.containsKey(key)){  
            length++;  
        }  
        obj[key] = value;  
    };  
    /** 
    * 根据给定的Key获得Value 
    */  
    this.get=function(key){  
        return this.containsKey(key)?obj[key]:null;  
    };  
    /** 
    * 根据给定的Key删除一个值 
    */  
    this.remove=function(key){  
        if(this.containsKey(key)&&(delete obj[key])){  
            length--;  
        }  
    };  
    /** 
    * 获得Map中的所有Value 
    */  
    this.values=function(){  
        var _values= new Array();  
        for(var key in obj){  
            _values.push(obj[key]);  
        }  
        return _values;  
    };  
    /** 
    * 获得Map中的所有Key 
    */  
    this.keySet=function(){  
        var _keys = new Array();  
        for(var key in obj){  
            _keys.push(key);  
        }  
        return _keys;  
    };  
    /** 
    * 获得Map的长度 
    */  
    this.size = function(){  
        return length;  
    };  
    /** 
    * 清空Map 
    */  
    this.clear = function(){  
        length = 0;  
        obj = new Object();  
    };  
} 

//模拟表单提交jsPost('IndexPage!wzOrTpOrSq.htm', {
	//    'type': type,
	//    'ID': ID,
	//    'memo':memo
	//});
var jsPost = function(action, values) {
    var id = Math.random();
    document.write('<form id="post' + id + '" name="post'+ id +'" action="' + action + '" method="post">');
    for (var key in values) {
        document.write('<input type="hidden" name="' + key + '" value="' + values[key] + '" />');
    }
    document.write('</form>');    
    document.getElementById('post' + id).submit();
}

//把日期转换为星期
function convertWK(dt){
	var weekDay = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"];
	var myDate = new Date(Date.parse(dt.replace(/-/g, "/"))); 
	return weekDay[myDate.getDay()];
}

function convertWKtwo(dt){
	var weekDay = ["星期一", "周一", "周二", "周三", "周四", "周五", "周六"];
	var myDate = new Date(Date.parse(dt.replace(/-/g, "/"))); 
	return weekDay[myDate.getDay()];
}

//获取当前日期的下一天
function getNextDay(dt){
	var myDate = new Date(Date.parse(dt.replace(/-/g, "/"))); 
	myDate.setDate(myDate.getDate()+1);
	var nextdate = convertDate(myDate).substring(0, 10);
    return nextdate;
}
function GetDateStr(AddDayCount) {
    var day = new Date();
    day.setDate(day.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var Year=0;
    var Month=0;
    var Day=0;
    var Hour = 0;
    var Minute = 0;
    var Second = 0;
    var CurrentDate="";
    // 初始化时间
    Year       = day.getFullYear();
    Month      = day.getMonth()+1;
    Day        = day.getDate();
    Hour       = day.getHours();
    Minute     = day.getMinutes();
    Second     = day.getSeconds();

    CurrentDate = Year + "-";
    if (Month >= 10 )
    {
        CurrentDate = CurrentDate + Month + "-";
    }
    else
    {
        CurrentDate = CurrentDate + "0" + Month + "-";
    }
    if (Day >= 10 )
    {
        CurrentDate = CurrentDate + Day ;
    }
    else
    {
        CurrentDate = CurrentDate + "0" + Day ;
    }

    if(Hour >=10)
    {
        CurrentDate = CurrentDate +" "+ Hour ;
    }
    else
    {
        CurrentDate = "0" + Hour ;
    }
    if(Minute >=10)
    {
        CurrentDate = CurrentDate + ":" + Minute ;
    }
    else
    {
        CurrentDate = CurrentDate + ":0" + Minute ;
    }
    if(Second>=10)
    {
        CurrentDate = CurrentDate + ":" + Second;
    }
    else
    {
        CurrentDate = CurrentDate + ":0" + Second;
    }
    return CurrentDate;//+" 00:00:00"
}
//统一调整到周日
function ToSun(dt){
	//如果是周六显示的是下周日
	if(convertWK(dt)=="周六")
		return getNextDay(dt);
	//否则显示的是这周日
	else{
		var myDate = new Date(Date.parse(dt.replace(/-/g, "/"))); 
		myDate.setDate(myDate.getDate()-parseInt(myDate.getDay(),10));
		var ToSundate = convertDate(myDate).substring(0,10);
		return ToSundate;
	}
}
//初始化一周的hashmap
function initMap(){
	var _xq = new HashMap();
	var weekDay = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"];
	for(var i=0;i<7;i++){
		var _dt = new Array();
		_xq.put(weekDay[i],_dt);
	}
	return _xq;
}
//获取星期几是数组中的index
function getdayindex(dt){
	var weekDay = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
	for(var i=0;i<7;i++){
		if(weekDay[i]==dt)
		  return i;
	}
}