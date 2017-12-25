package com.ourway.base.zk.template.utils;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.sys.page.*;
import com.ourway.base.zk.utils.PageUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zul.*;

import java.util.*;

/**
 * <p>方法: 页面功能选择 </p>
 * <ul>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/23 22:45  </li>
 * </ul>
 */
public class ComponentsBandbox extends BaseBandbox {
    private BaseListbox dataList = new BaseListbox();// 列表数据，双击选中
    private Bandpopup bandpopup = new Bandpopup(); //bandobx弹出页面
    BaseTextbox kjName;
    BaseTextbox kjAttribute;
    BaseListbox kjType;//控件类型
    List<Map<String, Object>> datas = null;
    Map<String, String> typeMap = new HashMap<String, String>();//控件类型

    public List<Map<String, Object>> getSelItems() {
        Set<Listitem> items = dataList.getSelectedItems();
        if (null == items || items.size() <= 0)
            return null;
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        for (Listitem data : items) {
            datas.add((Map<String, Object>) data.getValue());
        }
        return datas;
    }

    @Override
    public void onCreate() {
        this.addEventListener(Events.ON_OPEN, new EventHandle());
        initList();
        createListbox();
        if (!TextUtils.isEmpty(this.getValue())) {
            showDefault(this.getValue());
        } else
            this.setText("---请选择---");
    }

    private void initList() {
        initFilterContent();
        dataList.getItems().clear();
        dataList.setMold("paging");
        dataList.setPageSize(20);
        dataList.setParent(bandpopup);
        dataList.setWidth("99%");
        dataList.setCheckmark(true);
        dataList.setMultiple(true);
        dataList.setSizedByContent(false);
        bandpopup.setParent(this);
        bandpopup.setWidth("500px");
    }

    private void initFilterContent() {
        BaseGrid grid = new BaseGrid();
        grid.setParent(bandpopup);
        Rows rows = new Rows();
        rows.setParent(grid);


        Row row = new Row();
        row.setParent(rows);
        row.setSpans("1,2");
        BaseLabel _label = new BaseLabel();
        _label.setValue("名称");
        _label.setParent(row);

        kjName = new BaseTextbox();
        kjName.setParent(row);

        row = new Row();
        row.setParent(rows);
        row.setSpans("1,2");
        _label = new BaseLabel();
        _label.setValue("属性");
        _label.setParent(row);

        kjAttribute = new BaseTextbox();
        kjAttribute.setParent(row);

        row = new Row();
        row.setParent(rows);
        row.setSpans("1,2");
        _label = new BaseLabel();
        _label.setValue("按钮");
        _label.setParent(row);

        kjType = new BaseListbox();
        kjType.setMold("select");
        kjType.setParent(row);
        Listitem item = new Listitem("---所有控件---", "");
        item.setSelected(true);
        item.setParent(kjType);
        item = new Listitem("---显示按钮---", "1");
        item.setParent(kjType);
        item = new Listitem("---显示其它---", "2");
        item.setParent(kjType);

        //第二行
        row = new Row();
        row.setParent(rows);
        row.setSpans("3");

        BaseButton button = new BaseButton();
        button.setLabel("查询");
        button.setParent(row);
        button.addEventListener(Events.ON_CLICK, new EventHandle());

    }

    public BaseWindow getWindow(Component component) {
        if (component instanceof BaseWindow)
            return (BaseWindow) component;
        else
            return getWindow(component.getParent());
    }

    public BaseWindow getObjParent() {
        return getWindow(this);
    }


    @Override
    public void showDefault(Object val) {
        if (TextUtils.isEmpty(val)) {
            setText("---请选择---");
            setValue("---请选择---");
            return;
        }

    }


    private void createListbox() {

        Listhead head = PageUtils.createListHead(dataList);
        head.setSizable(true);
        Listheader h1 = PageUtils.createListheader(dataList, "中文名");
        h1 = PageUtils.createListheader(dataList, "属性名");
        h1 = PageUtils.createListheader(dataList, "控件类型");
        h1 = PageUtils.createListheader(dataList, "数据类型");
    }

    private void displayDatas() {

        BaseWindow _window = getObjParent();
        if (_window instanceof ComponentButtonSelAction) {
            datas = ((ComponentButtonSelAction) _window).listNotUseComponents();
        }
        if (_window instanceof ComponentFormSelAction) {
            datas = ((ComponentFormSelAction) _window).listNotUseComponents();
        }
        if (_window instanceof ComponentGridSelAction) {
            datas = ((ComponentGridSelAction) _window).listNotUseComponents();
        }
        if (_window instanceof ComponentBandboxGridSelAction) {
            datas = ((ComponentBandboxGridSelAction) _window).listNotUseComponents();
        }
        if (_window instanceof TabComponentButtonSelAction) {
            datas = ((TabComponentButtonSelAction) _window).listNotUseComponents();
        }
        if (_window instanceof ComponentTabFormSelAction) {
            datas = ((ComponentTabFormSelAction) _window).listNotUseComponents();
        }
        if (null == datas)
            return;
        //排序显示
        Collections.sort(datas, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return o1.get("kjName").toString().compareTo(o2.get("kjName").toString());
            }
        });


        displayData2Row(datas);

    }

    private void displayData2Row(List<Map<String, Object>> _datas) {
        dataList.getItems().clear();
        for (Map<String, Object> data : _datas) {
            Listitem listitem = PageUtils.createListItem(dataList);
            listitem.setValue(data);
            Listcell cell = PageUtils.createListcell(listitem, data.get("kjName"));
            cell = PageUtils.createListcell(listitem, data.get("kjAttribute"));
            cell = PageUtils.createListcell(listitem, data.get("kjTypeLabel"));
            cell = PageUtils.createListcell(listitem, data.get("kjDataTypeLabel"));
        }
    }

    private void filter() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        String name = kjName.getText();
        String attr = kjAttribute.getText();
        String _kjlx = kjType.getSelectedItem().getValue();
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(attr)&&TextUtils.isEmpty(_kjlx)) {
            displayData2Row(datas);
            return;
        }
        if (null != datas && datas.size() > 0) {
            for (Map<String, Object> map : datas) {
                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(map.get("kjName")) && map.get("kjName").toString().contains(name)) {
                        result.add(map);
                        continue;
                    }
                }
                if (!TextUtils.isEmpty(attr)) {
                    if (!TextUtils.isEmpty(map.get("kjAttribute")) && map.get("kjAttribute").toString().contains(attr)) {
                        result.add(map);
                        continue;
                    }
                }
                if (!TextUtils.isEmpty(_kjlx)) {
                    if (_kjlx.equalsIgnoreCase("1")) {
                        if (map.get("kjTypeLabel").toString().equalsIgnoreCase("Button"))
                            result.add(map);
                    } else {
                        if (!map.get("kjTypeLabel").toString().equalsIgnoreCase("Button"))
                            result.add(map);
                    }
                }
            }
        }
        displayData2Row(result);
    }

    private class EventHandle implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event instanceof OpenEvent) {
                OpenEvent openEvent = (OpenEvent) event;
                if (openEvent.isOpen()) {
                    displayDatas();
                }

            }
            if (event.getTarget() instanceof BaseButton) {
                filter();
            }
        }
    }

    @Override
    public List<Map<String, Object>> getSelVals() {
        if (null == selVals)
            selVals = new ArrayList<Map<String, Object>>();
        return selVals;
    }
}
