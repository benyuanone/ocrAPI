package com.ourway.base.zk.bandbox;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>方法: 页面功能选择 </p>
 * <ul>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/23 22:45  </li>
 * </ul>
 */
public class PageBandbox extends BaseBandbox {
    public static final String url = "/sysPageApi/listAllPages";
    private BaseListbox dataList = new BaseListbox();// 列表数据，双击选中
    private Bandpopup bandpopup = new Bandpopup(); //bandobx弹出页面
    BaseTextbox filter_pageName;
    BaseTextbox filter_pageCa;
    private PageBandbox instance;



    @Override
    public void onCreate() {
        this.addEventListener(Events.ON_OPEN, new EventHandle());
        createFilterGrid();
        createListbox();
        if (!TextUtils.isEmpty(this.getValue())) {
            showDefault(this.getValue());
        } else
            this.setText("---请选择---");
        instance = this;
    }

    //根据查询条件返回数据
    private List<Map<String, Object>> filterFromApi(List<FilterModel> models) {
        ResponseMessage mess = JsonPostUtils.executeAPI(models, url, 1, 100);
        if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
            List<Map<String, Object>> records = (List<Map<String, Object>>) mess.getBean();
            return records;
        }
        return null;
    }

    @Override
    public void showDefault(Object val) {
        if (TextUtils.isEmpty(val)) {
            setText(I18nUtil.getLabelContent(ERCode.BANDBOX_DEFAULT_TXT));
            setValue(I18nUtil.getLabelContent(ERCode.BANDBOX_DEFAULT_TXT));
            return;
        }
        List<Object> params = new ArrayList<Object>(1);
        params.add(val);
        FilterModel model = FilterModel.instance("owid", FilterModel.EQUALS, params);
        List<FilterModel> models = new ArrayList<FilterModel>(1);
        models.add(model);
        List<Map<String, Object>> records = filterFromApi(models);
        if (null != records && records.size() > 0) {
            Map<String, Object> data = records.get(0);
            setText(data.get("pageName").toString());
            getSelVals().add(data);
        }
    }

    /*设置为空*/
    public void setNull(){
        setText(I18nUtil.getLabelContent(ERCode.BANDBOX_DEFAULT_TXT));
        setValue(I18nUtil.getLabelContent(ERCode.BANDBOX_DEFAULT_TXT));
        setSelVals(new ArrayList<Map<String, Object>>());
    }

    //创建Grid的查询界面
    private void createFilterGrid() {
        bandpopup.setParent(this);
        bandpopup.setWidth("500px");
        BaseGrid baseGrid = PageUtils.createGrid(bandpopup);
        Rows rows = PageUtils.createRows(baseGrid);

        Row row = PageUtils.createRow(baseGrid, "1,3");
        Div div = PageUtils.createDiv(row, "right", "");
        BaseLabel label = PageUtils.createLabel(div, I18nUtil.getLabelContent("public.sys.pageName"), "");
        filter_pageName = PageUtils.createTextbox(row, "filter_pageQueryName", I18nUtil.getLabelContent("public.sys.pageName"));

        row = PageUtils.createRow(baseGrid, "1,3");
        div = PageUtils.createDiv(row, "right", "");
        label = PageUtils.createLabel(div, I18nUtil.getLabelContent("public.sys.pageCa"), "");
        filter_pageCa = PageUtils.createTextbox(row, "filter_pageQueryCa", I18nUtil.getLabelContent("public.sys.pageCa"));

        row = PageUtils.createRow(baseGrid, "4");
        div = PageUtils.createDiv(row, "center", "");
        Hbox hbox = PageUtils.createHbox(div, "");
        BaseButton filterBtn = PageUtils.createButton(hbox, "filterBtn", "", "public.sys.button.filter", new EventHandle());
        BaseButton resetBtn = PageUtils.createButton(hbox, "resetBtn", "", "public.sys.button.reset", new EventHandle());
    }

    private void createListbox() {
        dataList.getItems().clear();
        dataList.setStyle("height:400");
        dataList.setMold("paging");
        dataList.setPageSize(20);
        dataList.setParent(bandpopup);
        dataList.setWidth("99%");
        dataList.setSizedByContent(false);
        Listhead head = PageUtils.createListHead(dataList);
        Listheader h1 = PageUtils.createListheader(dataList, "pageNo");
        h1 = PageUtils.createListheader(dataList, "pageName");
        h1 = PageUtils.createListheader(dataList, "pageCa");
        h1 = PageUtils.createListheader(dataList, "pageTemplate");
    }

    private void displayDatas(List<Map<String, Object>> datas) {
        dataList.getItems().clear();
        if (null == datas)
            return;
        for (Map<String, Object> data : datas) {
            Listitem listitem = PageUtils.createListItem(dataList);
            listitem.addEventListener(Events.ON_DOUBLE_CLICK, new EventHandle());
            listitem.setValue(data);
            Listcell cell = PageUtils.createListcell(listitem, data.get("pageNo"));
            cell = PageUtils.createListcell(listitem, data.get("pageName"));
            cell = PageUtils.createListcell(listitem, data.get("pageCa"));
            cell = PageUtils.createListcell(listitem, data.get("pageTemplate"));
        }

    }

    private class EventHandle implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event instanceof OpenEvent) {
                //初始化所有数据
                OpenEvent openEvent = (OpenEvent) event;
                if (openEvent.isOpen()) {
                    filter_pageName.setText("");
                    filter_pageCa.setText("");
                    List<Map<String, Object>> datas = filterFromApi(null);
                    displayDatas(datas);
                }
            }
            if (event.getTarget() instanceof BaseButton) {
                BaseButton baseButton = (BaseButton) event.getTarget();
                if (baseButton.getId().equals("filterBtn")) {
                    List<FilterModel> models = new ArrayList<FilterModel>();
                    if (!TextUtils.isEmpty(filter_pageName.getText())) {
                        List<Object> _obj = new ArrayList<Object>(1);
                        _obj.add(filter_pageName.getText());
                        FilterModel model = FilterModel.instance("pageName", FilterModel.LIKE, _obj);
                        models.add(model);
                    }
                    if (!TextUtils.isEmpty(filter_pageCa.getText())) {
                        List<Object> _obj = new ArrayList<Object>(1);
                        _obj.add(filter_pageCa.getText());
                        FilterModel model = FilterModel.instance("pageCa", FilterModel.LIKE, _obj);
                        models.add(model);
                    }
                    List<Map<String, Object>> datas = filterFromApi(models);
                    displayDatas(datas);
                }
                if (baseButton.getId().equals("resetBtn")) {
                    filter_pageName.setText("");
                    filter_pageCa.setText("");
                }
            }

            if (event.getTarget() instanceof Listitem) {
                Listitem item = (Listitem) event.getTarget();
                Map<String, Object> _data = (Map<String, Object>) item.getValue();
                if(null==getSelVals())
                    setSelVals(new ArrayList< Map<String, Object>>());
                getSelVals().clear();
                getSelVals().add(_data);
                setText(_data.get("pageName").toString());
                //回写页面中的pageCa
                BaseTextbox baseInfo_pageCa = (BaseTextbox)getParentWindow(instance).getFellowIfAny("baseInfo_pageCa");
                if(null!=baseInfo_pageCa){
                    baseInfo_pageCa.setText(_data.get("pageCa").toString());
                }
                close();
            }
        }
    }

    public BaseWindow getParentWindow(Component component){
        if(component.getParent() instanceof BaseWindow)
            return (BaseWindow)component.getParent();
        else
            return getParentWindow(component.getParent().getParent());
    }
    @Override
    public List< Map<String, Object>> getSelVals() {
        if(null==selVals)
            selVals = new ArrayList< Map<String, Object>>();
        return selVals;
    }
}
