

zul.fud.Submit = zk.$extends(zul.wgt.Button, {
	getZclass: function () { 
		return this._zclass == null ? 'z-button' : this._zclass;
	},
	submit: function () {
		var f = this.$f('fileupload'),
			self = this;
		function t() {
			if (zul.Upload.isFinish(f)) {
				self.$o().submit();
				clearInterval(self._tmp);
				self._tmp = undefined;
				return true;
			}
		}
		if (t()) return;
		self._tmp = setInterval(t, 800);
		this.setDisabled(true);
		this.nextSibling.setDisabled(true);
		if (zk.ie < 11)
			this.$f('btns').rerender();
	},
	revert: function () {	
		clearInterval(this._tmp);
		this._tmp = undefined;
		this.setDisabled(false);
		this.nextSibling.setDisabled(false);
		if (zk.ie < 11)
			this.$f('btns').rerender();
	}
});
