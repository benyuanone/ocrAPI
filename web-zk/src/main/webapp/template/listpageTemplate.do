<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window   
    height="100%"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.ListPageTemplateAction">
    <custom-attributes caname="/template/listpageTemplate.do" />
    <borderlayout id="bdLayout">
         <center flex="true">
            <div >
               <vbox>
                  <grid id="conditionGrid" />
                  <grid id="buttonGrid" />
                  <grid id="dataList" />
               </vbox>
            </div>
         </center>
         <east id="eastLayout" title="高级查询" size="220px" flex="true" open="false" collapsible="true">
            <div >
               <grid id="otherConditionGrid" />
            </div>
         </east>
       </borderlayout>
</window>