zul.wgt.Groupbox=zk.$extends(zul.ContainerWidget,{_open:true,_closable:true,setOpen:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_open",(function(b,a){var c=this.$n();if(c&&this._closable){if(b){jq(c).removeClass(this.$s("collapsed"));zk(this).redoCSS(-1,{fixFontIcon:true})}zk(this.getCaveNode())[b?"slideDown":"slideUp"](this);if(!a){this.fire("onOpen",{open:b})}}})),getOpen:_zkf$=function(){return this._open},isOpen:_zkf$,setClosable:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_closable",(_zkf=function(){this._updDomOuter()})),getClosable:_zkf$=function(){return this._closable},isClosable:_zkf$,setContentStyle:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_contentStyle",(_zkf)),getContentStyle:_zkf$=function(){return this._contentStyle},isContentStyle:_zkf$,setContentSclass:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_contentSclass",(_zkf)),getContentSclass:_zkf$=function(){return this._contentSclass},isContentSclass:_zkf$,setTitle:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_title",(_zkf)),getTitle:_zkf$=function(){return this._title},isTitle:_zkf$,_isDefault:function(){return this._mold=="default"},_updDomOuter:function(){this.rerender(zk.Skipper.nonCaptionSkipper)},_contentAttrs:function(){var a=' class="',b=this._contentSclass;if(b){a+=b+" "}a+=this.$s("content")+'"';b=this._contentStyle;if(this.caption||this.getTitle()){b="border-top:0;"+(b||"")}if(!this._open){b="display:none;"+(b||"")}if(b){a+=' style="'+b+'"'}return a},_redrawCave:function(b,d){b.push('<div id="',this.uuid,'-cave"',this._contentAttrs(),">");if(!d){for(var a=this.firstChild,c=this.caption;a;a=a.nextSibling){if(a!=c){a.redraw(b)}}}b.push("</div>")},setHeight:function(){this.$supers("setHeight",arguments);if(this.desktop){this._fixHgh()}},_fixHgh:function(){var b=this.$n().style.height;if(b&&b!="auto"&&this.isOpen()){var e;if(e=this.$n("cave")){var c=zk(e);e.style.height=(c.revisedHeight(c.vflexHeight(),true)-(this._isDefault()?parseInt(jq(this).css("padding-top")):0))+"px"}}if(this._isDefault()){var d=this.$n("title"),a=this.caption;if(a){a.$n().style.top=jq.px(zk(a.$n("cave")).offsetHeight()/2*-1)}if(d){d.style.top=jq.px(zk(this.$n("title-cnt")).offsetHeight()/2*-1)}}},setFlexSizeH_:function(g,b,a,d){if(d&&(this.caption||this._title)){var e=this.$n(),f;a=this._isDefault()?jq(this.$n("header")).outerHeight():0;for(f=g.firstChild;f;f=f.nextSibling){a+=jq(f).outerHeight()}}this.$supers("setFlexSizeH_",arguments)},onSize:function(){this._fixHgh()},updateDomStyle_:function(){this.$supers("updateDomStyle_",arguments);if(this.desktop){this.onSize()}},focus_:function(c){var b=this.caption;for(var a=this.firstChild;a;a=a.nextSibling){if(a!=b&&a.focus_(c)){return true}}return b&&b.focus_(c)},bind_:function(){this.$supers(zul.wgt.Groupbox,"bind_",arguments);zWatch.listen({onSize:this});var a;if(this.getTitle()&&(a=this.$n("title"))){this.domListen_(a,"onClick","_doTitleClick")}if(zk.ie==8){zk(this).redoCSS()}},unbind_:function(){zWatch.unlisten({onSize:this});var a;if(a=this.$n("title")){this.domUnlisten_(a,"onClick","_doTitleClick")}this.$supers(zul.wgt.Groupbox,"unbind_",arguments)},_doTitleClick:function(){this.setOpen(!this.isOpen());this.$supers("doClick_",arguments)},onChildAdded_:function(a){this.$supers("onChildAdded_",arguments);if(a.$instanceof(zul.wgt.Caption)){this.caption=a;this.rerender()}},onChildRemoved_:function(a){this.$supers("onChildRemoved_",arguments);if(a==this.caption){this.caption=null;this.rerender()}},getChildMinSize_:function(a,b){if(!b.$instanceof(zul.wgt.Caption)){return this.$supers("getChildMinSize_",arguments)}},domClass_:function(){var a=this.$supers("domClass_",arguments);if(!this._isDefault()){if(a){a+=" "}a+=this.$s("3d")}if(!this.caption&&!this.getTitle()){if(a){a+=" "}a+=" "+this.$s("notitle")}if(!this._open&&this._isDefault()){if(a){a+=" "}a+=this.$s("collapsed")}return a},afterAnima_:function(d){if(!d&&this._isDefault()){jq(this.$n()).addClass(this.$s("collapsed"))}this.$supers("afterAnima_",arguments);var b=this.parent;for(var e=b.firstChild;e;e=e.nextSibling){if(e==this){continue}var a=e.getVflex();if(a&&a!="min"){zUtl.fireSized(b);break}}}});