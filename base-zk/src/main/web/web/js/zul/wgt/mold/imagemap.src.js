
function (out) {
	var uuid = this.uuid, mapid = uuid + '-map';
	out.push('<span', this.domAttrs_({content:1}), '><img id="',
		uuid, '-real"', this.contentAttrs_(),
		'/><map name="', mapid, '" id="', mapid, '">');

	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);

	out.push('</map></span>');
}
