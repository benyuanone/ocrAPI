

zul.wgt.Progressmeter = zk.$extends(zul.Widget, {
	_value: 0,

	$define: {
		
		
		value: function () {
			if(this.$n()) 
				this._fixImgWidth();
		}
	},

	
	_fixImgWidth: _zkf = function() {
		var n = this.$n(), 
			img = this.$n('img');
		if (img) {
			
			if (zk(n).isRealVisible()) { 
				var $img = jq(img);
				$img.animate({
					width: Math.round((jq(n).innerWidth() * this._value) / 100) + 'px'
				}, $img.zk.getAnimationSpeed('slow'));
			}
		}
	},
	onSize: _zkf,
	bind_: function () {
		this.$supers(zul.wgt.Progressmeter, 'bind_', arguments); 
		this._fixImgWidth(this._value);
		zWatch.listen({onSize: this});
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this});
		this.$supers(zul.wgt.Progressmeter, 'unbind_', arguments);
	},
	setWidth : function (val){
		this.$supers('setWidth', arguments);
		this._fixImgWidth();
	}
});

