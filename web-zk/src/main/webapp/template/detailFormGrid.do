<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 主从表详细界面 -->
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.DetailFormGridTemplateAction">
    <custom-attributes caname="/template/detailFormGrid.do" />

    <div  id="operateGrid" class="container-fluid buttonDiv operateGrid" >

    </div>
    <!-- 主表区 -->
    <div id="mainTableGrid" class="container-fluid mainTableGrid" >


    </div>
    <panel id="dataListGroupboxCaption"  sclass="panel-primary" collapsible="true" style="margin-top: -2px;">
	   <panelchildren>
	       <div  id="dataListHeader" class="container-fluid buttonDiv dataListHeader"  >

           </div>
           <div class="container-fluid dataListDiv" >
              <!--<div class="row">-->
                <!--<div class="col-sm-12">-->
                   <grid id="dataList"  />
                <!--</div>-->
              <!--</div>-->
           </div>
	   </panelchildren>
    </panel>

</window>