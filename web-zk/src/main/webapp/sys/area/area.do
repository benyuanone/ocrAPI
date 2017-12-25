<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
        xmlns="http://www.zkoss.org/2005/zul"
        xmlns:h="http://www.w3.org/1999/xhtml"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        use="com.ourway.base.zk.sys.area.AreaAction">
    <custom-attributes caname="/sys/area/area.do" />
    <borderlayout id="bdLayout">
        <west id="westLayout" title="地区管理" size="220px" flex="true" splittable="true" collapsible="true">
            <div align="left">
                <tree id="tree" zclass="z-dottree" >
                    <treechildren >
                        <treeitem id="rootItem" value="-1" >
                            <treerow>
                                <treecell> 地区维护 </treecell>
                            </treerow>
                            <treechildren id="treeChild" />
                        </treeitem>
                    </treechildren>
                </tree>
            </div>
        </west>
        <center flex="true">
            <grid  id="baseInfo">
                <rows>
                    <row spans="6" style="height:50px">
                        <div align="left">
                            <hbox>
                                <button id="saveBtn" label="保存" />
                                <button id="copyBtn" label="复制" />
                                <button id="pasteBtn" label="粘贴" />

                            </hbox>
                        </div>
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right"><label value="地区名称*" style="color:red" /></div>
                        <textbox id="baseInfo_areaName" style="width:600px"   />
                        <label value="地区名称" />
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right"><label value="地区编号*" style="color:red" /></div>
                        <textbox id="baseInfo_areaCode" style="width:600px"   />
                        <label value="地区编号" />
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right"><label value="地域别名*" style="color:red" /></div>
                        <textbox id="baseInfo_areaAlais" style="width:600px"   />
                        <label value="地域别名" />
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right"><label value="多语言*" style="color:red" /></div>
                        <textbox id="baseInfo_language" style="width:600px"   />
                        <label value="多语言显示" />
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right"><label value="是否显示" /></div>
                        <hbox>
                            <checkbox id="baseInfo_visiable" value="1" label="" />
                        </hbox>
                        <label value="控制该地址是否显示" />
                    </row>
                    <row spans="1,3,2" style="height:50px">
                        <div align="right"><label value="备注"  /></div>
                        <textbox id="baseInfo_memo" style="width:600px"   />
                        <label value="特别说明" />
                    </row>
                </rows>
            </grid>
        </center>
    </borderlayout>
</window>