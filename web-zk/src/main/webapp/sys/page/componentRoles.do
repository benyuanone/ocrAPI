<?xml version="1.0" encoding="UTF-8"?>
<?page title="角色详情"?>
<window
        width="800px"
        xmlns="http://www.zkoss.org/2005/zul"
        xmlns:h="http://www.w3.org/1999/xhtml"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        use="com.ourway.base.zk.sys.page.RolesComponentAction">
    <custom-attributes caname="/sys/page/componentRoles.do" />
    <grid>
        <rows>
            <row spans="6">
                <div align="left">
                    <hbox >
                        <button label="保存" id="saveBtn" />
                        <button label="取消" id="cancelBtn" />
                    </hbox>
                </div>
            </row>
        </rows>
    </grid>
    <tabbox id="tabbox" >
    <tabs id="tabs">
        <tab label="基本信息" />
        <tab label="角色-用户" />
        <tab label="角色-功能" />
    </tabs>
        <tabpanels>
        <tabpanel>
            <grid>
                <rows>
                <row spans="1,2,1,2">
                    <div align="right">
                        <label value="角色名" style="color:red" />
                    </div>
                    <textbox id="pageInfo_roleName" style="width:200px;" constraint="no empty:必填"/>
                    <div align="right">
                        <label value="角色类型" />
                    </div>
                    <listbox id="pageInfo_roleType" style="width:200px;" mold="select" />
                </row>
                    <row spans="1,2,1,2">
                        <div align="right">
                            <label value="是否默认" />
                        </div>
                        <checkbox id="pageInfo_isdefault" value="0" style="width:200px;" />
                        <div align="right">
                            <label value="角色描述"  />
                        </div>
                        <textbox id="pageInfo_roleMemo" style="width:200px;"  />
                    </row>

                </rows>
            </grid>
        </tabpanel>
            <tabpanel>
                    <grid>
                        <rows>
                            <row align="center">
                                <div>
                                    <vbox style="width:320px;height:300px;float:left">
                                        <label value="未分配用户" />
                                        <hbox>
                                            <textbox id="empName" style="width:220px;height:22px" />
                                            <!--onClick="winFpEmp.filter()"-->
                                            <button label="查询" style="width:70px" id="filterBtn" />
                                        </hbox>
                                        <listbox multiple="true" style="width:300px;height:260px;" rows="8" id="noused" />
                                    </vbox>
                                    <vbox style="width:80px;height:300px;float:left">
                                        <button id="addInBtn" label="分配-》" style="margin-top:120px;height:45px;width:75px;color:red;font-size:14px;" /><!--onClick="winFpEmp.addIn()"-->
                                        <space spacing="10px"/>
                                        <button id="moveOutBtn" label="《-移出"  style="height:45px;width:75px;color:red;font-size:14px;" /><!--onClick="winFpEmp.addOut()"-->
                                    </vbox>
                                    <vbox style="width:320px;height:300px;float:left;margin-left:10px;">
                                        <label value="已分配员工" />
                                        <listbox multiple="true" style="width:300px;height:290px;" rows="8" id="used" />
                                    </vbox>
                                </div>
                            </row>
                        </rows>
                    </grid>
            </tabpanel>
            <tabpanel>
                <grid>
                    <rows>
                        <row>
                            <tree id="privTree" checkmark="true" multiple="true" height="500px" >
                                <treechildren id="privTreeContent">
                                </treechildren>
                            </tree>
                        </row>
                    </rows>
                </grid>
            </tabpanel>
        </tabpanels>
   </tabbox>

</window>