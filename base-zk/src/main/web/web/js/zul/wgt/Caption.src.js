

zul.wgt.Caption = zk.$extends(zul.LabelImageWidget, {
	
	domDependent_: true, 
	rerender: function () {
		var p = this.parent;
		if (p)
			p.clearCache(); 
		this.$supers('rerender', arguments);
	},
	domContent_: function () {
		var label = this.getLabel(),
			img = this.getImage(),
			title = this.parent ? this.parent._title: '',
			iconSclass = this.domIcon_();
		if (title) label = label ? title + ' - ' + label: title;
		label = zUtl.encodeXML(label);
		if (!img && !iconSclass) return label;

		if (!img) img = iconSclass;
		else img = '<img id="' + this.uuid + '-img" src="' + img + '" class="' + this.$s('image') + '" />' + (iconSclass ? ' ' + iconSclass : '');
		return label ? img + ' ' + label: img;
	},
	updateDomContent_: function () {
		var cnt = this.domContent_(),
			dn = this.$n('cave'),
			size = this.nChildren,
			
			total = jq(dn).contents().filter(function () {
				return !(this.nodeType == 3 && !this.nodeValue.trim().length);
			}).length,
			index = 0;
		
		 
		if (dn) {
			
			jq(dn).contents().filter(function(){
			    return (size + index++) < total;
			}).remove();
			this.clearCache(); 
			jq(dn).prepend(cnt ? cnt : '&nbsp;');
		} 
	},
	domClass_: function (no) {
		var sc = this.$supers('domClass_', arguments),
			parent = this.parent;
			
		if (!parent.$instanceof(zul.wgt.Groupbox))
			return sc;
			
		return sc + (parent._closable ? '': ' ' + this.$s('readonly'));
	},
	doClick_: function () {
		if (this.parent.$instanceof(zul.wgt.Groupbox))
			this.parent.setOpen(!this.parent.isOpen());
		this.$supers('doClick_', arguments);
	},
	
	_getBlank: function () {
		return '&nbsp;';
	},
	
	_isCollapsibleVisible: function () {
		var parent = this.parent;
		return parent.isCollapsible && parent.isCollapsible();
	},
	
	_isCloseVisible: function () {
		var parent = this.parent;
		return parent.isClosable && parent.isClosable()
			&& !parent.$instanceof(zul.wgt.Groupbox);
	},
	
	_isMinimizeVisible: function () {
		var parent = this.parent;
		return parent.isMinimizable && parent.isMinimizable();
	},
	
	_isMaximizeVisible: function () {
		var parent = this.parent;
		return parent.isMaximizable && parent.isMaximizable();
	},
	beforeMinFlex_: function (o) { 
		if (o == 'w')
			this.$n().width = '';
	},
	
	setFlexSizeW_: function(n, zkn, width, isFlexMin) {
		if (isFlexMin) {
			if (this._isCloseVisible()) {
				var close = this.parent.$n('close');
				width += close.offsetWidth + zk(close).marginWidth();
			}
			if (this._isMaximizeVisible()) {
				var max = this.parent.$n('max');
				width += max.offsetWidth + zk(max).marginWidth();
			}
			if (this._isMinimizeVisible()) {
				var min = this.parent.$n('min');
				width += min.offsetWidth + zk(min).marginWidth();
			}
			if (this._isCollapsibleVisible()) {
				var exp = this.parent.$n('exp');
				width += exp.offsetWidth + zk(exp).marginWidth();		
			}
		}
		this.$supers('setFlexSizeW_', arguments);
	},
	
	
	getImageNode: function () {
		if (!this._eimg && this._image) {
			var n = this.$n('img');
			if (n) this._eimg = n;
		}
		return this._eimg;
	}
});