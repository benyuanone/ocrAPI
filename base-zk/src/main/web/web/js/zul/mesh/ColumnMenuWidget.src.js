
(function () {

zul.mesh.ColumnMenuWidget = zk.$extends(zul.mesh.HeadWidget, {
	_menupopup: 'none',
	_columnshide: true,
	_columnsgroup: true,

	$define: {
		
		
		columnshide: _zkf = function () {
			if (this.desktop)
				this._initColMenu();
		},
		
		
		columnsgroup: _zkf,
		
		
		menupopup: function () {
			if (this._menupopup != 'auto')
				this._mpop = null;
			this.rerender();		
		}
	},
	bind_: function (dt, skipper, after) {
		this.$supers(zul.mesh.ColumnMenuWidget, 'bind_', arguments);
		zWatch.listen({onResponse: this});
		var w = this;
		if (this._menupopup == 'auto') {
			after.push(function() {
				w._initColMenu();
			});
		}
	},
	unbind_: function () {
		zWatch.unlisten({onResponse: this});
		if (this._mpop) {
			this._mpop.parent.removeChild(this._mpop);
			this._shallColMenu = this._mpop = null;
		}
		this.$supers(zul.mesh.ColumnMenuWidget, 'unbind_', arguments);
	},
	onResponse: function () {
		if (this._shallColMenu)
			this.syncColMenu();
	},
	_syncColMenu: function () {
		this._shallColMenu = true;
	},
	_initColMenu: function () {
		if (this._mpop)
			this._mpop.parent.removeChild(this._mpop);
		this._mpop = new zul.mesh.ColumnMenupopup({columns: this});
	},
	
	syncColMenu: function () {
		this._shallColMenu = false;
		if (this._mpop) 
			this._mpop.syncColMenu();
	},
	_onColVisi: function (evt) {
		var item = evt.currentTarget,
			pp = item.parent;
			
		pp.close({sendOnOpen: true});
		var checked = 0;
		for (var w = pp.firstChild; w; w = w.nextSibling) {
			if (w.$instanceof(zul.menu.Menuitem) && w.isChecked())
				checked++;
		}
		if (checked == 0)
			item.setChecked(true);
			
		var col = zk.Widget.$(item._col);
		if (col && col.parent == this) {
			var mesh = this.getMeshWidget();
			if (mesh && mesh.isSizedByContent())
				mesh.clearCachedSize_(); 
			col.setVisible(item.isChecked());
		}
	},
	_onGroup: function (evt) {
		var ungroup;
		if ((ungroup = evt.target.parent._ungroup))
			ungroup.setVisible(true);
		
		this._mref.fire('onGroup', 'ascending' != this._mref.getSortDirection(), {toServer: true});
	},
	_onUngroup: zk.$void,
	_onAsc: function (evt) {
		this._mref.fire('onSort', true); 
	},
	_onDesc: function (evt) {
		this._mref.fire('onSort', false); 
	},
	_onMenuPopup: function (evt) {
		var mref = this._mref;
		if (mref)
			jq(mref.$n()).removeClass(mref.$s('visited')).removeClass(mref.$s('hover'));
		
		this._mref = evt.data.reference; 
	},
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		this._syncColMenu();
		var mesh = this.getMeshWidget();
		if (mesh && mesh._syncEmpty)
			mesh._syncEmpty();
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);
		if (!this.childReplacing_)
			this._syncColMenu();
		var mesh = this.getMeshWidget();
		if (mesh) mesh._syncEmpty();
	},
	getGroupPackage_: zk.$void
});


zul.mesh.ColumnMenupopup = zk.$extends(zul.menu.Menupopup, {
	$define: {
		columns: null
	},
	
	$init: function () {
		this.$supers('$init', arguments);
		this.afterInit(this._init);
	},
	
	getAscitem: function () {
		return this._asc;
	},
	
	getDescitem: function () {
		return this._desc;
	},
	
	getGroupitem: function () {
		return this._group;
	},
	getUngroupitem: zk.$void,
	_init: function () {
		var w = this._columns;
		
		this.listen({onOpen: [w, w._onMenuPopup]});
		
		if (zk.feature.pe && w.isColumnsgroup()) {
			if (!zk.isLoaded(w.getGroupPackage_()))
				zk.load(w.getGroupPackage_());
			var group = new zul.menu.Menuitem({
					label: msgzul.GRID_GROUP, visible: false
				});
				group.setSclass(w.$s('menugrouping'));
				group.listen({onClick: [w, w._onGroup]});
			this.appendChild(group);
			this._group = group;
			if (zk.feature.ee) {
				var ungroup = new zul.menu.Menuitem({
						label: msgzul.GRID_UNGROUP, visible: false
					});
				ungroup.setSclass(w.$s('menuungrouping'));
				ungroup.listen({onClick: [w, w._onUngroup]});
				this.appendChild(ungroup);
				this._ungroup = ungroup;
			}
		}
		var asc = new zul.menu.Menuitem({label: msgzul.GRID_ASC});
			asc.setSclass(w.$s('menuascending'));
			asc.listen({onClick: [w, w._onAsc]});
		this._asc = asc;
		this.appendChild(asc);
		
		var desc = new zul.menu.Menuitem({label: msgzul.GRID_DESC});
		desc.setSclass(w.$s('menudescending'));
		desc.listen({onClick: [w, w._onDesc]});
		this._desc = desc;
		this.appendChild(desc);
		this.syncColMenu();
		w.getPage().appendChild(this);
	},
	
	syncColMenu: function () {
		var w = this._columns;
		for (var c = this.lastChild, p; c != this._desc;) {
			p = c.previousSibling;
			this.removeChild(c);
			c = p;
		}
		if (w && w.isColumnshide()) {
			var sep = new zul.menu.Menuseparator();
			this.appendChild(sep);
			for (var item, c = w.firstChild; c; c = c.nextSibling) {
				item = new zul.menu.Menuitem({
					label: c.getLabel(),
					autocheck: true,
					checkmark: true,
					checked: c.isVisible()
				});
				item._col = c.uuid;
				item.listen({onClick: [w, w._onColVisi]});
				this.appendChild(item);
			}
		}
	}
});
})();