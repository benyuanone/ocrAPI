
(function () {
	function _setOpen(wgt, open, opts) {
		var colps = wgt.getCollapse();
		if (!colps || 'none' == colps) return; 

		var nd = wgt.$n('chdex'),
			vert = wgt.isVertical(),
			Splitter = wgt.$class,
			before = colps == 'before',
			sib = before ? Splitter._prev(nd): Splitter._next(nd),
			sibwgt = zk.Widget.$(sib),
			fd = vert ? 'height': 'width', 
			diff = 0;
		if (sib) {
			if (!open)
				zWatch.fireDown('onHide', sibwgt);

			sibwgt.setDomVisible_(sib, open);
			sibwgt.parent._fixChildDomVisible(sibwgt, open);
			
			var c = vert && sib.cells.length ? sib.cells[0] : sib;
			diff = zk.parseInt(c.style[fd]);
			if (!before && sibwgt && !sibwgt.nextSibling) {
				var sp = wgt.$n('chdex2');
				if (sp) {
					sp.style.display = open ? '': 'none';
					diff += zk.parseInt(sp.style[fd]);
				}
			}
		}

		var sib2 = before ? Splitter._next(nd): Splitter._prev(nd);
		if (sib2) {
			var c = vert && sib2.cells.length ? sib2.cells[0] : sib2;
				sz = c.style[fd];
			
			if (sz && sz.indexOf('px') > -1) {
				diff = zk.parseInt(c.style[fd]) + (open ? -diff: diff);
				if (diff < 0) diff = 0;
				c.style[fd] = diff + 'px';
			}
		}
		if (sib && open)
			zUtl.fireShown(sibwgt);
		if (sib2)
			zUtl.fireSized(zk.Widget.$(sib2), -1); 

		wgt._fixNSDomClass();
		wgt._fixbtn();
		wgt._fixszAll();

		if (!opts || opts.sendOnOpen)
			wgt.fire('onOpen', {open:open});
			
	}

zul.box.Splitter = zk.$extends(zul.Widget, {
	_collapse: 'none',
	_open: true,

	$define: {
		
		
		open: function(open, opts) {
			if (this.desktop)
				_setOpen(this, open, opts);
		}
	},

	
	isVertical: function () {
		var p = this.parent;
		return !p || p.isVertical();
	},
	
	getOrient: function () {
		var p = this.parent;
		return p ? p.getOrient(): 'vertical';
	},

	
	getCollapse: function () {
		return this._collapse;
	},
	
	setCollapse: function(collapse) {
		if (this._collapse != collapse) {
			var bOpen = this._open;
			if (!bOpen)
				this.setOpen(true, {sendOnOpen:false}); 

			this._collapse = collapse;
			if (this.desktop) {
				this._fixbtn();
				this._fixsz();
			}

			if (!bOpen)
				this.setOpen(false, {sendOnOpen:false});
		}
	},

	
	domClass_: function (no) {
		var sc = this.$supers('domClass_', arguments);
		if (!no || !no.zclass) {
			sc += ' ' + this.$s('vertical' == this.getOrient() ? 'vertical' : 'horizontal');
		}
		return sc;
	},
	setZclass: function () {
		this.$supers('setZclass', arguments);
		if (this.desktop)
			this._fixDomClass(true);
	},

	bind_: function () {
		this.$supers(zul.box.Splitter, 'bind_', arguments);

		var box = this.parent;
		if (box && !box._splitterKid) box._bindWatch();

		zWatch.listen({onSize: this, beforeSize: this});

		this._fixDomClass();
			

		var node = this.$n(),
			Splitter = this.$class;

		if (!this.$weave) {
			var $btn = jq(this.$n('btn'));
			$btn.click(Splitter.onclick);
		}

		this._fixbtn();

		this._drag = new zk.Draggable(this, node, {
			constraint: this.getOrient(), 
			ignoredrag: Splitter._ignoresizing,
			ghosting: Splitter._ghostsizing, 
			overlay: true, 
			zIndex: 12000,
			initSensitivity: 0,
			snap: Splitter._snap, 
			endeffect: Splitter._endDrag});

		this._shallClose = !this._open;
			
			
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this, beforeSize: this});

		var Splitter = this.$class,
			btn;
		if (btn = this.$n('btn')) {
			var $btn = jq(btn);
			$btn.unbind('click', Splitter.onclick);
		}

		this._drag.destroy();
		this._drag = null;
		this.$supers(zul.box.Splitter, 'unbind_', arguments);
	},

	
	_fixDomClass: function (inner) {
		var node = this.$n(),
			p = node.parentNode;
		if (p) {
			var vert = this.isVertical();
			if (vert) p = p.parentNode; 
			if (p && p.id.endsWith('chdex')) {
				p.className = this.$s('outer');
			}
		}
		if (inner) this._fixbtn();
	},
	_fixNSDomClass: function () {
		jq(this.$n())
			[this._open ? 'removeClass':'addClass'](this.$s('nosplitter'));
	},
	_fixbtn: function () {
		var $btn = jq(this.$n('btn')),
			$icon = jq(this.$n('icon')),
			colps = this.getCollapse();
		if (!colps || 'none' == colps) {
			$btn.addClass(this.$s('button-disabled'));
			$icon.hide();
		} else {
			var before = colps == 'before';
			if (!this._open) before = !before;

			if (this.isVertical()) {
				jq(this.$n('icon')).removeClass(before ? 'z-icon-caret-down' : 'z-icon-caret-up')
					.addClass(before ? 'z-icon-caret-up' : 'z-icon-caret-down');
			} else {
				jq(this.$n('icon')).removeClass(before ? 'z-icon-caret-right' : 'z-icon-caret-left')
					.addClass(before ? 'z-icon-caret-left' : 'z-icon-caret-right');
			}
			$btn.removeClass(this.$s('button-disabled'));
			$icon.show();
		}
	},
	setBtnPos_: function (ver) {
		var btn = this.$n('btn'),
			node = this.$n();
		if (ver)
			btn.style.marginLeft = ((node.offsetWidth - btn.offsetWidth) / 2)+'px';
		else
			btn.style.marginTop = ((node.offsetHeight - btn.offsetHeight) / 2)+'px';
	},
	_fixsz: _zkf = function () {
		if (!this.isRealVisible()) return;

		var node = this.$n(), pn = node.parentNode;
		if (pn) {
			var bfcolps = 'before' == this.getCollapse();
			if (this.isVertical()) {
				node.style.width = "100%"; 
				this.setBtnPos_(true);
			} else {
				node.style.height =
					(zk.webkit ? pn.parentNode.clientHeight: pn.clientHeight)+'px';
					
				this.setBtnPos_();
			}
		}

		if (this._shallClose) { 
			delete this._shallClose;
			_setOpen(this, false, {sendOnOpen:false});
		}
	},
	onSize: _zkf,
	beforeSize: function () {
		this.$n().style[this.isVertical() ? 'width': 'height'] = '';
	},

	_fixszAll: function () {
		
		var box;
		for (var p = this; p = p.parent;)
			if (p.$instanceof(zul.box.Box))
				box = p;

		if (box) this.$class._fixKidSplts(box);
		else this._fixsz();
	}
},{
	onclick: function (evt) {
		var wgt = zk.Widget.$(evt);
		wgt.setOpen(!wgt._open);
	},

	
	_ignoresizing: function (draggable, pointer, evt) {
		var wgt = draggable.control;
		if (!wgt._open || wgt.$n('icon') == evt.domTarget) return true;

		var run = draggable.run = {},
			node = wgt.$n(),
			nd = wgt.$n('chdex'),
			Splitter = zul.box.Splitter;
		run.prev = Splitter._prev(nd);
		run.next = Splitter._next(nd);
		if(!run.prev || !run.next) return true; 
		run.prevwgt = wgt.previousSibling;
		run.nextwgt = wgt.nextSibling;
		run.z_offset = zk(node).cmOffset();
		return false;
	},
	_ghostsizing: function (draggable, ofs, evt) {
		var $node = zk(draggable.node.parentNode);
		jq(document.body).append(
			'<div id="zk_ddghost" style="font-size:0;line-height:0;background:#AAA;position:absolute;top:'
			+ofs[1]+'px;left:'+ofs[0]+'px;width:'
			+$node.offsetWidth()+'px;height:'+$node.offsetHeight()
			+'px;"></div>');
		return jq("#zk_ddghost")[0];
	},
	_endDrag: function (draggable) {
		var wgt = draggable.control,
			vert = wgt.isVertical(),
			node = wgt.$n(),
			Splitter = zul.box.Splitter,
			flInfo = Splitter._fixLayout(wgt),
			bfcolps = 'before' == wgt.getCollapse(),
			run = draggable.run, diff, fd, w;

		if (vert) {
			diff = run.z_point[1];
			fd = 'height';

			
			if (run.next && run.next.cells.length) run.next = run.next.cells[0];
			if (run.prev && run.prev.cells.length) run.prev = run.prev.cells[0];
		} else {
			diff = run.z_point[0];
			fd = 'width';
		}
		
		var runNext = run.next, runPrev = run.prev;
		if (diff < 0) {
			runNext = run.prev;
			runPrev = run.next;
			diff = -diff;
			bfcolps = !bfcolps;
		}
		
		if (!diff) return; 
		
		
		if (w = run.nextwgt) {
			if (w.getHflex())
				w.setHflex('false');
			if (w.getVflex()) 
				w.setVflex('false');
			zWatch.fireDown('beforeSize', w);
		}
		if (w = run.prevwgt) {
			if (w.getHflex())
				w.setHflex('false');
			if (w.getVflex()) 
				w.setVflex('false');
			zWatch.fireDown('beforeSize', w);
		}
		
		
		if (runNext && runPrev) {
			var s = zk.parseInt(runNext.style[fd]),
				s2 = zk.parseInt(runPrev.style[fd]),
				totalFd = s + s2;
			
			s -= diff;
			if (s < 0) s = 0;
			var minusS = totalFd - s;
			runNext.style[fd] = s + 'px';
			runPrev.style[fd] = minusS + 'px';
			var nextClientFd = runNext['client' + fd.charAt(0).toUpperCase() + fd.slice(1)];
			var prevClientFd = totalFd - nextClientFd;
			if (nextClientFd != s)
				runNext.style[fd] = nextClientFd + 'px'; 
			if (prevClientFd != minusS)
				runPrev.style[fd] = prevClientFd + 'px'; 
			
			if (!bfcolps) 
				runNext.style.overflow = 'hidden';
			else
				runPrev.style.overflow = 'hidden';
		}

		if (w = run.nextwgt)
			zUtl.fireSized(w, -1); 
		if (w = run.prevwgt)
			zUtl.fireSized(w, -1); 

		Splitter._unfixLayout(flInfo);
			
			

		wgt._fixszAll();
			
		draggable.run = null;
	},
	_snap: function (draggable, pos) {
		var run = draggable.run,
			wgt = draggable.control,
			x = pos[0], y = pos[1];
		if (wgt.isVertical()) {
			if (y <= run.z_offset[1] - run.prev.offsetHeight) {
				y = run.z_offset[1] - run.prev.offsetHeight;
			} else {
				var max = run.z_offset[1] + run.next.offsetHeight - wgt.$n().offsetHeight;
				if (y > max) y = max;
			}
		} else {
			if (x <= run.z_offset[0] - run.prev.offsetWidth) {
				x = run.z_offset[0] - run.prev.offsetWidth;
			} else {
				var max = run.z_offset[0] + run.next.offsetWidth - wgt.$n().offsetWidth;
				if (x > max) x = max;
			}
		}
		run.z_point = [x - run.z_offset[0], y - run.z_offset[1]];

		return [x, y];
	},

	_next: function (n) {
		return jq(n).next().next()[0];
	},
	_prev: function (n) {
		return jq(n).prev().prev()[0];
	},

	_fixKidSplts: function (wgt) {
		if (wgt.isVisible()) { 
			var Splitter = zul.box.Splitter;
			if (wgt.$instanceof(Splitter))
				wgt._fixsz();

			for (wgt = wgt.firstChild; wgt; wgt = wgt.nextSibling)
				Splitter._fixKidSplts(wgt);
		}
	}
});


if (zk.opera) { 
	zul.box.Splitter._fixLayout = function (wgt) {
		var box = wgt.parent.$n();
		if (box.style.tableLayout != 'fixed') {
			var fl = [box, box.style.tableLayout];
			box.style.tableLayout = 'fixed';
			return fl;
		}
	};
	zul.box.Splitter._unfixLayout = function (fl) {
		if (fl) fl[0].style.tableLayout = fl[1];
	};
} else
	zul.box.Splitter._fixLayout = zul.box.Splitter._unfixLayout = zk.$void;

})();