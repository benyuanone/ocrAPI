

zul.sel.Option = zk.$extends(zul.Widget, {
	_selected: false,
	$define: {
    	
    	
		disabled: function (disabled) {
			var n = this.$n();
			if (n) n.disabled = disabled ? 'disabled' : '';
		},
		
		
		value: null
	},
	
	focus: function (timeout) {
		var p = this.parent;
		if (p) p.focus(timeout);
	},

	
	setVisible: function (visible) {
		if (this._visible != visible) {
			this._visible = visible;
			if (this.desktop)
				this.parent.rerender();
		}
	},
	
	setSelected: function (selected) {
		if (this.__updating__) { 
			delete this.__updating__;
			return; 
		}
		try {
			selected = selected || false;
			this.__updating__ = true;
			if (this._selected != selected) {
				if (this.parent)
					this.parent.toggleItemSelection(this);
				this._setSelectedDirectly(selected); 
			}
		} finally {
			delete this.__updating__;
		}
	},
	_setSelectedDirectly: function (selected) {
		var n = this.$n();
		
		if (n && n.selected != selected) {
			n.selected = selected ? 'selected' : '';
		}
		this._selected = selected;
	},
	
	isSelected: function () {
		return this._selected;
	},
	
	getLabel: function () {
		return this.firstChild ? this.firstChild.getLabel() : null; 
	},
	
	getMaxlength: function () {
		return this.parent ? this.parent.getMaxlength() : 0;
	},
	bind_: function () {
		this.$supers('bind_', arguments);
		
		if (this.isSelected())
			this.parent._selectedIndex = this._index;
	},
	
	getOptionIndex_: function () {
		var parent = this.parent, ret = -1;
		if (parent) {
			for (w = parent.firstChild; w; w = w.nextSibling) {
				if (w.$instanceof(zul.sel.Option)) {
					ret++;
					if(w == this) break;
				}		
			}
		}
		return ret;
	},
	domLabel_: function () {
		return zUtl.encodeXML(this.getLabel(), {maxlength: this.getMaxlength()});
	},
	domAttrs_: function () {
		var value = this.getValue();
		return this.$supers('domAttrs_', arguments) + (this.isDisabled() ? ' disabled="disabled"' :'') +
		(this.isSelected() ? ' selected="selected"' : '') + (value ? ' value="' + value + '"': '');
	},
	replaceWidget: function (newwgt) {
		this._syncItems(newwgt);
		this.$supers('replaceWidget', arguments);
	},
	_syncItems: function (newwgt) {
		if (this.parent && this.isSelected()) {
			var items = this.parent._selItems;
			if (items && items.$remove(this))
				items.push(newwgt);
		}
	}
});