
function (out) {
	var icon = this.$s('icon'),
		zicon = ' z-icon-ellipsis-' + ('vertical' == this.getOrient() ? 'horizontal' : 'vertical');
	out.push('<div', this.domAttrs_(), '><span id="',
			this.uuid, '-btn" class="', this.$s('button'),'">',
			'<i class="', icon, zicon, '"></i>',
			'<i id="', this.uuid ,'-icon" class="', icon, '"></i>',
			'<i class="', icon, zicon, '"></i>',
			'</span></div>');
}