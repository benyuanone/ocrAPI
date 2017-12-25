(function(){function b(d,f){var g=""+d,e=g.indexOf(f);return e<0?0:g.length-e-1}function a(i,h,g,e){var f;if(g){f=Math.pow(10,g);i*=f;h*=f;i=Math.round(i);h=Math.round(h)}var d=e?i+h:i-h;if(g){d/=f}return d}function c(h,g){var e=h._localizedSymbols?h._localizedSymbols.DECIMAL:zk.DECIMAL,f=b(h._step,e),d=b(g||h._value,e);h._fixedDigits=Math.max(f,d)}zul.inp.Doublespinner=zk.$extends(zul.inp.NumberInputWidget,{_step:1,_buttonVisible:true,setStep:(function(d,e){return function(f,g){var h=this[d];this[d]=f;if(h!==f||(g&&g.force)){this.__fname__=d.substring(1);e.apply(this,arguments);delete this.__fname__}return this}})("_step",(function(d){c(this)})),getStep:_zkf$=function(){return this._step},isStep:_zkf$,setButtonVisible:(function(d,e){return function(f,g){var h=this[d];this[d]=f;if(h!==f||(g&&g.force)){this.__fname__=d.substring(1);e.apply(this,arguments);delete this.__fname__}return this}})("_buttonVisible",(function(d){zul.inp.RoundUtl.buttonVisible(this,d)})),getButtonVisible:_zkf$=function(){return this._buttonVisible},isButtonVisible:_zkf$,inRoundedMold:function(){return true},doubleValue:function(){return this.$supers("getValue",arguments)},setConstraint:function(d){if(typeof d=="string"&&d.charAt(0)!="["){var e=new zul.inp.SimpleDoubleSpinnerConstraint(d);this._min=e._min;this._max=e._max;this.$supers("setConstraint",[e])}else{this.$supers("setConstraint",arguments)}},coerceFromString_:function(l){if(!l){return null}var f=zk.fmt.Number.unformat(this._format,l,false,this._localizedSymbols),m=f.raw,e=parseFloat(m),g=""+e,k=g.indexOf("."),i=m.indexOf(".");if(isNaN(e)||g.indexOf("e")<0){if(i==0){m="0"+m;++i}if(i>=0&&m.substring(m.substring(i+1))&&k<0){k=g.length;g+="."}var h=m.length,d=g.length;if(k>=0&&k<i){d-=k;h-=i;for(var j=i-k;j-->0;){g="0"+g}}if(d<h){for(var j=h-d;j-->0;){g+="0"}}if(isNaN(e)||(m!=g&&m!="-"+g&&m.indexOf("e")<0)){if(!isNaN(e)&&m!=g){return{error:zk.fmt.Text.format(msgzul.ILLEGAL_VALUE)}}return{error:zk.fmt.Text.format(msgzul.NUMBER_REQUIRED,l)}}}if(f.divscale){e=e/Math.pow(10,f.divscale)}c(this,e);return e},coerceToString_:function(f){var e=this._format,g=this._localizedSymbols?this._localizedSymbols.DECIMAL:zk.DECIMAL;if(typeof f==="number"&&f%1==0){var d=1;if(this._step&&(decimal=(this._step+"").split(".")[1])){d=decimal.length}f=parseFloat(f).toFixed(d)}return f==null?"":e?zk.fmt.Number.format(e,parseFloat(f),this._rounding,this._localizedSymbols):g=="."?(""+f):(""+f).replace(".",g)},onSize:function(){zul.inp.RoundUtl.onSize(this)},onHide:zul.inp.Textbox.onHide,validate:zul.inp.Doublebox.validate,doKeyDown_:function(d){var e=this.getInputNode();if(e.disabled||e.readOnly){return}switch(d.keyCode){case 38:this.checkValue();this._increase(true);d.stop();return;case 40:this.checkValue();this._increase(false);d.stop();return}this.$supers("doKeyDown_",arguments)},_ondropbtnup:function(d){this.domUnlisten_(document.body,"onZMouseup","_ondropbtnup");this._stopAutoIncProc();this._currentbtn=null},_btnDown:function(e){if(!this._buttonVisible||this._disabled){return}var f=this.$n("btn");if(!zk.dragging){if(this._currentbtn){this._ondropbtnup(e)}this.domListen_(document.body,"onZMouseup","_ondropbtnup");this._currentbtn=f}this.checkValue();var g=zk(f).revisedOffset(),d=(e.pageY-g[1])<f.offsetHeight/2;if(d){this._increase(true);this._startAutoIncProc(true)}else{this._increase(false);this._startAutoIncProc(false)}e.stop()},checkValue:function(){var f=this.getInputNode(),e=this._min,d=this._max;if(!f.value){if(e&&d){f.value=(e<=0&&0<=d)?0:e}else{if(e){f.value=e<=0?0:e}else{if(d){f.value=0<=d?0:d}else{f.value=0}}}}},_btnUp:function(e){if(!this._buttonVisible||this._disabled||zk.dragging){return}this._onChanging();this._stopAutoIncProc();var f=this.getInputNode();if(zk.ie<11){var d=f.value.length;zk(f).setSelectionRange(d,d)}f.focus()},_increase:function(g){var f=this.getInputNode(),h=this.coerceFromString_(f.value);if(h&&h.error){return}var e=Math.max(b(h),this._fixedDigits),d=a(h,this._step,e,g);if(d>Math.pow(2,63)-1){d=Math.pow(2,63)-1}else{if(d<-Math.pow(2,63)){d=-Math.pow(2,63)}}if(this._max!=null&&d>this._max){d=h}else{if(this._min!=null&&d<this._min){d=h}}f.value=this.coerceToString_(d);this._onChanging()},_clearValue:function(){this.getInputNode().value=this._defRawVal="";return true},_startAutoIncProc:function(d){var e=this;if(this.timerId){clearInterval(this.timerId)}this.timerId=setInterval(function(){e._increase(d)},200)},_stopAutoIncProc:function(){if(this.timerId){clearTimeout(this.timerId)}this.timerId=null},syncWidth:function(){zul.inp.RoundUtl.syncWidth(this,this.$n("btn"))},doFocus_:function(d){this.$supers("doFocus_",arguments);zul.inp.RoundUtl.doFocus_(this)},doBlur_:function(d){this.$supers("doBlur_",arguments);zul.inp.RoundUtl.doBlur_(this)},afterKeyDown_:function(d,e){if(!e&&this._inplace){jq(this.$n()).toggleClass(this.getInplaceCSS(),d.keyCode==13?null:false)}return this.$supers("afterKeyDown_",arguments)},getAllowedKeys_:function(){var d=this._localizedSymbols;return this.$supers("getAllowedKeys_",arguments)+(d?d:zk).DECIMAL+"e"},bind_:function(){this.$supers(zul.inp.Doublespinner,"bind_",arguments);var d;if(d=this.$n("btn")){this.domListen_(d,"onZMouseDown","_btnDown").domListen_(d,"onZMouseUp","_btnUp")}zWatch.listen({onSize:this})},unbind_:function(){if(this.timerId){clearTimeout(this.timerId);this.timerId=null}zWatch.unlisten({onSize:this});var d=this.$n("btn");if(d){this.domUnlisten_(d,"onZMouseDown","_btnDown").domUnlisten_(d,"onZMouseUp","_btnUp")}this.$supers(zul.inp.Doublespinner,"unbind_",arguments)},getBtnUpIconClass_:function(){return"z-icon-angle-up"},getBtnDownIconClass_:function(){return"z-icon-angle-down"}})})();