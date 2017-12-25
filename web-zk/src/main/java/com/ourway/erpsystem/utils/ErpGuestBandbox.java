package com.ourway.erpsystem.utils;

import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.syszk.utils.TextUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.ext.Scopes;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * yo
 */
public class ErpGuestBandbox extends BaseBandbox {
    private static final long serialVersionUID = -7917484960028594428L;
    public static final String API_URL = "guestApi/list";
    private BaseListbox lb = new BaseListbox();// 列表
    private Bandpopup pup = new Bandpopup();
    private List<Object> lbs = new ArrayList<Object>();
    private BaseTextbox guestId = new BaseTextbox();// 查询条件 ，工号
    private BaseTextbox name = new BaseTextbox();// 查询条件，姓名
    private Map<String, Object> dbClickVals;//双击选中的对象

    private EventListener _onCheckBack;// 选择返回事件

    @Override
    public void onCreate() {
        lb.setMultiple(false);
        this.addEventListener(Events.ON_OPEN, new OpenCusEvent());
        initFilterCon();
        initList();
        initHead();
        if (!TextUtils.isEmpty(this.getValue())) {
            // 如果有初始值，则显示初始值
            showDefault(this.getValue());
        } else
            this.setText("请选择");
    }

    public void setCheckBack(final String evl) {
        if (TextUtils.isEmpty(evl)) {
            this._onCheckBack = null;
            return;
        }
        this._onCheckBack = new EventListener() {
            @Override
            public void onEvent(Event event) {
                close();
                getPage().interpret("java", evl, Scopes.getCurrent(getPage())); //
                // 调用java方法
            }
        };
    }

    @Override
    public void showDefault(Object obj) {

    }

    public Map<String, Object> getDbClickVals() {
        return dbClickVals;
    }

    public void setDbClickVals(Map<String, Object> dbClickVals) {
        this.dbClickVals = dbClickVals;
    }

    /**
     * <p>
     * 初始化列表
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: CustomerBandBox.java,v 0.1 2012-3-12 上午08:53:13 Jack Exp $
     */
    public void initList() {
    }

    // 显示初始数据
    public void showDefault(String id) {


    }

    public void setBoxValue(String lb, String v) {
        this.setValue(lb);
        this.close();
    }

    /**
     * <p>
     * 初始化过滤条件
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DeviceInStorageBandBox.java,v 0.1 2012-3-7 下午01:55:28 Jack
     * Exp $
     */
    public void initFilterCon() {
        pup.setParent(this);
        pup.setWidth("500px");

        BaseGrid grid = new BaseGrid();
        grid.setParent(pup);
        Rows rows = new Rows();
        rows.setParent(grid);
        Row row = new Row();
        row.setParent(rows);
        row.setSpans("1,2");
        Div div1 = new Div();
        div1.setParent(row);
        div1.setAlign("right");
        BaseLabel lbel1 = new BaseLabel();
        lbel1.setParent(div1);
        lbel1.setValue("工号：");
        guestId.setParent(row);
        guestId.setWidth("200px");

        row = new Row();
        row.setParent(rows);
        row.setSpans("1,2");
        div1 = new Div();
        div1.setParent(row);
        div1.setAlign("right");
        lbel1 = new BaseLabel();
        lbel1.setParent(div1);
        lbel1.setValue("姓名：");
        name.setParent(row);
        name.setWidth("200px");

        row = new Row();
        row.setParent(rows);
        row.setSpans("3");
        div1 = new Div();
        div1.setParent(row);
        div1.setAlign("center");
        BaseButton bt = new BaseButton();
        bt.setWidth("80px");
        bt.addEventListener(Events.ON_CLICK, new ingueryClickEvent());
        bt.setLabel("查 询");
        bt.setParent(div1);
    }

    /**
     * <p>
     * 处理化列表头
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DeviceInStorageBandBox.java,v 0.1 2012-3-7 下午01:55:12 Jack
     * Exp $
     */
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
        // Button bt = new Button();
        // bt.setParent(row);
        // bt.setId("xz");
        // bt.addEventListener(Events.ON_CLICK, _onCheckBack);
        // bt.setLabel("确认选择");
        // bt = new Button();
        // bt.setParent(row);
        // bt.addEventListener(Events.ON_CLICK, _allCheckBack);
        // bt.setLabel("全部选择");
        // bt.setId("qbxz");
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
        Listheader h1 = new Listheader("客户编号");
        h1.setParent(head);
        h1 = new Listheader("客户姓名");
        h1.setParent(head);

    }

    // 点击查询的时候载入数据

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
        Map<String, Object> params = new HashMap<String, Object>();
        if (!com.ourway.base.utils.TextUtils.isEmpty(guestId.getText()))
            params.put("guestId", guestId.getText().trim());
        if (!com.ourway.base.utils.TextUtils.isEmpty(name.getText()))
            params.put("name", name.getText().trim());
        ResponseMessage mess = JsonPostUtils.executeAPI(params, API_URL);
        if (null != mess.getBean()) {
            List<Map<String, Object>> datas = (List<Map<String, Object>>) mess.getBean();
            initList(datas);
        }
    }


    private void initList(List<Map<String, Object>> datas) {
        for (Map<String, Object> cst : datas) {
            Listitem item = new Listitem();
            item.setValue(cst);
            item.setParent(lb);
            item.addEventListener(Events.ON_DOUBLE_CLICK, new ItemClickEvent());
            // PartNo
            Listcell cell = new Listcell();
            cell.setParent(item);
            cell.setLabel(null == cst.get("guestId") ? "" : cst.get("guestId").toString());
            cell = new Listcell();
            cell.setParent(item);
            cell.setLabel(null == cst.get("name") ? "" : cst.get("name").toString());
        }

    }

    public class ingueryClickEvent implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            loadData();
        }
    }

    // 打开bandbox搜索框时候触发的事件
    private class OpenCusEvent implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            OpenEvent oe = (OpenEvent) event;
            if (oe.isOpen()) {
                loadData();
            }
        }
    }

    // 双击选中
    private class ItemClickEvent implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            Listitem item = (Listitem) event.getTarget();
            Map<String, Object> itemval = (Map<String, Object>) item.getValue();
            setDbClickVals(itemval);
            if (null != item.getValue()) {
                setBoxValue(null == itemval.get("guestId") ? "" : itemval.get("guestId").toString(), null == itemval.get("name") ? "" : itemval.get("name").toString());
            } else
                setBoxValue("---请选择---", "");
        }
    }

    @Override
    public List<Map<String,Object>> getSelVals() {
        return selVals;
    }
}
