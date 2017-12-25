

(function () {
	function _syncSelectedPanels(panels) {
		
		var box;
		if (panels.desktop && (box = panels.getTabbox())) {
			var oldSel = panels._selPnl,
				sel = box._selTab;
			if (oldSel != (sel && (sel = sel.getLinkedPanel()))) {
				if (oldSel && oldSel.desktop) oldSel._sel(false, true);
				if (sel) sel._sel(true, true);
				panels._selPnl = sel;
			}
		}
	}


zul.tab.Tabpanels = zk.$extends(zul.Widget, {
	
	getTabbox: function() {
		return this.parent;
	},
	setWidth: function (val) {
		var n = this.$n(),
			tabbox = this.getTabbox(),
			isVer = n && tabbox ? tabbox.isVertical() : false;
		if (isVer && !this.__width)
			n.style.width = '';

		this.$supers('setWidth', arguments);
		
		if (isVer) {
			if (n.style.width)
				this.__width = n.style.width;

			zUtl.fireSized(this);
		}
	},
	setStyle: function (val) {
		var n = this.$n(),
			tabbox = this.getTabbox(),
			isVer = n && tabbox ? tabbox.isVertical() : false;
		if (isVer && !this.__width) {
			n.style.width = '';
		}
		this.$supers('setStyle', arguments);

		if (isVer) {
			if (n.style.width)
				this.__width = n.style.width;

			zUtl.fireSized(this);
		}
	},
	
	setVflex: function (v) { 
		if (v != 'min') v = false;
		this.$supers('setVflex', arguments);
	},
	
	setHflex: function (v) { 
		if (v != 'min') v = false;
		this.$supers('setHflex', arguments);
	},
	bind_: function () {
		this.$supers(zul.tab.Tabpanels, 'bind_', arguments);
		if (this.getTabbox().isVertical()) {
			this._zwatched = true;
			zWatch.listen({onSize: this, beforeSize: this});
			var n = this.$n();
			if (n.style.width)
				this.__width = n.style.width;
		}
		zWatch.listen({onResponse: this});
	},
	unbind_: function () {
		if (this._zwatched) {
			zWatch.unlisten({onSize: this, beforeSize: this});
			this._zwatched = false;
		}
		zWatch.unlisten({onResponse: this});
		this.$supers(zul.tab.Tabpanels, 'unbind_', arguments);
	},
	onSize: function () {
		this._fixWidth();
	},
	_fixWidth: function () {
		var parent = this.parent.$n();
		if (this.__width || !zk(parent).isRealVisible())
			return;

		var width = parent.offsetWidth,
			n = this.$n();

		width -= jq(parent).find('>div:first')[0].offsetWidth;

		n.style.width = jq.px0(width);
	},
	beforeSize: function () {
		this.$n().style.width = this.__width || '';
	},
	onChildRemoved_: function (child) {
		this.$supers("onChildRemoved_", arguments);
		this._shallSync = true;
	},
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		
		
		var tabbox = this.getTabbox(), 
			cave;
		if (tabbox && (!tabbox.inAccordionMold()
				|| (cave = child.$n('cave')) && cave.style.display != 'none'))
			this._shallSync = true;
	},
	onResponse: function () {
		
		if (this._shallSync) {
			_syncSelectedPanels(this);
			this._shallSync = false;
		}
	}
});
})();