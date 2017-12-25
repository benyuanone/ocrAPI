
(function () {
	var _isPE = (function () {
		var _isPE_ = zk.feature.pe;
		return function () {
				return _isPE_ && zk.isLoaded('zkex.grid')
			};
	})();
	function _syncFrozen(wgt) {
		var grid = wgt.getGrid(),
			frozen;
		if (grid && grid._nativebar && (frozen = grid.frozen))
			frozen._syncFrozen();
	}
	
var Rows =

zul.grid.Rows = zk.$extends(zul.Widget, {
	_visibleItemCount: 0,
	$init: function () {
		this.$supers('$init', arguments);
		this._groupsInfo = [];
	},
	$define: {
		
		visibleItemCount: null
	},
	
	getGrid: function () {
		return this.parent;
	},
	
	getGroupCount: function () {
		return this._groupsInfo.length;
	},
	
	getGroups: function () {
		return this._groupsInfo.$clone();
	},
	
	hasGroup: function () {
		return this._groupsInfo.length;
	},
	bind_: function (desktop, skipper, after) {
		this.$supers(Rows, 'bind_', arguments);
		var grid = this.getGrid();
		if (grid) 
			grid.ebodyrows = this.$n();
		zWatch.listen({onResponse: this});
		var w = this;
		after.push(function () {
			w.stripe();
			_syncFrozen(w);
		});
	},
	unbind_: function () {
		zWatch.unlisten({onResponse: this});
		this.$supers(Rows, 'unbind_', arguments);
	},
	onResponse: function () {
		if (this.desktop){
			if (this._shallStripe) { 
				this.stripe();
				this.getGrid().onSize();
			}
		}
	},
	replaceChildHTML_: function (child, n, desktop, skipper, _trim_) {
		if(child._renderdefer) {
			var scOdd = this.getGrid().getOddRowSclass(),
				isOdd = jq(n).hasClass(scOdd); 
		
			this.$supers('replaceChildHTML_', arguments);
			if(isOdd) jq(child).addClass(scOdd);
		} else 
			this.$supers('replaceChildHTML_', arguments);
	},
	_syncStripe: function () {
		this._shallStripe = true;
	},
	
	stripe: function () {
		var grid = this.getGrid(),
			scOdd = grid.getOddRowSclass();
		if (!scOdd) return;
		var n = this.$n();
		if (!n) return; 

		for (var j = 0, w = this.firstChild, even = !(this._offset & 1); w; w = w.nextSibling, ++j) {
			if (w.isVisible() && w.isStripeable_()) {
				
				for (;n.rows[j] && n.rows[j].id != w.uuid;++j);

				jq(n.rows[j])[even ? 'removeClass' : 'addClass'](scOdd);
				w.fire("onStripe");
				even = !even;
			}
		}
		this._shallStripe = false;
	},
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		if (_isPE() && child.$instanceof(zkex.grid.Group))
			this._groupsInfo.push(child);
		
		var g;
		if ((g = this.getGrid())) {
			if (g.fixForRowAdd_) 
				g.fixForRowAdd_();
			g._syncEmpty();
		}
		this._syncStripe();
		
		if (this.desktop)
			_syncFrozen(this);
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);
		if (_isPE() && child.$instanceof(zkex.grid.Group))
			this._groupsInfo.$remove(child);
		if (!this.childReplacing_)
			this._syncStripe();
		
		var g = this.getGrid();
		if (g) g._syncEmpty();
	},
	deferRedrawHTML_: function (out) {
		out.push('<tbody', this.domAttrs_({domClass:1}), ' class="z-renderdefer"></tbody>');
	}
});
})();