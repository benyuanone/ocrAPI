package com.ourway.base.zk.component;

import org.zkoss.zul.Comboitem;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/27.
 */
public class BaseComboitem extends Comboitem {

    private Map<String,Object> externProperty;
    private String itemValue;

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public BaseComboitem(String label){
        super(label);
    }

    public Map<String, Object> getExternProperty() {
        return externProperty;
    }

    public void setExternProperty(Map<String, Object> externProperty) {
        this.externProperty = externProperty;
    }

    public static BaseComboitem instance(String itemValue,String label){
        BaseComboitem comboitem = new BaseComboitem(label);
        comboitem.setItemValue(itemValue);
        return comboitem;
    }
    public static BaseComboitem instance(Map<String, Object> data){
        BaseComboitem comboitem = new BaseComboitem(data.get("label")+"");
        comboitem.setItemValue(data.get("value")+"");
        return comboitem;
    }
}
