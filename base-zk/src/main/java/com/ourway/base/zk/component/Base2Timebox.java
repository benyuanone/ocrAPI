package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentSer;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Hbox;

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
public class Base2Timebox extends Hbox implements ComponentSer<String> {
    private String property;
    private PageControlVO pgvo;//当前控件的属性对象
    private List<Object> selVals; //返回选中的值
    private BaseTimebox db1;//时间段1
    private BaseTimebox db2;//时间段2

    public void onCreate() {
        db1 = new BaseTimebox();
        db1.setReadonly(true);
        db1.setParent(this);
        db1.setFormat("HH:mm:ss");
        BaseLabel label = new BaseLabel();
        label.setValue("-");
        label.setParent(this);
        db2 = new BaseTimebox();
        db2.setReadonly(true);
        db2.setParent(this);
        db2.setFormat("HH:mm:ss");
    }

    public List<Object> getSelVals() {
        if (null == this.selVals)
            selVals = new ArrayList<Object>();
        if(TextUtils.isEmpty(db1.getText()))
            selVals.add(null);
        else
            selVals.add(db1.getText());
        if(TextUtils.isEmpty(db2.getText()))
            selVals.add(null);
        else
            selVals.add(db2.getText());
        return selVals;
    }

    public void setSelVals(List<Object> selVals) {
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
        return db1.getText() + "/" + db2.getText();
    }

    @Override
    public void setPageValue(Object obj) {
        if (!TextUtils.isEmpty(obj)) {
            String[] strs = obj.toString().split("\\/");
            if (!TextUtils.isEmpty(strs[0]))
                db1.setText(strs[0]);
            if (!TextUtils.isEmpty(strs[1]))
                db2.setText(strs[1]);
        }
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
            db1.setClass(css);
            db2.setClass(css);
        }
    }

    @Override
    public void setComponentDisable(boolean flag) {
        db1.setDisabled(flag);
        db2.setDisabled(flag);

    }
}
