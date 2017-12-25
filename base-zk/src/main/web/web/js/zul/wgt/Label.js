

zul.wgt.Label = zk.$extends(zul.Widget, {
	_value: '',
	_maxlength: 0,
	$define: {
		value: _zkf = function () {
			var n = this.$n();
			if (n) n.innerHTML = this.getEncodedText();
		},
		multiline: _zkf,
		pre: _zkf,
		maxlength: _zkf
	},
	getEncodedText: function () {
		return zUtl.encodeXML(this._value, {multiline:this._multiline,pre:this._pre, maxlength: this._maxlength});
	},
	getIconClass:function(){
		return this.iconSclass;
	},
	getMarginSize_: function (attr) {
		var o = this.$supers('getMarginSize_', arguments);
		if (attr == 'h') {
			var n = this.$n(),
				oh = zk(n).offsetHeight();
			return o + oh - n.offsetHeight;
		}
		return o;
	}
});
