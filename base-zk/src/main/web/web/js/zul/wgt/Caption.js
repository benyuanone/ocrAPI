zul.wgt.Caption=zk.$extends(zul.LabelImageWidget,{domDependent_:true,rerender:function(){var a=this.parent;if(a){a.clearCache()}this.$supers("rerender",arguments)},domContent_:function(){var c=this.getLabel(),a=this.getImage(),d=this.parent?this.parent._title:"",b=this.domIcon_();if(d){c=c?d+" - "+c:d}c=zUtl.encodeXML(c);if(!a&&!b){return c}if(!a){a=b}else{a='<img id="'+this.uuid+'-img" src="'+a+'" class="'+this.$s("image")+'" />'+(b?" "+b:"")}return c?a+" "+c:a},updateDomContent_:function(){var d=this.domContent_(),a=this.$n("cave"),c=this.nChildren,e=jq(a).contents().filter(function(){return !(this.nodeType==3&&!this.nodeValue.trim().length)}).length,b=0;if(a){jq(a).contents().filter(function(){return(c+b++)<e}).remove();this.clearCache();jq(a).prepend(d?d:"&nbsp;")}},domClass_:function(c){var b=this.$supers("domClass_",arguments),a=this.parent;if(!a.$instanceof(zul.wgt.Groupbox)){return b}return b+(a._closable?"":" "+this.$s("readonly"))},doClick_:function(){if(this.parent.$instanceof(zul.wgt.Groupbox)){this.parent.setOpen(!this.parent.isOpen())}this.$supers("doClick_",arguments)},_getBlank:function(){return"&nbsp;"},_isCollapsibleVisible:function(){var a=this.parent;return a.isCollapsible&&a.isCollapsible()},_isCloseVisible:function(){var a=this.parent;return a.isClosable&&a.isClosable()&&!a.$instanceof(zul.wgt.Groupbox)},_isMinimizeVisible:function(){var a=this.parent;return a.isMinimizable&&a.isMinimizable()},_isMaximizeVisible:function(){var a=this.parent;return a.isMaximizable&&a.isMaximizable()},beforeMinFlex_:function(a){if(a=="w"){this.$n().width=""}},setFlexSizeW_:function(h,b,e,c){if(c){if(this._isCloseVisible()){var g=this.parent.$n("close");e+=g.offsetWidth+zk(g).marginWidth()}if(this._isMaximizeVisible()){var a=this.parent.$n("max");e+=a.offsetWidth+zk(a).marginWidth()}if(this._isMinimizeVisible()){var d=this.parent.$n("min");e+=d.offsetWidth+zk(d).marginWidth()}if(this._isCollapsibleVisible()){var f=this.parent.$n("exp");e+=f.offsetWidth+zk(f).marginWidth()}}this.$supers("setFlexSizeW_",arguments)},getImageNode:function(){if(!this._eimg&&this._image){var a=this.$n("img");if(a){this._eimg=a}}return this._eimg}});