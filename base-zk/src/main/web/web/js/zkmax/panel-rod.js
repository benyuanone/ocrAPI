(function(){var a={};function b(c){if(c=c.panelchildren){zkmax.rodRender(c)}}zk.override(zul.wnd.Panelchildren.molds,a.molds={},{"default":function(c){if(this._rodopen){delete this._rodopen}else{var d;if((d=this.parent)&&!d._open&&zkmax.rod(this)){this.z_rod=true;c.push('<div id="',this.uuid,'" style="display:none"></div>');return}}a.molds["default"].apply(this,arguments)}});zk.override(zul.wnd.Panel.prototype,a,{setOpen:function(c){if(c){b(this)}a.setOpen.apply(this,arguments)},forcerender:function(){a.forcerender.apply(this,arguments);b(this)}})})();