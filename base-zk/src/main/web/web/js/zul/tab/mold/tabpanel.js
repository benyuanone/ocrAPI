function(b){var e=this.uuid,c=this.getTabbox();if(c.inAccordionMold()){var d=this.getLinkedTab();b.push('<div class="',this.getZclass(),'" id="',e,'">');if(d&&!d.$n()){d.redraw(b)}b.push('<div id="',e,'-cave"',this.domAttrs_({id:1,zclass:1}),">");for(var a=this.firstChild;a;a=a.nextSibling){a.redraw(b)}b.push("</div></div>")}else{b.push("<div ",this.domAttrs_(),">");for(var a=this.firstChild;a;a=a.nextSibling){a.redraw(b)}b.push("</div>")}};