
function (out) {
	out.push('<li', this.domAttrs_({text:true}), '><span class="',
		this.$s('image'), '">', this.domImage_(), '</span><span class="',
		this.$s('text'), '">', this.domLabel_());

	var v;
	if (v = this._description)
		out.push('<br/><span class="', this.$s('inner'), '">',
			zUtl.encodeXML(v), '</span>');
	if (v = this._content)
		out.push('<span class="', this.$s('content'), '">', v, '</span>');

	out.push('</span></li>');
}
