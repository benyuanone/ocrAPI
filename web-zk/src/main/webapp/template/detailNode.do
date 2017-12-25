<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 树节点详情 -->
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.DetailTreeNodeAction"
	style="width:800px;"
	>
    <custom-attributes caname="/template/detailNode.do" />
    <div class="container-fluid" style="padding-bottom:15px;" >
        <div class="row">
            <div class="col-sm-12" style="text-align:left;">
                <div class="btn-group">
                    <button mold="bs" id="saveBtn" class="btn-success">保存</button>
                    <button mold="bs" id="closeBtn" class="btn-info">关闭</button>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12" style="text-align:left;">
               <div class="col-sm-1"></div>
               <div class="col-sm-10 control-label" style="text-align:left;padding-right:2px;height:30px;">
                     <label id="treeDetail_nodeLabel" value="" style="color:red" />
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12" style="text-align:left;">
               <div class="col-sm-1"></div>
               <div class="col-sm-10 control-label" style="text-align:left;padding-right:2px;height:30px;">
                     <textbox  id="treeDetail_nodeName"  class="form-control input-sm"  constraint="no empty:必填" />
               </div>
            </div>
        </div>
    </div>

</window>