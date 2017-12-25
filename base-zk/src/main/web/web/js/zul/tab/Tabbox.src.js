



zul.tab.Tabbox = zk.$extends(zul.Widget, {
	_orient: 'top',
	_tabscroll: true,
	_maximalHeight: false,
	
	_animating: false,

	$define: {
    	
    	
		tabscroll: _zkf = function () {
			this.rerender();
		},
		
		
		orient: function (orient) {
			if (orient == 'horizontal')
				this._orient = 'top';
			else if (orient == 'vertical')
				this._orient = 'left';
			this.rerender();
		},
		
		
		
		maximalHeight: _zkf,
		
		
		panelSpacing: _zkf
	},
	
	getTabs: function () {
		return this.tabs;
	},
	
	getTabpanels: function () {
		return this.tabpanels;
	},
	
	getToolbar: function () {
		return this.toolbar;
	},
	domClass_: function (no) {
		var sc = this.$supers('domClass_', arguments);
		if (!no || !no.zclass) {
			var cls = this.inAccordionMold() ?
					this.$s('accordion') : this.$s(this.getOrient());
			sc += ' ' + cls;
		}
		return sc;
	},
	
	isHorizontal: function() {
		var orient = this.getOrient();
		return 'horizontal' == orient || 'top' == orient || 'bottom' == orient;
	},
	
	isTop: function() {
		var orient = this.getOrient();
		return 'horizontal' == orient || 'top' == orient;
	},
	
	isBottom: function() {
		return 'bottom' == this.getOrient();
	},
	
	isVertical: function() {
		var orient = this.getOrient();
		return 'vertical' == orient || 'left' == orient || 'right' == orient;
	},
	
	isRight: function() {
		var orient = this.getOrient();
		return 'vertical' == orient || 'left' == orient;
	},
	
	isLeft: function() {
		return 'left' == this.getOrient();
	},
	
	inAccordionMold: function () {
		return this.getMold().indexOf('accordion') != -1;
	},
	
	getSelectedIndex: function() {
		return this._selTab ? this._selTab.getIndex() : -1 ;
	},
	
	setSelectedIndex: function(index) {
		if (this.tabs)
			this.setSelectedTab(this.tabs.getChildAt(index));
	},
	
	getSelectedPanel: function() {
		return this._selTab ? this._selTab.getLinkedPanel() : null;
	},
	
	setSelectedPanel: function(panel) {
		if (panel && panel.getTabbox() != this)
			return
		var tab = panel.getLinkedTab();
		if (tab)
			this.setSelectedTab(tab);
	},
	
	getSelectedTab: function() {
		return this._selTab;
	},
	
	setSelectedTab: function(tab) {
		if (this._selTab != tab) {
			if (tab)
				tab.setSelected(true);
				
			this._selTab = tab;
		}
	},
	bind_: function (desktop, skipper, after) {
		this.$supers(zul.tab.Tabbox, 'bind_', arguments);
		
		
		this._scrolling = false;
		var toolbar = this.getToolbar();
		
		if (this.inAccordionMold())
			zWatch.listen({onResponse: this});
		else if (toolbar && this.getTabs()) {
			zWatch.listen({onResponse: this});
			this._toolbarWidth = jq(toolbar.$n()).width();
		}
		
		for (var btn, key = ['right', 'left', 'down', 'up'], le = key.length; le--;) 
			if (btn = this.$n(key[le])) 				
				this.domListen_(btn, 'onClick', '_doClick', key[le]);
		this._fixMaxHeight();
	},
	unbind_: function () {
		zWatch.unlisten({onResponse: this});
		for (var btn, key = ['right', 'left', 'down', 'up'], le = key.length; le--;)
			if (btn = this.$n(key[le]))
				this.domUnlisten_(btn, 'onClick', '_doClick', key[le]);
		this._toolbarWidth = null;
		this.$supers(zul.tab.Tabbox, 'unbind_', arguments);
	},
	_doClick: function(evt, direction) {
		if (!this.tabs || !this.tabs.nChildren) return; 

		var cave = this.tabs.$n('cave'),
			allTab =  jq(cave).children(),
			move = 0,
			tabbox = this,
			head = this.tabs.$n(),
			isVert = tabbox.isVertical(),
			scrollLength = isVert ? head.scrollTop : head.scrollLeft,
			offsetLength = isVert ? head.offsetHeight : head.offsetWidth,
			plus = scrollLength + offsetLength;
		
		switch (direction) {
		case 'right':
			for (var i = 0, count = allTab.length; i < count; i++) {
				if (allTab[i].offsetLeft + allTab[i].offsetWidth > plus) {
					move = allTab[i].offsetLeft + allTab[i].offsetWidth - scrollLength - offsetLength;
					if (!move || isNaN(move))
						return;
					this.tabs._doScroll('right', move);
					return;
				}
			}
			break;
		case 'left':
			for (var i = 0, count = allTab.length; i < count; i++) {
				if (allTab[i].offsetLeft >= scrollLength) {
					
					var tabli = jq(allTab[i]).prev('li')[0];
					if (!tabli)  return;
					move = scrollLength - tabli.offsetLeft;
					if (isNaN(move)) return;
					this.tabs._doScroll('left', move);
					return;
				};
			};
			move = scrollLength - allTab[allTab.length-1].offsetLeft;
			if (isNaN(move)) return;
			this.tabs._doScroll('left', move);
			break;
		case 'up':
			for (var i = 0, count = allTab.length; i < count; i++) {
				if (allTab[i].offsetTop >= scrollLength) {
					var preli = jq(allTab[i]).prev('li')[0];
					if (!preli) return;
					move = scrollLength - preli.offsetTop ;
					this.tabs._doScroll('up', move);
					return;
				};
			};
			var preli = allTab[allTab.length-1];
			if (!preli) return;
			move = scrollLength - preli.offsetTop ;
			this.tabs._doScroll('up', move);
			break;
		case 'down':
			for (var i = 0, count = allTab.length; i < count; i++) {
				if (allTab[i].offsetTop + allTab[i].offsetHeight > plus) {
					move = allTab[i].offsetTop + allTab[i].offsetHeight - scrollLength - offsetLength;
					if (!move || isNaN(move)) return ;
					this.tabs._doScroll('down', move);
					return;
				};
			}
			break;
		}
	},
	
	syncSize: function () {
		this._shallSize = false;
		if (this.desktop)
			zUtl.fireSized(this, -1); 
	},
	onResponse: function () {
		if (this.inAccordionMold()) {
			if (this._shallSize)
				this.syncSize();
		} else if (this._toolbarWidth) { 
			var toolbarWidth = jq(this.getToolbar().$n()).width();
			if (toolbarWidth != this._toolbarWidth) { 
				this._toolbarWidth = toolbarWidth;
				this.getTabs().onSize();
			}
		}
	},
	_syncSize: function () {
		if (this.desktop)
			this._shallSize = true;
	},
	
	onChildAdded_: function (child) {
		this.$supers('onChildAdded_', arguments);
		if (child.$instanceof(zul.wgt.Toolbar))
			this.toolbar = child;
		else if (child.$instanceof(zul.tab.Tabs))
			this.tabs = child;
		else if (child.$instanceof(zul.tab.Tabpanels)) {
			this.tabpanels = child;
		}
		this.rerender();
	},
	onChildRemoved_: function (child) {
		this.$supers('onChildRemoved_', arguments);
		if (child == this.toolbar)
			this.toolbar = null;
		else if (child == this.tabs)
			this.tabs = null;
		else if (child == this.tabpanels)
			this.tabpanels = null;
		if (!this.childReplacing_)
			this.rerender();
	},
	setWidth: function (width) {
		this.$supers('setWidth', arguments);
		if (this.desktop)
			zUtl.fireSized(this, -1); 
	},
	setHeight: function (height) {
		this.$supers('setHeight', arguments);
		if (this.desktop)
			zUtl.fireSized(this, -1); 
	},
	
	_fixMaxHeight: function() {
		var tabbox = this;
		if (tabbox._maximalHeight) {
			var max = 0,
				pnls = tabbox.getTabpanels(),
				fc = pnls.firstChild;
			
			for(var c = fc; c; c = c.nextSibling) {
				var panel = c ? c.getCaveNode() : null;
				if (!panel) 
					return;
				else {
					var hgh = jq(panel).outerHeight();
					if (hgh > max)
						max = hgh;
				}
			}
			
			for(var c = fc; c; c = c.nextSibling) {
				var panel = c.getCaveNode();
				if (panel)
					panel.style.height = jq.px0(max);
			}
		}
	}
});
