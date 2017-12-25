
(function () {
	
	function _initUpld(wgt) {
		zWatch.listen({onSize: wgt});
		var v;
		if (v = wgt._upload)
			wgt._uplder = new zul.Upload(wgt, null, v);
	}
	
	function _cleanUpld(wgt) {
		var v;
		if (v = wgt._uplder) {
			zWatch.unlisten({onSize: wgt});
			wgt._uplder = null;
			v.destroy();
		}
	}
	

zul.wgt.Toolbarbutton = zk.$extends(zul.LabelImageWidget, {
	_orient: 'horizontal',
	_dir: 'normal',
	_mode:'default',
	_checked: false,
	

	$define: {
		
		
		mode: function(mode) {
			this.rerender();
		},
		
		
		checked: function(val) {
			if (this.desktop && this._mode == 'toggle')
				jq(this.$n())[val ? 'addClass' : 'removeClass'](this.$s('checked'));
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
		
		
		href: null,
		
		
		target: null,
		
		
		dir: _zkf = function () {
			this.updateDomContent_();
		},
		
		
		orient: _zkf,
		
		
		tabindex: function (v) {
			var n = this.$n();
			if (n) n.tabIndex = v||'';
		},
		
		
		autodisable: null,
		
		
		upload: function (v) {
			var n = this.$n();
			if (n) {
				_cleanUpld(this);
				if (v && v != 'false' && !this._disabled)
					_initUpld(this);
			}
		}
	},

	
	getTextNode: function () {
		return this.$n('cnt');
	},
	bind_: function(){
		this.$supers(zul.wgt.Toolbarbutton, 'bind_', arguments);
		if (!this._disabled) {
			var n = this.$n();
			this.domListen_(n, 'onFocus', 'doFocus_')
				.domListen_(n, 'onBlur', 'doBlur_');
		}
		if (!this._disabled && this._upload) _initUpld(this);
	},
	unbind_: function(){
		_cleanUpld(this);
		var n = this.$n();
		this.domUnlisten_(n, 'onFocus', 'doFocus_')
			.domUnlisten_(n, 'onBlur', 'doBlur_');

		this.$supers(zul.wgt.Toolbarbutton, 'unbind_', arguments);
	},
	domContent_: function(){
		var label = zUtl.encodeXML(this.getLabel()), img = this.getImage(),
			iconSclass = this.domIcon_();
		if (!img && !iconSclass)
			return label;
		
		if (!img) img = iconSclass;
		else img = '<img src="' + img + '" align="absmiddle" />'
					+ (iconSclass ? ' ' + iconSclass : '');
		
		var space = label? 'vertical' == this.getOrient() ? '<br/>' : '&nbsp;' : '';
		return this.getDir() == 'reverse' ? label + space + img : img + space + label;
	},
	domClass_: function(no){
		var scls = [this.$supers('domClass_', arguments)], 
			zcls = this.getZclass(),
			nozcls = (!no || !no.zclass);
		
		if(this._mode == 'toggle' && this._checked && nozcls && zcls ) {
			scls.push(' ', this.$s('checked'));
		}
		
		return scls.join('');
	},
	domAttrs_: function(no){
		var attr = this.$supers('domAttrs_', arguments),
			v = this.getTabindex();
		if (this._disabled)
			attr += ' disabled="disabled"';
		if (v)
			attr += ' tabIndex="' + v + '"';
		return attr;
	},
	onSize: function () {
		if (this._uplder)
			this._uplder.sync();
	},
	doClick_: function(evt){
		if (!this._disabled) {
			if (!this._upload)
				zul.wgt.ADBS.autodisable(this);
			else if (!zk.ie || zk.ie > 10) 
				this._uplder.openFileDialog();
			
			this.fireX(evt);
			
			if (!evt.stopped) {
				var href = this._href,
					isMailTo = href ? href.toLowerCase().startsWith('mailto:') : false;
					
				if (href) {
					
					if (isMailTo) {
						var ifrm = jq.newFrame('mailtoFrame', href, null);
						jq(ifrm).remove();
					} else {
						zUtl.go(href, {target: this._target || (evt.data.ctrlKey ? '_blank' : '')});
					}
				}
				
				this.$super('doClick_', evt, true);
				
				if (this._mode == 'toggle') {
					this.setChecked(!this.isChecked());
					this.fire('onCheck', this.isChecked());
				}
			}
		}
	}
});
})();