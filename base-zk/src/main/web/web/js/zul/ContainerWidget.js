zul.ContainerWidget=zk.$extends(zul.Widget,{bind_:function(){this.$supers(zul.ContainerWidget,"bind_",arguments);if(jq(this).data("scrollable")){this.domListen_(this.getCaveNode(),"onScroll")}},_doScroll:function(){if(jq(this).data("scrollable")){zWatch.fireDown("onScroll",this)}},unbind_:function(){if(jq(this).data("scrollable")){this.domUnlisten_(this.getCaveNode(),"onScroll")}this.$supers(zul.ContainerWidget,"unbind_",arguments)}});