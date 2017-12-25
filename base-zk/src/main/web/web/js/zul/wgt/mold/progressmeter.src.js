
function (out) {
	out.push('<div', this.domAttrs_(), '><span id="',
			this.uuid,'-img"', 'class="', this.$s('image'),'"></span></div>');
}