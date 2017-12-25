
function (out) {
	out.push('<tr', this.domAttrs_(), ' style="text-align:left;">');
	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);
	var mesh = this.getMeshWidget();
	if (mesh && mesh._nativebar && !mesh.frozen)
		out.push('<th class="', this.$s('bar'), '" />');
	out.push('</tr>');
}
