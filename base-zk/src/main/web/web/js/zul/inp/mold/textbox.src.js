
function (out) {
	var uuid = this.uuid;
	
	
	if(this.isMultiline()) 
		out.push('<textarea', this.domAttrs_(), '>\n', this._areaText(), '</textarea>');
	else 
		out.push('<input', this.domAttrs_(), '/>');
}