<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %><%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %><%@ taglib uri="http://www.zkoss.org/dsp/web/theme" prefix="t" %>.z-paging{height:32px;border-bottom:1px solid #cfcfcf;padding:4px 10px 3px;background:#fafafa;position:relative}.z-paging-os{border-bottom:0;padding-bottom:4px}.z-paging ul{display:inline-block;margin:0;padding:0}.z-paging ul>li{display:inline}.z-paging-button{font-family:Arial,Sans-serif;font-size:12px;font-weight:normal;font-style:normal;color:#2184ba;display:inline-block;min-width:24px;height:24px;border:1px solid #cfcfcf;${t:borderRadius('3px') };margin-right:6px;padding:4px 0;line-height:14px;${t:gradient('ver','#fefefe 0%; #eeeeee 100%') };text-align:center;vertical-align:top;cursor:pointer;text-decoration:none;white-space:nowrap}.z-paging-button:hover{border-color:#a9a9a9;${t:boxShadow('0 0 2px rgba(0, 0, 0, 0.34)') }}.z-paging-button:active{border-color:#a9a9a9 #cfcfcf;${t:gradient('ver','#eeeeee 0%; #fefefe 100%') }}.z-paging-button[disabled]{color:#aaa;opacity:.6;filter:alpha(opacity=60);cursor:default}.z-paging-button[disabled]:hover{border-color:#cfcfcf;${t:boxShadow('0 0 0 rgba(0, 0, 0, 0)') }}.z-paging-button[disabled]:active{border-color:#cfcfcf;${t:gradient('ver','#fefefe 0%; #eeeeee 100%') }}.z-paging-button[disabled] .z-paging-icon,.z-paging-button[disabled] .z-paging-icon:hover{color:#aaa}.z-paging .z-paging-icon{font-size:18px;color:#2184ba;line-height:14px}.z-paging-noborder{border-color:transparent;background:0;filter:progid:DXImageTransform.Microsoft.gradient(enabled=false)}.z-paging-noborder:hover{border-color:transparent;${t:boxShadow('0 0 0 rgba(0, 0, 0, 0)') };text-decoration:underline}.z-paging-noborder:active{border-color:transparent;background:0;filter:progid:DXImageTransform.Microsoft.gradient(enabled=false)}.z-paging-selected{color:#363636;border-color:#a9a9a9;background:0;filter:progid:DXImageTransform.Microsoft.gradient(enabled=false);background:#e6e6e6}.z-paging-selected:hover{border-color:#a9a9a9;${t:boxShadow('0 0 0 rgba(0, 0, 0, 0)') }}.z-paging-selected:active{border-color:#a9a9a9;background:0;filter:progid:DXImageTransform.Microsoft.gradient(enabled=false);background:#e6e6e6}.z-paging-input{font-family:Arial,Sans-serif;font-size:12px;font-weight:normal;font-style:normal;color:#363636;height:24px;border:1px solid #cfcfcf;margin-left:6px;padding:3px 0;line-height:20px;vertical-align:baseline;<c:if test="${zk.ie == 8}">vertical-align:middle;</c:if>}.z-paging-text{font-family:Arial,Sans-serif;font-size:12px;font-weight:normal;font-style:normal;color:#363636;margin-right:12px}.z-paging-info{font-family:Arial,Sans-serif;font-size:12px;font-weight:normal;font-style:normal;color:#363636;padding:4px 0;position:absolute;top:4px;right:10px}