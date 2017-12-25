
function (out) {
	var uuid = this.uuid,
		tabbox = this.getTabbox();
	if (tabbox.inAccordionMold()) {
		var tab = this.getLinkedTab();
		
		out.push('<div class="', this.getZclass() , '" id="', uuid, '">');
		
		if (tab && !tab.$n())
			tab.redraw(out);
		out.push('<div id="', uuid, '-cave"', this.domAttrs_({id:1, zclass:1}), '>');
		for (var w = this.firstChild; w; w = w.nextSibling)
			w.redraw(out);
		out.push('</div></div>');

	} else {
		out.push('<div ', this.domAttrs_(), '>');
		for (var w = this.firstChild; w; w = w.nextSibling)
			w.redraw(out);
		out.push('</div>');
	}
}