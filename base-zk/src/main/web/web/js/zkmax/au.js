(function(){zAu.cmd0.echoGx=function(m,f){var h=zAu.cmd0.echoGx.getAll();for(var g=arguments.length;g-->2;){for(var c=arguments[g],d=h.length;d--;){try{var l=h[d];if(l.zk.Desktop.$(c)){var b="zAu.send(new zk.Event(zk.Desktop.$('"+c+"'),'"+m+"'";if(f!==null){b+=",'"+f+"'"}l.setTimeout(b+"))",0);break}}catch(k){}}}};zAu.cmd0.echoGx.getAll=function(){return zUtl.frames(top)};if(zk.mobile){zAu.cmd0.download=function(c){if(c){var d=document.createElement("a"),b=document.createEvent("Event");d.id="zk_download";d.href=c;d.target="_blank";b.initEvent("click",true,false);d.dispatchEvent(b)}}}zk.copy(jq,{camelCase:(function(){var f={},d=0,c=/^-ms-/,e=/-([\da-z])/gi,b=function(h,i){return i.toUpperCase()},g=function(h){return h.replace(c,"ms-").replace(e,b)};return function(h){if(f[h]===undefined){f[h]=g(h);if(d++>1000){d=0;f={}}}return f[h]}})()});var a=zAu.addAuRequest;zAu.addAuRequest=function(c,m){var h=zAu.getAuRequests(c),n=m.target;if(n){var b=n.uuid,k=m.name,d=n.$class;if(d._duplicateIgnoreEvts&&d._duplicateIgnoreEvts[k]){for(var g=0,e=h.length;g<e;g++){var f=h[g];if(f.target&&f.target.uuid==b&&f.name==k){h.splice(g,1);break}}}else{if(h.length&&d._repeatIgnoreEvts&&d._repeatIgnoreEvts[k]){var l=h[h.length-1];if(l.target&&l.target.uuid==b&&l.name==k){h.pop()}}}}a(c,m)}})();