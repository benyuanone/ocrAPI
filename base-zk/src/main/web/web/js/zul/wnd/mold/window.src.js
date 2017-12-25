
function (out, skipper) {
	var uuid = this.uuid,
		title = this.getTitle(),
		caption = this.caption,
		contentStyle = this.getContentStyle(),
		contentSclass = this.getContentSclass();

	out.push('<div', this.domAttrs_(), '>');
	
	if (caption || title) {
		out.push('<div id="',
			uuid, '-cap" class="', this.$s('header'), '">');

		
		if (caption) caption.redraw(out);
		else {
			var icon = this.$s('icon');
			if (this._closable) {
				out.push('<div id="', uuid , '-close" class="', icon, ' ',
						this.$s('close'), '"><i class="',
						this.getClosableIconClass_(), '"></i></div>');
			}
			if (this._maximizable) {
				var maxd = this._maximized;
				out.push('<div id="', uuid , '-max" class="', icon, ' ',
						this.$s('maximize'));
				if (maxd)
					out.push(' ', this.$s('maximized'));
				var maxIcon = maxd ? this.getMaximizedIconClass_() : this.getMaximizableIconClass_();
				out.push('"><i class="', maxIcon, '"></i></div>');
			}
			if (this._minimizable) {
				out.push('<div id="', uuid , '-min" class="', icon, ' ',
						this.$s('minimize'), '" ><i class="',
						this.getMinimizableIconClass_(), '"></i></div>');
			}
			out.push(zUtl.encodeXML(title));
		}
		out.push('</div>');
	} 
	out.push('<div id="', uuid, '-cave" class="');
	
	if (contentSclass)
		out.push(contentSclass, ' ');
	out.push(this.$s('content'), '" ');
	
	if (contentStyle)
		out.push(' style="', contentStyle, '"');
	out.push('>');

	if (!skipper)
		for (var w = this.firstChild; w; w = w.nextSibling)
			if (w != caption)
				w.redraw(out);
	out.push('</div></div>');
}