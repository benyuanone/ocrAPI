<?xml version="1.0" encoding="UTF-8"?>
<?page title="系统"?>
<window   
    height="100%"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	>
<custom-attributes caname="/controls.do" />
      <grid>
         <columns>
              <column>控件名称</column>
              <column>控件展示</column>
         </columns>
         <rows>
            <row>
               <label value="正常Label标签"/>
               <label value="你好你好"  />
            </row>
            <row>
               <label value="CSS Label标签"/>
               <label value="你好你好" style="color:red;font-size:14px;font-weight:bold"/>
            </row>
            <row>
               <label value="换行Label标签"/>
               <label value="你好你好你好" style="color:red;font-size:14px;font-weight:bold;display:block;width:50px;"/>
            </row>
            <row>
               <label value="提示Label标签"/>
               <label value="你好你好你好" iconSclass="tttt" tooltiptext="浮动提示浮动提示浮动提示浮动提示" style="color:red;font-size:14px;font-weight:bold;display:block;width:50px;"/>
            </row>
         </rows>
      </grid>
</window>