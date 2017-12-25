




zul.wgt.A = zk.$extends(zul.LabelImageWidget, {
	_dir: 'normal',
	

	$define: {
		
		
		disabled:  [
		    
		    
		    
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
			    		}
			    	};
		    	doDisable();
		    }
		],
		
		
		dir: _zkf = function () {
			var n = this.$n();
			if (n) n.innerHTML = this.domContent_();
		},
		
		
		href: function (v) {
			var n = this.$n();
			if (n) n.href = v || '';
		},
		
		
		target: function (v) {
			var n = this.$n();
			if (n) n.target = v || '';
		},
		
		
		tabindex: function (v) {
			var n = this.$n();
			if (n) n.tabIndex = v||'';
		},
		
		
		autodisable: null
	},

	
	bind_: function(){
		this.$supers(zul.wgt.A, 'bind_', arguments);
		if (!this._disabled) {
			var n = this.$n();
			this.domListen_(n, 'onFocus', 'doFocus_')
				.domListen_(n, 'onBlur', 'doBlur_');
		}
	},
	unbind_: function(){
		var n = this.$n();
		this.domUnlisten_(n, 'onFocus', 'doFocus_')
			.domUnlisten_(n, 'onBlur', 'doBlur_');

		this.$supers(zul.wgt.A, 'unbind_', arguments);
	},
	domContent_: function(){
		var label = zUtl.encodeXML(this.getLabel()), 
			img = this.getImage(),
			iconSclass = this.domIcon_();
		if (!img && !iconSclass) 
			return label;
		
		if (!img) {
			img = iconSclass;
		} else
			img = '<img src="' + img + '" align="absmiddle" />'
				+ (iconSclass ? ' ' + iconSclass : '');
		return this.getDir() == 'reverse' ? label + img : img + label;
	},
	domAttrs_: function(no){
		var attr = this.$supers('domAttrs_', arguments),
			v;
		if (v = this.getTarget())
			attr += ' target="' + v + '"';
		if (v = this.getTabindex()) 
			attr += ' tabIndex="' + v + '"';
		if (v = this.getHref()) 
			attr += ' href="' + v + '"';
		else 
			attr += ' href="javascript:;"';
		if(this._disabled)
			attr += ' disabled="disabled"';
		return attr;
	},
	doClick_: function(evt) {
		var href = this.getHref();
		
		if (href && href.toLowerCase().startsWith('mailto:')) {
			var ifrm = jq.newFrame('mailtoFrame', href, null);
			jq(ifrm).remove();
			evt.stop();
		}
		
		if (zk.ie < 11 && !href) {
			evt.stop({dom:true});
		}
		if (this._disabled)
			evt.stop(); 
		else {
			zul.wgt.ADBS.autodisable(this);
			
			this.fireX(evt);
			if (!evt.stopped)
				this.$super('doClick_', evt, true);
		}
			
	}
});

