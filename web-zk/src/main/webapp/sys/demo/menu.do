<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	>
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
         <grid  id="baseInfo">
            <rows>
                <row spans="6" style="height:50px">
                    <div align="left">
                       <hbox>
                        <button id="saveBtn" label="保存" />
                        <button id="copyBtn" label="复制" />
                        <button id="pasteBtn" label="粘贴" />
                       </hbox>
                    </div>
               </row>
               <row spans="1,3,2" style="height:50px">
                    <div align="right"><label value="菜单名称*" style="color:red" /></div>
                    <textbox id="baseInfo_name" style="width:600px"   />
                    <label value="做为系统内部查看使用" />
               </row>
               <row spans="1,3,2" style="height:50px">
                     <div align="right"><label value="多语言标签/控件属性*" style="color:red" /></div>
                     <textbox id="baseInfo_icon" style="width:600px"   />
                     <label value="系统显示使用，多个标签用,分隔" />
               </row>
               <row spans="1,3,2" style="height:50px">
                    <div align="right"><label value="菜单类型" /></div>
                    <listbox id="baseInfo_type" mold="select" style="width:600px" >
                        <listitem value="0" label="菜单" selected="true" />
                        <listitem value="2" label="页面" />
                        <listitem value="1" label="控件" />
                    </listbox>
                    <label value="需要区分按钮还是菜单，权限配置使用" />
               </row>
               <row spans="1,3,2" style="height:50px">
                    <div align="right"><label value="菜单iconClass" /></div>
                    <textbox id="baseInfo_iconClass" style="width:600px"/>
                    <label value="菜单的样式表" />
               </row>

               <row spans="1,3,2" style="height:50px">
                    <div align="right"><label value="页面类型*" /></div>
                    <listbox id="baseInfo_pageType" mold="select" style="width:600px" >
                        <listitem value="4" label="列表" selected="true" />
                        <listitem value="1" label="新增" />
                        <listitem value="2" label="修改" />
                        <listitem value="3" label="查看" />
                    </listbox>
                   <label value="" />
               </row>
               <row spans="1,3,2" style="height:50px">
                    <div align="right"><label value="关联功能"  /></div>
                    <bandbox width="400px" id="baseInfo_pageId" readonly="true" autodrop="false" use="com.ourway.base.zk.bandbox.PageBandbox" />
                    <label value="从页面中选择对应的功能" />
               </row>
               <row spans="1,3,2" style="height:50px">
                    <div align="right"><label value="权限相关" /></div>
                    <hbox>
                        <checkbox id="baseInfo_privsAllocate" value="1" label="不分配" />
                        <checkbox id="baseInfo_privsDefault" value="1" label="默认权限"/>
                    </hbox>
                   <label value="权限配置的时候使用" />

               </row>

            </rows>
         </grid>
      </center>
    </borderlayout>
</window>