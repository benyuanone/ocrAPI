

zul.inp.Textbox = zk.$extends(zul.inp.InputWidget, {
	_value: '',
	_rows: 1,

	$define: {
		
		
		multiline: function () {
			this.rerender();
		},
		
		
		tabbable: null,
		
		
		rows: function (v) {
			var inp = this.getInputNode();
			if (inp && this.isMultiline())
				inp.rows = v;
		},
		
		
		type: zk.ie < 11 ? function () {
			this.rerender(); 
		}: function (type) {
			var inp = this.getInputNode();
			if (inp)
				inp.type = type;
		}
	},
	
	textAttrs_: function () {
		var html = this.$supers('textAttrs_', arguments);
		if (this._multiline)
			html += ' rows="' + this._rows + '"';
		return html;
	}
});
