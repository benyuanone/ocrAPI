<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.PageComponentAction">
    <custom-attributes caname="/sys/page/pageComponent.do" />
    <grid>
       <rows>
         <row>
           <div align="left">
              <hbox>
                <div class="btn-group">
                   <button id="saveBtn" mold="bs" sclass="btn-primary" label="保 存" />
                   <button id="clsBtn" mold="bs"  sclass="btn-primary" label="取 消" />
                   <button id="saveAsBtn" mold="bs"  sclass="btn-primary" label="另 存" />
                   <button id="exportMbBtn" mold="bs"  sclass="btn-primary" label="报表模板" />
                </div>
             </hbox>
           </div>
         </row>
      </rows>
    </grid>
    <tabbox id="tabbox" >
         <tabs id="tabs">
            <tab id="baseInfo" label="页面基本信息" />
         </tabs>
         <tabpanels id="tabpanels">
            <tabpanel id="baseInfo_panel">
                <grid >
                    <rows>
                       <row spans="1,2,1,2">
                           <div align="right">
                               <label value="页面编号*" style="color:red" />
                           </div>
                           <textbox id="baseInfo_pageNo" style="width:200px;" constraint="no empty:必填" />
                           <div align="right">
                              <label value="页面CA*" style="color:red" />
                           </div>
                           <textbox id="baseInfo_pageCa" style="width:200px;" constraint="no empty:必填" />
                       </row>
                       <row spans="1,5">
                           <div align="right">
                               <label value="页面名称*" style="color:red" />
                           </div>
                           <textbox id="baseInfo_pageName" style="width:500px;" constraint="no empty:必填" />
                       </row>
                       <row spans="1,2,1,2">
                           <div align="right">
                               <label value="所用模板*" style="color:red"  />
                           </div>
                           <listbox mold="select"  id="baseInfo_pageTemplate" style="width:200px;" />
                           <div align="right">
                               <label value="页面状态"  />
                           </div>
                           <listbox mold="select" id="baseInfo_pageStatus" style="width:200px;" >
                               <listitem value="1" label="已发布" selected="true" />
                               <listitem value="0" label="未发布"  />
                               <listitem value="2" label="已失效"  />
                           </listbox>
                       </row>
                       <row spans="1,5">
                           <div align="right">
                               <label value="是否自定义"  />
                           </div>
                           <listbox mold="select" id="baseInfo_pageCustomer" style="width:200px;" >
                               <listitem value="1" label="自定义"  />
                               <listitem value="0" label="动态"  selected="true"/>
                           </listbox>
                       </row>
                        <row spans="1,5">
                           <div align="right">
                               <label value="方法/实现类名"  />
                           </div>
                           <combobox  id="baseInfo_pageInit" style="width:600px;" >
                               <comboitem label="com.ourway.base.zk.service.impl.APIPageInitSer" description="页面API初始化"  />
                           </combobox>
                       </row>
                        <row spans="1,5">
                           <div align="right">
                               <label value="接口参数"  />
                           </div>
                           <textbox id="baseInfo_pageParams" rows="3" style="width:600px;"  />
                       </row>
                       <row spans="1,5">
                           <div align="right">
                                <label value="初始化后执行"  />
                           </div>
                           <combobox  id="baseInfo_pageInitAfter" style="width:600px;" >
                                <comboitem label="com.ourway.base.zk.service.impl.AfterPageInitAction" description="页面初始化后执行"  />
                           </combobox>
                       </row>
                       <row spans="1,5">
                           <div align="right">
                               <label value="备注"  />
                           </div>
                           <textbox id="baseInfo_memo" style="width:600px;"  />
                       </row>
                    </rows>
                  </grid>
                  <grid>
                     <rows>
                        <row>
                           <hbox>
                              <div class="btn-group">
                                  <button label="添加" id="addBtn" mold="bs" sclass="btn-primary" />
                                  <button label="导入按钮" id="importBtn" mold="bs" sclass="btn-primary" />
                                  <button label="导入类属性" id="classBtn" mold="bs" sclass="btn-primary" />
                                  <button label="删除" id="removeBtn" mold="bs" sclass="btn-danger"/>
                              </div>
                           </hbox>
                        </row>
                     </rows>
                  </grid>
                  <grid style="height:400px;" id="componentGrid" >
                  </grid>
            </tabpanel>

         </tabpanels>
    </tabbox>

</window>