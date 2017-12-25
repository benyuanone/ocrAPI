package com.ourway.base.zk.component;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.PageUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseTree extends Tree implements ComponentSer<String> {
    private String property; //属性
    private String valProperty;//从选中的值中获取
    private String filterType;
    private Menupopup rightPopup;
    private Treeitem dragItem;
    private Treeitem dropItem;

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getValProperty() {
        return valProperty;
    }

    public void setValProperty(String valProperty) {
        this.valProperty = valProperty;
    }

    public Treeitem getDragItem() {
        return dragItem;
    }

    public void setDragItem(Treeitem dragItem) {
        this.dragItem = dragItem;
    }

    public Treeitem getDropItem() {
        return dropItem;
    }

    public void setDropItem(Treeitem dropItem) {
        this.dropItem = dropItem;
    }

    public Menupopup getRightPopup() {
        return rightPopup;
    }

    public Menupopup getRightPopup(String disableLabel) {
        Menupopup menupopup = getRightPopup();
        if (null != menupopup) {
            List<Component> menuitemList = menupopup.getChildren();
            for (Component menuitem : menuitemList) {
                if (menuitem instanceof Menuitem)
                    if (!TextUtils.isEmpty(menuitem.getAttribute("label")) && !TextUtils.isEmpty(disableLabel)) {
                        if (disableLabel.contains(menuitem.getAttribute("label").toString()))
                            menuitem.setVisible(false);
                    }
            }
        }
        return rightPopup;
    }

    public void setRightPopup(Menupopup rightPopup) {
        this.rightPopup = rightPopup;
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
        return "";
    }

    @Override
    public void setPageValue(Object obj) {

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

    }

    public FilterModel doComposeFilter() {
        Treeitem item = this.getSelectedItem();
        if (null == item)
            return null;
        TreeVO treeVO = item.getValue();
        if (null == treeVO)
            return null;
        List<Object> objects = new ArrayList<Object>(1);
        try {
            Object obj = BeanUtil.getProperty(treeVO, valProperty);
            objects.add(obj);
            return FilterModel.instance(property, Integer.parseInt(filterType), objects);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PageControlVO getPgvo() {
        return null;
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setVisible(flag);
    }
}
