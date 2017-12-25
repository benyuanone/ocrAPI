

zul.inp.Comboitem = zk.$extends(zul.LabelImageWidget, {
	$define: {
		
		
		disabled: function (v) {
			var n = this.$n();
			if (n) {
				var disd = this.$s('disabled');
				v ? jq(n).addClass(disd): jq(n).removeClass(disd);
			}
		},
		
		
		description: _zkf = function () {
			this.rerender();
		},
		
		
		content: _zkf
	},

	
	domLabel_: function () {
		return zUtl.encodeXML(this.getLabel(), {pre: 1});
	},
	doClick_: function (evt) {
		if (!this._disabled) {

			var cb = this.parent;
			cb._select(this, {sendOnSelect:true, sendOnChange: true});
			this._updateHoverImage();
			cb.close({sendOnOpen:true, focus:true});
			
			
			cb._shallClose = true;
			if (zul.inp.InputCtrl.isPreservedFocus(this))
				zk(cb.getInputNode()).focus();
			evt.stop();
		}
	},
	domClass_: function (no) {
		var scls = this.$supers('domClass_', arguments);
		if (this._disabled && (!no || !no.zclass)) {
			scls += ' ' + this.$s('disabled');
		}
		return scls;
	},
	deferRedrawHTML_: function (out) {
		out.push('<li', this.domAttrs_({domClass:1}), ' class="z-renderdefer"></li>');
	}
});
