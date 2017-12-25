package com.ourway.base.zk.component;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.models.PageControlVO;
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
import org.zkoss.zul.ListModelList;

import java.util.*;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseChosenbox extends Chosenbox implements ComponentSer<String> {
    private String property;
    private PageControlVO pgvo;//当前控件的属性对象
    private ListModelList<String> values;
    private Map<String, Map<String, Object>> valuesMap;

    public ListModelList<String> getValues() {
        return values;
    }

    public void setValues(ListModelList<String> values) {
        this.values = values;
    }

    public Map<String, Map<String, Object>> getValuesMap() {
        return valuesMap;
    }

    public void setValuesMap(Map<String, Map<String, Object>> valuesMap) {
        this.valuesMap = valuesMap;
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

    public void setDefault(Object obj){
        if(null==obj)
            setEmptyMessage(I18nUtil.getLabelContent(ERCode.LISTBOX_DEFAULT));
        else{
            List<Map<String,Object>> _list = (List<Map<String,Object>>)obj;
            if(null!=_list&&_list.size()>0) {
                ListModelList<String> _modelList = new ListModelList<String>(_list.size());
                for (Map<String, Object> map : _list) {
                    valuesMap.put(map.get("label").toString(), map);
                    _modelList.add(map.get("label").toString());
                }
                setSelectedObjects(_modelList);
            }
        }
    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {
        setEdit(pageControlVO);
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
                        List<Object> objs = JsonUtil.jsonToList(jsonArray);
                        if (null != objs && objs.size() > 0) {
                            values = new ListModelList<String>(objs.size());
                            valuesMap = new HashMap<String, Map<String, Object>>();
                            for (Object obj : objs) {
                                Map<String, Object> _map = (Map<String, Object>) obj;
                                values.add(_map.get("label").toString());
                                valuesMap.put(_map.get("label").toString(), _map);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2://调用接口
                ResponseMessage mess = JsonPostUtils.executeAPI(pageControlVO.getKjInitData().toString(), pageControlVO.getKjInterface());
                if (null != mess && mess.getBackCode() == 0) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) mess.getBean();
                    if (null != list && list.size() > 0) {
                        values = new ListModelList<String>(list.size());
                        valuesMap = new HashMap<String, Map<String, Object>>();
                        for (Map<String, Object> map : list) {
                            values.add(map.get("label").toString());
                            valuesMap.put(map.get("label").toString(), map);
                        }
                    }
                }
                break;
            case 3://自定义类
                try {
                    if (TextUtils.isEmpty(pageControlVO.getKjClassImpl())) {
                        AlterDialog.alert("未定义初始化类");
                        return;
                    }
                    Object _obj = Class.forName(pageControlVO.getKjClassImpl()).newInstance();
                    ComponentInitSer initSer = (ComponentInitSer) _obj;
                    initSer.doAction(win, this, pageControlVO);
                } catch (Exception e) {
                    AlterDialog.alert(e.getMessage());
                }
                break;
        }
        if (null != values && values.size() > 0) {
            this.setModel(values);
        }
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
        clearSelection();
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setDisabled(flag);
    }

    private void setEdit(PageControlVO pageControlVO) {
        if (null != pageControlVO.getLayoutComponent().getCompEditable() && pageControlVO.getLayoutComponent().getCompEditable() == 1)
            setDisabled(true);
    }

    //获取多选框的值
    public List<Map<String, Object>> getSelectedObjs() {
        LinkedHashSet<Object> allSelItems = getSelectedObjects();
        if (null != allSelItems && allSelItems.size() > 0) {
            List<Map<String, Object>> _result = new ArrayList<Map<String, Object>>(allSelItems.size());
            for (Object allSelItem : allSelItems) {
                if (null != valuesMap)
                    _result.add(valuesMap.get(allSelItem.toString()));
            }
            return _result;
        }
        return null;
    }
}
