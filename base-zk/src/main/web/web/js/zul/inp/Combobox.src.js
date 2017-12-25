

zul.inp.Combobox = zk.$extends(zul.inp.ComboWidget, {
	_autocomplete: true,

	$define: {
		
		
		autocomplete: null,
		
		repos: function () {
			if (this.desktop) {
				var n = this.getInputNode(),
					ofs;
				n.value = this.valueEnter_ != null ? this.valueEnter_ : this._value || '';
				
				
				if (zk.ie <= 11 && n.value) {
					ofs = n.value.length;
					ofs = [ofs, ofs];
				}
				this._typeahead(this._bDel, ofs);
				this._bDel = null;
				
				
				var pp = this.getPopupNode_();
				
				if (pp) {
					pp.style.width = 'auto';
					if(zk.webkit) this._shallRedoCss = true ;
				}
			}
			this._repos = false;
		}
	},
	onResponse: function () {
		this.$supers('onResponse',arguments);
		if (this._shallRedoCss) { 
			zk(this.getPopupNode_()).redoCSS(-1);
			this._shallRedoCss = null;
		}
		if (this._shallCheckPopupPosition) {
			this._checkPopupPosition();
			this._shallCheckPopupPosition = false;
		}
		
		if (this.isOpen() && this._shallSyncPopupPosition) {
			zk(this.getPopupNode_()).position(this.getInputNode(), 'before_start');
			this._shallSyncPopupPosition = false;
		}
	},
	setValue: function (val) {
		this.$supers('setValue', arguments);
		this._reIndex();
		this.valueEnter_ = null; 
		this._lastsel = this._sel; 
	},
	_reIndex: function () {
		var value = this.getValue();
		if (!this._sel || value != this._sel.getLabel()) {
			if (this._sel) {
				var n = this._sel.$n();
				if (n) jq(n).removeClass(this._sel.$s('selected'));
			}
			this._sel = this._lastsel = null;
			for (var w = this.firstChild; w; w = w.nextSibling) {
				if (value == w.getLabel()) {
					this._sel = w;
					break;
				}
			}
		}
	},
	
	validateStrict: function (val) {
		var cst = this._cst;
		return this._findItem(val, true) ? null: 
			(cst ? cst._errmsg: '') || msgzul.VALUE_NOT_MATCHED;
	},
	_findItem: function (val, strict) {
		return this._findItem0(val, strict);
	},
	_findItem0: function (val, strict, startswith, excluding) {
		var fchild = this.firstChild;
		if (fchild && val) {
			val = val.toLowerCase();
			var sel = this._sel;
			if (!sel || sel.parent != this) sel = fchild;

			for (var item = excluding ? sel.nextSibling ? sel.nextSibling : fchild : sel;;) {
				if ((!strict || !item.isDisabled()) && item.isVisible()
				&& (startswith ? item.getLabel().toLowerCase().startsWith(val) : val == item.getLabel().toLowerCase()))
					return item;
				if (!(item = item.nextSibling)) item = fchild;
				if (item == sel) break;
			}
		}
	},
	_hilite: function (opts) {
		this._hilite2(
			this._findItem(this.getInputNode().value,
				this._isStrict() || (opts && opts.strict)), opts);
	},
	_hilite2: function (sel, opts) {
		opts = opts || {};

		var oldsel = this._sel;
		this._sel = sel;

		if (oldsel && oldsel.parent == this) { 
			var n = oldsel.$n();
			if (n) {
				jq(n).removeClass(oldsel.$s('selected'));
			}
		}

		if (sel && !sel.isDisabled())
			jq(sel.$n()).addClass(sel.$s('selected'));

		if (opts.sendOnSelect && this._lastsel != sel) {
			this._lastsel = sel;
			if (sel) { 
				var inp = this.getInputNode(),
					val = sel.getLabel();
				this.valueEnter_ = inp.value = val;
				
				
				if (!opts.noSelectRange)
					if (zk.gecko)
						inp.select();
					else
						zk(inp).setSelectionRange(0, val.length);
			}
			
			if (opts.sendOnChange)
				this.$supers('updateChange_', []);

			this.fire('onSelect', {items: sel?[sel]:[], reference: sel, prevSeld: oldsel});
				
				
				
			
		}
	},
	_isStrict: function () {
		var strict = this.getConstraint();
		return strict && strict._flags && strict._flags.STRICT;
	},

	
	getIconClass_: function () {
		return 'z-icon-caret-down';
	},
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		this._shallCheckPopupPosition = true; 
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);
		this._shallCheckPopupPosition = true; 
	},
	open: function (opts) {
		this.$supers('open', arguments);
		this._hilite(); 
	},
	dnPressed_: function (evt) {
		this._updnSel(evt);
	},
	upPressed_: function (evt) {
		this._updnSel(evt, true);
	},
	_updnSel: function (evt, bUp) {
		var inp = this.getInputNode(),
			val = inp.value, sel, looseSel;
		
		if (val || this._sel) {
			val = val.toLowerCase();
			var beg = this._sel,
				last = this._next(null, bUp);
			if (!beg || beg.parent != this){
				beg = this._next(null, !bUp);
			}
			if (!beg) {
				evt.stop();
				return; 
			}

			
			for (var item = beg;;) {
				if (!item.isDisabled() && item.isVisible()) {
					var label = item.getLabel().toLowerCase();
					if (val == label) {
						sel = item;
						break;
					} else if (!looseSel && label.startsWith(val)) {
						looseSel = item;
						break;
					}
				}
				var nextitem = this._next(item, bUp);
				if( item == nextitem ) break;  
				if ((item = nextitem) == beg)
					break;
			}

			if (!sel)
				sel = looseSel;

			if (sel) { 
				var ofs = zk(inp).getSelectionRange();
				if (ofs[0] == 0 && ofs[1] == val.length){ 
					sel = this._next(sel, bUp); 
				}
			} else{
				sel = this._next(null, !bUp);
			}
		} else{
			sel = this._next(null, true);
		}

		if (sel)
			zk(sel).scrollIntoView(this.$n('pp'));
		
		
		this._select(sel, {sendOnSelect: true, sendOnChange: true});
		evt.stop();
	},
	_next: (function() {
		function getVisibleItemOnly(item, bUp, including) {
			var next = bUp ? 'previousSibling' : 'nextSibling';
			for (var n = including ? item : item[next]; n; n = n[next])
				if (!n.isDisabled() && n.isVisible()) 
					return n;
			return null;
		}
		return function(item, bUp) {
			if (item)
				item = getVisibleItemOnly(item, bUp);
			return item ? item : getVisibleItemOnly(
					bUp ? this.firstChild : this.lastChild, !bUp, true);
		};
	})(),
	_select: function (sel, opts) {
		var inp = this.getInputNode(),
			val = inp.value = sel ? sel.getLabel(): '';
		this.valueSel_ = val;
		this._hilite2(sel, opts);

		
		
		
		
		if (val)
			if (zk.gecko)
				inp.select();
			else
				zk(inp).setSelectionRange(0, val.length);
	},
	otherPressed_: function (evt) {
		var wgt = this,
			keyCode = evt.keyCode,
			bDel;
		this._bDel = bDel = keyCode == 8  || keyCode == 46 ;
		if (this._readonly)
			switch (keyCode) {
			case 35:
			case 36:
				this._hilite2();
				this.getInputNode().value = '';
				
			case 37:
			case 39:
				this._updnSel(evt, keyCode == 37 || keyCode == 35);
				break;
			case 8:
				evt.stop();
				break;
			default:
				
				if (keyCode >= 96 && keyCode <= 105)
					keyCode -= 48;
				var v = String.fromCharCode(keyCode);
				var sel = this._findItem0(v, true, true, !!this._sel);
				if (sel)
					this._select(sel, {sendOnSelect: true});
			}
		else
			setTimeout(function () {wgt._typeahead(bDel);}, zk.opera || zk.webkit ? 10 : 0);
			
	},
	_typeahead: function (bDel, ofs) {
		if (zk.currentFocus != this) return;
		var inp = this.getInputNode(),
			val = inp.value,
			ofs = ofs || zk(inp).getSelectionRange(),
			fchild = this.firstChild;
		this.valueEnter_ = val;
		if (!val || !fchild
		|| ofs[0] != val.length || ofs[0] != ofs[1]) 
			return this._hilite({strict:true});

		var sel = this._findItem(val, true);
		if (sel || bDel || !this._autocomplete) {
			
			if (sel && sel.getLabel().toLowerCase().startsWith(val.toLowerCase()) && this._autocomplete)
				inp.value = sel.getLabel();
			return this._hilite2(sel);
		}

		
		val = val.toLowerCase();
		sel = this._sel;
		if (!sel || sel.parent != this) sel = fchild;

		for (var item = sel;;) {
			if (!item.isDisabled() && item.isVisible()
			&& item.getLabel().toLowerCase().startsWith(val)) {
				inp.value = item.getLabel();
				zk(inp).setSelectionRange(val.length, inp.value.length);
				this._hilite2(item);
				return;
			}

			if (!(item = item.nextSibling)) item = fchild;
			if (item == sel) {
				this._hilite2(); 
				return;
			}
		}
	},
	updateChange_: function () {
		var chng = this._value != this.getInputNode().value; 
		if (this.$supers('updateChange_', arguments) && chng) {
			this._hilite({sendOnSelect:true, noSelectRange:true});
			return true;
		}
		this.valueEnter_ = null;
	},
	bind_: function () {
		this.$supers(zul.inp.Combobox, 'bind_', arguments);
		
		if (this.isListen('onOpen'))
			this.listen({onChanging: zk.$void}, -1000);
		
		if (this._value && !this._sel)
			this.setValue(this._value, true);
	},
	unbind_: function () {
		this._hilite2();
		this._sel = this._lastsel = null;
		
		if (this.isListen('onOpen'))
			this.unlisten({onChanging: zk.$void});
		this.$supers(zul.inp.Combobox, 'unbind_', arguments);
	},
	
	redrawpp_: function (out) {
		var uuid = this.uuid;
		out.push('<div id="', uuid, '-pp" class="', this.$s('popup'),
		' ', this.getSclass(), '" style="display:none"><ul id="',
		uuid, '-cave" class="', this.$s('content'), '" >');

		for (var w = this.firstChild; w; w = w.nextSibling)
			w.redraw(out);

		out.push('</ul></div>');
	},
	afterAnima_: function (visible) {
		
		
		if (visible && this._lastsel)
			zk(this._lastsel).scrollIntoView(this.$n('pp'));
		this.$supers('afterAnima_', arguments);
	}
});
