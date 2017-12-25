<?page title="奥威ERP系统"?>
<?link rel="stylesheet" type="text/css"	href="/bootstrap/v3/bootstrap/themes/bootstrap-default.min.css" if="${empty param.theme}"?>
<?link rel="stylesheet" type="text/css"	href="/bootstrap/v3/assets/css/docs.css" ?>
<?link rel="stylesheet" type="text/css"	href="/bootstrap/v3/assets/css/pygments-manni.css" ?>
<?script src="/bootstrap/v3/assets/js/html5shiv.js" if="${zk.ie < 9}" ?>
<?script src="/bootstrap/v3/assets/js/respond.min.js" if="${zk.ie < 9}"?>
<!-- 引入websocket -->
<?script src="/assets/lib/jquery/jquery.min.js" ?>
<?script src="/assets/common/app.js" ?>
<?script src="/assets/common/md5.js" ?>
<?script src="/assets/lib/sockjs/sockjs.js" ?>
<?script src="/assets/common/func.js" ?>
<?script src="/assets/common/SocketClient.js" ?>
<!-- Load fonts from local -->
<?link href="css/index.css" rel="stylesheet" type="text/css"?>
<?link href="css/whitetone.css" rel="stylesheet" type="text/css"?>
<?link href="css/fonts.css" rel="stylesheet" type="text/css"?>

<zk xmlns:n="native">
<window use="com.ourway.base.zk.main.MainAction"
        id="mainWin" height="100%" width="100%"
        xmlns="http://www.zkoss.org/2005/zul"
        xmlns:h="http://www.w3.org/1999/xhtml"
        >
    <custom-attributes caname="/application/index.do" />
	<div sclass="navbar">
		<a image="../charisma/img/img_logo.png" label="欢迎使用奥威ERP V1.0" sclass="pull-left navbar-brand"/>
		<textbox id="hiddenMessage" visible="false" >
		       <custom-attributes org.zkoss.zk.ui.updateByClient="true"/>
		</textbox>
		<a id="commandBtn" visible="false" onClick="mainWin.invokeByJs()"/>
		<hlayout sclass="nav-user pull-right" spacing="0" >
		   <a id="anoti" iconSclass="z-icon-bell"  popup="notipp, position=after_end, type=toggle" style="border-left:none">
           		<label value="0" id="anotiNumLabel" sclass="badge badge-important"/>
           </a>
		    <menubar sclass="user-menu light-blue menu-message">
            	<menu label="" iconSclass="z-icon z-icon-message" >
            		<menupopup>
            			<menuitem label="默认主题" onClick='Executions.sendRedirect("?theme=default")' iconSclass="z-icon-angle-double-right"/>
            			<menuitem label="Amelia" onClick='Executions.sendRedirect("?theme=amelia")' iconSclass="z-icon-angle-double-right"/>
            		</menupopup>
            	</menu>
            </menubar>
			<menubar sclass="user-menu light-blue">
				<menu tooltiptext="John's Avatar" label="" id="userInfo" image="assets/avatars/user.jpg"><!--  -->
					<menupopup>
						<menuitem label="退出" onClick='mainWin.logout()' iconSclass="z-icon-angle-double-right"/>
					</menupopup>
				</menu>
			</menubar>
		</hlayout>
		<popup id="notipp" sclass="menu menu-pink" width="240px">
        	<a label="" id="notippLabel" sclass="header" iconSclass="z-icon-exclamation-triangle"/>
        	<vlayout id="taskList" spacing="0">

        	</vlayout>
        </popup>
	</div>

  <hlayout id="main" width="100%" spacing="0" sclass="indexContent">
	<div id="sidebar" sclass="sidebar">
	   <!-- Navigation List -->
	    <navbar id="navbar" mold="default" sclass="nav-list" orient="vertical">

	    </navbar>
	    <!-- Toggler -->
	    <div sclass="sidebar-collapse">
		    <a id="toggler" iconSclass="z-icon-angle-double-left" onClick="mainWin.openClose()" />
	    </div>
    </div>
	<tabbox id="resources" sclass="sourcetabs" >
	    <tabs id="resourceTabs"></tabs>
	    <tabpanels id="resourceTabpanels" sclass="z-tabpanels-index"></tabpanels>
    </tabbox>
  </hlayout>
  <div sclass="indexFooter">
       <label style="font-color:#000000;font-size:12px;text-align:center;width:100%;" value="Copyright © 2015-2017 ourway. All Rights Reserved | www.ourway.com "> </label>
  </div>
</window>	
</zk>