<?xml version="1.0" encoding="UTF-8"?>
<?page title="语言信息"?>
<window id="winEdit" title="语言界面"
	xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.syszk.test.TestEdit">
	<custom-attributes caname="/sys/language/languageDetail.do" />
		<grid>
			<rows>
				<row spans="1,2,1,2">
					<div align="right"  style="color:red">
						<label value="语言标签*："/>
					</div>
					<textbox id="labelKey"  style="width:150px" constraint="no empty:必输" />
					<div align="right">
						<label value="名称：" />
					</div>
					<textbox id="labelName" style="width:150px" />
				</row>
				<row spans="1,2,1,2">
                					<div align="right"  style="color:red">
                						<label value="语言类型："/>
                					</div>
                					<intbox id="labelType"  style="width:150px" constraint="no empty:必输" />

                </row>
				<row spans="6" align="center">
					<hbox>
						<button id="updateBtn" label="保存" onClick='winEdit.save()'/>
						<space spacing="10px" />
						<button label="取消" onClick="winEdit.detch()" />
					</hbox>
				</row>
			</rows>
		</grid>
</window>
