



(function () {

	
	function _spacing0(spacing) {
		return spacing && spacing.startsWith('0') && !zk.parseInt(spacing);
	}
	function _spacingHTML(box, child) {
		var oo = [],
			spacing = box._spacing,
			spacing0 = _spacing0(spacing),
			vert = box.isVertical(),
			spstyle = spacing && spacing != 'auto' ? (vert?'height:':'width:') + spacing: '';

		oo.push('<t', vert?'r':'d', ' id="', child.uuid,
			'-chdex2" class="', box.$s('separator'), '"');

		var s = spstyle;
		if (spacing0 || !child.isVisible()) s = 'display:none;' + s;
		if (s) oo.push(' style="', s, '"');

		oo.push('>', vert?'<td>':'', zUtl.img0, vert?'</td></tr>':'</td>');
		return oo.join('');
	}

	
	function _fixTd() {
		
		var vert = this.isVertical();
		if (this._isStretchAlign() || (vert && this._nhflex) || (!vert && this._nvflex)) {
			for(var child = this.firstChild; child; child = child.nextSibling) {
				if (child.isVisible()) {
					var c = child.$n();
					if (vert) {
						if (child._nhflex && child._nhflex > 0) 
							child.setFlexSize_({width:'auto'});
						else if (c && this._isStretchAlign()) {
									 
									 
							var oldwidth= c.style.width;
							if (oldwidth) {
								var oldoffwidth= c.offsetWidth;
								c.style.width= ''; 
								if (c.offsetWidth > oldoffwidth)
									c.style.width= oldwidth;
							}
						}
						if (!child.$instanceof(zul.wgt.Cell) && this._nhflex) {
							var chdex = child.$n('chdex');
							chdex.style.width = '';
						}
					} else {
						if (child._nvflex && child._nvflex > 0) 
							child.setFlexSize_({height:'auto'});
						else if (c && this._isStretchAlign()) {
									 
									 
							var oldheight= c.style.height;
							if (oldheight) {
								var oldoffheight = c.offsetHeight;
								c.style.height= ''; 
								if (c.offsetHeight > oldoffheight)
									c.style.height= oldheight;
							}
						}
						if (!child.$instanceof(zul.wgt.Cell) && this._nvflex) {
							var chdex = child.$n('chdex');
							chdex.style.height = '';
						}
					}
				}
			}
		}
		
		
		var nh;
		if (zk.webkit && !vert && (nh = this.$n().style.height)) {
			var td = this.$n('frame');
			td.style.height = '';
			td.style.height = nh.indexOf('%') > 0 ? 
				jq.px0(td.offsetHeight) : nh; 
		}
	}

var Box =

zul.box.Box = zk.$extends(zul.Widget, {
	_mold: 'vertical',
	_align: 'start',
	_pack: 'start',
	_sizedByContent: true,

	$define: {
		
		
		align:  _zkf = function () {
			this.rerender(); 
		},
		
		
		pack: _zkf,
		
		
		spacing: _zkf,
		
		
		sizedByContent: _zkf,
		widths: _zkf = function (val) {
		    this._sizes = val;
		    this.rerender();
		}
	},
	setHeights: function (val) {
		this.setWidths(val);
	},
	getHeights: function () {
		return this.getWidths();
	},
	
	isVertical: function () {
		return 'vertical' == this._mold;
	},
	
	getOrient: function () {
		return this._mold;
	},

	
	getZclass: function () {
		var zcs = this._zclass;
		return zcs != null ? zcs: this.isVertical() ? 'z-vbox' : 'z-hbox';
	},

	onChildVisible_: function (child) {
		this.$supers('onChildVisible_', arguments);
		if (this.desktop) this._fixChildDomVisible(child, child._visible);
	},
	replaceChildHTML_: function (child) {
		this.$supers('replaceChildHTML_', arguments);
		this._fixChildDomVisible(child, child._visible);
		if (child.$instanceof(zul.box.Splitter)) {
			var n = this._chdextr(child);
			if (n) {
				n.style.height = '';
				n.style.width = '';
			}
			zUtl.fireSized(this, -1); 
		}
	},
	_fixChildDomVisible: function (child, visible) {
		var n = this._chdextr(child);
		if (n) n.style.display = visible ? '': 'none';
		n = child.$n('chdex2');
		if (n) n.style.display = visible && !_spacing0(this._spacing) ? '': 'none';

		if (this.lastChild == child) {
			n = child.previousSibling;
			if (n) {
				n = n.$n('chdex2');
				if (n) n.style.display = visible ? '': 'none';
			}
		}
	},
	_chdextr: function (child) {
		return child.$n('chdex') || child.$n();
	},
	insertChildHTML_: function (child, before, desktop) {
		if (before) {
			jq(this._chdextr(before)).before(this.encloseChildHTML_(child));
		} else {
			var n = this.$n('real'), tbs = n.tBodies;
			if (!tbs || !tbs.length)
				n.appendChild(document.createElement('tbody'));
			jq(this.isVertical() ? tbs[0]: tbs[0].rows[0]).append(
				this.encloseChildHTML_(child, true));
		}
		child.bind(desktop);
	},
	removeChildHTML_: function (child) {
		this.$supers('removeChildHTML_', arguments);
		jq(child.uuid + '-chdex', zk).remove();
		jq(child.uuid + '-chdex2', zk).remove();
		var sib;
		if (this.lastChild == child && (sib = child.previousSibling)) 
			jq(sib.uuid + '-chdex2', zk).remove();
	},
	
	encloseChildHTML_: function (child, prefixSpace, out) {
		var oo = [],
			isCell = child.$instanceof(zul.wgt.Cell);
		if (this.isVertical()) {
			oo.push('<tr id="', child.uuid, '-chdex"',
				this._childOuterAttrs(child), '>');
				
			if (!isCell) {
				oo.push('<td', this._childInnerAttrs(child));
				
				var v = this.getAlign();
				if (v && v != 'stretch') oo.push(' align="', zul.box.Box._toHalign(v), '"');
				oo.push('>');
			}
				
			child.redraw(oo);
			
			if (!isCell) oo.push('</td>');
			
			oo.push('</tr>');

		} else {
			if (!isCell) {
				oo.push('<td id="', child.uuid, '-chdex"',
				this._childOuterAttrs(child),
				this._childInnerAttrs(child),
				'>');
			}
			child.redraw(oo);
			if (!isCell)
				oo.push('</td>');
		}
		var next = child.nextSibling; 
		if (next && !next.$instanceof(zul.wgt.Popup))
			oo.push(_spacingHTML(this, child));
		else if (prefixSpace) {
			var pre = child.previousSibling;
			if (pre) oo.unshift(_spacingHTML(this, pre));
		}
		
		if (!out) return oo.join('');

		for (var j = 0, len = oo.length; j < len; ++j)
			out.push(oo[j]);
	},
	_resetBoxSize: function (vert) {
		var	vert = this.isVertical(),
			k = -1,
			szes = this._sizes;
		
		if (!zk.mounting) {
			if (vert) {
				for (var kid = this.firstChild; kid; kid = kid.nextSibling) {
					if (szes && !kid.$instanceof(zul.box.Splitter) && !kid.$instanceof(zul.wgt.Cell))
						++k;
					if (kid._nvflex && kid.getVflex() != 'min') {
						kid.setFlexSize_({height:'', width:''});
						var chdex = kid.$n('chdex');
						if (chdex) {
							var n;
							if ((n = kid.$n()) && (n.scrollTop || n.scrollLeft)) 
								;
							else {
								chdex.style.height = szes && k < szes.length ? szes[k] : '';
								chdex.style.width = '';
							}
						}
					}
				}
			} else {
				for (var kid = this.firstChild; kid; kid = kid.nextSibling) {
					if (szes && !kid.$instanceof(zul.box.Splitter) && !kid.$instanceof(zul.wgt.Cell))
						++k;
					if (kid._nhflex && kid.getHflex() != 'min') {
						kid.setFlexSize_({height:'', width:''});
						var chdex = kid.$n('chdex');
						if (chdex) {
							var n;
							if ((n = kid.$n()) && (n.scrollTop || n.scrollLeft)) 
								;
							else {
								chdex.style.width = szes && k < szes.length ? szes[k] : '';
								chdex.style.height = '';
							}
						}
					}
				}
			}
		}
	},
	
	afterResetChildSize_: function () {
		for (var kid = this.firstChild, vert = this.isVertical(); kid; kid = kid.nextSibling) {				
			
			if (kid.desktop) { 
				var chdex = vert ? kid.$n('chdex').firstChild : kid.$n('chdex');
				if (chdex)
					chdex.style.minWidth = '1px';
			}
		}
	},
	
	resetSize_: function (orient) { 
		this.$supers(zul.Widget, 'resetSize_', arguments);
		var	vert = this.isVertical(),
		k = -1,
		szes = this._sizes;
		if (vert) {
			for (var kid = this.firstChild; kid; kid = kid.nextSibling) {
				if (szes && !kid.$instanceof(zul.box.Splitter) && !kid.$instanceof(zul.wgt.Cell))
					++k;
				if (kid._nvflex && kid.getVflex() != 'min') {
					var chdex = kid.$n('chdex');
					if (chdex) {
						if (orient == 'h')
							chdex.style.height = szes && k < szes.length ? szes[k] : '';
						if (orient == 'w')
							chdex.style.width = '';
					}
				}
			}
		} else {
			for (var kid = this.firstChild; kid; kid = kid.nextSibling) {
				if (szes && !kid.$instanceof(zul.box.Splitter) && !kid.$instanceof(zul.wgt.Cell))
					++k;
				if (kid._nhflex && kid.getHflex() != 'min') {
					var chdex = kid.$n('chdex');
					if (chdex) {
						if (orient == 'w')
							chdex.style.width = szes && k < szes.length ? szes[k] : '';
						if (orient == 'h')
							chdex.style.height = '';
					}
				}
			}
		}
	},
	_getContentSize: function () {
		
		var p = this.$n(),
			zkp = zk(p),
			hgh = this._vflexsz !== undefined ? 
					this._vflexsz - zkp.padBorderHeight() - zkp.marginHeight()
					
					: zkp.contentHeight(true),
			wdh = this._hflexsz !== undefined ?
					this._hflexsz - zkp.padBorderWidth() - zkp.marginWidth()
					
					: zkp.contentWidth(true);
		return zkp ? {height: hgh, width: wdh} : {height: 0, width: 0};
	},
	beforeChildrenFlex_: function(child) {
		child._flexFixed = true;
		
		var	vert = this.isVertical(),
			vflexs = [],
			vflexsz = vert ? 0 : 1,
			hflexs = [],
			hflexsz = !vert ? 0 : 1,
			chdex = child.$n('chdex'), 
			p = chdex ? chdex.parentNode : child.$n().parentNode,
			zkp = zk(this.$n()),
			psz = this._getContentSize(),
			hgh = psz.height,
			wdh = psz.width,
			xc = p.firstChild,
			k = -1,
			szes = this._sizes,
			scrWdh;

		if (!zk.mounting) { 
			this._resetBoxSize(vert);
		}

		
		if(zkp.hasVScroll()) 
			wdh -= (scrWdh = jq.scrollbarWidth());
			
		
		if(zkp.hasHScroll()) 
			hgh -= scrWdh || jq.scrollbarWidth();
		
		for (; xc; xc = xc.nextSibling) {
			var c = xc.id && xc.id.endsWith('-chdex') ? vert ? 
					xc.firstChild.id ? xc.firstChild: xc.firstChild.firstChild : xc.firstChild : xc;

			
			for (; c; c = c.nextSibling)
				if (c.nodeType != 3) break; 
				
				zkc = zk(c),
				fixedSize = false;
			if (zkc.isVisible()) {
				var j = c.id ? c.id.indexOf('-') : 1,
						cwgt = j < 0 ? zk.Widget.$(c.id) : null;

				if (szes && cwgt && !cwgt.$instanceof(zul.box.Splitter) && !cwgt.$instanceof(zul.wgt.Cell)) {
					++k;
					if (k < szes.length && szes[k] && ((vert && !cwgt._nvflex) || (!vert && !cwgt._nhflex))) {
						c = xc;
						zkc = zk(c);
						fixedSize = szes[k].endsWith('px');
					}
				}
				var offhgh = fixedSize && vert ? zk.parseInt(szes[k]) : 
						zk.ie < 11 && xc.id && xc.id.endsWith('-chdex2') && xc.style.height && xc.style.height.endsWith('px') ? 
						zk.parseInt(xc.style.height) : zkc.offsetHeight(),
					offwdh = fixedSize && !vert ? zk.parseInt(szes[k]) : zkc.offsetWidth(),
					cwdh = offwdh + zkc.marginWidth(),
					chgh = offhgh + zkc.marginHeight();
				
				
				if (cwgt && cwgt._nvflex) {
					if (cwgt !== child)
						cwgt._flexFixed = true; 
					if (cwgt._vflex == 'min') {
						cwgt.fixMinFlex_(c, 'h');
						if (vert) hgh -= chgh;
					} else {
						vflexs.push(cwgt);
						if (vert) vflexsz += cwgt._nvflex;
					}
				} else if (vert) hgh -= chgh;
				
				
				if (cwgt && cwgt._nhflex) {
					if (cwgt !== child)
						cwgt._flexFixed = true; 
					if (cwgt._hflex == 'min') {
						cwgt.fixMinFlex_(c, 'w');
						if (!vert) wdh -= cwdh;
					} else {
						hflexs.push(cwgt);
						if (!vert) hflexsz += cwgt._nhflex;
					}
				} else if (!vert) wdh -= cwdh;
			}
		}

		
		
		var lastsz = hgh > 0 ? hgh : 0;
		for (var j = vflexs.length - 1; j > 0; --j) {
			var cwgt = vflexs.shift(), 
				vsz = (cwgt._nvflex * hgh / vflexsz) | 0, 
				
				isz = vsz,
				chdex = cwgt.$n('chdex'),
				$chdex = zk(chdex),
				minus = $chdex.padBorderHeight(),
				isInit = !cwgt.$n().style.height;
			
			
			cwgt.setFlexSize_({height:isz - minus});
			cwgt._vflexsz = vsz - minus;
			
			if (!cwgt.$instanceof(zul.wgt.Cell)) {				
				
				chdex.style.height = jq.px0(vsz - $chdex.marginHeight());
			}

			if (vert) lastsz -= vsz;
			
			if (!isInit && vert && cwgt !== child)
				zUtl.fireSized(cwgt);
		}
		
		if (vflexs.length) {
			var cwgt = vflexs.shift(),
				
				isz = lastsz,
				chdex = cwgt.$n('chdex'),
				$chdex = zk(chdex),
				minus = $chdex.padBorderHeight(),
				isInit = !cwgt.$n().style.height;
			
			
			cwgt.setFlexSize_({height:isz - minus});
			cwgt._vflexsz = lastsz - minus;
			
			if (!cwgt.$instanceof(zul.wgt.Cell)) {
				
				chdex.style.height = jq.px0(lastsz - $chdex.marginHeight());
			}
			
			if (!isInit && vert && cwgt !== child)
				zUtl.fireSized(cwgt);
		}
		
		
		
		lastsz = wdh > 0 ? wdh : 0;
		for (var j = hflexs.length - 1; j > 0; --j) {
			var cwgt = hflexs.shift(), 
				hsz = (cwgt._nhflex * wdh / hflexsz) | 0, 
				chdex = cwgt.$n('chdex'),
				$chdex = zk(chdex),
				minus = $chdex.padBorderWidth(),
				isInit = !cwgt.$n().style.width;
			
			
			cwgt.setFlexSize_({width:hsz - minus});
			cwgt._hflexsz = hsz - minus;
			
			if (!cwgt.$instanceof(zul.wgt.Cell)) {
				
				chdex.style.width = jq.px0(hsz - $chdex.marginWidth());
			}
			if (!vert) lastsz -= hsz;
			
			if (!isInit && !vert && cwgt !== child)
				zUtl.fireSized(cwgt);
		}
		
		if (hflexs.length) {
			var cwgt = hflexs.shift(),
				chdex = cwgt.$n('chdex'),
				$chdex = zk(chdex),
				minus = $chdex.padBorderWidth(),
				isInit = !cwgt.$n().style.width;

			
			cwgt.setFlexSize_({width:lastsz - minus});
			cwgt._hflexsz = lastsz - minus;
			
			if (!cwgt.$instanceof(zul.wgt.Cell)) {
				
				chdex.style.width = jq.px0(lastsz - $chdex.marginWidth());
			}
			
			if (!isInit && !vert && cwgt !== child)
				zUtl.fireSized(cwgt);
		}
		
		
		child.parent.afterChildrenFlex_(child);
		child._flexFixed = false;
		
		return false; 
	},
	_childOuterAttrs: function (child) {
		var html = '';
		if (child.$instanceof(zul.box.Splitter))
			html = ' class="' + child.$s('outer') + '"';
		else if (this.isVertical()) {
			if (this._isStretchPack()) {
				var v = this._pack2; 
				html = ' valign="' + (v ? zul.box.Box._toValign(v) : 'top') + '"';
			} else html = ' valign="top"';
		} else
			return ''; 

		if (!child.isVisible()) html += ' style="display:none"';
		return html;
	},
	_childInnerAttrs: function (child) {
		var html = '',
			vert = this.isVertical(),
			$Splitter = zul.box.Splitter;
		if (child.$instanceof($Splitter))
			return '';
				

		if (this._isStretchPack()) {
			var v = vert ? this.getAlign() : this._pack2;
			if (v) html += ' align="' + zul.box.Box._toHalign(v) + '"';
		}
		
		var style = '', szes = this._sizes;
		if (szes) {
			for (var j = 0, len = szes.length, c = this.firstChild;
			c && j < len; c = c.nextSibling) {
				if (child == c) {
					style = (vert ? 'height:':'width:') + szes[j];
					break;
				}
				if (!c.$instanceof($Splitter))
					++j;
			}
		}

		if (!vert && !child.isVisible()) style += style ? ';display:none' : 'display:none';
		if (!vert) style += style ? ';height:100%' : 'height:100%';
		return style ? html + ' style="' + style + '"': html;
	},
	_isStretchPack: function() {
		
		
		return this._splitterKid || this._stretchPack;
	},
	_isStretchAlign: function() {
		return this._align == 'stretch';
	},
	
	_bindWatch: function () {
		if (!this._watchBound) {
			this._watchBound = true;
			zWatch.listen({onSize: this, onHide: this});
		}
	},
	_unbindWatch: function() {
		if (this._watchBound) {
			zWatch.unlisten({onSize: this, onHide: this});
			delete this._watchBound;
		}
	},
	bind_: function() {
		this.$supers(Box, 'bind_', arguments);
		this._bindFixTd();
		if (this._isStretchAlign())
			this._bindAlign();
		if (this._splitterKid)
			this._bindWatch();
	},
	unbind_: function () {
		this._unbindWatch();
		this._unbindAlign();
		this._unbindFixTd();
		this.$supers(Box, 'unbind_', arguments);
	},
	_bindAlign: function() {
		if (!this._watchAlign) {
			this._watchAlign = true;
			zWatch.listen({onSize: [this, this._fixAlign], onHide: [this, this._fixAlign]});
		}
	},
	_unbindAlign: function() {
		if (this._watchAlign) {
			zWatch.unlisten({onSize: [this, this._fixAlign], onHide: [this, this._fixAlign]});
			delete this._watchAlign;
		}
	},
	_fixAlign: function () {
		if (this._isStretchAlign()) {
			var vert = this.isVertical(),
				td = this.$n('frame'),
				zktd = zk(td),
				tdsz = vert ? zktd.revisedWidth(td.offsetWidth) : zktd.revisedHeight(td.offsetHeight);
			
			for(var child = this.firstChild, c; child; child = child.nextSibling) {
				if (child.isVisible() && (c = child.$n())) {
					
					if (vert)
						c.style.width = zk(c).revisedWidth(tdsz, !zk.webkit) + 'px';
					else 
						c.style.height = zk(c).revisedHeight(tdsz - ((zk.ie < 11 && c.offsetTop > 0) ? (c.offsetTop * 2) : 0), !zk.webkit) + 'px';
				}
			}
		}
	},
	_bindFixTd: function() {
		if (!this._watchTd) {
			this._watchTd = true;
			zWatch.listen({onSize: [this, _fixTd], onHide: [this, _fixTd]});
		}
	},
	_unbindFixTd: function() {
		if (this._watchTd) {
			zWatch.unlisten({onSize: [this, _fixTd], onHide: [this, _fixTd]});
			delete this._watchTd;
		}
	},
	_configPack: function() {
		var v = this._pack;
		if (v) {
	    	var v = v.split(',');
	    	if (v[0].trim() == 'stretch') {
	    		this._stretchPack = true;
	    		this._pack2 = v.length > 1 ? v[1].trim() : null;
	    	} else {
	    		this._stretchPack = v.length > 1 && v[1].trim() == 'stretch';
	    		this._pack2 = v[0].trim();
	    	}
    	} else {
    		delete this._pack2;
    		delete this._stretchPack;
    	}
	},
	
	onSize: _zkf = function () {
		if (!this._splitterKid) return; 
		var vert = this.isVertical(), node = this.$n(), real = this.$n('real');
		real.style.height = real.style.width = '100%'; 
		
		
		
		

		var nd = vert ? real.rows: real.rows[0].cells,
			total = vert ? zk(real).revisedHeight(real.offsetHeight):
							zk(real).revisedWidth(real.offsetWidth),
			sizes = this._sizes;

		for (var i = nd.length; i--;) {
			var d = nd[i];
			if (zk(d).isVisible())
				if (vert) {
					var diff = d.offsetHeight;
					if(d.id && !d.id.endsWith('-chdex2')) { 
						
						if (d.cells.length) {
							var c = d.cells[0];
							c.style.height = zk(c).revisedHeight(i ? diff: total) + 'px';
							d.style.height = ''; 
						} else {
							d.style.height = zk(d).revisedHeight(i ? diff: total) + 'px';
						}
					}
					total -= diff;
				} else {
					var diff = d.offsetWidth;
					
					
					if(!sizes && d.id && !d.id.endsWith('-chdex2')) 
						d.style.width = zk(d).revisedWidth(i ? diff: total) + 'px';
					total -= diff;
				}
		}
	},
	onHide: _zkf
},{ 
	_toValign: function (v) {
		return v ? 'start' == v ? 'top': 'center' == v ? 'middle':
			'end' == v ? 'bottom': v: null;
	},
	_toHalign: function (v) {
		return v ? 'start' == v ? 'left': 'end' == v ? 'right': v: null;
	}
});

})();
