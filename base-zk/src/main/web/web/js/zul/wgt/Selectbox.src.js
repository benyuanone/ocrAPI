

zul.wgt.Selectbox = zk.$extends(zul.Widget, {
	$define: {
		
		
		tabindex: function (tabindex) {
			var n = this.$n();
			if (n) n.tabindex = tabindex || '';
		},
		
		
		selectedIndex: function (selectedIndex) {
			var n = this.$n();
			if (n)
				n.selectedIndex = selectedIndex;
		},
		
		
		disabled: function (disabled) {
			var n = this.$n();
			if (n) n.disabled = disabled ? 'disabled' : '';
		},
		
		
		name: function (name) {
			var n = this.$n();
			if (n) n.name = name;
		}
	},
	_fixSelIndex: function () {
		if (this._selectedIndex < 0)
			this.$n().selectedIndex = -1;
	},
	bind_: function () {
		this.$supers(zul.wgt.Selectbox, 'bind_', arguments);
		var n = this.$n();
		this.domListen_(n, 'onChange')
			.domListen_(n, 'onFocus', 'doFocus_')
			.domListen_(n, 'onBlur', 'doBlur_');
		
		if (!zk.gecko) {
			var fn = [this,  this._fixSelIndex];
			zWatch.listen({onRestore: fn, onVParent: fn});
		}

		this._fixSelIndex();
	},
	unbind_: function () {
		var n = this.$n();
		this.domUnlisten_(n, 'onChange')
			.domUnlisten_(n, 'onFocus', 'doFocus_')
			.domUnlisten_(n, 'onBlur', 'doBlur_')
			.$supers(zul.wgt.Selectbox, 'unbind_', arguments);

		var fn = [this,  this._fixSelIndex];
		zWatch.unlisten({onRestore: fn, onVParent: fn});
	},
	_doChange: function (evt) {
		var n = this.$n(),
			v = n.selectedIndex;
		if (zk.opera) n.selectedIndex = v; 
		if (this._selectedIndex == v)
			return;
		this.setSelectedIndex(n.selectedIndex);
		this.fire('onSelect', n.selectedIndex);
	},
	
	doBlur_: function (evt) {
		this._doChange(evt);
		return this.$supers('doBlur_', arguments); 		
	},
	
	beforeCtrlKeys_: function (evt) {
		this._doChange(evt);
	},
	domAttrs_: function () {
		var v;
		return this.$supers('domAttrs_', arguments)
			+ (this.isDisabled() ? ' disabled="disabled"' :'')
			+ ((v=this.getSelectedIndex()) > -1 ? ' selectedIndex="' + v + '"': '')
			+ ((v=this.getTabindex()) ? ' tabindex="' + v + '"': '')
			+ ((v=this.getName()) ? ' name="' + v + '"': '');
	}
});