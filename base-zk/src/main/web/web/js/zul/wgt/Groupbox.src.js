

zul.wgt.Groupbox = zk.$extends(zul.ContainerWidget, {
	_open: true,
	_closable: true,

	$define: { 
		
		
		open: function (open, fromServer) {
			var node = this.$n();
			if (node && this._closable) {
				if (open) {
					jq(node).removeClass(this.$s('collapsed'));
					zk(this).redoCSS(-1, {'fixFontIcon': true});
				}
				zk(this.getCaveNode())[open ? 'slideDown' : 'slideUp'](this);			
				
				if (!fromServer) this.fire('onOpen', {open:open});
			}			
		},
		
		
		closable: _zkf = function () {
			this._updDomOuter();
		},
		
		
		contentStyle: _zkf,
		
		
		contentSclass: _zkf,
		
		
		title: _zkf
	},
	_isDefault: function () {
		return this._mold == 'default';
	},
	_updDomOuter: function () {
		this.rerender(zk.Skipper.nonCaptionSkipper);
	},
	_contentAttrs: function () {
		var html = ' class="', s = this._contentSclass;
		if (s)
			html += s + ' ';
		html += this.$s('content') + '"';

		s = this._contentStyle;
		if (this.caption || this.getTitle()) 
			s = 'border-top:0;' + (s||'');
		if (!this._open)
			s = 'display:none;' + (s||'');
		if (s)
			html += ' style="' + s + '"';
		return html;
	},
	_redrawCave: function (out, skipper) { 
		out.push('<div id="', this.uuid, '-cave"', this._contentAttrs(), '>');

		if (!skipper)
			for (var w = this.firstChild, cap = this.caption; w; w = w.nextSibling)
				if (w != cap)
					w.redraw(out);

		out.push('</div>');
	},

	setHeight: function () {
		this.$supers('setHeight', arguments);
		if (this.desktop) this._fixHgh();
	},
	_fixHgh: function () {
		var hgh = this.$n().style.height;
		if (hgh && hgh != 'auto' && this.isOpen()) {
			var n;
			if (n = this.$n('cave')) {
				var $n = zk(n);
				
				n.style.height = ($n.revisedHeight($n.vflexHeight(), true) - 
								 (this._isDefault() ? parseInt(jq(this).css('padding-top')) : 0)) + 'px';
					
					
					
			}
		}
		if (this._isDefault()) {
			var title = this.$n('title'),
				cap = this.caption;
			if (cap)
				cap.$n().style.top = jq.px(zk(cap.$n('cave')).offsetHeight() / 2 * -1);
			if (title)
				title.style.top = jq.px(zk(this.$n('title-cnt')).offsetHeight() / 2 * -1);
		}
	},
	
	setFlexSizeH_: function(n, zkn, height, isFlexMin) {
		if (isFlexMin && (this.caption || this._title)) {
			
			var node = this.$n(),
				c;
			height = this._isDefault() ? jq(this.$n('header')).outerHeight() : 0;
			for (c = n.firstChild; c; c = c.nextSibling)
				height += jq(c).outerHeight();
		}

		this.$supers('setFlexSizeH_', arguments);
	},
	
	onSize: function () {
		this._fixHgh();
		
		
		
	},
	updateDomStyle_: function () {
		this.$supers('updateDomStyle_', arguments);
		if (this.desktop) this.onSize();
	},

	
	focus_: function (timeout) {
		var cap = this.caption;
		for (var w = this.firstChild; w; w = w.nextSibling)
			if (w != cap && w.focus_(timeout))
				return true;
		return cap && cap.focus_(timeout);
	},
	bind_: function () {
		this.$supers(zul.wgt.Groupbox, 'bind_', arguments);
		zWatch.listen({onSize: this});
		var tt;
		if (this.getTitle() && (tt = this.$n('title')))
			this.domListen_(tt, 'onClick', '_doTitleClick');
		if(zk.ie == 8) 
			zk(this).redoCSS();
		
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this});
		var tt;
		if (tt = this.$n('title'))
			this.domUnlisten_(tt, 'onClick', '_doTitleClick');
		this.$supers(zul.wgt.Groupbox, 'unbind_', arguments);
	},
	
	_doTitleClick: function () {
		this.setOpen(!this.isOpen());
		this.$supers('doClick_', arguments);
	},
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		if (child.$instanceof(zul.wgt.Caption)) {
			this.caption = child;
			this.rerender();
		}
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);
		if (child == this.caption) {
			this.caption = null;
			this.rerender();
		}
	},
	
	getChildMinSize_: function (attr, wgt) {
		if (!wgt.$instanceof(zul.wgt.Caption))
			return this.$supers('getChildMinSize_', arguments);
	},

	domClass_: function () {
		var cls = this.$supers('domClass_', arguments);
		if (!this._isDefault()) {
			if (cls) cls += ' ';
			cls += this.$s('3d');
		}
		
		if (!this.caption && !this.getTitle()) {
			if (cls) cls += ' ';
			cls += ' '+ this.$s('notitle');
		}
			
		if (!this._open && this._isDefault()) {
			if (cls) cls += ' ';
			cls += this.$s('collapsed');
		}
		return cls;
	},
	afterAnima_: function (visible) {
		if (!visible && this._isDefault())
			jq(this.$n()).addClass(this.$s('collapsed'));		
				
		this.$supers('afterAnima_', arguments);
		
		
		var p = this.parent;
		for (var c = p.firstChild; c; c = c.nextSibling) {
			if (c == this)
				continue;
			var vflex = c.getVflex();
			if (vflex && vflex != 'min') {
				zUtl.fireSized(p);
				break;
			}
		}
	}
});
