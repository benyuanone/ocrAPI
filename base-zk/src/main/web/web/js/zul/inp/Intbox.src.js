

zul.inp.Intbox = zk.$extends(zul.inp.NumberInputWidget, {
	
	intValue: function (){
		return this.$supers('getValue', arguments);
	},
	coerceFromString_: function (value) {
		if (!value) return null;

		var info = zk.fmt.Number.unformat(this._format, value, false, this._localizedSymbols),
			val = parseInt(info.raw, 10),
			sval;
		if (info.raw.length < 17) 
			sval = val.toString();
		else 
			sval = new zk.BigDecimal(info.raw).$toString(); 
	
		
		if (isNaN(val) || (info.raw != sval && info.raw != '-'+sval))
			return {error: zk.fmt.Text.format(msgzul.INTEGER_REQUIRED, value)};
		if (val > 2147483647 || val < -2147483648)
			return {error: zk.fmt.Text.format(msgzul.OUT_OF_RANGE+'(−2147483648 - 2147483647)')};

		if (info.divscale) val = Math.round(val / Math.pow(10, info.divscale));
		return val;
	},
	coerceToString_: function (value) {
		var fmt = this._format;
		return fmt ? zk.fmt.Number.format(fmt, value, this._rounding, this._localizedSymbols)
					: value != null  ? ''+value: '';
	}
});