package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutControlVO;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.PageUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseTimebox extends Timebox implements ComponentSer<String> {
    private String property;
    private PageControlVO pgvo;//当前控件的属性对象
    @Override
    public PageControlVO getPgvo() {
        return pgvo;
    }

    public void setPgvo(PageControlVO pgvo) {
        this.pgvo = pgvo;
    }

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
        return this.getText();
    }

    @Override
    public void setPageValue(Object obj) {
        if(!TextUtils.isEmpty(obj))
            setText(obj.toString());
    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {
        PageUtils.doAddEvent(this, win, pageControlVO);
    }

    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {

    }

    @Override
    public void reset() {
        setText("");
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setDisabled(flag);
    }
}
