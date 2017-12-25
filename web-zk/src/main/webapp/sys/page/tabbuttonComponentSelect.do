<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 按钮区操作 -->
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.TabComponentButtonSelAction">
    <custom-attributes caname="/sys/page/tabbuttonComponentSelect.do" />

    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
        <div class="row form-horizontal" >
            <div class="col-sm-4">
               <div class="form-group">
                  <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="是否隐藏" />
                  </div>
                  <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                       <checkbox id="button_controlIsshow" value="1"  class="input-sm" label="无效隐藏"/>
                  </div>
               </div>
            </div>
            <div class="col-sm-4">
               <div class="form-group">
                 <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;height:30px;">
                     <label value="添加控件" />
                 </div>
                 <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                     <spinner id="button_rowNum" style="width:80px" class=" input-sm" />
                     <bandbox id="button_controlList" use="com.ourway.base.zk.template.utils.ComponentsBandbox" class=" input-sm"/>
                 </div>
               </div>
            </div>
            <div class="col-sm-4">
                 <div class="col-sm-12" style="text-align:left;padding-left:2px;">
                      <button mold="bs" class="btn-success" id="button_addBtn">添加控件</button>
                 </div>
            </div>
        </div>
    </div>
    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
        <div class="row form-horizontal" >
           <div class="col-sm-12" id="button_result">


           </div>
        </div>
    </div>
</window>