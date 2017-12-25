
function(out) {
	if(null!=this.getIconClass()){
		out.push("<i class='",this.getIconClass(),"'></i>");
	}
	out.push('<span', this.domAttrs_(), '>', this.getEncodedText(), '</span>');
}