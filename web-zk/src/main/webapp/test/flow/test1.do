<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="页面控件详情"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.test.FlowTestAction">
    <custom-attributes caname="/sys/page/component.do" />

    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
            <div class="row form-horizontal" >

                <div class="col-sm-2">

                </div>
                <div class="col-sm-8">
                    <textbox id="content" class="form-control input-sm" />
                </div>
                <div class="col-sm-2">

                </div>
            </div>
             <div class="row form-horizontal" >
                <div class="col-sm-12" style="text-align:left;padding-left:2px;">
                    <button mold="bs" class="btn-success" id="button_addBtn">提交审核</button>
                </div>

            </div>
    </div>

    <div id="step1" class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
        <div class="row form-horizontal" >
           <div class="col-sm-2">

           </div>
           <div class="col-sm-8">
               <textbox id="content1" class="form-control input-sm" />
           </div>
           <div class="col-sm-2">

           </div>
        </div>
        <div class="row form-horizontal" >
           <div class="col-sm-12" style="text-align:left;padding-left:2px;">
               <button mold="bs" class="btn-success" id="reject1_addBtn">拒绝</button>
               <button mold="bs" class="btn-success" id="agree1_addBtn">通过</button>
           </div>
        </div>

    </div>
    <div id="step2" class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 0px 10px;" >
            <div class="row form-horizontal" >
               <div class="col-sm-2">

               </div>
               <div class="col-sm-8">
                   <textbox id="content2" class="form-control input-sm" />
               </div>
               <div class="col-sm-2">

               </div>
            </div>
            <div class="row form-horizontal" >
               <div class="col-sm-12" style="text-align:left;padding-left:2px;">
                   <button mold="bs" class="btn-success" id="reject2_addBtn">拒绝</button>
                   <button mold="bs" class="btn-success" id="agree2_addBtn">通过</button>
               </div>
            </div>

        </div>


</window>