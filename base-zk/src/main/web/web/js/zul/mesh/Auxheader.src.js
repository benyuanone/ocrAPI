

zul.mesh.Auxheader = zk.$extends(zul.mesh.HeaderWidget, {
	_colspan: 1,
	_rowspan: 1,

	$define: {
		
		
		colspan: function (v) {
			var n = this.$n();
			if (n) {
				n.colSpan = v;
				if (zk.ie < 11) this.rerender(); 
			}
		},
		
		
		rowspan: function (v) {
			var n = this.$n();
			if (n) {
				n.rowSpan = v;
				if (zk.ie < 11) this.rerender(); 
			}
		}
	},
	
	domAttrs_: function () {
		var s = this.$supers('domAttrs_', arguments), v;
		if ((v = this._colspan) != 1)
			s += ' colspan="' + v + '"';
		if ((v = this._rowspan) != 1)
			s += ' rowspan="' + v + '"';
		return s;
	}
});