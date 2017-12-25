package com.ourway.base.zk.sys.bandbox;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 SysObjectBandbox : <p>
 * <p>说明:业务类选择bandbox，用于工作流定义界面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/7/19 22:49
 * </pre>
 */
public class SysObjectBandbox extends BaseBandbox {
    private static final long serialVersionUID = -7917484960028594428L;
    public static final String API_URL = "/sysObjectApi/listObjByName";
    private BaseListbox lb = new BaseListbox();// 列表
    private Bandpopup pup = new Bandpopup();
    private List<Object> lbs = new ArrayList<Object>();
    private BaseTextbox className = new BaseTextbox();// 查询条件
    private Map<String, Object> dbClickVals;//双击选中的对象


    @Override
    public void onCreate() {
        setAutodrop(false);
        addEventListener(Events.ON_OK, new OpenCusEvent());
        addEventListener(Events.ON_OPEN, new OpenCusEvent());
        addEventListener(Events.ON_BLUR, new OpenCusEvent());
        initFilterCon();
        initHead();
        if (!TextUtils.isEmpty(this.getValue())) {
            // 如果有初始值，则显示初始值
            showDefault(this.getValue());
        } else
            this.setPlaceholder(I18nUtil.getLabelContent(ERCode.FLOW_OBJ_SEL));
    }

    @Override
    public void showDefault(Object obj) {
        if (!TextUtils.isEmpty(obj)) {
            List<Object> arrayList = new ArrayList<Object>();
            List<Map<String, Object>> datas = filterFromApi(obj.toString());
            if (null != datas && datas.size() > 0) {
                setDbClickVals(datas.get(0));
            }
        }
    }

    public Map<String, Object> getDbClickVals() {
        return dbClickVals;
    }

    public void setDbClickVals(Map<String, Object> dbClickVals) {
        this.dbClickVals = dbClickVals;
        diplayRelateVal(dbClickVals);
    }


    private void diplayRelateVal(Map<String, Object> data) {
        Row row = (Row) this.getParent();
        List<Component> components = row.getChildren();
        //加入到选定的值中
        setText(getVal(data, "className") + "");
        List<Map<String,Object>> objs = new ArrayList<Map<String,Object>>(1);
        objs.add(data);
        setSelVals(objs);

        for (Component component : components) {
            if (component instanceof BaseTextbox) {
                BaseTextbox textbox = (BaseTextbox) component;
                if (textbox.isReadonly()) {
                    if (null != getVal(data, textbox.getProperty())) {
                        textbox.setText(getVal(data, textbox.getProperty()) + "");
                    }
                }
            }
            if (component instanceof BaseDecimalbox) {
                BaseDecimalbox textbox = (BaseDecimalbox) component;
                if (textbox.isReadonly()) {
                    if (null != getVal(data, textbox.getProperty())) {
                        textbox.setText(getVal(data, textbox.getProperty()) + "");
                    }
                }
            }

        }
    }

    private Object getVal(Map<String, Object> data, String property) {
        String key = "";
        String[] args = property.split("\\.");
        if (args.length > 0)
            key = args[args.length - 1];
        else
            key = property;

        if (TextUtils.isEmpty(data.get(key))) {
            return "";
        } else
            return data.get(key);

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
        row.setSpans("3");
        Hbox hbox = new Hbox();
        hbox.setParent(row);
        className.setParent(hbox);
        className.setWidth("200px");
        BaseButton bt = new BaseButton();
        bt.setWidth("80px");
        bt.addEventListener(Events.ON_CLICK, new ingueryClickEvent());
        bt.setLabel(ERCode.FLOW_QUERY_LABEL);
        bt.setParent(hbox);

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

        lb.getItems().clear();
        lb.setStyle("height:400");
        lb.setMold("paging");
        lb.setPageSize(15);
        lb.setParent(pup);
        lb.setWidth("99%");
        lb.setSizedByContent(false);

        Listhead head = PageUtils.createListHead(lb);
        Listheader h1 = PageUtils.createListheader(lb, ERCode.FLOW_SUB_CLASSNAME);
        h1 = PageUtils.createListheader(lb, ERCode.FLOW_SUB_CLASSDESC);

    }

    //根据查询条件返回数据
    private List<Map<String, Object>> filterFromApi(String className) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("className", className);
        ResponseMessage mess = JsonPostUtils.executeAPI(params, API_URL);
        if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
            List<Map<String, Object>> records = (List<Map<String, Object>>) mess.getBean();
            return records;
        }
        return null;
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
        String keys = getText();
        List<Map<String, Object>> datas = filterFromApi(keys);
        if (null != datas && datas.size() > 0)
            initList(datas);
    }

    public void queryData() {
        lb.getItems().clear();
        List<Map<String, Object>> datas = filterFromApi(className.getText());
        if (null != datas && datas.size() > 0)
            initList(datas);
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
            cell.setLabel(null == cst.get("className") ? "" : cst.get("className").toString());
            cell = new Listcell();
            cell.setParent(item);
            cell.setLabel(null == cst.get("classDesc") ? "" : cst.get("classDesc").toString());
        }

    }

    public class ingueryClickEvent implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            queryData();
        }
    }

    // 打开bandbox搜索框时候触发的事件
    private class OpenCusEvent implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event.getName().equalsIgnoreCase(Events.ON_BLUR)) {
                queryData();
            }
            if (event instanceof OpenEvent) {
                OpenEvent oe = (OpenEvent) event;
                if (oe.isOpen()) {
                    loadData();
                    open();
                }
            }
            if (event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                if (keyEvent.getKeyCode() == 13) {
                    loadData();
                    open();
                }
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
            close();
        }
    }

    @Override
    public List<Map<String,Object>> getSelVals() {
        return selVals;
    }
}
