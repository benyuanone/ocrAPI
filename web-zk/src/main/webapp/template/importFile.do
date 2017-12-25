<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 表格过滤 -->
<window
    title="附件中心"
    width="300px"
    height="150px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.attachfile.ImportFileAction"
	mode="overlapped" position="center,top"
	>
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
    <custom-attributes caname="/template/importFile.do" />

    <div class="container-fluid" style="padding-bottom:15px;" >
      <div class="row">
         <div class="col-sm-12">
           <div class="btn-group">
           	 <button mold="bs" id="closeBtn" class="btn-info">关闭</button>
           	 <button mold="bs" id="uploadBtn" upload="true,maxsize=-1,multiple=true" class="btn-info btn">上传</button>
           </div>
         </div>
      </div>
    </div>
    <dropupload  id="fileDropUpload" maxsize="-1"  content="Drap Here to Upload" detection="browser" />
    <div style="color:#ccc;font-size:12px;">
       请点击上传按钮上传文件或者把需要导入的文件拖入到此区域中上传。
    </div>
</window>