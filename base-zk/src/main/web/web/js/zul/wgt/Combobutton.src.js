
(function() {
	
	
	function _setCloseTimer (wgt) {
		if (!wgt._tidclose)
			wgt._tidclose = setTimeout(function() {
				if (!wgt._bover) {
					if (wgt._autodrop && wgt.isOpen())
						wgt.close({sendOnOpen: true});
				}
				wgt._tidclose = null;
			}, 200);
	}

	function _fireOnOpen (wgt, opts, o) {
		if (opts && opts.sendOnOpen)
			wgt.fire('onOpen', {open:o, value: wgt.getLabel()}, {rtags: {onOpen: 1}});
	}
	
	function _attachPopup(wgt, bListen) {
		
		if (!wgt._oldppclose) {
			var pp = wgt.firstChild;
			if (pp) {
				var $pp = jq(pp),
					wd = jq(wgt).width();
				if($pp.width() < wd) {
					$pp.width(wd - zk(pp).padBorderWidth());

					
					pp.fire(pp.firstChild);
					var openInfo = pp._openInfo;
					if (openInfo) {
						pp.position.apply(pp, openInfo);
						
						
					}
				}
			}
			wgt._oldppclose = pp.close;
			
			if (bListen)
				wgt.domListen_(pp, 'onMouseOver')
					.domListen_(pp, 'onMouseOut');

			
			pp.close = function (opts) {
				wgt._oldppclose.apply(pp, arguments);
				_fireOnOpen(wgt, opts, false);

				if (bListen)
					wgt.domUnlisten_(pp, 'onMouseOver')
						.domUnlisten_(pp, 'onMouseOut');
				pp.close = wgt._oldppclose;
				delete wgt._oldppclose;
			}
		}
	}

zul.wgt.Combobutton = zk.$extends(zul.wgt.Button, {
	$define: {
		
		
		autodrop: null
	},
	getZclass: function () {
		return 'z-combobutton';
	},
	domContent_: function () {
		var label = '<span id="' + this.uuid + '-txt" class="' + this.$s('text') + '">' 
		 	+ zUtl.encodeXML(this.getLabel()) + '</span>',
			img = this.getImage(),
			iconSclass = this.domIcon_();
		if (!img && !iconSclass) return label;

		if (!img) img = iconSclass;
		else
			img = '<img class="' + this.$s('image') + '" src="' + img + '" />'
				+ (iconSclass ? ' ' + iconSclass : '');
		var space = "vertical" == this.getOrient() ? '<br/>': ' ';
		return this.getDir() == 'reverse' ?
			label + space + img: img + space + label;
	},
	domClass_: function (no) {
		var cls = this.$supers(zul.wgt.Combobutton, 'domClass_', arguments);
		if (!this._isDefault())
			cls += ' z-combobutton-toolbar';
		return cls;
	},
	_isDefault: function () {
		return this._mold == 'default';
	},
	
	isOpen: function () {
		var pp = this.firstChild;
		return pp && pp.isOpen();
	},
	
	setOpen: function (b, opts) {
		if (!this._disabled && !zk.animating())
			
			this[b ? 'open' : 'close'](opts || {});
	},
	renderInner_: function (out) {
		for (var w = this.firstChild; w; w = w.nextSibling)
			w.redraw(out);
	},
	isTableLayout_: function () {
		return true;
	},
	unbind_: function () {
		var pp;
		
		if ((pp = this.firstChild)
			&& (pp = pp.$n()))
			this.domUnlisten_(pp, 'onMouseOver')
				.domUnlisten_(pp, 'onMouseOut');
		this.$supers('unbind_', arguments);
	},
	doFocus_: function (evt) {
		if (this == evt.target)
			
			this.$supers('doFocus_', arguments);
	},

	
	open: function (opts) {
		var pp = this.firstChild;
		if (pp && !this.isOpen()) {
			if (pp.$instanceof(zul.wgt.Popup)) {
				pp.open(this.uuid, null, 'after_start', opts);
				_fireOnOpen(this, opts, true);
			}
			_attachPopup(this, !pp.$instanceof(zul.wgt.Menupopup));
		}
	},
	
	close: function (opts) {
		if (this.isOpen())
			this.firstChild.close(opts);
	},

	doClick_: function (evt) {
		var d = evt.domTarget;
		
		
		if (d) {
			
			
			var open = !this.isOpen();
			if (this == evt.target)
				if (this.$n('btn') == d || this.$n('icon') == d || !open)
					this.setOpen(open, {sendOnOpen: true});
				else
					this.$supers('doClick_', arguments);
		}
	},
	doMouseDown_: function (evt) {
		if (this == evt.target)
			
			this.$supers('doMouseDown_', arguments);
	},
	doMouseOver_: function (evt) {
		this._bover = true;
		if (this == evt.target) {
			var d = evt.domTarget;
			
			if (this._autodrop && (this.$n('btn') == d || this.$n('icon') == d) && !this.isOpen())
				this.open({sendOnOpen: true});
			this.$supers('doMouseOver_', arguments);
		}
	},
	doMouseOut_: function (evt) {
		this._bover = false;
		_setCloseTimer(this);
		this.$supers('doMouseOut_', arguments);
	},
	_doMouseOver: function (evt) { 
		
		this._bover = true;
	},
	_doMouseOut: function (evt) { 
		
		this._bover = false;
		_setCloseTimer(this);
	},
	ignoreDescendantFloatUp_: function (des) {
		return des && des.$instanceof(zul.wgt.Popup);
	},
	
	
	
	rerender: function(skipper) {
		if (this.isOpen()) {
			this.close();
		}
		this.$supers('rerender', arguments);
	}
});
})();