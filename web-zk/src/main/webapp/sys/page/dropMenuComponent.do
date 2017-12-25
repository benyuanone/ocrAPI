<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="下拉菜单配置"
    width="800px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.TreeComponentAction">
    <custom-attributes caname="/sys/page/dropMenuComponent.do" />

    <div  id="operateGrid" class="container-fluid buttonDiv" style="padding-top:-2px;padding-bottom:0px;" >

    </div>
    <!-- 主表区 -->
    <div id="mainTableGrid" class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
      <panel title="菜单1" sclass="panel-primary">
        <panelchildren>
         <div class="row form-horizontal form-group" >
              <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                  <label value="名称:"    />
              </div>
              <div class="col-sm-1" style="text-align:left;padding-left:2px;">
                  <textbox  id="componentDetail_chnName1" class="form-control input-sm"   />
              </div>
              <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                   <label value="标签:"   tooltiptext="" />
              </div>
              <div class="col-sm-1" style="text-align:left;padding-left:2px;">
                    <textbox  id="componentDetail_lanLabelid1" class="form-control input-sm"   />
              </div>
              <div class="col-sm-1 control-label" style="text-align:right;padding-right:2px;height:30px;">
                    <label value="点击事件:"   tooltiptext="" />
              </div>
              <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                    <combobox  id="componentDetail_evnetClass1" style="width:99%;"   class="input-sm" ></combobox>
              </div>
         </div>
        </panelchildren>
      </panel>
    </div>

    <panel title="加载事件" sclass="panel-primary">
     	<panelchildren>
           <grid>
              <rows>
                 <row spans="1,5">
                    <div align="right">
                        <label value="加载事件" tooltiptext=""  />
                    </div>
                    <combobox  id="event_eventClass1" style="width:600px;" ></combobox>
                 </row>
                 <row spans="1,5">
                    <div align="right">
                        <label value="事件参数" tooltiptext=""  />
                    </div>
                    <textbox id="atributeGrid_eventParam1" rows="4" style="width:600px;"/>
                 </row>
              </rows>
           </grid>
     	</panelchildren>
     </panel>
     <panel title="单击事件" sclass="panel-primary">
        <panelchildren>
                <grid>
                   <rows>
                      <row spans="1,5">
                         <div align="right">
                             <label value="动态加载" tooltiptext=""  />
                         </div>
                         <combobox  id="event_eventClass2" style="width:600px;" ></combobox>
                      </row>
                      <row spans="1,5">
                         <div align="right">
                             <label value="事件参数" tooltiptext=""  />
                         </div>
                         <textbox id="atributeGrid_eventParam2" rows="4" style="width:600px;"/>
                      </row>
                   </rows>
                </grid>
        </panelchildren>
     </panel>
     <panel title="双击事件" sclass="panel-primary">
        <panelchildren>
                <grid>
                   <rows>
                      <row spans="1,5">
                         <div align="right">
                             <label value="双击事件" tooltiptext=""  />
                         </div>
                         <combobox  id="event_eventClass3" style="width:600px;" ></combobox>
                      </row>
                      <row spans="1,5">
                         <div align="right">
                             <label value="事件参数" tooltiptext=""  />
                         </div>
                         <textbox id="atributeGrid_eventParam3" rows="4" style="width:600px;"/>
                      </row>
                   </rows>
                </grid>
        </panelchildren>
     </panel>
     <panel title="移动事件" sclass="panel-primary">
        <panelchildren>
                <grid>
                   <rows>
                      <row spans="1,5">
                         <div align="right">
                             <label value="移动事件" tooltiptext=""  />
                         </div>
                         <combobox  id="event_eventClass4" style="width:600px;" ></combobox>
                      </row>
                      <row spans="1,5">
                         <div align="right">
                             <label value="事件参数" tooltiptext=""  />
                         </div>
                         <textbox id="atributeGrid_eventParam4" rows="4" style="width:600px;"/>
                      </row>
                   </rows>
                </grid>
        </panelchildren>
     </panel>
     <grid  >
        <rows>
          <row spans="6">
             <div align="center">
                <button id="saveBtn" label="保 存" />
                <button id="clsBtn" label="取 消" />
             </div>
          </row>
        </rows>
     </grid>
</window>