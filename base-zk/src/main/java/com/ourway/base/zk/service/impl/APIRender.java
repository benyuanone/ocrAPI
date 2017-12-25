package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseBandbox;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.RenderUtils;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用API转换显示类
 * Created by Administrator on 2017/5/16.
 */
public class APIRender implements RenderUtils {

    @Override
    public String rendar(Object o, String formatter, Component component) {
        if (TextUtils.isEmpty(o))
            return "";
        if (TextUtils.isEmpty(formatter))
            return "";
        String api = "";
        String vals = "";
        if (formatter.indexOf("#") >= 0) {
            String[] _vals = formatter.split("\\#");
            api = _vals[0];
            vals = _vals[1];
        } else
            api = formatter;
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("val", o);
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, api);
        if (TextUtils.isEmpty(responseMessage) || responseMessage.getBackCode() != 0 || TextUtils.isEmpty(responseMessage.getBean()))
            return "";
        else {
//            Map<String, Object> data = (Map<String, Object>) responseMessage.getBean();
//            if (!TextUtils.isEmpty(vals))
//                setVlaueToRow(component, vals, data);
            return responseMessage.getBean().toString();

        }
    }

    private Row getCurrentRow(Component component) {
        if (component.getParent() instanceof Row)
            return (Row) component.getParent();
        else
            return getCurrentRow(component.getParent());
    }

    private void setVlaueToRow(Component component, String str, Map<String, Object> dataMap) {
        Row row = getCurrentRow(component);
        List<Component> list = row.getChildren();
        String[] strs = str.split("\\,");
        String[] eles = null;
        Object obj = null;
        for (String s : strs) {
            eles = s.split("\\-");
            obj = ComponentUtils.doGetObjByLabel(eles[0], dataMap);
            if (!TextUtils.isEmpty(eles[1])) {
                for (Component component1 : list) {
                    if (!TextUtils.isEmpty(component.getAttribute("property")) && component.getAttribute("property").toString().equalsIgnoreCase(eles[1])) {
                        DataBinder.binderToPage(eles[1], obj, component);
                        break;
                    }
                }
            }
        }
    }
}
