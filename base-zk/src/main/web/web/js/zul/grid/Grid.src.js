
(function () {
	
	function _fixForEmpty(wgt) {
		if (wgt.desktop) {
			var empty = wgt.$n('empty'),
				colspan = 0;
			if (wgt.rows && wgt.rows.nChildren) {
				empty.style.display = 'none';
			} else {
				if (wgt.columns) {
					for (var w = wgt.columns.firstChild; w; w = w.nextSibling)
						if (w.isVisible())
							colspan++;
				}
				empty.colSpan = colspan || 1;
				
				empty.style.display = 'table-cell';
			}
		}
		wgt._shallFixEmpty = false;
	}
var Grid =

zul.grid.Grid = zk.$extends(zul.mesh.MeshWidget, {
	_scrollbar: null,
	$define: {
		
		
		emptyMessage: function(msg) {
			if(this.desktop)
				jq(this.$n('empty')).html(msg);
		}
	},
		
	getCell: function (row, col) {
		var rows;
		if (!(rows = this.rows))
			return null;
		
		if (rows.nChildren <= row)
			return null;
		
		var row = rows.getChildAt(row);
		return row.nChildren <= col ? null: row.getChildAt(col);
	},
	
	getOddRowSclass: function () {
		return this._scOddRow == null ? this.$s('odd') : this._scOddRow;
	},
	
	setOddRowSclass: function (scls) {
		if (!scls) scls = null;
		if (this._scOddRow != scls) {
			this._scOddRow = scls;
			var n = this.$n();
			if (n && this.rows)
				this.rows.stripe();
		}
		return this;
	},
	rerender: function () {
		this.$supers(Grid, 'rerender', arguments);
		if (this.rows)
			this.rows._syncStripe();
		return this;
	},
	insertBefore: function (child, sibling, ignoreDom) {
		if (this.$super('insertBefore', child, sibling, !this.z_rod)) {
			this._fixOnAdd(child, ignoreDom, ignoreDom);
			return true;
		}
	},
	appendChild: function (child, ignoreDom) {
		if (this.$super('appendChild', child, !this.z_rod)) {
			if (!this.insertingBefore_)
				this._fixOnAdd(child, ignoreDom, ignoreDom);
			return true;
		}
	},
	_fixOnAdd: function (child, ignoreDom, _noSync) {
		if (child.$instanceof(zul.grid.Rows)) {
			this.rows = child;
			this._syncEmpty();
		} else if (child.$instanceof(zul.grid.Columns)) {
			this.columns = child;
			this._syncEmpty();
		} else if (child.$instanceof(zul.grid.Foot)) 
			this.foot = child;
		else if (child.$instanceof(zul.mesh.Paging)) 
			this.paging = child;
		else if (child.$instanceof(zul.mesh.Frozen)) 
			this.frozen = child;

		if (!ignoreDom)
			this.rerender();
		if (!_noSync) 
			this._syncSize();  
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);

		var isRows;
		if (child == this.rows) {
			this.rows = null;
			isRows = true;
			this._syncEmpty();
		} else if (child == this.columns) {
			this.columns = null;
			this._syncEmpty();
		} else if (child == this.foot) 
			this.foot = null;
		else if (child == this.paging) 
			this.paging = null;
		else if (child == this.frozen) {
			this.frozen = null;
			this.destroyBar_();
		}
		if (!isRows && !this.childReplacing_) 
			this._syncSize();
	},
	
	redrawEmpty_: function (out) {
		out.push('<tbody class="', this.$s('emptybody'), '"><tr><td id="'
				, this.uuid, '-empty" style="display:none">',
				this._emptyMessage ,'</td></tr></tbody>');
	},
	bind_: function (desktop, skipper, after) {
		this.$supers(Grid, 'bind_', arguments);
		var w = this;
		after.push(function() {
			_fixForEmpty(w);
		});
	},
	unbind_: function () {
		this.destroyBar_();
		this.$supers(Grid, 'unbind_', arguments);
	},
	onSize: function () {
		this.$supers(Grid, 'onSize', arguments);
		var self = this,
			canInitScrollbar = this.desktop && !this._nativebar;
		if (!this._scrollbar && canInitScrollbar)
			this._scrollbar = zul.mesh.Scrollbar.init(this); 
		setTimeout(function () {
			if (canInitScrollbar) {
				self.refreshBar_();
			}
		}, 200);
	},
	destroyBar_: function () {
		var bar = this._scrollbar;
		if (bar) {
			bar.destroy();
			bar = this._scrollbar = null;
		}
	},
	onResponse: function (ctl, opts) {
		if (this.desktop) {
			if (this._shallFixEmpty) 
				_fixForEmpty(this);
		}
		this.$supers(Grid, 'onResponse', arguments);
	},
	
	_syncEmpty: function () {
		this._shallFixEmpty = true;
	},
	onChildAdded_: function(child) {
		this.$supers(Grid, 'onChildAdded_', arguments);
		if (this.childReplacing_) 
			this._fixOnAdd(child, true); 
		
	},
	insertChildHTML_: function (child, before, desktop) {
		if (child.$instanceof(zul.grid.Rows)) {
			this.rows = child;
			var fakerows = this.$n('rows');
			if (fakerows) {
				jq(fakerows).replaceWith(child.redrawHTML_());
				child.bind(desktop);
				this.ebodyrows = child.$n();
				return;
			} else {
				var tpad = this.$n('tpad');
				if (tpad) {
					jq(tpad).after(child.redrawHTML_());
					child.bind(desktop);
					this.ebodyrows = child.$n();
					return;
				} else if (this.ebodytbl) {
					jq(this.ebodytbl).append(child.redrawHTML_());
					child.bind(desktop);
					this.ebodyrows = child.$n();
					return;
				}
			}
		}

		this.rerender();
	},
	
	getHeadWidgetClass: function () {
		return zul.grid.Columns;
	},
	
	getBodyWidgetIterator: function (opts) {
		return new zul.grid.RowIter(this, opts);
	},
	
	hasGroup: function () {
		return this.rows && this.rows.hasGroup();
	}
});
})();


zul.grid.RowIter = zk.$extends(zk.Object, {
	
	$init: function (grid, opts) {
		this.grid = grid;
		this.opts = opts;
	},
	_init: function () {
		if (!this._isInit) {
			this._isInit = true;
			var p = this.grid.rows ? this.grid.rows.firstChild: null;
			if (this.opts && this.opts.skipHidden)
				for (; p && !p.isVisible(); p = p.nextSibling) {}
			this.p = p;
		}
	},
	 
	hasNext: function () {
		this._init();
		return this.p;
	},
	
	next: function () {
		this._init();
		var p = this.p,
			q = p ? p.nextSibling : null;
		if (this.opts && this.opts.skipHidden)
			for (; q && !q.isVisible(); q = q.nextSibling) {}
		if (p) 
			this.p = q;
		return p;
	}
});