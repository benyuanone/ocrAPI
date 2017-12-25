<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="行详情"
    width="500px"
    height="240px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.TabButtonDetailAction">
    <custom-attributes caname="/sys/page/tabPanelDetail.do" />
    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
            <div class="row form-horizontal" >
                <div class="col-sm-12">
                   <div class="btn-group">
                      <button id="saveBtn" mold="bs" sclass="btn-primary" label="确 定" />
                      <button id="removeBtn" mold="bs"  sclass="btn-primary" label="删 除" />
                      <button id="clsBtn" mold="bs"  sclass="btn-primary" label="关 闭" />
                   </div>
                </div>
            </div>
        </div>
        <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
            <div class="row form-horizontal" >
                       <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                            <label value="组号：" />
                       </div>
                      <div class="col-sm-3 control-label" style="text-align:right;padding-right:2px;height:30px;">
                           <intbox  id="compHbox" class="form-control input-sm"   />
                      </div>
                      <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                           <label value="所在行：" />
                      </div>
                      <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                           <textbox  id="compColor" class="form-control input-sm"   />
                      </div>
            </div>
            <div class="row form-horizontal" >
                      <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                            <label value="样式：" />
                       </div>
                      <div class="col-sm-8 control-label" style="text-align:right;padding-right:2px;height:30px;">
                           <textbox  id="compCss" class="form-control input-sm"   />
                      </div>

            </div>
        </div>
</window>