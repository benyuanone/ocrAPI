
function (out) {
	var uuid = this.uuid;
	
	out.push('<div', this.domAttrs_(), '><div id="', uuid, '-cave" class="',
			this.$s('body'), '">');
	for (var j = 0, w = this.firstChild; w; w = w.nextSibling, j++)
		w.redraw(out);
	out.push('</div><div id="', uuid, '-scrollX" class="', this.$s('inner'), 
			'"><div></div></div><div class="z-clear"></div></div>');
}
