<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.PageComponentAction">
    <custom-attributes caname="/template/pageComponent.do" />
    <tabbox id="tabbox" >
         <tabs id="tabs">
            <tab label="页面基本信息" />

         </tabs>
         <tabpanels>
            <tabpanel>
              <vbox>
                <grid >
                    <rows>
                       <row spans="1,2,1,2">
                           <div align="right">
                               <label value="页面编号*" style="color:red" />
                           </div>
                           <textbox id="pageNo" style="width:200px;" constraint="no empty:必填" />
                           <div align="right">
                              <label value="页面CA*" style="color:red" />
                           </div>
                           <textbox id="pageCa" style="width:200px;" constraint="no empty:必填" />
                       </row>
                       <row spans="1,5">
                           <div align="right">
                               <label value="页面名称*" style="color:red" />
                           </div>
                           <textbox id="pageName" style="width:500px;" constraint="no empty:必填" />
                       </row>
                       <row spans="1,2,1,2">
                           <div align="right">
                               <label value="所用模板"  />
                           </div>
                           <listbox mold="select" id="pageTemplate" style="width:200px;" />
                           <div align="right">
                               <label value="页面状态"  />
                           </div>
                           <listbox mold="select" id="pageStatus" style="width:200px;" >
                               <listitem value="1" label="已发布" selected="true" />
                               <listitem value="0" label="未发布"  />
                               <listitem value="2" label="已失效"  />
                           </listbox>
                       </row>
                       <row spans="1,2,1,2">
                           <div align="right">
                               <label value="是否自定义"  />
                           </div>
                           <listbox mold="select" id="pageCustomer" style="width:200px;" >
                               <listitem value="1" label="自定义"  />
                               <listitem value="0" label="动态"  selected="true"/>
                           </listbox>
                           <label value="" />
                           <label value="" />
                       </row>

                       <row spans="1,5">
                           <div align="right">
                               <label value="备注"  />
                           </div>
                           <textbox id="memo" style="width:500px;"  />
                       </row>
                    </rows>
                  </grid>
                  <grid>
                     <rows>
                        <row>
                           <hbox>
                              <button label="添加" />
                              <button label="删除" />
                              <button label="复制" />
                           </hbox>
                        </row>
                     </rows>
                  </grid>
                  <grid style="height:400px;" >
                     <columns>
                         <column label="顺序号" />
                         <column label="中文名" />
                         <column label="多语言显示标签名" />
                         <column label="对应属性名" />
                         <column label="对应显示属性名" />
                         <column label="控件类型" />
                         <column label="控件实现类" />
                         <column label="数据类型" />
                         <column label="是否报表参数" />
                         <column label="是否报表字段" />
                         <column label="数据来源" />
                         <column label="初始化传入数据" />
                         <column label="接口名称" />
                         <column label="默认数据" />
                         <column label="查询条件类型" />
                         <column label="是否隐藏" />
                     </columns>
                     <rows id="componentList" />
                  </grid>
                </vbox>
            </tabpanel>

         </tabpanels>
    </tabbox>
</window>