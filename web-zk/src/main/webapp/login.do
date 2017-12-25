<?xml version="1.0" encoding="UTF-8"?>
<?page title="奥威ERP系统"?>
<?meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /?>
<?meta name="renderer" content="webkit" /?>
<?link href="/charisma/css/bootstrap-cerulean.min.css" rel="stylesheet" type="text/css"?>
<?link href="/charisma/css/charisma-app.css" rel="stylesheet" type="text/css"?>
<?link href="/charisma/css/login.css" rel="stylesheet" type="text/css"?>
<window
    xmlns="http://www.zkoss.org/2005/zul"
    xmlns:h="http://www.w3.org/1999/xhtml"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.syszk.login.LoginAction"
	id="loginWin"
	onOk="loginWin.check()">
<custom-attributes caname="/login.do" />
<grid onClientInfo="loginWin.onClientInfo(event)"></grid>
<h:div><h:img src="charisma/img/header.png"></h:img></h:div>
 <h:div class="ch-container" style="position:relative;">
     <h:div class="ch-container-bg">

     </h:div>
            <h:div class="row">

                <h:div class="row">
                    <h:div class="well col-md-5 center login-box" style="margin-left: 56% !important;">
                        <h:div class="alert" style="font-size: 28px;">
                            欢迎登录
                        </h:div>
                        <h:form class="form-horizontal" >
                            <h:fieldset>
                                <h:div class="input-group input-group-lg" style="height:35px;">
                                   <h:span class="input-group-addon" style="background-color: #aaacab"><h:i class="glyphicon glyphicon-list white"></h:i></h:span>
                                   <listbox id="loginWin_language" class="form-control" mold="select" >
                                   </listbox>
                                </h:div>
                                <h:div class="clearfix" style="height: 25px;">
                                      <h:p></h:p>
                                </h:div>
                                <h:div class="input-group input-group-lg" style="height:65px;">
                                    <h:span class="input-group-addon" style="background-color: #aaacab"><h:i class="glyphicon glyphicon-user white"></h:i>
                                    </h:span>
                                    <textbox id="loginWin_empNo" class="form-control" placeholder="账号" constraint="no empty:账号不能为空"/>
                                </h:div>
                                <h:div class="clearfix" style="height: 25px;">
                                    <h:p></h:p>
                                </h:div>
                                <h:div class="input-group input-group-lg"  style="height:65px;">
                                    <h:span class="input-group-addon" style="background-color: #aaacab">
                                        <h:i class="glyphicon glyphicon-lock white"></h:i>
                                    </h:span>
                                    <textbox id="loginWin_empPsw" type="password"  class="form-control"  placeholder="密码" constraint="no empty:密码不能为空">
                                        <attribute name="onOK"><![CDATA[
                        									saveBtn.focus();
                        									loginWin.check();
                        		]]></attribute>
                                    </textbox>
                                </h:div>

                                <h:div class="clearfix"><h:p></h:p></h:div>
                                <h:p class="center col-md-5" style="width:100%;padding: 0px;">
                                    <button label="登   录" id="saveBtn" class="btn btn-login" onClick="loginWin.check()" />
                                </h:p>
                                <h:div class="clearfix"><h:p></h:p></h:div>

                            </h:fieldset>
                        </h:form>
                    </h:div>
                    <!--/span-->
                </h:div><!--/row-->
            </h:div><!--/fluid-row-->

        </h:div><!--/.fluid-container-->
    <h:div style="text-align: center;"><h:p style="padding: 5px; margin: 5px;">Copyright © 2015-2017 ourway. All Rights Reserved | www.ourway.</h:p></h:div>
</window>