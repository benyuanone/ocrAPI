<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %><%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %><%@ taglib uri="http://www.zkoss.org/dsp/web/theme" prefix="t" %>.z-hbox,.z-vbox{border-spacing:0}.z-hbox th,.z-vbox th,.z-hbox td,.z-vbox td{padding:0;background-clip:padding-box}.z-hbox th,.z-vbox th{text-align:inherit}.z-hbox-separator,.z-vbox-separator{margin:0;padding:0}.z-hbox-separator{width:.3em}.z-vbox-separator{height:.3em}.z-vbox-separator td{line-height:0}tr.z-splitter-outer>td{height:8px;max-height:8px}td.z-splitter-outer{width:8px;max-width:8px;padding:0}.z-splitter{border:1px solid #cfcfcf}.z-splitter-horizontal{width:8px;border-width:0 1px;${t:gradient('hor','#fdfdfd 0%; #f1f1f1 100%') };overflow:hidden;cursor:e-resize}.z-splitter-horizontal>.z-splitter-button{width:8px;height:30px;border-width:1px 0}.z-splitter-horizontal .z-splitter-icon{font-size:11px;position:absolute;top:9px;left:1px}.z-splitter-horizontal .z-splitter-icon.z-icon-ellipsis-vertical{font-size:10px;<c:if test="${zk.ie == 8}">font-size:14px;</c:if>top:-21px;left:2px;cursor:e-resize}.z-splitter-horizontal .z-splitter-icon.z-icon-ellipsis-vertical ~ .z-splitter-icon.z-icon-ellipsis-vertical{top:39px;<c:if test="${zk.ie == 8}">top:37px;</c:if>}.z-splitter-vertical{height:8px;border-width:1px 0;${t:gradient('ver','#fdfdfd 0%; #f1f1f1 100%') };overflow:hidden;cursor:s-resize}.z-splitter-vertical>.z-splitter-button{width:30px;height:8px;border-width:0 1px}.z-splitter-vertical .z-splitter-icon{font-size:10px;line-height:normal;position:absolute;top:-3px;left:11px}.z-splitter-vertical .z-splitter-icon.z-icon-ellipsis-horizontal{top:-2px;left:-16px;cursor:s-resize;<c:if test="${zk.ie == 8}">font-size:14px;top:-3px;</c:if>}.z-splitter-vertical .z-splitter-icon.z-icon-ellipsis-horizontal ~ .z-splitter-icon.z-icon-ellipsis-horizontal{left:36px;<c:if test="${zk.ie == 8}">left:38px;</c:if>}.z-splitter-button{color:#636363;display:inline-block;border:1px solid #cfcfcf;position:relative;vertical-align:top;cursor:pointer}.z-splitter-button-disabled{border:0}.z-splitter-vertical .z-splitter-button-disabled{cursor:s-resize}.z-splitter-horizontal .z-splitter-button-disabled{cursor:e-resize}.z-splitter-icon{opacity:.5;filter:alpha(opacity=50)}.z-splitter-nosplitter{cursor:default}.z-splitter-button:hover .z-icon-caret-up,.z-splitter-button:hover .z-icon-caret-down,.z-splitter-button:hover .z-icon-caret-right,.z-splitter-button:hover .z-icon-caret-left{opacity:1;filter:alpha(opacity=100)}