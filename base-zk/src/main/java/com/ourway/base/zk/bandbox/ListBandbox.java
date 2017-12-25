package com.ourway.base.zk.bandbox;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import javassist.bytecode.SignatureAttribute;
import net.sf.json.JSONNull;
import org.apache.commons.collections.map.HashedMap;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>方法: 页面功能选择 </p>
 * <ul>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/23 22:45  </li>
 * </ul>
 */
public class ListBandbox extends BaseBandbox {
    private Bandpopup bandpopup = new Bandpopup(); //bandobx弹出页面
    BaseBandboxWindow window;

    EventHandle onBlurEvent = null;
    boolean flag = true;
    String oldVal = "";

    @Override
    public void onCreate() {
//        this.addEventListener(Events.ON_OPEN, new EventHandle());
//        bandpopup.setParent(this);
//        bandpopup.setWidth("500px");
//        if (!TextUtils.isEmpty(this.getValue())) {
//            showDefault(this.getValue());
//        } else
//            this.setText(I18nUtil.getLabelContent(ERCode.BANDBOX_DEFAULT));
    }

    @Override
    public void onCreate(String path) {
        this.addEventListener(Events.ON_OPEN, new EventHandle());
        onBlurEvent = new EventHandle();
        this.addEventListener(Events.ON_BLUR, onBlurEvent);
        this.addEventListener(Events.ON_CLICK, new EventHandle());
        this.addEventListener(Events.ON_OK, new EventHandle());
        bandpopup.setParent(this);
        createContent(path);
        if (!TextUtils.isEmpty(this.getValue())) {
//            showDefault(this.getValue());
        } else
            this.setPlaceholder(I18nUtil.getLabelContent(ERCode.BANDBOX_DEFAULT));
        flag = true;
    }

    private void createContent(String filePath) {
        try {
            PageVO pageVO = PageDataUtil.detailPageByCa(filePath);
            Map<String, Object> _params = new HashMap<String, Object>();
            _params.put("pageCa", pageVO.getPageCa());
            _params.put("pageType", 4);
            Component winEdit = Executions.createComponents(pageVO.getPageTemplatePath(), null, _params);
            if (winEdit instanceof BaseBandboxWindow) {
                window = (BaseBandboxWindow) winEdit;
                bandboxVo = window.getBandboxProperty(pageVO.getPageCa());
                if (!TextUtils.isEmpty(bandboxVo.getControlWidth())) {
                    bandpopup.setWidth(bandboxVo.getControlWidth() + "px");
                }
                if (!TextUtils.isEmpty(bandboxVo.getControlHeight())) {
                    bandpopup.setHeight(bandboxVo.getControlHeight() + "px");
                }
                window.setClosable(false);
                bandpopup.getChildren().clear();
                bandpopup.appendChild(window);
                if (!TextUtils.isEmpty(this.getValue())) {
                    // showDefault(this.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void showDefault(Object val) {
        if (TextUtils.isEmpty(val)) {
            setPlaceholder(I18nUtil.getLabelContent(ERCode.BANDBOX_DEFAULT));
            setSelVals(null);
            return;
        }
        List<Object> params = null;
        FilterModel model = null;
        if (val.toString().indexOf(",") > 0) {
            String[] vals = val.toString().split("\\,");
            params = new ArrayList<Object>(vals.length);
            for (String s : vals) {
                if (!TextUtils.isEmpty(s))
                    params.add(s);
            }
            model = FilterModel.instance(bandboxVo.getControlCss(), FilterModel.MULT_EQUALS, params);
        } else {
            params = new ArrayList<Object>(1);
            params.add(val);
            model = FilterModel.instance(bandboxVo.getControlCss(), FilterModel.EQUALS, params);
        }
        List<FilterModel> _params = new ArrayList<FilterModel>(1);
        _params.add(model);
        ResponseMessage mess = JsonPostUtils.executeAPI(_params, bandboxVo.getControlBandboxInt(), 0, 1);
        if (null == mess || mess.getBackCode() != 0)
            return;
        if (TextUtils.isEmpty(mess.getBean()))
            return;
        PageInfoVO _pageinfovo = null;
        if (mess.getBean() instanceof Map)
            _pageinfovo = BeanUtil.map2Obj((HashMap) mess.getBean(), PageInfoVO.class);
        if (mess.getBean() instanceof ArrayList) {
            _pageinfovo = new PageInfoVO();
            _pageinfovo.setRecords((List<Map<String, Object>>) mess.getBean());
        }
        List<Map<String, Object>> result = _pageinfovo.getRecords();
        if (null == result) {
            setPlaceholder(I18nUtil.getLabelContent(ERCode.BANDBOX_DEFAULT));
            setSelVals(null);
        } else {
            String sx = "";
            if (TextUtils.isEmpty(bandboxVo.getControlClass()))
                sx = bandboxVo.getControlInt();
            else
                sx = bandboxVo.getControlClass();
            String _val = "";
            for (Map<String, Object> map : result) {
                _val += TextUtils.isEmpty(map.get(sx)) ? "" : map.get(sx).toString() + ",";
            }
            if (_val.length() > 0)
                _val = _val.substring(0, _val.length() - 1);
            setText(_val);
            setSelVals(result);
            doBackEvent();
        }
    }


    private class EventHandle implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event.getName().equals(Events.ON_CLICK)) {
                if (!flag) {
                    flag = true;
                    addEventListener(Events.ON_BLUR, onBlurEvent);
                }
            }
            if (getText().equals(oldVal)) {
                return;
            }
            if (event instanceof OpenEvent) {
                //初始化所有数据
                OpenEvent openEvent = (OpenEvent) event;
                if (openEvent.isOpen()) {
                    window.filterByKey(getText());
                    doDefaultSelectRow();
                }
            }
            if (event instanceof KeyEvent) {
                List<Map<String, Object>> obj = window.filterByKey(getText());
                if (null != obj) {
                    setSelVals(obj);
                } else {
                    open();
                    doDefaultSelectRow();
                }
            }
            if (event.getName().equals(Events.ON_BLUR)) {
                List<Map<String, Object>> obj = window.filterByKey(getText());
                if (null != obj) {
                    setSelVals(obj);
                } else {
                    open();
                    doDefaultSelectRow();
                }
            }

        }
    }

    @Override
    public List<Map<String, Object>> getSelVals() {
//        if(!TextUtils.isEmpty(getText()))
//            return new ArrayList<Map<String, Object>>();
        if (null == selVals)
            selVals = new ArrayList<Map<String, Object>>();
        return selVals;
    }

    @Override
    public void setSelVals(List<Map<String, Object>> selVals) {
        this.removeEventListener(Events.ON_BLUR, onBlurEvent);
        flag = false;
        if (null != selVals && selVals.size() >= 1) {
            Map<String, Object> map = (Map<String, Object>) selVals.get(0);
            if (!TextUtils.isEmpty(bandboxVo.getControlClass())) {
                Object _obj = doHandleMultipleProperty(map, bandboxVo.getControlClass());
                if (null != _obj) {
                    setText(_obj.toString());
                    oldVal = _obj.toString();
                }
            }
        }
        this.selVals = selVals;
        //判断是否有定义选中后执行的时间
        doBackEvent();
    }

    public Object doHandleMultipleProperty(Map<String, Object> data, String labelKey) {
        Object _obj = null;
        _obj = doGetObjByLabel(labelKey, data);
        if (_obj instanceof JSONNull)
            _obj = null;
        return _obj;
    }

    private Object doGetObjByLabel(String labelKey, Map<String, Object> data) {
        Object _obj = null;
        if (labelKey.contains(".")) {
            Map<String, Object> tmpData = data;
            String[] _labelKeys = labelKey.split("\\.");
            for (int index = 0; index < _labelKeys.length; index++) {
                if (index != _labelKeys.length - 1) {
                    tmpData = (Map<String, Object>) tmpData.get(_labelKeys[index]);
                    if (TextUtils.isEmpty(tmpData))
                        return null;
                } else
                    _obj = tmpData.get(_labelKeys[index]);
            }
        } else
            _obj = data.get(labelKey);
        return _obj;
    }

    //上传成功的返回
    public void doBackEvent() {
        if (!TextUtils.isEmpty(getPgvo().getLayoutComponent().getEvnetFormula())) {
            String clzName = getPgvo().getLayoutComponent().getEvnetFormula();
            try {
                Object _obj = Class.forName(clzName).newInstance();
                ComponentListinerSer compAction = (ComponentListinerSer) _obj;
                Event event = new KeyEvent("bandbox", this, 0, false, false, false);
                compAction.doAction(parentWindow, event, getPgvo());
            } catch (Exception e) {
                e.printStackTrace();
                AlterDialog.alert(clzName + "执行错误");
            }
        }

    }

    public void doDefaultSelectRow() {
        String sx = "";
        String _val = "";
        String _val2 = "";
        List<Row> allRows = window.doGetGridRow();
        if (null == allRows || allRows.size() <= 0) {
            return;
        }
        List<Map<String, Object>> sels = getSelVals();
        if (null == sels || sels.size() <= 0)
            return;
        if (TextUtils.isEmpty(bandboxVo.getControlClass()))
            sx = bandboxVo.getControlInt();
        else
            sx = bandboxVo.getControlClass();


        for (Map<String, Object> sel : sels) {
            if (TextUtils.isEmpty(sel.get(sx)))
                _val = "";
            else
                _val = sel.get(sx).toString();

            for (Row row : allRows) {
                Map<String, Object> rowVal = row.getValue();
                if (TextUtils.isEmpty(rowVal.get(sx)))
                    _val2 = "";
                else
                    _val2 = rowVal.get(sx).toString();
                if (_val.equalsIgnoreCase(_val2)) {
                    if (row.getFirstChild() instanceof BaseCheckbox) {
                        BaseCheckbox checkbox = (BaseCheckbox) row.getFirstChild();
                        if (!checkbox.isChecked())
                            checkbox.setChecked(true);
                    }
                    break;
                }
            }
        }

    }
}
