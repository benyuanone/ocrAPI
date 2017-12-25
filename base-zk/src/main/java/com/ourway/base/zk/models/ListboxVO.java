package com.ourway.base.zk.models;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.utils.data.I18nUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
*<p>方法: 下拉框的model </p>
*<ul>
*<li>@return   </li>
*<li>@author JackZhou </li>
*<li>@date 2017/5/14 21:57  </li>
*</ul>
*/
public class ListboxVO implements Serializable {

    private Object value;
    private String label;
    private Boolean selected;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getLabel() {
        return I18nUtil.getLabelContent(label);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public ListboxVO(Object value,String label,Boolean selected){
        this.value = value;
        this.label = label;
        this.selected = selected;
    }
    public ListboxVO(){}

    public static void main(String[] args){
        ListboxVO vo = new ListboxVO("12","test",true);
        ListboxVO vo1 = new ListboxVO("12","test",true);
        ListboxVO vo2 = new ListboxVO("12","test",true);
        List<ListboxVO> volist = new ArrayList<ListboxVO>();
        volist.add(vo);
        volist.add(vo1);
        volist.add(vo2);
        System.out.println(JsonUtil.toJson(volist.toArray()));

    }
}
