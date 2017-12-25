<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.TabTreeAction">
    <custom-attributes caname="/sys/page/tabTreeComponent.do" />
    <borderlayout id="bdLayout">
            <west id="westLayout" size="220px" flex="true" splittable="true" collapsible="true">
               <div align="left">
                   <tree id="tree" zclass="z-dottree" >
                        <treechildren >
                            <treeitem id="rootItem" >
                               <treerow>
                                  <treecell id="rootCell"></treecell>
                               </treerow>
                               <treechildren id="treeChild" />
                            </treeitem>
                        </treechildren>
                   </tree>
               </div>
            </west>
            <center flex="true">
                <div  id="mainDiv" class="container-fluid mainDiv"  >
                     <div class="row form-horizontal"  >
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px; ">
                             <label value="Tab类型：" />
                         </div>
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px; ">
                             <listbox id="tabType" mold="select" class="form-control input-sm"  >
                                 <listitem value="tab" label="Tab" selected="true" />
                                 <listitem value="Panel" label="panel"  />
                             </listbox>
                         </div>
                         <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px; ">
                             <label value="上级：" />
                         </div>
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px; ">
                             <textbox id="tabParent" class="form-control input-sm"  />
                         </div>
                         <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px; ">
                             <label value="TabId：" />
                         </div>
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px; ">
                             <textbox id="tabId"  class="form-control input-sm"   />
                         </div>
                     </div>
                     <div class="row form-horizontal"  >
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px; ">
                             <label value="Tab名称：" />
                         </div>
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px; ">
                             <textbox id="tabName"  class="form-control input-sm"   />
                         </div>
                         <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px; ">
                             <label value="Tab标签：" />
                         </div>
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;">
                             <textbox id="tabLabel"  class="form-control input-sm"   />
                         </div>
                         <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;">
                             <label value="Tab组号：" />
                         </div>
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;">
                             <intbox id="tabGroup"  class="form-control input-sm"   />
                         </div>
                     </div>
                     <div class="row form-horizontal"  >
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px; ">
                             <label value="Panel默认开关：" />
                         </div>
                         <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px; ">
                             <listbox id="tabClose"  mold="select">
                                 <listitem value="0" label="默认打开" selected="true" />
                                 <listitem value="1" label="关闭"  />
                             </listbox>
                         </div>
                     </div>

                     <div class="row form-horizontal"  >
                         <div class="col-sm-5 control-label" style="text-align:center;padding-right:2px;align:center;">
                         </div>
                         <div class="col-sm-3 control-label" style="text-align:center;padding-right:2px;align:center;">
                             <hbox>
                                <button id="confirmBtn" mold="bs" sclass="btn-success input-sm" style="height:40px;width:80px;" label="确定" />
                                <button id="removeBtn" mold="bs" sclass="btn-warning input-sm" style="margin-left:15px;height:40px;width:80px;" label="删除" />
                                <button id="clearBtn" mold="bs" sclass="btn-success input-sm" style="margin-left:15px;height:40px;width:80px;" label="清空" />
                             </hbox>
                         </div>
                     </div>

                    <div class="row form-horizontal" style="border-bottom:1px solid #ccc;margin-top:30px;height:80px;" >
                        <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                            <label value="grid数量：" />
                        </div>
                        <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                            <intbox  id="gridNum" class="form-control input-sm"   />
                        </div>
                        <div class="col-sm-4 control-label" style="text-align:right;padding-right:2px;height:30px;">
                        </div>
                        <div class="col-sm-3 control-label" style="text-align:left;padding-right:2px;height:30px;">
                            <button id="generateBtn" mold="bs" sclass="btn-success" label="生成Grid" />
                        </div>
                    </div>
                </div>
            </center>
        </borderlayout>
</window>