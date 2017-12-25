<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %><%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %><%@ taglib uri="http://www.zkoss.org/dsp/web/theme" prefix="t" %>.z-tree{border:1px solid #cfcfcf;overflow:hidden;zoom:1}.z-tree-header{width:100%;<c:if test="${zk.ie != 8}">${t:gradient('ver','#fefefe 0%; #eeeeee 100%') };</c:if>position:relative;overflow:hidden}.z-tree-header table{border-spacing:0}.z-tree-header table th,.z-tree-header table td{background-clip:padding-box;padding:0}.z-tree-header table th{text-align:inherit}.z-tree-header-border{border-bottom:1px solid #cfcfcf;margin-top:-1px;position:relative}.z-tree-body{position:relative;overflow:hidden}.z-tree-body table{border-spacing:0}.z-tree-body table th,.z-tree-body table td{background-clip:padding-box;padding:0}.z-tree-body table th{text-align:inherit}.z-tree-emptybody td{font-family:Arial,Sans-serif;font-size:12px;font-weight:normal;font-style:normal;color:#aaa;font-style:italic;text-align:center;height:1px}.z-tree-footer{border-top:1px solid #cfcfcf;background:#fafafa;overflow:hidden}.z-tree-footer table{border-spacing:0}.z-tree-footer table th,.z-tree-footer table td{background-clip:padding-box;padding:0}.z-tree-footer table th{text-align:inherit}.z-tree-icon,.z-tree-line{display:inline-block;width:16px;height:16px;line-height:16px;vertical-align:middle}.z-tree-icon{font-size:14px;color:#636363;text-align:center;cursor:pointer}.z-tree-icon:hover{color:#636363}.z-treecols th:first-child{border-left:none}.z-treecols th:first-child.z-treecols-border{border-left:1px solid #cfcfcf}.z-treecols-bar{border-left:1px solid #cfcfcf;border-bottom:1px solid #cfcfcf}.z-treecol{border-left:1px solid #cfcfcf;border-bottom:1px solid #cfcfcf;padding:0;<c:if test="${zk.ie != 8}">${t:gradient('ver','#fefefe 0%; #eeeeee 100%') };</c:if>background-clip:padding-box;position:relative;overflow:hidden;white-space:nowrap}.z-treecol-sort{cursor:pointer}.z-treecol-sort:hover{<c:if test="${zk.ie != 8}">${t:gradient('ver','#f2f9fe 0%; #d6f0fd 100%') };</c:if>}.z-treecol-sort .z-treecol-sorticon{color:#636363;position:absolute;top:-7px;left:50%}.z-treecol-sizing,.z-treecol-sizing .z-treecol-content{cursor:e-resize}.z-treerow{background:#fff}.z-treerow .z-treecell{overflow:hidden;cursor:pointer}.z-treerow:hover>.z-treecell{<c:if test="${zk.ie != 8}">${t:gradient('ver','#f2f9fe 0%; #d6f0fd 100%') };</c:if>background-clip:padding-box;position:relative}.z-treerow:hover>.z-treecell>.z-treecell-content{color:#636363}.z-treerow-checkable{display:inline-block;width:16px;height:16px;border:1px solid #8e8f8f;background:#f8f8f8;vertical-align:middle}.z-treerow-checkable.z-treerow-radio{${t:borderRadius('8px') }}.z-treerow-checkable .z-treerow-icon{display:none;cursor:default}.z-treerow.z-treerow-selected>.z-treecell{<c:if test="${zk.ie != 8}">${t:gradient('ver','#e5f4fb 0%; #d3edfa 100%') };</c:if>background-clip:padding-box;position:relative}.z-treerow.z-treerow-selected>.z-treecell>.z-treecell-content{color:#636363}.z-treerow.z-treerow-selected:hover>.z-treecell{<c:if test="${zk.ie != 8}">${t:gradient('ver','#cfebf8 0%; #a5daf5 100%') };</c:if>}.z-treerow.z-treerow-selected:hover>.z-treecell>.z-treecell-content{color:#636363}.z-treerow-selected>.z-treecell>.z-treecell-content>.z-treerow-checkable .z-treerow-icon{color:#2184ba;display:block;padding-left:1px;line-height:14px}.z-treerow-selected>.z-treecell>.z-treecell-content>.z-treerow-checkable .z-treerow-icon.z-icon-radio{width:8px;height:8px;${t:borderRadius('4px') };margin:3px;padding:0;background:#2184ba}.z-treerow.z-treerow-disabled *{color:#aaa!important;cursor:default!important}.z-treerow.z-treerow-disabled:hover>.z-treecell{background:0;filter:progid:DXImageTransform.Microsoft.gradient(enabled=false);position:relative}.z-treecol-content,.z-treecell-content,.z-treefooter-content{font-family:Arial,Sans-serif;font-size:12px;font-weight:normal;font-style:normal;color:#636363;padding:4px 5px;line-height:24px;overflow:hidden}.z-treecol-content{font-weight:bold;padding:4px 5px 3px;position:relative}.z-treecell-content{padding:4px 2px;line-height:14px}.z-treecell-text{vertical-align:middle}.z-tree-paging-top{border-bottom:1px solid #cfcfcf;overflow:hidden;width:100%}.z-tree-paging-bottom{border-top:1px solid #cfcfcf;overflow:hidden;width:100%}.z-tree-autopaging .z-treecell-content{height:24px;overflow:hidden}.ie8 .z-tree-header{background:#f5f5f5}.ie8 .z-treecol{position:static;background:#f5f5f5}.ie8 .z-treecol-hover,.ie8 .z-treecol-sort:hover{background:#e5f7ff}.ie8 .z-treerow:hover>.z-treecell{background:#e5f7ff}.ie8 .z-treerow.z-treerow-selected>.z-treecell{position:static;background:#e5f7ff}.ie8 .z-treerow.z-treerow-selected:hover>.z-treecell{position:static;background:#d9f2ff}.ie8 .z-treerow>.z-treecell>.z-treecell-content>.z-treerow-checkable{border-width:0;background:transparent}.ie8 .z-treerow>.z-treecell>.z-treecell-content>.z-treerow-checkable .z-treerow-icon{display:block;width:13px;height:13px;background:url(${c:encodeThemeURL('~./zul/img/common/check-sprite.gif')}) no-repeat;position:relative;top:2px;left:2px}.ie8 .z-treerow>.z-treecell>.z-treecell-content>.z-treerow-checkable .z-icon-check{background-position:0 0}.ie8 .z-treerow>.z-treecell>.z-treecell-content>.z-treerow-checkable .z-icon-check:before{display:none}.ie8 .z-treerow>.z-treecell>.z-treecell-content>.z-treerow-checkable .z-icon-radio{background-position:0 -13px}.ie8 .z-treerow-selected>.z-treecell>.z-treecell-content>.z-treerow-checkable .z-treerow-icon{display:block;width:13px;height:13px}.ie8 .z-treerow-selected>.z-treecell>.z-treecell-content>.z-treerow-checkable .z-icon-check{background-position:-26px 0}.ie8 .z-treerow-selected>.z-treecell>.z-treecell-content>.z-treerow-checkable .z-icon-radio{margin:0;background-position:-26px -13px}