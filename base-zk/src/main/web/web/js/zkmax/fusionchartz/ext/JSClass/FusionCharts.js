if(typeof infosoftglobal=="undefined"){var infosoftglobal=new Object()}if(typeof infosoftglobal.FusionChartsUtil=="undefined"){infosoftglobal.FusionChartsUtil=new Object()}infosoftglobal.FusionCharts=function(d,a,k,g,m,e,i,l,b,f,j){if(!document.getElementById){return}this.initialDataSet=false;this.params=new Object();this.variables=new Object();this.attributes=new Array();if(d){this.setAttribute("swf",d)}if(a){this.setAttribute("id",a)}k=k.toString().replace(/\%$/,"%25");if(k){this.setAttribute("width",k)}g=g.toString().replace(/\%$/,"%25");if(g){this.setAttribute("height",g)}if(i){this.addParam("bgcolor",i)}this.addParam("quality","high");this.addParam("allowScriptAccess","always");this.addVariable("chartWidth",k);this.addVariable("chartHeight",g);m=m?m:0;this.addVariable("debugMode",m);this.addVariable("DOMId",a);e=e?e:0;this.addVariable("registerWithJS",e);l=l?l:"noScale";this.addVariable("scaleMode",l);b=b?b:"EN";this.addVariable("lang",b);this.detectFlashVersion=f?f:1;this.autoInstallRedirect=j?j:1;this.installedVer=infosoftglobal.FusionChartsUtil.getPlayerVersion();if(!window.opera&&document.all&&this.installedVer.major>7){infosoftglobal.FusionCharts.doPrepUnload=true}};infosoftglobal.FusionCharts.prototype={setAttribute:function(a,b){this.attributes[a]=b},getAttribute:function(a){return this.attributes[a]},addParam:function(a,b){this.params[a]=b},getParams:function(){return this.params},addVariable:function(a,b){this.variables[a]=b},getVariable:function(a){return this.variables[a]},getVariables:function(){return this.variables},getVariablePairs:function(){var a=new Array();var b;var c=this.getVariables();for(b in c){a.push(b+"="+c[b])}return a},getSWFHTML:function(){var d="";if(navigator.plugins&&navigator.mimeTypes&&navigator.mimeTypes.length){d='<embed type="application/x-shockwave-flash" src="'+this.getAttribute("swf")+'" width="'+this.getAttribute("width")+'" height="'+this.getAttribute("height")+'"  ';d+=' id="'+this.getAttribute("id")+'" name="'+this.getAttribute("id")+'" ';var c=this.getParams();for(var a in c){d+=[a]+'="'+c[a]+'" '}var b=this.getVariablePairs().join("&");if(b.length>0){d+='flashvars="'+b+'"'}d+="/>"}else{d='<object id="'+this.getAttribute("id")+'" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="'+this.getAttribute("width")+'" height="'+this.getAttribute("height")+'">';d+='<param name="movie" value="'+this.getAttribute("swf")+'" />';var c=this.getParams();for(var a in c){d+='<param name="'+a+'" value="'+c[a]+'" />'}var b=this.getVariablePairs().join("&");if(b.length>0){d+='<param name="flashvars" value="'+b+'" />'}d+="</object>"}return d},setDataURL:function(a){if(this.initialDataSet==false){this.addVariable("dataURL",a);this.initialDataSet=true}else{var b=infosoftglobal.FusionChartsUtil.getChartObject(this.getAttribute("id"));if(!b.setDataURL){__flash__addCallback(b,"setDataURL")}b.setDataURL(a)}},encodeDataXML:function(d){var g=["\\$","\\+"];var c=d.match(/=\s*\".*?\"/g);if(c){for(var e=0;e<c.length;e++){var h=c[e].replace(/^=\s*\"|\"$/g,"");h=h.replace(/\'/g,"%26apos;");var f=d.indexOf(c[e]);var a="='"+h+"'";var b=d.substring(0,f);var j=d.substring(f+c[e].length);var d=b+a+j}}d=d.replace(/\"/g,"%26quot;");d=d.replace(/%(?![\da-f]{2}|[\da-f]{4})/ig,"%25");d=d.replace(/\&/g,"%26");return d},setDataXML:function(a){if(this.initialDataSet==false){this.addVariable("dataXML",this.encodeDataXML(a));this.initialDataSet=true}else{var b=infosoftglobal.FusionChartsUtil.getChartObject(this.getAttribute("id"));b.setDataXML(a)}},setTransparent:function(a){if(typeof a=="undefined"){a=true}if(a){this.addParam("WMode","transparent")}else{this.addParam("WMode","Opaque")}},render:function(a){if((this.detectFlashVersion==1)&&(this.installedVer.major<6)){if(this.autoInstallRedirect==1){var b=window.confirm("You need Adobe Flash Player 6 (or above) to main the charts. It is a free and lightweight installation from Adobe.com. Please click on Ok to install the same.");if(b){window.location="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash"}else{return false}}else{return false}}else{var c=(typeof a=="string")?document.getElementById(a):a;c.innerHTML=this.getSWFHTML();if(!document.embeds[this.getAttribute("id")]&&!window[this.getAttribute("id")]){window[this.getAttribute("id")]=document.getElementById(this.getAttribute("id"))}return true}}};infosoftglobal.FusionChartsUtil.getPlayerVersion=function(){var c=new infosoftglobal.PlayerVersion([0,0,0]);if(navigator.plugins&&navigator.mimeTypes.length){var a=navigator.plugins["Shockwave Flash"];if(a&&a.description){c=new infosoftglobal.PlayerVersion(a.description.replace(/([a-zA-Z]|\s)+/,"").replace(/(\s+r|\s+b[0-9]+)/,".").split("."))}}else{if(navigator.userAgent&&navigator.userAgent.indexOf("Windows CE")>=0){var d=1;var b=3;while(d){try{b++;d=new ActiveXObject("ShockwaveFlash.ShockwaveFlash."+b);c=new infosoftglobal.PlayerVersion([b,0,0])}catch(f){d=null}}}else{try{var d=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7")}catch(f){try{var d=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");c=new infosoftglobal.PlayerVersion([6,0,21]);d.AllowScriptAccess="always"}catch(f){if(c.major==6){return c}}try{d=new ActiveXObject("ShockwaveFlash.ShockwaveFlash")}catch(f){}}if(d!=null){c=new infosoftglobal.PlayerVersion(d.GetVariable("$version").split(" ")[1].split(","))}}}return c};infosoftglobal.PlayerVersion=function(a){this.major=a[0]!=null?parseInt(a[0]):0;this.minor=a[1]!=null?parseInt(a[1]):0;this.rev=a[2]!=null?parseInt(a[2]):0};infosoftglobal.FusionChartsUtil.cleanupSWFs=function(){var c=document.getElementsByTagName("OBJECT");for(var b=c.length-1;b>=0;b--){c[b].style.display="none";for(var a in c[b]){if(typeof c[b][a]=="function"){c[b][a]=function(){}}}}};if(infosoftglobal.FusionCharts.doPrepUnload){if(!infosoftglobal.unloadSet){infosoftglobal.FusionChartsUtil.prepUnload=function(){__flash_unloadHandler=function(){};__flash_savedUnloadHandler=function(){};window.attachEvent("onunload",infosoftglobal.FusionChartsUtil.cleanupSWFs)};window.attachEvent("onbeforeunload",infosoftglobal.FusionChartsUtil.prepUnload);infosoftglobal.unloadSet=true}}if(!document.getElementById&&document.all){document.getElementById=function(a){return document.all[a]}}if(Array.prototype.push==null){Array.prototype.push=function(a){this[this.length]=a;return this.length}}infosoftglobal.FusionChartsUtil.getChartObject=function(b){var a=null;if(navigator.appName.indexOf("Microsoft Internet")==-1){if(document.embeds&&document.embeds[b]){a=document.embeds[b]}else{a=window.document[b]}}else{a=window[b]}if(!a){a=document.getElementById(b)}return a};infosoftglobal.FusionChartsUtil.updateChartXML=function(c,b){var a=infosoftglobal.FusionChartsUtil.getChartObject(c);a.SetVariable("_root.dataURL","");a.SetVariable("_root.isNewData","1");a.SetVariable("_root.newData",b);a.TGotoLabel("/","JavaScriptHandler")};var getChartFromId=infosoftglobal.FusionChartsUtil.getChartObject;var updateChartXML=infosoftglobal.FusionChartsUtil.updateChartXML;var FusionCharts=infosoftglobal.FusionCharts;