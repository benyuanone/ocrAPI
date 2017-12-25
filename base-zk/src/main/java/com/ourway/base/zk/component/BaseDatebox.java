package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseDatebox extends Datebox implements ComponentSer<String> {
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
        return getText();
    }

    @Override
    public void setPageValue(Object obj) {
        if (!TextUtils.isEmpty(obj))
            setText(obj.toString());
    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {
        PageUtils.doAddEvent(this, win, pageControlVO);
    }

    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {
        boolean flag = null == pgvo.getLayoutComponent() || null == pgvo.getLayoutComponent().getEventType() || pgvo.getLayoutComponent().getEventType() == 0;
        if (flag)
            return;
        //根据类别组装调用的数据
        if (!TextUtils.isEmpty(pgvo.getLayoutComponent().getEvnetFormula())) {
            String clzName = pgvo.getLayoutComponent().getEvnetFormula();
            try {
                Object _obj = Class.forName(clzName).newInstance();
                ComponentListinerSer compAction = (ComponentListinerSer) _obj;
                compAction.doAction(win, event, pgvo);
            } catch (Exception e) {
                e.printStackTrace();
                AlterDialog.alert(clzName + "执行错误");
            }
        }
    }

    @Override
    public void reset() {
        setValue(null);
        setText("");
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setDisabled(flag);
    }
}
