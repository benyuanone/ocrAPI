zkmax.layout.Portallayout=zk.$extends(zul.Widget,{_maximizedMode:"column",_orient:"vertical",$init:function(){this.$supers("$init",arguments);this.drags={}},setMaximizedMode:(function(a){return function(b){this[a]=b;return this}})("_maximizedMode"),getMaximizedMode:_zkf$=function(){return this._maximizedMode},isMaximizedMode:_zkf$,setOrient:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_orient",(_zkf)),getOrient:_zkf$=function(){return this._orient},isOrient:_zkf$,bind_:function(){this.$supers(zkmax.layout.Portallayout,"bind_",arguments);zWatch.listen({onSize:this});for(var b=this.firstChild;b;b=b.nextSibling){if(b._renderdefer){continue}for(var a=b.firstChild;a;a=a.nextSibling){if(a._renderdefer){continue}this._initDrag(a)}}},unbind_:function(){zWatch.unlisten({onSize:this});for(var b=this.firstChild;b;b=b.nextSibling){for(var a=b.firstChild;a;a=a.nextSibling){this._cleanupDrag(a)}}this.$supers(zkmax.layout.Portallayout,"unbind_",arguments)},_initDrag:function(a){if(a._draggable=="false"){return}var d=a.$n();if(this.drags[d.id]){return}var c=a.$n("cap");if(!c){return}var b=this.$class;this.drags[d.id]=new zk.Draggable(a,d,{handle:c,zIndex:99999,stackup:true,starteffect:zul.wnd.Panel._startmove,ghosting:b._ghostMove,ignoredrag:b._ignoreMove,endeffect:b._endMove,change:b._changeMove});jq(c).addClass(a.$s("header-move"));zk(c).disableSelection()},_cleanupDrag:function(a){var c;if(a&&this.drags[c=a.uuid]){this.drags[c].destroy();delete this.drags[c];var b=a.$n("cap");if(b){b.style.cursor=""}}},getPanel:function(a,b){if(a<0||b<0||this.nChildren<=a){return null}var c=this.getChildAt(a);if(c.nChildren<=b){return null}return c.getChildAt(b)},setPanel:function(a,b,c){if(b<0||c<0||a==null||this.nChildren<=b){return false}var d=this.getChildAt(b);if(d.nChildren>=c){return d.appendChild(a)}else{return d.insertBefore(a,d.getChildAt(c))}},getPosition:function(a){var d=[-1,-1],c=a.parent;if(a==null||c==null){return d}for(var b=0;b<c.nChildren;b++){if(a==c.getChildAt(b)){d[1]=b}}for(var b=0;b<this.nChildren;b++){if(c==this.getChildAt(b)){d[0]=b}}return d},_renderChild:function(b){if(this.desktop){for(var a=b.firstChild;a;a=a.nextSibling){if(a.$n()){this._initDrag(a)}}this.render()}},onChildAdded_:function(a){this.$supers("onChildAdded_",arguments);this._renderChild(a)},onChildRemoved_:function(b){this.$supers("onChildRemoved_",arguments);if(this.desktop){for(var a=b.firstChild;a;a=a.nextSibling){this._cleanupDrag(a)}if(!this.childReplacing_){this.render()}}},onChildRenderDefer_:function(a){a._renderdefer=-1;this._renderChild(a)},render:_zkf=function(){var d=this.$n();if(!zk(d).isRealVisible()){return}var a=this.isVertical(),c=a?d.offsetWidth:d.offsetHeight,b=jq(d).find(">div:first")[0],f;for(;b;b=jq(b).next("div:first")[0]){f=zk.Widget.$(b.id);if(f.desktop){var e=f._oriSize;if(e&&e.indexOf("px")>0){c-=a?b.offsetWidth:b.offsetHeight}}}c=Math.max(0,c);b=jq(d).find(">div:first")[0];for(;b;b=jq(b).next("div:first")[0]){f=zk.Widget.$(b.id);if(f.desktop){var e=f._oriSize;if(e&&e.indexOf("%")>0){b.style[a?"width":"height"]=jq.px0((Math.floor(zk.parseInt(e)/100*c))-1)}}}},onSize:_zkf,domClass_:function(b){var a=this.$supers("domClass_",arguments);if(!b||!b.zclass){a+=" "+this.$s(this.getOrient())}return a},isVertical:function(){return this.getOrient()=="vertical"},setWidth:function(){this.$supers("setWidth",arguments);this.onSize()}},{_getColWidths:function(e){var a=e.nChildren,d=[],b=e.isVertical()?function(g){return zk(g).revisedOffset()[0]}:function(g){return zk(g).revisedOffset()[1]};for(var c=0;c<a;c++){var f=e.getChildAt(c).$n();if(jq(f).is(":visible")){d.push(b(f))}else{d.push(-1)}}return d},_getColHeights:function(e){var a=e.nChildren,d=[],b=e.parent.isVertical()?function(g){return zk(g).revisedOffset()[1]+g.offsetHeight/2}:function(g){return zk(g).revisedOffset()[0]+g.offsetWidth/2};for(var c=0;c<a;c++){var f=e.getChildAt(c).$n();if(jq(f).is(":visible")){d.push(b(f))}else{d.push(-1)}}return d},_changeMove:function(o,j,k){var f=o.control,p=f.parent,b=p.parent,q=jq("#"+f.uuid+"-proxy")[0];var e=o._widths,i=e.length,a=j,l=b.isVertical(),d=l?a[0]:a[1],c=l?a[1]:a[0];for(;--i>=0;){if(e[i]!=-1&&e[i]<=d){break}}if(i<0){i=0}var g=b.getChildAt(i),r=g.firstChild,n=b.$class._getColHeights(g),h=0,m=n.length;while(h<m){if(n[h]!=-1&&n[h]>c){break}h++}if(r=g.getChildAt(h)){if(h<m){jq(q).insertBefore(r.$n())}else{jq(q).insertAfter(r.$n())}}else{g.$n("cave").appendChild(q)}},_ignoreMove:function(b,c,a){return b.control.isMaximized()||zul.wnd.Panel._ignoremove(b,c,a)},_initProxy:function(e,d){var a=document.createElement("div"),c=a.style,b=d.style;a.id=d.id+"-proxy";c.marginTop=b.marginTop;c.marginLeft=b.marginLeft;c.marginRight=b.marginRight;c.marginBottom=b.marginBottom;jq(a).addClass("z-panel-move-block");if(e.isVertical()){c.width="auto";c.height="10px"}else{c.height="100%";c.width="10px"}d.parentNode.insertBefore(a,d.previousSibling);jq(d).hide()},_cleanProxy:function(a){jq(a.id+"-proxy",zk).remove();jq(a).show()},_ghostMove:function(i,g,k){var f=i.control.parent.parent,a=i.node=jq("#"+i.handle.id.split("-")[0])[0],b=zk(a),j=jq(a).find("#"+a.id+"-head")[0],c=jq(j).clone()[0],e=jq(a).height()-jq(j).height()-zk(j).padBorderHeight(),d=f.$class;jq(document.body).prepend('<div id="zk_ddghost" class="z-panel-move-ghost" style="position:absolute;top:'+g[1]+"px;left:"+g[0]+"px;width:"+b.offsetWidth()+"px;height:"+b.offsetHeight()+"px;z-index:"+a.style.zIndex+'"><dl></dl></div>');i._zoffs=g;i._cns=f.firstChild.$n();i._widths=d._getColWidths(f);d._initProxy(f,a);a=i.node=jq("#zk_ddghost")[0];a.lastChild.style.height=zk(a.lastChild).revisedHeight(e,true)+"px";a.insertBefore(c,a.lastChild);return i.node},_endMove:function(k,m){var a=k.control,n=a.$n(),g=a.parent,h=jq(n.id+"-proxy",zk)[0],b=zk.Widget.$(h.parentNode.id.split("-")[0]),j=jq(k.node).next("div:first")[0]!=h,l=a.parent.parent,f=l.$class;if(j){if(h.nextSibling){var d=zk.Widget.$("#"+h.nextSibling.id.split("-")[0]);b.insertBefore(a,d)}else{b.appendChild(a)}for(var e=0;e<b.nChildren;e++){var c=b.getChildAt(e);if(c.isMaximized()){c.setMaximized(false)}}l.fire("onPortalMove",{from:g.uuid,to:b.uuid,dragged:a.uuid,index:jq(h).index()},{toServer:true},0)}f._cleanProxy(a.$n());if(j){zUtl.fireSized(l)}k._cns=k._columns=null}});