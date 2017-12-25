<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="控件选择列表"
    width="800px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.HeadComponentSelAction">
    <custom-attributes caname="/sys/page/componentSelect.do" />
    <grid >
        <rows>
            <row spans="1,5">
               <div align="right">
                   <label value="控件*:" style="color:red" tooltiptext="请从列表中选择需要放置的控件" />
               </div>
               <listbox mold="select" id="componentSelGrid_pageRefOwid" style="width:400px;" />
            </row>
           <row spans="1,2,1,2">
                <div align="right">
                   <label value="顺序*:" style="color:red" tooltiptext="表头头部显示列的顺序号" />
                </div>
                <intbox  id="componentSelGrid_compOrder" style="width:200px;" constraint="no empty:必填" />
                <div align="right">
                   <label value="行号:" style="color:red" tooltiptext="目前支持表格头部是1行" />
                </div>
                <intbox  id="componentSelGrid_compStartRow" value="1" style="width:200px;" constraint="no empty:必填" />
           </row>
            <row spans="1,2,1,2">
                <div align="right">
                   <label value="排序字段:"  tooltiptext="该字段是否可排序"/>
                </div>
                <checkbox id="componentSelGrid_compIsorder" label="可排序" value="1" />
                <div align="right">
                   <label value="编辑字段:" tooltiptext="修改的时候，该字段是否可编辑" />
                </div>
                <checkbox id="componentSelGrid_compEditable" label="可编辑" value="1" />
           </row>
           <row spans="1,2,1,2">
                <div align="right">
                   <label value="统计类型:"  tooltiptext="该字段是否可统计及其统计类型"/>
                </div>
                <listbox id="componentSelGrid_compTjlx" mold="select" style="width:200px" >
                     <listitem value="0" selected="true" label="无" />
                     <listitem value="1"  label="固定文本" />
                     <listitem value="2"  label="总条数" />
                     <listitem value="3"  label="总数求和" />
                     <listitem value="4"  label="平均值" />
                     <listitem value="5"  label="最大" />
                     <listitem value="6"  label="最小" />
                </listbox>
                <div align="right">
                   <label value="固定文本:" tooltiptext="当选择固定文本的时候，这里输入文本内容" />
                </div>
                <textbox id="componentSelGrid_compTjwb" style="width:200px" />
           </row>
            <row spans="1,2,1,2">
                <div align="right">
                   <label value="控件样式:"  tooltiptext="整个表头的样式，用css定义，这里输入css的名称"/>
                </div>
                <textbox  id="componentSelGrid_compCss"  style="width:200px;" />
                <div align="right">
                   <label value="初始化:"  tooltiptext="进入双击修改页面，是否要初始化该控件"/>
                </div>
                <checkbox id="componentSelGrid_compInit" label="需要初始化" value="1" />
           </row>
           <row spans="1,5">
                <div align="right">
                   <label value="字体颜色:"   tooltiptext="json格式的字符串颜色变化"/>
                </div>
                <vbox>
                   <textbox  id="componentSelGrid_compFontCss" rows="2" style="width:600px;" />
                   <label value='json格式：[{"min":1.2,"max":2.5,"css":"color:red"},{"min":1.2,"max":2.5,"css":"color:red"}]' style="color:red" />
                </vbox>
           </row>
           <row spans="1,5">
                <div align="right">
                   <label value="检验规则:"  tooltiptext="校验正则表达式"/>
                </div>
                <textbox  id="componentSelGrid_kjConstraint" rows="2" style="width:600px;" />
           </row>
           <row spans="1,5">
                <div align="right">
                   <label value="校验提示label:"  tooltiptext="多语言标签"/>
                </div>
                <textbox  id="componentSelGrid_kjConstraintKey" style="width:600px;" />
           </row>
           <row spans="1,5">
                <div align="right">
                   <label value="事件类型:" tooltiptext="所能触发的事件" />
                </div>
                 <listbox mold="select"  id="componentSelGrid_eventType" style="width:200px;" >
                   <listitem value="0" label="无效"  selected="true"/>
                   <listitem value="1" label="onClick"   />
                   <listitem value="2" label="onDoubleClick"   />
                   <listitem value="3" label="onOK"   />
                   <listitem value="4" label="onChange"   />
                   <listitem value="5" label="onBlur"   />
                   <listitem value="6" label="onSelect"   />
                   <listitem value="7" label="onFocus"   />
                 </listbox>

           </row>
           <row spans="1,5">
              <div align="right">
                   <label value="执行类:" tooltiptext="事件触发的内容"  />
              </div>
             <combobox  id="componentSelGrid_evnetFormula" style="width:600px;" >

             </combobox>
           </row>
           <row spans="1,5" id="eventDataContentRow">
              <div align="right">
                  <label value="自定义方法参数:" tooltiptext="当选择自定义类的时候，输入自定义类所需要的参数数据"  />
              </div>
              <textbox  id="componentSelGrid_eventDataContent" rows="3" style="width:600px;" />
           </row>
           <row spans="6">
               <div align="center">
                  <hbox>
                     <button id="saveBtn" label="保存" />
                      <button id="removeBtn" label="删除" />
                     <button id="closeBtn" label="取消" />
                  </hbox>
               </div>
           </row>
        </rows>
    </grid>

</window>