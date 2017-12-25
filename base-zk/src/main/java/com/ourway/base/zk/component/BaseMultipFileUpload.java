package com.ourway.base.zk.component;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.Div;
import org.zkoss.zul.Span;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>方法: 时间段查询 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/31 23:22  </li>
 * </ul>
 */
public class BaseMultipFileUpload extends Div implements ComponentSer<String> {
    private String property;
    private PageControlVO pgvo;
    private String result;
    private BaseButton db1;
    private BaseTextbox textbox;
    private BaseWindow win;
    Map<String, Object> filePpt;

    public Map<String, Object> getFilePpt() {
        return filePpt;
    }

    public void onCreate() {
        this.setClass("input-group");
        textbox = new BaseTextbox();
        textbox.setDisabled(true);
        textbox.setClass("form-control input-sm ");
        textbox.setParent(this);
        Span span = new Span();
        span.setClass("input-group-btn");
        span.setParent(this);
        db1 = new BaseButton();
        db1.setAttribute("type","attachFileBtn");
        db1.setClass("btn-default input-sm z-fileUpload-button ");
        db1.setParent(span);
        db1.setLabel(I18nUtil.getLabelContent(pgvo.getKjLabelid()));
    }

    public void setCustClass(String custClass) {
        if (!TextUtils.isEmpty(custClass)) {
            textbox.setClass("form-control input-sm " + custClass);
        }
    }

    public void setFileNum(String custClass) {
        textbox.setText(custClass);
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
        return result;
    }

    @Override
    public void setPageValue(Object obj) {

    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {
        this.win = win;
    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {
        db1.setPgvo(pgvo);
        PageUtils.doAddEvent(db1, win, pageControlVO);
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

    }

    //上传成功的返回
    public void doBackEvent() {
        if (!TextUtils.isEmpty(pgvo.getLayoutComponent().getEvnetFormula())) {
            String clzName = pgvo.getLayoutComponent().getEvnetFormula();
            try {
                Event event = new KeyEvent("baseFileUpload", this, 0, false, false, false);
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
    public void setComponentDisable(boolean flag) {
        db1.setDisabled(flag);

    }
}
