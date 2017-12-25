

zul.menu.Menuseparator = zk.$extends(zul.Widget, {
	
	isPopup: function () {
		return this.parent && this.parent.$instanceof(zul.menu.Menupopup);
	},
	
	getMenubar: function () {
		for (var p = this.parent; p; p = p.parent)
			if (p.$instanceof(zul.menu.Menubar))
				return p;
		return null;
	},
	doMouseOver_: function () {
		if (zul.menu._nOpen)
			zWatch.fire('onFloatUp', this); 
		this.$supers('doMouseOver_', arguments);
	}
});
