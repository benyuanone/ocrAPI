package com.ourway.erpsystem.utils;

import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.erpsystem.ERPTipConstants;
import com.ourway.syszk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>方法 ErpEmployeeBandbox : <p>
*<p>说明:员工数据bandbox</p>
*<pre>
*@author zhou_xtian
*@date 2017-07-12 10:27
</pre>
*/
public class ErpEmployeeBandbox extends BaseBandbox {
        private static final long serialVersionUID = -7917484960028594428L;
        public static final String API_URL = "/erpEmployeeApi/listAllEmployee";
        public static final String API_OR_URL = "/erpEmployeeApi/listAllEmployee";
        private BaseListbox lb = new BaseListbox();// 列表
        private Bandpopup pup = new Bandpopup();
        private List<Object> lbs = new ArrayList<Object>();
        private BaseTextbox goodId = new BaseTextbox();// 查询条件
        private BaseTextbox goodName = new BaseTextbox();// 查询条件
        private Map<String, Object> dbClickVals;//双击选中的对象


        @Override
        public void onCreate() {
            setAutodrop(false);
            addEventListener(Events.ON_OK, new com.ourway.erpsystem.utils.ErpEmployeeBandbox.OpenCusEvent());
            addEventListener(Events.ON_OPEN, new com.ourway.erpsystem.utils.ErpEmployeeBandbox.OpenCusEvent());
            addEventListener(Events.ON_BLUR,new com.ourway.erpsystem.utils.ErpEmployeeBandbox.OpenCusEvent());
            initFilterCon();
            initHead();
            if (!TextUtils.isEmpty(this.getValue())) {
                // 如果有初始值，则显示初始值
                showDefault(this.getValue());
            } else
                this.setPlaceholder(I18nUtil.getLabelContent(ERPTipConstants.PLEASE_SEL));
        }

        @Override
        public void showDefault(Object obj) {
            if(!TextUtils.isEmpty(obj)){
                List<Object> arrayList = new ArrayList<Object>();
                FilterModel model = new FilterModel();
                model.setKey("owid");
                model.setType(FilterModel.EQUALS);
                arrayList.add(obj.toString());
                model.setDatas(arrayList);
                List<FilterModel> models = new ArrayList<FilterModel>(1);
                models.add(model);
                List<Map<String, Object>> datas = filterFromApi(models);
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
            //加入到选定的值中
            setText(getVal(data,"name")+"");
            List<Map<String,Object>> objs = new ArrayList<Map<String,Object>>(1);
            objs.add(data);
            setSelVals(objs);
        }

        private Object getVal(Map<String,Object> data,String property){
            String key = "";
            String[] args = property.split("\\.");
            if(args.length>0)
                key = args[args.length-1];
            else
                key = property;

            if(TextUtils.isEmpty(data.get(key))){
                return "";
            }else
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
            row.setSpans("1,2");
            Div div1 = new Div();
            div1.setParent(row);
            div1.setAlign("right");
            BaseLabel lbel1 = new BaseLabel();
            lbel1.setParent(div1);
            lbel1.setValue(I18nUtil.getLabelContent(ERPTipConstants.ERP_DIC_GOOD_GOODID));
            goodId.setParent(row);
            goodId.setWidth("200px");

            row = new Row();
            row.setParent(rows);
            row.setSpans("1,2");
            div1 = new Div();
            div1.setParent(row);
            div1.setAlign("right");
            lbel1 = new BaseLabel();
            lbel1.setParent(div1);
            lbel1.setValue(I18nUtil.getLabelContent(ERPTipConstants.ERP_DIC_GOOD_GOODNAME));
            goodName.setParent(row);
            goodName.setWidth("200px");

            row = new Row();
            row.setParent(rows);
            row.setSpans("3");
            div1 = new Div();
            div1.setParent(row);
            div1.setAlign("center");
            BaseButton bt = new BaseButton();
            bt.setWidth("80px");
            bt.addEventListener(Events.ON_CLICK, new com.ourway.erpsystem.utils.ErpEmployeeBandbox.ingueryClickEvent());
            bt.setLabel(ERPTipConstants.ERP_DIC_GOOD_GOODQUERY);
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

            lb.getItems().clear();
            lb.setStyle("height:400");
            lb.setMold("paging");
            lb.setPageSize(15);
            lb.setParent(pup);
            lb.setWidth("99%");
            lb.setSizedByContent(false);

            Listhead head = PageUtils.createListHead(lb);
            Listheader h1 = PageUtils.createListheader(lb, "owid");
            h1 = PageUtils.createListheader(lb, "name");

        }

        //根据查询条件返回数据
        private List<Map<String, Object>> filterFromApi(List<FilterModel> models) {
            ResponseMessage mess = JsonPostUtils.executeAPI(models, API_URL, 1, 0);
            if (null != mess && mess.getBackCode() == 0 && null != mess.getBean()) {
                List<Map<String, Object>> records = (List<Map<String, Object>>) mess.getBean();
                return records;
            }
            return null;
        }

        //根据查询条件返回数据
        private List<Map<String, Object>> filterOrFromApi(String key) {
            Map<String,Object> params = new HashMap<String,Object>(1);
            params.put("key",key);
            ResponseMessage mess = JsonPostUtils.executeAPIByMap(params, API_OR_URL);
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

            List<Map<String, Object>> datas = filterOrFromApi(keys);
            if (null != datas && datas.size() > 0)
                initList(datas);
        }

        public void queryData() {
            lb.getItems().clear();
            List<FilterModel> models = new ArrayList<FilterModel>(1);
            List<Object> arrayList = new ArrayList<Object>();
            FilterModel model = new FilterModel();
            model.setKey("owid");
            model.setType(FilterModel.LIKE);
            arrayList.add(goodId.getText());
            model.setDatas(arrayList);
            models.add(model);
            arrayList.clear();
            model = new FilterModel();
            model.setKey("name");
            model.setType(FilterModel.LIKE);
            arrayList.add(goodName.getText());
            model.setDatas(arrayList);
            models.add(model);
            List<Map<String, Object>> datas = filterFromApi(models);
            if (null != datas && datas.size() > 0)
                initList(datas);
        }


        private void initList(List<Map<String, Object>> datas) {
            for (Map<String, Object> cst : datas) {
                Listitem item = new Listitem();
                item.setValue(cst);
                item.setParent(lb);
                item.addEventListener(Events.ON_DOUBLE_CLICK, new com.ourway.erpsystem.utils.ErpEmployeeBandbox.ItemClickEvent());
                // PartNo
                Listcell cell = new Listcell();
                cell.setParent(item);
                cell.setLabel(null == cst.get("owid") ? "" : cst.get("owid").toString());
                cell = new Listcell();
                cell.setParent(item);
                cell.setLabel(null == cst.get("name") ? "" : cst.get("name").toString());
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
                if(event.getName().equalsIgnoreCase(Events.ON_BLUR)){
                    AlterDialog.alert("请选择");
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
