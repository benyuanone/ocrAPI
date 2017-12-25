
(function () {
	var _isPE = (function () {
		var _isPE_ = zk.feature.pe;
		return function () {
				return _isPE_ && zk.isLoaded('zkex.grid')
			};
	})();

zul.grid.Row = zk.$extends(zul.Widget, {
	$define: {
		
		
		align: function (v) {
			var n = this.$n();
			if (n)
				n.align = v;
		},
		
		
		nowrap: function (v) {
			var cells = this.$n();
			if (cells && (cells = cells.cells))
				for (var j = cells.length; j--;)
					cells[j].noWrap = v;
		},
		
		
		valign: function (v) {
			var n = this.$n();
			if (n)
				n.vAlign = v;
		}
	},
	
	getGrid: function () {
		return this.parent ? this.parent.parent : null;
	},
	setVisible: function (visible) {
		if (this.isVisible() != visible) {
			this.$supers('setVisible', arguments);
			if (visible && this.isStripeable_() && this.parent)
				this.parent.stripe();
		}
	},
	
	getSpans: function () {
		return zUtl.intsToString(this._spans);
	},
	
	setSpans: function (spans) {
		if (this.getSpans() != spans) {
			this._spans = zUtl.stringToInts(spans, 1);
			this.rerender();
		}
	},
	_getIndex: function () {
		return this.parent ? this.getChildIndex() : -1;
	},
	
	getGroup: function () {
		
		if (_isPE() && this.parent && this.parent.hasGroup())
			for (var w = this; w; w = w.previousSibling)
				if (w.$instanceof(zkex.grid.Group)) return w;
				
		return null;
	},
	setStyle: function (style) {
		if (this._style != style) {
			if (!zk._rowTime) zk._rowTime = jq.now();
			this._style = style;
			this.rerender();
		}
	},
	rerender: function () {
		if (this.desktop) {
			this.$supers('rerender', arguments);
			if (this.parent)
				this.parent._syncStripe();
		}
	},
	getSclass: function () {
		var sclass = this.$supers('getSclass', arguments);
		if (sclass != null)
			return sclass;
		
		var grid = this.getGrid();
		return grid ? grid.getSclass(): sclass;
	},
	_getChdextr: function (child) {
		return child.$n('chdextr') || child.$n();
	},
	scrollIntoView: function () {
		var bar = this.getGrid()._scrollbar;
		if (bar) {
			bar.syncSize();
			bar.scrollToElement(this.$n());
		} else {
			this.$supers('scrollIntoView', arguments);
		}
	},
	insertChildHTML_: function (child, before, desktop) {
		var childHTML = this.encloseChildHTML_({
				child: child,
				index: child.getChildIndex(),
				zclass: this.getZclass()
			});
		if (before)
			jq(this._getChdextr(before)).before(childHTML);
		else
			jq(this).append(childHTML);
		child.bind(desktop);
	},
	removeChildHTML_: function (child) {
		this.$supers('removeChildHTML_', arguments);
		jq(child.uuid + '-chdextr', zk).remove();
	},
	
	encloseChildHTML_: function (opts) {
		var out = opts.out || [],
			child = opts.child,
			isCell = child.$instanceof(zul.wgt.Cell);
		if (!isCell) {
			out.push('<td id="', child.uuid, '-chdextr"',
				this._childAttrs(child, opts.index), '><div id="', child.uuid,
				'-cell" class="', opts.zclass, '-content">');
		}
		child.redraw(out);
		if (!isCell)
			out.push('</div></td>');
		if (!opts.out)
			return out.join('');
	},
	_childAttrs: function (child, index) {
		var realIndex = index, span = 1;
		if (this._spans) {
			for (var j = 0, k = this._spans.length; j < k; ++j) {
				if (j == index) {
					span = this._spans[j];
					break;
				}
				realIndex += this._spans[j] - 1;
			}
		}
		var visible, hgh, align, valign,
			grid = this.getGrid();
		if (grid) {
			var cols = grid.columns;
			if (cols) {
				if (realIndex < cols.nChildren) {
					var col = cols.getChildAt(realIndex);
					visible = col.isVisible() ? '' : 'display:none;';
					hgh = col.getHeight();
					align = col.getAlign();
					valign = col.getValign();
				}
			}
		}
		var style = this.domStyle_({visible:1, width:1, height:1}),
			isDetail = zk.isLoaded('zkex.grid') && child.$instanceof(zkex.grid.Detail);
		if (isDetail) {
			var wd = child.getWidth();
			if (wd) 
				style += 'width:' + wd + ';';
		}
		if (visible || hgh || align || valign) {
			style += visible;
			if (hgh)
				style += 'height:' + hgh + ';';
			if (align)
				style += 'text-align:' + align + ';';
			if (valign)
				style += 'vertical-align:' + valign + ';';
		}
		var clx = isDetail ? child.$s('outer') : this.$s('inner'),
			attrs = '';
		if (span !== 1)
			attrs += ' colspan="' + span + '"';
		if (this._nowrap)
			attrs += ' nowrap="nowrap"';
		if (style)
			attrs += ' style="' + style + '"'
		return attrs + ' class="' + clx + '"';
	},
	
	isStripeable_: function () {
		return true;
	},
	
	domStyle_: function (no) {
		if ((_isPE() && (this.$instanceof(zkex.grid.Group) || this.$instanceof(zkex.grid.Groupfoot)))
				|| (no && no.visible))
			return this.$supers('domStyle_', arguments);
		
		var style = this.$supers('domStyle_', arguments),
			group = this.getGroup();
		if (this._align)
			style += ' text-align:' + this._align + ';';
		if (this._valign)
			style += ' vertical-align:' + this._valign + ';';
		return group && !group.isOpen() ? style + 'display:none;' : style;
	},
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		if (child.$instanceof(zul.grid.Detail))
			this.detail = child;
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);
		if (child == this.detail)
			this.detail = null;
	},
	doFocus_: function (evt) {
		this.$supers('doFocus_', arguments);
		
		var grid = this.getGrid(),
			frozen = grid ? grid.frozen : null,
			tbody = grid && grid.rows ? grid.rows.$n() : null,
			td, tds;
		if (frozen && tbody) {
			tds = jq(evt.domTarget).parents('td');
			for (var i = 0, j = tds.length; i < j; i++) {
				td = tds[i];
				if (td.parentNode.parentNode == tbody) {
					grid._moveToHidingFocusCell(td.cellIndex);
					break;
				}
			}
		}
	},
	doMouseOver_: function(evt) {
		if (this._musin)
			return;
		this._musin = true;
		var n = this.$n();
		
		
		if (n && zk.gecko && this._draggable && 
				!jq.nodeName(evt.domTarget, 'input', 'textarea')) {
			jq(n).addClass('z-draggable-over');
		}
		
		this.$supers('doMouseOver_', arguments);
	},
	doMouseOut_: function(evt) {
		var n = this.$n();
		if ((this._musin && jq.isAncestor(n,
				evt.domEvent.relatedTarget || evt.domEvent.toElement))) {
			
			this.parent._musout = this;
			return;
		}
		this._musin = false;
		
		
		if (n && zk.gecko && this._draggable &&
				!jq.nodeName(evt.domTarget, 'input', 'textarea')) {
			jq(n).removeClass('z-draggable-over');
		}
		
		this.$supers('doMouseOut_', arguments);
	},
	domClass_: function () {
		var cls = this.$supers('domClass_', arguments),
			grid = this.getGrid();
		if (grid && jq(this.$n()).hasClass(grid = grid.getOddRowSclass()))
			return cls + ' ' + grid; 
		return cls;
	},
	deferRedrawHTML_: function (out) {
		out.push('<tr', this.domAttrs_({domClass:1}), ' class="z-renderdefer"></tr>');
	}
});
})();