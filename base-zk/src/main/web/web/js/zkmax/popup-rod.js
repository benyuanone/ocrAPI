(function(){var a={},b=zul.wgt.Popup;function c(d){if(d.z_rod&&(!d.parent||!d.parent.z_rod)){d._rodopen=true;zkmax.rodRender(d)}}zk.override(b.molds,a.molds={},{"default":function(d){if(this._rodopen){delete this._rodopen}else{if(!this.isOpen()&&zkmax.rod(this)){this.z_rod=true;d.push('<div id="',this.uuid,'" style="display:none"></div>');return}}a.molds["default"].apply(this,arguments)}});zk.override(b.prototype,a,{open:function(){c(this);a.open.apply(this,arguments)},close:function(){c(this);a.close.apply(this,arguments)},forcerender:function(){a.forcerender.apply(this,arguments);c(this)}})})();