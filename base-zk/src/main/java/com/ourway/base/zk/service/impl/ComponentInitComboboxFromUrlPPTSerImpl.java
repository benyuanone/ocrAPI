package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseCombobox;
import com.ourway.base.zk.component.BaseComboitem;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/5/28.
 */
public class ComponentInitComboboxFromUrlPPTSerImpl implements ComponentInitSer {
    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        //[{"apiUrl":"接口路径","type":1：必填}]
        if (component instanceof BaseCombobox) {
            BaseCombobox combobox = (BaseCombobox) component;
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
            ResponseMessage mess = JsonPostUtils.executeAPI(window.getPpt(), param.get("apiUrl").toString());
            if (null != mess && mess.getBackCode() == 0) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) mess.getBean();
                for (Map<String, Object> map : list) {
                    BaseComboitem item = BaseComboitem.instance(map);
                    if (null == dataMap)
                        dataMap = new HashMap<String, Object>();
                    if (!com.ourway.base.utils.TextUtils.isEmpty(map.get("type"))) {
                        if (map.get("type").toString().equals("1"))
                            combobox.setRequired(true);
                        else
                            combobox.setRequired(false);
                    } else
                        combobox.setRequired(false);
                    dataMap.put(item.getLabel(), item.getItemValue());
                    item.setParent(combobox);
                }
                combobox.setDataMap(dataMap);
            }
        }
    }
}
