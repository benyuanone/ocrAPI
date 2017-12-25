<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 表格操作 -->
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.ComponentTreeSelAction">
    <custom-attributes caname="/sys/page/treeComponentSelect.do" />

    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;padding-bottom:30px;" >
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
               <label value="树名称" />
           </div>
           <div class="col-sm-2" style="text-align:left;padding-left:2px;">
               <textbox id="button_controlLabel"   class="form-control input-sm" style="width:180px;" />
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                <label value="查询属性" />
           </div>
           <div class="col-sm-1" style="text-align:left;padding-left:2px;">
                <textbox id="button_controlParentId"   class="form-control input-sm" style="width:180px;" />
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                <label value="取值属性" />
           </div>
           <div class="col-sm-1" style="text-align:left;padding-left:2px;">
                <textbox id="button_controlPath"   class="form-control input-sm" style="width:180px;" />
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                <label value="搜索类型" />
           </div>
           <div class="col-sm-1" style="text-align:left;padding-left:2px;">
                <listbox id="button_controlClass" mold="select" style="width:180px;">
                     <listitem value="0" label="等值条件" selected="true" />
                     <listitem value="1" label="like 条件"  />
                     <listitem value="2" label="between条件"  />
                     <listitem value="3" label="in 多个值"  />
                     <listitem value="4" label="大于等于和小于等于"  />
                </listbox>

           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
               <label value="树样式" />
           </div>
           <div class="col-sm-1" style="text-align:left;padding-left:2px;">
               <textbox id="button_controlSclass"   class="form-control input-sm" style="width:180px;" />
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

        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="单击事件" />
           </div>
           <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula3" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent3"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="新增子节点" />
           </div>
           <div class="col-sm-2" style="text-align:left;padding-left:2px;">
              <textbox  id="loadEvent_kjConstraintKey4" style="width:99%;"   class="input-sm" ></textbox>
           </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula4" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent4"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="新增同级节点" />
           </div>
           <div class="col-sm-2" style="text-align:left;padding-left:2px;">
              <textbox  id="loadEvent_kjConstraintKey7" style="width:99%;"   class="input-sm" ></textbox>
           </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula7" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent7"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="修改节点" />
           </div>
           <div class="col-sm-2" style="text-align:left;padding-left:2px;">
              <textbox  id="loadEvent_kjConstraintKey8" style="width:99%;"   class="input-sm" ></textbox>
           </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula8" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent8"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="删除" />
           </div>
            <div class="col-sm-2" style="text-align:left;padding-left:2px;">
               <textbox  id="loadEvent_kjConstraintKey5" style="width:99%;"   class="input-sm" ></textbox>
            </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula5" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent5"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="移动" />
           </div>
           <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula6" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent6"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="其它右键1" />
           </div>
            <div class="col-sm-2" style="text-align:left;padding-left:2px;">
               <textbox  id="loadEvent_kjConstraintKey9" style="width:99%;"   class="input-sm" ></textbox>
            </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula9" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent9"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="其它右键2" />
           </div>
            <div class="col-sm-2" style="text-align:left;padding-left:2px;">
               <textbox  id="loadEvent_kjConstraintKey10" style="width:99%;"   class="input-sm" ></textbox>
            </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula10" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent10"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="其它右键3" />
           </div>
            <div class="col-sm-2" style="text-align:left;padding-left:2px;">
               <textbox  id="loadEvent_kjConstraintKey11" style="width:99%;"   class="input-sm" ></textbox>
            </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula11" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent11"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="其它右键4" />
           </div>
            <div class="col-sm-2" style="text-align:left;padding-left:2px;">
               <textbox  id="loadEvent_kjConstraintKey12" style="width:99%;"   class="input-sm" ></textbox>
            </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula12" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent12"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="其它右键5" />
           </div>
            <div class="col-sm-2" style="text-align:left;padding-left:2px;">
               <textbox  id="loadEvent_kjConstraintKey13" style="width:99%;"   class="input-sm" ></textbox>
            </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula13" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent13"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
         <div class="row form-horizontal form-group" >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="其它右键6" />
           </div>
            <div class="col-sm-2" style="text-align:left;padding-left:2px;">
               <textbox  id="loadEvent_kjConstraintKey14" style="width:99%;"   class="input-sm" ></textbox>
            </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula14" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent14"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
        <div class="row form-horizontal form-group"  >
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
              <label value="其它右键7" />
           </div>
            <div class="col-sm-2" style="text-align:left;padding-left:2px;">
               <textbox  id="loadEvent_kjConstraintKey15" style="width:99%;"   class="input-sm" ></textbox>
            </div>
           <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <combobox  id="loadEvent_evnetFormula15" style="width:99%;"   class="input-sm" ></combobox>
           </div>
           <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="事件参数" />
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                 <textbox  id="loadEvent_eventDataContent15"  rows="3"   class="form-control input-sm" ></textbox>
            </div>
        </div>
    </div>
</window>