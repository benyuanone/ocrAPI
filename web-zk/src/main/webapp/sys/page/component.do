<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="页面控件详情"
    width="800px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.ComponentAction">
    <custom-attributes caname="/sys/page/component.do" />
        <grid style="border:0px;">
           <rows>
              <row>
                <div align="left">
                   <hbox>
                       <button id="saveBtn" label="保存" />
                       <button id="closeBtn" label="关闭" />
                       <button id="copyBtn" label="复制" />
                       <button id="pasteBtn" label="粘贴" />
                    </hbox>
                 </div>
              </row>
           </rows>
        </grid>
                <grid id="atributeGrid" >
                    <rows>
                       <row spans="1,2,1,2">
                           <div align="right">
                              <label value="中文名*" tooltiptext="用户内部标志控件名称，必填字段" style="color:red" />
                           </div>
                           <textbox id="atributeGrid_kjName"  style="width:200px;" constraint="no empty:必填" />
                           <div align="right">
                               <label value="多语言标签*" tooltiptext="多语言标签id，多个标签之间用英文逗号分隔，必填" style="color:red" />
                           </div>
                           <textbox id="atributeGrid_kjLabelid" style="width:200px;" constraint="no empty:必填" />
                       </row>
                       <row spans="1,2,1,2">
                           <div align="right">
                              <label value="属性名*" tooltiptext="对应控件类中的属性名称" style="color:red" />
                           </div>
                           <textbox id="atributeGrid_kjAttribute"  style="width:200px;" constraint="no empty:必填" />
                           <div align="right">
                               <label value="显示属性名" tooltiptext="部分显示需要进行转化显示，输入转化后的属性名"  />
                           </div>
                           <textbox id="atributeGrid_kjAttributeDisplay" style="width:200px;"  />
                       </row>
                       <row spans="1,2,1,2">
                           <div align="right">
                              <label value="绑定属性名*" tooltiptext="绑定到后台的属性名称" style="color:red" />
                           </div>
                           <textbox id="atributeGrid_kjBindKey"  style="width:200px;" constraint="no empty:必填" />
                           <div align="right">
                               <label value="" tooltiptext=""  />
                           </div>

                       </row>
                       <row spans="1,2,1,2">
                            <div align="right">
                               <label value="查询字段名" tooltiptext="涉及到多表，请在字段前加前缀"  />
                            </div>
                            <textbox id="atributeGrid_kjQueryAttribute"  style="width:200px;"  />
                            <div align="right">
                                  <label value="默认样式" tooltiptext="默认样式，如果不配置就采用默认"  />
                            </div>
                            <textbox id="atributeGrid_kjDefaultcss"  style="width:200px;"  />
                       </row>
                       <row spans="1,5">
                            <div align="right">
                               <label value="按钮IconClass" tooltiptext="按钮前面的小图标"  />
                            </div>
                            <textbox id="atributeGrid_kjIconclass"  style="width:200px;"  />
                       </row>


                       <row spans="1,2,1,2">
                           <div align="right">
                               <label value="控件类型*" tooltiptext="在界面中显示的控件类别" style="color:red"   />
                           </div>
                           <listbox mold="select" id="atributeGrid_kjType" style="width:200px;" >
                           </listbox>
                           <div align="right">
                               <label value="数据类型*" tooltiptext="控件返回的数据类型" style="color:red"   />
                           </div>
                           <listbox mold="select" id="atributeGrid_kjDataType" style="width:200px;" >
                               <listitem value="0" label="String"  selected="true"/>
                               <listitem value="1" label="Integer"  />
                               <listitem value="2" label="Byte"  />
                               <listitem value="3" label="Double"  />
                               <listitem value="4" label="Decimal"  />
                           </listbox>
                       </row>
                       <row spans="1,5" id="kjTranslateRow" visible="false">
                           <div align="right">
                               <vbox>
                                  <label value="控件格式" tooltiptext="若控件时间控件，需要定义时间格式"   />
                               </vbox>
                           </div>
                           <textbox id="atributeGrid_kjTranslate" style="width:600px;"/>
                       </row>
                       <row spans="1,5" id="kjKzsxRow">
                         <div align="right">
                            <label value="扩展属性" tooltiptext="该控件的其它属性"   />
                         </div>
                         <textbox id="atributeGrid_kjKzsx" rows="3" style="width:600px;"/>
                       </row>
                       <row spans="1,5" id="kjClassRow" visible="false">
                           <div align="right">
                               <vbox>
                                  <label value="BandBox实现类" tooltiptext="若控件是bandbox，请输入具体的bandbox实现类"   />
                               </vbox>
                           </div>
                           <textbox id="atributeGrid_kjClass" style="width:600px;"/>
                       </row>
                       <row spans="1,2,1,2">
                           <div align="right">
                               <label value="报表相关" tooltiptext="用于JasperReport报表"   />
                           </div>
                           <hbox>
                              <checkbox id="atributeGrid_kjReportParam" label="报表参数" value="1" />
                              <space width="15px" />
                              <checkbox id="atributeGrid_kjReportField" label="报表字段" value="1" />
                           </hbox>
                           <div align="right">
                               <label value="是否隐藏" tooltiptext="若隐藏，则界面中不能显示"  />
                           </div>
                             <checkbox id="atributeGrid_kjHidden" label="隐藏" value="1" />
                       </row>
                       <row spans="1,2,1,2">
                           <div align="right">
                               <label value="初始化" tooltiptext="设置控件是否有初始值，该初始值在布局控件中控制是否加载"  />
                           </div>
                           <listbox mold="select" id="atributeGrid_kjDatasource" style="width:200px;" >
                               <listitem value="0" label="用户输入" selected="true" />
                               <listitem value="1" label="默认值"  />
                               <listitem value="3" label="自定义类"  />
                           </listbox>
                           <div align="right">
                               <label value="条件类型" tooltiptext="若该属性作为查询条件，则要定义查询条件的类型" />
                           </div>
                           <listbox id="atributeGrid_kjFilterType" mold="select" style="width:200px;">
                              <listitem value="0" label="等值条件" selected="true" />
                              <listitem value="1" label="like 条件"  />
                              <listitem value="2" label="between条件"  />
                              <listitem value="3" label="in 多个值"  />
                              <listitem value="4" label="大于等于和小于等于"  />
                           </listbox>
                       </row>
                       <row spans="1,2,1,2">
                           <div align="right">
                               <label value="输入框前缀" tooltiptext="输入框前缀"  />
                           </div>
                           <textbox id="atributeGrid_kjPre" style="width:200px;"/>
                           <div align="right">
                               <label value="输入框后缀" tooltiptext="输入框后缀" />
                           </div>
                           <textbox id="atributeGrid_kjAfter" style="width:200px;"/>
                       </row>
                       <row spans="1,5" id="kjInterfaceRow" visible="false">
                           <div align="right">
                               <label value="API接口" tooltiptext="调用的API路径" />
                           </div>
                           <textbox id="atributeGrid_kjInterface"  style="width:600px;"/>
                       </row>

                       <row spans="1,5" id="kjDefaultDataRow" visible="false">
                           <div align="right">
                               <label value="默认数据"  tooltiptext="默认数据，json格式，不同的控件有不同的要求"/>
                           </div>
                           <vbox>
                              <textbox id="atributeGrid_kjDefaultData" rows="4" style="width:600px;"/>
                              <label value='下拉框：[{"label":"显示标签","selected":true,"value":"对应值"},{"label":"显示标签","selected":false,"value":"对应值"}]' />
                              <label value='checkbox/radiobutton：[{"label":"显示名称","selected":true,"value":"对应值"}]' />
                              <label value='按钮下拉框：[{"label":"显示名称","link":"文件链接","event":"对应事件","type":"1:链接 2上传"}，{"label":"显示名称","link":"文件链接","event":"对应事件","type":"1:链接 2上传"}]' />
                              <label value='combobox：[{"label":"显示名称","value":"值","type":1必须有value，2label=value}，{"label":"显示名称","value":"值","type":1必须有value，2label=value}]' />
                              <label value='多选框：[{"label":"显示名称",其它属性自定}，{"label":"显示名称",其它属性自定}]' />
                           </vbox>
                       </row>
                       <row spans="1,5" id="kjClassImplRow" visible="false">
                           <div align="right">
                               <label value="自定义实现类"  tooltiptext="继承接口ComponenInitSer，扩展实现"/>
                           </div>
                           <combobox  id="atributeGrid_kjClassImpl" style="width:600px;" >

                           </combobox>
                       </row>
                        <row spans="1,5" id="kjInitDataRow" visible="false">
                           <div align="right">
                               <label value="接口/自定义参数"  tooltiptext="调用API所需要的参数，json对象数组{'变量':'值'}格式" />
                           </div>
                           <textbox id="atributeGrid_kjInitData" rows="4"   style="width:600px;"/>
                       </row>
                       <row spans="1,5" id="kjFormatRow" visible="true">
                          <div align="right">
                              <label value="转换显示类"  tooltiptext="对数据进行转换显示" />
                          </div>
                          <combobox  id="atributeGrid_kjFormat" style="width:600px;" ></combobox>
                       </row>
                       <row spans="1,5" >
                          <div align="right">
                              <label value="提示标签"  tooltiptext="" />
                          </div>
                          <textbox  id="atributeGrid_kjTipsLabel" style="width:600px;" ></textbox>
                       </row>

                    </rows>
                  </grid>
</window>