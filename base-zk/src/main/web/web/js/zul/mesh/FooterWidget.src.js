

zul.mesh.FooterWidget = zk.$extends(zul.LabelImageWidget, {
	_span: 1,
	
	$define: {
		
    	
		span: function (v) {
			var n = this.$n();
			if (n) n.colSpan = v;
		},
		
    	
		align: function (v) {
			var n = this.$n();
			if (n) n.align = v;
		},
		
		
		valign: function (v) {
			var n = this.$n();
			if (n) n.vAlign = v;
		}
	},
	
	getMeshWidget: function () {
		return this.parent ? this.parent.parent : null;
	},
	
	getHeaderWidget: function () {
		var meshWidget = this.getMeshWidget();
		if (meshWidget) {
			var cs = meshWidget.getHeadWidget();
			if (cs)
				return cs.getChildAt(this.getChildIndex());
		}
		return null;
	},
	
	domStyle_: function (no) {
		var style = '',
			header = this.getHeaderWidget();
		if (this._align)
			style += 'text-align:' + this._align + ';';
		else if (header && header._align)
			style += 'text-align:' + header._align + ';';
		if (this._valign)
			style += 'vertical-align:' + this._align + ';';
		else if (header && header._valign)
			style += 'vertical-align:' + header._valign + ';';
		if (header && !header.isVisible()) 
			style += 'display: none;';
		
		return style + this.$super('domStyle_', no);
	},
	domAttrs_: function () {
		return this.$supers('domAttrs_', arguments)
			+ (this._span > 1 ? ' colspan="' + this._span + '"' : '');
	},
	deferRedrawHTML_: function (out) {
		out.push('<td', this.domAttrs_({domClass:1}), ' class="z-renderdefer"></td>');
	}
});