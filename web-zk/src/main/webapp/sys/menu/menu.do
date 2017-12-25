<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.menu.MenuAction">
    <custom-attributes caname="/sys/menu/menu.do" />
    <borderlayout id="bdLayout">
       <west id="westLayout" title="菜单管理" size="220px" flex="true" splittable="true" collapsible="true">
           <div align="left">
               <tree id="tree" zclass="z-dottree" >
                   <treechildren >
                      <treeitem id="rootItem" value="-1" >
                        <treerow>
                           <treecell> 菜单维护 </treecell>
                        </treerow>
                        <treechildren id="treeChild" />
                      </treeitem>
                   </treechildren>
               </tree>
           </div>
       </west>
      <center flex="true">
        <vbox>
            <div  id="operateGrid" class="container-fluid buttonDiv operateGrid" >
               <div class="col-sm-12">
                  <div class="btn-group">
                       <button id="saveBtn" mold="bs" class="btn-primary" iconSclass="z-icon z-icon-save">保存</button>
                       <button id="copyBtn" mold="bs" class="btn-primary" iconSclass="z-icon z-icon-copy">复制</button>
                       <button id="pasteBtn" mold="bs" class="btn-primary" iconSclass="z-icon z-icon-paste">粘贴</button>
                  </div>
               </div>
            </div>
            <!-- 主表区 -->
            <div id="mainTableGrid" class="container-fluid mainTableGrid" >
                 <div class="row form-horizontal" style="margin-top:20px;height:50px;" >
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;line-height:2;">
                         <label value="菜单名称*" style="color:red" />
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;">
                         <textbox id="baseInfo_name" class="form-control input-sm"   />
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;height:30px;line-height:2;">
                         <label value="做为系统内部查看使用" />
                     </div>
                 </div>
                 <div class="row form-horizontal" style="height:50px;" >
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;line-height:2;">
                         <label value="多语言标签*" style="color:red" />
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;">
                         <textbox id="baseInfo_icon" class="form-control input-sm"   />
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;height:30px;line-height:2;">
                         <label value="系统显示使用" />
                     </div>
                 </div>
                 <div class="row form-horizontal" style="height:50px;" >
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;line-height:2;">
                         <label value="菜单类型*" style="color:red" />
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;">
                         <listbox id="baseInfo_type" mold="select" class="form-control input-sm"  >
                             <listitem value="0" label="菜单" selected="true" />
                             <listitem value="2" label="页面" />
                             <listitem value="1" label="控件" />
                             <listitem value="3" label="按钮" />
                         </listbox>
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;height:30px;line-height:2;">
                         <label value="需要区分按钮还是菜单，权限配置使用" />
                     </div>
                 </div>

                 <div class="row form-horizontal" style="height:50px;">
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;line-height:2;">
                         <label value="页面类型*"  style="color:red"/>
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;">
                         <listbox id="baseInfo_pageType" mold="select" class="form-control input-sm" >
                             <listitem value="4" label="列表" selected="true" />
                             <listitem value="1" label="新增" />
                             <listitem value="2" label="查看" />
                             <listitem value="3" label="修改" />
                         </listbox>
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;height:30px;line-height:2;">
                         <label value="菜单的样式表" />
                     </div>
                 </div>
                 <div class="row form-horizontal" style="height:50px;" >
                   <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;line-height:2;">
                      <label value="页面CA/控件属性*" style="color:red" />
                   </div>
                   <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;">
                       <textbox id="baseInfo_pageCa" class="form-control input-sm"   />
                   </div>
                   <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;height:30px;line-height:2;">
                        <label value="页面ca或控件属性" />
                   </div>
                 </div>
                 <div class="row form-horizontal" style="height:50px;">
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;line-height:2;">
                         <label value="菜单iconClass"  />
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;">
                         <textbox id="baseInfo_iconClass" class="form-control input-sm"   />
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;height:30px;line-height:2;">
                         <label value="菜单的样式表" />
                     </div>
                 </div>
                 <div class="row form-horizontal" style="height:50px;">
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;line-height:2;">
                         <label value="关联页面"  />
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;">
                         <bandbox class="input-sm" style="width:100%;" id="baseInfo_pageId" readonly="true" autodrop="false" use="com.ourway.base.zk.bandbox.PageBandbox" />
                     </div>
                     <div class="col-sm-4 control-label" style="text-align:left;padding-right:2px;height:30px;line-height:2;">
                         <label value="从页面配置中选择对应的功能页面" />
                     </div>
                 </div>





            </div>
          </vbox>


      </center>
    </borderlayout>
</window>