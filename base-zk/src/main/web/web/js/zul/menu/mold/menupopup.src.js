
function (out) {
	var uuid = this.uuid,
		tags = zk.ie < 11 || zk.gecko ? 'a' : 'button';
	out.push('<div', this.domAttrs_(), '><', tags, ' id="', uuid,
			'-a" tabindex="-1" onclick="return false;" href="javascript:;"',
			' class="z-focus-a"></',
			tags, '><div class="', this.$s('separator') ,'"></div><ul class="', this.$s('content'), '" id="', uuid, '-cave">');

	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);

	out.push('</ul></div>');
}
