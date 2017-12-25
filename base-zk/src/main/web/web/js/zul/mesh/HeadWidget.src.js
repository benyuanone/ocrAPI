
(function () {
	
	var _fixOnChildChanged = zk.opera ? function (head) {
		return (head = head.parent) && head.rerender(); 
	} : zk.$void;

	function _syncFrozen(wgt) {
		var mesh = wgt.getMeshWidget(), frozen;
		if (mesh && (frozen = mesh.frozen)) {
			var hdfaker;
			if (mesh._nativebar) {
				frozen._syncFrozen();
			} else if ((hdfaker = mesh.ehdfaker)) {
				
				frozen._scrollScale = 
					hdfaker.childNodes.length - frozen._columns - 1;
				
				
				frozen._shallSyncScale = false;
			}
		}
	}

var HeadWidget =

zul.mesh.HeadWidget = zk.$extends(zul.Widget, {
	$init: function () {
		this.$supers('$init', arguments);
		this.listen({onColSize: this}, -1000);
	},
	
	$define: {
		
		
		sizable: function () {
			this.rerender();
		},
		
		visible: function () {
			this.rerender();
			var mesh = this.getMeshWidget();
			setTimeout(function() {
				
				if (mesh && mesh.desktop) {
					
					
					var foot = mesh.$n('foot'),
						pgib = mesh.$n('pgib'),
						hgh = zk(mesh).contentHeight() - mesh.$n('head').offsetHeight 
						- (foot ? foot.offsetHeight : 0) - (pgib ? pgib.offsetHeight : 0)
						- (mesh._nativebar && mesh.frozen ? mesh.frozen.$n().offsetHeight : 0) 
					mesh.ebody.style.height = jq.px0(hgh);
				}
			}, 0);
		}
	},
	
	removeChildHTML_: function (child) {
		this.$supers('removeChildHTML_', arguments);
		if (!this.$instanceof(zul.mesh.Auxhead))
			for (var faker, fs = child.$class._faker, i = fs.length; i--;)
				jq(child.uuid + '-' + fs[i], zk).remove();
	},
	
	
	setVflex: function (v) { 
		v = false;
		this.$super(HeadWidget, 'setVflex', v);
	},
	
	setHflex: function (v) { 
		v = false;
		this.$super(HeadWidget, 'setHflex', v);
	},

	
	getMeshWidget: function () {
		return this.parent;
	},

	onColSize: function (evt) {
		var owner = this.parent;
		evt.column._width = evt.width;
		owner._innerWidth = owner.eheadtbl.width || owner.eheadtbl.style.width;
		owner.fire('onInnerWidth', owner._innerWidth);
		owner.fireOnRender(zk.gecko ? 200 : 60);
	},

	bind_: function (desktop, skipper, after) {
		this.$supers(HeadWidget, 'bind_', arguments);
		var w = this;
		after.push(function () {
			_syncFrozen(w);
		});
		
		this.fixBorder_();
	},
	
	fixBorder_: function() {
		var fc = jq(this).children(':first-child'),
			rspan = fc.attr('rowspan'),
			times = parseInt(rspan) - 1;
		if (rspan && times > 0) {
			for (var head = this.nextSibling; head && times != 0; head = head.nextSibling, times--) 
				jq(head.firstChild).addClass(this.$s('border'))
		}
		
	},
	unbind_: function () {
		jq(this.hdfaker).remove();
		jq(this.bdfaker).remove();
		jq(this.ftfaker).remove();
		this.$supers(HeadWidget, 'unbind_', arguments);
	},
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		if (this.desktop) {
			if (!_fixOnChildChanged(this) && this.parent._fixHeaders()) {
				
				this.parent._syncSize();
			}
			_syncFrozen(this);
			this.parent._minWd = null;
			var mesh = this.getMeshWidget();
			
			
			if (this.$instanceof(zul.mesh.Auxhead)) {
				var frozen = mesh ? mesh.frozen : null;
				if (frozen) {
					frozen.onSize();
				}
				return;
			}
			
			
			var head = this,
				fakers = ['hdfaker', 'bdfaker', 'ftfaker'];
			
			for (var i = 0; i < fakers.length; i++) {
				faker = fakers[i];
				var $faker = jq(mesh['e' + faker]);
				if ($faker[0] != null && $faker.find(child.$n(faker))[0] == null) {
					var wd = child._hflexWidth ? child._hflexWidth + 'px' : child.getWidth(),
						visible = !child.isVisible() ? 'display:none;' : '';
					wd = wd ? 'width:' + wd + ';' : '';
					
					
					var html = '<col id="' + child.uuid + '-' + faker + '" style="' + wd + visible + '"/>',
						$bar = jq(mesh).find('.' + head.$s('bar')), 
						bar = $bar[0],
						$hdfakerbar = jq(head.$n('hdfaker')).find('[id*=hdfaker-bar]'),
						hdfakerbar = $hdfakerbar[0],
						barstyle = '', hdfakerbarstyle ='',
						recoverFakerbar = !mesh.frozen ? zk(mesh.ebody).hasVScroll() : false,
						index = child.getChildIndex();

					
					
					if ((faker == 'hdfaker') && bar) {
						var s;
						if (s = bar.style) {
							
							
							barstyle += s.width ? 'width:' + s.width + ';' : '';
						}
						$bar.remove();
		            
						if (recoverFakerbar && hdfakerbar && (s = hdfakerbar.style)) {
							hdfakerbarstyle = s.display ? 'display:' + s.display + ';' : '';
							hdfakerbarstyle += s.width ? 'width:' + s.width + ';' : '';
						}
						$hdfakerbar.remove();
					}
					
					
					if (index > 0)
						jq($faker.find('col')[index - 1]).after(html);
					else 
						$faker.append(html);
	          
					
					$bar = jq(mesh).find('.' + head.$s('bar'));
					bar = $bar[0];
					$hdfakerbar = jq(head.$n('hdfaker')).find('[id*=hdfaker-bar]');
					hdfakerbar = $hdfakerbar[0];
	          
					if ((faker == 'hdfaker') && !bar && recoverFakerbar) {
						if (!hdfakerbar)
							jq(head.$n('hdfaker')).append('<col id="' + head.uuid + '-hdfaker-bar" style="' + hdfakerbarstyle + '" />')
						jq(head).append('<th id="' + head.uuid + '-bar" class="' + head.$s('bar') + '" style="' + barstyle + '" />');
					}
				}
			}
	    }
	},
	onChildRemoved_: function () {
		this.$supers('onChildRemoved_', arguments);
		if (this.desktop) {
			if (!_fixOnChildChanged(this) && !this.childReplacing_ &&
				this.parent._fixHeaders()) 
				this.parent.onSize();
			this.parent._minWd = null;
			
			if (!zk.safari) {
				var mesh = this.getMeshWidget();
				mesh.rerender(1);
			}
		}
	},
	beforeChildrenFlex_: function (hwgt) { 
		if (hwgt && !hwgt._flexFixed) {
			
			
			var wgt = this.parent,
				hdfaker = wgt.ehdfaker,
				bdfaker = wgt.ebdfaker,
				hdf = hdfaker ? hdfaker.firstChild : null,
				bdf = bdfaker ? bdfaker.firstChild : null,
				everFlex = false; 
			for (var h = this.firstChild; h; h = h.nextSibling) {
				
				if (h.isVisible() && h._nhflex > 0) { 
					everFlex = true;
					if (hdf) hdf.style.width = '';
					if (bdf) bdf.style.width = '';
				}
				if (hdf) hdf = hdf.nextSibling;
				if (bdf) bdf = bdf.nextSibling;
			}
		}
		return true;
	},
	afterChildrenFlex_: function (hwgt) { 
		var wgt = this.parent,
			ebody = wgt.ebody,
			ehead = wgt.ehead,
			efoot = wgt.efoot,
			currentLeft = wgt._currentLeft;
		if (wgt) {
			wgt._adjFlexWd();
			wgt._adjSpanWd(); 
			
			if (zk(ebody).hasHScroll() && currentLeft != ebody.scrollLeft) {
				ebody.scrollLeft = currentLeft;
				if (ehead)
					ehead.scrollLeft = currentLeft;
				if (efoot)
					efoot.scrollLeft = currentLeft;
			}
		}
	},
	deferRedrawHTML_: function (out) {
		out.push('<tr', this.domAttrs_({domClass:1}), ' class="z-renderdefer"></tr>');
	}
},{ 
	redraw: function (out) {
		out.push('<tr', this.domAttrs_(), ' style="text-align: left;">');
		for (var w = this.firstChild; w; w = w.nextSibling)
			w.redraw(out);

		var mesh = this.getMeshWidget();
		if (!mesh.frozen)
			out.push('<th id="', this.uuid, '-bar" class="', this.$s('bar'), '" />');

		out.push('</tr>');
	}
});

})();