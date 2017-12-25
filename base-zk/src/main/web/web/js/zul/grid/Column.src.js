

zul.grid.Column = zk.$extends(zul.mesh.SortWidget, {
	
	getGrid: function () {
		return this.parent ? this.parent.parent : null;
	},

	$init: function () {
		this.$supers('$init', arguments);
		this.listen({onGroup: this}, -1000);
	},
	
	getMeshBody: function () {
		var grid = this.getGrid();
		return grid ? grid.rows : null;  
	},
	checkClientSort_: function (ascending) {
		var body;
		return !(!(body = this.getMeshBody()) || body.hasGroup()) &&
			this.$supers('checkClientSort_', arguments);
	},
	
	group: function (ascending, evt) {
		var dir = this.getSortDirection();
		if (ascending) {
			if ('ascending' == dir) return false;
		} else {
			if ('descending' == dir) return false;
		}

		var sorter = ascending ? this._sortAscending: this._sortDescending;
		if (sorter == 'fromServer')
			return false;
		else if (sorter == 'none') {
			evt.stop();
			return false;
		}
		
		var mesh = this.getMeshWidget();
		if (!mesh || mesh.isModel() || !zk.feature.pe || !zk.isLoaded('zkex.grid'))
			return false;
			
			
		var	body = this.getMeshBody();
		if (!body) return false;
		evt.stop();
		
		var desktop = body.desktop,
			node = body.$n();
		try {
			body.unbind();
			if (body.hasGroup()) {
				for (var gs = body.getGroups(), len = gs.length; --len >= 0;) 
					body.removeChild(gs[len]);
			}
			
			var d = [], col = this.getChildIndex();
			for (var i = 0, z = 0, it = mesh.getBodyWidgetIterator(), w; (w = it.next()); z++) 
				for (var k = 0, cell = w.firstChild; cell; cell = cell.nextSibling, k++) 
					if (k == col) {
						d[i++] = {
							wgt: cell,
							index: z
						};
					}
			
			var dsc = dir == 'ascending' ? -1 : 1,
				fn = this.sorting,
				isNumber = sorter == 'client(number)';
			d.sort(function(a, b) {
				var v = fn(a.wgt, b.wgt, isNumber) * dsc;
				if (v == 0) {
					v = (a.index < b.index ? -1 : 1);
				}
				return v;
			});
			
			
			for (;body.firstChild;)
				body.removeChild(body.firstChild);
			
			for (var previous, row, index = this.getChildIndex(), i = 0, k = d.length; i < k; i++) {
				row = d[i];
				if (!previous || fn(previous.wgt, row.wgt, isNumber) != 0) {
					
					var group, cell = row.wgt.parent.getChildAt(index);
					if (cell && cell.$instanceof(zul.wgt.Label)) {
						group = new zkex.grid.Group();
						group.appendChild(new zul.wgt.Label({
							value: cell.getValue()
						}));
					} else {
						var cc = cell.firstChild;
						if (cc && cc.$instanceof(zul.wgt.Label)) {
							group = new zkex.grid.Group();
							group.appendChild(new zul.wgt.Label({
								value: cc.getValue()
							}));
						} else {
							group = new zkex.grid.Group();
							group.appendChild(new zul.wgt.Label({
								value: msgzul.GRID_OTHER
							}));
						}
					}
					body.appendChild(group);
				}
				body.appendChild(row.wgt.parent);
				previous = row;
			}
			this._fixDirection(ascending);
		} finally {
			body.replaceHTML(node, desktop);
		}
		return true;
	},
	setLabel: function (label) {
		this.$supers('setLabel', arguments);
		if (this.parent)
			this.parent._syncColMenu();
	},
	setVisible: function (visible) {
		if (this.isVisible() != visible) {
			this.$supers('setVisible', arguments);
			if (this.parent)
				this.parent._syncColMenu();
		}
	},
	
	onGroup: function (evt) {
		var dir = this.getSortDirection();
		if ('ascending' == dir)
			this.group(false, evt);
		else if ('descending' == dir)
			this.group(true, evt);
		else if (!this.group(true, evt))
			this.group(false, evt);
	},
	bind_: function () {
		this.$supers(zul.grid.Column, 'bind_', arguments);
		var n = this.$n();
		this.domListen_(n, 'onMouseOver')
			.domListen_(n, 'onMouseOut');
		var btn = this.$n('btn');
		if (btn)
			this.domListen_(btn, 'onClick', '_doMenuClick');
	},
	unbind_: function () {
		var n = this.$n();
		this.domUnlisten_(n, 'onMouseOver')
			.domUnlisten_(n, 'onMouseOut');
		var btn = this.$n('btn');
		if (btn)
			this.domUnlisten_(btn, 'onClick', '_doMenuClick');
		this.$supers(zul.grid.Column, 'unbind_', arguments);
	},
	_doMouseOver: function(evt) {
		if (this.isSortable_() ||
				(this.parent._menupopup && this.parent._menupopup != 'none'))
			jq(this.$n()).addClass(this.$s('hover'));
	},
	_doMouseOut: function (evt) {
		if (this.isSortable_() ||
				(this.parent._menupopup && this.parent._menupopup != 'none')) {
			var $n = jq(this.$n());
			if (!$n.hasClass(this.$s('visited')))
				$n.removeClass(this.$s('hover'));
		}
	}
});
