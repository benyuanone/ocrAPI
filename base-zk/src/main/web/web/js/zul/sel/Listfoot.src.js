

zul.sel.Listfoot = zk.$extends(zul.Widget, {
	
	getListbox: function () {
		return this.parent;
	},
	
	setVflex: function (v) { 
		v = false;
		this.$super(zul.sel.Listfoot, 'setVflex', v);
	},
	
	setHflex: function (v) { 
		v = false;
		this.$super(zul.sel.Listfoot, 'setHflex', v);
	},
	deferRedrawHTML_: function (out) {
		out.push('<tr', this.domAttrs_({domClass:1}), ' class="z-renderdefer"></tr>');
	}
});
