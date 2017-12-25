



(function () {
	var _modals = [], _lastfocus;

	function _syncMaximized(wgt) {
		if (!wgt._lastSize) return;
		var node = wgt.$n(),
			zkn = zk(node),
			floated = wgt._mode != 'embedded',
			$op = floated ? jq(node).offsetParent() : jq(node).parent(),
			s = node.style;
			
		s.width = jq.px0($op[0].clientWidth - $op.zk.paddingWidth());
		s.height = jq.px0($op[0].clientHeight - $op.zk.paddingHeight());
	}

	
	function _startmove(dg) {
		
		var el = dg.node;
		if(el.style.top && el.style.top.indexOf('%') >= 0)
			 el.style.top = el.offsetTop + 'px';
		if(el.style.left && el.style.left.indexOf('%') >= 0)
			 el.style.left = el.offsetLeft + 'px';

		
		
		
		zWatch.fire('onFloatUp', dg.control); 
	}
	function _ghostmove(dg, ofs, evt) {
		var wnd = dg.control,
			el = dg.node,
			$el = jq(el),
			$top = $el.find('>div:first'),
			top = $top[0],
			$fakeT = jq(top).clone(),
			fakeT = $fakeT[0],
			zcls = wnd.getZclass();
		_hideShadow(wnd);
		
		jq(document.body).prepend(
			'<div id="zk_wndghost" class="' + zcls + '-move-ghost" style="position:absolute;' + 
			'top:' + ofs[1] + 'px; left:' + ofs[0] + 'px;' + 
			'width:' + ($el.width() + zk(el).padBorderWidth()) + 'px;' + 
			'height:'+ ($el.height() + zk(el).padBorderHeight()) +'px;' + 
			'z-index:'+el.style.zIndex+'"><dl></dl></div>');
		dg._wndoffs = ofs;
		el.style.visibility = 'hidden';
		var h = el.offsetHeight - wnd._titleHeight(el);
		el = jq('#zk_wndghost')[0];
		
		var f = el.firstChild;
		f.style.height = jq.px0(zk(f).revisedHeight(h));
		
		el.insertBefore(fakeT, el.lastChild);
		return el;
	}
	function _endghostmove(dg, origin) {
		var el = dg.node; 
		origin.style.top = jq.px(origin.offsetTop + el.offsetTop - dg._wndoffs[1]);
		origin.style.left = jq.px(origin.offsetLeft + el.offsetLeft - dg._wndoffs[0]);

		document.body.style.cursor = '';
		zWatch.fire('onMove'); 
	}
	function _ignoremove(dg, pointer, evt) {
		var el = dg.node,
			wgt = dg.control,
			tar = evt.domTarget, wtar;

		if (!tar.id)
			tar = tar.parentNode;
		switch (tar) {
		case wgt.$n('close'):
		case wgt.$n('max'):
		case wgt.$n('min'):
			return true; 
		}
		if(wgt != (wtar = zk.Widget.$(tar)) && wgt.caption != wtar)
			return true; 
		if (!wgt.isSizable()
		|| (el.offsetTop + 4 < pointer[1] && el.offsetLeft + 4 < pointer[0]
		&& el.offsetLeft + el.offsetWidth - 4 > pointer[0]))
			return false; 
		return true;
	}
	function _aftermove(dg, evt) {
		dg.node.style.visibility = '';
		var wgt = dg.control;

		
		
		

		
        if (wgt._position && wgt._position != 'parent') {
			wgt._position = null;
		}
		wgt.zsync();
		wgt._fireOnMove(evt.data);
		zk(wgt).redoCSS(-1, {'fixFontIcon': true});
	}

	function _doOverlapped(wgt) {
		var pos = wgt._position,
			n = wgt.$n(),
			$n = zk(n);
		if (!pos && (!n.style.top || !n.style.left)) {
			var xy = $n.revisedOffset();
			
			if (!n.style.left) {
				n.style.left = jq.px(xy[0]);
			}
			if (!n.style.top) {
				n.style.top = jq.px(xy[1]);
			}
		} else if (pos == 'parent')
			_posByParent(wgt);

		$n.makeVParent();
		
		zWatch.fireDown('onVParent', wgt);

		wgt.zsync();
		_updDomPos(wgt);
		wgt.setTopmost();
		_makeFloat(wgt);
	}
	function _doModal(wgt) {
		var pos = wgt._position,
			n = wgt.$n(),
			$n = zk(n);
		if (pos == 'parent') _posByParent(wgt);

		$n.makeVParent();
		
		zWatch.fireDown('onVParent', wgt);

		wgt.zsync();
		_updDomPos(wgt, true, false, true);

		
		var realVisible = wgt.isRealVisible();
		wgt.setTopmost();

		if (!wgt._mask) {
			var anchor = wgt._shadowWgt ? wgt._shadowWgt.getBottomElement(): null;
			wgt._mask = new zk.eff.FullMask({
				id: wgt.uuid + '-mask',
				anchor: anchor ? anchor: wgt.$n(),
				
				zIndex: wgt._zIndex,
				visible: realVisible
			});
		}
		if (realVisible)
			_markModal(wgt);

		_makeFloat(wgt);
	}
	function _markModal(wgt) {
		zk.currentModal = wgt;
		var wnd = _modals[0], fc = zk.currentFocus;
		if (wnd) wnd._lastfocus = fc;
		else _lastfocus = fc;
		_modals.unshift(wgt);

		
		
		
		
		setTimeout(function () {
			zk.afterAnimate(function () {
				if (!zUtl.isAncestor(wgt, zk.currentFocus)) {
					if (zk.ie9_)
						wgt.focus(100);
					else
						wgt.focus();
				}
			}, -1)});
	}
	function _unmarkModal(wgt) {
		_modals.$remove(wgt);
		if (zk.currentModal == wgt) {
			var wnd = zk.currentModal = _modals[0],
				fc = wnd ? wnd._lastfocus: _lastfocus;
			if (!wnd)
				_lastfocus = null;
			if (!fc || !fc.desktop)
				fc = wnd;
			if (fc)
				if (wgt._updDOFocus === false)
					wgt._updDOFocus = fc; 
				else
					fc.focus(0); 
					
		}
		wgt._lastfocus = null;
	}
	
	function _posByParent(wgt) {
		var n = wgt.$n(),
			ofs = zk(zk(n).vparentNode(true)).revisedOffset();
		wgt._offset = ofs;
		n.style.left = jq.px(ofs[0] + zk.parseInt(wgt._left));
		n.style.top = jq.px(ofs[1] + zk.parseInt(wgt._top));
	}
	function _updDomOuter(wgt, opts) {
		
		wgt._notSendMaximize = !opts || !opts.sendOnMaximize;
		wgt._updDOFocus = false; 
		try {
			var last = wgt._lastSize;
			wgt.rerender(wgt._skipper);
			if (last) {
				wgt._lastSize = last;
				
				
				if (n = wgt.$n()) {
					var s = n.style;
					
					
					if (last.h)
						s.height = last.h;
					if (last.w)
						s.width = last.w;
					wgt._fixHgh();
				}
			}
			var cf;
			if (cf = wgt._updDOFocus) 
				cf.focus(10);
		} finally {
			delete wgt._updDOFocus;
			delete wgt._notSendMaximize;
		}
	}
	
	function _updDomPos(wgt, force, posParent, minTop) {
		if (!wgt.desktop || wgt._mode == 'embedded')
			return;

		var n = wgt.$n(), pos = wgt._position;
		if (pos == 'parent') {
			if (posParent)
				_posByParent(wgt);
			return;
		}
		if (!pos && !force)
			return;

		var st = n.style;
		st.position = 'absolute'; 
		var ol = st.left, ot = st.top;
		if (pos != 'nocenter')
			zk(n).center(pos);
		var sdw = wgt._shadowWgt;
		if (pos && sdw) {
			var opts = sdw.opts, l = n.offsetLeft, t = n.offsetTop;
			if (pos.indexOf('left') >= 0 && opts.left < 0)
				st.left = jq.px(l - opts.left);
			else if (pos.indexOf('right') >= 0 && opts.right > 0)
				st.left = jq.px(l - opts.right);
			if (pos.indexOf('top') >= 0 && opts.top < 0)
				st.top = jq.px(t - opts.top);
			else if (pos.indexOf('bottom') >= 0 && opts.bottom > 0)
				st.top = jq.px(t - opts.bottom);
		}

		if (minTop && !pos) { 
			var top = zk.parseInt(n.style.top), y = jq.innerY();
			if (y) {
				var y1 = top - y;
				if (y1 > 100) n.style.top = jq.px0(top - (y1 - 100));
			} else if (top > 100)
				n.style.top = '100px';
		}

		wgt.zsync();
		if (ol != st.left || ot != st.top)
			wgt._fireOnMove();
	}

	function _hideShadow(wgt) {
		var shadow = wgt._shadowWgt;
		if (shadow) shadow.hide();
	}
	function _makeSizer(wgt) {
		if (!wgt._sizer) {
			wgt.domListen_(wgt.$n(), 'onMouseMove');
			wgt.domListen_(wgt.$n(), 'onMouseOut');
			var Window = wgt.$class;
			wgt._sizer = new zk.Draggable(wgt, null, {
				stackup: true,
				overlay: true, 
				draw: Window._drawsizing,
				snap: Window._snapsizing,
				initSensitivity: 0,
				starteffect: Window._startsizing,
				ghosting: Window._ghostsizing,
				endghosting: Window._endghostsizing,
				ignoredrag: Window._ignoresizing,
				endeffect: Window._aftersizing});
		}
	}
	function _makeFloat(wgt) {
		var handle = wgt.$n('cap');
		if (handle && !wgt._drag) {
			jq(handle).addClass(wgt.getZclass() + '-header-move');
			var Window = wgt.$class;
			wgt._drag = new zk.Draggable(wgt, null, {
				handle: handle, stackup: true,
				fireOnMove: false,
				starteffect: Window._startmove,
				ghosting: Window._ghostmove,
				endghosting: Window._endghostmove,
				ignoredrag: Window._ignoremove,
				endeffect: Window._aftermove,
				zIndex: 99999 
			});
		}
	}

	function _isModal(mode) {
		return mode == 'modal' || mode == 'highlighted';
	}

	
	function _getPosByParent(wgt, left, top) {
		var pos = wgt._position,
			left = zk.parseInt(left),
			top = zk.parseInt(top),
			x = 0, y = 0;
		if (pos == 'parent') {
			var vp = zk(wgt.$n()).vparentNode();
			if (vp) {
				var ofs = zk(vp).revisedOffset();
				x = ofs[0];
				y = ofs[1];
			}
		}
		return [jq.px(left - x), jq.px(top - y)];
	}

var Window =

zul.wnd.Window = zk.$extends(zul.ContainerWidget, {
	_mode: 'embedded',
	_border: 'none',
	_minheight: 100,
	_minwidth: 200,
	_shadow: true,

	$init: function () {
		this._fellows = {};
		this._lastSize = {};
		
		this.$supers('$init', arguments);

		this.listen({onMaximize: this, onClose: this, onMove: this, onSize: this.onSizeEvent, onZIndex: this}, -1000);
		this._skipper = new zul.wnd.Skipper(this);
	},

	$define: { 
		
		
		mode: _zkf = function () {
			_updDomOuter(this);
		},
		
		
		title: function () {
			if (this.caption)
				this.caption.updateDomContent_(); 
			else
				_updDomOuter(this);
		},
		
		
		border: _zkf,
		
		
		closable: _zkf,
		
		
		sizable: function (sizable) {
			if (this.desktop) {
				if (sizable)
					_makeSizer(this);
				else if (this._sizer) {
					this._sizer.destroy();
					this._sizer = null;
				}
			}
		},
		
		
		maximizable: _zkf,
		
		
		minimizable: _zkf,
		
		
		maximized: function (maximized, fromServer) {
			var node = this.$n();
			if (node) {
				var $n = zk(node),
					isRealVisible = this.isRealVisible();
				if (!isRealVisible && maximized) return;

				var l, t, w, h, 
					s = node.style, 
					up = this.getMaximizableIconClass_(),
					down = this.getMaximizedIconClass_();
				if (maximized) {
					jq(this.$n('max')).addClass(this.$s('maximized'))
						.children('.' + up).removeClass(up).addClass(down);

					var floated = this._mode != 'embedded',
						$op = floated ? jq(node).offsetParent() : jq(node).parent();
					l = s.left;
					t = s.top;
					w = s.width;
					h = s.height;

					
					s.top = '-10000px';
					s.left = '-10000px';
					
					s.width = jq.px0($op[0].clientWidth - (!floated ? $op.zk.paddingWidth() : 0));
					s.height = jq.px0($op[0].clientHeight - (!floated ? $op.zk.paddingHeight() : 0));
					this._lastSize = {l:l, t:t, w:w, h:h};

					
					s.top = '0';
					s.left = '0';

					
					w = s.width;
					h = s.height;
				} else {
					var max = this.$n('max'),
						$max = jq(max);
					$max.removeClass(this.$s('maximized'))
						.children('.' + down).removeClass(down).addClass(up);
					if (this._lastSize) {
						s.left = this._lastSize.l;
						s.top = this._lastSize.t;
						s.width = this._lastSize.w;
						s.height = this._lastSize.h;
						this._lastSize = null;
					}
					l = s.left;
					t = s.top;
					w = s.width;
					h = s.height;

					var body = this.$n('cave');
					if (body)
						body.style.width = body.style.height = '';
				}
				if (!fromServer || isRealVisible) {
					this._visible = true;
					
					if (!this._notSendMaximize) {
						var p = _getPosByParent(this, l, t); 
						this.fire('onMaximize', {
							left: p[0],
							top: p[1],
							width: w,
							height: h,
							maximized: maximized,
							fromServer: fromServer
						});
					}
				}
				if (isRealVisible)
					zUtl.fireSized(this);
			}
		},
		
		
		minimized: function (minimized, fromServer) {
			if (this._maximized)
				this.setMaximized(false);

			var node = this.$n();
			if (node) {
				var s = node.style;
				if (minimized) {
					zWatch.fireDown('onHide', this);
					jq(node).hide();
				} else {
					jq(node).show();
					zUtl.fireShown(this);
				}
				if (!fromServer) {
					this._visible = false;
					this.zsync();
					var p = _getPosByParent(this, s.left, s.top); 
					this.fire('onMinimize', {
						left: p[0],
						top: p[1],
						width: s.width,
						height: s.height,
						minimized: minimized
					});
				}
			}
		},
		
		
		contentStyle: _zkf,
		
		
		contentSclass: _zkf,
		
		
		position: function () {
			_updDomPos(this, false, this._visible);
		},
		
		
		minheight: null, 
		
		
		minwidth: null, 
		
		
		shadow: function () {
			if (this._shadow) {
				this.zsync();
			} else if (this._shadowWgt) {
				this._shadowWgt.destroy();
				this._shadowWgt = null;
			}
		}
	},
	
	repos: function () {
		_updDomPos(this, false, this._visible);
	},
	
	doOverlapped: function () {
		this.setMode('overlapped');
	},
	
	doPopup: function () {
		this.setMode('popup');
	},
	
	doHighlighted: function () {
		this.setMode('highlighted');
	},
	
	doModal: function () {
		this.setMode('modal');
	},
	
	doEmbedded: function () {
		this.setMode('embedded');
	},

	
	afterAnima_: function (visible) { 
		this.$supers('afterAnima_', arguments);
		this.zsync();
	},

	zsync: function () {
		this.$supers('zsync', arguments);
		if (this.desktop) {
			if (this._mode == 'embedded') {
				if (this._shadowWgt) {
					this._shadowWgt.destroy();
					this._shadowWgt = null;
				}
			} else if (this._shadow) {
				if (!this._shadowWgt)
					this._shadowWgt = new zk.eff.Shadow(this.$n(),
						{left: -4, right: 4, top: -2, bottom: 3});
				if (this._maximized || this._minimized || !this._visible) 
					_hideShadow(this);
				else
					this._shadowWgt.sync();
			}
			if (this._mask ) { 
				var n = (this._shadowWgt && this._shadowWgt.getBottomElement()) || this.$n() ; 
				if (n) this._mask.sync(n);
			}
		}
	},

	
	onClose: function () {
		if (!this.inServer) 
			this.parent.removeChild(this); 
	},
	onMove: function (evt) {
		this._left = evt.left;
		this._top = evt.top;
	},
	onMaximize: function (evt) {
		var data = evt.data;
		this._top = data.top;
		this._left = data.left;
		this._height = data.height;
		this._width = data.width;
	},
	onSizeEvent: function (evt) {
		var data = evt.data,
			node = this.$n(),
			s = node.style;

		_hideShadow(this);
		if (data.width != s.width) {
			s.width = data.width;
			
			
			this._width = s.width;
		}
		if (data.height != s.height) {
			s.height = data.height;
			this._fixHgh();
			
			
			this._height = s.height;
		}

		if (data.left != s.left || data.top != s.top) {
			s.left = data.left;
			s.top = data.top;
			this._fireOnMove(evt.keys);
		}

		this.zsync();
		var self = this;
		setTimeout(function() {
			zUtl.fireSized(self);
		});
	},
	onZIndex: _zkf = function (evt) {
		this.zsync();
	},
	
	onResponse: _zkf,
	onShow: function (ctl) {
		var w = ctl.origin;
		if (this != w && this._mode != 'embedded'
		&& this.isRealVisible({until: w, dom: true})) {
			zk(this.$n()).cleanVisibility();
			this.zsync();
		}
	},
	onHide: function (ctl) {
		var w = ctl.origin;
		if (this != w && this._mode != 'embedded'
		&& this.isRealVisible({until: w, dom: true})) {
		
		
		
		
			this.$n().style.visibility = 'hidden';
			this.zsync();
		}
	},
	onSize: function() {
		_hideShadow(this);
		if (this._maximized)
			_syncMaximized(this);
		this._fixHgh(true);
		if (this._mode != 'embedded')
			_updDomPos(this);
		this.zsync();
	},
	onFloatUp: function (ctl) {
		
		if (!this._visible || this._mode == 'embedded' || this._mask)
			return; 

		var wgt = ctl.origin;
		if (this._mode == 'popup') {
			for (var floatFound; wgt; wgt = wgt.parent) {
				if (wgt == this) {
					if (!floatFound) this.setTopmost();
					return;
				}
				floatFound = floatFound || wgt.isFloating_();
			}
			this.setVisible(false);
			this.fire('onOpen', {open:false});
		} else
			for (; wgt; wgt = wgt.parent) {
				if (wgt == this) {
					this.setTopmost();
					return;
				}
				if (wgt.isFloating_())
					return;
			}
	},
	_fixHgh: function (ignoreVisible) {
		if (ignoreVisible || this.isRealVisible()) {
			var n = this.$n(),
				hgh = n.style.height,
				cave = this.$n('cave'),
				cvh = cave.style.height;
	
			if (hgh && hgh != 'auto') {
				cave.style.height = jq.px0(this._offsetHeight(n));
			} else if (cvh && cvh != 'auto') {
				cave.style.height = '';
			}
		}
	},
	_offsetHeight: function (n) {
		return zk(n).offsetHeight() - this._titleHeight() - zk(n).padBorderHeight();		
	},
	_titleHeight: function () {
		var cap = this.getTitle() || this.caption ? this.$n('cap') : null;
		return cap ? cap.offsetHeight : 0;
	},

	_fireOnMove: function (keys) {
		var s = this.$n().style,
			p = _getPosByParent(this, s.left, s.top); 
		this.fire('onMove', zk.copy({
			left: p[0],
			top: p[1]
		}, keys), {ignorable: true});

	},
	
	setVisible: function (visible) {
		if (this._visible != visible) {
			if (this._maximized) {
				this.setMaximized(false);
			} else if (this._minimized) {
				this.setMinimized(false);
			}

			var modal = _isModal(this._mode);
			if (visible) {
				_updDomPos(this, modal, true, modal);
				if (modal && (!this.parent || this.parent.isRealVisible())) {
					this.setTopmost();
					_markModal(this);
				}
			} else if (modal)
				_unmarkModal(this);

			this.$supers('setVisible', arguments);

			if (!visible)
				this.zsync();
		}
	},
	setHeight: function (height) {
		this.$supers('setHeight', arguments);
		if (this.desktop)
			zUtl.fireSized(this);
				
	},
	setWidth: function (width) {
		this.$supers('setWidth', arguments);
		if (this.desktop)
			zUtl.fireSized(this);
	},
	setTop: function () {
		_hideShadow(this);
		this.$supers('setTop', arguments);
		this.zsync();

	},
	setLeft: function () {
		_hideShadow(this);
		this.$supers('setLeft', arguments);
		this.zsync();
	},
	setZIndex: _zkf = function (zIndex) {
		var old = this._zIndex;
		this.$supers('setZIndex', arguments);
		if (old != zIndex)
			this.zsync();
	},
	setZindex: _zkf,
	focus_: function (timeout) {
		var cap = this.caption;
		if(!zk.mobile) { 
			for (var w = this.firstChild; w; w = w.nextSibling)
				
				if (w.desktop && w != cap && w.focus_(timeout))
					return true;
		}
		return cap && cap.focus_(timeout);
	},
	
	domClass_: function(no) {
		var cls = this.$supers(zul.wnd.Window, 'domClass_', arguments),
			bordercls = this._border;
		
		bordercls = 'normal' == bordercls ? '':
			'none' == bordercls ? 'noborder' : bordercls;
		
		if (bordercls)
			cls += ' ' + this.$s(bordercls);
		
		if (!(this.getTitle() || this.caption))
			cls += ' ' + this.$s('noheader');
		
		cls += ' ' + this.$s(this._mode)   
		return cls;	
	},
	
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		if (child.$instanceof(zul.wgt.Caption)) {
			this.caption = child;
			this.rerender(this._skipper); 
		}
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);
		if (child == this.caption) {
			this.caption = null;
			this.rerender(this._skipper); 
		}
	},
	insertChildHTML_: function (child, before, desktop) {
		if (!child.$instanceof(zul.wgt.Caption)) 
			this.$supers('insertChildHTML_', arguments);
	},
	domStyle_: function (no) {
		var style = this.$supers('domStyle_', arguments);
		if ((!no || !no.visible) && this._minimized)
			style = 'display:none;'+style;
		if (this._mode != 'embedded')
			style = 'position:absolute;'+style;
		return style;
	},

	bind_: function (desktop, skipper, after) {
		this.$supers(Window, 'bind_', arguments);

		var mode = this._mode;
		zWatch.listen({onSize: this});

		if (mode != 'embedded') {
			zWatch.listen({onFloatUp: this, onHide: this, onShow: this});
			this.setFloating_(true);

			if (_isModal(mode)) _doModal(this);
			else _doOverlapped(this);
		}

		if (this._sizable)
			_makeSizer(this);

		if (this._maximizable && this._maximized) {
			var self = this;
			after.push(function() {
				self._maximized = false;
				self.setMaximized(true, true);
			});
		}

		if (this._mode != 'embedded' && (!zk.css3)) {
			jq.onzsync(this); 
			zWatch.listen({onResponse: this});
		}
	},
	detach: function() {
		
		if (zk.ie > 8 || zk.chrome) {
			var $jq = jq(this.$n()).find('iframe');
			if ($jq.length)
				$jq.hide().remove();
		}
		this.$supers(Window, 'detach', arguments);
	},
	unbind_: function () {
		var node = this.$n();
		zk(node).beforeHideOnUnbind();
		node.style.visibility = 'hidden'; 

		if (!zk.css3) jq.unzsync(this);

		
		if (this._shadowWgt) {
			this._shadowWgt.destroy();
			this._shadowWgt = null;
		}
		if (this._drag) {
			this._drag.destroy();
			this._drag = null;
		}
		if (this._sizer) {
			this._sizer.destroy();
			this._sizer = null;
		}

		if (this._mask) {
			this._mask.destroy();
			this._mask = null;
		}

		
		
		if (zk.ie > 9) {
			var $jq = jq(this.$n()).find('iframe');
			if ($jq.length)
				$jq.hide().remove();
		}
		zk(node).undoVParent(); 
		zWatch.unlisten({
			onFloatUp: this,
			onSize: this,
			onShow: this,
			onHide: this,
			onResponse: this
		});
		this.setFloating_(false);

		_unmarkModal(this);

		this.domUnlisten_(this.$n(), 'onMouseMove');
		this.domUnlisten_(this.$n(), 'onMouseOut');
		this.$supers(Window, 'unbind_', arguments);
	},
	_doMouseMove: function (evt) {
		if (this._sizer && evt.target == this) {
			var n = this.$n(),
				c = this.$class._insizer(n, zk(n).revisedOffset(), evt.pageX, evt.pageY),
				handle = this._mode == 'embedded' ? false : this.$n('cap');
			if (!this._maximized && c) {
				if (this._backupCursor == undefined)
					this._backupCursor = n.style.cursor;
				n.style.cursor = c == 1 ? 'n-resize': c == 2 ? 'ne-resize':
					c == 3 ? 'e-resize': c == 4 ? 'se-resize':
					c == 5 ? 's-resize': c == 6 ? 'sw-resize':
					c == 7 ? 'w-resize': 'nw-resize';
				if (handle) jq(handle).removeClass(this.$s('header-move'));
			} else {
				n.style.cursor = this._backupCursor || ''; 
				if (handle) jq(handle).addClass(this.$s('header-move'));
			}
		}
	},
	_doMouseOut: function (evt) {
		this.$n().style.cursor = this._backupCursor || '';
	},
	doClick_: function (evt) {
		var n = evt.domTarget;
		if (!n.id)
			n = n.parentNode;
		if (n) { 
			switch (n) {
			case this.$n('close'):
				this.fire('onClose');
				break;
			case this.$n('max'):
				this.setMaximized(!this._maximized);
				break;
			case this.$n('min'):
				this.setMinimized(!this._minimized);
				break;
			default:
				this.$supers('doClick_', arguments);
				return;
			}
			evt.stop();
		}
		this.$supers('doClick_', arguments);
	},
	
	afterChildrenMinFlex_: function (orient) {
		this.$supers('afterChildrenMinFlex_', arguments);
		if (_isModal(this._mode)) 
			_updDomPos(this, true); 
	},
	
	afterChildrenFlex_: function (cwgt) {
		this.$supers('afterChildrenFlex_', arguments);
		if (_isModal(this._mode))
			_updDomPos(this, true); 
	},
	
	getChildMinSize_: function (attr, wgt) {
		var including = true;
		if (wgt == this.caption) {
			if (attr == 'w') {
				including = !!(wgt.$n().style.width);
			} else {
				including = !!(wgt.$n().style.height);
			}
		}
		if (including) {
			return this.$supers('getChildMinSize_', arguments);
		} else {
			return 0;
		}
	},
	
	getContentEdgeWidth_: function (width) {
		if (this.caption && (width == this.caption.$n().offsetWidth)) {
			

			var p = this.$n(),
				fc = this.caption,
				c = fc ? fc.$n() : p.firstChild,
				zkp = zk(p),
				w = zkp.padBorderWidth();
			
			if (c) {
				c = c.parentNode;
				while (c && c.nodeType == 1 && p != c) {
					var zkc = zk(c);
					w += zkc.padBorderWidth() + zkc.sumStyles('lr', jq.margins);
					c = c.parentNode;
				}
				return w;
			}
			return 0;
		} else {
			return this.$supers('getContentEdgeWidth_', arguments);
		}
	},
	setFlexSizeH_: function(n, zkn, height, isFlexMin) {
		if (isFlexMin) {
			height += this._titleHeight(n);
		}
		this.$supers('setFlexSizeH_', arguments);
	},
	
	ignoreFlexSize_: function (type) {
		return this._mode != 'embedded';
	},
	getClosableIconClass_: function () {
		return 'z-icon-times';
	},
	getMaximizableIconClass_: function () {
		return 'z-icon-resize-full';
	},
	getMaximizedIconClass_: function () {
		return 'z-icon-resize-small';
	},
	getMinimizableIconClass_: function () {
		return 'z-icon-minus';
	}
},{ 
	
	_startsizing: function (dg) {
		zWatch.fire('onFloatUp', dg.control); 
	},
	_snapsizing: function (dg, pos) {
			
		var px = (dg.z_dir >= 6 && dg.z_dir <= 8) ? Math.max(pos[0], 0) : pos[0],
			
			py = (dg.z_dir == 8 || dg.z_dir <= 2) ? Math.max(pos[1], 0) : pos[1];
		return [px, py];
	},
	_ghostsizing: function (dg, ofs, evt) {
		var wnd = dg.control,
			el = dg.node;
		_hideShadow(wnd);
		wnd.setTopmost();
		var $el = jq(el);
		jq(document.body).append(
			'<div id="zk_ddghost" class="' + wnd.getZclass() + '-resize-faker"'
			+' style="position:absolute;top:'
			+ofs[1]+'px;left:'+ofs[0]+'px;width:'
			+$el.zk.offsetWidth()+'px;height:'+$el.zk.offsetHeight()
			+'px;z-index:'+el.style.zIndex+'"><dl></dl></div>');
		return jq('#zk_ddghost')[0];
	},
	_endghostsizing: function (dg, origin) {
		var el = dg.node; 
		if (origin) {
			dg.z_szofs = {
				top: el.offsetTop + 'px', left: el.offsetLeft + 'px',
				height: jq.px0(zk(el).revisedHeight(el.offsetHeight)),
				width: jq.px0(zk(el).revisedWidth(el.offsetWidth))
			};
		}
	},
	_insizer: function(node, ofs, x, y) {
		var r = ofs[0] + node.offsetWidth, b = ofs[1] + node.offsetHeight;
		if (x - ofs[0] <= 5) {
			if (y - ofs[1] <= 5)
				return 8;
			else if (b - y <= 5)
				return 6;
			else
				return 7;
		} else if (r - x <= 5) {
			if (y - ofs[1] <= 5)
				return 2;
			else if (b - y <= 5)
				return 4;
			else
				return 3;
		} else {
			if (y - ofs[1] <= 5)
				return 1;
			else if (b - y <= 5)
				return 5;
		}
	},
	_ignoresizing: function (dg, pointer, evt) {
		var el = dg.node,
			wgt = dg.control;
		if (wgt._maximized || evt.target != wgt) return true;

		var offs = zk(el).revisedOffset(),
			v = wgt.$class._insizer(el, offs, pointer[0], pointer[1]);
		if (v) {
			_hideShadow(wgt);
			dg.z_dir = v;
			dg.z_box = {
				top: offs[1], left: offs[0] ,height: el.offsetHeight,
				width: el.offsetWidth, minHeight: zk.parseInt(wgt.getMinheight()),
				minWidth: zk.parseInt(wgt.getMinwidth())
			};
			dg.z_orgzi = el.style.zIndex;
			return false;
		}
		return true;
	},
	_aftersizing: function (dg, evt) {
		var wgt = dg.control,
			data = dg.z_szofs;
		wgt.fire('onSize', zk.copy(data, evt.keys), {ignorable: true});
		dg.z_szofs = null;
	},
	_drawsizing: function(dg, pointer, evt) {
		if (dg.z_dir == 8 || dg.z_dir <= 2) {
			var h = dg.z_box.height + dg.z_box.top - pointer[1];
			if (h < dg.z_box.minHeight) {
				pointer[1] = dg.z_box.height + dg.z_box.top - dg.z_box.minHeight;
				h = dg.z_box.minHeight;
			}
			dg.node.style.height = jq.px0(h);
			dg.node.style.top = jq.px(pointer[1]);
		}
		if (dg.z_dir >= 4 && dg.z_dir <= 6) {
			var h = dg.z_box.height + pointer[1] - dg.z_box.top;
			if (h < dg.z_box.minHeight)
				h = dg.z_box.minHeight;
			dg.node.style.height = jq.px0(h);
		}
		if (dg.z_dir >= 6 && dg.z_dir <= 8) {
			var w = dg.z_box.width + dg.z_box.left - pointer[0];
			if (w < dg.z_box.minWidth) {
				pointer[0] = dg.z_box.width + dg.z_box.left - dg.z_box.minWidth;
				w = dg.z_box.minWidth;
			}
			dg.node.style.width = jq.px0(w);
			dg.node.style.left = jq.px(pointer[0]);
		}
		if (dg.z_dir >= 2 && dg.z_dir <= 4) {
			var w = dg.z_box.width + pointer[0] - dg.z_box.left;
			if (w < dg.z_box.minWidth)
				w = dg.z_box.minWidth;
			dg.node.style.width = jq.px0(w);
		}
	},
	_startmove: _startmove,
	_ghostmove: _ghostmove,
	_endghostmove: _endghostmove,
	_ignoremove: _ignoremove,
	_aftermove: _aftermove
});

zul.wnd.Skipper = zk.$extends(zk.Skipper, {
	$init: function (wnd) {
		this._w = wnd;
	},
	restore: function () {
		this.$supers('restore', arguments);
		var w = this._w;
		if (w._mode != 'embedded') {
			_updDomPos(w); 
			w.zsync();
		}
	}
});


zul.wnd.WindowRenderer = {
	
	shallCheckBorder: function (wgt) {
		return wgt._mode != 'popup' &&
			(wgt._mode != 'embedded' || wgt.getBorder() != 'none');
	}
};
})();