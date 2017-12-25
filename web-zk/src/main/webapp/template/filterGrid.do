<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 表格过滤 -->
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.FilterGridAction">
    <custom-attributes caname="/template/filterGrid.do" />
    <!-- 主表区 -->
    <div id="mainTableGrid" class="container-fluid mainTableGrid" >
        <div class="row form-horizontal form-group" >
            <div class="col-sm-4" style="text-align:left;padding-left:10px;">
                <listbox  mold="select" id="key1" class="form-control input-sm"  />
            </div>
            <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <listbox mold="select" id="rel1" class="form-control input-sm" >
                    <listitem value="0" label="==" selected="true" />
                    <listitem value="1" label="&gt;="  />
                    <listitem value="2" label="&gt;"  />
                    <listitem value="3" label="&lt;="  />
                    <listitem value="4" label="&lt;"  />
                    <listitem value="5" label="like"  />
                </listbox>
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                <textbox  id="val1" class="form-control input-sm" />
            </div>
        </div>
        <div class="row form-horizontal form-group" >
            <div class="col-sm-4" style="text-align:left;padding-left:10px;">
                <listbox  mold="select" id="key2" class="form-control input-sm"  />
            </div>
            <div class="col-sm-3" style="text-align:left;padding-left:2px;">
                <listbox  mold="select" id="rel2" class="form-control input-sm" >
                     <listitem value="0" label="==" selected="true" />
                     <listitem value="1" label="&gt;="  />
                     <listitem value="2" label="&gt;"  />
                     <listitem value="3" label="&lt;="  />
                     <listitem value="4" label="&lt;"  />
                     <listitem value="5" label="like"  />
                </listbox>
            </div>
            <div class="col-sm-5" style="text-align:left;padding-left:2px;">
                <textbox  id="val2" class="form-control input-sm" />
            </div>
        </div>

    </div>
    <div id="operateGrid" class="container-fluid buttonDiv operateGrid" style="text-align:center;margin-top:20px;"  >
      <div class="col-sm-12" style="text-align:center;width:100%;" >
       <button label="search" mold="bs" id="confirm" sclass="btn-primary " iconSclass="z-icon z-icon-btnsearch" style="border:1px solid #ccc"  />
       <button label="cancer" mold="bs" id="close" sclass="btn-primary " iconSclass="z-icon z-icon-cancel" style="padding-left:10px;"  />
      </div>
    </div>

</window>