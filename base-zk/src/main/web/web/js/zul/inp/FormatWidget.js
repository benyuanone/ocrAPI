zul.inp.FormatWidget=zk.$extends(zul.inp.InputWidget,{setFormat:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_format",(function(){var a=this.getInputNode();if(a){a.value=this.coerceToString_(this._value)}})),getFormat:_zkf$=function(){return this._format},isFormat:_zkf$,doFocus_:function(a){this.$supers("doFocus_",arguments);if(this._shortcut){this.getInputNode().value=this._shortcut}},updateChange_:function(a){var b=this.$supers("updateChange_",arguments);if(a){delete this._shortcut}return b}});