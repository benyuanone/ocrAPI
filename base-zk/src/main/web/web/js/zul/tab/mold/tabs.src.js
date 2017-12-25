
function (out) {
	var tbx = this.getTabbox(),
		uuid = this.uuid;
	out.push('<div ', this.domAttrs_(), '>', 	
			   '<ul id="', uuid, '-cave" class="', this.$s('content'), '">');
	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);
	out.push(  '</ul>',
			 '</div>');
	
}