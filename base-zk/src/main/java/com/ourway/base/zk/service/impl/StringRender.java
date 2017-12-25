package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.service.RenderUtils;
import org.zkoss.zk.ui.Component;

/**
 * Created by Administrator on 2017/5/16.
 */
public class StringRender implements RenderUtils {
    @Override
    public String rendar(Object o,String formatter, Component component) {
        if (null == o)
            return "";
        return o.toString();
    }
}
