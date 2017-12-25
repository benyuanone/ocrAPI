
(function () {
	
	function _shallIgnore(evt) {
		var v = evt.domEvent;
		return v && jq.nodeName(v.target, 'label');
	}

var Checkbox =

zul.wgt.Checkbox = zk.$extends(zul.LabelImageWidget, {
	
	_checked: false,

	$define: {
		
		
		disabled: function (v) {
			var n = this.$n('real');
			if (n) n.disabled = v;
		},
		
		
		checked: function (v) {
			var n = this.$n('real');
			if (n) {
				
				jq(n).prop('checked', v);
			}
		},
		
		
		name: function (v) {
			var n = this.$n('real');
			if (n) n.name = v || '';
		},
		
		
		tabindex: function (v) {
			var n = this.$n('real');
			if (n) n.tabIndex = v || '';
		},
		
		
		value: function (v) {
			var n = this.$n('real');
			if (n) n.value = v || '';
		},
		
		
		autodisable: null
	},

	
	focus_: function (timeout) {
		zk(this.$n('real') || this.$n()).focus(timeout);
		return true;
	},
	contentAttrs_: function () {
		var html = '', 
			v; 
		if (v = this.getName())
			html += ' name="' + v + '"';
		if (this._disabled)
			html += ' disabled="disabled"';
		if (this._checked)
			html += ' checked="checked"';
		if (v = this._tabindex)
			html += ' tabindex="' + v + '"';
		if (v = this.getValue())
			html += ' value="' + v + '"';
		return html;
	},
	bind_: function (desktop) {
		this.$supers(Checkbox, 'bind_', arguments);

		var n = this.$n('real');
		
		
		if (n.checked != n.defaultChecked)
			n.checked = n.defaultChecked;

		this.domListen_(n, 'onFocus', 'doFocus_')
			.domListen_(n, 'onBlur', 'doBlur_');
	},
	unbind_: function () {
		var n = this.$n('real');
		
		this.domUnlisten_(n, 'onFocus', 'doFocus_')
			.domUnlisten_(n, 'onBlur', 'doBlur_');

		this.$supers(Checkbox, 'unbind_', arguments);
	},
	doSelect_: function (evt) {
		if (!_shallIgnore(evt))
			this.$supers('doSelect_', arguments);
	},
	doClick_: function (evt) {
		if (!_shallIgnore(evt)) {
			
			
			zul.wgt.ADBS.autodisable(this);
			var real = this.$n('real'),
				checked = real.checked;
			if (checked != this._checked) { 
				this.setChecked(checked); 
				this.fireOnCheck_(checked);
			}
			if (zk.webkit && !zk.mobile) 
				zk(real).focus();

			
			
			if (!(this.$instanceof(zul.wgt.Radio) && this.getRadiogroup()))
				evt.stop({propagation: true});

			return this.$supers('doClick_', arguments);
		}
	},
	fireOnCheck_: function (checked) {
		this.fire('onCheck', checked);
	},
	beforeSendAU_: function (wgt, evt) {
		if (evt.name != 'onClick') 
			this.$supers('beforeSendAU_', arguments);
	},
	getTextNode: function () {
		return this.$n('cnt');
	}
});

})();