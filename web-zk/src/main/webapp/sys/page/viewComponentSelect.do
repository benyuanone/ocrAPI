<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="控件选择列表"
    width="800px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.page.ComponentSelAction">
    <custom-attributes caname="/sys/page/componentSelect.do" />
    <grid id="componentSelGrid">
        <rows>
           <row spans="1,5">
               <div align="right">
                  <label value="控件*:" style="color:red" tooltiptext="请从列表中选择需要放置的控件" />
               </div>
               <listbox mold="select" id="componentSelGrid_pageRefOwid" style="width:400px;" />
           </row>
           <row spans="1,2,1,2">
                <div align="right">
                   <label value="所在行:" tooltiptext="当前控件所在行"  />
                </div>
                <label id="componentSelGrid_compStartRow" style="color:red"  />
                <div align="right">
                    <label value="占据列数*:" style="color:red" tooltiptext="当前控件所占的列数，列数必须小于表格所定义的总列数" />
                </div>
                 <intbox  id="componentSelGrid_compCols" style="width:200px;" constraint="no empty:必填" />
           </row>
            <row spans="1,2,1,2">
                <div align="right">
                   <label value="合并编号:" tooltiptext="多个控件的Hbox横向合并，输入同一编号，0表示不合并。若多个控件合并，第一个控件所占列数必须为整个合并体所占列数，其它合并控件所占列数为0" />
                </div>
                <intbox  id="componentSelGrid_compHbox" style="width:200px;" />
                <div align="right">
                    <label value="显示label:" tooltiptext="是否需要显示控件的label标签，占据1列"  />
                </div>
                <checkbox id="componentSelGrid_compLable" value="1" label="显示Label" />
           </row>
           <row spans="1,5">
                <div align="right">
                    <label value="输入框行数:" tooltiptext="当为多行输入框时候，输入行数，默认为1" />
                </div>
                <intbox  id="componentSelGrid_compRowsnum"  style="width:200px;" />
            </row>
            <row spans="1,2,1,2">
               <div align="right">
                   <label value="Label样式CSS:"  tooltiptext="Label目前用css实现"/>
               </div>
               <textbox  id="componentSelGrid_compColor" style="width:200px;" />
           </row>
           <row spans="1,5">
              <div align="right">
                  <label value="检验正则式:" tooltiptext="作为输入控件的时候，前台的校验正则表达式" />
              </div>
              <textbox  id="componentSelGrid_kjConstraint"  style="width:600px;" />
           </row>
           <row spans="1,5">
              <div align="right">
                  <label value="校验提示标签:" tooltiptext="校验失败后提示标签，多个标签用,分隔"  />
              </div>
              <textbox  id="componentSelGrid_kjConstraintKey" style="width:600px;" />
           </row>
           <row spans="1,5">
                <div align="right">
                   <label value="控件样式名:" tooltiptext="整个控件的css样式"  />
                </div>
                <textbox  id="componentSelGrid_compCss" style="width:600px;" />
           </row>
           <row spans="1,2,1,2">
                <div align="right">
                   <label value="初始化:"  tooltiptext="在该布局中，是否要初始化，若需要，则根据控件的配置进行初始化"/>
                </div>
                <checkbox id="componentSelGrid_compInit" label="需要初始化" value="1" />
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
             <combobox  id="componentSelGrid_evnetFormula" style="width:600px;" > </combobox>
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