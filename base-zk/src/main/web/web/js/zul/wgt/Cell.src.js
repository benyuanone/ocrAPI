

zul.wgt.Cell = zk.$extends(zul.Widget, {
	_colspan: 1,
	_rowspan: 1,
	_rowType: 0,
	_boxType: 1,
	
	$define: {
		
		
		colspan: function (v) {
			var n = this.$n();
			if (n)
				n.colSpan = v;
		},
		
		
		rowspan: function (v) {
			var n = this.$n();
			if (n)
				n.rowSpan = v;
		},
		
		
		align: function (v) {
			var n = this.$n();
			if (n)
				n.align = v;
		},
		
		
		valign: function (v) {
			var n = this.$n();
			if (n)
				n.valign = v;
		}
	},
	_getParentType: function () {
		var isRow = zk.isLoaded('zul.grid') && this.parent.$instanceof(zul.grid.Row);
		if (!isRow) {
			return zk.isLoaded('zul.box') && this.parent.$instanceof(zul.box.Box) ?
					this._boxType : null;
		}
		return this._rowType;
	},
	_getRowAttrs: function () {
		return this.parent._childAttrs(this, this.getChildIndex());
	},
	_getBoxAttrs: function () {
		return this.parent._childInnerAttrs(this);
	},
	_colHtmlPre: function () {
		var s = '',
			p = this.parent;
		if(zk.isLoaded('zkex.grid') && p.$instanceof(zkex.grid.Group) && this == p.firstChild)
			s += p.domContent_();
		return s;
	},
	domStyle_: function (no) {
		var style = this.$supers('domStyle_', arguments);
		if (this._align)
			style += ' text-align:' + this._align + ';';
		if (this._valign)
			style += ' vertical-align:' + this._valign + ';';
		return style;
	},
	
	domAttrs_: function (no) {
		var s = this.$supers('domAttrs_', arguments), v;	
		if ((v = this._colspan) != 1)
			s += ' colspan="' + v + '"';
		if ((v = this._rowspan) != 1)
			s += ' rowspan="' + v + '"';
			
		var m1, m2 = zUtl.parseMap(s, ' ', '"');		
		switch (this._getParentType()) {
		case this._rowType:
			m1 = zUtl.parseMap(this._getRowAttrs(), ' ', '"');
			break;
		case this._boxType:
			m1 = zUtl.parseMap(this._getBoxAttrs(), ' ', '"');
			break;
		}
		if (m1) {
			
			var s1 = m1.style,
				s2 = m2.style,
				style;
			if (s1 && s2) {
				s1 = zUtl.parseMap(s1.replace(/"/g, '').replace(/:/g, '='), ';');
				s2 = zUtl.parseMap(s2.replace(/"/g, '').replace(/:/g, '='), ';');
				zk.copy(s1, s2);
				style = zUtl.mapToString(s1, ':', ';');
			}
			zk.copy(m1, m2);
			if (style)
				m1.style = '"' + style + '"';
		}
		return ' ' + zUtl.mapToString(m1);
	},
	setVisible: function(visible) {
		this.$supers('setVisible', arguments);
		
		if (zk.ie10_ && visible)
			zk(this.$n()).redoCSS();
			
	},
	deferRedrawHTML_: function (out) {
		out.push('<td', this.domAttrs_({domClass:1}), ' class="z-renderdefer"></td>');
	}
});