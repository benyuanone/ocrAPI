package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.LanguageVO;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.*;

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
public class BaseLabelTip extends Div implements ComponentSer<String> {
    private String property;
    private String _iconSclass;
    private PageControlVO pgvo;//当前控件的属性对象
    private BaseLabel label;
    private Image image;
    private Popup popup;

    public void onCreate() {

    }

    public void onCreate(String labelVal, String html, boolean required) {
//        Hbox hbox = new Hbox();
//        hbox.setParent(this);
        labelVal = I18nUtil.getLabelContent(labelVal);
        html = I18nUtil.getLabelContent(html);
        label = new BaseLabel();
        label.setParent(this);
        if (required) {
            label.setValue(labelVal + "*");
            label.setStyle(ZKConstants.DIV_COMP_REQUIRED_LABEL);
        } else
            label.setValue(labelVal);
        image = new Image();
        image.setSrc("~./zul/img/question40.png");
        image.setParent(this);
        image.setClass(ZKConstants.LABEL_POPUP_IMG_CSS);
        popup = new Popup();
        popup.setParent(this);
        popup.setClass(ZKConstants.LABEL_POPUP_CSS);
        Div _div = new Div();
        _div.setParent(popup);
        Image closeBtn = new Image();
        closeBtn.setSrc("~./zul/img/close.png");
        closeBtn.setParent(_div);
        closeBtn.setStyle("right:5px;padding-top:5px;width:20px;height:20px;");
        closeBtn.addEventListener(Events.ON_CLICK,new EventHandle());
        Html html1 = new Html();
        html1.setContent(html);
        html1.setParent(_div);
        image.setPopup(popup);
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

    public String getIconSclass() {
        return _iconSclass;
    }

    public void setIconSclass(String iconSclass) {
        this._iconSclass = iconSclass;
        this.smartUpdate("iconSclass", iconSclass);
    }

    @Override
    public void setPageValue(Object obj) {

    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {
    }

    @Override
    public void init(Map<String, Object> pageObj, String property) {
        this.property = property;
    }

    @Override
    public String getPageValue() {
        return "";
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        this.render(renderer, "iconSclass", this._iconSclass);
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
    private class EventHandle implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            popup.close();
        }
    }

    @Override
    public void setComponentDisable(boolean flag) {

    }
}
