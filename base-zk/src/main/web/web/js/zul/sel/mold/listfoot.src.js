
function (out) {
	out.push('<tr', this.domAttrs_(), '>');
	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);
	
	var listbox = this.getListbox();
	if (listbox._nativebar && !listbox.frozen)
		out.push('<td class="', this.$s('bar'), '" />');
	
	out.push('</tr>');
}
