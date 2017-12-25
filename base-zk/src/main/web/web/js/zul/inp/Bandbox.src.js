

zul.inp.Bandbox = zk.$extends(zul.inp.ComboWidget, {
	
	getPopupSize_: function (pp) {
		var bp = this.firstChild, 
			w, h;
		if (bp) {
			w = bp._hflex == 'min' && bp._hflexsz ? jq.px0(bp._hflexsz + zk(pp).padBorderWidth()) : bp.getWidth();
			h = bp._vflex == 'min' && bp._vflexsz ? jq.px0(bp._vflexsz + zk(pp).padBorderHeight()) : bp.getHeight();
		}
		return [w||'auto', h||'auto'];
	},
	getCaveNode: function () {
		return this.$n('pp') || this.$n();
	},
	redrawpp_: function (out) {
		out.push('<div id="', this.uuid, '-pp" class="', this.$s('popup'),
		'" style="display:none">');

		for (var w = this.firstChild; w; w = w.nextSibling)
			w.redraw(out);
	
		out.push('</div>');
	},
	
	getIconClass_: function () {
		return 'z-icon-search';
	},
	open: function (opts) {
		if (!this.firstChild) { 
			
			if (opts && opts.sendOnOpen)
				this.fire('onOpen', {open:true, value: this.getInputNode().value}, {rtags: {onOpen: 1}});
			return;
		}
		this.$supers('open', arguments);
	},
	presize_: function () {
		var bp = this.firstChild;
		if (bp && (bp._hflex == 'min' || bp._vflex == 'min')) {
			zWatch.fireDown('onFitSize', bp, {reverse: true});	
			return true;
		}
	},
	enterPressed_: function (evt) {
		
		if(evt.domTarget == this.getInputNode())
			this.$supers('enterPressed_', arguments);
	},
	doKeyUp_: function(evt) {
		
		if(evt.domTarget == this.getInputNode())
			this.$supers('doKeyUp_', arguments);
	}
});
