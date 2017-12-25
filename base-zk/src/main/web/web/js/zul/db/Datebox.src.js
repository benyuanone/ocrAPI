
(function () {
	function _initPopup () {
		this._pop = new zul.db.CalendarPop();
		this._tm = new zul.db.CalendarTime();
		this.appendChild(this._pop);
		this.appendChild(this._tm);
	}
	function _reposition(db, silent) {
		if (!db.$n()) return;
		var pp = db.$n('pp'),
			inp = db.getInputNode();

		if(pp) {
			zk(pp).position(inp, 'after_start');
			db._pop.syncShadow();
			if (!silent)
				zk(inp).focus();
		}
	}
	function _blurInplace(db) {
		zul.inp.RoundUtl.doBlur_(db);
	}
	function _equalDate(d1, d2) {
		return (d1 == d2) || (d1 && d2 && d1.getTime() == d2.getTime());
	}
	function _prepareTimeFormat(h, m, s) {
		var o =[];
		if (h) o.push(h);
		if (m) o.push(m);
		if (s) o.push(s);
		return o.join(':');
	}
	
var globallocalizedSymbols = {},
	_quotePattern = /\'/g, 
	_innerDateFormat = 'yyyy/MM/dd ',
	Datebox =

zul.db.Datebox = zk.$extends(zul.inp.FormatWidget, {
	_buttonVisible: true,
	_lenient: true,
	$init: function() {
		this.$supers('$init', arguments);
		this.afterInit(_initPopup);
		this.listen({onChange: this}, -1000);
	},

	$define: {
		
		
		buttonVisible: function (v) {
			zul.inp.RoundUtl.buttonVisible(this, v);
		},
		
		
		format: function () {
			if (this._pop) {
				this._pop.setFormat(this._format);
				if (this._value)
					this._value = this._pop.getTime();
			}
			var inp = this.getInputNode();
			if (inp)
				inp.value = this.getText();
		},
		
		
		constraint: function (cst) {
			if (typeof cst == 'string' && cst.charAt(0) != '[')
				this._cst = new zul.inp.SimpleDateConstraint(cst, this);
			else
				this._cst = cst;
			if (this._cst)
				this._reVald = true; 
			if (this._pop) {
				this._pop.setConstraint(this._constraint);
				this._pop.rerender();
			}
		},
		
		
		timeZone: function (timezone) {
			this._timezone = timezone;
			this._setTimeZonesIndex();
		},
		
		
		timeZonesReadonly: function (readonly) {
			var select = this.$n('dtzones');
			if (select) select.disabled = readonly ? 'disabled' : '';
		},
		
		
		displayedTimeZones: function (dtzones) {
			this._dtzones = dtzones ? dtzones.split(',') : null;
		},
		
		
		unformater: function (unf) {
			eval('Datebox._unformater = ' + unf);
		},
		
		
		lenient: null,
		localizedSymbols: [
			function (val) {
				if(val) {
					if (!globallocalizedSymbols[val[0]])
						globallocalizedSymbols[val[0]] = val[1];
					return globallocalizedSymbols[val[0]];
				}
				return val;
			},
			function () {
				
				
				if (this._tm)
					this._tm._localizedSymbols = this._localizedSymbols;
				if (this._pop)
					this._pop.setLocalizedSymbols(this._localizedSymbols);
			}
		],
		
	    
		weekOfYear: function (v) {
			if (this._pop)
				this._pop.setWeekOfYear(v);
		}
	},
	getIconClass_: function () {
		return 'z-icon-calendar';
	},
	inRoundedMold: function () {
		return true;
	},
	_setTimeZonesIndex: function () {
		var select = this.$n('dtzones');
		if (select && this._timezone) {
			var opts = jq(select).children('option');
			for (var i = opts.length; i--;) {
				if (opts[i].text == this._timezone) select.selectedIndex = i;
			}
		}
	},
	onSize: function () {
		zul.inp.RoundUtl.onSize(this);
	},
	
	getTimeFormat: function () {
	
		var fmt = this._format,
			aa = fmt.indexOf('a'),
			hh = fmt.indexOf('h'),
			KK = fmt.indexOf('K'),
			HH= fmt.indexOf('HH'),
			kk = fmt.indexOf('k'),
			mm = fmt.indexOf('m'),
			ss = fmt.indexOf('s'),
			hasAM = aa > -1,
			
			hasHour1 = (hasAM || hh) ? hh > -1 || KK > -1 : false,
			hv,
			mv = mm > -1 ? 'mm' : '',
			sv = ss > -1 ? 'ss' : '';
		
		if (hasHour1) {
			var time = _prepareTimeFormat(hh < KK ? 'KK' : 'hh', mv, sv);
			if (aa == -1) 
				return time;
			else if ((hh != -1 && aa < hh) || (KK != -1 && aa < KK)) 
				return 'a ' + time;
			else
				return time + ' a';
		} else
			return _prepareTimeFormat(HH < kk ? 'kk' : HH > -1 ? 'HH' : '', mv, sv);
		
	},
	
	getDateFormat: function () {
		return this._format.replace(/[ahKHksm]/g, '');
	},
	
	setOpen: function(open, _focus_) {
		if (this.isRealVisible()) {
			var pop;
			if (pop = this._pop)
				if (open) pop.open(!_focus_);
				else pop.close(!_focus_);
		}
	},
	isOpen: function () {
		return this._pop && this._pop.isOpen();
	},
	coerceFromString_: function (val, pattern) {
		var unf = Datebox._unformater;
		if (unf && jq.isFunction(unf)) {
			var cusv = unf(val);
			if (cusv) {
				this._shortcut = val;
				return cusv;
			}
		}
		if (val) {
			var format = this.getFormat(),
				d = new zk.fmt.Calendar().parseDate(val, pattern || format, !this._lenient, this._value, this._localizedSymbols);
			if (!d) return {error: zk.fmt.Text.format(msgzul.DATE_REQUIRED + (this.localizedFormat.replace(_quotePattern, '')))};
			
			if(!format.match(/[HkKh]/))
				d = new zk.fmt.Calendar().escapeDSTConflict(d);
			return d;
		}
		return null;
	},
	coerceToString_: function (val, pattern) {
		return val ? new zk.fmt.Calendar().formatDate(val, pattern || this.getFormat(), this._localizedSymbols) : '';
	},
	doFocus_: function (evt) {
		this.$supers('doFocus_', arguments);

		zul.inp.RoundUtl.doFocus_(this);
	},
	doBlur_: function (evt) {
		if (this._inplace && this._pop && this._pop.isOpen())
			return; 
		this.$supers('doBlur_', arguments);
		_blurInplace(this);
	},
	doClick_: function (evt) {
		if (this._disabled) return;
		if (this._readonly && this._buttonVisible && this._pop && !this._pop.isOpen())
			this._pop.open();
		this.$supers('doClick_', arguments);
	},
	doKeyDown_: function (evt) {
		this._doKeyDown(evt);
		if (!evt.stopped)
			this.$supers('doKeyDown_', arguments);
	},
	_doKeyDown: function (evt) {
		if (jq.nodeName(evt.domTarget, 'option', 'select'))
			return;
			
		var keyCode = evt.keyCode,
			bOpen = this._pop.isOpen();
		if (keyCode == 9 || (zk.webkit && keyCode == 0)) { 
			if (bOpen) this._pop.close();
			return;
		}

		if (evt.altKey && (keyCode == 38 || keyCode == 40)) {
			if (bOpen) this._pop.close();
			else this._pop.open();

			
			var opts = {propagation:true};
			if (zk.ie < 11) opts.dom = true;
			evt.stop(opts);
			return;
		}

		
		if (bOpen && (keyCode == 13 || keyCode == 27)) { 
			if (keyCode == 13) this.enterPressed_(evt);
			else this.escPressed_(evt);
			return;
		}

		if (keyCode == 18 || keyCode == 27 || keyCode == 13
		|| (keyCode >= 112 && keyCode <= 123)) 
			return; 
		
		
		
		if (this._pop.isOpen()) {
			this._pop.doKeyDown_(evt);
		}
	},
	
	enterPressed_: function (evt) {
		this._pop.close();
		this.updateChange_();
		evt.stop();
	},
	
	escPressed_: function (evt) {
		this._pop.close();
		evt.stop();
	},
	afterKeyDown_: function (evt, simulated) {
		if (!simulated && this._inplace)
			jq(this.$n()).toggleClass(this.getInplaceCSS(),  evt.keyCode == 13 ? null : false);

		return this.$supers('afterKeyDown_', arguments);
	},
	bind_: function (){
		this.$supers(Datebox, 'bind_', arguments);
		var btn, inp = this.getInputNode();

		if (btn = this.$n('btn')) {
			this.domListen_(btn, zk.android ? 'onTouchstart' : 'onClick', '_doBtnClick');
		}

		zWatch.listen({onSize: this, onScroll: this});
		this._pop.setFormat(this.getDateFormat());
	},
	unbind_: function () {
		var btn;
		if (btn = this._pop)
			btn.close(true);

		if (btn = this.$n('btn')) {
			this.domUnlisten_(btn, zk.android ? 'onTouchstart' : 'onClick', '_doBtnClick');
		}

		zWatch.unlisten({onSize: this, onScroll: this});
		this.$supers(Datebox, 'unbind_', arguments);
	},
	_doBtnClick: function (evt) {
		if (!this._buttonVisible) return;
		if (!this._disabled)
			this.setOpen(!jq(this.$n('pp')).zk.isVisible(), zul.db.DateboxCtrl.isPreservedFocus(this));
		evt.stop();
	},
	_doTimeZoneChange: function (evt) {
		var select = this.$n('dtzones'),
			timezone = select.value;
		this.updateChange_();
		this.fire('onTimeZoneChange', {timezone: timezone}, {toServer:true}, 150);
		if (this._pop) this._pop.close();
	},
	onChange: function (evt) {
		var data = evt.data,
			inpValue = this.getInputNode().value;
		if (this._pop)
			this._pop.setValue(data.value);
		
		
		if (!data.value && inpValue
				&& this.getFormat() && this._cst == '[c')
			data.value = inpValue;
	},
	onScroll: function (wgt) {
		if (this.isOpen()) {
			
			if (wgt && (pp = this._pop))
				
				if (this.getInputNode() && zul.inp.InputWidget._isInView(this))
					_reposition(this, true);
				else
					pp.close();
		}
	},
	
	getTimeZoneLabel: function () {
		return '';
	},

	redrawpp_: function (out) {
		out.push('<div id="', this.uuid, '-pp" class="', this.$s('popup'),
			'" style="display:none">');
		for (var w = this.firstChild; w; w = w.nextSibling)
			w.redraw(out);

		this._redrawTimezone(out);
		out.push('</div>');
	},
	_redrawTimezone: function (out) {
		var timezones = this._dtzones;
		if (timezones) {
			out.push('<div class="', this.$s('timezone'), '">',
					this.getTimeZoneLabel(),
					'<select id="', this.uuid, '-dtzones">');
			for (var i = 0, len = timezones.length; i < len; i++)
				out.push('<option value="', timezones[i], '">', timezones[i], '</option>');
			out.push('</select></div>');
			
		}
	}
});

var CalendarPop =
zul.db.CalendarPop = zk.$extends(zul.db.Calendar, {
	$init: function () {
		this.$supers('$init', arguments);
		this.listen({onChange: this}, -1000);
	},
	setFormat: function (fmt) {
		this._fmt = fmt;
	},
	setLocalizedSymbols: function (symbols) {
		this._localizedSymbols = symbols;
	},
	
	rerender: function () {
		this.$supers('rerender', arguments);
		if (this.desktop) this.syncShadow();
	},
	
	close: function (silent) {
		var db = this.parent,
			pp = db.$n('pp');

		if (!pp || !zk(pp).isVisible()) return;
		if (this._shadow) {
			
			this._shadow.destroy();
			this._shadow = null;
		}
		pp.style.display = 'none';
		pp.className = db.$s('popup');

		jq(pp).zk.undoVParent();
		db.setFloating_(false);

		if (silent)
			db.updateChange_();
		else if (zul.db.DateboxCtrl.isPreservedFocus(this))
			zk(db.getInputNode()).focus();
		
		jq(pp).removeClass(db.$s('open'));
	},
	isOpen: function () {
		return zk(this.parent.$n('pp')).isVisible();
	},
	open: function(silent) {
		var db = this.parent,
			dbn = db.$n(), pp = db.$n('pp');
		if (!dbn || !pp)
			return;

		db.setFloating_(true, {node:pp});
		zWatch.fire('onFloatUp', db); 
		var topZIndex = this.setTopmost();
		this._setView('day');
		var zcls = db.getZclass();

		pp.className = dbn.className + ' ' + pp.className;
		jq(pp).removeClass(zcls);

		pp.style.width = 'auto'; 
		pp.style.display = 'block';
		pp.style.zIndex = topZIndex > 0 ? topZIndex : 1;

		
		
		jq(pp).zk.makeVParent();

		if (pp.offsetWidth < dbn.offsetWidth) {
			pp.style.width = dbn.offsetWidth + 'px';
		} else {
			var wd = jq.innerWidth() - 20;
			if (wd < dbn.offsetWidth)
				wd = dbn.offsetWidth;
			if (pp.offsetWidth > wd)
				pp.style.width = wd;
		}
		var inp = db.getInputNode();
		zk(pp).position(inp, 'after_start');
		delete db._shortcut;
		
		var self = this;
		setTimeout(function() {
			_reposition(db, silent);
			zWatch.fireDown('onVParent', self.parent.$n('pp'), { shadow: self._shadow });
		}, 150);
		
		
		var fmt = db.getTimeFormat(),
			unf = Datebox._unformater,
			value = unf ? unf(inp.value) : null;
		
		if (!value)
			value = new zk.fmt.Calendar(zk.fmt.Date.parseDate(inp.value, db._format, false, db._value, this._localizedSymbols), this._localizedSymbols).toUTCDate()
				|| (inp.value ? db._value: zUtl.today(fmt));
		
		if (value)
			this.setValue(value);
		if (fmt) {
			var tm = db._tm;
			tm.setVisible(true);
			tm.setFormat(fmt);
			tm.setValue(value || new Date());
			tm.onSize();
		} else {
			db._tm.setVisible(false);
		}
		
		jq(pp).addClass(db.$s('open'));
	},
	syncShadow: function () {
		if (!this._shadow)
			this._shadow = new zk.eff.Shadow(this.parent.$n('pp'), {
				left: -4, right: 4, top: 2, bottom: 3});
		this._shadow.sync();
	},
	onChange: function (evt) {
		var date = this.getTime(),
			db = this.parent,
			fmt = db.getTimeFormat(),
			oldDate = db.getValue(),
			readonly = db.isReadonly(),
			cal = new zk.fmt.Calendar();
		
		if (fmt) {
			var tm = db._tm,
				time = tm.getValue();
			date.setHours(time.getHours(), time.getMinutes(), time.getSeconds(), time.getMilliseconds());
			
			
			if(!fmt.match(/[HkKh]/))
				date = cal.escapeDSTConflict(date);
		} else if (oldDate) {
			date = new Date(date.getFullYear(), date.getMonth(),
				date.getDate(), oldDate.getHours(),
				oldDate.getMinutes(), oldDate.getSeconds(), oldDate.getMilliseconds());
			
			
			
			
			if(!this.getFormat().match(/[HkKh]/))
				date = cal.escapeDSTConflict(date);
		}		
		
		
		if (!evt.data.shiftView)
			db.getInputNode().value = db.coerceToString_(date);
		
		if (this._view == 'day' && evt.data.shallClose !== false) {
			this.close();
			db._inplaceout = true;
			
			
			evt.data.value = date;
			if(!_equalDate(date, oldDate))
				db.updateChange_();
		}
		evt.stop();
	},
	onFloatUp: function (ctl) {
		if (this.isOpen()) {
			var db = this.parent;
			if (!zUtl.isAncestor(db, ctl.origin)) {
				this.close(true);
				db._inplaceout = true;
				_blurInplace(db);
			}
		}
	},
	bind_: function () {
		this.$supers(CalendarPop, 'bind_', arguments);
		this._bindTimezoneEvt();

		zWatch.listen({onFloatUp: this});
	},
	unbind_: function () {
		zWatch.unlisten({onFloatUp: this});
		this._unbindfTimezoneEvt();
		if (this._shadow) {
			this._shadow.destroy();
			this._shadow = null;
		}
		this.$supers(CalendarPop, 'unbind_', arguments);
	},
	_bindTimezoneEvt: function () {
		var db = this.parent;
		var select = db.$n('dtzones');
		if (select) {
			select.disabled = db.isTimeZonesReadonly() ? 'disable' : '';
			db.domListen_(select, 'onChange', '_doTimeZoneChange');
			db._setTimeZonesIndex();
		}
	},
	_unbindfTimezoneEvt: function () {
		var db = this.parent,
			select = db.$n('dtzones');
		if (select)
			db.domUnlisten_(select, 'onChange', '_doTimeZoneChange');
	},
	_setView: function (val, force) {
		if (this.parent.getTimeFormat())
			this.parent._tm.setVisible(val == 'day');
		this.$supers('_setView', arguments);

		
		if (zk.ie > 9) {
			this.syncShadow();
		}
		
		if (zk.ie9_ && force) {
			zk(this.parent.$n('pp')).redoCSS(500); 
		}
	},
	
	doKeyDown_: function (evt) {
		this.$supers('doKeyDown_', arguments);
		if (evt.keyCode == 27) {
			this.parent.escPressed_(evt);
		}
	}
});
zul.db.CalendarTime = zk.$extends(zul.db.Timebox, {
	$init: function () {
		this.$supers('$init', arguments);
		this.listen({onChanging: this}, -1000);
	},
	onChanging: function (evt) {
		var db = this.parent,
			oldDate = db.getValue() || db._pop.getValue(),
			cal = new zk.fmt.Calendar(),
			
			
			dateTime = db.coerceToString_(oldDate, _innerDateFormat) + evt.data.value, 
			pattern = _innerDateFormat + db.getTimeFormat();
		
		
		dateTime += pattern.indexOf('a') > -1 ? 
				dateTime.indexOf('AM') < 0 && dateTime.indexOf('PM') < 0 ? 'AM' : '' : '';
		var	date = db.coerceFromString_(dateTime, pattern);

		
		if(date instanceof Date) {
			db.getInputNode().value = evt.data.value
				= db.coerceToString_(date);	
			db.fire(evt.name, evt.data); 
		}
		
		if (this._view == 'day' && evt.data.shallClose !== false) {
			this.close();
			db._inplaceout = true;
		}
		evt.stop();
	}
});



zul.db.DateboxCtrl = {
	
	isPreservedFocus: function (wgt) {
		return true;
	}
};
})();
