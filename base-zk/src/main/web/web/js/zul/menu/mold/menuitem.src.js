
function (out) {
	var uuid = this.uuid,
	target = this.getTarget();
	
	out.push('<li', this.domAttrs_(), '>');
	
	out.push('<a href="', this.getHref() ? this.getHref() : 'javascript:;', '"');
	if (target)
		out.push(' target="', target, '"');
	out.push(' id="', uuid, '-a" class="', this.$s('content'), '"',
			this._disabled ? ' disabled="disabled"' : '',
			'>', this.domContent_(), '</a></li>'); 
}
