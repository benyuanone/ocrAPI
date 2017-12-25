


zul.layout.Absolutechildren = zk.$extends(zul.Widget, {
	_x: 0,
	_y: 0,
	$define: {
		
		
		x: function () {
			if (this.desktop) {
				this._rePositionX();
			}
		},
		
		
		y: function () {
			if (this.desktop) {
				this._rePositionY();
			}
		}
	},
	_rePositionBoth: function() {
		this._rePositionX();
		this._rePositionY();
	},
	_rePositionX: function() {
		jq(this.$n()).css('left', this._x);
	},
	_rePositionY: function() {
		jq(this.$n()).css('top', this._y);
	},
	bind_: function () {
		this.$supers(zul.layout.Absolutechildren, 'bind_', arguments);
		this._rePositionBoth();
	}
});
