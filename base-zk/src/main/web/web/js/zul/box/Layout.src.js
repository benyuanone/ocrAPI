

zul.box.Layout = zk.$extends(zk.Widget, {
	_spacing: '5px',
	$define: {
		
		
		spacing: function () {
			var n = this.$n(),
				vert = this.isVertical_(),
				spc = this._spacing;
			if (n)
				jq(n).children('div:not(:last-child)').css('padding-' + (vert ? 'bottom' : 'right'), (spc && spc != 'auto') ? spc : '');
		}
	},
	_chdextr: function (child) {
		return child.$n('chdex') || child.$n();
	},
	insertChildHTML_: function (child, before, desktop) {
		if (before)
			jq(this._chdextr(before)).before(this.encloseChildHTML_(child));
		else {
			var jqn = jq(this.$n()),
			spc = this._spacing;
			jqn.children('div:last-child').css('padding-' + (this.isVertical_() ? 'bottom' : 'right'), (spc && spc != 'auto') ? spc : '');
			jqn.append(this.encloseChildHTML_(child));
		}
		child.bind(desktop);
	},
	bind_: function () {
		this.$supers(zul.box.Layout, 'bind_', arguments);
		zWatch.listen({onResponse: this});
	},
	unbind_: function () {
		zWatch.unlisten({onResponse: this});
		this.$supers(zul.box.Layout, 'unbind_', arguments);
	},
	
	syncSize: function () {
		this._shallSize = false;
		if (this.desktop) {
			
			for (var w = this.firstChild; w; w = w.nextSibling) {
				if (w._nvflex || w._nhflex) {
					zUtl.fireSized(this);
					break;
				}
			}
		}
	},
	onResponse: function () {
		if (this._shallSize)
			this.syncSize();
	},
	
	onChildVisible_: function (child) {
		this.$supers('onChildVisible_', arguments);
		if (this.desktop) {
			this._shallSize = true;
			
			child.$n('chdex').style.display = child.isVisible() ? '' : 'none';
		}
	},
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		if (this.desktop) {
			var n = child.$n('chdex');
			this._shallSize = true;
			
			if(n) n.style.display = child.isVisible() ? '' : 'none';
		}
	},
	onChildRemoved_: function () {
		this.$supers('onChildRemoved_', arguments);
		if (this.desktop)
			this._shallSize = true;
	},
	removeChildHTML_: function (child) {
		this.$supers('removeChildHTML_', arguments);
		jq(child.uuid + '-chdex', zk).remove();
		var rmsp = this.lastChild == child;
		if(this._spacing != 'auto' && this.lastChild == child)
			jq(this.$n()).children('div:last-child').css('padding-' + (this.isVertical_() ? 'bottom' : 'right'), '');
	},
	
	encloseChildHTML_: function (child, out) {
		var oo = [],
			vert = this.isVertical_(),
			spc = this._spacing;

		oo.push('<div id="', child.uuid, '-chdex" class="', this.$s('inner'), '"');
		if (spc && spc != 'auto') {
			oo.push(' style="', !child.isVisible() ? 'display:none;' : ''); 
			var next = child.nextSibling; 
			if (next && !next.$instanceof(zul.wgt.Popup))
				oo.push('padding-', vert ? 'bottom:' : 'right:', spc);
			oo.push('"');
		}
		oo.push('>');
		child.redraw(oo);
		oo.push('</div>');
		if (!out) return oo.join('');

		for (var j = 0, len = oo.length; j < len; ++j)
			out.push(oo[j]);
	},
	
	isVertical_: zk.$void,
	_resetBoxSize: function (vert) {
		for (var kid = this.firstChild; kid; kid = kid.nextSibling) {
			var chdex = kid.$n('chdex');
			
			if (chdex) {
				
				if (vert && kid._nvflex && kid.getVflex() != 'min') {
					var n;
					if ((n = kid.$n()) && (n.scrollTop || n.scrollLeft)) 
						;
					else
						kid.setFlexSize_({height:'', width:''});
					if (chdex)
						chdex.style.height = '';
				}
				if (!vert && kid._nhflex && kid.getHflex() != 'min') {
					var n;
					if ((n = kid.$n()) && (n.scrollTop || n.scrollLeft)) 
						;
					else
						kid.setFlexSize_({height:'', width:''});
					if (chdex)
						chdex.style.width = '';
				}
			}
		}
	},
	
	afterResetChildSize_: function(orient) {
		for (var kid = this.firstChild; kid; kid = kid.nextSibling) {
			var chdex = kid.$n('chdex');
			if (chdex) {
				if (orient == 'h')
					chdex.style.height = '';
				if (orient == 'w')
					chdex.style.width = '';
				chdex.style.minWidth = '1px'; 
			}
		}
	},
	
	resetSize_: function (orient) { 
		this.$supers(zul.box.Layout, 'resetSize_', arguments);
		var vert = this.isVertical_();
		for (var kid = this.firstChild; kid; kid = kid.nextSibling) {
			if (vert ? (kid._nvflex && kid.getVflex() != 'min')
					 : (kid._nhflex && kid.getHflex() != 'min')) {

				var chdex = kid.$n('chdex');
				if (chdex) {
					if (orient == 'h')
						chdex.style.height = '';
					if (orient == 'w')
						chdex.style.width = '';
				}
			}
		}
	},
	getChildMinSize_: function (attr, wgt) { 
		var el = wgt.$n(); 
		
		
		if (attr == 'w' && wgt._hflex && this.isVertical_()) {
			for (var w = wgt.nextSibling, max = 0, width; w; w = w.nextSibling) {
				if (!w._hflex) {
					width = zjq.minWidth(w.$n());
					max = width > max ? width : max;
				}
			}
			return max;
		}
		if (attr == 'h') { 
			return zk(el.parentNode).contentHeight();
		} else {
			return zjq.minWidth(el); 
		} 
	},
	
	getContentEdgeHeight_: function () {
		var h = 0;
		for (var kid = this.firstChild; kid; kid = kid.nextSibling)
			h += zk(kid.$n('chdex')).paddingHeight();

		return h;
	},
	
	getContentEdgeWidth_: function () {
		var w = 0;
		for (var kid = this.firstChild; kid; kid = kid.nextSibling)
			w += zk(kid.$n('chdex')).paddingWidth();

		return w;
	},
	beforeChildrenFlex_: function(child) {
		
		this._shallSize = false;
		child._flexFixed = true;
		
		var	vert = this.isVertical_(),
			vflexs = [],
			vflexsz = vert ? 0 : 1,
			hflexs = [],
			hflexsz = !vert ? 0 : 1,
			p = this.$n(),
			psz = child.getParentSize_(p),
			zkp = zk(p),
			hgh = psz.height,
			wdh = psz.width,
			xc = this.firstChild,
			scrWdh;

		if (!zk.mounting) { 
			this._resetBoxSize(vert);
		}

		
		if(zkp.hasVScroll()) 
			wdh -= (scrWdh = jq.scrollbarWidth());
			
		
		if(zkp.hasHScroll()) 
			hgh -= scrWdh || jq.scrollbarWidth();
		
		for (; xc; xc = xc.nextSibling) {
			
			var zkc;
			if (xc.isVisible() && !(zkc = zk(xc)).hasVParent()) {
				var cwgt = xc,
					c = cwgt.$n(),
					cp = c.parentNode,
					zkxc = zk(cp);
				
				if (xc && xc._nvflex) {
					if (cwgt !== child)
						cwgt._flexFixed = true; 
					if (cwgt._vflex == 'min') {
						cwgt.fixMinFlex_(c, 'h');
						
						
						var h = c.offsetHeight + zkc.marginHeight() + zkxc.padBorderHeight();
						cp.style.height = jq.px0(h);
						if (vert)
							hgh -= cp.offsetHeight + zkxc.marginHeight();
					} else {
						vflexs.push(cwgt);
						if (vert) {
							vflexsz += cwgt._nvflex;
							
							
							hgh = hgh - zkxc.marginHeight();
						}
					}
				} else if (vert) {
					
					var isIssueComp = cwgt.$instanceof(zul.wgt.Label) || cwgt.$instanceof(zul.wgt.Span) ||
							cwgt.$instanceof(zul.wgt.Div) || cwgt.$instanceof(zul.wgt.A);
					hgh -= (isIssueComp && zk.ie > 8 ? 1 : 0) + cp.offsetHeight + zkxc.marginHeight();
				}

				
				if (cwgt && cwgt._nhflex) {
					if (cwgt !== child)
						cwgt._flexFixed = true; 
					if (cwgt._hflex == 'min') {
						cwgt.fixMinFlex_(c, 'w');
						
						
						var w = c.offsetWidth + zkc.marginWidth() + zkxc.padBorderWidth();
						cp.style.width = jq.px0(zkxc.revisedWidth(w));
						if (!vert)
							wdh -= cp.offsetWidth + zkxc.marginWidth();
					} else {
						hflexs.push(cwgt);
						if (!vert) {
							hflexsz += cwgt._nhflex;
							
							
							wdh = wdh - zkxc.marginWidth(); 
						}
					}
				} else if (!vert)
					wdh -= cp.offsetWidth + zkxc.marginWidth();
			}
		}

		
		
		var lastsz = hgh > 0 ? hgh : 0;
		while (vflexs.length > 1) {
			var cwgt = vflexs.shift(),
				vsz = (vert ? (cwgt._nvflex * hgh / vflexsz) : hgh) | 0, 
				offtop = cwgt.$n().offsetTop,
				isz = vsz - ((zk.ie < 11 && offtop > 0) ? (offtop * 2) : 0),
				chdex = cwgt.$n('chdex'),
				minus = zk(chdex).padBorderHeight(),
				isInit = !cwgt.$n().style.height;
			
			
			cwgt.setFlexSize_({height:isz - minus});
			cwgt._vflexsz = vsz - minus;

			
			chdex.style.height = jq.px0(vsz);
			if (vert) lastsz -= vsz;
			
			if (!isInit && vert && cwgt !== child)
				zUtl.fireSized(cwgt);
		}
		
		if (vflexs.length) {
			var cwgt = vflexs.shift(),
				offtop = cwgt.$n().offsetTop,
				isz = lastsz - ((zk.ie < 11 && offtop > 0) ? (offtop * 2) : 0),
				chdex = cwgt.$n('chdex'),
				minus = zk(chdex).padBorderHeight(),
				isInit = !cwgt.$n().style.height;
			
			
			cwgt.setFlexSize_({height:isz - minus});
			cwgt._vflexsz = lastsz - minus;
			
			
			chdex.style.height = jq.px0(lastsz);
			
			if (!isInit && vert && cwgt !== child)
				zUtl.fireSized(cwgt);
		}
		
		
		lastsz = wdh > 0 ? wdh : 0;
		while (hflexs.length > 1) {
			var cwgt = hflexs.shift(), 
				hsz = (vert ? wdh : (cwgt._nhflex * wdh / hflexsz)) | 0, 
				chdex = cwgt.$n('chdex'),
				minus = zk(chdex).padBorderWidth(),
				isInit = !cwgt.$n().style.width;
			
			
			cwgt.setFlexSize_({width:hsz - minus});
			cwgt._hflexsz = hsz - minus;

			
			chdex.style.width = jq.px0(hsz);

			if (!vert) lastsz -= hsz;
			
			if (!isInit && !vert && cwgt !== child)
				zUtl.fireSized(cwgt);
		}
		
		if (hflexs.length) {
			var cwgt = hflexs.shift(),
				chdex = cwgt.$n('chdex'),
				minus = zk(chdex).padBorderWidth(),
				isInit = !cwgt.$n().style.width;
			
			
			cwgt.setFlexSize_({width:lastsz - minus});
			cwgt._hflexsz = lastsz - minus;

			
			chdex.style.width = jq.px0(lastsz);
			
			if (!isInit && !vert && cwgt !== child)
				zUtl.fireSized(cwgt);
				
		}

		
		child.parent.afterChildrenFlex_(child);
		child._flexFixed = false;

		return false; 
	},
	afterChildrenMinFlex_: function (opts) {
		var n = this.$n();
		if (opts == 'h') {
			if (this.isVertical_()) {
    			var total = 0;
    			for (var w = n.firstChild; w; w = w.nextSibling) {
    				var fchd = w.firstChild;
    				if (fchd.style.height) {
    					var hgh = fchd.offsetHeight
								+ zk(w).padBorderHeight()
								+ zk(fchd).marginHeight();
    					w.style.height = jq.px0(hgh);
    					total += hgh;
    				} else
    					total += w.offsetHeight;
    			}
    			n.style.height = jq.px0(total);
			} else {
    			var max = 0;
    			for (var w = n.firstChild; w; w = w.nextSibling) {
    				
    				
    				var h = w.offsetHeight;
    				if (h > max)
    					max = h;
    			}
    			n.style.height = jq.px0(max);
			}
		} else {
			if (!this.isVertical_()) {
    			var total = 0;
    			for (var w = n.firstChild; w; w = w.nextSibling) {
    				var fchd = w.firstChild;
    				if (fchd.style.width) {
    					var wdh = fchd.offsetWidth
								+ zk(w).padBorderWidth()
								+ zk(fchd).marginWidth();
    					w.style.width = jq.px0(wdh);
    					total += wdh;
    				} else
    					total += w.offsetWidth;
    			}

				
				if ((zk.ie > 8) && this._hflexsz)
					total = Math.max(this._hflexsz, total);

    			n.style.width = jq.px0(total);
			} else {
    			var max = 0;
    			for (var w = n.firstChild; w; w = w.nextSibling) {
    				var wd = w.firstChild.offsetWidth;
    				if (wd > max)
    					max = wd;
    			}
    			
    			
				if ((zk.ie > 8)&& this._hflexsz)
					max = Math.max(this._hflexsz, max);
				
    			n.style.width = jq.px0(max);
			}
		}
	}
});
