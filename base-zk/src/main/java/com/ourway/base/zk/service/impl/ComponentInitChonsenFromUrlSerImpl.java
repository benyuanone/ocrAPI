package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseChosenbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.ListModelList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/5/28.
 */
public class ComponentInitChonsenFromUrlSerImpl implements ComponentInitSer {
    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        //[{"apiUrl":"接口路径"}]
        boolean flag = false;
        int index = 0;
        if (component instanceof BaseChosenbox) {
            BaseChosenbox combobox = (BaseChosenbox) component;
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(pageControlVO.getKjInitData().toString());
            if (null == paramsList || paramsList.size() <= 0) {
                AlterDialog.alert("请传入本接口所需的JSON参数");
                return;
            }
            Map<String, Object> param = paramsList.get(0);
            if (TextUtils.isEmpty(param.get("apiUrl"))) {
                AlterDialog.alert("请传入获取的url");
                return;
            }

            Map<String, Object> dataMap = new HashMap<String, Object>();
            ResponseMessage mess = JsonPostUtils.executeAPI("", param.get("apiUrl").toString());
            if (null != mess && mess.getBackCode() == 0) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) mess.getBean();
                ListModelList<String> values = new ListModelList<String>(list.size());
                Map<String, Map<String, Object>> valuesMap = new HashMap<String, Map<String, Object>>();
                for (Map<String, Object> map : list) {
                    if (TextUtils.isEmpty(map.get("label")))
                        continue;
                    values.add(map.get("label").toString());
                    valuesMap.put(map.get("label").toString(), map);
                }
                combobox.setValues(values);
                combobox.setValuesMap(valuesMap);
            }
        }
    }
}
