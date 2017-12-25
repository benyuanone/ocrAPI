
(function () {
	function _digitsAfterDecimal(v) {
		var vs = '' + v,
			i = vs.indexOf('.');
		return i < 0 ? 0 : vs.length - i - 1;
	}
	function _roundDecimal(v, p) {
		var mul = Math.pow(10, p);
		return Math.round(v * mul) / mul;
	}
	function _getBtnNewPos(wgt) {
		var btn = wgt.$n('btn'),
			curpos = wgt._curpos,
			isDecimal = wgt.isDecimal();
		
		btn.title = isDecimal ? curpos.toFixed(_digitsAfterDecimal(_getStep(wgt))) : curpos;
		wgt.updateFormData(curpos);
		
		var isVertical = wgt.isVertical(),
			ofs = zk(wgt.$n()).cmOffset(),
			totalLen = isVertical ? wgt._getHeight(): wgt._getWidth(), 
			x = totalLen > 0 ? ((curpos - wgt._minpos) * totalLen) / (wgt._maxpos - wgt._minpos) : 0;
		if(!isDecimal)
			x = Math.round(x);
	
		ofs = zk(btn).toStyleOffset(ofs[0], ofs[1]);
		ofs = isVertical ? [0, (ofs[1] + x)]: [(ofs[0] + x), 0];
		ofs = wgt._snap(ofs[0], ofs[1]);
		
		return ofs[(isVertical ? 1: 0)];
	}
	function _getNextPos(wgt, offset) {
		var $btn = jq(wgt.$n('btn')),
			fum = wgt.isVertical()? ['top', 'height']: ['left', 'width'],
			newPosition = {};
		
		newPosition[fum[0]] = jq.px0(offset ? 
			(offset + zk.parseInt($btn.css(fum[0])) - $btn[fum[1]]() / 2):
			_getBtnNewPos(wgt));
		
		return newPosition;
	}
	function _getStep(wgt) {
		var step = wgt._step;
		return (!wgt.isDecimal() || step != -1) ? step : 0.1;
	}
	

zul.inp.Slider = zk.$extends(zul.Widget, {
	_orient: 'horizontal',
	_height: '200px',
	_width: '200px',
	_curpos: 0,
	_minpos: 0,
	_maxpos: 100,
	_slidingtext: '{0}',
	_pageIncrement: -1,
	_step: -1,
	_mode: 'integer',
	
	$define: {
		
		
		orient: function() {
			this.rerender();
		},
		
		
		curpos: function () {
			if (this.desktop) {
				this._fixPos();
			}
		},
		
		
		minpos: function(minpos) {
			if (this._curpos < minpos) {
				this._curpos = minpos;
			}
			this._fixStep();
			if (this.desktop)
				this._fixPos();
		},
		
		
		maxpos: function(maxpos) {
			if (this._curpos > maxpos) {
				this._curpos = maxpos;
			}
			this._fixStep();
			if (this.desktop)
				this._fixPos();
		},
		
		
		slidingtext: null,
		
		
		pageIncrement: null,
		
		
		step: function () {
			this._fixStep();
		},
		
		
		name: function() {
			if (this.efield) 
				this.efield.name = this._name;
		},
		
		
		mode: function () {
			this._fixStep();
			if(this.desktop) {
				this._fixPos();
			}
		}
	},
	domClass_: function() {
		var scls = this.$supers('domClass_', arguments),
			isVertical = this.isVertical();
		if (isVertical)
			scls += ' ' + this.$s('vertical');
		else
			scls += ' ' + this.$s('horizontal');
		if (this.inSphereMold())
            scls += ' ' + this.$s('sphere');
		else if (this.inScaleMold() && !isVertical) 
			scls += ' ' + this.$s('scale');
		
		return scls;
	},
	onup_: function(evt) {
		var btn = zul.inp.Slider.down_btn, widget;
		if (btn) {
			widget = zk.Widget.$(btn);
		}
		
		zul.inp.Slider.down_btn = null;
		if (widget)
			jq(document).unbind('zmouseup', widget.onup_);
	},
	doMouseDown_: function(evt) {
		jq(document).bind('zmouseup', this.onup_);
		zul.inp.Slider.down_btn = this.$n('btn');
		this.$supers('doMouseDown_', arguments);
	},
	doClick_: function(evt) {
		var $btn = jq(this.$n('btn')),
			pos = $btn.zk.revisedOffset(),
			wgt = this,
			pageIncrement = this._pageIncrement,
			moveToCursor = pageIncrement < 0 && _getStep(this) < 0,
			isVertical = this.isVertical(),
			height = this._getHeight(),
			width = this._getWidth(),
			offset = isVertical ? evt.pageY - pos[1]: evt.pageX - pos[0];
		
		if (!$btn[0] || $btn.is(':animated')) return;
		
		if (!moveToCursor) {
			if (pageIncrement > 0) {
				var curpos = this._curpos + (offset > 0 ? pageIncrement: - pageIncrement);
				this._curpos = _roundDecimal(this._constraintPos(curpos), _digitsAfterDecimal(pageIncrement));
			} else {
				var total = isVertical ? height : width,
					to = (offset / total) * (this._maxpos - this._minpos);
				this._curpos = this._getSteppedPos(to + this._curpos);
			}
			offset = null; 
		}
		
		var nextPos = _getNextPos(this, offset);
		if (isVertical && zk.parseInt(nextPos.top) > height)
			nextPos.top = jq.px0(height);
		if (!isVertical && zk.parseInt(nextPos.left) > width)
			nextPos.left = jq.px0(width);
		$btn.animate(nextPos, 'slow', function() {
			pos = moveToCursor ? wgt._realpos(): wgt._curpos;
			pos = wgt._constraintPos(pos);
			wgt.fire('onScroll', wgt.isDecimal() ? {decimal: pos} : pos);
			if (moveToCursor)
				wgt._fixPos();
		});
		this.$supers('doClick_', arguments);
	},
	_makeDraggable: function() {
		var opt = {
				constraint: this._orient || 'horizontal',
				starteffect: this._startDrag,
				snap: opt,
				change: this._dragging,
				endeffect: this._endDrag
				};
		if (_getStep(this) > 0)
			opt.snap = this._getStepOffset();
		this._drag = new zk.Draggable(this, this.$n('btn'), opt);
	},
	_snap: function(x, y) {
		var btn = this.$n('btn'), ofs = zk(this.$n()).cmOffset();
		ofs = zk(btn).toStyleOffset(ofs[0], ofs[1]);
		if (x <= ofs[0]) {
			x = ofs[0];
		} else {
			var max = ofs[0] + this._getWidth();
			if (x > max) 
				x = max;
		}
		if (y <= ofs[1]) {
			y = ofs[1];
		} else {
			var max = ofs[1] + this._getHeight();
			if (y > max) 
				y = max;
		}
		return [x, y];
	},
	_startDrag: function(dg) {
		var widget = dg.control;
		widget.$n('btn').title = ''; 
		widget.slidepos = widget._curpos,
		vert = widget.isVertical();
		
		jq(document.body)
			.append('<div id="zul_slidetip" class="z-slider-popup"'
			+ 'style="position:absolute;display:none;z-index:60000;'
			+ 'background-color:white;border: 1px outset">' + widget.slidepos +
			'</div>');
		
		widget.slidetip = jq('#zul_slidetip')[0];
		if (widget.slidetip) {
			var slideStyle = widget.slidetip.style;
			if (zk.webkit) { 
				slideStyle.top = '0px';
				slideStyle.left = '0px';
			}
			slideStyle.display = 'block';
			zk(widget.slidetip).position(widget.$n(), vert ? 'end_before' : 'after_start');
		}
	},
	_dragging: function(dg) {
		var widget = dg.control,
			isDecimal = widget.isDecimal(),
			pos = widget._realpos();
		if (pos != widget.slidepos) {
			widget.slidepos = pos = widget._constraintPos(pos);
			var text = isDecimal ? pos.toFixed(_digitsAfterDecimal(_getStep(widget))) : pos;
			if (widget.slidetip) 
				widget.slidetip.innerHTML = widget._slidingtext.replace(/\{0\}/g, text);
			widget.fire('onScrolling', isDecimal ? {decimal: pos} : pos);
		}
		widget._fixPos();
	},
	_endDrag: function(dg) {
		var widget = dg.control,
			pos = widget._realpos();
		
		widget.fire('onScroll', widget.isDecimal() ? {decimal: pos} : pos);
		
		widget._fixPos();
		jq(widget.slidetip).remove();
		widget.slidetip = null;
	},
	_realpos: function(dg) {
		var btnofs = zk(this.$n("btn")).revisedOffset(),
			refofs = zk(this.$n()).revisedOffset(),
			maxpos = this._maxpos,
			minpos = this._minpos,
			step = _getStep(this),
			pos;
		if (this.isVertical()) {
			var ht = this._getHeight();
			pos = ht ? ((btnofs[1] - refofs[1]) * (maxpos - minpos)) / ht : 0;
		} else {
			var wd = this._getWidth();
			pos = wd ? ((btnofs[0] - refofs[0]) * (maxpos - minpos)) / wd : 0;
		}
		if (!this.isDecimal())
			pos = Math.round(pos);
		if (step > 0) {
			return this._curpos = pos > 0 ? _roundDecimal(pos + minpos, _digitsAfterDecimal(step)) : minpos;
		}
		else 
			return this._curpos = (pos > 0 ? pos : 0) + minpos;
	},
	_constraintPos: function(pos) {
		return pos < this._minpos ? this._minpos : (pos > this._maxpos ? this._maxpos : pos);
	},
	_getSteppedPos: function(pos) {
		var minpos = this._minpos,
			step = _getStep(this),
			mul = 1,
			rmdPos;
		pos -= minpos;
		if (this.isDecimal()) {
			mul = Math.pow(10, _digitsAfterDecimal(step));
			pos *= mul;
			step *= mul
		}
		rmdPos = pos % step;
		return (pos - rmdPos + Math.round((rmdPos) / step) * step) / mul + minpos;
	},
	_getWidth: function() {
		return this.$n().clientWidth - this.$n('btn').offsetWidth;
	},
	_getHeight: function() {
		return this.$n().clientHeight - this.$n('btn').offsetHeight;
	},
	_getStepOffset: function() {
		var totalLen = this.isVertical() ? this._getHeight(): this._getWidth(),
			step = _getStep(this),
			ofs = [0, 0];
		if (step)
			ofs[(this.isVertical() ? 1: 0)] = totalLen > 0 ? totalLen * step / (this._maxpos - this._minpos) : 0;
		return ofs;
	},
	_fixSize: function() {
		var n = this.$n(),
			btn = this.$n('btn'),
			inners = this.$n('inner').style;
		if (this.isVertical()) {
			btn.style.top = '-' + btn.offsetHeight / 2 + 'px';
			var het = n.clientHeight;
			inners.height = jq.px0(het > 0 ? het : this._height - btn.offsetHeight);
		} else { 
			btn.style.left = '-' + btn.offsetWidth / 2 + 'px';
			var wd = n.clientWidth;
			inners.width = jq.px0(wd > 0 ? wd : this._width - btn.offsetWidth);
		}
	},
	_fixPos: function() {
		this.$n('btn').style[this.isVertical()? 'top': 'left'] = jq.px0(_getBtnNewPos(this));
	},
	_fixStep: function() {
		var step = _getStep(this);
		if (this._drag) {
			if (step <= 0) {
				if(this._drag.opts.snap)
					delete this._drag.opts.snap;
			} else
				this._drag.opts.snap = this._getStepOffset();
		}
	},
	onSize: function() {
		this._fixSize();
		this._fixPos();
	},
	
	inScaleMold: function() {
		return this.getMold() == 'scale';
	},
	
	inSphereMold: function() {
		return this.getMold() == 'sphere';
	},
	
	isVertical: function() {
		return 'vertical' == this._orient;
	},
	
	isDecimal: function() {
		return 'decimal' == this._mode;
	},
	updateFormData: function(val) {
		if (this._name) {
			val = val || 0;
			if (!this.efield) 
				this.efield = jq.newHidden(this._name, val, this.$n());
			else 
				this.efield.value = val;
		}
	},
	onShow: function () {
		
		
		if (!this._drag) {
			this._makeDraggable();
		}
	},
	bind_: function() {
		this.$supers(zul.inp.Slider, 'bind_', arguments);
		this._fixSize();
		
		if(this.isRealVisible())
			this._makeDraggable();
		
		zWatch.listen({
			onSize: this,
			
			onShow: this
		});
		this.updateFormData(this._curpos);
		this._fixPos();
	},
	unbind_: function() {
		this.efield = null;
		if (this._drag) {
			this._drag.destroy();
			this._drag = null;
		}
		
		zWatch.unlisten({
			onSize: this,
			
			onShow: this
		});
		this.$supers(zul.inp.Slider, 'unbind_', arguments);
	}
});
})();