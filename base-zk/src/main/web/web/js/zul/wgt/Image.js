zul.wgt.Image=zk.$extends(zul.Widget,{setSrc:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_src",(function(a){if(a&&this._preloadImage){zUtl.loadImage(a)}var b=this.getImageNode();if(b){b.src=a||""}})),getSrc:_zkf$=function(){return this._src},isSrc:_zkf$,setHover:(function(a){return function(b){this[a]=b;return this}})("_hover"),getHover:_zkf$=function(){return this._hover},isHover:_zkf$,setAlign:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_align",(function(a){var b=this.getImageNode();if(b){b.align=a||""}})),getAlign:_zkf$=function(){return this._align},isAlign:_zkf$,setHspace:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_hspace",(function(a){var b=this.getImageNode();if(b){b.hspace=a}})),getHspace:_zkf$=function(){return this._hspace},isHspace:_zkf$,setVspace:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_vspace",(function(a){var b=this.getImageNode();if(b){b.vspace=a}})),getVspace:_zkf$=function(){return this._vspace},isVspace:_zkf$,getImageNode:function(){return this.$n()},doMouseOver_:function(){var b=this._hover;if(b){var a=this.getImageNode();if(a){a.src=b}}this.$supers("doMouseOver_",arguments)},doMouseOut_:function(){if(this._hover){var a=this.getImageNode();if(a){a.src=this._src||""}}this.$supers("doMouseOut_",arguments)},domAttrs_:function(b){var a=this.$supers("domAttrs_",arguments);if(!b||!b.content){a+=this.contentAttrs_()}return a},contentAttrs_:function(){var a=' src="'+(this._src||"")+'"',b;if(b=this._align){a+=' align="'+b+'"'}if(b=this._hspace){a+=' hspace="'+b+'"'}if(b=this._vspace){a+=' vspace="'+b+'"'}return a}});