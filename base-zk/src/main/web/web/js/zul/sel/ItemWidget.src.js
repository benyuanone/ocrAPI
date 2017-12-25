
(function () {
	function _isListgroup(w) {
		return zk.isLoaded('zkex.sel') && w.$instanceof(zkex.sel.Listgroup);
	}
	function _isListgroupfoot(w) {
		return zk.isLoaded('zkex.sel') && w.$instanceof(zkex.sel.Listgroupfoot);
	}

zul.sel.ItemWidget = zk.$extends(zul.Widget, {
	_checkable: true,
	$define: {
		
		
		checkable: function () {
			if (this.desktop)
				this.rerender();
		},
		
		
		disabled: function () {
			if (this.desktop)
				this.rerender();
		},
		
		
		value: null
	},
	
	setSelected: function (selected) {
		if (this._selected != selected) {
			var box = this.getMeshWidget();
			if (box)
				box.toggleItemSelection(this);
				
			this._setSelectedDirectly(selected);
		}
	},
	_setSelectedDirectly: function (selected) {
		var n = this.$n();
		if (n) {
			jq(n)[selected ? 'addClass' : 'removeClass'](this.$s('selected'));
			
            
			this._updHeaderCM();
		}
		this._selected = selected;
	},
	
	getLabel: function () {
		return this.firstChild ? this.firstChild.getLabel() : null; 
	},
	
	isSelected: function () {
		return this._selected;
	},
	
	isStripeable_: function () {
		return true;
	},
	
	getMeshWidget: function () {
		return this.parent;
	},
	_getVisibleChild: function (row) {
		for (var i = 0, j = row.cells.length; i < j; i++)
			if (zk(row.cells[i]).isVisible()) return row.cells[i];
		return row;
	},
	
	setVisible: function (visible) {
		if (this._visible != visible) { 
			this.$supers('setVisible', arguments);
			if (this.isStripeable_()) {
				var p = this.getMeshWidget();
				if (p) p.stripe();
			}
		}
	},
	domClass_: function (no) {
		var scls = this.$supers('domClass_', arguments);
		if (!no || !no.zclass) {
			var zcls = this.getZclass();
			if (this.isDisabled())
				scls += (scls ? ' ': '') + this.$s('disabled');
			
			if (_isListgroup(this) || _isListgroupfoot(this)) {
				if (this.getMeshWidget().groupSelect && this.isSelected())
					scls += (scls ? ' ': '') + this.$s('selected');
			} else {
				if (this.isSelected())
					scls += (scls ? ' ': '') + this.$s('selected');
			}
		}
		return scls;
	},
	focus_: function (timeout) {
		var mesh = this.getMeshWidget();
		this._doFocusIn();
		mesh._syncFocus(this);
		mesh.focusA_(mesh.$n('a'), timeout);
		return true;
	},
	_doFocusIn: function () {
		var n = this.$n();
		if (n)
			jq(this._getVisibleChild(n)).addClass(this.$s('focus'));
		
		if (n = this.getMeshWidget())
			n._focusItem = this;			
	},
	_doFocusOut: function () {
		var n = this.$n();
		if (n) {
			var cls = this.$s('focus');
			jq(n).removeClass(cls);
			jq(n.cells).removeClass(cls);
		}
	},
	_updHeaderCM: function (bRemove) { 
		var box;
		if ((box = this.getMeshWidget()) && box._headercm && box._multiple) {
			if (bRemove) {
				box._updHeaderCM();
				return;
			}

			var zcls = zk.Widget.$(box._headercm).$s('checked'),
				$headercm = jq(box._headercm);

			if (!this.isSelected())
				$headercm.removeClass(zcls);
			else if (!$headercm.hasClass(zcls))
				box._updHeaderCM(); 
		}
	},
	
	beforeParentChanged_: function (newp) {
		if (!newp) 
			this._updHeaderCM(true);
		this.$supers('beforeParentChanged_', arguments);
	},
	
	afterParentChanged_: function () {
		if (this.parent) 
			this._updHeaderCM();
		this.$supers('afterParentChanged_', arguments);
	},

	
	doSelect_: function(evt) {
		if (this.isDisabled() || !this.isCheckable()) return;
		if (!evt.itemSelected) {
			this.getMeshWidget()._doItemSelect(this, evt);
			evt.itemSelected = true;
		}
		this.$supers('doSelect_', arguments);
	},
	doKeyDown_: function (evt) {
		var mesh = this.getMeshWidget();
		if (!zk.gecko || !jq.nodeName(evt.domTarget, 'input', 'textarea'))
			zk(mesh.$n()).disableSelection();
		mesh._doKeyDown(evt);
		this.$supers('doKeyDown_', arguments);
	},
	doKeyUp_: function (evt) {
		var mesh = this.getMeshWidget();
		zk(mesh.$n()).enableSelection();
		mesh._doKeyUp(evt);
		this.$supers('doKeyUp_', arguments);
	},
	deferRedrawHTML_: function (out) {
		out.push('<tr', this.domAttrs_({domClass:1}), ' class="z-renderdefer"></tr>');
	}
});
})();