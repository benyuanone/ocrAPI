



(function () {
	var _shallFocusBack;
	function _calcMinWd(wgt) {
		var wgtn = wgt.$n(),
			ws = wgtn ? wgtn.style.whiteSpace : ''; 
		if (wgtn) {
			if (zk.ie8)
				wgt._wsbak = ws; 
			wgtn.style.whiteSpace = 'nowrap'; 
		}
		var eheadtblw,
			efoottblw,
			ebodytblw,
			eheadtblfix,
			efoottblfix,
			ebodytblfix,
			hdfaker = wgt.ehdfaker,
			bdfaker = wgt.ebdfaker,
			ftfaker = wgt.eftfaker,
			head = wgt.head,
			headn = head ? head.$n() : null,
			hdfakerws = [],
			bdfakerws = [],
			ftfakerws = [],
			hdws = [],
			hdcavews = [];
		
		if (wgt.eheadtbl && headn) {
			wgt.ehead.style.width = '';
			eheadtblw = wgt.eheadtbl.width;
			wgt.eheadtbl.width = '';
			wgt.eheadtbl.style.width = '';
			eheadtblfix = wgt.eheadtbl.style.tableLayout;
			wgt.eheadtbl.style.tableLayout = '';
			if (zk.chrome)
				wgt.ebodytbl.style.display = 'block';
			var headcol = hdfaker.firstChild,
				headcell = headn.firstChild;
			for (var i = 0; headcol; headcol = headcol.nextSibling, i++) {
				var headcave = headcell.firstChild;
				if (!headcave)
					continue;
				hdfakerws[i] = headcol.style.width;
				headcol.style.width = '';
				hdws[i] = headcell.style.width;
				headcell.style.width = '';
				hdcavews[i] = headcave.style.width;
				headcave.style.width = '';
				
				headcell = headcell.nextSibling;
			}
		}
		if (headn)
			headn.style.width = '';
		if (wgt.efoottbl) {
			wgt.efoot.style.width = '';
			efoottblw = wgt.efoottbl.width;
			wgt.efoottbl.width = '';
			wgt.efoottbl.style.width = '';
			efoottblfix = wgt.efoottbl.style.tableLayout;
			wgt.efoottbl.style.tableLayout = '';
			if (zk.chrome)
				wgt.ebodytbl.style.display = 'block';
			if (ftfaker) {
				var footcol = ftfaker.firstChild
				for (var i = 0; footcol; footcol = footcol.nextSibling) {
					ftfakerws[i++] = footcol.style.width;
					footcol.style.width = '';
				}
			}
		}
		if (wgt.ebodytbl) {
			wgt.ebody.style.width = '';
			ebodytblw = wgt.ebodytbl.width;
			wgt.ebodytbl.width = '';
			wgt.ebodytbl.style.width = '';
			ebodytblfix = wgt.ebodytbl.style.tableLayout;
			wgt.ebodytbl.style.tableLayout = '';
			if (zk.chrome)
				wgt.ebodytbl.style.display = 'block';
			if (bdfaker) {
				var bodycol = bdfaker.firstChild;
				for (var i = 0; bodycol; bodycol = bodycol.nextSibling) {
					bdfakerws[i++] = bodycol.style.width;
					bodycol.style.width = '';
				}
			}
		}

		
		var wds = [],
			width = 0,
			len = head ? head.nChildren : 0,
			w = head ? head = head.firstChild : null,
			headWgt = wgt.getHeadWidget(),
			max = 0, maxj;
		if (bdfaker && w) {
			var bodycells = wgt._getFirstRowCells(wgt.ebodyrows),
				footcells = ftfaker ? wgt._getFirstRowCells(wgt.efootrows) : null;
			
			for (var i = 0; i < len; i++) {
				var wd = bodycells && bodycells[i] ? bodycells[i].offsetWidth : 0,
					$cv = zk(w.$n('cave')),
					hdwd = w && w.isVisible() ? ($cv.textSize()[0] + $cv.padBorderWidth() + zk(w.$n()).padBorderWidth()) : 0,
					ftwd = footcells && footcells[i] && zk(footcells[i]).isVisible() ? footcells[i].offsetWidth : 0,
					header;
				
				if ((header = headWgt.getChildAt(i)) && header.getWidth())
					hdwd = Math.max(hdwd, ftwd);
				if (hdwd > wd)
					wd = hdwd;
				if (ftwd > wd)
					wd = ftwd;
				wds[i] = wd;
				if (zk.ff > 4 || zk.ie > 8) 
					++wds[i];
				width += wds[i]; 
				if (w)
					w = w.nextSibling;
			}
			wgt._deleteFakeRow(wgt.ebodyrows);
			if (ftfaker)
				wgt._deleteFakeRow(wgt.efootrows);
		} else {
			var tr;
			if (tr = _getSigRow(wgt)) {
				for (var cells = tr.cells, i = cells.length; i--;) {
					var wd = cells[i].offsetWidth;
					wds[i] = wd;
					if (zk.ff > 4 || zk.ie > 8) 
						++wds[i];
					width += wds[i]; 
				}
			}
		}

		if (wgt.eheadtbl && headn) {
			wgt.eheadtbl.width = eheadtblw || '';
			wgt.eheadtbl.style.tableLayout = eheadtblfix || '';
			if (zk.chrome)
				wgt.eheadtbl.style.display = '';
			var headcol = hdfaker.firstChild,
				headcell = headn.firstChild;
			for (var i = 0; headcol; headcol = headcol.nextSibling, i++) {
				var headcave = headcell.firstChild;
				if (!headcave)
					continue;
				headcol.style.width = hdfakerws[i];
				headcell.style.width = hdws[i];
				headcave.style.width = hdcavews[i];
				
				headcell = headcell.nextSibling;
			}
		}
		if (wgt.efoottbl) {
			wgt.efoottbl.width = efoottblw || '';
			wgt.efoottbl.style.tableLayout = efoottblfix || '';
			if (zk.chrome)
				wgt.efoottbl.style.display = '';
			if (ftfaker) {
				var footcol = ftfaker.firstChild
				for (var i = 0; footcol; footcol = footcol.nextSibling)
					footcol.style.width = ftfakerws[i++];
			}
		}
		if (wgt.ebodytbl) {
			wgt.ebodytbl.width = ebodytblw || '';
			wgt.ebodytbl.style.tableLayout = ebodytblfix || '';
			if (zk.chrome)
				wgt.ebodytbl.style.display = '';
			if (bdfaker) {
				var bodycol = bdfaker.firstChild;
				for (var i = 0; bodycol; bodycol = bodycol.nextSibling)
					bodycol.style.width = bdfakerws[i++];
			}
		}

		if (wgtn)
			wgtn.style.whiteSpace = ws;
		return {width: width, wds: wds};
	}
	function _fixBodyMinWd(wgt, fixMesh) {
		
		var sbc = wgt.isSizedByContent(),
			meshmin = wgt._hflex == 'min';
		if (!wgt.head && (meshmin || sbc)) {
			var bdw = zk(wgt.$n()).padBorderWidth(),
				wd = _getMinWd(wgt) + bdw, 
				tr = wgt.ebodytbl,
				wds = wgt._minWd.wds,
				wlen = wds.length;
			if (fixMesh && meshmin)
				wgt.setFlexSize_({width:wd}, true);
			if (!(tr = tr.firstChild) || !(tr = tr.firstChild))
				return; 
			for (var c = tr.firstChild, i = 0; c && (i < wlen); c = c.nextSibling)
				c.style.width = jq.px(wds[i++]);
			if (sbc && !meshmin) {
				
				var bdfx = tr.lastChild,
					bdfxid = wgt.uuid + '-bdflex';
				if (!bdfx || bdfx.id != bdfxid) {
					jq(tr).append('<td id="' + bdfxid + '"></td>');
					bdfx = tr.lastChild;
				}
			}
		}
	}
	function _fixPageSize(wgt, rows) {
		var ebody = wgt.ebody;
		if (!ebody)
			return; 
		var max = ebody.offsetHeight;
		if (zk(ebody).hasHScroll()) 
			max -= jq.scrollbarWidth();
		if (max == wgt._prehgh) return false; 
		wgt._prehgh = max;
		var ebodytbl = wgt.ebodytbl,
			etbparent = ebodytbl.offsetParent,
			etbtop = ebodytbl.offsetTop,
			hgh = 0,
			row,
			j = 0;
		for (var it = wgt.getBodyWidgetIterator({skipHidden:true}),
				len = rows.length, w; (w = it.next()) && j < len; j++) {
			row = rows[j];
			var top = row.offsetTop - (row.offsetParent == etbparent ? etbtop : 0);
			if (top > max) {
				--j;
				break;
			}
			hgh = top;
		}
		if (row) { 
			if (top <= max) { 
				hgh = hgh + row.offsetHeight;
				j = Math.floor(j * max / hgh);
			}
			
			if (j == 0) j = 1; 
			if (j != wgt.getPageSize()) {
				wgt.fire('onPageSize', {size: j});
				return true;
			}
		}
	}
	function _adjMinWd(wgt) {
		if (wgt._hflex == 'min') {
			var w = _getMinWd(wgt),
				n = wgt.$n();
			wgt._hflexsz = w + zk(n).padBorderWidth(); 
			n.style.width = jq.px0(wgt._hflexsz);
		}
	}
	function _getMinWd(wgt) {
		wgt._calcMinWds();
		var bdfaker = wgt.ebdfaker,
			wd,
			wds = [],
			width,
			_minwds = wgt._minWd.wds;
		if (wgt.head && bdfaker) {
			width = 0;
			var w = wgt.head.firstChild,
				bdcol = bdfaker.firstChild;
			
			for (var i = 0; w; w = w.nextSibling) {
				if (w._hflex == 'min')
					wd = wds[i] = _minwds[i] + zk(w.$n()).padBorderWidth();
				else {
					if (w._width && w._width.indexOf('px') > 0)
						wd = wds[i] = zk.parseInt(w._width);
					else
						wd = wds[i] = zk.parseInt(bdcol.style.width);
				}
				
				w._origWd = jq.px0(wd);
				width += wd;
				++i;
				bdcol = bdcol.nextSibling;
			}
		} else
			width = wgt._minWd.width; 
		return width;
	}
	function _getSigRow(wgt) {
		
		var rw = wgt.getBodyWidgetIterator().next(),
			tr = rw ? rw.$n() : null;
		if (!tr)
			return;
		for (var maxtr = tr, len, max = maxtr.cells.length; tr; tr = tr.nextSibling)
			if ((len = tr.cells.length) > max) {
				maxtr = tr;
				max = len;
			}
		return maxtr;
	}
	function _cpCellWd(wgt) {
		var dst = wgt.efootrows.rows[0],
			srcrows = wgt.ebodyrows.rows;
		if (!dst || !srcrows || !srcrows.length || !dst.cells.length)
			return;
		var ncols = dst.cells.length,
			src, maxnc = 0;
		for (var j = 0, it = wgt.getBodyWidgetIterator({skipHidden:true}), w; (w = it.next());) {
			if (wgt._modal && !w._loaded)
				continue;

			var row = srcrows[j++], $row = zk(row),
				cells = row.cells, nc = $row.ncols(),
				valid = cells.length == nc && $row.isVisible();
				
			if (valid && nc >= ncols) {
				maxnc = ncols;
				src = row;
				break;
			}
			if (nc > maxnc) {
				src = valid ? row: null;
				maxnc = nc;
			} else if (nc == maxnc && !src && valid) {
				src = row;
			}
		}
		if (!maxnc) return;

		var fakeRow = !src;
		if (fakeRow) { 
			src = document.createElement('TR');
			src.style.height = '0px';
				
			for (var j = 0; j < maxnc; ++j)
				src.appendChild(document.createElement('TD'));
			srcrows[0].parentNode.appendChild(src);
		}
		
		
		for (var j = maxnc; j--;)
			dst.cells[j].style.width = '';

		var sum = 0;
		for (var j = maxnc; j--;) {
			var d = dst.cells[j], s = src.cells[j];
			if (zk.opera) {
				sum += s.offsetWidth;
				d.style.width = zk(s).contentWidth();
			} else {
				d.style.width = s.offsetWidth + 'px';
				if (maxnc > 1) { 
					var v = s.offsetWidth - d.offsetWidth;
					if (v != 0) {
						v += s.offsetWidth;
						if (v < 0) v = 0;
						d.style.width = v + 'px';
					}
				}
			}
		}
		if (zk.opera && wgt.isSizedByContent())
			dst.parentNode.parentNode.style.width = sum + 'px';
		if (fakeRow)
			src.parentNode.removeChild(src);
	}


zul.mesh.MeshWidget = zk.$extends(zul.Widget, {
	_pagingPosition: 'bottom',
	_prehgh: -1,
	_minWd: null, 
	$init: function () {
		this.$supers('$init', arguments);
		this.heads = [];
	},

	_innerWidth: '100%',
	_currentTop: 0,
	_currentLeft: 0,

	$define: {
		
		
		pagingPosition: _zkf = function () {
			this.rerender();
		},
		
		
		sizedByContent: _zkf,
		
		
		span: function(v) {
			var x = (true === v || 'true' == v) ? -65500 : (false === v || 'false' == v) ? 0 : (zk.parseInt(v) + 1);
			this._nspan = x < 0 && x != -65500 ? 0 : x;
			this.rerender();
		},
		
		
		autopaging: _zkf,
		
		
		paginal: null,
		
		
		model: null,
		
		
		innerWidth: function (v) {
			if (v == null) this._innerWidth = v = '100%';
			if (this.eheadtbl) this.eheadtbl.style.width = v;
			if (this.ebodytbl) this.ebodytbl.style.width = v;
			if (this.efoottbl) this.efoottbl.style.width = v;
		}
	},
	
	getPageSize: function () {
		return (this.paging || this._paginal).getPageSize();
	},
	
	setPageSize: function (pgsz) {
		(this.paging || this._paginal).setPageSize(pgsz);
	},
	
	getPageCount: function () {
		return (this.paging || this._paginal).getPageCount();
	},
	
	getActivePage: function () {
		return (this.paging || this._paginal).getActivePage();
	},
	
	setActivePage: function (pg) {
		(this.paging || this._paginal).setActivePage(pg);
	},
	
	inPagingMold: function () {
		return 'paging' == this.getMold();
	},

	setHeight: function (height) {
		this.$supers('setHeight', arguments);
		if (this.desktop) {
			this._setHgh(height);
			this.onSize();
		}
	},
	setWidth: function (width) {
		this.$supers('setWidth', arguments);
		if (this.eheadtbl) this.eheadtbl.style.width = '';
		if (this.efoottbl) this.efoottbl.style.width = '';
		if (this.desktop)
			this.onSize();
	},
	setStyle: function (style) {
		if (this._style != style) {
			this.$supers('setStyle', arguments);
			if (this.desktop)
				this.onSize();
		}
	},

	
	getHeadWidget: function () {
		return this.head;
	},
	
	getFocusCell: function (el) {
		var td;
		jq([this.ebodytbl, this.eheadtbl, this.efoottbl]).each(function (i) {
			if (this && jq.isAncestor(this, el)) {
				var tds = jq(el).parents(i == 1 ? 'th' : 'td'); 
				for (var i = 0, j = tds.length; i < j; i++) {
					td = tds[i];
					if (jq(td).parents('table')[0] == this) {
						return false; 
					}
				}
			}
		});
		return td;
	},
	_moveToHidingFocusCell: function (index) { 
		
		var td = this.ehdfaker ? this.ehdfaker.childNodes[index] : null,
			frozen = this.frozen,
			bar;
		if (td && frozen && zk.parseInt(td.style.width) == 0 &&
			(index = index - frozen.getColumns()) >= 0) {
			if (this._nativebar) {
				frozen.setStart(index);
			} else if (bar = this._scrollbar) {
				frozen._doScrollNow(index);
				bar.setBarPosition(index);
			}
			_shallFocusBack = true;
		}
	},
	_restoreFocus: function () { 
		if (_shallFocusBack && zk.currentFocus) {
			_shallFocusBack = false;
			zk.currentFocus.focus();
		}
	},

	bind_: function () {
		this.$supers(zul.mesh.MeshWidget, 'bind_', arguments);
		
		this._bindDomNode();
		if (this._hflex != 'min')
			this._fixHeaders();
		if (this.ehead) 
			this.domListen_(this.ehead, 'onScroll', '_doSyncScroll');
		
		var ebody = this.ebody;
		if (this._nativebar && ebody) {
			this.domListen_(ebody, 'onScroll', '_doScroll');
			ebody.style.overflow = 'auto';
			
			
			
			if (this.efrozen)
				jq(ebody).css('overflow-x', 'hidden'); 
		}
		zWatch.listen({onSize: this, onResponse: this});
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this, onResponse: this});
		if (this.ehead) 
			this.domUnlisten_(this.ehead, 'onScroll', '_doSyncScroll');
		var ebody = this.ebody;
		if (this._nativebar && ebody && this.efrozen)
			jq(ebody).css('overflow-x', 'auto');
		this.$supers(zul.mesh.MeshWidget, 'unbind_', arguments);
	},
	clearCache: function () {
		this.$supers('clearCache', arguments);
		this.ebody = this.ehead = this.efoot = this.efrozen = this.ebodytbl
			= this.eheadtbl = this.efoottbl = this.ebodyrows
			= this.ehdfaker = this.ebdfaker = null;
	},

	
	syncSize: function () {
		if (this.desktop) {
			this.clearCachedSize_();
			if (this._hflex == 'min') {
				zFlex.onFitSize.apply(this);
			} else {
				this._calcMinWds();
				this._fixHeaders();
				this.onSize();
			}
		}
	},
	onResponse: function () {
		if (this._shallSize) {
			this.syncSize();
			this._shallSize = false; 
        }
	},
	_syncSize: function () {
		
		if (this.desktop)
			this._shallSize = true;
	},
	_fixHeaders: function (force) { 
		if (this.head && this.ehead) {
			var empty = true,
				flex = false,
				hdsmin = (this._hflex == 'min') || this.isSizedByContent();
			for (var i = this.heads.length; i-- > 0;) {
				var header = this.heads[i],
					emptyHeader = true;
				for (var w = header.firstChild; w; w = w.nextSibling) {
					
					
					var childNode = this.ehdfaker.childNodes[i]; 
					if (!childNode) continue;
					if (hdsmin && !childNode.style.width && !w._nhflex) {
						
						w._hflex = 'min';
						w._nhflex = -65500; 
						w._nhflexbak = true;
					}
					if (!flex && w._nhflex)
						flex = true;
					if (w.getLabel() || w.getImage() || w.nChildren) {
						emptyHeader = false;
						empty = false;
					}
				}
				
				if(header._visible) {
					
					var n = header.$n();
					if (n)
						n.style.display = emptyHeader ? 'none' : ''; 
				}
			}
			var old = this.ehead.style.display,
				tofix = force && flex && this.isRealVisible(); 
			this.ehead.style.display = empty ? 'none' : '';
			
			for (var w = this.head.firstChild; w; w = w.nextSibling) {
				if (tofix && w._nhflex)
					w.fixFlex_();
				if (w._nhflexbak) {
					delete w._hflex;
					delete w._nhflex;
					delete w._nhflexbak;
				}
			}
			return old != this.ehead.style.display;
		}
	},
	_adjFlexWd: function () { 
		var head = this.head;
		if (head) {
			var hdfaker = this.ehdfaker,
				bdfaker = this.ebdfaker,
				hdcol = hdfaker.firstChild,
				bdcol = bdfaker.firstChild,
				ftfaker = this.eftfaker,
				ftcol;
			
			
			if (ftfaker)
				ftcol = ftfaker.firstChild;
			
			
			this.eheadtbl.style.width = '';
			this.ebodytbl.style.width = '';
			
			
			var tblWidth = 0;
			
			
			for (var w = head.firstChild, wd; w && bdcol; w = w.nextSibling) {
				
				
				var wwd = w.$n().offsetWidth;
				if (w.isVisible() && wwd > 0.1)
					w._origWd = jq.px0(wwd);
				
				if (w.isVisible() && (wd = w._hflexWidth) !== undefined) {
					var revisedWidth = zk(bdcol).revisedWidth(Math.round(wd));
					
					w._origWd = revisedWidth + 'px';
					bdcol.style.width = revisedWidth + 'px';
					hdcol.style.width = bdcol.style.width;
					if (ftcol)
						ftcol.style.width = bdcol.style.width;
						
					
					tblWidth += revisedWidth;
				}
				bdcol = bdcol.nextSibling;
				hdcol = hdcol.nextSibling;
				if (ftcol)
					ftcol = ftcol.nextSibling;
			}
			
			
			var allWidths = this._isAllWidths();
			if (allWidths) {
				var hdtbl = this.eheadtbl,
					bdtbl = this.ebodytbl,
					fttbl = this.efoottbl;
				
				if (hdtbl) {
					hdtbl.style.width = tblWidth + 'px';
					if (bdtbl)
						bdtbl.style.width = tblWidth + 'px';
					if (fttbl)
						fttbl.style.width = tblWidth + 'px';
				}
			}
			
			_adjMinWd(this);
		}
	},
	_bindDomNode: function () {
		this.ehead = this.$n('head');
		this.eheadtbl = this.$n('headtbl');
		this.ebody = this.$n('body');
		this.ebodytbl = this.$n('cave');
		this.efoot = this.$n('foot');
		this.efoottbl = this.$n('foottbl');
		this.efrozen = this.$n('frozen');
		
		
		var erows = this.$n('rows');
		if (this.ebody && erows)
			this.ebodyrows = erows;
		
		if (this.ehead) {
			this.eheadrows = this.$n('headrows');
			this.ehdfaker = this.head.$n('hdfaker');
			this.ebdfaker = this.head.$n('bdfaker');
			if (this.efoot)
				this.eftfaker = this.head.$n('ftfaker');
		}
		if (this.efoot)
			this.efootrows = this.$n('footrows');
	},
	replaceHTML: function() { 
		var old = this._syncingbodyrows;
		this._syncingbodyrows = true;
		try {
			
			
			
			
			
			
			
			
			this.$supers(zul.mesh.MeshWidget, 'replaceHTML', arguments);
		} finally {
			this._syncingbodyrows = old;
		}
	},
	replaceChildHTML_: function() { 
		var old = this._syncingbodyrows;
		this._syncingbodyrows = true;
		try {
			this.$supers('replaceChildHTML_', arguments);
		} finally {
			this._syncingbodyrows = old;
		}
	},
	fireOnRender: function (timeout) {
		if (!this._pendOnRender) {
			this._pendOnRender = true;
			setTimeout(this.proxy(this._onRender), timeout ? timeout : 100);
		}
	},
	_doScroll: function () { 
		var t = zul.mesh.Scrollbar.getScrollPosV(this),
			l = zul.mesh.Scrollbar.getScrollPosH(this),
			scrolled = (t != this._currentTop || l != this._currentLeft),
			ebody = this.ebody,
			ehead = this.ehead,
			efoot = this.efoot;

		
		if (jq(this).data('scrollable'))
			zWatch.fireDown('onScroll', this);
		
		
		if (scrolled && !(this.fire('onScroll', ebody.scrollLeft).stopped) && this._nativebar)
			if (this._currentLeft != ebody.scrollLeft) {
				if (ehead)
					ehead.scrollLeft = ebody.scrollLeft;
				if (efoot)
					efoot.scrollLeft = ebody.scrollLeft;
			}
		
		
		if (scrolled )
			this._currentTop = t;

		if (scrolled) 
			this._currentLeft = l;

		if (!this.paging && !this._paginal)
			this.fireOnRender(zk.gecko ? 200 : 60);

		if (scrolled)
			this._fireOnScrollPos();
	},
	_doSyncScroll: function () { 
		var ehead = this.ehead,
			ebody = this.ebody,
			efoot = this.efoot;
		if (ehead && zk(ehead).isVisible()) {
			if (this._currentLeft != ehead.scrollLeft) {
				if (ebody)
					ebody.scrollLeft = ehead.scrollLeft;
				if (efoot) 
					efoot.scrollLeft = ehead.scrollLeft;
			}
		}
	},
	_timeoutId: null,
	_fireOnScrollPos: function (time) { 
		clearTimeout(this._timeoutId);
		this._timeoutId = setTimeout(this.proxy(this._onScrollPos), time >= 0 ? time : 300);
	},
	_onScrollPos: function () {
		
		if (this.ebody) {
			this._currentTop = zul.mesh.Scrollbar.getScrollPosV(this);
			this._currentLeft = zul.mesh.Scrollbar.getScrollPosH(this);
			this.fire('onScrollPos', {
				top: this._currentTop,
				left: this._currentLeft
			});
		}
	},
	_onRender: function () { 
		if (!this.$n())
			return; 

		this._pendOnRender = false;
		if (this._syncingbodyrows || zAu.processing()) { 
			this.fireOnRender(zk.gecko ? 200 : 60); 
			return true;
		}

		var rows = this.ebodyrows ? this.ebodyrows.rows : null;
		if (this.inPagingMold() && this._autopaging && rows && rows.length)
			if (_fixPageSize(this, rows))
				return; 

		if (zk.ie8 && (this._wsbak !== undefined)) { 
			this.$n().style.whiteSpace = this._wsbak;
			delete this._wsbak;
		}

		if (!this.desktop || !this._model || !rows || !rows.length) return;

		
		
		var items = [],
			min = zul.mesh.Scrollbar.getScrollPosV(this),
			max = min + this.ebody.offsetHeight;
		for (var j = 0, it = this.getBodyWidgetIterator({skipHidden:true}),
				len = rows.length, w; (w = it.next()) && j < len; j++) {
			if (!w._loaded) {
				
				
				var row = w.$n(), $row = zk(row),
					top = $row.offsetTop();

				if (top + $row.offsetHeight() < min) continue;
				if (top > max) break; 
				items.push(w);
			}
		}
		if (items.length) {
			this.fire('onRender', {items: items}, {implicit:true});
		}
	},
	onSize: function () {
		if (this.isRealVisible()) { 
			var n = this.$n();
			if (n._lastsz && n._lastsz.height == n.offsetHeight 
					&& n._lastsz.width == n.offsetWidth) {
				this.fireOnRender(155); 
				return; 
			}
			this._calcSize();
			this.fireOnRender(155);
			
			if (this._nativebar) { 
				var ebody = this.ebody;
				if (ebody.scrollHeight >= this._currentTop)
					ebody.scrollTop = this._currentTop;
				
				if (ebody.scrollWidth >= this._currentLeft) {
					ebody.scrollLeft = this._currentLeft;
					if (this.ehead) 
						this.ehead.scrollLeft = this._currentLeft;
					if (this.efoot) 
						this.efoot.scrollLeft = this._currentLeft;
				}
			}
			this._shallSize = false;
		}
	},
	_vflexSize: function () {
		var n = this.$n(),
			pgHgh = 0;
		if (this.paging) {
			var pgit = this.$n('pgit'),
				pgib = this.$n('pgib');
			if (pgit) pgHgh += pgit.offsetHeight;
			if (pgib) pgHgh += pgib.offsetHeight;
		}
		
		var hgh = zk(n).contentHeight()
			- (this.ehead ? this.ehead.offsetHeight : 0)
			- (this.efoot ? this.efoot.offsetHeight : 0)
			- pgHgh;
		return this.frozen && this._nativebar ?
				hgh - this.efrozen.offsetHeight : hgh;
	},
	setFlexSize_: function (sz) {
		var n = this.$n(),
			head = this.$n('head');
		if (sz.height !== undefined) {
			if (sz.height == 'auto') {
				n.style.height = '';
				if (head)
					head.style.height = '';
			} else {
				return this.$supers('setFlexSize_', arguments);
			}
		}
		if (sz.width !== undefined) {
			if (sz.width == 'auto') {
				if (this._hflex != 'min')
					n.style.width = '';
				if (head)
					head.style.width = '';
			} else {
				return this.$supers('setFlexSize_', arguments);
			}
		}
	},
	
	_setHgh: function (hgh) {
		var n = this.$n(),
			ebody = this.ebody,
			ebodyStyle = ebody.style;
		if (this.isVflex() || (hgh && hgh != 'auto' && hgh.indexOf('%') < 0)) {
			if (zk.webkit && ebodyStyle.height == jq.px(this._vflexSize()))
				return; 
			ebodyStyle.height = ''; 
			var h = this._vflexSize();
			if (h < 0)
				h = 0;
			if (this._vflex != 'min')
				ebodyStyle.height = h + 'px';
		} else {
			
			ebodyStyle.height = '';
			n.style.height = hgh;
		}
	},
	_ignoreHghExt: function () {
		return false;
	},
	
	_calcSize: function () {
		this._beforeCalcSize();
		
		
		
		
		var n = this.$n(),
			
			tblwd = zk(n).contentWidth(),
			sizedByContent = this.isSizedByContent(),
			ehead = this.ehead,
			ebody = this.ebody,
			ebodyrows = this.ebodyrows,
			efoot = this.efoot,
			efootrows = this.efootrows;
		
		
		if (zk.ie9_ && ebody && tblwd)
			ebody.style.width = tblwd + 'px';
		
		if (ehead) {
			if (tblwd) {
				ehead.style.width = tblwd + 'px';
				if (ebody)
					ebody.style.width = tblwd + 'px';
				if (efoot)
					efoot.style.width = tblwd + 'px';
			}
			if (sizedByContent && ebodyrows)
				this._adjHeadWd();
			else if (tblwd && efoot)
				efoot.style.width = tblwd + 'px';
		} else if (efoot) {
			if (tblwd)
				efoot.style.width = tblwd + 'px';
			if (efootrows && ebodyrows)
				_cpCellWd(this);
		}
		
		
		this._adjSpanWd();
		
		_fixBodyMinWd(this, true);
		
		
		
		
		if (sizedByContent 
				&& this.getRows && this.getRows() > 1 
				&& (typeof this._calcHgh == 'function')
				&& this.ebody.style.height) { 
			this._calcHgh(); 
		}
		
		n._lastsz = {height: n.offsetHeight, width: n.offsetWidth}; 
		
		this._afterCalcSize();
	},
	_beforeCalcSize: function () {
		var ebody = this.ebody;
		if (!this._nativebar && (ebody.scrollLeft || ebody.scrollTop)) {
			
			this._ebodyScrollPos = {l: ebody.scrollLeft, t: ebody.scrollTop};
		}
		this._setHgh(this.$n().style.height);
	},
	_afterCalcSize: function () {
		if (this._ebodyScrollPos) {
			
			this.ebody.scrollLeft = this._ebodyScrollPos.l;
			this.ebody.scrollTop = this._ebodyScrollPos.t;
			this._ebodyScrollPos = null;
		}
		
		
		var allWidths = this._isAllWidths();
		if (allWidths) {
			var hdtbl = this.eheadtbl,
				bdtbl = this.ebodytbl,
				fttbl = this.efoottbl;
			
			if (hdtbl) {
				var wd = 0;
				for (var w = this.ehdfaker.firstChild; w; w = w.nextSibling) {
					if (w.style.display != 'none' && !w.id.endsWith('hdfaker-bar')) 
						wd += zk.parseInt(w.style.width);
				}
				hdtbl.style.width = wd + 'px';
				if (bdtbl)
					bdtbl.style.width = wd + 'px';
				if (fttbl)
					fttbl.style.width = wd + 'px';
			}
		} else if (this.frozen) {
			
			if (this.ebody) {
				var bdtbl = this.ebodytbl;
				if (bdtbl)
					bdtbl.style.width = this.ebody.style.width;
			}
		}
		if (!this.frozen) {
			var zkb = zk(this.ebody),
				hScroll = zkb.hasHScroll(),
				vScroll = zkb.hasVScroll(),
				hdfakerbar = this.head ? this.head.$n('hdfaker-bar') : null,
				ftfakerbar = this.eftfaker ? this.head.$n('ftfaker-bar') : null;
			if (vScroll) {
				if (hdfakerbar)
					hdfakerbar.style.width = vScroll + 'px';
				if (ftfakerbar)
					ftfakerbar.style.width = vScroll + 'px';
			} else {
				
				if (hdfakerbar)
					hdfakerbar.style.width = 0.1 + 'px';
				if (ftfakerbar)
					ftfakerbar.style.width = 0.1 + 'px';
			}
		}
	},
	
	_isAllWidths: function() {
		
		if (this.isSizedByContent() && this.ebodyrows && this.ebodyrows.firstChild)
			return true;
		if (!this.head)
			return false;
		var allwidths = true;
		for (var w = this.head.firstChild; w; w = w.nextSibling) {
			if (allwidths 
					&& (w._width === undefined || w._width.indexOf('px') <= 0) 
					&& (w._hflex != 'min' || w._hflexsz === undefined) 
					&& w.isVisible()) {
				allwidths = false;
				break;
			}
		}
		return allwidths;
	},
	domFaker_: function (out, fakeId) { 
		var head = this.head;
		out.push('<colgroup id="', head.uuid, fakeId, '">');
		for (var w = head.firstChild; w; w = w.nextSibling) {
			var wd = w._hflexWidth ? w._hflexWidth + 'px' : w.getWidth(),
				visible = !w.isVisible() ? 'display:none;' : '';
			
			wd = wd ? 'width: ' + wd + ';' : '';
			out.push('<col id="', w.uuid, fakeId, '" style="', wd, visible, '"/>');
		}
		if (!this.frozen && (fakeId.indexOf('hd') > 0 || fakeId.indexOf('ft') > 0))
			out.push('<col id="', head.uuid, fakeId, '-bar" style="width: 0px" />');
		out.push('</colgroup>');
	},

	
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);

		if (child.$instanceof(this.getHeadWidgetClass())) {
			this.head = child;
			this._minWd = null;
		} else if (!child.$instanceof(zul.mesh.Auxhead))
			return;

		var nsib = child.nextSibling;
		if (nsib)
			for (var hds = this.heads, j = 0, len = hds.length; j < len; ++j)
				if (hds[j] == nsib) {
					hds.splice(j, 0, child);
					return; 
				}
		this.heads.push(child);
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);

		if (child == this.head) {
			this._minWd = this.head = null;
			this.heads.$remove(child);
		} else if (child.$instanceof(zul.mesh.Auxhead))
			this.heads.$remove(child);
		else if (child.$instanceof(zul.mesh.Frozen))
			this.efrozen = null;
	},
	
	resetSize_: function(orient) {
		this.$supers('resetSize_', arguments);
		if (orient == 'w') {
			if (this.ehead)
				this.ehead.style.width = '';
			if (this.ebody)
				this.ebody.style.width = '';
			if (this.efoot)
				this.efoot.style.width = '';
		}
	},
	
	beforeMinFlex_: function (orient) {
		if (this._hflexsz === undefined && orient == 'w' && this._width === undefined) {
			if (this.isSizedByContent())
				this._calcSize();
			if (this.head) {
				this._fixHeaders(true);
				for(var w = this.head.firstChild; w; w = w.nextSibling)
					if (w._hflex == 'min' && w.hflexsz === undefined) 
						return null;
			}
			_fixBodyMinWd(this); 
			return _getMinWd(this); 
		}
		return null;
	},
	
	beforeParentMinFlex_: function (orient) {
		if (orient == 'w') {
			if (this.isSizedByContent())
				this._calcSize();
			if (this.head)
				this._fixHeaders();
		} else
			this._calcSize();
	},
	clearCachedSize_: function() {
		this.$supers('clearCachedSize_', arguments);
		this._clearCachedSize();

		var tr;
		if (!this.ebdfaker && (tr = _getSigRow(this))) { 
			for (var cells = tr.cells, i = cells.length; i--;)
				cells[i].style.width = '';
		}
		var head = this.getHeadWidget();
		if (head) {
			for (var w = head.firstChild, wn; w; w = w.nextSibling)
				delete w._hflexsz;
		}
	},
	_clearCachedSize: function() {
		var n;
		if (n = this.$n())
			n._lastsz = this._minWd = null;
	},
	_calcMinWds: function () { 
		if (!this._minWd)
			this._minWd = _calcMinWd(this);
		return this._minWd;
	},
	_adjSpanWd: function () { 
		if (!this._isAllWidths() || !this.isSpan())
			return;
		var hdfaker = this.ehdfaker,
			bdfaker = this.ebdfaker,
			ftfaker = this.eftfaker;
		if (!hdfaker || !bdfaker)
			return;

		var head = this.head.$n();
		if (!head) 
			return;
		this._calcMinWds();
		var wd,
			wds = [],
			width = 0,
			hdcol = hdfaker.firstChild,
			bdcol = bdfaker.firstChild,
			_minwds = this._minWd.wds,
			hdlen = this.head.nChildren;
		
		for (var temphdcol = hdcol, w = this.head.firstChild, i = 0; w; w = w.nextSibling, i++) {
			if (zk(temphdcol).isVisible()) {
				var wdh = w._width;
				
				if (w._hflex == 'min')
					wd = wds[i] = _minwds[i];
				else if (wdh && wdh.endsWith('px'))
					wd = wds[i] = zk.parseInt(wdh);
				else
					wd = wds[i] = zk.parseInt(temphdcol.style.width);
				
				width += wd;
			}
			temphdcol = temphdcol.nextSibling;
		}
		
		var	ftcol = ftfaker ? ftfaker.firstChild : null,
			total = this.ebody.clientWidth,
			extSum = total - width,
			count = total,
			visj = -1,
			tblWidth = 0; 
		
		if (this._nspan < 0) { 
			for (var i = 0; hdcol && i < hdlen; hdcol = hdcol.nextSibling, i++) {
				
				if (!zk(hdcol).isVisible(true)) {
					bdcol = bdcol.nextSibling;
					if (ftcol)
						ftcol = ftcol.nextSibling;
					continue;
				} else {
					wds[i] = wd = extSum <= 0 ? wds[i] : (((wds[i] * total / width) + 0.5) || 0);
					var stylew = jq.px0(wd);
					count -= wd;
					visj = i;
					
					hdcol.style.width = stylew;
					bdcol.style.width = stylew;
					tblWidth += wd; 
					bdcol = bdcol.nextSibling;
					
					if (ftcol) {
						ftcol.style.width = stylew;
						ftcol = ftcol.nextSibling;
					}
				}
			}
			
			if (extSum > 0 && count != 0 && visj >= 0) {
				tblWidth -= wd; 
				wd = wds[visj] + count;
				var stylew = jq.px0(wd);
				
				bdfaker.childNodes[visj].style.width = stylew;
				hdfaker.childNodes[visj].style.width = stylew;
				tblWidth += wd; 
				
				if (ftfaker)
					ftfaker.childNodes[visj].style.width = stylew;
			}
		} else { 
			visj = this._nspan - 1;
			for (var i = 0; hdcol && i < hdlen; hdcol = hdcol.nextSibling, i++) {
				if (!zk(hdcol).isVisible()) {
					bdcol = bdcol.nextSibling;
					if (ftcol)
						ftcol = ftcol.nextSibling;
					continue;
				} else {
					wd = visj == i && extSum > 0 ? (wds[visj] + extSum) : wds[i];
					var stylew = jq.px0(wd);
					hdcol.style.width = stylew;
					bdcol.style.width = stylew;
					tblWidth += wd; 
					bdcol = bdcol.nextSibling;
					if (ftcol) {
						ftcol.style.width = stylew;
						ftcol = ftcol.nextSibling;
					}
				}
			}
		}
		
		
		var allWidths = this._isAllWidths();
		if (allWidths) {
			var hdtbl = this.eheadtbl,
				bdtbl = this.ebodytbl,
				fttbl = this.efoottbl;
			
			if (hdtbl) {
				hdtbl.style.width = tblWidth + 'px';
				if (bdtbl)
					bdtbl.style.width = tblWidth + 'px';
				if (fttbl)
					fttbl.style.width = tblWidth + 'px';
			}
		}
		
		
		if (zk.opera)
			zk(this.$n()).redoCSS();
	},
	_adjHeadWd: function () {
		var hdfaker = this.ehdfaker,
			bdfaker = this.ebdfaker,
			ftfaker = this.eftfaker;
		
		if (!hdfaker || !bdfaker || !this.getBodyWidgetIterator().hasNext())
			return;
		
		var hdtable = this.eheadtbl,
			head = this.head.$n();
		
		if (!head)
			return;
		
		
		var ebody = this.ebody,
			bdtable = this.ebodytbl,
			bdwd = ebody.offsetWidth,
			total = Math.max(hdtable.offsetWidth, bdtable.offsetWidth),
			tblwd = Math.min(bdwd, bdtable.offsetWidth);
		
		if (total == bdwd && bdwd > tblwd && bdwd - tblwd < 20)
			total = tblwd;
		
		var minWd = this._calcMinWds(),
			wds = minWd.wds,
			width = minWd.width,
			hdcol = hdfaker.firstChild,
			bdcol = bdfaker.firstChild,
			ftcol = ftfaker ? ftfaker.firstChild : null,
			hwgt = this.head.firstChild;
		
		
		for (var i = 0; hwgt; hwgt = hwgt.nextSibling, i++) {
			
			if (hwgt._width || wds[i] == 0) {
				if (wds[i] == 0) {
					hdcol.style.width = zk.chrome ? '0.1px' : '0px';
					bdcol.style.width = '0px';
					if (ftcol)
						ftcol.style.width = '0px';
				}
				hdcol = hdcol.nextSibling;
				bdcol = bdcol.nextSibling;
				if (ftcol)
					ftcol = ftcol.nextSibling;
			} else {
				var wd = jq.px(wds[i]);
				hdcol.style.width = bdcol.style.width = wd;
				hdcol = hdcol.nextSibling;
				bdcol = bdcol.nextSibling;
				if (ftcol) {
					ftcol.style.width = wd;
					ftcol = ftcol.nextSibling;
				}
			}
		}
		
		hdtable.style.width = jq.px(width);
		bdtable.style.width = jq.px(width);
		if (ftfaker)
			this.efoottbl.style.width = jq.px(width);
		
		_adjMinWd(this);
	},
	_getFirstRowCells: function (tbody) {
		if (tbody && tbody.rows && tbody.rows.length) {
			var cells = tbody.rows[0].cells,
				length = cells.length,
				ncols = 0;
			for (var i = 0; i < length; i++) {
				var span = cells[i].colSpan;
				ncols += span != 1 ? span : 1;
			}
			if (ncols == length)
				return cells;
			else {
				var out = [];
				out.push('<tr id="', tbody.id,
						'-fakeRow" style="visibility:hidden;height:0">');
				for (var i = 0; i < ncols; i++)
					out.push('<td></td>');
				out.push('</tr>');
				jq(tbody.rows[0]).before(out.join(''));
				out = null;
				return tbody.rows[0].cells;
			}
		}
	},
	_deleteFakeRow: function (tbody) {
		if (tbody)
			jq('#' + tbody.id + '-fakeRow').remove();
	}, 
	refreshBar_: function (showBar, scrollToTop) {
		var bar = this._scrollbar;
		if (bar) {
			
			var currentLeft = this._currentLeft,
				currentTop = this._currentTop;
			bar.syncSize(showBar || this._shallShowScrollbar);
			delete this._shallShowScrollbar; 
			if (scrollToTop)
				bar.scrollTo(0, 0);
			else
				bar.scrollTo(currentLeft, currentTop);
			
			var frozen = this.frozen,
				start;
			if (frozen && (start = frozen._start) != 0) {
				frozen._doScrollNow(start);
				bar.setBarPosition(start);
			}
			this._afterCalcSize(); 
		}
	},
});

zul.mesh.Scrollbar = {
	
	init: function (wgt) {
		var embed = jq(wgt.$n()).data('embedscrollbar') !== false, 
			frozen = wgt.frozen,
			startPositionX = 0;
		
		if (frozen) {
			var columns = frozen.getColumns();
			if (wgt.eheadtbl) {
				var cells = wgt._getFirstRowCells(wgt.eheadrows);
				if (cells) {
					for (var i = 0; i < columns; i++)
						startPositionX += cells[i].offsetWidth;
				}
				wgt._deleteFakeRow(wgt.eheadrows);
			}
		}
		var scrollbar = new zul.Scrollbar(wgt.ebody, wgt.ebodytbl, {
			embed: embed,
			startPositionX: startPositionX,
			onSyncPosition: function() {
				if (!this.frozen) {
					var pos = this.getCurrentPosition(),
						head = wgt.ehead,
						foot = wgt.efoot;
					if (pos && this.hasHScroll()) {
						if (head)
							head.scrollLeft = pos.x;
						if (foot)
							foot.scrollLeft = pos.x;
					}
				}
			},
			onScrollEnd: function() {
				wgt._doScroll();
			}
		});
		return scrollbar;
	},
	
	getScrollPosV: function (wgt) {
		var bar = wgt._scrollbar;
		if (bar)
			return bar.getCurrentPosition().y;
		
		return wgt.ebody.scrollTop;
	},
	
	getScrollPosH: function (wgt) {
		var bar = wgt._scrollbar;
		if (bar)
			return bar.getCurrentPosition().x;
		
		return wgt.ebody.scrollLeft;
	}
};
})();