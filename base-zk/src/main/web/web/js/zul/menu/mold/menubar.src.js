
function (out) {
	var uuid = this.uuid;
	if ('vertical' == this.getOrient()) {
		out.push('<div', this.domAttrs_(), '><ul id="', uuid, '-cave">');
		for (var w = this.firstChild; w; w = w.nextSibling)
			this.encloseChildHTML_({out: out, child: w, vertical: true});
		out.push('</ul></div>');
	} else {
		var scrollable;
		out.push('<div', this.domAttrs_(), '>')
		if (scrollable = this.checkScrollable()) {
			var scrollableCls = this.$s('scrollable'),
				scrollIcon = this.$s('icon');
				
			out.push('<div id="', uuid, '-left" class="', this.$s('left'), ' ',
						scrollableCls, '"><i class="', scrollIcon,
						' z-icon-chevron-left"></i></div>',
					'<div id="', uuid, '-right" class="', this.$s('right'), ' ',
						scrollableCls, '"><i class="', scrollIcon,
						' z-icon-chevron-right"></i></div>',
					'<div id="', uuid, '-body" class="', this.$s('body'), '">',
					'<div id="', uuid, '-cnt" class="', this.$s('content'), '">');
		}
		out.push('<ul id="', uuid, '-cave">');
		for (var w = this.firstChild; w; w = w.nextSibling)
			w.redraw(out);
		out.push('</ul>');
		if (scrollable)
			out.push('</div></div>');
		out.push('</div>');
	}
}