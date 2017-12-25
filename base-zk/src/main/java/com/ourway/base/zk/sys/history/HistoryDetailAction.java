package com.ourway.base.zk.sys.history;

import com.ourway.base.utils.*;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.RenderUtils;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;
import sun.awt.TextureSizeConstraining;

import java.util.*;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class HistoryDetailAction extends BaseWindow {
    protected Log info = LogFactory.getLog(HistoryDetailAction.class);
    private static final String HISTORY_URL = "/sysLogApi/detailLog";
    Div dataContent;
    Map<String, Object> subMap = null; //子表定义

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        dataContent = (Div) getFellowIfAny("dataContent");
        dataContent.setStyle("max-height:500px;overflow-y:auto;");
        Map map = event.getArg();
        if (null == map) {
            AlterDialog.alert("无参数");
            detach();
        }
        ppt = (Map<String, Object>) map;
        init(ppt.get("className").toString(), ppt.get("owid").toString());

    }

    private void init(String className, String owid) {
        PublicData data = PublicData.instantce();
        data.setMethod(HISTORY_URL);
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("className", className);
        ppt.put("owid", owid);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
                Map<String, Object> bean = (Map<String, Object>) mess.getBean();
                if (!TextUtils.isEmpty("mainTable")) {
                    displayGrid(I18nUtil.getLabelContent(ERCode.GRID_PRIMARY_TABLE), (List<Map<String, Object>>) bean.get("mainTable"), (List<Map<String, Object>>) bean.get("mainData"), dataContent);
                }
                //判断有没有子表
                if (!TextUtils.isEmpty(bean.get("subTable"))) {
                    subMap = (Map<String, Object>) bean.get("subTable");
//                    Map<String, Object> subData = (Map<String, Object>) bean.get("refData");
//                    Set<String> set = subMap.keySet();
//                    for (String s : set) {
//                        displayGrid(I18nUtil.getLabelContent(s), (List<Map<String, Object>>) subMap.get(s), (List<Map<String, Object>>) subData.get(s));
//                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayGrid(String name, List<Map<String, Object>> headers, List<Map<String, Object>> mainData, Component component) {
        Panel panel = new Panel();
        panel.setParent(component);
        panel.setTitle(I18nUtil.getLabelContent(name));
        Panelchildren pc = new Panelchildren();
        pc.setParent(panel);
        BaseGrid baseGrid = new BaseGrid();
        baseGrid.setParent(pc);
        baseGrid.setSizedByContent(true);
        baseGrid.setSpan(true);
        List<String> headMaps = new ArrayList<String>();//待取得属性
        Map<String, String> transFerMap = new HashMap<String, String>();//字段转换设置
        //定义表头
        Columns cls = new Columns();
        cls.setParent(baseGrid);
        cls.setSizable(true);
        Column col = new Column();
        col.setAlign("center");
        col.setParent(cls);
        col.setWidth("40px");
        col.setContext("");
        Column cl = new Column();
        cl.setLabel(I18nUtil.getLabelContent(ERCode.GRID_INDEXNO));
        cl.setParent(cls);
        cl = new Column();
        cl.setLabel(I18nUtil.getLabelContent(ERCode.GRID_OPERATER));
        cl.setParent(cls);
        cl = new Column();
        cl.setLabel(I18nUtil.getLabelContent(ERCode.GRID_OPERATERTIME));
        cl.setParent(cls);
        cl.setWidth("140px;");
        cl = new Column();
        cl.setLabel(I18nUtil.getLabelContent(ERCode.GRID_OPERATERTYPE));
        cl.setParent(cls);
        for (Map<String, Object> head : headers) {
            cl = new Column();
            cl.setLabel(I18nUtil.getLabelContent(head.get("attributeLabelId").toString()));
            cl.setParent(cls);
            headMaps.add(head.get("attributeName").toString());
            if (!TextUtils.isEmpty(head.get("attributeDisplayclass"))) {
                transFerMap.put(head.get("attributeName").toString(), head.get("attributeDisplayclass").toString());
            }
        }
        Rows rows = baseGrid.getHeaderRows();
        //数据展现
        int indexno = 1;
        for (Map<String, Object> data : mainData) {
            switch ((Integer) data.get("logType")) {
                case 1://新增，直接展现这一条
                    Row row = new Row();
                    row.setParent(rows);
                    if (!TextUtils.isEmpty(data.get("subLogs"))) {
                        Detail detail = new Detail();
                        detail.setParent(row);
                        detail.setOpen(false);
                        detail.setAttribute("subDatas", data.get("subLogs"));
                        detail.addEventListener(Events.ON_OPEN, this);
                    } else {
                        BaseTextbox label = TemplateUtils.doCreateInGridTextbox();
                        label.setText("");
                        label.setParent(row);
                    }
                    BaseTextbox label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(indexno + "");
                    label.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(data.get("logOperateName") + "");
                    label.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(data.get("logOperateTime") + "");
                    label.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(I18nUtil.getLabelContent(ERCode.GRID_OPERATE_ADD) + "");
                    label.setParent(row);
                    Map<String, Object> dataVal = (Map<String, Object>) data.get("logNewValue");
                    for (String s : headMaps) {
                        label = TemplateUtils.doCreateInGridTextbox();
                        if (!TextUtils.isEmpty(transFerMap.get(s))) {
                            //进行值转换
                            transferByClass(transFerMap, label, dataVal, s);
                        } else
                            label.setText(dataVal.get(s) + "");
                        label.setStyle("color:red");
                        label.setParent(row);
                    }
                    break;
                case 2://修改界面
                    row = new Row();
                    row.setParent(rows);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText("");
                    label.setParent(row);
                    //Cell cell = new Cell();
                    //cell.setRowspan(2);
                    //cell.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(indexno + "");
                    label.setParent(row);

                    //cell = new Cell();
                   // cell.setRowspan(2);
                    //cell.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(data.get("logOperateName") + "");
                    label.setParent(row);

//                    cell = new Cell();
//                    cell.setRowspan(2);
//                    cell.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(data.get("logOperateTime") + "");
                    label.setParent(row);

//                    cell = new Cell();
//                    cell.setRowspan(2);
//                    cell.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(I18nUtil.getLabelContent(ERCode.GRID_OPERATE_UPDATE) + "");
                    label.setParent(row);

                    dataVal = (Map<String, Object>) data.get("logOldValue");
                    for (String s : headMaps) {
                        label = TemplateUtils.doCreateInGridTextbox();
                        if (!TextUtils.isEmpty(transFerMap.get(s))) {
                            //进行值转换
                            transferByClass(transFerMap, label, dataVal, s);
                        } else
                            label.setText(dataVal.get(s) + "");
                        label.setParent(row);
                    }
                    Map<String, Object> newVal = (Map<String, Object>) data.get("logNewValue");
                    Row row1 = new Row();
                    row1.setParent(rows);
                    if (!TextUtils.isEmpty(data.get("subLogs"))) {
                        Detail detail = new Detail();
                        detail.setParent(row1);
                        detail.setOpen(false);
                        detail.setAttribute("subDatas", data.get("subLogs"));
                        detail.addEventListener(Events.ON_OPEN, this);
                    } else {
                        label = TemplateUtils.doCreateInGridTextbox();
                        label.setText("");
                        label.setParent(row1);
                    }
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText("");
                    label.setParent(row1);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText("");
                    label.setParent(row1);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText("");
                    label.setParent(row1);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText("");
                    label.setParent(row1);
                    for (String s : headMaps) {
                        label = TemplateUtils.doCreateInGridTextbox();
                        if (!TextUtils.isEmpty(transFerMap.get(s))) {
                            //进行值转换
                            transferByClass(transFerMap, label, dataVal, s);
                        } else
                            label.setText(dataVal.get(s) + "");
                        if (isDiff(newVal.get(s), dataVal.get(s)))
                            label.setStyle("color:red");
                        label.setParent(row1);
                    }

                    break;
                case 3:
                    row = new Row();
                    row.setParent(rows);
                    if (!TextUtils.isEmpty(data.get("subLogs"))) {
                        Detail detail = new Detail();
                        detail.setParent(row);
                        detail.setOpen(false);
                        detail.setAttribute("subDatas", data.get("subLogs"));
                        detail.addEventListener(Events.ON_OPEN, this);
                    } else {
                        label = TemplateUtils.doCreateInGridTextbox();
                        label.setText("");
                        label.setParent(row);
                    }
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(indexno + "");
                    label.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(data.get("logOperateName") + "");
                    label.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(data.get("logOperateTime") + "");
                    label.setParent(row);
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setText(I18nUtil.getLabelContent(ERCode.GRID_OPERATE_DELTE) + "");
                    label.setParent(row);
                    dataVal = (Map<String, Object>) data.get("logOldValue");
                    for (String s : headMaps) {
                        label = TemplateUtils.doCreateInGridTextbox();
                        if (!TextUtils.isEmpty(transFerMap.get(s))) {
                            //进行值转换
                            transferByClass(transFerMap, label, dataVal, s);
                        } else
                            label.setText(dataVal.get(s) + "");
                        label.setStyle("color:red");
                        label.setParent(row);
                    }
                    break;
            }
            Row blankRow = new Row();
            blankRow.setParent(rows);
            blankRow.setStyle("height:8px;");

            indexno++;
        }

    }

    //如果存在列需要转换的，数据转换成现实。则调用改方法。
    private void transferByClass(Map<String, String> transFerMap, BaseTextbox label, Map<String, Object> dataVal, String s) {
        try {
            String cls = "";
            String params = "";
            if (transFerMap.get(s).indexOf("?") > 0) {
                String[] _kjformats = transFerMap.get(s).split("\\?");
                cls = _kjformats[0];
                params = _kjformats[1];
            } else
                cls = transFerMap.get(s);
            RenderUtils renderUtils = (RenderUtils) Class.forName(cls).newInstance();
            label.setValue(doRemoveNull(renderUtils.rendar(dataVal.get(s), params, label)));
        } catch (Exception e) {
            label.setValue("");
        }
    }

    //去除内容是null的显示
    private String doRemoveNull(Object obj) {
        if (TextUtils.isEmpty(obj))
            return "";
        else
            return obj.toString();
    }

    private boolean isDiff(Object o1, Object o2) {
        if (!TextUtils.isEmpty(o1) && !TextUtils.isEmpty(o2) && o1.toString().compareTo(o2.toString()) != 0) {
            return true;
        }
        if (!TextUtils.isEmpty(o1) && TextUtils.isEmpty(o2))
            return true;
        if (TextUtils.isEmpty(o1) && !TextUtils.isEmpty(o2))
            return true;
        return false;
    }

    @Override
    public void onEvent(Event event) throws Exception {
        if (event.getTarget() instanceof Detail) {
            doDetailClick(event);
        }

    }

    private void doDetailClick(Event event) {
        Detail detail = (Detail) event.getTarget();
        if (!detail.isOpen())
            return;
        detail.getChildren().clear();
        Map<String, Object> subData = (Map<String, Object>) detail.getAttribute("subDatas");
        Set<String> set = subMap.keySet();
        Vbox vbox = new Vbox();
        vbox.setParent(detail);
        for (String s : set) {
            displayGrid(I18nUtil.getLabelContent(s), (List<Map<String, Object>>) subMap.get(s), (List<Map<String, Object>>) subData.get(s), vbox);
        }
    }


}
