function(c){c.push("<tr",this.domAttrs_(),">");for(var b=this.firstChild;b;b=b.nextSibling){b.redraw(c)}var a=this.getTree();if(a._nativebar&&!a.frozen){c.push('<td class="',this.$s("bar"),'" />')}c.push("</tr>")};