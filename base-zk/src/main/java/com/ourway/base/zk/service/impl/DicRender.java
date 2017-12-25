package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.service.RenderUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import org.zkoss.zk.ui.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public class DicRender implements RenderUtils {
    @Override
    public String rendar(Object o, String formatter, Component component) {
        if (TextUtils.isEmpty(o))
            return "";
        if (formatter.indexOf("-") < 0) {
            return "";
        }
        String[] str = formatter.split("\\-");
        if (str.length != 3)
            return "";
        int type = Integer.parseInt(str[0]);
        String property = str[1];
        Map<String, Object> val = DicUtil.listOneDetail(type, property, o);
        if (null == val)
            return "";
        else {
            if (null == val.get(str[2]))
                return "";
            else

                return val.get(str[2]) + "";
        }
    }
}
