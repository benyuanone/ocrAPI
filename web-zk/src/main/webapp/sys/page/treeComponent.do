<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="树管理"
    width="800px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.TreeComponentAction">
    <custom-attributes caname="/sys/page/treeComponent.do" />
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