<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 表格操作 -->
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.ComponentTreeBandboxSelAction">
    <custom-attributes caname="/sys/page/treeBandboxComponentSelect.do" />

    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;padding-bottom:30px;" >
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
               <label value="树名称" />
           </div>
           <div class="col-sm-5" style="text-align:left;padding-left:2px;">
               <textbox id="button_controlLabel"   class="form-control input-sm" style="width:180px;" />
           </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
               <label value="详情接口" />
           </div>
           <div class="col-sm-5" style="text-align:left;padding-left:2px;">
               <textbox id="button_controlDetailInt"   class="form-control input-sm" style="width:180px;" />
           </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="加载事件" />
           </div>
           <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula1" style="width:99%;"   class="input-sm" ></combobox>
           </div>
            <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent1" rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="动态加载加载" />
           </div>
           <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula2" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent2"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
    </div>
</window>