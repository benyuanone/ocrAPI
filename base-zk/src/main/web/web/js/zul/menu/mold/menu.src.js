
function (out) {
	var uuid = this.uuid,
		zcls = this.getZclass(),
		contentHandler = this._contentHandler;
	
	out.push('<li', this.domAttrs_(), '><a href="javascript:;" id="', uuid,
			'-a" class="', this.$s('content'), '">', this.domContent_(), '</a>');
	if (this.menupopup)
		this.menupopup.redraw(out);
	else if (contentHandler)
		contentHandler.redraw(out);
	
	out.push('</li>');
}