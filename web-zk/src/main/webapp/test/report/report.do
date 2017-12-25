<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="报表测试"
    id="reportWin"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.test.ReportTestAction">
    <custom-attributes caname="/sys/page/component.do" />

    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
            <div class="row form-horizontal" >
               <div class="col-lg-6">

                   <span class="input-group-btn">
                         <button dir="reverse" class="btn-default dropdown-toggle" popup="m1, after_start" iconSclass="caret">
                                          报表
                          </button>
                   </span>
               </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->

    </div>

 <menupopup id="m1">
        <menuitem label="测试1" id="defaultItem" />
        <menuitem label="Another action" />
        <menuitem label="Something else here" />
        <menuseparator />
        <menuitem label="Separated link" />
    </menupopup>

    <chosenbox id="chosenbox" />

   <button id="submit" label="tijiao"  />

</window>