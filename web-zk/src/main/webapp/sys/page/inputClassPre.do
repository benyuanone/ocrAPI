<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 导入指定包的列 -->
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.PackageClassSelect"
	style="width:800px;height:200px;"
	>
    <custom-attributes caname="/sys/page/inputClassPre.do" />
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
                  <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="输入完整的包名：" />
                  </div>
                  <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                       <textbox  id="className" class="form-control input-sm"   />
                  </div>
               </div>
               <div class="form-group">
                  <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="多语言前缀：" />
                  </div>
                  <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                     <vbox>
                       <textbox  id="preClassName" class="form-control input-sm"   />
                       <label value="前缀.类名.属性" style="color:red" />
                     </vbox>
                  </div>
               </div>
            </div>
        </div>
    </div>

</window>