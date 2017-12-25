<?xml version="1.0" encoding="UTF-8"?>
<window
    width="99%"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.BandboxTreeTemplateAction">
    <custom-attributes caname="/template/bandboxTree.do" />
    <div>
        <tree id="tree" zclass="z-dottree">
           <treechildren>
               <treeitem id="rootItem">
                   <treerow>
                      <treecell id="rootCell"></treecell>
                   </treerow>
                   <treechildren id="treeChild" />
               </treeitem>
           </treechildren>
         </tree>
    </div>
</window>