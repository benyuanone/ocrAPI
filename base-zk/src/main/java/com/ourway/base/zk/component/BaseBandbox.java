package com.ourway.base.zk.component;

import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Bandbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public abstract class BaseBandbox extends Bandbox implements ComponentSer<String> {
    private String property;
    private PageControlVO pgvo;//当前控件的属性对象
    protected List<Map<String, Object>> selVals; //返回选中的值
    protected BaseWindow parentWindow;
    protected PageLayoutVO bandboxVo;

    public PageLayoutVO getBandboxVo() {
        return bandboxVo;
    }

    public void setBandboxVo(PageLayoutVO bandboxVo) {
        this.bandboxVo = bandboxVo;
    }

    public void onCreate(CreateEvent createEvent) {
        onCreate();
    }

    public abstract void onCreate();

    public void onCreate(String path) {
    }

    /**
     * <p>方法:getSelVals bandbox中从选中的数据返回，返回时List类型 </p>
     * <ul>
     * <li>@return java.util.List<java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/6/11 15:08  </li>
     * </ul>
     */
    public abstract List<Map<String, Object>> getSelVals();

    /**
     * <p>方法:showDefault 初始化 </p>
     * <ul>
     * <li> @param obj 初始化数据的值</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/6/11 15:08  </li>
     * </ul>
     */
    public abstract void showDefault(Object obj);

    public void setSelVals(List<Map<String, Object>> selVals) {
        this.selVals = selVals;
    }

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
    public String getPageValue() {
        return "";
    }


    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {
        parentWindow = win;
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
        setText(I18nUtil.getLabelContent("public.sys.common.bandbox.default"));
        setSelVals(null);
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setDisabled(flag);
    }

    @Override
    public void setPageValue(Object obj) {
        showDefault(obj);
    }
}
