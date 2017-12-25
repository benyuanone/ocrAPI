<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 主从表详细界面 -->
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.DetailTwoTableTemplateAction">
    <custom-attributes caname="/template/detailTwoTableTemplate.do" />
    <grid id="operateGrid" class="operateGrid">

    </grid>
    <grid id="mainTableGrid" class="mainTableGrid">

    </grid>
    <groupbox mold="3d" >
        <caption id="dataListGroupboxCaption">
            <span id="arrow" class="open-true" />
        </caption>
        <vbox>
           <grid id="dataListHeader" class="dataListHeader"/>
           <grid id="dataList" style="width:100%" class="dataListDiv"/>
        </vbox>
    </groupbox>

</window>