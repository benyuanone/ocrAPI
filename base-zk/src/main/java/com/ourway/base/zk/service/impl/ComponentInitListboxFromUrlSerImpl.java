package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseCombobox;
import com.ourway.base.zk.component.BaseComboitem;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/5/28.
 */
public class ComponentInitListboxFromUrlSerImpl implements ComponentInitSer {
    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        //[{"apiUrl":"接口路径","default":"yes"}]
        boolean flag = false;
        int index = 0;
        if (component instanceof BaseCombobox) {
            BaseListbox combobox = (BaseListbox) component;
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
            if (!TextUtils.isEmpty(param.get("default")) && param.get("default").toString().equalsIgnoreCase("yes")) {
                Listitem item = new Listitem();
                item.setValue("");
                item.setLabel(I18nUtil.getLabelContent(ERCode.LISTBOX_DEFAULT));
                item.setSelected(true);
                item.setParent(combobox);
                flag = true;
                return;
            }

            Map<String, Object> dataMap = new HashMap<String, Object>();
            ResponseMessage mess = JsonPostUtils.executeAPI("", param.get("apiUrl").toString());
            if (null != mess && mess.getBackCode() == 0) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) mess.getBean();
                for (Map<String, Object> map : list) {
                    if (TextUtils.isEmpty(map.get("value")))
                        continue;
                    Listitem item = new Listitem();
                    if (!TextUtils.isEmpty(map.get("value")))
                        item.setValue(map.get("value"));
                    if (!TextUtils.isEmpty(map.get("label")))
                        item.setLabel(map.get("label").toString());
                    if (index == 0 && flag)
                        item.setSelected(true);
                    index++;
                    item.setParent(combobox);
                }
            }
        }
    }
}
