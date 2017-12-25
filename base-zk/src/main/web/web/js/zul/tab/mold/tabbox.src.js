
function (out) {
	var icon = this.$s('icon'),
		uuid = this.uuid,
		tabscroll = this.isTabscroll(),
		tabs = this.tabs,
		tabpanels = this.tabpanels,
		toolbar = this.toolbar,
		getIcon = function(fontIconCls) {
			return '<i class="z-icon-chevron-' + fontIconCls + '"></i>';
		};
	out.push('<div ', this.domAttrs_(), '>');
	if (this.isHorizontal()) { 
		if (this.isBottom()) {
			if (tabpanels)
				tabpanels.redraw(out);
			if (tabs)
				tabs.redraw(out);
		} else {
			if (tabs)
				tabs.redraw(out);
			if (tabpanels)
				tabpanels.redraw(out);
		}
		if (tabscroll) {
			out.push(
				'<div id="', uuid , '-left" class="', icon, ' ', this.$s('left-scroll'), '">', getIcon('left'), '</div>',
				'<div id="', uuid , '-right" class="', icon, ' ', this.$s('right-scroll'), '">' , getIcon('right'),  '</div>');
		}
		if (tabscroll && toolbar)
			toolbar.redraw(out);
	} else { 
		if (tabs)
			tabs.redraw(out);
		if (tabpanels)
			tabpanels.redraw(out);
		if (this.isVertical()) { 
			if (tabscroll) {
				out.push(
					'<div id="', uuid , '-up" class="', icon, ' ', this.$s('up-scroll'), '">', getIcon('up'), '</div>',
					'<div id="', uuid , '-down" class="', icon, ' ', this.$s('down-scroll'), '">' , getIcon('down'),  '</div>');
			}
			out.push('<div class="z-clear"></div>');
		}
	}
	out.push("</div>");
}