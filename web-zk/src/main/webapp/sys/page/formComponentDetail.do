<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="控件选择列表"
    width="800px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.FormComponentDetailAction">
    <custom-attributes caname="/sys/page/formComponentDetail.do" />
<div class="container-fluid" style="padding-bottom:15px;" >
  <div class="row">
      <div class="col-sm-12" style="text-align:left;padding-left: 15px;">
           <div class="btn-group">
           	 <button mold="bs" id="saveBtn" class="btn-success">保存</button>
           	 <button mold="bs" id="delBtn" class="btn-danger">删除</button>
           </div>
      </div>
  </div>
</div>
    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 0px;" >
         <div class="row form-horizontal form-group" >
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                         <label value="行号*:" style="color:red"  />
                     </div>
                     <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                         <intbox  id="componentDetail_compStartRow" class="form-control input-sm"   />
                     </div>
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                         <label value="顺序*:" style="color:red" tooltiptext="表头头部显示列的顺序号" />
                     </div>
                     <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                         <doublebox  id="componentDetail_compOrder" class="form-control input-sm"  readonly="true" />
                     </div>
         </div>
         <div class="row form-horizontal form-group" >
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                         <label value="标签列数*:" style="color:red" tooltiptext="" />
                     </div>
                     <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                         <intbox  id="componentSelGrid_compLable"  class="form-control input-sm"  constraint="no empty:必填" />
                     </div>
                      <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                         <label value="控件列数*:" style="color:red" tooltiptext="" />
                     </div>
                     <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                         <intbox  id="componentSelGrid_compCols"  class="form-control input-sm"  constraint="no empty:必填" />
                     </div>
         </div>
         <div class="row form-horizontal form-group" >
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                         <label value="分组号:"    />
                     </div>
                     <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                          <intbox id="componentSelGrid_compHbox" class="form-control input-sm" />
                     </div>
                     <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                         <label value="控件样式:"  tooltiptext="整个表头的样式，用css定义，这里输入css的名称"/>
                     </div>
                     <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                         <textbox  id="componentSelGrid_compCss"  class="form-control input-sm"  />
                     </div>
         </div>
         <div class="row form-horizontal form-group">
              <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                   <label value="标签样式:"  />
              </div>
              <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                   <textbox id="componentSelGrid_compColor" class="form-control input-sm" />
              </div>
              <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                   <label value="是否需要初始化:"  />
              </div>
              <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                   <listbox mold="select"  id="componentSelGrid_compInit" class="form-control input-sm" >
                        <listitem value="0" label="不初始化"  selected="true"/>
                        <listitem value="1" label="需要初始化"   />
                   </listbox>
              </div>
         </div>
         <div class="row form-horizontal form-group">
              <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                   <label value="检验规则:"  />
              </div>
              <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                   <textbox id="componentSelGrid_kjConstraint" class="form-control input-sm" />
              </div>
              <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                   <label value="校验提示标签:"  />
              </div>
              <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                   <textbox id="componentSelGrid_kjConstraintKey" class="form-control input-sm" />
              </div>
         </div>
         <div class="row form-horizontal form-group">
              <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                   <label value="多文本框:" tooltiptext="多文本框的行数" />
              </div>
              <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                   <intbox  id="componentSelGrid_compRowsnum"  class="form-control input-sm"    />
              </div>
              <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                   <label value="可写:" tooltiptext="" />
              </div>
              <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                   <listbox mold="select"  id="componentSelGrid_compEditable" class="form-control input-sm" >
                        <listitem value="0" label="可编辑"  selected="true"/>
                        <listitem value="1" label="不可编辑"   />
                   </listbox>
              </div>
         </div>
         <div class="row form-horizontal form-group">
              <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                   <label value="控件编号:" tooltiptext="" />
              </div>
              <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                   <textbox  id="componentSelGrid_compId"  class="form-control input-sm"  readonly="true"   />
              </div>
              <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                   <label value="事件类型:" tooltiptext="触发事件" />
              </div>
              <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                   <listbox mold="select"  id="componentSelGrid_eventType" class="form-control input-sm" >
                        <listitem value="0" label="无效"  selected="true"/>
                        <listitem value="1" label="onClick"   />
                        <listitem value="2" label="onDoubleClick"   />
                        <listitem value="3" label="onOK"   />
                        <listitem value="4" label="onChange"   />
                        <listitem value="5" label="onBlur"   />
                        <listitem value="6" label="onSelect"   />
                        <listitem value="7" label="onFocus"   />
                   </listbox>
              </div>
         </div>
         <div class="row form-horizontal form-group" >
             <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                  <label value="执行类:" tooltiptext="事件触发的内容"  />
             </div>
             <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                 <combobox  id="componentSelGrid_evnetFormula" style="width:99%;"   class="input-sm" ></combobox>
             </div>
         </div>
         <div class="row form-horizontal form-group" >
             <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                 <label value="参数:" tooltiptext="当选择自定义类的时候，输入自定义类所需要的参数数据"  />
             </div>
             <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                 <textbox  id="componentSelGrid_eventDataContent" rows="3" class="form-control input-sm" />
             </div>
         </div>
         <div class="row form-horizontal form-group" >
             <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                <label value="按钮有效控制" />
             </div>
             <div class="col-sm-9" style="text-align:left;padding-left:2px;">
                <textbox  id="componentSelGrid_columnDisable" style="width:99%;"   class="input-sm" ></textbox>
             </div>
         </div>
         <div class="row form-horizontal form-group" >
             <div class="col-sm-2 control-label" style="text-align:right;padding-right:2px;height:30px;">
                    <label value="长度:"  />
             </div>
             <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                      <intbox id="componentSelGrid_compLength" class="form-control input-sm" />
             </div>
         </div>
    </div>

</window>