package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.service.RenderUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import org.zkoss.zk.ui.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public class DicRenderWithLanguage implements RenderUtils {
    @Override
    public String rendar(Object o, String formatter, Component component) {
        if (TextUtils.isEmpty(o))
            return "";
        if (TextUtils.isEmpty(formatter)) {
            return "";
        }

        int type = Integer.parseInt(formatter);
//        String property = str[1];
        Map<String, Object> val = DicUtil.listOneDetailWithLanguage(type, "code", o);
        if (null == val)
            return "";
        else {
            if (null == val.get("dicVal1"))
                return "";
            else
                return val.get("dicVal1") + "";
        }
    }
}
