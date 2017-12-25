
function (out) {
	out.push('<tr', this.domAttrs_(), '>');
	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);
	
	var grid = this.getGrid();
	if (grid._nativebar && !grid.frozen)
		out.push('<td class="', this.$s('bar'), '" />');
	
	out.push('</tr>');
}
