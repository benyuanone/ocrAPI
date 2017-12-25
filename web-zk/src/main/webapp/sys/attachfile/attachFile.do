<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="附件中心"
    width="800px"
    height="400px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.attachfile.AttachFileAction"
	mode="overlapped" position="center,top"
	>
    <custom-attributes caname="/sys/attachfile/attachFile.do" />
<style>
.z-button {
       display: inline-block;
       padding: 6px 12px;
       margin-bottom: 0;
       font-size: 14px;
       font-weight: normal;
       line-height: 1.428571429;
       text-align: center;
       white-space: nowrap;
       vertical-align: middle;
       cursor: pointer;
       border: 1px solid transparent;
       border-radius: 4px;
       -webkit-user-select: none;
       -moz-user-select: none;
       -ms-user-select: none;
       -o-user-select: none;
       user-select: none;
       background: -webkit-linear-gradient(top, #5BC0DE 0%, #5BC0DE 100%);
       background-color: #5bc0de;
       border-color: #46b8da;
}

</style>
<div class="container-fluid" style="padding-bottom:15px;" >
  <div class="row">
      <div class="col-sm-12">
           <div class="btn-group">
           	 <button mold="bs" id="closeBtn" class="btn-info">关闭</button>
           	 <button mold="bs" id="saveBtn" class="btn-success">保存</button>
           	 <button mold="bs" id="uploadBtn" upload="true,maxsize=-1,multiple=true" class="btn-info btn">上传</button>
           	 <button mold="bs" id="downBtn" class="btn-info">下载</button>
           	 <button mold="bs" id="refreshBtn" class="btn-info">刷新</button>
           	 <button mold="bs" id="removeBtn" class="btn-danger">删除</button>
           </div>
      </div>
  </div>
</div>
<dropupload  id="fileDropUpload" maxsize="-1"  content="Drap Here to Upload" detection="browser" />
<listbox id="datas" style="width:99%;height:300px;" />

</window>