



(function () {

	var _ambit = {
		'north': function (ambit, center, width, height) {
			ambit.w = width - ambit.w;
			center.y = ambit.ts;
			center.h -= ambit.ts;
		},
		'south': function (ambit, center, width, height) {
			ambit.w = width - ambit.w;
			ambit.y = height - ambit.y;
			center.h -= ambit.ts;
		},
		'east': function (ambit, center, width) {
			ambit.y += center.y;
			ambit.h = center.h - ambit.h;
			ambit.x = width - ambit.x;
			center.w -= ambit.ts;
		},
		'west': function (ambit, center) {
			ambit.y += center.y;
			ambit.h = center.h - ambit.h;
			center.x += ambit.ts;
			center.w -= ambit.ts;
		}
	};
	
	function _getRegionSize (wgt, hor, ext) {
		if (!wgt)
			return 0;
		var n = wgt.$n('real'),
			sz = hor ? 'offsetWidth' : 'offsetHeight',
			sum = n[sz];
		if (ext) {
			var cn = wgt.$n('colled'),
				sn = wgt.$n('split');
			if (cn)
				sum += cn[sz];
			if (sn)
				sum += sn[sz];
		}
		return sum;
	}

var Borderlayout =

zul.layout.Borderlayout = zk.$extends(zul.Widget, {
	setResize: function () {
		this.resize();
	},
	
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		var BL = zul.layout.Borderlayout;
		switch (child.getPosition()) {
		case BL.NORTH:
			this.north = child;
			break;
		case BL.SOUTH:
			this.south = child;
			break;
		case BL.CENTER:
			this.center = child;
			break;
		case BL.WEST:
			this.west = child;
			break;
		case BL.EAST:
			this.east = child;
			break;
		}
		this._shallResize = true;
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);
		if (child == this.north)
			this.north = null;
		else if (child == this.south)
			this.south = null;
		else if (child == this.center)
			this.center = null;
		else if (child == this.west)
			this.west = null;
		else if (child == this.east)
			this.east = null;
		if (!this.childReplacing_)
			this._shallResize = true;
	},
	bind_: function () {
		this.$supers(Borderlayout, 'bind_', arguments);
		zWatch.listen({onSize: this, onCommandReady: this});
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this, onCommandReady: this});
		this.$supers(Borderlayout, 'unbind_', arguments);
	},
	onCommandReady: function () {
		if (this._shallResize)
			this.resize();
	},
	beforeMinFlex_: function (o) {
		
		var east = this.east,
			west = this.west,
			north = this.north,
			south = this.south,
			center = this.center;
		return o == 'w' ?
			Math.max(
				_getRegionSize(north, true), _getRegionSize(south, true),
				_getRegionSize(east, true, true) + _getRegionSize(west, true, true) +
				_getRegionSize(center, true)):
			_getRegionSize(north, false, true) + 
				_getRegionSize(south, false, true) +
				Math.max(
					_getRegionSize(east), _getRegionSize(west),
					_getRegionSize(center));
	},
	
	afterChildrenFlex_: function () {
		
		
		if (this._isOnSize)
			this._resize(true);
	},
	
	resize: function () {
		if (this.desktop)
			this._resize();
	},
	_resize: function (isOnSize) {
		this._shallResize = false;
		this._isOnSize = isOnSize;
		if (!this.isRealVisible()) return;

		
		var rs = ['north', 'south', 'west', 'east'], k = rs.length; 
		for (var region, j = 0; j < k; ++j) {
			region = this[rs[j]];
			if (region && zk(region.$n()).isVisible()
				&& ((region._nvflex && region._vflexsz === undefined) 
						|| (region._nhflex && region._hflexsz === undefined)))
				return;	
						
						
						
						
		}

		var el = this.$n(),
			width = el.offsetWidth,
			height = el.offsetHeight,
			center = { 
				x: 0,
				y: 0,
				w: width,
				h: height
			};
		
		
		if (zk.opera && !height && (!el.style.height || el.style.height == '100%')) {
			var parent = el.parentNode;
			center.h = height = parent.offsetHeight;
		}
		
		for (var region, ambit, margin,	j = 0; j < k; ++j) {
			region = this[rs[j]];
			if (region && zk(region.$n()).isVisible()) {
				ambit = region._ambit();
				_ambit[rs[j]](ambit, center, width, height);
				this._resizeWgt(region, ambit); 
			}
		}
		if (this.center && zk(this.center.$n()).isVisible()) {
			var mars = this.center.getCurrentMargins_();
			center.x += mars.left;
			center.y += mars.top;
			center.w -= mars.left + mars.right;
			center.h -= mars.top + mars.bottom;
			this._resizeWgt(this.center, center); 
		}
		this._isOnSize = false; 
	},
	_resizeWgt: function (wgt, ambit, ignoreSplit) {
		if (wgt._open) {
			if (!ignoreSplit && wgt.$n('split')) {
				wgt._fixSplit();
				ambit = wgt._reszSplt(ambit);
			}
			zk.copy(wgt.$n('real').style, {
				left: jq.px(ambit.x),
				top: jq.px(ambit.y)
			});
			this._resizeBody(wgt, ambit);
		} else {
			wgt.$n('split').style.display = 'none';
			var colled = wgt.$n('colled');
			if (colled) {
				var $colled = zk(colled);
				zk.copy(colled.style, {
					left: jq.px(ambit.x),
					top: jq.px(ambit.y),
					width: jq.px0(ambit.w),
					height: jq.px0(ambit.h)
				});
			}
			
			var real = wgt.$n('real');
			if (real && zk(real).isVisible()) {
				var isSouch = wgt.$instanceof(zul.layout.South),
					isEast = wgt.$instanceof(zul.layout.East);
				if (isSouch || wgt.$instanceof(zul.layout.North)) {
					ambit.h = real.offsetHeight;
					if (isSouch)
						real.style.top = jq.px(ambit.y - ambit.h);
				}
				if (isEast || wgt.$instanceof(zul.layout.West)) {
					ambit.w = real.offsetWidth;
					if (isEast)
						real.style.left = jq.px(ambit.x - ambit.w);
				}
				this._resizeBody(wgt, ambit);
			}
		}
	},
	_resizeBody: function (wgt, ambit) {
		ambit.w = Math.max(0, ambit.w);
		ambit.h = Math.max(0, ambit.h);
		var el = wgt.$n('real');
		if (!this._ignoreResize(el, ambit.w, ambit.h)) {
			var fchild = wgt.isFlex() && wgt.getFirstChild(),
				bodyEl = fchild ? wgt.getFirstChild().$n() : wgt.$n('cave'),
				bs = bodyEl.style,
				$el = zk(el);
			
			el.style.width = jq.px0(ambit.w);
			bs.width = jq.px0($el.contentWidth());
			el.style.height = jq.px0(ambit.h);
			if (wgt.$n('cap'))
				ambit.h = Math.max(0, ambit.h - wgt.$n('cap').offsetHeight);
			
			
			if (fchild) { 
				var cv;
				if (cv = wgt.$n('cave'))
					cv.style.height = jq.px0(ambit.h - $el.padBorderHeight());
			}
			bs.height = jq.px0(ambit.h - $el.padBorderHeight());
			if (wgt._nativebar && wgt.isAutoscroll()) { 
				bs.overflow = 'auto';
				bs.position = 'relative';
			}
			if (!this._isOnSize)
				zUtl.fireSized(wgt);
		}
	},
	_ignoreResize : function(el, w, h) { 
		if (el._lastSize && el._lastSize.width == w && el._lastSize.height == h) {
			return true;
		}
		
		
		el._lastSize = {width: w, height: h};
		return false;
	},
	
	onSize: function () {
		this._resize(true);
	}
}, {
	
	NORTH: 'north',
	
	SOUTH: 'south',
	
	EAST: 'east',
	
	WEST: 'west',
	
	CENTER: 'center'
});

})();