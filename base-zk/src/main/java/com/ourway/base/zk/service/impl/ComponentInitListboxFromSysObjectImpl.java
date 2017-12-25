package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.LogSettingUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/5/28.
 */
public class ComponentInitListboxFromSysObjectImpl implements ComponentInitSer {
    public static final String YES = "yes";

    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        //[{"value":"owid","label":"className","sortStr":"className","default":"yes"}]
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(pageControlVO.getKjInitData().toString());
        if (null == paramsList || paramsList.size() <= 0) {
            AlterDialog.alert("请传入本接口所需的JSON参数");
            return;
        }
        Map<String, Object> param = paramsList.get(0);
        if (TextUtils.isEmpty(param.get("value"))) {
            AlterDialog.alert("请传入Listbox的Value对应属性");
            return;
        }
        if (TextUtils.isEmpty(param.get("label"))) {
            AlterDialog.alert("请传入Listbox的Label对应属性");
            return;
        }
        if (TextUtils.isEmpty(param.get("sortStr"))) {
            AlterDialog.alert("请传入Listbox的排序字段");
            return;
        }
        if(null!=param.get("default")&& YES.equalsIgnoreCase(param.get("default").toString())){
            Listitem item = new Listitem("---请选择---","");
            item.setParent(component);
            item.setSelected(true);
        }
        String label = "";
        String value = "";
        List<Map<String, Object>> datas = LogSettingUtil.listLog();
        if (null != datas && datas.size() > 0) {
            for (Map<String, Object> data : datas) {
                label = TextUtils.isEmpty(data.get(param.get("label").toString()))?"":data.get(param.get("label").toString()).toString();
                value = TextUtils.isEmpty(data.get(param.get("value").toString()))?"":data.get(param.get("value").toString()).toString();
                Listitem item = new Listitem(label,value);
                item.setParent(component);
            }
        }
    }
}
