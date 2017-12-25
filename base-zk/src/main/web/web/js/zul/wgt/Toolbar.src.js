

zul.wgt.Toolbar = zk.$extends(zul.Widget, {
	_orient: 'horizontal',
	_align: 'start',

	$define: {
		
		
		align: _zkf = function () {
			this.rerender();
		},
		
		
		orient: _zkf
	},
	
	domClass_: function (no) {
		var sc = this.$supers('domClass_', arguments);
		if (!no || !no.zclass) {
			var tabs = this.parent && zk.isLoaded('zul.tab') && this.parent.$instanceof(zul.tab.Tabbox) ? this.$s('tabs') : '';
				
			if (tabs)
				sc += ' ' + tabs;
			if (this.inPanelMold())
				sc += ' ' + this.$s('panel');
		}
		return sc;
	},
	
	
	setFlexSizeW_: function(n, zkn, width, isFlexMin) {
		this.$supers('setFlexSizeW_', arguments);
		if (!isFlexMin && this.getAlign() == 'start') {
			var cave = this.$n('cave');
			if (cave)
				cave.style.width = jq.px0(zk(this.$n()).contentWidth());
		}
	},
	
	inPanelMold: function(){
		return this._mold == 'panel';
	},
	
	onChildAdded_: function(){
		this.$supers('onChildAdded_', arguments);
		if (this.inPanelMold()) 
			this.rerender();
	},
	onChildRemoved_: function(){
		this.$supers('onChildRemoved_', arguments);
		if (!this.childReplacing_ && this.inPanelMold())
			this.rerender();
	}	
});
