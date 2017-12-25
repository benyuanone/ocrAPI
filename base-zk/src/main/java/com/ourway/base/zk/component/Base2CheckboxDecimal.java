package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Span;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class Base2CheckboxDecimal extends Div implements ComponentSer<String> {
    private String property;
    private PageControlVO pgvo;//当前控件的属性对象
    private List<Object> selVals; //返回选中的值
    private BaseCheckbox db1;//时间段1
    private BaseDecimalbox db2;//时间段2

    public void onCreate() {
        this.setClass("input-group");
        db1 = new BaseCheckbox();
        db1.setParent(this);
        db1.addEventListener(Events.ON_CLICK, new CheckEvent());
        db1.setClass("input-group-addon checkbox");
        db2 = new BaseDecimalbox();
        db2.setReadonly(true);
        db2.setParent(this);
        db2.setClass("form-control input-sm");
    }

    public void setDisable(boolean flag) {
        db1.setDisabled(flag);
        db2.setDisabled(flag);
    }

    public List<Object> getSelVals() {
        if (null == this.selVals)
            selVals = new ArrayList<Object>();
        if (db1.isChecked())
            selVals.add(1);
        else
            selVals.add(0);
        selVals.add(db2.getValue());
        return selVals;
    }

    public String getDefaultVal() {
        String val = "";
        if (db1.isChecked())
            val = "1-";
        else
            val = "0-";
        if (null != db2.getValue())
            val += db2.getValue();
        else
            val += "0";
        return val;
    }

    public void setDefault(String val) {
        String[] _val = val.split("\\-");
        if ("1".equals(_val[0])) {
            db1.setChecked(true);
            db2.setReadonly(false);
        } else {
            db1.setChecked(false);
            db2.setReadonly(true);
        }
        db2.setText(_val[1]);
    }

    public void setSelVals(List<Object> selVals) {
        this.selVals = selVals;
        if (selVals.size() != 2) {
            AlterDialog.alert("2checkbox默认值错误");
            return;
        } else {
            if (selVals.get(0).toString().equals("1")) {
                db1.setChecked(true);
                db2.setReadonly(false);
            } else {
                db1.setChecked(false);
                db2.setReadonly(true);
            }
            if (null != selVals.get(1))
                db2.setValue(new BigDecimal(selVals.get(1).toString()));
        }

    }

    @Override
    public PageControlVO getPgvo() {
        return pgvo;
    }

    public void setPgvo(PageControlVO pgvo) {
        this.pgvo = pgvo;
        if (!TextUtils.isEmpty(pgvo.getKjAfter())) {
            Span span = new Span();
            span.setClass("input-group-addon");
            span.setParent(this);
            span.setClass("input-group-addon");
            BaseLabel label = new BaseLabel();
            label.setValue(I18nUtil.getLabelContent(pgvo.getKjAfter()));
            label.setParent(span);
        }
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
        if (null == db2.getValue())
            return "";
        else
            return db2.getValue().toString();
    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {
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


    /*设置控件样式*/
    public void setControlCss(String css) {
        if (!TextUtils.isEmpty(css)) {
            //db1.setClass(css);
            //db2.setClass(css);
        }
    }

    private class CheckEvent implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event.getTarget() instanceof BaseCheckbox) {
                BaseCheckbox checkbox = (BaseCheckbox) event.getTarget();
                if (checkbox.isChecked())
                    db2.setReadonly(false);
                else
                    db2.setReadonly(true);
            }
        }
    }

    @Override
    public void setComponentDisable(boolean flag) {
        db1.setDisabled(flag);
        db2.setDisabled(flag);
    }

    @Override
    public void setPageValue(Object obj) {
        if (!TextUtils.isEmpty(obj))
            db2.setValue(obj.toString());
    }
}
