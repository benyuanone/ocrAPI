<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 左边树带查询的list列表，支持分页和不分页，总体布局上部分查询条件，下部分为grid，grid上部为按钮区 -->
<window
    height="100%"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.TreeTemplateGridAction">
    <custom-attributes caname="/template/leftTreeGridTemplate.do" />
    <borderlayout id="bdLayout">
        <west id="westLayout" size="140px" flex="true" splittable="true" collapsible="true">
           <div align="left">
            <div  class="container-fluid" style="padding:1px 2px 4px 1px;" >
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
            <!-- 顶部查询区 -->
            <div id="conditionGrid" class="container-fluid conditionGrid" >


            </div>
            <div  id="buttonGrid" class="container-fluid buttonDiv buttonGrid" >

            </div>
            <div class="container-fluid dataListDiv" >
                <div class="row">
                    <div class="col-sm-12">
                        <grid id="dataList"  />
                    </div>
                </div>
            </div>
          </vbox>
        </center>
    </borderlayout>
</window>