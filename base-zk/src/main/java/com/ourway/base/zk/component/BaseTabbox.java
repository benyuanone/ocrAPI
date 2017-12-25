package com.ourway.base.zk.component;

import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutControlVO;
import com.ourway.base.zk.service.ComponentSer;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Tabbox;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseTabbox extends Tabbox implements ComponentSer<String> {
    private String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public void init(Map<String, Object> pageObj, String property) {

    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {

    }

    @Override
    public String getPageValue() {
        return "";
    }

    @Override
    public void setPageValue(Object obj) {

    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {

    }

    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {

    }

    @Override
    public void reset() {

    }

    @Override
    public PageControlVO getPgvo() {
        return null;
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setVisible(flag);
    }
}
