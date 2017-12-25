
function (out) {
	var uuid = this.uuid, content = this.domContent_();
	out.push('<span', this.domAttrs_(), '><input type="checkbox" id="', uuid,
			'-real"', this.contentAttrs_(), '/><label for="', uuid,
			'-real" id="', uuid, '-cnt"', this.domTextStyleAttr_(), 
			' class="', this.$s('content') ,'">', this.domContent_(),
			'</label></span>');
}