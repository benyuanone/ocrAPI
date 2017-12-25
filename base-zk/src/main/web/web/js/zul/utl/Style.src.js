

zul.utl.Style = zk.$extends(zk.Widget, {
	$define: {
    	
		
		src: function () {
			this._content = null;
			this.rerender();
		},
		
		
		content: function () {
			this._src = null;
			this.rerender();
		},
		
		
		media: function (v) {
			var n = this.$n('real');
			if (n) n.media = v;
		}
	},
	bind_: zk.ie8_ ? function () {
		this.$supers(zul.utl.Style, 'bind_', arguments);

		
		if (this._src) {
			var self = this;
			setTimeout(function () {
				var n = self.$n('real');
				if (n) n.href = self._src;
			});
		}
	} : function () {
		this.$supers(zul.utl.Style, 'bind_', arguments);
	}
});