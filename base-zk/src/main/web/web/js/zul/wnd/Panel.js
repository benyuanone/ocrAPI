(function(){function a(c,b){while(c&&c.parentNode!=b){c=c.parentNode}return c}zul.wnd.Panel=zk.$extends(zul.Widget,{_border:"none",_title:"",_open:true,_minheight:100,_minwidth:200,$init:function(){this.$supers("$init",arguments);this.listen({onMaximize:this,onClose:this,onMove:this,onSize:this.onSizeEvent},-1000);this._skipper=new zul.wnd.PanelSkipper(this)},setMinheight:(function(b){return function(c){this[b]=c;return this}})("_minheight"),getMinheight:_zkf$=function(){return this._minheight},isMinheight:_zkf$,setMinwidth:(function(b){return function(c){this[b]=c;return this}})("_minwidth"),getMinwidth:_zkf$=function(){return this._minwidth},isMinwidth:_zkf$,setSizable:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_sizable",(function(b){if(this.desktop){if(b){this._makeSizer()}else{if(this._sizer){this._sizer.destroy();this._sizer=null}}}})),getSizable:_zkf$=function(){return this._sizable},isSizable:_zkf$,setMovable:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_movable",(_zkf=function(){var b=this._lastSize;this.rerender(this._skipper);if(b){this._lastSize=b}})),getMovable:_zkf$=function(){return this._movable},isMovable:_zkf$,setFloatable:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_floatable",(_zkf)),getFloatable:_zkf$=function(){return this._floatable},isFloatable:_zkf$,setMaximizable:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_maximizable",(_zkf)),getMaximizable:_zkf$=function(){return this._maximizable},isMaximizable:_zkf$,setMinimizable:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_minimizable",(_zkf)),getMinimizable:_zkf$=function(){return this._minimizable},isMinimizable:_zkf$,setCollapsible:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_collapsible",(_zkf)),getCollapsible:_zkf$=function(){return this._collapsible},isCollapsible:_zkf$,setClosable:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_closable",(_zkf)),getClosable:_zkf$=function(){return this._closable},isClosable:_zkf$,setBorder:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_border",(function(){var b=this._lastSize;this.rerender();if(b){this._lastSize=b}})),getBorder:_zkf$=function(){return this._border},isBorder:_zkf$,setTitle:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_title",(function(){if(this.caption){this.caption.updateDomContent_()}else{var b=this._lastSize;this.rerender(this._skipper);if(b){this._lastSize=b}}})),getTitle:_zkf$=function(){return this._title},isTitle:_zkf$,setOpen:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_open",(function(d,c){var e=this.$n(),b=this.getCollapseOpenIconClass_(),g=this.getCollapseCloseIconClass_();if(e){var f=jq(this.$n("body"));if(f[0]&&!f.is(":animated")){if(d){jq(e).removeClass(this.$s("collapsed"));jq(this.$n("exp")).children("."+g).removeClass(g).addClass(b);f.zk.slideDown(this)}else{jq(e).addClass(this.$s("collapsed"));jq(this.$n("exp")).children("."+b).removeClass(b).addClass(g);this._hideShadow();f.zk.slideUp(this)}if(!c){this.fire("onOpen",{open:d})}}}})),getOpen:_zkf$=function(){return this._open},isOpen:_zkf$,setMaximized:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_maximized",(function(c,n){var v=this.$n();if(v){var B=zk(v),D=B.isRealVisible();if(!D&&c){return}var x,k,i,z,m=v.style,f=this.getMaximizableIconClass_(),g=this.getMaximizedIconClass_();if(c){jq(this.$n("max")).addClass(this.$s("maximized")).children("."+f).removeClass(f).addClass(g);this._hideShadow();if(this._collapsible&&!this._open){B.jq.removeClass(this.$s("collapsed"));var j=this.$n("body");if(j){j.style.display=""}}var b=this.isFloatable(),e=b?jq(v).offsetParent():jq(v).parent(),C=e[0].clientHeight;if(zk.isLoaded("zkmax.layout")&&this.parent.$instanceof(zkmax.layout.Portalchildren)){var A=this.parent.parent;if(A.getMaximizedMode()=="whole"){this._inWholeMode=true;var r=A.$n(),u=r.style;C=r.clientHeight;var q=this._oldNodeInfo={_scrollTop:r.parentNode.scrollTop};r.parentNode.scrollTop=0;B.makeVParent();zWatch.fireDown("onVParent",this);q._pos=m.position;q._ppos=u.position;q._zIndex=m.zIndex;m.position="absolute";this.setFloating_(true);this.setTopmost();r.appendChild(v);u.position="relative";if(!u.height){u.height=jq.px0(C);q._pheight=true}}}var b=this.isFloatable(),e=b?jq(v).offsetParent():jq(v).parent();x=m.left;k=m.top;i=m.width;z=m.height;m.top="-10000px";m.left="-10000px";var o=e[0].clientWidth;if(!b){o-=e.zk.paddingWidth();o=B.revisedWidth(o);C-=e.zk.paddingHeight();C=B.revisedHeight(C)}m.width=jq.px0(o);m.height=jq.px0(C);this._lastSize={l:x,t:k,w:i,h:z};m.top="0";m.left="0";i=m.width;z=m.height}else{var y=this.$n("max"),d=jq(y);d.removeClass(this.$s("maximized")).children("."+g).removeClass(g).addClass(f);if(this._lastSize){m.left=this._lastSize.l;m.top=this._lastSize.t;m.width=this._lastSize.w;m.height=this._lastSize.h;this._lastSize=null}x=m.left;k=m.top;i=m.width;z=m.height;if(this._collapsible&&!this._open){jq(v).addClass(this.$s("collapsed"));var j=this.$n("body");if(j){j.style.display="none"}}var j=this.panelchildren?this.panelchildren.$n():null;if(j){j.style.width=j.style.height=""}if(this._inWholeMode){B.undoVParent();zWatch.fireDown("onVParent",this);var q=this._oldNodeInfo;v.style.position=q?q._pos:"";this.setZIndex((q?q._zIndex:""),{fire:true});this.setFloating_(false);var r=this.parent.parent.$n();r.style.position=q?q._ppos:"";r.parentNode.scrollTop=q?q._scrollTop:0;if(q&&q._pheight){r.style.height=""}this._oldNodeInfo=null;this._inWholeMode=false}}if(!n&&D){this._visible=true;this.fire("onMaximize",{left:x,top:k,width:i,height:z,maximized:c,fromServer:n})}if(D){zUtl.fireSized(this)}}})),getMaximized:_zkf$=function(){return this._maximized},isMaximized:_zkf$,setMinimized:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_minimized",(function(e,d){if(this._maximized){this.setMaximized(false)}var j=this.$n();if(j){var i=j.style,c=i.left,f=i.top,b=i.width,g=i.height;if(e){zWatch.fireDown("onHide",this);jq(j).hide()}else{jq(j).show();zUtl.fireShown(this)}if(!d){this._visible=false;this.fire("onMinimize",{left:i.left,top:i.top,width:i.width,height:i.height,minimized:e})}}})),getMinimized:_zkf$=function(){return this._minimized},isMinimized:_zkf$,setTbar:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_tbar",(function(b){this.tbar=zk.Widget.$(b);if(this.bbar==this.tbar){this.bbar=null}if(this.fbar==this.tbar){this.fbar=null}this.rerender()})),getTbar:_zkf$=function(){return this._tbar},isTbar:_zkf$,setBbar:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_bbar",(function(b){this.bbar=zk.Widget.$(b);if(this.tbar==this.bbar){this.tbar=null}if(this.fbar==this.bbar){this.fbar=null}this.rerender()})),getBbar:_zkf$=function(){return this._bbar},isBbar:_zkf$,setFbar:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_fbar",(function(b){this.fbar=zk.Widget.$(b);if(this.tbar==this.fbar){this.tbar=null}if(this.bbar==this.fbar){this.bbar=null}this.rerender()})),getFbar:_zkf$=function(){return this._fbar},isFbar:_zkf$,setVisible:function(b){if(this._visible!=b){if(this._maximized){this.setMaximized(false)}else{if(this._minimized){this.setMinimized(false)}}this.$supers("setVisible",arguments)}},setHeight:function(){this.$supers("setHeight",arguments);if(this.desktop){zUtl.fireSized(this)}},setWidth:function(){this.$supers("setWidth",arguments);if(this.desktop){zUtl.fireSized(this)}},setTop:function(){this._hideShadow();this.$supers("setTop",arguments);this.zsync()},setLeft:function(){this._hideShadow();this.$supers("setLeft",arguments);this.zsync()},updateDomStyle_:function(){this.$supers("updateDomStyle_",arguments);if(this.desktop){zUtl.fireSized(this)}},addToolbar:function(b,c){switch(b){case"tbar":this.tbar=c;break;case"bbar":this.bbar=c;break;case"fbar":this.fbar=c;break;default:return false}return this.appendChild(c)},onClose:function(){if(!this.inServer||!this.isListen("onClose",{asapOnly:1})){this.parent.removeChild(this)}},onMove:function(b){this._left=b.left;this._top=b.top},onMaximize:function(b){var c=b.data;this._top=c.top;this._left=c.left;this._height=c.height;this._width=c.width},onSizeEvent:function(b){var f=b.data,e=this.$n(),d=e.style;this._hideShadow();if(f.width!=d.width){this._width=d.width=f.width}if(f.height!=d.height){this._height=d.height=f.height;this._fixHgh(true)}if(f.left!=d.left||f.top!=d.top){d.left=f.left;d.top=f.top;this.fire("onMove",zk.copy({left:e.style.left,top:e.style.top},b.data),{ignorable:true})}this.zsync();var c=this;setTimeout(function(){zUtl.fireSized(c)})},setFlexSizeH_:function(e,c,b,d){if(d){b+=this._titleHeight(e)}this.$supers("setFlexSizeH_",arguments)},setFlexSizeW_:function(e,b,d,c){if(c&&this.caption){if(d==this.caption.$n().offsetWidth){d+=zk(this.$n("head")).padBorderWidth()}}this.$supers("setFlexSizeW_",arguments)},beforeSize:function(){if(!this._flexListened){this.$n("body").style.width=""}},resetSize_:function(b){this.$supers(zul.wnd.Panel,"resetSize_",arguments);(this.$n("body")).style[b=="w"?"width":"height"]=""},onSize:(function(){function b(k){if(!k._lastSize){return}var g=k.$n(),f=zk(g),i=k.isFloatable(),j=i?jq(g).offsetParent():jq(g).parent(),e=g.style;var c=j[0].clientWidth;if(!i){c-=j.zk.paddingWidth();c=f.revisedWidth(c)}e.width=jq.px0(c);if(k._open){var d=j[0].clientHeight;if(!i){d-=j.zk.paddingHeight();d=f.revisedHeight(d)}e.height=jq.px0(d)}}return function(c){this._hideShadow();if(this._maximized){b(this)}if(this.tbar){c.fireDown(this.tbar)}if(this.bbar){c.fireDown(this.bbar)}if(this.fbar){c.fireDown(this.fbar)}this._fixHgh(true);this._fixWdh();this.zsync()}})(),onHide:function(){this._hideShadow()},_fixHgh:function(e){var c;if(!(c=this.panelchildren)||c.z_rod||(!e&&!this.isRealVisible())){return}var f=this.$n(),b=c.$n(),d=f.style.height;if(d&&d!="auto"){b.style.height=jq.px0(this._offsetHeight(f))}},_fixWdh:function(){var d=this.panelchildren;if(!d||d.z_rod||!this.isRealVisible()){return}var b=d.$n(),e,f;if(b&&(e=b.style)&&(f=e.width)&&f!="auto"){var c=zk(this.$n()).contentWidth();e.width=c-zk(this.$n("body")).padBorderWidth()+"px"}},_rounded:_zkf=function(){return this._border.startsWith("rounded")},isFramable:_zkf,_bordered:function(){var b;return(b=this._border)!="none"&&b!="rounded"},_offsetHeight:function(g){var e=this._titleHeight(),b=this.$n("body");h=zk(e?g:b).contentHeight()-this._titleHeight();if(e){h-=zk(b).padBorderHeight()}var c=this.tbar?this.$n("tb"):null,f=this.bbar?this.$n("bb"):null,d=this.fbar?this.$n("fb"):null;if(c){h-=c.offsetHeight}if(f){h-=f.offsetHeight}if(d){h-=d.offsetHeight}return h},_titleHeight:function(){var b=this.getTitle()||this.caption?this.$n("head"):null;return b?b.offsetHeight:0},onFloatUp:function(b){if(!this._visible||!this.isFloatable()){return}for(var c=b.origin;c;c=c.parent){if(c==this){this.setTopmost();return}if(c.isFloating_()){return}}},_makeSizer:function(){if(!this._sizer){this.domListen_(this.$n(),"onMouseMove");this.domListen_(this.$n(),"onMouseOut");var b=this.$class;this._sizer=new zk.Draggable(this,null,{stackup:true,draw:b._drawsizing,snap:b._snapsizing,starteffect:b._startsizing,ghosting:b._ghostsizing,endghosting:b._endghostsizing,ignoredrag:b._ignoresizing,endeffect:b._aftersizing})}},_initFloat:function(){var c=this.$n();if(!c.style.top||!c.style.left){var b=zk(c).revisedOffset();c.style.left=jq.px(b[0]);c.style.top=jq.px(b[1])}c.style.position="absolute";if(this.isMovable()){this._initMove()}this.zsync();if(this.isRealVisible()){this.setTopmost()}},_initMove:function(c){var d=this.$n("head");if(d&&!this._drag){jq(d).addClass(this.$s("header-move"));var b=this.$class;this._drag=new zk.Draggable(this,null,{handle:d,stackup:true,starteffect:b._startmove,ignoredrag:b._ignoremove,endeffect:b._aftermove})}},zsync:function(){this.$supers("zsync",arguments);if(!this.isFloatable()){if(this._shadow){this._shadow.destroy();this._shadow=null}}else{var b=this.$n("body");if(b&&zk(b).isRealVisible()){if(!this._shadow){this._shadow=new zk.eff.Shadow(this.$n(),{left:-4,right:4,top:-2,bottom:3})}if(this._maximized||this._minimized||!this._visible){this._hideShadow()}else{this._shadow.sync()}}}},_hideShadow:function(){var b=this._shadow;if(b){b.hide()}},afterAnima_:function(e){this.$supers("afterAnima_",arguments);var d=this.parent;for(var f=d.firstChild;f;f=f.nextSibling){if(f==this){continue}var b=f.getVflex();if(b&&b!="min"){zUtl.fireSized(d);break}}},bind_:function(g,f,e){this.$supers(zul.wnd.Panel,"bind_",arguments);zWatch.listen({onSize:this,onHide:this});var d=this.uuid,c=this.$class;if(this._sizable){this._makeSizer()}if(this.isFloatable()){zWatch.listen({onFloatUp:this});this.setFloating_(true);this._initFloat();if(!zk.css3){jq.onzsync(this)}}if(this._maximizable&&this._maximized){var b=this;e.push(function(){b._maximized=false;b.setMaximized(true,true)})}},unbind_:function(){if(this._inWholeMode){var c=this.$n(),b;if(zk.ie>9){var e=jq(this.$n()).find("iframe");if(e.length){e.hide().remove()}}zk(c).undoVParent();var d=this.parent;if(d&&(d=d.parent)&&(d=d.$n())&&(b=this._oldNodeInfo)){d.style.position=b._ppos;d.parentNode.scrollTop=b._scrollTop}this._inWholeMode=false}zWatch.unlisten({onSize:this,onHide:this,onFloatUp:this});this.setFloating_(false);if(!zk.css3){jq.unzsync(this)}if(this._shadow){this._shadow.destroy();this._shadow=null}if(this._drag){this._drag.destroy();this._drag=null}if(this._sizer){this._sizer.destroy();this._sizer=null}this.domUnlisten_(this.$n(),"onMouseMove");this.domUnlisten_(this.$n(),"onMouseOut");this.$supers(zul.wnd.Panel,"unbind_",arguments)},_doMouseMove:function(b){if(this._sizer&&zUtl.isAncestor(this,b.target)){var f=this.$n(),e=this.$class._insizer(f,zk(f).revisedOffset(),b.pageX,b.pageY),d=this.isMovable()?this.$n("head"):false;if(!this._maximized&&this._open&&e){if(this._backupCursor==undefined){this._backupCursor=f.style.cursor}f.style.cursor=e==1?"n-resize":e==2?"ne-resize":e==3?"e-resize":e==4?"se-resize":e==5?"s-resize":e==6?"sw-resize":e==7?"w-resize":"nw-resize";if(d){jq(d).removeClass(this.$s("header-move"))}}else{f.style.cursor=this._backupCursor||"";if(d){jq(d).addClass(this.$s("header-move"))}}}},_doMouseOut:function(b){this.$n().style.cursor=this._backupCursor||""},doClick_:function(c){var e=this.$n("max"),f=this.$n("min"),g=c.domTarget;if(!g.id){g=g.parentNode}switch(g){case this.$n("close"):this.fire("onClose");break;case e:this.setMaximized(!this._maximized);break;case f:this.setMinimized(!this._minimized);break;case this.$n("exp"):var b=this.$n("body"),d=b?zk(b).isVisible():this._open;if(!d==this._open){this._open=d}this.setOpen(!d);break;default:this.$supers("doClick_",arguments);return}c.stop()},domClass_:function(d){var c=this.$supers("domClass_",arguments);if(!d||!d.zclass){var b=this._bordered()?"":this.$s("noborder");if(b){c+=(c?" ":"")+b}b=this._open?"":this.$s("collapsed");if(b){c+=(c?" ":"")+b}if(!(this.getTitle()||this.caption)){c+=" "+this.$s("noheader")}if(!this._rounded()){c+=" "+this.$s("noframe")}}return c},onChildAdded_:function(b){this.$supers("onChildAdded_",arguments);if(b.$instanceof(zul.wgt.Caption)){this.caption=b}else{if(b.$instanceof(zul.wnd.Panelchildren)){this.panelchildren=b}else{if(b.$instanceof(zul.wgt.Toolbar)){if(this.firstChild==b||(this.nChildren==(this.caption?2:1))){this.tbar=b}else{if(this.lastChild==b&&b.previousSibling.$instanceof(zul.wgt.Toolbar)){this.fbar=b}else{if(b.previousSibling.$instanceof(zul.wnd.Panelchildren)){this.bbar=b}}}}}}this.rerender()},onChildRemoved_:function(b){this.$supers("onChildRemoved_",arguments);if(b==this.caption){this.caption=null}else{if(b==this.panelchildren){this.panelchildren=null}else{if(b==this.tbar){this.tbar=null}else{if(b==this.bbar){this.bbar=null}else{if(b==this.fbar){this.fbar=null}}}}}if(!this.childReplacing_){this.rerender()}},onChildVisible_:function(b){this.$supers("onChildVisible_",arguments);if((b==this.tbar||b==this.bbar||b==this.fbar)&&this.$n()){this._fixHgh()}},getChildMinSize_:function(b,d){var c=true;if(d==this.caption){if(b=="w"){c=!!(d.$n().style.width)}else{c=!!(d.$n().style.height)}}if(c){return this.$supers("getChildMinSize_",arguments)}else{return 0}},isExcludedHflex_:function(){if(zk.isLoaded("zkmax.layout")&&this.parent.$instanceof(zkmax.layout.Portalchildren)){var b=this.parent;if(b.parent){return b.parent.isVertical()}}},isExcludedVflex_:function(){if(zk.isLoaded("zkmax.layout")&&this.parent.$instanceof(zkmax.layout.Portalchildren)){var b=this.parent;if(b.parent){return !(b.parent.isVertical())}}},getCollapseOpenIconClass_:function(){return"z-icon-caret-up"},getCollapseCloseIconClass_:function(){return"z-icon-caret-down"},getClosableIconClass_:function(){return"z-icon-times"},getMaximizableIconClass_:function(){return"z-icon-resize-full"},getMaximizedIconClass_:function(){return"z-icon-resize-small"},getMinimizableIconClass_:function(){return"z-icon-minus"}},{_startmove:function(c){c.control._hideShadow();var b=c.node;if(b.style.top&&b.style.top.indexOf("%")>=0){b.style.top=b.offsetTop+"px"}if(b.style.left&&b.style.left.indexOf("%")>=0){b.style.left=b.offsetLeft+"px"}},_ignoremove:function(d,f,c){var e=d.control,b=c.domTarget;if(!b.id){b=b.parentNode}switch(b){case e.$n("close"):case e.$n("max"):case e.$n("min"):case e.$n("exp"):return true}return false},_aftermove:function(c,b){c.control.zsync();var d=c.control;zk(d).redoCSS(-1,{fixFontIcon:true})},_startsizing:zul.wnd.Window._startsizing,_ghostsizing:zul.wnd.Window._ghostsizing,_endghostsizing:zul.wnd.Window._endghostsizing,_insizer:zul.wnd.Window._insizer,_ignoresizing:function(f,i,b){var d=f.node,g=f.control;if(g._maximized||!g._open){return true}var e=zk(d).revisedOffset(),c=g.$class._insizer(d,e,i[0],i[1]);if(c){g._hideShadow();f.z_dir=c;f.z_box={top:e[1],left:e[0],height:d.offsetHeight,width:d.offsetWidth,minHeight:zk.parseInt(g.getMinheight()),minWidth:zk.parseInt(g.getMinwidth())};f.z_orgzi=d.style.zIndex;return false}return true},_snapsizing:zul.wnd.Window._snapsizing,_aftersizing:zul.wnd.Window._aftersizing,_drawsizing:zul.wnd.Window._drawsizing});zul.wnd.PanelSkipper=zk.$extends(zk.Skipper,{$init:function(b){this._p=b},skip:function(d,c){var b;if(b=jq(c||(d.uuid+"-body"),zk)[0]){b.parentNode.removeChild(b);return b}},restore:function(){this.$supers("restore",arguments);this._p.zsync()}});zul.wnd.PanelRenderer={isFrameRequired:function(b){return true}}})();