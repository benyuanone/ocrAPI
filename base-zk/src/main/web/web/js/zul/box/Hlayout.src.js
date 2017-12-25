

zul.box.Hlayout = zk.$extends(zul.box.Layout, {
	_valign: 'top',
	$define: { 
		
		
		valign: function () {
			 this.updateDomClass_();
		}
	},
	isVertical_: function () {
		return false;
	},
	
	
	
	domClass_: function () {
		var clsnm = this.$supers('domClass_', arguments),
			v;
		if ((v = this._valign) == 'middle')
			clsnm += ' z-valign-middle';
		else if (v == 'bottom')
			clsnm += ' z-valign-bottom';
		return clsnm;
	}
});