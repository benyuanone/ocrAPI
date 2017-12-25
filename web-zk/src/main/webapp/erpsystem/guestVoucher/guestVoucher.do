<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 左边树带查询的list列表，支持分页和不分页，总体布局上部分查询条件，下部分为grid，grid上部为按钮区 -->
<window
    height="100%"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.erpbasedata.erpguestvoucher.MainPage">
    <custom-attributes caname="/erpsystem/guestVoucher/guestVoucher.do" />
    <borderlayout id="bdLayout">
        <west id="westLayout" size="140px" flex="true" splittable="true" collapsible="true">
           <div align="left">
            <div class="container-fluid" style="padding:1px 2px 4px 1px;" >
               <div class="row">
                   <textbox id="searchTxt" placeholder="请输入名称过滤" sclass="col-sm-10" style="margin-left:20px;"  />
               </div>
            </div>
               <tree id="tree" zclass="z-dottree" >
                    <treechildren >
                        <treeitem id="rootItem" >
                           <treerow>
                              <treecell id="rootCell"></treecell>
                           </treerow>
                           <treechildren id="treeChild" />
                        </treeitem>
                    </treechildren>
               </tree>
           </div>
        </west>
        <center flex="true">
            <vbox>
                <div id="buttonGrid" class="container-fluid buttonDiv buttonGrid" >
                </div>
                <div class="container-fluid mainDiv" style="text-align:center;height:400px">
                    <image id="bigImage"/>
                    <div id="fileLabel"></div>
                </div>
                <div id="fileList" class="container-fluid mainDiv" style="text-align:center;height:400px">
                </div>
            </vbox>
        </center>
    </borderlayout>
</window>