(function(){var a=(function(){var b=zk.feature.pe;return function(){return b&&zk.isLoaded("zkex.grid")}})();zul.grid.Row=zk.$extends(zul.Widget,{setAlign:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_align",(function(b){var c=this.$n();if(c){c.align=b}})),getAlign:_zkf$=function(){return this._align},isAlign:_zkf$,setNowrap:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_nowrap",(function(b){var d=this.$n();if(d&&(d=d.cells)){for(var c=d.length;c--;){d[c].noWrap=b}}})),getNowrap:_zkf$=function(){return this._nowrap},isNowrap:_zkf$,setValign:(function(b,c){return function(d,e){var f=this[b];this[b]=d;if(f!==d||(e&&e.force)){this.__fname__=b.substring(1);c.apply(this,arguments);delete this.__fname__}return this}})("_valign",(function(b){var c=this.$n();if(c){c.vAlign=b}})),getValign:_zkf$=function(){return this._valign},isValign:_zkf$,getGrid:function(){return this.parent?this.parent.parent:null},setVisible:function(b){if(this.isVisible()!=b){this.$supers("setVisible",arguments);if(b&&this.isStripeable_()&&this.parent){this.parent.stripe()}}},getSpans:function(){return zUtl.intsToString(this._spans)},setSpans:function(b){if(this.getSpans()!=b){this._spans=zUtl.stringToInts(b,1);this.rerender()}},_getIndex:function(){return this.parent?this.getChildIndex():-1},getGroup:function(){if(a()&&this.parent&&this.parent.hasGroup()){for(var b=this;b;b=b.previousSibling){if(b.$instanceof(zkex.grid.Group)){return b}}}return null},setStyle:function(b){if(this._style!=b){if(!zk._rowTime){zk._rowTime=jq.now()}this._style=b;this.rerender()}},rerender:function(){if(this.desktop){this.$supers("rerender",arguments);if(this.parent){this.parent._syncStripe()}}},getSclass:function(){var c=this.$supers("getSclass",arguments);if(c!=null){return c}var b=this.getGrid();return b?b.getSclass():c},_getChdextr:function(b){return b.$n("chdextr")||b.$n()},scrollIntoView:function(){var b=this.getGrid()._scrollbar;if(b){b.syncSize();b.scrollToElement(this.$n())}else{this.$supers("scrollIntoView",arguments)}},insertChildHTML_:function(e,b,d){var c=this.encloseChildHTML_({child:e,index:e.getChildIndex(),zclass:this.getZclass()});if(b){jq(this._getChdextr(b)).before(c)}else{jq(this).append(c)}e.bind(d)},removeChildHTML_:function(b){this.$supers("removeChildHTML_",arguments);jq(b.uuid+"-chdextr",zk).remove()},encloseChildHTML_:function(d){var c=d.out||[],e=d.child,b=e.$instanceof(zul.wgt.Cell);if(!b){c.push('<td id="',e.uuid,'-chdextr"',this._childAttrs(e,d.index),'><div id="',e.uuid,'-cell" class="',d.zclass,'-content">')}e.redraw(c);if(!b){c.push("</div></td>")}if(!d.out){return c.join("")}},_childAttrs:function(f,g){var h=g,s=1;if(this._spans){for(var r=0,p=this._spans.length;r<p;++r){if(r==g){s=this._spans[r];break}h+=this._spans[r]-1}}var c,i,q,d,b=this.getGrid();if(b){var n=b.columns;if(n){if(h<n.nChildren){var e=n.getChildAt(h);c=e.isVisible()?"":"display:none;";i=e.getHeight();q=e.getAlign();d=e.getValign()}}}var t=this.domStyle_({visible:1,width:1,height:1}),m=zk.isLoaded("zkex.grid")&&f.$instanceof(zkex.grid.Detail);if(m){var l=f.getWidth();if(l){t+="width:"+l+";"}}if(c||i||q||d){t+=c;if(i){t+="height:"+i+";"}if(q){t+="text-align:"+q+";"}if(d){t+="vertical-align:"+d+";"}}var u=m?f.$s("outer"):this.$s("inner"),o="";if(s!==1){o+=' colspan="'+s+'"'}if(this._nowrap){o+=' nowrap="nowrap"'}if(t){o+=' style="'+t+'"'}return o+' class="'+u+'"'},isStripeable_:function(){return true},domStyle_:function(d){if((a()&&(this.$instanceof(zkex.grid.Group)||this.$instanceof(zkex.grid.Groupfoot)))||(d&&d.visible)){return this.$supers("domStyle_",arguments)}var b=this.$supers("domStyle_",arguments),c=this.getGroup();if(this._align){b+=" text-align:"+this._align+";"}if(this._valign){b+=" vertical-align:"+this._valign+";"}return c&&!c.isOpen()?b+"display:none;":b},onChildAdded_:function(b){this.$supers("onChildAdded_",arguments);if(b.$instanceof(zul.grid.Detail)){this.detail=b}},onChildRemoved_:function(b){this.$supers("onChildRemoved_",arguments);if(b==this.detail){this.detail=null}},doFocus_:function(b){this.$supers("doFocus_",arguments);var f=this.getGrid(),h=f?f.frozen:null,d=f&&f.rows?f.rows.$n():null,k,g;if(h&&d){g=jq(b.domTarget).parents("td");for(var e=0,c=g.length;e<c;e++){k=g[e];if(k.parentNode.parentNode==d){f._moveToHidingFocusCell(k.cellIndex);break}}}},doMouseOver_:function(b){if(this._musin){return}this._musin=true;var c=this.$n();if(c&&zk.gecko&&this._draggable&&!jq.nodeName(b.domTarget,"input","textarea")){jq(c).addClass("z-draggable-over")}this.$supers("doMouseOver_",arguments)},doMouseOut_:function(b){var c=this.$n();if((this._musin&&jq.isAncestor(c,b.domEvent.relatedTarget||b.domEvent.toElement))){this.parent._musout=this;return}this._musin=false;if(c&&zk.gecko&&this._draggable&&!jq.nodeName(b.domTarget,"input","textarea")){jq(c).removeClass("z-draggable-over")}this.$supers("doMouseOut_",arguments)},domClass_:function(){var b=this.$supers("domClass_",arguments),c=this.getGrid();if(c&&jq(this.$n()).hasClass(c=c.getOddRowSclass())){return b+" "+c}return b},deferRedrawHTML_:function(b){b.push("<tr",this.domAttrs_({domClass:1}),' class="z-renderdefer"></tr>')}})})();