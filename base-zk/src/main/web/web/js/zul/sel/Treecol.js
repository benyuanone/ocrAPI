(function(){function b(d,e){if(d){for(var c=d.firstChild,f;c;c=c.nextSibling){if((f=c.treerow)&&e<f.nChildren){f.getChildAt(e).rerender()}b(c.treechildren,e)}}}function a(g,c,e,l,q){var m=[];for(var j=0,n=0,o=g.firstChild;o;o=o.nextSibling,n++){if(o.treechildren){a(o.treechildren,c,e,l,q)}for(var f=0,p=o.getFirstCell();p;p=p.nextSibling,f++){if(f==c){m[j++]={wgt:p,index:n}}}}var h=e=="ascending"?-1:1;m.sort(function(i,d){var k=l(i.wgt,d.wgt,q)*h;if(k==0){k=(i.index<d.index?-1:1)}return k});for(var j=0,f=m.length;j<f;j++){g.appendChild(m[j].wgt.parent.parent)}}zul.sel.Treecol=zk.$extends(zul.mesh.SortWidget,{getTree:function(){return this.parent?this.parent.parent:null},getMeshBody:function(){var c=this.getTree();return c?c.treechildren:null},checkClientSort_:function(d){var c;return !(!this.getMeshBody()||!(c=this.getTree())||("paging"==c._mold))&&this.$supers("checkClientSort_",arguments)},replaceCavedChildrenInOrder_:function(e){var g=this.getMeshWidget(),c=this.getMeshBody(),f=c.desktop;try{c.unbind();a(c,this.getChildIndex(),this.getSortDirection(),this.sorting,(this[e?"_sortAscending":"_sortDescending"]=="client(number)"));this._fixDirection(e)}finally{var d=g._syncingbodyrows;g._syncingbodyrows=true;try{g.clearCache();jq(g.$n("rows")).replaceWith(c.redrawHTML_());c.bind(f);g._bindDomNode()}finally{g._syncingbodyrows=d}}},setMaxlength:(function(c,d,e){return function(f,g){var h=this[c];this.__fname__=c.substring(1);this[c]=f=d.apply(this,arguments);if(h!==f||(g&&g.force)){e.apply(this,arguments)}delete this.__fname__;return this}})("_maxlength",(function(c){return !c||c<0?0:c}),(function(){if(this.desktop){this.rerender();this.updateCells_()}})),getMaxlength:_zkf$=function(){return this._maxlength},isMaxlength:_zkf$,updateCells_:function(){var c=this.getTree();if(c){var d=this.getChildIndex(),e=c.treefoot;b(c.treechildren,d);if(e&&d<e.nChildren){e.getChildAt(d).rerender()}}},bind_:function(){this.$supers(zul.sel.Treecol,"bind_",arguments);var c;if(c=this.$n()){this.domListen_(c,"onMouseOver","_doSortMouseEvt").domListen_(c,"onMouseOut","_doSortMouseEvt")}},unbind_:function(){var c;if(c=this.$n()){this.domUnlisten_(c,"onMouseOver","_doSortMouseEvt").domUnlisten_(c,"onMouseOut","_doSortMouseEvt")}this.$supers(zul.sel.Treecol,"unbind_",arguments)},_doSortMouseEvt:function(c){var d=this.getSortAscending();if(d!="none"){jq(this.$n())[c.name=="onMouseOver"?"addClass":"removeClass"](this.getZclass()+"-sort-over")}},domLabel_:function(){return zUtl.encodeXML(this.getLabel(),{maxlength:this._maxlength})}})})();