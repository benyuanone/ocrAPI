package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.LanguageVO;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.Label;
import org.zkoss.zul.impl.Utils;

import java.io.IOException;
import java.util.Map;

/**
 * <p>方法 BaseLabel : <p>
 * <p>说明:label的二次实现</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/15 12:40
 * </pre>
 */
public class BaseLabel extends Label implements ComponentSer<String> {
    private String property;
    private String _iconSclass;
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

    public String getIconSclass() {
        return _iconSclass;
    }

    public void setIconSclass(String iconSclass) {
        this._iconSclass = iconSclass;
        this.smartUpdate("iconSclass", iconSclass);
    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {
//判断是否需要初始化
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
                setValue(defaultValue);
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
    public void init(Map<String, Object> pageObj, String property) {
        this.property = property;
    }

    @Override
    public String getPageValue() {
        return this.getValue();
    }

    @Override
    public void setPageValue(Object obj) {
        if(!TextUtils.isEmpty(obj))
            setValue(obj.toString());
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        this.render(renderer, "iconSclass", this._iconSclass);
    }

    /**
     * <p>方法:displayWithI18n 显示多语言 </p>
     * <ul>
     * <li> @param key 多语言的标签</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/9 15:14  </li>
     * </ul>
     */
    public void displayWithI18n(String key) {
        LanguageVO languageVO = I18nUtil.getLanguage(key);
        if (null == languageVO)
            setValue(key);
        else {
            setValue(languageVO.getLabelContent());
            if (!TextUtils.isEmpty(languageVO.getLabelTips()) ) {
                setValue(languageVO.getLabelContent());
                setTooltiptext(languageVO.getLabelTips());
            }
        }
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
    public void setComponentDisable(boolean flag) {

    }
}
