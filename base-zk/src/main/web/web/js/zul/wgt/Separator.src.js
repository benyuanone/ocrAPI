
(function () {

	var _shallFixPercent = zk.gecko ? function (wgt) {
			var s;
			return (s = wgt._spacing) && s.endsWith('%');
		}: zk.$void;


zul.wgt.Separator = zk.$extends(zul.Widget, {
	_orient: 'horizontal',

	$define: { 
		
		
		orient: function () {
			this.updateDomClass_();
		},
		
		
		bar: function () {
			this.updateDomClass_();
		},
		
		
		spacing: function () {
			this.updateDomStyle_();		
		}
	},

	
	isVertical: function () {
		return this._orient == 'vertical';
	},

	
	bind_: function () {
		this.$supers(zul.wgt.Separator, 'bind_', arguments);
	},
	getZclass: function () {
		return 'z-separator';
	},
	domClass_: function (no) {
		var sc = this.$supers('domClass_', arguments),
			bar = this.isBar();
		if (!no || !no.zclass) {
			sc += ' ' + this.$s((this.isVertical() ? 'vertical' + (bar ? '-bar' : '') :
				'horizontal' + (bar ? '-bar' : '')));
		}
		return sc;
	},
	domStyle_: function () {
		var s = this.$supers('domStyle_', arguments);
		if (!_shallFixPercent(this))
			return s;

		
		var space = this._spacing,
			v = zk.parseInt(space.substring(0, space.length - 1).trim());
		if (v <= 0) return s;
		v = v >= 2 ? (v / 2) + '%' : '1%';

		return 'margin:' + (this.isVertical() ? '0 ' + v : v + ' 0')
			+ ';' + s;
	},
	getWidth: function () {
		var wd = this.$supers('getWidth', arguments);
		return !this.isVertical() || (wd != null && wd.length > 0)
			|| _shallFixPercent(this) ? wd : this._spacing;
		
	},
	getHeight: function () {
		var hgh = this.$supers('getHeight', arguments);
		return this.isVertical() || (hgh != null && hgh.length > 0)
			|| _shallFixPercent(this) ? hgh : this._spacing;
	}
});

})();