

zul.sel.Treerow = zk.$extends(zul.Widget, {
	
	getTree: function () {
		return this.parent ? this.parent.getTree() : null;
	},
	
	getLevel: function () {
		return this.parent ? this.parent.getLevel(): 0;
	},
	
	getLinkedTreechildren: function () {
		return this.parent ? this.parent.treechildren : null;
	},
	domClass_: function (no) {
		var scls = this.$supers('domClass_', arguments),
			p = this.parent;
		if (p && (!no || !no.zclass)) {
			var zcls = this.getZclass();
			if (p.isDisabled())
				scls += (scls ? ' ': '') + this.$s('disabled');
			if (p.isSelected())
				scls += (scls ? ' ': '') + this.$s('selected');
		}
		return scls;
	},
	domTooltiptext_ : function () {
		return this._tooltiptext || this.parent._tooltiptext || this.parent.parent._tooltiptext;
	},
	
	domStyle_: function (no) {
		
		return ((this.parent && !this.parent._isRealVisible() && this.isVisible()) ?
				'display:none;' : '') + this.$supers('domStyle_', arguments);
	},
	
	removeChild: function (child) {
		for (var w = child.firstChild; w;) {
			var n = w.nextSibling; 
			child.removeChild(w); 
			w = n;
		}
		this.$supers('removeChild', arguments);
	},
	
	doClick_: function(evt) {
		var ti = this.parent,
			tg = evt.domTarget;
		if (tg == this.$n('open') || tg == this.$n('icon')) {
			ti.setOpen(!ti._open);
			evt.stop();
		} else if (!ti.isDisabled())
			this.$supers('doClick_', arguments);
	},
	
	scrollIntoView: function () {
		var bar = this.getTree()._scrollbar;
		if (bar) {
			bar.syncSize();
			bar.scrollToElement(this.$n());
		} else {
			this.$supers('scrollIntoView', arguments);
		}
	},
	deferRedrawHTML_: function (out) {
		out.push('<tr', this.domAttrs_({domClass:1}), ' class="z-renderdefer"></tr>');
	}
});