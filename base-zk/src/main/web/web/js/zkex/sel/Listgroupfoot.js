zkex.sel.Listgroupfoot=zk.$extends(zul.sel.Listitem,{isStripeable_:function(){return false},bind_:function(){this.$supers(zkex.sel.Listgroupfoot,"bind_",arguments);var g=this.$n(),f=g.parentNode.parentNode,a=f.firstChild;if(a){var e=0;for(var c=a.firstChild;c;c=c.nextSibling){if(zk(c).isVisible()){e++}}for(var b=g.cells,d=b.length;d--;){e-=b[d].colSpan}if(e>0&&g.cells.length){g.cells[g.cells.length-1].colSpan+=e}}}});