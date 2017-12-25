

zul.LabelImageWidget = zk.$extends(zul.Widget, {
	_label: '',

	$define: {
		
		
		label: _zkf = function () {
			if (this.desktop)
				this.updateDomContent_();
		},
		
		
		iconSclass: _zkf,
		
		
		image: function (v) {
			if (v && this._preloadImage) zUtl.loadImage(v);
			var n = this.getImageNode();
			if (n) {
				var img = v || '';
				if (jq.nodeName(n, 'img')) 
					n.src = img;
				else
					jq(n).css('background-image', 'url('+img+')');
			} else if (this.desktop) 
				this.updateDomContent_();
		},
		
		
		hoverImage: null
	},
	
	updateDomContent_: function () {
		this.rerender();
	},
	
	domImage_: function () {
		var img = this._image;
		return img ? '<img src="' + img + '" align="absmiddle" />': '';
	},
	
	domIcon_: function () {
		var icon = this.getIconSclass(); 
		return icon ? '<i class="' + icon + '"></i>': '';
	},
	
	domLabel_: function () {
		return zUtl.encodeXML(this.getLabel());
	},
	
	domContent_: function () {
		var label = this.domLabel_(),
			icon = this.domIcon_(),
			img = this.domImage_();
		
		if (img) {
			if (icon) {
				return img + ' ' + icon + (label ? ' ' + label : ''); 
			} else {
				return label ? img + ' ' + label: img;
			}
		} else {
			return icon ? label ? icon + ' ' + label : icon : label;
		}
	},
	doMouseOver_: function () {
		this._updateHoverImage(true);
		this.$supers('doMouseOver_', arguments);
	},
	doMouseOut_: function () {
		this._updateHoverImage();
		this.$supers('doMouseOut_', arguments);
	},
	
	getImageNode: function () {
		if (!this._eimg && this._image) {
			var n = this.$n();
			if (n) this._eimg = jq(n).find('img:first')[0];
		}
		return this._eimg;
	},
	_updateHoverImage: function (inHover) {
		var n = this.getImageNode(),
			img = inHover ? this._hoverImage : this._image;
		if (n && this._hoverImage) {
			if (jq.nodeName(n, 'img'))
				n.src = img;
			else
				jq(n).css('background-image', 'url('+img+')');
		}
	},
	
	clearCache: function () {
		this._eimg = null;
		this.$supers('clearCache', arguments);
	}
});
