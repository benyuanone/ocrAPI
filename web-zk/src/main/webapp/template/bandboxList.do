<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 带查询的list列表，支持分页和不分页，总体布局上部分查询条件，下部分为grid，grid上部为按钮区 -->
<window
    width="95%"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.BandboxListTemplatePagesAction">
    <custom-attributes caname="/template/listTemplatePages.do" />
    <!-- 顶部查询区 -->
    <div id="conditionGrid" class="container-fluid conditionGrid"  >


    </div>
    <div  id="buttonGrid" class="container-fluid buttonDiv buttonGrid">

    </div>
    <div class="container-fluid dataListDiv"  >
       <div class="row">
          <div class="col-sm-12">
             <grid id="dataList"  />
          </div>
       </div>
    </div>
</window>