package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Spinner;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseSpinner extends Spinner implements ComponentSer<Integer> {
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
        boolean flag = null == pageControlVO || null == pageControlVO.getLayoutComponent() || null == pageControlVO.getLayoutComponent().getCompInit() || pageControlVO.getLayoutComponent().getCompInit() == 0;
        if (flag)
            return;
        if (null == pageControlVO.getKjDatasource())
            return;
        String defaultValue = "";
        switch (pageControlVO.getKjDatasource()) {
            case 0://用户输入
                break;
            case 1://默认值
                defaultValue = (null == pageControlVO.getKjDefaultData() ? "" : I18nUtil.getLabelContent(pageControlVO.getKjDefaultData().toString()));
                setText(defaultValue);
                break;
            case 3://调用接口
                try {
                    ComponentInitSer ser = (ComponentInitSer) Class.forName(pageControlVO.getKjClassImpl()).newInstance();
                    ser.doAction(win, this, pageControlVO);
                } catch (Exception e) {
                    AlterDialog.alert("控件初始化类失败");
                }
//
                break;
        }
    }

    @Override
    public Integer getPageValue() {
        return this.getValue();
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
        setValue(null);
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setDisabled(flag);
    }
}
