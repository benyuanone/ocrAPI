package com.ourway.base.zk.bandbox;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>接口 ErpGoodsBandbox.java : <p>
 * <p>说明：产品信息的Bandbox</p>
 * <pre>
 * @author zhangxinyi
 * @date 2017-06-05 14:50
 * </pre>
 */
public class ErpGoodsBandbox extends BaseBandbox {
    public static final String url = "/erpGoodsApi/listErpGoods";
    private BaseListbox dataList = new BaseListbox();// 列表数据，双击选中
    private Bandpopup bandpopup = new Bandpopup(); //bandobx弹出页面
    BaseTextbox filter_goodsId;
    BaseTextbox filter_name;

    @Override
    public void onCreate() {
        this.addEventListener(Events.ON_OPEN, new EventHandle());
        createFilterGrid();
        createListbox();
        if (!TextUtils.isEmpty(this.getValue())) {
            showDefault(this.getValue());
        } else
            this.setText("---请选择---");
    }

    //根据查询条件返回数据
    private List<Map<String, Object>> filterFromApi(List<FilterModel> models) {
        ResponseMessage mess = JsonPostUtils.executeAPI(models, url, 1, 100);
        if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
            Map<String, Object> data = (Map<String, Object>) mess.getBean();
            if (null != data.get("records")) {
                List<Map<String, Object>> records = (List<Map<String, Object>>) data.get("records");
                return records;
            }
        }
        return null;
    }

    @Override
    public void showDefault(Object val) {
        if (TextUtils.isEmpty(val)) {
            setText("---请选择---");
            setValue("---请选择---");
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
            setText(data.get("name").toString());
            getSelVals().add(data);
        }
    }

    //创建Grid的查询界面
    private void createFilterGrid() {
        bandpopup.setParent(this);
        bandpopup.setWidth("500px");
        BaseGrid baseGrid = PageUtils.createGrid(bandpopup);
        Rows rows = PageUtils.createRows(baseGrid);

        Row row = PageUtils.createRow(baseGrid, "1,3");
        Div div = PageUtils.createDiv(row, "right", "");
        BaseLabel label = PageUtils.createLabel(div, I18nUtil.getLabelContent("name"), "");
        filter_name = PageUtils.createTextbox(row, "filter_name", I18nUtil.getLabelContent("name"));

        row = PageUtils.createRow(baseGrid, "1,3");
        div = PageUtils.createDiv(row, "right", "");
        label = PageUtils.createLabel(div, I18nUtil.getLabelContent("goodsId"), "");
        filter_goodsId = PageUtils.createTextbox(row, "filter_goodsId", I18nUtil.getLabelContent("goodsId"));

        row = PageUtils.createRow(baseGrid, "4");
        div = PageUtils.createDiv(row, "center", "");
        Hbox hbox = PageUtils.createHbox(div, "");
        BaseButton filterBtn = PageUtils.createButton(hbox, "filterBtn", "", "filterBtn", new EventHandle());
        BaseButton resetBtn = PageUtils.createButton(hbox, "resetBtn", "", "resetBtn", new EventHandle());
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
        Listheader h1 = PageUtils.createListheader(dataList, "goodsId");
        h1 = PageUtils.createListheader(dataList, "name");
        h1 = PageUtils.createListheader(dataList, "engName");
        h1 = PageUtils.createListheader(dataList, "otherName");
    }

    private void displayDatas(List<Map<String, Object>> datas) {
        dataList.getItems().clear();
        if (null == datas)
            return;
        for (Map<String, Object> data : datas) {
            Listitem listitem = PageUtils.createListItem(dataList);
            listitem.addEventListener(Events.ON_DOUBLE_CLICK, new EventHandle());
            listitem.setValue(data);
            Listcell cell = PageUtils.createListcell(listitem, data.get("goodsId"));
            cell = PageUtils.createListcell(listitem, data.get("name"));
            cell = PageUtils.createListcell(listitem, data.get("engName"));
            cell = PageUtils.createListcell(listitem, data.get("otherName"));
        }

    }

    private class EventHandle implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event instanceof OpenEvent) {
                //初始化所有数据
                OpenEvent openEvent = (OpenEvent) event;
                if (openEvent.isOpen()) {
                    filter_name.setText("");
                    filter_goodsId.setText("");
                    List<Map<String, Object>> datas = filterFromApi(null);
                    displayDatas(datas);
                }
            }
            if (event.getTarget() instanceof BaseButton) {
                BaseButton baseButton = (BaseButton) event.getTarget();
                if (baseButton.getId().equals("filterBtn")) {
                    List<FilterModel> models = new ArrayList<FilterModel>();
                    if (!TextUtils.isEmpty(filter_name.getText())) {
                        List<Object> _obj = new ArrayList<Object>(1);
                        _obj.add(filter_name.getText());
                        FilterModel model = FilterModel.instance("name", FilterModel.LIKE, _obj);
                        models.add(model);
                    }
                    if (!TextUtils.isEmpty(filter_goodsId.getText())) {
                        List<Object> _obj = new ArrayList<Object>(1);
                        _obj.add(filter_goodsId.getText());
                        FilterModel model = FilterModel.instance("goodsId", FilterModel.LIKE, _obj);
                        models.add(model);
                    }
                    List<Map<String, Object>> datas = filterFromApi(models);
                    displayDatas(datas);
                }
                if (baseButton.getId().equals("resetBtn")) {
                    filter_name.setText("");
                    filter_goodsId.setText("");
                }
            }

            if (event.getTarget() instanceof Listitem) {
                Listitem item = (Listitem) event.getTarget();
                Map<String, Object> _data = (Map<String, Object>) item.getValue();
                getSelVals().clear();
                getSelVals().add(_data);
                setText(_data.get("goodsId").toString());
                close();
            }
        }
    }

    @Override
    public List<Map<String,Object>> getSelVals() {
        return null;
    }
}
