

zul.inp.Bandpopup = zk.$extends(zul.Widget, {
	
	afterChildrenMinFlex_: function(orient) {
		if (orient == 'w') {
			var bandbox = this.parent,
				pp = bandbox && bandbox.$n('pp');
			if (pp) {
				
				pp.style.width = jq.px0(this._hflexsz + zk(pp).padBorderWidth());
				zk(pp)._updateProp(['width']);
			}
		}
	}
});
