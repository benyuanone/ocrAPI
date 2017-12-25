(function(){function b(c){if(c.isBothPaging()){c.parent.rerender();return true}}function a(c){return(c.toString().search(/^[0-9]+$/)==0)}zul.mesh.Paging=zk.$extends(zul.Widget,{_pageSize:20,_totalSize:0,_pageCount:1,_activePage:0,_pageIncrement:zk.mobile?5:10,setTotalSize:(function(c,d){return function(e,f){var g=this[c];this[c]=e;if(g!==e||(f&&f.force)){this.__fname__=c.substring(1);d.apply(this,arguments);delete this.__fname__}return this}})("_totalSize",(function(){this._updatePageNum();if(this._detailed){if(!b(this)){var c=this.$n("info");if(c){c.innerHTML=this.infoText_()}else{if(this._totalSize){this.rerender()}}}}})),getTotalSize:_zkf$=function(){return this._totalSize},isTotalSize:_zkf$,setPageIncrement:(function(c,d){return function(e,f){var g=this[c];this[c]=e;if(g!==e||(f&&f.force)){this.__fname__=c.substring(1);d.apply(this,arguments);delete this.__fname__}return this}})("_pageIncrement",(_zkf=function(){this.rerender()})),getPageIncrement:_zkf$=function(){return this._pageIncrement},isPageIncrement:_zkf$,setDetailed:(function(c,d){return function(e,f){var g=this[c];this[c]=e;if(g!==e||(f&&f.force)){this.__fname__=c.substring(1);d.apply(this,arguments);delete this.__fname__}return this}})("_detailed",(_zkf)),getDetailed:_zkf$=function(){return this._detailed},isDetailed:_zkf$,setPageCount:(function(c,d){return function(e,f){var g=this[c];this[c]=e;if(g!==e||(f&&f.force)){this.__fname__=c.substring(1);d.apply(this,arguments);delete this.__fname__}return this}})("_pageCount",(_zkf)),getPageCount:_zkf$=function(){return this._pageCount},isPageCount:_zkf$,setActivePage:(function(c,d){return function(e,f){var g=this[c];this[c]=e;if(g!==e||(f&&f.force)){this.__fname__=c.substring(1);d.apply(this,arguments);delete this.__fname__}return this}})("_activePage",(_zkf)),getActivePage:_zkf$=function(){return this._activePage},isActivePage:_zkf$,setPageSize:(function(c,d){return function(e,f){var g=this[c];this[c]=e;if(g!==e||(f&&f.force)){this.__fname__=c.substring(1);d.apply(this,arguments);delete this.__fname__}return this}})("_pageSize",(function(){this._updatePageNum()})),getPageSize:_zkf$=function(){return this._pageSize},isPageSize:_zkf$,setAutohide:(function(c,d){return function(e,f){var g=this[c];this[c]=e;if(g!==e||(f&&f.force)){this.__fname__=c.substring(1);d.apply(this,arguments);delete this.__fname__}return this}})("_autohide",(function(){if(this._pageCount==1){this.rerender()}})),getAutohide:_zkf$=function(){return this._autohide},isAutohide:_zkf$,setStyle:function(){this.$supers("setStyle",arguments);b(this)},setSclass:function(){this.$supers("setSclass",arguments);b(this)},setWidth:function(){this.$supers("setWidth",arguments);b(this)},setHeight:function(){this.$supers("setHeight",arguments);b(this)},setLeft:function(){this.$supers("setLeft",arguments);b(this)},setTop:function(){this.$supers("setTop",arguments);b(this)},setTooltiptext:function(){this.$supers("setTooltiptext",arguments);b(this)},replaceHTML:function(){if(!b(this)){this.$supers("replaceHTML",arguments)}},isBothPaging:function(){return this.parent&&this.parent.getPagingPosition&&"both"==this.parent.getPagingPosition()},_updatePageNum:function(){var d=Math.floor((this._totalSize-1)/this._pageSize+1);if(d==0){d=1}if(d!=this._pageCount){this._pageCount=d;if(this._activePage>=this._pageCount){this._activePage=this._pageCount-1}if(this.desktop&&this.parent){if(!b(this)){this.rerender();if(this.parent.$instanceof(zul.mesh.MeshWidget)){var c=this;setTimeout(function(){if(c.desktop){var e=c.parent.$n();if(e&&e._lastsz){e._lastsz=null;c.parent.onSize()}}})}}}}},infoText_:function(){var d=this._activePage,c=this._pageSize,f=this._totalSize,e=(d+1)*c,g="";if("os"!=this.getMold()){g=" - "+(e>f?f:e)}return"[ "+(d*c+1)+g+" / "+f+" ]"},_infoTags:function(c){if(this._totalSize==0){return}c.push('<div class="',this.$s("info"),'"><span ',b(this)?"name":"id",'="',this.uuid,'-info">',this.infoText_(),"</span></div>")},_innerTags:function(){var e=[],d=this._pageIncrement,h=this._pageCount,g=this._activePage,k=Math.round(d/2),f,c=this._activePage+k-1;if(c>=h){c=h-1;f=c-d+1;if(f<0){f=0}}else{f=this._activePage-k;if(f<0){f=0}c=f+d-1;if(c>=h){c=h-1}}e.push("<ul>");if(g>0){if(f>0){this.appendAnchor(e,msgzul.FIRST,0)}this.appendAnchor(e,msgzul.PREV,g-1)}var i=g<h-1;for(;f<=c;++f){this.appendAnchor(e,f+1,f,f==g)}if(i){this.appendAnchor(e,msgzul.NEXT,g+1);if(c<h-1){this.appendAnchor(e,msgzul.LAST,h-1)}}e.push("</ul>");if(this._detailed){this._infoTags(e)}return e.join("")},appendAnchor:function(e,d,h,f){var g=a(d),c=this.$s("button");if(!g){c+=" "+this.$s("noborder")}if(f){c+=" "+this.$s("selected")}e.push('<li><a class="',c,'" href="javascript:;" onclick="zul.mesh.Paging.go(this,',h,')">',d,"</a></li>")},domClass_:function(){var c=this.$supers(zul.mesh.Paging,"domClass_",arguments),d="os"==this.getMold()?" "+this.$s("os"):"";return c+d},isVisible:function(){var c=this.$supers("isVisible",arguments);return c&&(this._pageCount>1||!this._autohide)},bind_:function(){this.$supers(zul.mesh.Paging,"bind_",arguments);var c=this.uuid,o=jq.$$(c,"real"),h=this.$class,r=this._pageCount,n=this._activePage,q=["first","prev","last","next"],e=zul.mesh.Paging._autoFocusInfo;if(!this.$weave){for(var l=o.length;l--;){jq(o[l]).keydown(h._domKeyDown).blur(h._domBlur)}}for(var f=q.length;f--;){var d=jq.$$(c,q[f]);for(var g=d.length;g--;){if(!this.$weave){jq(d[g]).click(h["_dom"+q[f]+"Click"])}if(r==1){jq(d[g]).attr("disabled",true)}else{if(q[f]=="first"||q[f]=="prev"){if(n==0){jq(d[g]).attr("disabled",true)}}else{if(n==r-1){jq(d[g]).attr("disabled",true)}}}}}if(e&&e.uuid===this.uuid){var m=e.lastPos,p=zk(o[e.inpIdx]);p.focus();p.setSelectionRange(m[0],m[1]);zul.mesh.Paging._autoFocusInfo=null}},unbind_:function(){if(this.getMold()!="os"){var h=this.uuid,e=jq.$$(h,"real"),c=this.$class,l=["first","prev","last","next"];for(var g=e.length;g--;){jq(e[g]).unbind("keydown",c._domKeyDown).unbind("blur",c._domBlur)}for(var d=l.length;d--;){var f=jq.$$(h,l[d]);for(j=f.length;j--;){jq(f[j]).unbind("click",c["_dom"+l[d]+"Click"])}}}this.$supers(zul.mesh.Paging,"unbind_",arguments)}},{go:function(f,h,e){var g=zk.Widget.isInstance(f)?f:zk.Widget.$(f);if(g&&g.getActivePage()!=h){if(e){var d=g.uuid,c=zul.mesh.Paging._autoFocusInfo={uuid:d};c.lastPos=zk(e).getSelectionRange();jq(jq.$$(d,"real")).each(function(i){if(this==e){c.inpIdx=i;return false}})}g.fire("onPaging",h)}},_domKeyDown:function(c){var e=c.target,g=zk.Widget.$(e),f=zk(e).getSelectionRange();if(e.disabled||e.readOnly){return}var d=c.keyCode;switch(d){case 48:case 96:case 49:case 97:case 50:case 98:case 51:case 99:case 52:case 100:case 53:case 101:case 54:case 102:case 55:case 103:case 56:case 104:case 57:case 105:break;case 37:break;case 38:g.$class._increase(e,g,1);c.stop();break;case 39:break;case 40:g.$class._increase(e,g,-1);c.stop();break;case 33:g.$class._increase(e,g,-1);g.$class.go(g,e.value-1,e);c.stop();break;case 34:g.$class._increase(e,g,+1);g.$class.go(g,e.value-1,e);c.stop();break;case 36:g.$class.go(g,0,e);c.stop();break;case 35:g.$class.go(g,g._pageCount-1,e);c.stop();break;case 9:case 8:case 46:break;case 13:g.$class._increase(e,g,0);g.$class.go(g,e.value-1,e);c.stop();break;default:if(!(d>=112&&d<=123)&&!c.ctrlKey&&!c.altKey){c.stop()}}},_domBlur:function(c){var d=c.target,e=zk.Widget.$(d);if(d.disabled||d.readOnly){return}e.$class._increase(d,e,0);e.$class.go(e,d.value-1);c.stop()},_increase:function(c,f,e){var d=zk.parseInt(c.value);d+=e;if(d<1){d=1}else{if(d>f._pageCount){d=f._pageCount}}c.value=d},_domfirstClick:function(c){var h=zk.Widget.$(c),g=h.uuid,l=["first","prev"];if(h.getActivePage()!=0){h.$class.go(h,0);for(var d=l.length;d--;){for(var f=jq.$$(g,l[d]),e=f.length;e--;){jq(f[e]).attr("disabled",true)}}}},_domprevClick:function(c){var l=zk.Widget.$(c),h=l.uuid,g=l.getActivePage(),m=["first","prev"];if(g>0){l.$class.go(l,g-1);if(g-1==0){for(var d=m.length;d--;){for(var f=jq.$$(h,m[d]),e=f.length;e--;){jq(f[e]).attr("disabled",true)}}}}},_domnextClick:function(l){var n=zk.Widget.$(l),c=n.uuid,f=n.getActivePage(),h=n.getPageCount(),m=["last","next"];if(f<h-1){n.$class.go(n,f+1);if(f+1==h-1){for(var e=m.length;e--;){for(var d=jq.$$(c,m[e]),g=d.length;g--;){jq(d[g]).attr("disabled",true)}}}}},_domlastClick:function(c){var l=zk.Widget.$(c),h=l.uuid,e=l.getPageCount(),m=["last","next"];if(l.getActivePage()<e-1){l.$class.go(l,e-1);for(var d=m.length;d--;){for(var g=jq.$$(h,m[d]),f=g.length;f--;){jq(g[f]).attr("disabled",true)}}}}})})();