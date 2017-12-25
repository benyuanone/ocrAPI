package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.service.RenderUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import sun.awt.TextureSizeConstraining;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public class StatusRender implements RenderUtils {
    //参数格式： 值:多语言标签,值:多语言标签
    @Override
    public String rendar(Object o, String formatter, Component component) {
        if (TextUtils.isEmpty(o))
            return "";
        if (TextUtils.isEmpty(formatter)) {
            return "";
        }
        String[] str = formatter.split("\\,");
        Map<String, String> valMap = new HashMap<String, String>();
        for (String s : str) {
            if (!TextUtils.isEmpty(s)) {
                String[] _t = s.split("\\:");
                if (_t.length != 2)
                    continue;
                valMap.put(_t[0], I18nUtil.getLabelContent(_t[1]));
            }
        }
        if(!TextUtils.isEmpty(o)){
            if(!TextUtils.isEmpty(valMap.get(o.toString()))){
                return valMap.get(o.toString());
            }
        }
        return "";
    }
}
