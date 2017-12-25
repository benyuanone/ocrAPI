
function (out) {
	out.push('<th', this.domAttrs_(), '><div id="', this.uuid, '-cave" class="',
			this.$s('content'), '"', this.domTextStyleAttr_(),
			'><div class="', this.$s('sorticon'), '"><i id="', this.uuid, '-sort-icon"></i></div>', this.domContent_());
	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);
	out.push('</div></th>');	
}
