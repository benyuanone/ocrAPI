

zul.wnd.Panelchildren = zk.$extends(zul.ContainerWidget, {
	
	setHeight: zk.$void,      
	
	setWidth: zk.$void,       

	
	domClass_: function (no) {
		var scls = this.$supers('domClass_', arguments);
		if (!no || !no.zclass) {
			var zcls = this.getZclass();
			var added = !this.parent.getTitle() && !this.parent.caption ?
				zcls + '-noheader' : '';				
			if (added) scls += (scls ? ' ': '') + added;
			added = this.parent._bordered() ? '' : zcls + '-noborder';
			if (added) scls += (scls ? ' ': '') + added;
		}
		return scls;
	},
	updateDomStyle_: function () {
		this.$supers('updateDomStyle_', arguments);
		if (this.desktop)
			zUtl.fireSized(this.parent);
	}
});