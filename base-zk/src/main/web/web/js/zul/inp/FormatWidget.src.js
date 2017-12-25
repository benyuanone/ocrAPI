

zul.inp.FormatWidget = zk.$extends(zul.inp.InputWidget, {
	$define: { 
		
		
		format: function () {
			var inp = this.getInputNode();
			if (inp)
				inp.value = this.coerceToString_(this._value);
		}
	},
	doFocus_: function (evt) {
		this.$supers('doFocus_', arguments);
		if (this._shortcut)
			this.getInputNode().value = this._shortcut;
	},
	updateChange_: function (clear) {
		var upd = this.$supers('updateChange_', arguments);
		if (clear)
			delete this._shortcut;
		return upd;
	}
});