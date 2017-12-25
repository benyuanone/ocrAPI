package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.ListboxVO;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import net.sf.json.JSONArray;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tree;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseCheckbox extends Checkbox implements ComponentSer<String> {
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
        //判断是否需要初始化
        boolean flag = null == pageControlVO || null == pageControlVO.getLayoutComponent() || null == pageControlVO.getLayoutComponent().getCompInit() || pageControlVO.getLayoutComponent().getCompInit() == 0;
        if (flag)
            return;
        if (null == pageControlVO.getKjDatasource())
            return;
        switch (pageControlVO.getKjDatasource()) {
            case 0://用户输入
                break;
            case 1://默认值
                if (null != pageControlVO.getKjDefaultData()) {
                    try {
                        JSONArray jsonArray = JSONArray.fromObject(pageControlVO.getKjDefaultData().toString());
                        List<Object> objs = com.ourway.base.utils.JsonUtil.jsonToList(jsonArray);
                        if (null != objs && objs.size() > 0) {
                            for (Object obj : objs) {
                                Map<String, Object> _map = (Map<String, Object>) obj;
                                ListboxVO vo = com.ourway.base.utils.JsonUtil.map2Bean(_map, ListboxVO.class);
                                if (vo.getSelected())
                                    this.setChecked(true);
                                else
                                    this.setChecked(false);
                                if (!TextUtils.isEmpty(vo.getLabel()))
                                    this.setLabel(I18nUtil.getLabelContent(vo.getLabel()));
                                if (!TextUtils.isEmpty(vo.getValue()))
                                    this.setValue(vo.getValue().toString());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2://调用接口

                break;
            case 3://自定义类

                break;
        }
    }

    @Override
    public String getPageValue() {
        if (isChecked())
            return "1";
        else
            return "0";
    }

    @Override
    public void setPageValue(Object obj) {
        if (!TextUtils.isEmpty(obj)) {
            if (obj.toString().equalsIgnoreCase("1"))
                setChecked(true);
            else
                setChecked(false);
        }
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
        setChecked(false);
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setDisabled(flag);
    }
}
