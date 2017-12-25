<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	class="container"
>
<custom-attributes caname="/sys/demo/input.do" />
<custom-attributes caname="/sys/demo/input.do" />
   <menupopup id="m1">
   	<menuitem label="Action" />
   	<menuitem label="Another action" />
   	<menuitem label="Something else here" />
   	<menuseparator />
   	<menuitem label="Separated link" />
   </menupopup>
   <div class="btn-group">
   	<button mold="bs" class="btn-primary">Action</button>
   	<button mold="bs" class="btn-primary dropdown-toggle" iconSclass="caret"
   		onClick='m1.open(self.previousSibling, "after_start")'>
   	</button>
   </div>

	<button dir="reverse" mold="bs" class="btn-primary dropdown-toggle"
		popup="m1, after_start" iconSclass="caret">
		Action
	</button>

<separator  />
<button mold="bs" class="btn-primary">Action</button>

<separator  />
<button mold="bs" >Action</button>
<separator  />
<textbox class="form-control" placeholder="Username" />
<intbox class="form-control" placeholder="Username" />
<spinner  />
<separator  />
<a class="btn btn-primary btn-lg">
			Learn more
		</a>


		<panel title="Panel Primary" sclass="panel-primary">
        	<panelchildren>Panel content</panelchildren>
        </panel>
        <hbox>
<div class="btn-group">
	<button mold="bs" class="btn-success" iconSclass="z-icon z-icon-save">保存</button>
	<button mold="bs" class="btn-info" iconSclass="z-icon z-icon-edit">修改</button>
	<button mold="bs" class="btn-danger" iconSclass="z-icon z-icon-delete">删除</button>
	<button mold="bs" class="btn-info" iconSclass="z-icon z-icon-refresh">刷新</button>
	<button mold="bs" class="btn-info" iconSclass="z-icon z-icon-copy">复制</button>
	<button mold="bs" class="btn-info" iconSclass="z-icon z-icon-paste">粘贴</button>
</div>
<div class="btn-group">
	<button mold="bs" class="btn-primary">Left</button>
	<button mold="bs" class="btn-primary">Middle</button>
	<button mold="bs" class="btn-primary">Right</button>
	<button mold="bs" class="btn-primary">Right</button>
	<button mold="bs" class="btn-primary">Right</button>
	<button mold="bs" class="btn-primary">Right</button>
</div>
</hbox>

</window>