
function (out, skipper) {	
	var	uuid = this.uuid,
		cap = this.caption,
		title = this.getTitle();
		title = title && !cap ? zUtl.encodeXML(title) :  null;
	
	out.push('<div ', this.domAttrs_(), '>');
	
	if (title || cap) {
		out.push('<div id="', uuid, '-header" class="', this.$s('header'),
				(this._closable? '': ' ' +  this.$s('readonly')),'">');
		if (cap)
			cap.redraw(out);
		else
			out.push('<div id="', uuid,'-title" class="', this.$s('title'), 
					'"><div id="', uuid,'-title-cnt" class="',
					this.$s('title-content'), '">', title, '</div></div>');
		out.push('</div>');
	}
	
	this._redrawCave(out, skipper);
	
	out.push('</div>');
}