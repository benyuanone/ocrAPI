<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 按钮区操作 -->
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.ComponentClassSelect"
	style="width:800px;height:200px;"
	>
    <custom-attributes caname="/sys/page/buttonComponentSelect.do" />
    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
        <div class="row form-horizontal" >
            <div class="col-sm-12">
               <div class="btn-group">
                  <button id="saveBtn" mold="bs" sclass="btn-primary" label="确 定" />
                  <button id="clsBtn" mold="bs"  sclass="btn-primary" label="关 闭" />
               </div>
            </div>
        </div>
    </div>
    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
        <div class="row form-horizontal" >
            <div class="col-sm-12">
               <div class="form-group">
                   <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                        <label value="类前缀：" />
                   </div>
                  <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <textbox  id="classPreName" class="form-control input-sm"   />
                  </div>
                  <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="导入类名：" />
                  </div>
                  <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                       <textbox  id="className" class="form-control input-sm"   />
                  </div>
               </div>
            </div>
        </div>
    </div>

</window>