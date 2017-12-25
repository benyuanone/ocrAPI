
function (out) {
	out.push('<tr', this.domAttrs_(), '>');
	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);
	
	var tree = this.getTree();
	if (tree._nativebar && !tree.frozen)
		out.push('<td class="', this.$s('bar'), '" />');
	
	out.push('</tr>');
}
