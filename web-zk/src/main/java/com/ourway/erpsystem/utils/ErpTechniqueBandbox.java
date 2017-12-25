package com.ourway.erpsystem.utils;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.erpsystem.ERPTipConstants;
import com.ourway.syszk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.Scopes;
import org.zkoss.zul.*;

import javax.xml.soap.Text;
import java.util.*;


public class ErpTechniqueBandbox extends BaseBandbox {
    private static final long serialVersionUID = -7917484960028594428L;
    private BaseListbox lb = new BaseListbox();// 列表
    private Bandpopup pup = new Bandpopup();
    private List<Object> lbs = new ArrayList<Object>();
    private String selLabel = "";

    @Override
    public void onCreate() {
        lb.setMultiple(true);
        lb.setCheckmark(true);
        setReadonly(true);
        this.addEventListener(Events.ON_OPEN, new OpenCusEvent());
        initFilterCon();
        initList();
        initHead();

        if (!TextUtils.isEmpty(this.getValue())) {
            showDefault(this.getValue());
        } else
            this.setText(I18nUtil.getLabelContent(ERPTipConstants.PLEASE_SEL));
    }


    @Override
    public void showDefault(Object obj) {
        if (TextUtils.isEmpty(obj))
            return;
        String vals = obj.toString();
        if (vals.indexOf(",") > 0) {
            if (null == getSelVals())
                setSelVals(new ArrayList<Map<String,Object>>());
            String[] _vals = vals.split("\\,");
//            for (String val : _vals) {
//                if (!getSelVals().contains(val))
//                    getSelVals().add(val);
//            }
        }
        loadData();
    }


    public void initFilterCon() {
        pup.setParent(this);
        pup.setWidth("200px");
    }


    public void initHead() {
        // 这里加个按钮，点击选中所有
        BaseGrid grid = new BaseGrid();
        grid.setWidth("99%");
        grid.setParent(pup);
        Rows rows = new Rows();
        rows.setParent(grid);
        Row row = new Row();
        row.setParent(rows);
        row.setAlign("left");

        lb.getItems().clear();
        lb.setStyle("height:400");
        lb.setMold("paging");
        lb.setPageSize(15);
        lb.setParent(pup);
        lb.setWidth("99%");
        lb.setSizedByContent(false);

        Listhead head = lb.getListhead();
        if (null == head)
            head = new Listhead();
        head.setParent(lb);
        Listheader h1 = new Listheader(I18nUtil.getLabelContent(ERPTipConstants.ERP_DIC_TANK_TECHONIC_NAME));
        h1.setParent(head);
    }

    public void initList() {
    }

    /**
     * <p>
     * 加载数据
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DeviceInStorageBandBox.java,v 0.1 2012-3-7 下午01:55:04 Jack
     * Exp $
     */
    public void loadData() {
        lb.getItems().clear();
        List<Map<String, Object>> datas = DicUtil.listDic(1004, "b.dicVal1");
        displayList(datas);

    }


    private void displayList(List<Map<String, Object>> datas) {
        if (null == datas || datas.size() <= 0)
            return;
        for (Map<String, Object> cst : datas) {
            Listitem item = new Listitem();
            item.setValue(cst);
            item.setParent(lb);
            item.addEventListener(Events.ON_CLICK, new OpenCusEvent());
            Listcell cell = new Listcell();
            cell.setParent(item);
            cell.setLabel(I18nUtil.getLabelContent(cst.get("dicVal2").toString()));
            //判断是否选中
            if (null != getSelVals() && getSelVals().contains(cst.get("owid").toString())) {
                item.setSelected(true);
                selLabel += cell.getLabel();
                if (!TextUtils.isEmpty(cst.get("dicVal3")) && cst.get("dicVal3").toString().equals("1")) {
                    Component comp = ComponentUtils.getWindow(this).getFellowIfAny("mainTableGrid_heatTyep");
                    if (null != comp && comp instanceof BaseListbox) {
                        BaseListbox lb = (BaseListbox) comp;
                        lb.setDisabled(false);
                    }
                }
            }
            if (!TextUtils.isEmpty(selLabel))
                setText(selLabel);
        }
    }

    // 打开bandbox搜索框时候触发的事件
    private class OpenCusEvent implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event instanceof OpenEvent) {
                OpenEvent oe = (OpenEvent) event;
                if (null == lb.getItems() || lb.getItems().size() <= 0)
                    loadData();
            }
            if (event.getTarget() instanceof Listitem) {
                checkSelJrfs();
//                AlterDialog.alert("checked");
            }
        }
    }

    void checkSelJrfs() {
        Set<Listitem> listitems = lb.getSelectedItems();
        boolean flag = false;
        for (Listitem listitem : listitems) {
            if (null != listitem.getValue()) {
                Map<String, Object> data = (Map<String, Object>) listitem.getValue();
                if (!TextUtils.isEmpty(data.get("dicVal3")) && data.get("dicVal3").toString().equals("1")) {
                    flag = true;
                }
            }
        }
//        if(flag){
        Component comp = ComponentUtils.getWindow(this).getFellowIfAny("mainTableGrid_heatTyep");
        if (null != comp && comp instanceof BaseListbox) {
            BaseListbox lb = (BaseListbox) comp;
            if(!flag)
                lb.setSelectedIndex(0);
            lb.setDisabled(!flag);
        }
//        }
    }

    @Override
    public List<Map<String,Object>> getSelVals() {
        if (null == selVals)
            selVals = new ArrayList<Map<String,Object>>();
        Set<Listitem> set = lb.getSelectedItems();
        if (null != set && set.size() > 0) {
            for (Listitem listitem : set) {
                Map<String, Object> data = (Map<String, Object>) listitem.getValue();
//                if (!selVals.contains(data.get("owid").toString()))
//                    selVals.add(data.get("owid").toString());
            }
        }
        return selVals;
    }
}
