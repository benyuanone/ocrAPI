<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
        xmlns="http://www.zkoss.org/2005/zul"
        xmlns:h="http://www.w3.org/1999/xhtml"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        use="com.ourway.base.zk.sys.department.DepartAction">
    <custom-attributes caname="/sys/department/department.do"/>
    <borderlayout id="bdLayout">
        <west id="westLayout" title="部门管理" size="220px" flex="true" splittable="true" collapsible="true">
            <div align="left">
                <tree id="tree" zclass="z-dottree">
                    <treechildren>
                        <treeitem id="rootItem" value="-1">
                            <treerow>
                                <treecell>部门维护</treecell>
                            </treerow>
                            <treechildren id="treeChild"/>
                        </treeitem>
                    </treechildren>
                </tree>
            </div>
        </west>
        <center flex="true">
            <grid id="baseInfo">
                <rows>
                    <row spans="6" style="height:50px">
                        <div align="left">
                            <hbox>
                                <button id="saveBtn" label="保存"/>
                                <button id="copyBtn" label="复制"/>
                                <button id="pasteBtn" label="粘贴"/>

                            </hbox>
                        </div>
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right">
                            <label value="部门名称*" style="color:red"/>
                        </div>
                        <textbox id="depart_name" style="width:600px"/>
                        <label value="所在部门名称"/>
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right">
                            <label value="部门地址*" style="color:red"/>
                        </div>
                        <textbox id="depart_depAddr" style="width:600px"/>
                        <label value="所在部门地址"/>
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right">
                            <label value="联系人"/>
                        </div>
                        <textbox id="depart_depLxr" style="width:600px"/>
                        <label value=""/>
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right">
                            <label value="联系电话"/>
                        </div>
                        <textbox id="depart_depPhone" style="width:600px"/>
                        <label value=""/>
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right">
                            <label value="联系email"/>
                        </div>
                        <textbox id="depart_depMail" style="width:600px"/>
                        <label value=""/>
                    </row>
                </rows>
            </grid>
        </center>
    </borderlayout>
</window>