

zul.layout.Anchorchildren = zk.$extends(zul.Widget, {
	_anchor: null,
	$define: {
		
		
		anchor: function () {
			if (this.desktop)
				this.onSize();
		}
	},
	bind_: function () {
		this.$supers(zul.layout.Anchorchildren, 'bind_', arguments);
		zWatch.listen({onSize: this});
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this});
		this.$supers(zul.layout.Anchorchildren, 'unbind_', arguments);
	},
	onSize: function () {
		
		var n = this.$n(),
			parentn = this.parent.$n(),
			parentwidth = jq(parentn).width(),
			parentheight = jq(parentn).height(),
			arr = this._anchor ? this._anchor.split(' ',2) : [],
			anchorWidth = arr[0],
			anchorHeight = arr[1];

		if (anchorWidth) {
			if (anchorWidth.indexOf('%') > 0) {
				n.style.width = anchorWidth;
			} else {
				n.style.width = jq.px0(parentwidth + zk.parseInt(anchorWidth));
			}
		}

		if (anchorHeight) {
			if (anchorHeight.indexOf('%') > 0) {
				n.style.height = anchorHeight;
			} else {
				n.style.height = jq.px0(parentheight + zk.parseInt(anchorHeight));
			}
		}
	}
});