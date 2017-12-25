
function (out) {
	var tabi = this._tabindex,
		uuid = this.uuid;
	
	out.push('<span ', this.domAttrs_());
	
	if (this._disabled)
		out.push(' disabled="disabled"');
	
	out.push(' ><span id="', uuid, '-real" class="', this.$s('content') ,'"');
	
	if (tabi)
		out.push(' tabindex="', tabi, '"');
	
	out.push('>', this.domContent_(), 
			 '<span id="', uuid, '-btn" class="', this.$s('button'), '">', 
				'<i id="', uuid, '-icon" class="', this.$s('icon'), ' z-icon-caret-down"></i>', 
			 '</span></span>');
	
	if (this.firstChild)
		this.firstChild.redraw(out);
	
	out.push('</span>');
}