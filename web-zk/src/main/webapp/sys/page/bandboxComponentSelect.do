<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 表格操作 -->
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.BandboxComponentGridSelAction">
    <custom-attributes caname="/sys/page/bandboxComponentSelect.do" />

    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
        <div class="row form-horizontal form-group" >
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="宽度" />
                  </div>
                  <div class="col-sm-2" style="text-align:left;padding-left:2px;">
                        <intbox id="button_controlWidth"   class="form-control input-sm" style="width:200px;" />
                  </div>
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="高度" />
                  </div>
                  <div class="col-sm-2" style="text-align:left;padding-left:2px;">
                       <intbox id="button_controlHeight"   class="form-control input-sm" style="width:200px;" />
                  </div>
                  <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                       <label value="显示属性" />
                  </div>
                  <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                       <textbox  id="componentSelGrid_controlClass"   class="form-control input-sm" style="width:200px;" ></textbox>
                  </div>
        </div>
        <div class="row form-horizontal form-group" >
               <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                    <label value="查询值" />
               </div>
               <div class="col-sm-2" style="text-align:left;padding-left:2px;">
                     <textbox  id="componentSelGrid_controlInt"   class="form-control input-sm" style="width:200px;" ></textbox>
               </div>
               <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                      <label value="查询属性" />
               </div>
               <div class="col-sm-2" style="text-align:left;padding-left:2px;">
                     <textbox  id="componentSelGrid_controlCss"   class="form-control input-sm" style="width:200px;" ></textbox>
               </div>
               <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                      <label value="搜索类型" />
               </div>
               <div class="col-sm-2" style="text-align:left;padding-left:2px;">
                     <listbox id="componentSelGrid_controlDetailInt" mold="select" style="width:200px;">
                           <listitem value="0" label="等值条件" selected="true" />
                           <listitem value="1" label="like 条件"  />
                           <listitem value="2" label="between条件"  />
                           <listitem value="3" label="in 多个值"  />
                           <listitem value="4" label="大于等于和小于等于"  />
                     </listbox>
               </div>
        </div>
        <div class="row form-horizontal form-group" >
               <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                    <label value="默认接口" />
               </div>
               <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                     <textbox  id="componentSelGrid_controlBandboxInt"   class="form-control input-sm"  ></textbox>
               </div>

        </div>

    </div>

</window>