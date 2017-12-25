

zul.wgt.Popup = zk.$extends(zul.Widget, {
	_visible: false,
	
	isOpen: function () {
		return this.isVisible();
	},
	
	
	open: function (ref, offset, position, opts) {
		var posInfo = this._posInfo(ref, offset, position),
			node = this.$n(),
			top = node.style.top,
			$n = jq(node);
		
		
		
		
		
		
		this._openInfo = arguments;
		
		
		this._shallToggle = opts && opts.type == 'toggle';

		$n.css({position: 'absolute'}).zk.makeVParent();
		
		
		zWatch.fire('onVParent', this);

		if (posInfo)
			$n.zk.position(posInfo.dim, posInfo.pos, opts);
		
		this.setFloating_(true); 
		this.setTopmost();
		this.openAnima_(ref, offset, position, opts);
	},
	
	openAnima_: function (ref, offset, position, opts) {
		this.afterOpenAnima_(ref, offset, position, opts);
	},
	
	afterOpenAnima_: function (ref, offset, position, opts) {
		var node = this.$n();
		this.setVisible(true);
		if ((!opts || !opts.disableMask) && this.isListen('onOpen', {asapOnly:true})) {
			
			if (this.mask) this.mask.destroy(); 
			
			
			this.mask = new zk.eff.Mask({
				id: this.uuid + '-mask',
				anchor: node
			});
			
			
			
			zWatch.listen({onResponse: this});		
		}
		
		
		if (this.shallStackup_() && node) {
			if (!this._stackup)
				this._stackup = jq.newStackup(node, node.id + '-stk');
			else {
				var dst, src;
				(dst = this._stackup.style).top = (src = node.style).top;
				dst.left = src.left;
				dst.zIndex = src.zIndex;
				dst.display = 'block';
			}
		}
		ref = zk.Widget.$(ref); 
		if (opts && opts.sendOnOpen) this.fire('onOpen', {open: true, reference: ref});
		
		jq(node).addClass(this.$s('open'));
		
		
		var openInfo = this._openInfo;
		if (openInfo) {
			this.position.apply(this, openInfo);
		}
	},
	
	shallStackup_: function () {
		return zk.eff.shallStackup();
	},
	
	position: function (ref, offset, position, opts) {
		var posInfo = this._posInfo(ref, offset, position);
		if (posInfo)
			zk(this.$n()).position(posInfo.dim, posInfo.pos, opts);
	},
	_posInfo: function (ref, offset, position, opts) {
		var pos, dim;
		
		if (position) {
			if (ref) {
				if (typeof ref == 'string')
					ref = zk.Widget.$(ref);
					
				if (ref) {
					var refn = zul.Widget.isInstance(ref) ? ref.$n() : ref;
					
					if (refn) {
						pos = position;
						dim = zk(refn).dimension(true);
					} else 
						return {pos: position};
				}
			} else
				return {pos: position};
		} else if (jq.isArray(offset)) {
			dim = {
				left: zk.parseInt(offset[0]), top: zk.parseInt(offset[1]),
				width: 0, height: 0
			}
		}
		if (dim) {
			
			var $n = zk(this.$n());
			dim.top += $n.sumStyles('t', jq.margins);
			dim.left += $n.sumStyles('l', jq.margins);
			return {pos: pos, dim: dim};
		}
	},
	onResponse: function () {
		if (this.mask) this.mask.destroy();
		
		var openInfo = this._openInfo;
		if (openInfo) {
			this.position.apply(this, openInfo);
			this._openInfo = null;
		}
		zWatch.unlisten({onResponse: this});
		this.mask = null;
	},
	
	close: function (opts) {
		if (this._stackup)
			this._stackup.style.display = 'none';
		
		this._shallToggle = false;
		this.closeAnima_(opts);  
	},
	
	closeAnima_: function (opts) {
		this.afterCloseAnima_(opts);
	},
	
	afterCloseAnima_: function (opts) {
		this.setVisible(false);
		
		var node = this.$n();
		zk(node).undoVParent();
		zWatch.fireDown('onVParent', this);

		this.setFloating_(false);
		if (opts && opts.sendOnOpen)
			this.fire('onOpen', {open:false});
		
		if (zk.ie < 11) { 
			var that = this;
			setTimeout(function() {
				that.replaceHTML(node); 
			}, 50);
		}
		
		jq(node).removeClass(this.$s('open'));
	},
	onFloatUp: function(ctl, opts){
		if (!this.isVisible()) 
			return;
		var openInfo = this._openInfo,
			length = ctl.args.length;
		
		
		if (this._shallToggle && openInfo && opts && (
				opts.triggerByClick === undefined || (
				openInfo[3].which == opts.triggerByClick && zUtl.isAncestor(this._openInfo[0], ctl.origin)))) {
				return;
		}
		this._doFloatUp(ctl);
	},
	_doFloatUp: function (ctl) {
		if (!this.isVisible()) 
			return;
		var wgt = ctl.origin;
		for (var floatFound; wgt; wgt = wgt.parent) {
			if (wgt == this) {
				if (!floatFound) 
					this.setTopmost();
				return;
			}
			if (wgt == this.parent && wgt.ignoreDescendantFloatUp_(this))
				return;
			floatFound = floatFound || wgt.isFloating_();
		}
		this.close({sendOnOpen:true});
	},
	onVParent: function (ctl) {
		
		if (this._shallToggle)
			this._doFloatUp(ctl);
	},
	bind_: function () {
		this.$supers(zul.wgt.Popup, 'bind_', arguments);
		zWatch.listen({onFloatUp: this, onShow: this, onVParent: this});
		this.setFloating_(true);
	},
	unbind_: function () {
		zk(this.$n()).undoVParent(); 
		if (this._stackup) {
			jq(this._stackup).remove();
			this._stackup = null;
		}
		if (this._openInfo)
			this._openInfo = null;
		this._shallToggle = null;
		zWatch.unlisten({onFloatUp: this, onShow: this, onVParent: this});
		this.setFloating_(false);
		this.$supers(zul.wgt.Popup, 'unbind_', arguments);
	},
	onShow: function (ctl) {
		
		ctl.fire(this.firstChild);
		var openInfo = this._openInfo;
		if (openInfo) {
			this.position.apply(this, openInfo);
			
			
		}
		zk(this).redoCSS(-1, {'fixFontIcon': true});
	},
	setHeight: function (height) {
		this.$supers('setHeight', arguments);
		if (this.desktop)
			zUtl.fireShown(this);
	},
	setWidth: function (width) {
		this.$supers('setWidth', arguments);
		if (this.desktop)
			zWatch.fireDown('onShow', this);
	},
	prologHTML_: function (out) {
	},
	epilogHTML_: function (out) {
	}
});
