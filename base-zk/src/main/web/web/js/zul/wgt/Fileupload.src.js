

zul.wgt.Fileupload = zk.$extends(zul.wgt.Button, {
	getZclass: function () { 
		return this._zclass == null ? 'z-button' : this._zclass;
	}
});
