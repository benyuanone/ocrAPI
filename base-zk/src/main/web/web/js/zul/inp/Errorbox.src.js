
(function () {

	var _dirMap = {
		'u': 'up',
		'd': 'down',
		'l': 'left',
		'r': 'right'
	};

zul.inp.Errorbox = zk.$extends(zul.wgt.Notification, {
	$init: function (owner, msg) {
		this.parent = owner; 
		this.parent.__ebox = this;
		this.msg = msg;
		this.$supers('$init', [msg, {ref: parent}]);
	},
	
	show: function () {
		jq(document.body).append(this);
		var cstp = this.parent._cst && this.parent._cst._pos;
		
		
		var self = this, cstp = this.parent._cst && this.parent._cst._pos;
		
		setTimeout(function(){
			if (self.parent && zul.inp.InputWidget._isInView(self)) 
				self.open(self.parent, null, cstp || 'end_before', {dodgeRef: !cstp});
		}, 50); 
		zWatch.listen({onHide: [this.parent, this.onParentHide]});
	},
	
	destroy: function () {
		if (this.parent) {
			zWatch.unlisten({onHide: [this.parent, this.onParentHide]});
			delete this.parent.__ebox;
		}
		this.close();
		this.unbind();
		jq(this).remove();
		this.parent = null;
	},
	onParentHide: function () {
		if (this.__ebox) {
			this.__ebox.setFloating_(false);
			this.__ebox.close();
		}
	},
	
	bind_: function () {
		this.$supers(zul.inp.Errorbox, 'bind_', arguments);

		var Errorbox = zul.inp.Errorbox;
		this._drag = new zk.Draggable(this, null, {
			starteffect: zk.$void,
			endeffect: Errorbox._enddrag,
			ignoredrag: Errorbox._ignoredrag,
			change: Errorbox._change
		});
		zWatch.listen({onScroll: this});
	},
	unbind_: function () {
		
		var drag = this._drag;
		this._drag = null;
		if (drag)
			drag.destroy();
		zWatch.unlisten({onScroll: this});
		
		
		if (this.parent)
			zWatch.unlisten({onHide: [this.parent, this.onParentHide]});
		
		this.$supers(zul.inp.Errorbox, 'unbind_', arguments);
	},
	getInputNode: function() {
		return this.parent ? this.parent.$n() : null;
	},
	
	onScroll: function (wgt) {
		if (wgt) { 
			if (zul.inp.InputWidget._isInView(this)) {
				if (!this.isOpen()) 
					this.open();
				this.position(this.parent, null, 'end_before', {overflow:true});
				this._fixarrow();
			} else {
				this.close();
			}
		}
	},
	setDomVisible_: function (node, visible) {
		this.$supers('setDomVisible_', arguments);
		var stackup = this._stackup;
		if (stackup) stackup.style.display = visible ? '': 'none';
	},
	doClick_: function (evt) {
		var p = evt.domTarget;
		if (jq.contains(this.$n('cls'), p)) {
			if ((p = this.parent) && p.clearErrorMessage) {
				p.clearErrorMessage(true, true);
				p.focus(0); 
			} else
				zAu.wrongValue_(p, false);
		} else {
			this.$supers('doClick_', arguments);
			this.parent.focus(0);
		}
	},
	open: function () {
		this.$supers('open', arguments);
		this.setTopmost();
		this._fixarrow();
	},
	afterCloseAnima_: function (opts) {
		this.setVisible(false);
		this.setFloating_(false);
		if (opts && opts.sendOnOpen)
			this.fire('onOpen', {open:false});
	},
	redraw: function (out) {
		var uuid = this.uuid,
			icon = this.$s('icon');
		out.push('<div', this.domAttrs_(), '><div id="', uuid, '-p" class="',
				this.$s('pointer'), '"></div><i id="', uuid, '-icon" class="',
				icon, ' z-icon-exclamation-triangle"></i><div id="', uuid,
				'-cave" class="', this.$s('content'), '" title="',
				(zUtl.encodeXML(msgzk.GOTO_ERROR_FIELD)), '">',
				zUtl.encodeXML(this.msg, {multiline:true}),
				'</div><div id="', uuid, '-cls" class="',
				this.$s('close'), '"><i class="', icon,
				' z-icon-times"></i></div></div>');
	},
	onFloatUp: function (ctl) {
		var wgt = ctl.origin;
		if (wgt == this) {
			this.setTopmost();
			return;
		}
		if (!wgt || wgt == this.parent || !this.isVisible())
			return;

		var top1 = this, top2 = wgt;
		while ((top1 = top1.parent) && !top1.isFloating_())
			if (top1 == wgt) 
				return;
		for (; top2 && !top2.isFloating_(); top2 = top2.parent)
			;
		if (top1 == top2) { 
			var n = wgt.$n();
			if (n) this._uncover(n);
		}
	},
	_uncover: function (el) {
		var elofs = zk(el).cmOffset(),
			node = this.$n(),
			nodeofs = zk(node).cmOffset();

		if (jq.isOverlapped(
		elofs, [el.offsetWidth, el.offsetHeight],
		nodeofs, [node.offsetWidth, node.offsetHeight])) {
			var parent = this.parent.$n(), y;
			var ptofs = zk(parent).cmOffset(),
				pthgh = parent.offsetHeight,
				ptbtm = ptofs[1] + pthgh;
			y = elofs[1] + el.offsetHeight <=  ptbtm ? ptbtm: ptofs[1] - node.offsetHeight;
				

			var ofs = zk(node).toStyleOffset(0, y);
			node.style.top = ofs[1] + 'px';
			this._fixarrow();
		}
	},
	_fixarrow: function () {
		var parent = this.parent.$n(),
			node = this.$n(),
			pointer = this.$n('p'),
			ptofs = zk(parent).revisedOffset(),
			nodeofs = zk(node).revisedOffset(),
			dx = nodeofs[0] - ptofs[0], 
			dy = nodeofs[1] - ptofs[1], 
			dir,
			s = node.style
			pw = 2 + (zk(pointer).borderWidth() / 2) || 0,
			ph = 2 + (zk(pointer).borderHeight() / 2) || 0;
		
		
		if (dx >= parent.offsetWidth - pw)
			dir = dy < ph - node.offsetHeight ? 'ld': dy >= parent.offsetHeight - ph ? 'lu': 'l';
		else if (dx < pw - node.offsetWidth)
			dir = dy < ph - node.offsetHeight ? 'rd': dy >= parent.offsetHeight - ph ? 'ru': 'r';
		else
			dir = dy < 0 ? 'd': 'u';
		
		node.style.padding = '0';
		
		if(dir == 'd' || dir == 'u') {
			var md = (Math.max(dx, 0) + Math.min(node.offsetWidth + dx, parent.offsetWidth))/2 - dx - 6,
				mx = node.offsetWidth - 11;
			pointer.style.left = (md > mx ? mx : md < 1 ? 1 : md) + 'px';
			if(dir == 'd') { 
				pointer.style.top = null;
				pointer.style.bottom = '-4px';
				s.paddingBottom = ph + 'px';
			} else {
				pointer.style.top = '-4px';
				s.paddingTop = ph + 'px';
			}
			
		} else if(dir == 'l' || dir == 'r') {
			var md = (Math.max(dy, 0) + Math.min(node.offsetHeight + dy, parent.offsetHeight))/2 - dy - 6,
				mx = node.offsetHeight - 11;
			pointer.style.top = (md > mx ? mx : md < 1 ? 1 : md) + 'px';
			if(dir == 'r') { 
				pointer.style.left = null;
				pointer.style.right = '-4px';
				s.paddingRight = pw + 'px';
			} else {
				pointer.style.left = '-4px';
				s.paddingLeft = pw + 'px';
			}
			
		} else {
			var ps = pointer.style;
			ps.left = ps.top = ps.right = ps.bottom = null;
			switch (dir) {
			case 'lu':
				ps.left = '0px';
				ps.top = '-4px';
				s.paddingTop = ph + 'px';
				break;
			case 'ld':
				ps.left = '0px';
				ps.bottom = '-4px';
				s.paddingBottom = ph + 'px';
				break;
			case 'ru':
				ps.right = '0px';
				ps.top = '-4px';
				s.paddingTop = ph + 'px';
				break;
			case 'rd':
				ps.right = '0px';
				ps.bottom = '-4px';
				s.paddingBottom = ph + 'px';
				break;
			}
			dir = dir == 'ru' || dir == 'lu' ? 'u' : 'd';
		}

		pointer.className = this.$s('pointer') + (_dirMap[dir] ? ' ' + this.$s(_dirMap[dir]) : '');
		jq(pointer).show();
	}
},{
	_enddrag: function (dg) {
		var errbox = dg.control;
		errbox.setTopmost();
		errbox._fixarrow();
	},
	_ignoredrag: function (dg, pointer, evt) {
		return zul.inp.InputCtrl.isIgnoredDragForErrorbox(dg, pointer, evt);
	},
	_change: function (dg) {
		var errbox = dg.control,
			stackup = errbox._stackup,
			el = errbox.$n();
		if (stackup) {
			stackup.style.top = el.style.top;
			stackup.style.left = el.style.left;
		}
		errbox._fixarrow();
		if (zk.mobile)
			zk(el).redoCSS();
	}
});
})();