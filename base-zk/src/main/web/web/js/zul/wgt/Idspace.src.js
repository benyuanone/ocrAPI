

zul.wgt.Idspace = zk.$extends(zul.wgt.Div, {
	$init: function () {
		this._fellows = {};
		this.$supers('$init', arguments);
	}
});
