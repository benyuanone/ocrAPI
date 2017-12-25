
function (out) {
	var tbx = this.getTabbox(),
		uuid = this.uuid,
		icon = this.$s('icon'),
		removeIcon = '<i id="' + uuid + '-cls" class="z-icon-times ' + icon + '"></i>',
		isAccordion = tbx.inAccordionMold(),
		tag = isAccordion ? 'div' : 'li', 
		panel = isAccordion ? this.getLinkedPanel() : null,
		n = panel? panel.$n() : null;
	 
	if (isAccordion) {
		var c = n? n.firstChild : null;
		
		
		
		
		
		
		
		if (!panel || (c && c != panel.$n('cave')
			&& (this._oldId? c.id != this._oldId : c != this.$n()))) 
			return;
		
		out = n? [] : out;
	}

	out.push('<', tag, ' ', this.domAttrs_(), '>');
	var c = this.firstChild,
		hasCaption = c ? c.$instanceof(zul.wgt.Caption) : false;
	if (!hasCaption) 
		out.push('<a id="', uuid, '-cave" class="', this.$s('content'), '" >');

	if (this.isClosable())
		out.push('<div id="', uuid , '-btn" class="', this.$s('button'), '">', removeIcon, '</div>');

	this.contentRenderer_(out);
		
	if (!hasCaption)
		out.push('</a>');
	out.push('</', tag, '>');

	if (isAccordion && n) 
		jq(n).prepend(out.join(''));
	
}