
(function () {
	function _initUpld(wgt) {
		var v;
		if (v = wgt._upload)
			wgt._uplder = new zul.Upload(wgt, null, v);
	}
	
	function _cleanUpld(wgt) {
		var v;
		if (v = wgt._uplder) {
			wgt._uplder = null;
			v.destroy();
		}
	} 
	
var Button = 

zul.wgt.Button = zk.$extends(zul.LabelImageWidget, {
	_orient: 'horizontal',
	_dir: 'normal',
	_type: 'button',

	$define: {
		
		
		href: null,
		
		
		target: null,
		
		
		dir: _zkf = function () {
			this.updateDomContent_();
		},
		
		
		orient: _zkf,
		
		
		type: _zkf,
		
		
		disabled: [
		    
		    
		    function (v, opts) {
		    	if (opts && opts.adbs)
		    		
		    		this._adbs = true;	
		    	else if (!opts || opts.adbs === undefined)
		    		
		    		this._adbs = false;	
		    	if (!v) {
		    		if (this._adbs) {
		    			
		    			this._adbs = false;
		    		} else if (opts && opts.adbs === false)
		    			
		    			return this._disabled;
		    	}
		    	return v;
		    }, 
		    function (v) {
		    	var self = this,
		    		doDisable = function() { 
			    		if (self.desktop) {
			    			jq(self.$n()).attr('disabled', v); 
			    			
			    			if (self._upload)
			    				v ? _cleanUpld(self) : _initUpld(self);
			    		}
			    	};
		    	
		    	
		    	if (this._type == 'submit')
		    		setTimeout(doDisable, 50);
		    	else
		    		doDisable();
		    }
		],
		
		
		
		tabindex: function (v) {
			var n = this.$n();
			if (n) n.tabIndex = v||'';
		},
		
		
		autodisable: null,
		
		
		upload: function (v) {
			var n = this.$n();
			if (n && !this._disabled) {
				_cleanUpld(this);
				if (v && v != 'false') _initUpld(this);
			}
		}
	},

	
	focus_: function (timeout) {
		
		var wgt = this,
			btn = this.$n();
		if (btn.disabled && !wgt._delayFocus) {
			wgt._delayFocus = true;
			setTimeout(function() {
				wgt.focus_(timeout);
				wgt._delayFocus = null;				
			}, 0);
		}		
		
		if (!zk.focusBackFix || !this._upload)
			zk(btn).focus(timeout);
		return true;
	},
	
	domContent_: function () {
		var label = zUtl.encodeXML(this.getLabel()),
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
	onShow: function() {
		
		if (this.$n() && !this._disabled && this._uplder) {
			this._uplder.sync();
		}
	},
	bind_: function () {
		this.$supers(Button, 'bind_', arguments);

		var n = this.$n();
		this.domListen_(n, 'onFocus', 'doFocus_')
			.domListen_(n, 'onBlur', 'doBlur_');
		zWatch.listen({onShow: this});

		if (!this._disabled && this._upload) _initUpld(this);
	},
	unbind_: function () {
		_cleanUpld(this);

		var n = this.$n();
		this.domUnlisten_(n, 'onFocus', 'doFocus_')
			.domUnlisten_(n, 'onBlur', 'doBlur_');
		zWatch.unlisten({onShow: this});

		this.$supers(Button, 'unbind_', arguments);
	},
	doClick_: function (evt) {
		if (!evt.domEvent) 
			return;
		
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
			}
		}
		
		
	},
	setFlexSize_: function(sz) { 
		var n = this.$n();
		if (sz.height !== undefined) {
			if (sz.height == 'auto')
				n.style.height = '';
			else if (sz.height != '')
				n.style.height = jq.px0(sz.height);
			else
				n.style.height = this._height ? this._height : '';
		}
		if (sz.width !== undefined) {
			if (sz.width == 'auto')
				n.style.width = '';
			else if (sz.width != '')
				n.style.width = jq.px0(sz.width);
			else
				n.style.width = this._width ? this._width : '';
		}
	}
});

zul.wgt.ADBS = zk.$extends(zk.Object, {
	$init: function (ads) {
		this._ads = ads;
	},
	onResponse: function () {
		for (var ads = this._ads, ad; ad = ads.shift();)
			
			ad.setDisabled(false, {adbs: false, skip: true});
		zWatch.unlisten({onResponse: this});
	}
},{ 
	
	autodisable: function(wgt) {
		var ads = wgt._autodisable, aded, uplder;
		if (ads) {
			ads = ads.split(',');
			for (var j = ads.length; j--;) {
				var ad = ads[j].trim();
				if (ad) {
					var perm;
					if (perm = ad.charAt(0) == '+')
						ad = ad.substring(1);
					ad = 'self' == ad ? wgt: wgt.$f(ad);
					
					if (ad == wgt) { 
						uplder = wgt._uplder;
						wgt._uplder = null;
						wgt._autodisable_self = true;
					}
					if (ad && !ad._disabled) {
						
						ad.setDisabled(true, {adbs: true, skip: true});
						if (wgt.inServer)
							if (perm)
								ad.smartUpdate('disabled', true);
							else if (!aded) aded = [ad];
							else aded.push(ad);
					}
				}
			}
		}
		if (aded) {
			aded = new zul.wgt.ADBS(aded);
			if (uplder) {
				uplder._aded = aded;
				wgt._uplder = uplder;
			} else if (wgt.isListen('onClick', {asapOnly:true}))
				zWatch.listen({onResponse: aded});
			else
				setTimeout(function () {aded.onResponse();}, 800);
		}
	}
});

})();
