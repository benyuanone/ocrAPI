package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.service.RenderUtils;
import org.zkoss.zk.ui.Component;

/**
 * Created by Administrator on 2017/5/16.
 */
public class YMDRender implements RenderUtils {
    @Override
    public String rendar(Object o,String formatter, Component component) {
        if(TextUtils.isEmpty(o))
            return "";
        if(o instanceof String){
            return o.toString();
        }
        try {
            java.util.Date date = (java.util.Date) o;
            return DateUtil.getDateString(date, formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
