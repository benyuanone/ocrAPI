

function (out) {
	var isVer = this.isVertical(),
		uuid = this.uuid;
	out.push('<div', this.domAttrs_(isVer ? {width:true} : {height:true}), '><div id="',
				uuid, '-inner" class="', this.$s('center'), '"><div id="',
			    uuid, '-btn" class="', this.$s('button'), '"></div></div></div>');
}