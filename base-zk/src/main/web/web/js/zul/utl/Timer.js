zul.utl.Timer=zk.$extends(zk.Widget,{_running:true,_delay:0,setRepeats:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_repeats",(_zkf=function(){if(this.desktop){this._sync()}})),getRepeats:_zkf$=function(){return this._repeats},isRepeats:_zkf$,setDelay:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_delay",(_zkf)),getDelay:_zkf$=function(){return this._delay},isDelay:_zkf$,setRunning:(function(a,b){return function(c,d){var e=this[a];this[a]=c;if(e!==c||(d&&d.force)){this.__fname__=a.substring(1);b.apply(this,arguments);delete this.__fname__}return this}})("_running",(_zkf)),getRunning:_zkf$=function(){return this._running},isRunning:_zkf$,play:function(){this.setRunning(true)},stop:function(){this.setRunning(false)},_sync:function(){this._stop();this._play()},_play:function(){if(this._running){var a=this.proxy(this._tmfn);if(this._repeats){this._iid=setInterval(a,this._delay);zAu.onError(this.proxy(this._onErr))}else{this._tid=setTimeout(a,this._delay)}}},_stop:function(){var a=this._iid;if(a){this._iid=null;clearInterval(a)}a=this._tid;if(a){this._tid=null;clearTimeout(a)}zAu.unError(this.proxy(this._onErr))},_onErr:function(b,a){if(a==410||a==404||a==405){this._stop()}},_tmfn:function(){if(!this._repeats){this._running=false}this.fire("onTimer",null,{ignorable:true})},redraw:function(){},bind_:function(){this.$supers(zul.utl.Timer,"bind_",arguments);this._visible=false;if(this._running){this._play()}},unbind_:function(){this._stop();this.$supers(zul.utl.Timer,"unbind_",arguments)}});