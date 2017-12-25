package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.models.ListboxVO;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.ValidComponent;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import net.sf.json.JSONArray;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listitem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseCombobox extends Combobox implements ComponentSer<String> {
    private String property;
    private PageControlVO pgvo;//当前控件的属性对象
    private Map<String, Object> dataMap;
    private boolean required = false;//是否要value

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
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
                        List<Object> objs = com.ourway.base.utils.JsonUtil.jsonToList(jsonArray);
                        if (null != objs && objs.size() > 0) {
                            for (Object obj : objs) {
                                Map<String, Object> _map = (Map<String, Object>) obj;
                                BaseComboitem item = BaseComboitem.instance(_map);
                                if (null == dataMap)
                                    dataMap = new HashMap<String, Object>();
                                if (!TextUtils.isEmpty(_map.get("type"))) {
                                    if (_map.get("type").toString().equals("1"))
                                        required = true;
                                    else
                                        required = false;
                                } else
                                    required = false;
                                dataMap.put(item.getLabel(), item.getItemValue());
                                item.setParent(this);
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
                    for (Map<String, Object> map : list) {
                        BaseComboitem item = BaseComboitem.instance(map);
                        if (null == dataMap)
                            dataMap = new HashMap<String, Object>();
                        if (!TextUtils.isEmpty(map.get("type"))) {
                            if (map.get("type").toString().equals("1"))
                                required = true;
                            else
                                required = false;
                        } else
                            required = false;
                        dataMap.put(item.getLabel(), item.getItemValue());
                        item.setParent(this);
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

    }

    private void setEdit(PageControlVO pageControlVO) {
        if (null != pageControlVO.getLayoutComponent().getCompEditable() && pageControlVO.getLayoutComponent().getCompEditable() == 1)
            setDisabled(true);
    }

    @Override
    public String getPageValue() {
        String val = "";
        if (TextUtils.isEmpty(this.getText()))
            val = "";
        else {
            if (required) {
                if (null == dataMap.get(getText().trim()))
                    throw new WrongValueException(this, I18nUtil.getLabelContent(ERCode.COMBOBOX_NO_VALUE));
                else
                    val = dataMap.get(getText().trim()).toString();
            } else
                val = getText().trim();
        }
//        if (null != pgvo && !TextUtils.isEmpty(pgvo.getLayoutComponent()) && !TextUtils.isEmpty(pgvo.getLayoutComponent().getKjConstraint())) {
//            if (ValidComponent.valid(pgvo.getLayoutComponent().getKjConstraint(), val)) {
//                return val;
//            } else
//                throw new WrongValueException(this, I18nUtil.getLabelContent(pgvo.getLayoutComponent().getKjConstraintKey()));
//        }
        return val;
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
        setSelectedIndex(0);
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setDisabled(flag);
    }
}
