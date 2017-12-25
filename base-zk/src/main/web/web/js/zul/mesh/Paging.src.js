
(function () {
	function _rerenderIfBothPaging(wgt) {
		if (wgt.isBothPaging()) {
			wgt.parent.rerender();
			return true;
		}
	}
	
	
	function _isUnsignedInteger(s) {
		  return (s.toString().search(/^[0-9]+$/) == 0);
	}


zul.mesh.Paging = zk.$extends(zul.Widget, {
	_pageSize: 20,
	_totalSize: 0,
	_pageCount: 1,
	_activePage: 0,
	_pageIncrement: zk.mobile ? 5 : 10,

	$define: { 
    	
    	
		totalSize: function () {
			this._updatePageNum();
			if (this._detailed) {
				if (!_rerenderIfBothPaging(this)) {
					var info = this.$n('info');
					if (info) {
						info.innerHTML = this.infoText_();
					} else if (this._totalSize) {
						this.rerender(); 
					}
				}
			}
		},
		
		
		pageIncrement: _zkf = function () {
			this.rerender();
		},
		
		
		detailed: _zkf,
		
		
		pageCount: _zkf, 
		
		
		activePage: _zkf,
		
		
		pageSize: function () {
			this._updatePageNum();
		},
		
		
		autohide: function () {
			if (this._pageCount == 1) this.rerender();
		}
	},
	setStyle: function () {
		this.$supers('setStyle', arguments);
		_rerenderIfBothPaging(this)
	},
	setSclass: function () {
		this.$supers('setSclass', arguments);
		_rerenderIfBothPaging(this);
	},
	setWidth: function () {
		this.$supers('setWidth', arguments);
		_rerenderIfBothPaging(this);
	},
	setHeight: function () {
		this.$supers('setHeight', arguments);
		_rerenderIfBothPaging(this);
	},
	setLeft: function () {
		this.$supers('setLeft', arguments);
		_rerenderIfBothPaging(this);
	},
	setTop: function () {
		this.$supers('setTop', arguments);
		_rerenderIfBothPaging(this);
	},
	setTooltiptext: function () {
		this.$supers('setTooltiptext', arguments);
		_rerenderIfBothPaging(this);
	},
	replaceHTML: function () {
		if (!_rerenderIfBothPaging(this))
			this.$supers('replaceHTML', arguments);
	},
	
	isBothPaging: function () {
		return this.parent && this.parent.getPagingPosition
					&& 'both' == this.parent.getPagingPosition();
	},
	_updatePageNum: function () {
		var v = Math.floor((this._totalSize - 1) / this._pageSize + 1);
		if (v == 0) v = 1;
		if (v != this._pageCount) {
			this._pageCount = v;
			if (this._activePage >= this._pageCount)
				this._activePage = this._pageCount - 1;
			if (this.desktop && this.parent) {
				if (!_rerenderIfBothPaging(this)) {
					this.rerender();

					
					if (this.parent.$instanceof(zul.mesh.MeshWidget)) {
						var self = this;
						
						setTimeout(function () {
							if (self.desktop) {
								var n = self.parent.$n();
		
								
								if (n && n._lastsz) {
									n._lastsz = null;
									self.parent.onSize();
								}
							}
						});
					}
				}
			}
		}
	},
	
	infoText_: function () {
		var acp = this._activePage,
			psz = this._pageSize,
			tsz = this._totalSize,
			lastItem = (acp + 1) * psz,
			dash = '';
		
		if ('os' != this.getMold())
			dash = ' - ' + (lastItem > tsz ? tsz : lastItem);
		
		return '[ ' + (acp * psz + 1) + dash + ' / ' + tsz + ' ]';
	},
	_infoTags: function (out) {
		if (this._totalSize == 0)
			return;
		out.push('<div class="', this.$s('info'), '"><span ',
				_rerenderIfBothPaging(this) ? 'name' : 'id', 
				'="', this.uuid,
				'-info">', this.infoText_(), '</span></div>');
	},
	_innerTags: function () {
		var out = [],
			pinc = this._pageIncrement,
			pcount = this._pageCount,
			acp = this._activePage,
			half = Math.round(pinc / 2),
			begin,
			end = this._activePage + half - 1;
		
		if (end >= pcount) {
			end = pcount - 1;
			begin = end - pinc + 1;
			if (begin < 0)
				begin = 0;
		} else {
			begin = this._activePage - half;
			if (begin < 0)
				begin = 0;
			end = begin + pinc - 1;
			if (end >= pcount)
				end = pcount - 1;
		}
		out.push('<ul>');
		if (acp > 0) {
			if (begin > 0) 
				this.appendAnchor(out, msgzul.FIRST, 0);
			this.appendAnchor(out, msgzul.PREV, acp - 1);
		}

		var bNext = acp < pcount - 1;
		for (; begin <= end; ++begin)
			this.appendAnchor(out, begin + 1, begin, begin == acp);

		if (bNext) {
			this.appendAnchor(out, msgzul.NEXT, acp + 1);
			if (end < pcount - 1) 
				this.appendAnchor(out, msgzul.LAST, pcount - 1);
		}
		out.push('</ul>');
		if (this._detailed)
			this._infoTags(out);
		return out.join('');
	},
	appendAnchor: function (out, label, val, seld) {
		var isInt = _isUnsignedInteger(label),
			cls = this.$s('button');
		
		if (!isInt)
			cls += ' ' + this.$s('noborder');
		if (seld)
			cls += ' ' + this.$s('selected');
		
		out.push('<li><a class="', cls,
				'" href="javascript:;" onclick="zul.mesh.Paging.go(this,', val,
				')">', label, '</a></li>');
	},
	domClass_: function () {
		var cls = this.$supers(zul.mesh.Paging, 'domClass_', arguments),
			added = 'os' == this.getMold() ? ' ' + this.$s('os') : '';
		return cls + added;
	},
	isVisible: function () {
		var visible = this.$supers('isVisible', arguments);
		return visible && (this._pageCount > 1 || !this._autohide);
	},
	bind_: function () {
		this.$supers(zul.mesh.Paging, 'bind_', arguments);
		var uuid = this.uuid,
			input = jq.$$(uuid, 'real'),
			Paging = this.$class,
			pcount = this._pageCount,
			acp = this._activePage,
			postfix = ['first', 'prev', 'last', 'next'],
			focusInfo = zul.mesh.Paging._autoFocusInfo;

		if (!this.$weave)
			for (var i = input.length; i--;)
				jq(input[i]).keydown(Paging._domKeyDown).blur(Paging._domBlur);

		for (var k = postfix.length; k--; ) {
			var btn = jq.$$(uuid, postfix[k]);
			for (var j = btn.length; j--;) {
				if (!this.$weave)
					jq(btn[j]).click(Paging['_dom' + postfix[k] + 'Click']);
	
				if (pcount == 1) {
					jq(btn[j]).attr('disabled', true);
				} else if (postfix[k] == 'first' || postfix[k] == 'prev') {
					if (acp == 0)
						jq(btn[j]).attr('disabled', true);
				} else if (acp == pcount - 1) {
					jq(btn[j]).attr('disabled', true);
				}
			}
		}
		
		if(focusInfo && focusInfo.uuid === this.uuid) {			
			var pos = focusInfo.lastPos,
				zinp = zk(input[focusInfo.inpIdx]);
			zinp.focus();
			zinp.setSelectionRange(pos[0], pos[1]);
			zul.mesh.Paging._autoFocusInfo = null;
		}
	},
	unbind_: function () {
		if (this.getMold() != 'os') {
			var uuid = this.uuid,
				input = jq.$$(uuid, 'real'),
				Paging = this.$class,
				postfix = ['first', 'prev', 'last', 'next'];

			for (var i = input.length; i--;)
				jq(input[i])
					.unbind('keydown', Paging._domKeyDown)
					.unbind('blur', Paging._domBlur);

			for (var k = postfix.length; k--;) {
				var btn = jq.$$(uuid, postfix[k]);
				for (j = btn.length; j--;)
					jq(btn[j]).unbind('click', Paging['_dom' + postfix[k] + 'Click']);
			}
		}
		this.$supers(zul.mesh.Paging, 'unbind_', arguments);
	}
}, { 
	
	go: function (anc, pgno, inp) {
		var wgt = zk.Widget.isInstance(anc) ? anc : zk.Widget.$(anc);
		if (wgt && wgt.getActivePage() != pgno) {
			if(inp) {
				var uuid = wgt.uuid,
					focusInfo = zul.mesh.Paging._autoFocusInfo = {uuid: uuid};
				focusInfo.lastPos = zk(inp).getSelectionRange();
				
				jq(jq.$$(uuid, 'real')).each(function(idx){
					if(this == inp) {
						focusInfo.inpIdx = idx;
						return false;
					}
				});
			}
			wgt.fire('onPaging', pgno);
		}
	},
	_domKeyDown: function (evt) {
		var inp = evt.target,
			wgt = zk.Widget.$(inp),
			lastPos = zk(inp).getSelectionRange();
		if (inp.disabled || inp.readOnly)
			return;

		var code = evt.keyCode;
		switch(code){
		case 48:case 96:
		case 49:case 97:
		case 50:case 98:
		case 51:case 99:
		case 52:case 100:
		case 53:case 101:
		case 54:case 102:
		case 55:case 103:
		case 56:case 104:
		case 57:case 105:
			break;
		case 37:
			break;
		case 38: 
			wgt.$class._increase(inp, wgt, 1);
			evt.stop();
			break;
		case 39:
			break;
		case 40: 
			wgt.$class._increase(inp, wgt, -1);
			evt.stop();
			break;
		case 33: 
			wgt.$class._increase(inp, wgt, -1);
			wgt.$class.go(wgt, inp.value-1, inp);
			evt.stop();
			break;
		case 34: 
			wgt.$class._increase(inp, wgt, +1);
			wgt.$class.go(wgt, inp.value-1, inp);
			evt.stop();
			break;
		case 36:
			wgt.$class.go(wgt,0, inp);
			evt.stop();
			break;
		case 35:
			wgt.$class.go(wgt, wgt._pageCount - 1, inp);
			evt.stop();
			break;
		case 9: case 8: case 46: 
			break;
		case 13: 
			wgt.$class._increase(inp, wgt, 0);
			wgt.$class.go(wgt, inp.value-1, inp);
			evt.stop();
			break;
		default:
			if (!(code >= 112 && code <= 123) 
					&& !evt.ctrlKey && !evt.altKey)
				evt.stop();
		}
	},
	_domBlur: function (evt) {
		var inp = evt.target,
			wgt = zk.Widget.$(inp);
		if (inp.disabled || inp.readOnly)
			return;

		wgt.$class._increase(inp, wgt, 0);
		wgt.$class.go(wgt, inp.value-1);
		evt.stop();
	},
	_increase: function (inp, wgt, add){
		var value = zk.parseInt(inp.value);
		value += add;
		if (value < 1)
			value = 1;
		else if (value > wgt._pageCount)
			value = wgt._pageCount;
		inp.value = value;
	},
	_domfirstClick: function (evt) {
		var wgt = zk.Widget.$(evt),
			uuid = wgt.uuid,
			postfix = ['first', 'prev'];
		
		if (wgt.getActivePage() != 0) {
			wgt.$class.go(wgt, 0);
			for (var k = postfix.length; k--;)
				for (var btn = jq.$$(uuid, postfix[k]), i = btn.length; i--;)
					jq(btn[i]).attr('disabled', true);
		}
	},
	_domprevClick: function (evt) {
		var wgt = zk.Widget.$(evt),
			uuid = wgt.uuid,
			ap = wgt.getActivePage(),
			postfix = ['first', 'prev'];

		if (ap > 0) {
			wgt.$class.go(wgt, ap - 1);
			if (ap - 1 == 0) {
				for (var k = postfix.length; k--;)
					for (var btn = jq.$$(uuid, postfix[k]), i = btn.length; i--;)
						jq(btn[i]).attr('disabled', true);
			}
		}
	},
	_domnextClick: function (evt) {
		var wgt = zk.Widget.$(evt),
			uuid = wgt.uuid,
			ap = wgt.getActivePage(),
			pc = wgt.getPageCount(),
			postfix = ['last', 'next'];

		if (ap < pc - 1) {
			wgt.$class.go(wgt, ap + 1);
			if (ap + 1 == pc - 1) {
				for (var k = postfix.length; k--;)
					for (var btn = jq.$$(uuid, postfix[k]), i = btn.length; i--;)
						jq(btn[i]).attr('disabled', true);
			}
		}
	},
	_domlastClick: function (evt) {
		var wgt = zk.Widget.$(evt),
			uuid = wgt.uuid,
			pc = wgt.getPageCount(),
			postfix = ['last', 'next'];

		if (wgt.getActivePage() < pc - 1) {
			wgt.$class.go(wgt, pc - 1);
			for (var k = postfix.length; k--;)
				for (var btn = jq.$$(uuid, postfix[k]), i = btn.length; i--;)
					jq(btn[i]).attr('disabled', true);
		}
	}
});

})();