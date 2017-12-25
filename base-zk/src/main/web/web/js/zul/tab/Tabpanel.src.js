

zul.tab.Tabpanel = zk.$extends(zul.ContainerWidget, {
	
	getTabbox: function() {
		return this.parent ? this.parent.parent : null;
	},
	isVisible: function() {
		return this.$supers('isVisible', arguments) && this.isSelected();
	},
	setVisible: function() {
		this.$supers('setVisible', arguments);
		if (this.desktop && !this.isSelected()) 
			this.$n().style.display = 'none';
	},
	domClass_: function() {
		var cls = this.$supers('domClass_', arguments),
			tabbox = this.getTabbox();
		if (tabbox.inAccordionMold())
			cls += ' ' + this.$s('content');
		return cls;
	},
	
	getLinkedTab: function() {
		var tabbox =  this.getTabbox();
		if (!tabbox) return null;

		var tabs = tabbox.getTabs();
		return tabs ? tabs.getChildAt(this.getIndex()) : null;
	},
	
	getIndex:function() {
		return this.getChildIndex();
	},
	
	isSelected: function() {
		var tab = this.getLinkedTab();
		return tab && tab.isSelected();
	},
	
	_changeSel: function (oldPanel) {
		if (oldPanel) {
			var cave = this.$n('cave');
			if (cave && !cave.style.height && (oldPanel = oldPanel.$n('cave')))
				cave.style.height = oldPanel.style.height;
		}
	},
	_sel: function (toSel, animation) { 
		var tabbox = this.getTabbox();
		if(!tabbox) return; 
		var accd = tabbox.inAccordionMold();

		if (accd && animation) {
			var zkp = zk(this.$n('cave'));
			if (toSel) {
				
				tabbox._animating = true;
				zkp.slideDown(
					this,
					{'afterAnima': function(){delete tabbox._animating;}}
				);
			} else {
				zkp.slideUp(this);
			}
		} else {
			var $pl = jq(accd ? this.$n('cave') : this.$n()),
				vis = $pl.zk.isVisible();
			if (toSel) {
				if (!vis) {
					$pl.show();
					
					if (zk.ie8 || zk.webkit)
						$pl.scrollTop(this._lastScrollTop);
					zUtl.fireShown(this);
				}
			} else if (vis) {
				zWatch.fireDown('onHide', this);
				
				if (zk.ie8 || zk.webkit)
					this._lastScrollTop = $pl.scrollTop();
				$pl.hide();
			}
		}
	},
	getPanelContentHeight_: function () {
		var node = this.$n(),
			tabpanelsNode = this.parent && this.parent.$n(),
			panelContentHeight = tabpanelsNode &&
				(tabpanelsNode.scrollHeight + zk(tabpanelsNode).padBorderHeight());

		return Math.max(node && node.offsetHeight,panelContentHeight) ; 
	},
	_fixPanelHgh: function() {
		var tabbox = this.getTabbox(),
			tbx = tabbox.$n(),
			hgh = tbx.style.height;
		
		if (hgh && hgh != 'auto') {
			if (!tabbox.inAccordionMold()) {
				var n = this.$n(),
					isHor = tabbox.isHorizontal();

				hgh = isHor ? zk(tabbox).contentHeight() - zk(tabbox.tabs).offsetHeight() 
						    : zk(tabbox).contentHeight() - zk(n.parentNode).padBorderHeight();
					
				n.style.height = jq.px0(hgh);
			} else {
				var n = this.$n(),
					hgh = tbx.offsetHeight,
					zkp = zk(n.parentNode);
				hgh = hgh - zkp.padBorderHeight();
				for (var e = n.parentNode.firstChild; e; e = e.nextSibling)
					if (e != n)
						hgh -= e.offsetHeight;
				hgh -= n.firstChild.offsetHeight;
				var cave = this.$n('cave'),
					s = cave.style;
				s.height = jq.px0(hgh);
			}
		}
	},
	onSize: function() {
		var tabbox = this.getTabbox();
		if (tabbox.inAccordionMold() && !zk(this.$n('cave')).isVisible())
			return;
		this._fixPanelHgh();		
	},

	
	setVflex: function (v) { 
		if (v != 'min') v = false;
		this.$supers('setVflex', arguments);
	},
	
	setHflex: function (v) { 
		if (v != 'min') v = false;
		this.$supers('setHflex', arguments);
	},
	bind_: function(desktop) {
		this.$supers(zul.tab.Tabpanel, 'bind_', arguments);
		zWatch.listen({onSize: this});
		
		var tab;
		if (this.getTabbox().inAccordionMold()
				&& (tab=this.getLinkedTab())) {
			
			if (!tab.$n())
				tab.unbind().bind(desktop);
			else if (!jq.isAncestor(this.$n(), tab.$n())) {
				
				
				
				var cave = this.$n('cave');
				if (cave) cave.style.display = 'none';
			}
		}
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this});
		this._lastScrollTop = null;
		this.$supers(zul.tab.Tabpanel, 'unbind_', arguments);
	}
});