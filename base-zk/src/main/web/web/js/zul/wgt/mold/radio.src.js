
function (out) {
	var uuid = this.uuid,
		rg = this.getRadiogroup();
	out.push('<span', this.domAttrs_(), '><input type="radio" id="', uuid,
			'-real"', this.contentAttrs_(), '/><label for="', uuid, '-real"', 
			' id="', uuid, '-cnt"', this.domTextStyleAttr_(),
			' class="', this.$s('content') ,'">', this.domContent_(),'</label>',
			(rg && rg._orient == 'vertical' ? '<br/>' :''), '</span>');
}