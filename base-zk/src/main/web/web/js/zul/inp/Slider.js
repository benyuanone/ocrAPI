(function(){function d(f){var h=""+f,g=h.indexOf(".");return g<0?0:h.length-g-1}function c(f,h){var g=Math.pow(10,h);return Math.round(f*g)/g}function a(m){var h=m.$n("btn"),l=m._curpos,i=m.isDecimal();h.title=i?l.toFixed(d(e(m))):l;m.updateFormData(l);var k=m.isVertical(),j=zk(m.$n()).cmOffset(),g=k?m._getHeight():m._getWidth(),f=g>0?((l-m._minpos)*g)/(m._maxpos-m._minpos):0;if(!i){f=Math.round(f)}j=zk(h).toStyleOffset(j[0],j[1]);j=k?[0,(j[1]+f)]:[(j[0]+f),0];j=m._snap(j[0],j[1]);return j[(k?1:0)]}function b(j,i){var h=jq(j.$n("btn")),f=j.isVertical()?["top","height"]:["left","width"],g={};g[f[0]]=jq.px0(i?(i+zk.parseInt(h.css(f[0]))-h[f[1]]()/2):a(j));return g}function e(g){var f=g._step;return(!g.isDecimal()||f!=-1)?f:0.1}zul.inp.Slider=zk.$extends(zul.Widget,{_orient:"horizontal",_height:"200px",_width:"200px",_curpos:0,_minpos:0,_maxpos:100,_slidingtext:"{0}",_pageIncrement:-1,_step:-1,_mode:"integer",setOrient:(function(f,g){return function(h,i){var j=this[f];this[f]=h;if(j!==h||(i&&i.force)){this.__fname__=f.substring(1);g.apply(this,arguments);delete this.__fname__}return this}})("_orient",(function(){this.rerender()})),getOrient:_zkf$=function(){return this._orient},isOrient:_zkf$,setCurpos:(function(f,g){return function(h,i){var j=this[f];this[f]=h;if(j!==h||(i&&i.force)){this.__fname__=f.substring(1);g.apply(this,arguments);delete this.__fname__}return this}})("_curpos",(function(){if(this.desktop){this._fixPos()}})),getCurpos:_zkf$=function(){return this._curpos},isCurpos:_zkf$,setMinpos:(function(f,g){return function(h,i){var j=this[f];this[f]=h;if(j!==h||(i&&i.force)){this.__fname__=f.substring(1);g.apply(this,arguments);delete this.__fname__}return this}})("_minpos",(function(f){if(this._curpos<f){this._curpos=f}this._fixStep();if(this.desktop){this._fixPos()}})),getMinpos:_zkf$=function(){return this._minpos},isMinpos:_zkf$,setMaxpos:(function(f,g){return function(h,i){var j=this[f];this[f]=h;if(j!==h||(i&&i.force)){this.__fname__=f.substring(1);g.apply(this,arguments);delete this.__fname__}return this}})("_maxpos",(function(f){if(this._curpos>f){this._curpos=f}this._fixStep();if(this.desktop){this._fixPos()}})),getMaxpos:_zkf$=function(){return this._maxpos},isMaxpos:_zkf$,setSlidingtext:(function(f){return function(g){this[f]=g;return this}})("_slidingtext"),getSlidingtext:_zkf$=function(){return this._slidingtext},isSlidingtext:_zkf$,setPageIncrement:(function(f){return function(g){this[f]=g;return this}})("_pageIncrement"),getPageIncrement:_zkf$=function(){return this._pageIncrement},isPageIncrement:_zkf$,setStep:(function(f,g){return function(h,i){var j=this[f];this[f]=h;if(j!==h||(i&&i.force)){this.__fname__=f.substring(1);g.apply(this,arguments);delete this.__fname__}return this}})("_step",(function(){this._fixStep()})),getStep:_zkf$=function(){return this._step},isStep:_zkf$,setName:(function(f,g){return function(h,i){var j=this[f];this[f]=h;if(j!==h||(i&&i.force)){this.__fname__=f.substring(1);g.apply(this,arguments);delete this.__fname__}return this}})("_name",(function(){if(this.efield){this.efield.name=this._name}})),getName:_zkf$=function(){return this._name},isName:_zkf$,setMode:(function(f,g){return function(h,i){var j=this[f];this[f]=h;if(j!==h||(i&&i.force)){this.__fname__=f.substring(1);g.apply(this,arguments);delete this.__fname__}return this}})("_mode",(function(){this._fixStep();if(this.desktop){this._fixPos()}})),getMode:_zkf$=function(){return this._mode},isMode:_zkf$,domClass_:function(){var f=this.$supers("domClass_",arguments),g=this.isVertical();if(g){f+=" "+this.$s("vertical")}else{f+=" "+this.$s("horizontal")}if(this.inSphereMold()){f+=" "+this.$s("sphere")}else{if(this.inScaleMold()&&!g){f+=" "+this.$s("scale")}}return f},onup_:function(f){var g=zul.inp.Slider.down_btn,h;if(g){h=zk.Widget.$(g)}zul.inp.Slider.down_btn=null;if(h){jq(document).unbind("zmouseup",h.onup_)}},doMouseDown_:function(f){jq(document).bind("zmouseup",this.onup_);zul.inp.Slider.down_btn=this.$n("btn");this.$supers("doMouseDown_",arguments)},doClick_:function(p){var n=jq(this.$n("btn")),l=n.zk.revisedOffset(),s=this,r=this._pageIncrement,j=r<0&&e(this)<0,m=this.isVertical(),q=this._getHeight(),f=this._getWidth(),h=m?p.pageY-l[1]:p.pageX-l[0];if(!n[0]||n.is(":animated")){return}if(!j){if(r>0){var g=this._curpos+(h>0?r:-r);this._curpos=c(this._constraintPos(g),d(r))}else{var k=m?q:f,o=(h/k)*(this._maxpos-this._minpos);this._curpos=this._getSteppedPos(o+this._curpos)}h=null}var i=b(this,h);if(m&&zk.parseInt(i.top)>q){i.top=jq.px0(q)}if(!m&&zk.parseInt(i.left)>f){i.left=jq.px0(f)}n.animate(i,"slow",function(){l=j?s._realpos():s._curpos;l=s._constraintPos(l);s.fire("onScroll",s.isDecimal()?{decimal:l}:l);if(j){s._fixPos()}});this.$supers("doClick_",arguments)},_makeDraggable:function(){var f={constraint:this._orient||"horizontal",starteffect:this._startDrag,snap:f,change:this._dragging,endeffect:this._endDrag};if(e(this)>0){f.snap=this._getStepOffset()}this._drag=new zk.Draggable(this,this.$n("btn"),f)},_snap:function(g,j){var h=this.$n("btn"),i=zk(this.$n()).cmOffset();i=zk(h).toStyleOffset(i[0],i[1]);if(g<=i[0]){g=i[0]}else{var f=i[0]+this._getWidth();if(g>f){g=f}}if(j<=i[1]){j=i[1]}else{var f=i[1]+this._getHeight();if(j>f){j=f}}return[g,j]},_startDrag:function(g){var f=g.control;f.$n("btn").title="";f.slidepos=f._curpos,vert=f.isVertical();jq(document.body).append('<div id="zul_slidetip" class="z-slider-popup"style="position:absolute;display:none;z-index:60000;background-color:white;border: 1px outset">'+f.slidepos+"</div>");f.slidetip=jq("#zul_slidetip")[0];if(f.slidetip){var h=f.slidetip.style;if(zk.webkit){h.top="0px";h.left="0px"}h.display="block";zk(f.slidetip).position(f.$n(),vert?"end_before":"after_start")}},_dragging:function(h){var g=h.control,f=g.isDecimal(),j=g._realpos();if(j!=g.slidepos){g.slidepos=j=g._constraintPos(j);var i=f?j.toFixed(d(e(g))):j;if(g.slidetip){g.slidetip.innerHTML=g._slidingtext.replace(/\{0\}/g,i)}g.fire("onScrolling",f?{decimal:j}:j)}g._fixPos()},_endDrag:function(g){var f=g.control,h=f._realpos();f.fire("onScroll",f.isDecimal()?{decimal:h}:h);f._fixPos();jq(f.slidetip).remove();f.slidetip=null},_realpos:function(l){var n=zk(this.$n("btn")).revisedOffset(),f=zk(this.$n()).revisedOffset(),j=this._maxpos,i=this._minpos,g=e(this),k;if(this.isVertical()){var m=this._getHeight();k=m?((n[1]-f[1])*(j-i))/m:0}else{var h=this._getWidth();k=h?((n[0]-f[0])*(j-i))/h:0}if(!this.isDecimal()){k=Math.round(k)}if(g>0){return this._curpos=k>0?c(k+i,d(g)):i}else{return this._curpos=(k>0?k:0)+i}},_constraintPos:function(f){return f<this._minpos?this._minpos:(f>this._maxpos?this._maxpos:f)},_getSteppedPos:function(j){var f=this._minpos,h=e(this),i=1,g;j-=f;if(this.isDecimal()){i=Math.pow(10,d(h));j*=i;h*=i}g=j%h;return(j-g+Math.round((g)/h)*h)/i+f},_getWidth:function(){return this.$n().clientWidth-this.$n("btn").offsetWidth},_getHeight:function(){return this.$n().clientHeight-this.$n("btn").offsetHeight},_getStepOffset:function(){var f=this.isVertical()?this._getHeight():this._getWidth(),g=e(this),h=[0,0];if(g){h[(this.isVertical()?1:0)]=f>0?f*g/(this._maxpos-this._minpos):0}return h},_fixSize:function(){var j=this.$n(),g=this.$n("btn"),f=this.$n("inner").style;if(this.isVertical()){g.style.top="-"+g.offsetHeight/2+"px";var i=j.clientHeight;f.height=jq.px0(i>0?i:this._height-g.offsetHeight)}else{g.style.left="-"+g.offsetWidth/2+"px";var h=j.clientWidth;f.width=jq.px0(h>0?h:this._width-g.offsetWidth)}},_fixPos:function(){this.$n("btn").style[this.isVertical()?"top":"left"]=jq.px0(a(this))},_fixStep:function(){var f=e(this);if(this._drag){if(f<=0){if(this._drag.opts.snap){delete this._drag.opts.snap}}else{this._drag.opts.snap=this._getStepOffset()}}},onSize:function(){this._fixSize();this._fixPos()},inScaleMold:function(){return this.getMold()=="scale"},inSphereMold:function(){return this.getMold()=="sphere"},isVertical:function(){return"vertical"==this._orient},isDecimal:function(){return"decimal"==this._mode},updateFormData:function(f){if(this._name){f=f||0;if(!this.efield){this.efield=jq.newHidden(this._name,f,this.$n())}else{this.efield.value=f}}},onShow:function(){if(!this._drag){this._makeDraggable()}},bind_:function(){this.$supers(zul.inp.Slider,"bind_",arguments);this._fixSize();if(this.isRealVisible()){this._makeDraggable()}zWatch.listen({onSize:this,onShow:this});this.updateFormData(this._curpos);this._fixPos()},unbind_:function(){this.efield=null;if(this._drag){this._drag.destroy();this._drag=null}zWatch.unlisten({onSize:this,onShow:this});this.$supers(zul.inp.Slider,"unbind_",arguments)}})})();