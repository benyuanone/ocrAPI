
var North =

zul.layout.North = zk.$extends(_zkf = zul.layout.LayoutRegion, {
	_sumFlexHeight: true, 
	
	setWidth: zk.$void, 
	sanchor: 't',

	$init: function () {
		this.$supers('$init', arguments);
		this._cmargins = [3, 0, 0, 3];
	},
	
	getPosition: function () {
		return zul.layout.Borderlayout.NORTH;
	},
	
	getSize: function () {
		
		return this.$supers(North, 'getHeight', arguments);
	},
	
	setSize: function () {
		return this.$supers(North, 'setHeight', arguments);
	},

	_ambit2: function (ambit, mars, split) {
		ambit.w = mars.left + mars.right;
		ambit.h += split.offsetHeight;
		ambit.ts = ambit.y + ambit.h + mars.bottom; 
	},
	_reszSp2: function (ambit, split) {
		ambit.h -= split.h;
		return {
		  	left: jq.px0(ambit.x),
			top: jq.px0(ambit.y + ambit.h),
			width: jq.px0(ambit.w)
		};
	}
});