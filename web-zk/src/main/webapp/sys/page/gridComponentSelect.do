<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 表格操作 -->
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.ComponentGridSelAction">
    <custom-attributes caname="/sys/page/gridComponentSelect.do" />

    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="是否隐藏" />
                  </div>
                  <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                       <checkbox id="button_controlIsshow" value="1"  class="input-sm" label="无效隐藏"/>
                  </div>

                 <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                     <label value="添加表头" />
                 </div>
                 <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                    <hbox>
                        <bandbox id="button_controlList" use="com.ourway.base.zk.template.utils.ComponentsBandbox" class=" input-sm"/>
                     </hbox>
                 </div>
                 <div class="col-sm-2" style="text-align:left;padding-left:2px;">
                      <button mold="bs" class="btn-success" id="button_addBtn">添加表头</button>
                      <button mold="bs" class="btn-success" id="button_removeBtn">删除</button>
                 </div>
        </div>
        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="所在行" />
                  </div>
                  <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                       <textbox id="button_controlParentId"  class="form-control input-sm" style="width:180px;" />
                  </div>
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="宽度" />
                  </div>
                  <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                       <intbox id="button_controlWidth"   class="form-control input-sm" style="width:180px;" />
                  </div>
                 <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                     <label value="高度/距离" />
                 </div>
                 <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                    <intbox id="button_controlHeight"   class="form-control input-sm" style="width:180px;"/>
                    <label value="内部子表表示高度,查询列表表示与顶部的距离" style="color:red" />
                 </div>
        </div>
        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="多选框" />
                  </div>
                  <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                       <listbox mold="select"  id="componentSelGrid_controlCheck" class="form-control input-sm" style="width:180px;" >
                             <listitem value="0" label="不需要"  selected="true"/>
                             <listitem value="1" label="多选框"   />
                             <listitem value="2" label="单选框"   />
                        </listbox>
                  </div>
                 <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                     <label value="宽度自适应" />
                 </div>
                 <div class="col-sm-4" style="text-align:left;padding-left:2px;">
                       <listbox mold="select"  id="componentSelGrid_controlSizebycontent" class="form-control input-sm" style="width:180px;" >
                             <listitem value="0" label="根据定义宽度"  selected="true"/>
                             <listitem value="1" label="自适应"   />
                        </listbox>
                 </div>
        </div>

        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="数据初始化接口" />
                  </div>
                  <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                       <textbox  id="componentSelGrid_controlInt"   class="form-control input-sm" ></textbox>
                  </div>
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="查询条件" />
                  </div>
                  <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                       <textbox  id="componentSelGrid_controlIntGrid"   class="form-control input-sm" ></textbox>
                       <label value="查询区布局控件的id，多个用逗号分隔" />
                  </div>
        </div>
        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="默认加载" />
                  </div>
                  <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                       <listbox mold="select"  id="componentSelGrid_controlLoad" class="form-control input-sm" style="width:180px;">
                             <listitem value="0" label="不加载"  selected="true"/>
                             <listitem value="1" label="加载数据"   />
                        </listbox>
                  </div>
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="是否分页" />
                  </div>
                  <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                       <listbox mold="select"  id="componentSelGrid_controlSplitpage" class="form-control input-sm" style="width:180px;">
                             <listitem value="0" label="不分页"  selected="true"/>
                             <listitem value="1" label="分页"   />
                        </listbox>
                  </div>
        </div>
        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="每页条数" />
                  </div>
                  <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                       <intbox id="button_controlPagesize"   class="form-control input-sm" style="width:180px;"/>
                  </div>
                 <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="提交方式" />
                 </div>
                 <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                    <listbox mold="select"  id="componentSelGrid_controlWay" class="form-control input-sm" style="width:180px;">
                       <listitem value="0" label="增量数据"  selected="true"/>
                       <listitem value="1" label="全部数据"   />
                    </listbox>
                 </div>

        </div>
         <div class="row form-horizontal form-group" >
                          <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                               <label value="Master处理接口" />
                          </div>
                          <div class="col-sm-11" style="text-align:left;padding-left:2px;">
                               <combobox  id="componentSelGrid_controlDetailInt" style="width:99%;"   class="input-sm" ></combobox>
                          </div>
                </div>
        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="双击事件接口" />
                  </div>
                  <div class="col-sm-11" style="text-align:left;padding-left:2px;">
                       <combobox  id="componentSelGrid_controlDbclickEvent" style="width:99%;"   class="input-sm" ></combobox>
                  </div>
        </div>

        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="双击接口参数" />
                  </div>
                  <div class="col-sm-11" style="text-align:left;padding-left:2px;">
                       <textbox  id="componentSelGrid_controlDbclickEventParam" rows="3" class="form-control input-sm" />
                  </div>
        </div>
        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="行样式CSS" />
                  </div>
                  <div class="col-sm-11" style="text-align:left;padding-left:2px;">
                       <textbox  id="componentSelGrid_controlRowCss" rows="3" class="form-control input-sm" />
                       <label value='[{"formula":"status=2","css","warning"}] 若字符串，两边都用$进行包围' style="color:red" />
                  </div>
        </div>


    </div>
    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
        <div class="row form-horizontal" >
           <div class="col-sm-12" id="button_result">
               <panel title="表头" sclass="panel-primary"  >
                    <panelchildren id="button_group1">

                    </panelchildren>
                </panel>
           </div>
        </div>
    </div>
</window>