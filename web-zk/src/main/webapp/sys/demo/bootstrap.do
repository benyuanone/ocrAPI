<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml"
	xmlns:n="http://www.w3.org/1999/xhtml"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>
<custom-attributes caname="/sys/demo/input.do" />
<div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
  <div class="row form-horizontal" >
      <div class="col-sm-4">
        <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;height:30px;">
                <label value="User name:" />
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <textbox   class="form-control input-sm"/>
            </div>
        </div>
      </div>
      <div class="col-sm-4">
         <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;">
               <label  value="Password"></label>
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <textbox   type="password" class="form-control input-sm" />
            </div>
         </div>
      </div>
      <div class="col-sm-4">
        <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;">
                 <label   value="Administrator"></label>
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <div sclass="input-group">
                    <checkbox sclass="input-group-addon input-sm"/>
                    <textbox   sclass="form-control input-sm" />
                </div>
            </div>
         </div>
      </div>
   </div>


 <div class="row form-horizontal" >
      <div class="col-sm-4">
        <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;height:30px;">
                <label value="User name:" />
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <textbox   class="form-control input-sm"/>
            </div>
        </div>
      </div>
      <div class="col-sm-4">
         <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;">
               <label  value="Password"></label>
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <textbox  type="password" class="form-control input-sm" />
            </div>
         </div>
      </div>
      <div class="col-sm-4">
        <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;">
                 <label   value="Administrator"></label>
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <div sclass="input-group">
                    <checkbox sclass="input-group-addon input-sm"/>
                    <textbox   sclass="form-control input-sm" />
                </div>
            </div>
         </div>
      </div>
   </div>
  </div>


<div class="container-fluid" style="padding-bottom:15px;" >
  <div class="row">
      <div class="col-sm-10">
           <div class="btn-group">
           	 <button mold="bs" class="btn-success" iconSclass="z-icon z-icon-save">保存</button>
           	 <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-edit">修改</button>
           	 <button mold="bs" class="btn-danger" iconSclass="z-icon z-icon-delete">删除</button>
           	 <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-refresh">刷新</button>
           	 <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-copy">复制</button>
           	 <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-paste">粘贴</button>
           </div>
           <div class="btn-group">
                <button mold="bs" class="btn-success" iconSclass="z-icon z-icon-save">保存</button>
                <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-edit">修改</button>
                <button mold="bs" class="btn-danger" iconSclass="z-icon z-icon-delete">删除</button>
                <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-refresh">刷新</button>
                <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-copy">复制</button>
                <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-paste">粘贴</button>
           </div>
      </div>
      <div class="col-sm-2" style="text-align:right;">
         <div class="btn-group">
            <button mold="bs" class="btn-success" iconSclass="z-icon z-icon-btnsearch">查询</button>
            <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-reset">重置</button>
            <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-btnmore">更多条件</button>
         </div>
      </div>
  </div>
</div>
<!-- 主表区 -->
<div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
  <div class="row form-horizontal" >
      <div class="col-sm-4">
        <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;height:30px;">
                <label value="User name:" />
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <textbox id="txtMail" class="form-control input-sm"/>
            </div>
        </div>
      </div>
      <div class="col-sm-4">
         <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;">
               <label  value="Password"></label>
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <textbox id="txtPassword" type="password" class="form-control input-sm" />
            </div>
         </div>
      </div>
      <div class="col-sm-4">
        <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;">
                 <label   value="Administrator"></label>
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <div sclass="input-group">
                    <checkbox sclass="input-group-addon  checkbox" />
                    <textbox id="chkAdmin" sclass="form-control input-sm" />
                </div>
            </div>
         </div>
      </div>
   </div>


 <div class="row form-horizontal" >
      <div class="col-sm-4">
        <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;height:30px;">
                <label value="User name:" />
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <textbox   class="form-control input-sm"/>
            </div>
        </div>
      </div>
      <div class="col-sm-4">
         <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;">
               <label  value="Password"></label>
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <textbox  type="password" class="form-control input-sm" />
            </div>
         </div>
      </div>
      <div class="col-sm-4">
        <div class="form-group">
            <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;">
                 <label   value="Administrator"></label>
            </div>
            <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <div sclass="input-group">
                    <checkbox sclass="input-group-addon input-sm"/>
                    <textbox   sclass="form-control input-sm" />
                </div>
            </div>
         </div>
      </div>
   </div>
  </div>
  <!-- 主表区结束 -->
  <!-- 子表按钮 -->
<div class="container-fluid" style="padding-top:10px;" >
  <div class="row">
      <div class="col-sm-12">
           <div class="btn-group">
           	 <button mold="bs" class="btn-success" iconSclass="z-icon z-icon-save">保存</button>
           	 <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-edit">修改</button>
           	 <button mold="bs" class="btn-danger" iconSclass="z-icon z-icon-delete">删除</button>
           	 <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-refresh">刷新</button>
           	 <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-copy">复制</button>
           	 <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-paste">粘贴</button>
           </div>
           <div class="btn-group">
                <button mold="bs" class="btn-success" iconSclass="z-icon z-icon-save">保存</button>
                <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-edit">修改</button>
                <button mold="bs" class="btn-danger" iconSclass="z-icon z-icon-delete">删除</button>
                <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-refresh">刷新</button>
                <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-copy">复制</button>
                <button mold="bs" class="btn-info" iconSclass="z-icon z-icon-paste">粘贴</button>
           </div>
          <checkbox sclass="checkbox" />
      </div>
  </div>
</div>
<!-- 子表 -->
<div class="container-fluid" style="padding-top:10px;" >
   <div class="row">
        <div class="col-sm-12">
            <grid sizedByContent="true">
               <columns sizable="true">
                  <column label="From" style="min-width:100px;" tooltiptext="计算机公式"  sort="auto"/>
                  <column label="Subject" style="min-width:100px;" sort="auto"/>
                  <column label="Received" style="min-width:100px;"  sort="auto"/>
                  <column label="Size" style="min-width:100px;" sort="auto" />
                  <column label="Size" style="min-width:100px;" sort="auto" />
                  <column label="Size" style="min-width:100px;" sort="auto" />
                  <column label="Size" style="min-width:100px;" sort="auto" />
                  <column label="Size" style="min-width:100px;" sort="auto" />
                  <column label="Size" style="min-width:100px;"  sort="auto" />
                  <column label="Size" style="min-width:100px;"  sort="auto"/>
                  <column label="Size" style="min-width:100px;" sort="auto" />
                  <column label="Size" style="min-width:100px;"  sort="auto"/>
                  <column label="Size" style="min-width:100px;"  sort="auto"/>
                  <column label="Size" style="min-width:100px;"  sort="auto"/>
                  <column label="Size" style="min-width:100px;"  sort="auto"/>
                  <column label="Size" style="min-width:100px;" sort="auto"/>
                  <column label="Size" style="min-width:100px;" sort="auto"/>
                  <column label="Size" style="min-width:100px;" sort="auto"/>
                  <column label="Size" style="min-width:100px;" sort="auto"/>
                  <column label="Size" style="min-width:100px;" sort="auto"/>

               </columns>
               <rows>
                 <row>
                    <textbox inplace="true" width="200px" value="1" tooltiptext="123123" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" tooltiptext="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                 </row>
                 <row class="success">
                    <textbox inplace="true" width="99%" value="2" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                 </row>
                 <row class="danger">
                    <textbox inplace="true" width="99%" value="3" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                 </row>
                 <row class="warning">
                    <textbox inplace="true" width="99%" value="3" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true" />
                    <textbox inplace="true" width="99%" value="1231231adasdfasdfasdfasdf" readonly="true"  />
                 </row>


               </rows>

            </grid>
        </div>
   </div>
</div>



</window>