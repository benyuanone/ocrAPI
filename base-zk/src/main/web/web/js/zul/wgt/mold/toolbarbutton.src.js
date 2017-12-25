
function (out) {
	out.push('<a', this.domAttrs_(), '><span id="', this.uuid, '-cnt"',
			this.domTextStyleAttr_(), 'class="', this.$s('content'), '">',
			this.domContent_(), '</span></a>');
}
