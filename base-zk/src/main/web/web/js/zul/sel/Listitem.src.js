
(function () {

	function _isPE() {
		return zk.isLoaded('zkex.sel');
	}
	
	function updateImg(drag) {
		var dragImg = drag._dragImg;
		if (dragImg) {
			
			var allow = jq(drag.node).hasClass('z-drop-allow');
			for (var len = 0; len < dragImg.length; len ++) {
				if (allow)
					jq(dragImg[len]).removeClass('z-icon-times').addClass('z-icon-check');
				else
					jq(dragImg[len]).removeClass('z-icon-check').addClass('z-icon-times');
			}
		}
	}

zul.sel.Listitem = zk.$extends(zul.sel.ItemWidget, {
	
	getListbox: function () {
		return this.parent;
	},
	
	getListgroup: function () {
		
		if (_isPE() && this.parent && this.parent.hasGroup())
			for (var w = this; w; w = w.previousSibling)
				if (w.$instanceof(zkex.sel.Listgroup))
					return w;
				
		return null;
	},
	
	setLabel: function (val) {
		this._autoFirstCell().setLabel(val);
	},
	
	getDragMessage_: function () {
		var p = this.parent,
			p_sel = p._selItems,
			length = p_sel.length,
			inst = zul.sel.Listitem,
			msg,
			cnt = 2;
		
		
		
		
		if (!this.isSelected() || !length || (length == 1 && p_sel[0] == this))
			return this.getLabel();
		for (var w = p.firstChild; w; w = w.nextSibling)
			if (w.$instanceof(inst) && w.isSelected()) {
				var label = w.getLabel();
				if (label.length > 9)
					label = label.substring(0, 9) + "...";
				if (!msg)
					msg = label;
				else
					msg += '</div><div class="z-drop-content"><span id="zk_ddghost-img'
						+ (cnt++) + '" class="z-drop-icon"></span>&nbsp;'
						+ label;
			}
		return msg;
	},
	
	getDragOptions_: function (map) {
		var old = map.change;
		map.change =  function (drag, pt, evt) {
			old(drag, pt, evt);
			
			updateImg(drag);
		};
		return this.$supers('getDragOptions_', arguments);
	},
	
	
	cloneDrag_: function (drag, ofs) {
		
		var msg = this.getDragMessage_();
		var dgelm = zk.DnD.ghost(drag, ofs, msg);

		drag._orgcursor = document.body.style.cursor;
		document.body.style.cursor = 'pointer';
		jq(this.getDragNode()).addClass('z-dragged'); 
		
		drag._dragImg = jq('span[id^="zk_ddghost-img"]');
		return dgelm;
	},
	
	setImage: function (val) {
		this._autoFirstCell().setImage(val);
	},
	_autoFirstCell: function () {
		if (!this.firstChild)
			this.appendChild(new zul.sel.Listcell());
		return this.firstChild;
	},
	
	domStyle_: function (no) {
		if (_isPE() && (this.$instanceof(zkex.sel.Listgroup) || this.$instanceof(zkex.sel.Listgroupfoot))
				|| (no && no.visible))
			return this.$supers('domStyle_', arguments);
			
		var style = this.$supers('domStyle_', arguments),
			group = this.getListgroup();
		return group && !group.isOpen() ? style + 'display:none;' : style;
	},
	domClass_: function () {
		var cls = this.$supers('domClass_', arguments),
			list = this.getListbox();
		if (list && jq(this.$n()).hasClass(list = list.getOddRowSclass()))
			return cls + ' ' + list; 
		return cls;
	},
	replaceWidget: function (newwgt) {
		this._syncListitems(newwgt);
		this.$supers('replaceWidget', arguments);
	},
	scrollIntoView: function () {
		var bar = this.getListbox()._scrollbar;
		if (bar) {
			bar.syncSize();
			bar.scrollToElement(this.$n());
		} else {
			this.$supers('scrollIntoView', arguments);
		}
	},
	_updHeaderCM: function (bRemove) {
		
		var box, lh;
		if (!this.isSelected() && (box = this.getListbox()) 
			&& box._headercm && box._multiple && 
				(lh = box.listhead) && (lh = lh.firstChild))
			lh._checked = false;
		this.$supers('_updHeaderCM', arguments);
	},
	_syncListitems: function (newwgt) {
		var box;
		if (box = this.getListbox()) {
			if (box.firstItem.uuid == newwgt.uuid)
				box.firstItem = newwgt;
			if (box.lastItem.uuid == newwgt.uuid)
				box.lastItem = newwgt;

			var items = box._selItems, b1, b2;
			if (b1 = this.isSelected())
				items.$remove(this);
			if (b2 = newwgt.isSelected())
				items.push(newwgt);
			if (b1 != b2)
				box._updHeaderCM();
		}
	}
});
})();