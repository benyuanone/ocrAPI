
(function () {

	function _initUpld(wgt) {
		zWatch.listen({onShow: wgt});
		var v;
		if (v = wgt._upload)
			wgt._uplder = new zul.Upload(wgt, wgt._getUploadRef(), v);
	}
	
	function _cleanUpld(wgt) {
		var v;
		if (v = wgt._uplder) {
			zWatch.unlisten({onShow: wgt});
			wgt._uplder = null;
			v.destroy();
		}
	}
	
(
zul.menu.Menuitem = zk.$extends(zul.LabelImageWidget, {
	_value: '',

	$define: {
		
		
		checkmark: _zkf = function () {
			this.rerender();
		},
		
		
		disabled: [
			
			
			function (v, opts) {
		    	if (opts && opts.adbs)
		    		
		    		this._adbs = true;	
		    	else if (!opts || opts.adbs === undefined)
		    		
		    		this._adbs = false;	
		    	if (!v) {
		    		if (this._adbs)
		    			
		    			this._adbs = false;
		    		else if (opts && opts.adbs === false)
		    			
		    			return this._disabled;
		    	}
		    	return v;
			}, 
			function (v, opts) {
				this.rerender(opts && opts.skip ? -1 : 0); 
			}
		],
		
		
		href: _zkf,
		
		
		value: null,
		
		
		checked: function (checked) {
			if (checked)
				this._checkmark = checked;
			var n = this.$n();
			if (n && !this.isTopmost() && !this.getImage()) {
				var $n = jq(n);
				$n[checked ? 'addClass' : 'removeClass'](this.$s('checked'));
				if (this._checkmark)
					$n.addClass(this.$s('checkable'));
			}
		},
		
		
		autocheck: null,
		
		
		target: function (target) {
			var anc = this.$n('a');
			if (anc) {
				if (this.isTopmost())
					anc = anc.parentNode;
				anc.target = this._target;
			}
		},
		
		
		autodisable: null,
		
		
		upload: function (v) {
			var n = this.$n();
			if (n) {
				_cleanUpld(this);
				if (v && v != 'false') _initUpld(this);
			}
		}
	},
	
	isTopmost: function () {
		return this._topmost;
	},
	beforeParentChanged_: function (newParent) {
		this._topmost = newParent && !(newParent.$instanceof(zul.menu.Menupopup));
		this.$supers('beforeParentChanged_', arguments);
	},
	domClass_: function (no) {
		var scls = this.$supers('domClass_', arguments);
		if (!no || !no.zclass) {
			var added = this.isDisabled() ? this.$s('disabled') : '';
			if (added) scls += (scls ? ' ': '') + added;
			added = (!this.getImage() && this.isCheckmark()) ? 
						this.$s('checkable') + (this.isChecked() ? ' ' + this.$s('checked') : '') : '';
			if (added) scls += (scls ? ' ': '') + added;
		}
		return scls;
	},
	domContent_: function () {
		var label = '<span class="' + this.$s('text') + '">' + 
				(zUtl.encodeXML(this.getLabel())) + '</span>',
			icon = '<i class="' + this.$s('icon') + ' z-icon-check"></i>',
			img = this.getImage(),
			iconSclass = this.domIcon_();
		
		if (img)
			img = '<img src="' + img + '" class="' + this.$s('image') + '" align="absmiddle" />'
				+ (iconSclass ? ' ' + iconSclass : '');
		else {
			if (iconSclass) {
				img = iconSclass;
			} else {
				img = '<img ' + (this.isTopmost() ? 'style="display:none"' : '') +
					' src="data:image/png;base64,R0lGODlhAQABAIAAAAAAAAAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==" class="' +
					this.$s('image') + '" align="absmiddle" />';
			}
		}
		return img + (this.isAutocheck() || this.isCheckmark() ? icon : '') + ' ' + label;
	},
	
	getMenubar: function () {
		for (var p = this.parent; p; p = p.parent)
			if (p.$instanceof(zul.menu.Menubar))
				return p;
		return null;
	},
	bind_: function () {
		this.$supers(zul.menu.Menuitem, 'bind_', arguments);

		if (!this.isDisabled()) {
			var anc = this.$n('a');
			if (this.isTopmost()) {
				this.domListen_(anc, 'onFocus', 'doFocus_')
					.domListen_(anc, 'onBlur', 'doBlur_');
			}
			this.domListen_(anc, 'onMouseEnter');
			if (this._upload) _initUpld(this);
		}
	},
	unbind_: function () {
		if (!this.isDisabled()) {
			if (this._upload) _cleanUpld(this);
			var anc = this.$n('a');
			if (this.isTopmost()) {
				this.domUnlisten_(anc, 'onFocus', 'doFocus_')
					.domUnlisten_(anc, 'onBlur', 'doBlur_');
			}
			this.domUnlisten_(anc, 'onMouseEnter');
		}

		this.$supers(zul.menu.Menuitem, 'unbind_', arguments);
	},

	doClick_: function (evt) {
		if (this._disabled)
			evt.stop();
		else {
			if (!this._canActivate(evt)) return;
			if (!this._upload)
				zul.wgt.ADBS.autodisable(this);
			else if (!zk.ie || zk.ie > 10) 
				this._uplder.openFileDialog();

			var topmost = this.isTopmost(),
				anc = this.$n('a');
			
			if (anc.href.startsWith('javascript:')) {
				if (this.isAutocheck()) {
					this.setChecked(!this.isChecked());
					this.fire('onCheck', this.isChecked());
				}
				this.fireX(evt);
			} else if (anc.href.toLowerCase().startsWith('mailto:')) { 
				var ifrm = jq.newFrame('mailtoFrame', anc.href, null);
				jq(ifrm).remove();
				evt.stop();
			} else {
				if (zk.ie < 11 && topmost && this.$n().id != anc.id)
					zUtl.go(anc.href, {target: anc.target});
					
					
					
				if (zk.gecko && topmost && this.$n().id != anc.id) {
					zUtl.go(anc.href, {target: anc.target});
					evt.stop();
					
				}
			}
			if (!topmost)
				for (var p = this.parent; p; p = p.parent)
					if (p.$instanceof(zul.menu.Menupopup)) {
						
						if (!p.isOpen() || this._uplder )
							break;
						this._updateHoverImage(); 
						p.close({sendOnOpen:true});
					} else if (!p.$instanceof(zul.menu.Menu)) 
						break;
					else
						p._updateHoverImage(); 

			var menubar;
			if (zk.webkit && (menubar=this.getMenubar()) && menubar._autodrop)
				menubar._noFloatUp = true;
				
			this.$super('doClick_', evt, true);
		}
	},
	_canActivate: function (evt) {
		return !this.isDisabled() && (!zk.ie < 11 || !this.isTopmost() || this._uplder
				|| jq.isAncestor(this.$n('a'), evt.domTarget));
	},
	_getUploadRef: function () {
		return this.isTopmost() ? this.$n() : this.$n('a');
	},
	_doMouseEnter: function (evt) {
		if (zul.menu._nOpen || this.isTopmost())
			zWatch.fire('onFloatUp', this); 
	},
	deferRedrawHTML_: function (out) {
		var tag = this.isTopmost() ? 'td' : 'li';
		out.push('<', tag, this.domAttrs_({domClass:1}), ' class="z-renderdefer"></', tag,'>');
	},
	
	getImageNode: function () {
		if (!this._eimg && (this._image || this._hoverImage)) {
			var n = this.$n();
			if (n) 
				this._eimg = this.$n('a').firstChild;
		}
		return this._eimg;
	}
}, {
	_isActive: function (wgt) {
		return jq(wgt.$n()).hasClass(wgt.$s('hover'));
	},
	_addActive: function (wgt) {
		var top = wgt.isTopmost();
		jq(wgt.$n()).addClass(wgt.$s('hover'));
		if (!top && wgt.parent.parent.$instanceof(zul.menu.Menu))
			this._addActive(wgt.parent.parent);
	},
	_rmActive: function (wgt) {
		return jq(wgt.$n()).removeClass(wgt.$s('hover'));
	}
})).prototype['onShow'] = function () {
	if (this._uplder)
		this._uplder.sync();
};

})();