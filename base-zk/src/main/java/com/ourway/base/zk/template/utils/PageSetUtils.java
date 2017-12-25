package com.ourway.base.zk.template.utils;

import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zul.Tab;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/27.
 */
public class PageSetUtils {

    public static Tab doCreateTab(Map<String,Object> control){
        Tab tab = new Tab();
        tab.setId(control.get("controlId").toString());
        tab.setLabel(control.get("controlName").toString() + "(" + control.get("controlId").toString() + ")");
        tab.setTooltiptext(TextUtils.isEmpty(control.get("controlDesc")) ? "" : control.get("controlDesc").toString());
        return tab;
    }

    public static BaseButton doCreateButton(String kjAttribute,String name,String kjType){
        BaseButton button = new BaseButton();
        button.setProperty(kjAttribute);
        button.setLabel(name);
        return button;
    }
}
