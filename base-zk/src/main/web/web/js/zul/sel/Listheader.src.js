

zul.sel.Listheader = zk.$extends(zul.mesh.SortWidget, {
	
	getListbox: _zkf = function () {
		return this.parent ? this.parent.parent : null;
	},

	$init: function () {
		this.$supers('$init', arguments);
		this.listen({onGroup: this}, -1000);
	},
	
	getMeshBody: _zkf,
	checkClientSort_: function (ascending) {
		var body;
		return !(!(body = this.getMeshBody()) || body.hasGroup()) && 
			this.$supers('checkClientSort_', arguments);
	},
	$define: {
		
		
		maxlength: [function (v) {
			return !v || v < 0 ? 0 : v; 
		}, function () {
			if (this.desktop) {
				this.rerender();
				this.updateCells_();
			}
		}]
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
		if (!mesh || mesh.isModel() || !zk.feature.pe || !zk.isLoaded('zkex.sel')) return false;
			
		
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
			
			
			for (var item = body.firstItem; item; item = body.nextItem(item))
				body.removeChild(item);
			
			for (var previous, row, index = this.getChildIndex(), i = 0, k = d.length; i < k; i++) {
				row = d[i];
				if (!previous || fn(previous.wgt, row.wgt, isNumber) != 0) {
					
					var group, cell = row.wgt.parent.getChildAt(index);
					if (cell) {
						if (cell.getLabel()) {
							group = new zkex.sel.Listgroup({
								label: cell.getLabel()
							});
						} else {
							var cc = cell.firstChild;
							if (cc && cc.$instanceof(zul.wgt.Label)) {
								group = new zkex.sel.Listgroup({
									label: cc.getValue()
								});
							} else {
								group = new zkex.sel.Listgroup({
									label: msgzul.GRID_OTHER
								});
							}
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
	
	onGroup: function (evt) {
		var dir = this.getSortDirection();
		if ('ascending' == dir)
			this.group(false, evt);
		else if ('descending' == dir)
			this.group(true, evt);
		else if (!this.group(true, evt))
			this.group(false, evt);
	},
	
	updateCells_: function () {
		var box = this.getListbox();
		if (box == null || box.getMold() == 'select')
			return;

		var jcol = this.getChildIndex(), w;
		for (var it = box.getBodyWidgetIterator(); (w = it.next());)
			if (jcol < w.nChildren)
				w.getChildAt(jcol).rerender();

		w = box.listfoot;
		if (w && jcol < w.nChildren)
			w.getChildAt(jcol).rerender();
	},
	
	bind_: function () {
		this.$supers(zul.sel.Listheader, 'bind_', arguments);
		var cm = this.$n('cm'),
			n = this.$n();
		if (cm) {
			var box = this.getListbox();
			if (box) box._headercm = cm;
			this.domListen_(cm, 'onClick', '_doClick');
		}
		if (n)
			this.domListen_(n, 'onMouseOver', '_doMouseOver')
				.domListen_(n, 'onMouseOut', '_doMouseOut');
		var btn = this.$n('btn');
		if (btn)
			this.domListen_(btn, 'onClick', '_doMenuClick');
	},
	unbind_: function () {
		var cm = this.$n('cm'),
			n = this.$n();
		if (cm) {
			var box = this.getListbox();
			if (box) box._headercm = null;
			this._checked = null;
			this.domUnlisten_(cm, 'onClick', '_doClick');
		}
		if (n)
			this.domUnlisten_(n, 'onMouseOver', '_doMouseOver')
				.domUnlisten_(n, 'onMouseOut', '_doMouseOut');
		var btn = this.$n('btn');
		if (btn)
			this.domUnlisten_(btn, 'onClick', '_doMenuClick');
		this.$supers(zul.sel.Listheader, 'unbind_', arguments);
	},
	_doMouseOver: function (evt) {
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
	},
	_doClick: function (evt) {
		this._checked = !this._checked;
		var box = this.getListbox(),
			cm = this.$n('cm'),
			$n = jq(cm);
		if (this._checked) {
			$n.addClass(this.$s('checked'));
			
			
			box.selectAll(true, evt);
		} else {
			$n.removeClass(this.$s('checked'));
			box._select(null, evt);
		}
		box.fire('onCheckSelectAll', this._checked);
	},
	
	doClick_: function (evt) {
		var box = this.getListbox(),
			cm = this.$n('cm');
		if (box && box._checkmark) {
			var n = evt.domTarget;
			if (n == cm || n.parentNode == cm) 
				return; 
		}
		this.$supers('doClick_', arguments);
	},
	
	domContent_: function () {
		var s = this.$supers('domContent_', arguments),
			box = this.getListbox();
		if (box != null && this.parent.firstChild == this 
				&& box._checkmark && box._multiple && !box._listbox$noSelectAll) 
			s = '<span id="' + this.uuid + '-cm" class="' + this.$s('checkable') + 
				'"><i class="' + this.$s('icon') + ' z-icon-check"></i></span>'
				+ (s ? '&nbsp;' + s:'');
		return s;
	},
	
	domLabel_: function () {
		return zUtl.encodeXML(this.getLabel(), {maxlength: this._maxlength});
	}
});
