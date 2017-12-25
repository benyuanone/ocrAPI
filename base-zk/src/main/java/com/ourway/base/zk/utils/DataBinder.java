package com.ourway.base.zk.utils;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.bandbox.ListBandbox;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.xml.soap.Text;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * ZK对象绑定类
 */
public class DataBinder {

    public static final int OBJ_TO_PAGE = 1;// 对象绑定页面
    public static final int PAGE_TO_OBJ = 2;// 页面绑定对象
    public static final int PAGE_TO_MAP = 3;// 页面绑定到MAP

    /*从分离点路径，获取数据*/
    private static Object doGetObjByLabel(String labelKey, Map<String, Object> data) {
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

    //重新初始化所有button对象的展现
    public static void refreshButton(Map<String, Object> ppt, BaseWindow window) {
        Collection<Component> comps = window.getFellows();
        for (Component comp : comps) {
            refreshSingleButton(ppt, comp);
        }
    }

    //重新初始化所有button对象的展现
    public static void refreshSingleButton(Map<String, Object> ppt, Component component) {
        if (component instanceof BaseButton) {
            BaseButton btn = (BaseButton) component;
            if (null == btn.getPgvo() || null == btn.getPgvo().getLayoutComponent() || TextUtils.isEmpty(btn.getPgvo().getLayoutComponent().getEventColor()))
                return;
            String formula = btn.getPgvo().getLayoutComponent().getEventColor();
            formula = formula.replaceAll("\\$", "'");
            Set<String> keys = ppt.keySet();
            for (String s : keys) {
                if (formula.indexOf(s) >= 0)
                    formula = formula.replace(s, TextUtils.isEmpty(ppt.get(s)) ? "" : ppt.get(s).toString());
            }
            boolean flag = doRunFormula(formula);
            if (flag)
                btn.setDisabled(true);
            else
                btn.setDisabled(false);
        }
    }

    private static boolean doRunFormula(String formula) {
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            return jse.eval(formula).toString().equals("true");
        } catch (Exception t) {
            return false;
        }
    }

    /**
     * <p>方法:obj2Component 对象绑定到页面的控件 </p>
     * <ul>
     * <li> @param obj 对象</li>
     * <li> @param component 控件</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/3 1:11  </li>
     * </ul>
     */
    public static void obj2Component(Object obj, Component component, Map<String, Object> ppt) {

        if (component instanceof BaseTextbox) {
            try {
                BaseTextbox comp = (BaseTextbox) component;
                comp.clearErrorMessage();
                if (!TextUtils.isEmpty(comp.getPgvo()) && !TextUtils.isEmpty(comp.getPgvo().getKjAttributeDisplay())) {
                    obj = doGetObjByLabel(comp.getPgvo().getKjAttributeDisplay(), ppt);
                }
                comp.setText(TextUtils.isEmpty(obj) ? null : obj.toString());
            } catch (Exception e) {

            }
        }
        if (component instanceof BaseIntbox) {
            try {
                BaseIntbox comp = (BaseIntbox) component;
                comp.clearErrorMessage();
                if (!TextUtils.isEmpty(comp.getPgvo()) && !TextUtils.isEmpty(comp.getPgvo().getKjAttributeDisplay())) {
                    obj = doGetObjByLabel(comp.getPgvo().getKjAttributeDisplay(), ppt);
                }
                comp.setValue(TextUtils.isEmpty(obj) ? null : new Integer(obj.toString()));
            } catch (Exception e) {

            }
        }
        if (component instanceof BaseLabel) {
            BaseLabel comp = (BaseLabel) component;
            if (!TextUtils.isEmpty(comp.getPgvo()) && !TextUtils.isEmpty(comp.getPgvo().getKjAttributeDisplay())) {
                obj = doGetObjByLabel(comp.getPgvo().getKjAttributeDisplay(), ppt);
            }
            comp.setValue(TextUtils.isEmpty(obj) ? "" : obj.toString());
        }
        if (component instanceof BaseDoublebox) {
            try {
                BaseDoublebox comp = (BaseDoublebox) component;
                if (!TextUtils.isEmpty(comp.getPgvo()) && !TextUtils.isEmpty(comp.getPgvo().getKjAttributeDisplay())) {
                    obj = doGetObjByLabel(comp.getPgvo().getKjAttributeDisplay(), ppt);
                }
                comp.clearErrorMessage();
                comp.setValue(TextUtils.isEmpty(obj) ? null : new Double(obj.toString()));
            } catch (Exception e) {

            }
        }
        if (component instanceof BaseDecimalbox) {
            try {
                BaseDecimalbox comp = (BaseDecimalbox) component;
                if (!TextUtils.isEmpty(comp.getPgvo()) && !TextUtils.isEmpty(comp.getPgvo().getKjAttributeDisplay())) {
                    obj = doGetObjByLabel(comp.getPgvo().getKjAttributeDisplay(), ppt);
                }
                comp.clearErrorMessage();
                comp.setValue(TextUtils.isEmpty(obj) ? null : new BigDecimal(obj.toString()));
            } catch (Exception e) {

            }
        }
        if (component instanceof BaseLongbox) {
            try {
                BaseLongbox comp = (BaseLongbox) component;
                if (!TextUtils.isEmpty(comp.getPgvo()) && !TextUtils.isEmpty(comp.getPgvo().getKjAttributeDisplay())) {
                    obj = doGetObjByLabel(comp.getPgvo().getKjAttributeDisplay(), ppt);
                }
                comp.clearErrorMessage();
                comp.setValue(TextUtils.isEmpty(obj) ? null : new Long(obj.toString()));
            } catch (Exception e) {

            }
        }
        if (component instanceof BaseCombobox) {
            try {
                BaseCombobox comp = (BaseCombobox) component;
                if (!TextUtils.isEmpty(comp.getPgvo()) && !TextUtils.isEmpty(comp.getPgvo().getKjAttributeDisplay())) {
                    obj = doGetObjByLabel(comp.getPgvo().getKjAttributeDisplay(), ppt);
                }
                comp.clearErrorMessage();
                comp.setValue(TextUtils.isEmpty(obj) ? null : obj.toString());
            } catch (Exception e) {

            }
        }
        if (component instanceof Html) {

            ((Html) component).setContent(TextUtils.isEmpty(obj) ? null : obj.toString());
        }

        if (component instanceof BaseDatebox) {
            try {
                BaseDatebox _db = (BaseDatebox) component;
                _db.clearErrorMessage();
                String _format = _db.getFormat();
                if (TextUtils.isEmpty(obj))
                    obj = null;
                if (null != obj)
                    if (obj instanceof java.util.Date) {
                        _db.setText(DateUtil.getDateString((java.util.Date) obj, _format));
                    } else
                        _db.setText(obj.toString());
            } catch (Exception e) {

            }
        }
        if (component instanceof BaseTimebox) {
            try {
                BaseTimebox _db = (BaseTimebox) component;
                _db.clearErrorMessage();
                String _format = _db.getFormat();
                if (TextUtils.isEmpty(obj))
                    obj = null;
                if (null != obj)
                    if (obj instanceof java.util.Date) {
                        _db.setText(DateUtil.getDateString((java.util.Date) obj, _format));
                    } else
                        _db.setText(obj.toString());
            } catch (Exception e) {

            }
        }
        if (component instanceof BaseListbox) {
            BaseListbox _db = (BaseListbox) component;

            if (null != _db.getItems() && _db.getItems().size() > 0)
                for (Listitem item : _db.getItems()) {
                    if (!TextUtils.isEmpty(item.getValue())) {
                        if (null != obj && item.getValue().toString().equals(obj.toString()))
                            item.setSelected(true);
                    }
                }
        }
        if (component instanceof BaseBandbox) {
            BaseBandbox _db = (BaseBandbox) component;
            _db.clearErrorMessage();
            if (TextUtils.isEmpty(obj))
                obj = null;
            if (component instanceof ListBandbox) {
                ListBandbox lbd = (ListBandbox) component;
                lbd.showDefault(obj);
            } else
                _db.showDefault(obj);
        }
        if (component instanceof BaseCheckbox) {
            BaseCheckbox _db = (BaseCheckbox) component;
            if (null != obj && _db.getValue() != null && _db.getValue().toString().equals(obj.toString()))
                _db.setChecked(true);
            else
                _db.setChecked(false);
        }
        if (component instanceof Base2CheckboxDecimal) {
            Base2CheckboxDecimal _db = (Base2CheckboxDecimal) component;
            if (!TextUtils.isEmpty(obj)) {
                _db.setDefault(obj.toString());
            }
        }
        if (component instanceof BaseMultipFileUpload) {
            BaseMultipFileUpload _db = (BaseMultipFileUpload) component;
            if (!TextUtils.isEmpty(obj)) {
                _db.setFileNum(obj.toString());
            }
        }
        if (component instanceof BaseFileUpload) {
            BaseFileUpload _db = (BaseFileUpload) component;
            if (!TextUtils.isEmpty(obj))
                _db.showDefault(obj);
        }
        if (component instanceof Image) {
            Image image = (Image) component;
            if (!TextUtils.isEmpty(obj)) {
                image.setSrc(ZKConstants.SYSTEM_FILE_URL + obj.toString());
            } else {

            }
        }
        if (component instanceof BaseMultipleTree) {
            BaseMultipleTree _db = (BaseMultipleTree) component;
            if (!TextUtils.isEmpty(obj))
                _db.setDefault(obj.toString());
        }
        //多选框
        if (component instanceof BaseChosenbox) {
            BaseChosenbox chosenbox = (BaseChosenbox) component;
            chosenbox.setDefault(obj);
        }
        //图片上传
        if (component instanceof BaseImagebox) {
            BaseImagebox chosenbox = (BaseImagebox) component;
            chosenbox.showDefault(obj);
        }
    }

    //是否要进行校验的获取数据方式，validFlag true表示需要
    public static Object component2Obj(Component component, boolean validFlag, BaseWindow window) {
        Object obj = component2Obj(component);
        boolean flag = false;
        if (!validFlag)
            return obj;
        if (component instanceof BaseTextbox) {
            BaseTextbox baseTextbox = (BaseTextbox) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseDatebox) {
            BaseDatebox baseTextbox = (BaseDatebox) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag) {
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
            }
        }
        if (component instanceof BaseIntbox) {
            BaseIntbox baseTextbox = (BaseIntbox) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseDecimalbox) {
            BaseDecimalbox baseTextbox = (BaseDecimalbox) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseLongbox) {
            BaseLongbox baseTextbox = (BaseLongbox) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseTimebox) {
            BaseTimebox baseTextbox = (BaseTimebox) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseDoublespinner) {
            BaseDoublespinner baseTextbox = (BaseDoublespinner) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseSpinner) {
            BaseSpinner baseTextbox = (BaseSpinner) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseBandbox) {
            BaseBandbox baseTextbox = (BaseBandbox) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseMultipleTree) {
            BaseMultipleTree baseTextbox = (BaseMultipleTree) component;
            flag = null != baseTextbox.getPgvo() && null != baseTextbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(baseTextbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(baseTextbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseChosenbox) {
            BaseChosenbox chosenbox = (BaseChosenbox) component;
            flag = null != chosenbox.getPgvo() && null != chosenbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(chosenbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(chosenbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(chosenbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        if (component instanceof BaseListbox) {
            BaseListbox listbox = (BaseListbox) component;
            flag = null != listbox.getPgvo() && null != listbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(listbox.getPgvo().getLayoutComponent().getKjConstraint());
            if (flag)
                if (!ValidComponent.valid(listbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(listbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
        }
        //图片上传 校验判断
        if (component instanceof BaseImagebox) {
            BaseImagebox listbox = (BaseImagebox) component;
            flag = null != listbox.getPgvo() && null != listbox.getPgvo().getLayoutComponent() && !TextUtils.isEmpty(listbox.getPgvo().getLayoutComponent().getKjConstraint());
            if(flag){
                if (!ValidComponent.valid(listbox.getPgvo().getLayoutComponent().getKjConstraint(), obj, window)) {
                    throw new WrongValueException(component, I18nUtil.getLabelContent(listbox.getPgvo().getLayoutComponent().getKjConstraintKey()));
                }
            }

        }

        return obj;
    }

    public static Object component2Obj(Component component) {
        if (component instanceof BaseTextbox) {
            try {
                return ((BaseTextbox) component).getText();
            } catch (WrongValueException e) {
                return "";
            }
        }
        if (component instanceof BaseIntbox) {
            try {
                return ((BaseIntbox) component).getValue();
            } catch (WrongValueException e) {
                return null;
            }
        }
        if (component instanceof BaseLabel) {
            try {
                return ((BaseLabel) component).getValue();
            } catch (WrongValueException e) {
                return null;
            }
        }
        if (component instanceof BaseDoublebox) {
            try {
                return ((BaseDoublebox) component).getValue();
            } catch (WrongValueException e) {
                return null;
            }
        }
        if (component instanceof BaseLongbox) {
            try {
                return ((BaseLongbox) component).getValue();
            } catch (WrongValueException e) {
                return null;
            }
        }
        if (component instanceof BaseDecimalbox) {
            try {
                return ((BaseDecimalbox) component).getValue();
            } catch (WrongValueException e) {
                return null;
            }
        }
        if (component instanceof BaseCombobox) {
            try {
                return ((BaseCombobox) component).getPageValue();
            } catch (WrongValueException e) {
                return null;
            }
        }

        if (component instanceof BaseDatebox) {
            BaseDatebox _db = (BaseDatebox) component;
            String _format = _db.getFormat();
            try {
//                if (!TextUtils.isEmpty(_db.getText()))
//                    return DateUtil.getDate(_db.getText(), _format);
//                else
                return _db.getText();
            } catch (Exception e) {
                return null;
            }
        }
        if (component instanceof BaseTimebox) {
            BaseTimebox _db = (BaseTimebox) component;
            String _format = _db.getFormat();
            try {
                if (!TextUtils.isEmpty(_db.getText()))
                    return DateUtil.getDate(_db.getText(), _format);
                else
                    return null;
            } catch (Exception e) {
                return null;
            }
        }
        if (component instanceof BaseListbox) {
            BaseListbox _db = (BaseListbox) component;
            if (null != _db.getSelectedItem())
                return _db.getSelectedItem().getValue();
        }
        if (component instanceof BaseBandbox) {
            BaseBandbox _db = (BaseBandbox) component;
            String ids = "";
            if (null == _db.getSelVals())
                return ids;
            if (_db.getSelVals().size() == 1)
                return _db.getSelVals().get(0);
            else
                return _db.getSelVals();
//            for (Object o : _db.getSelVals()) {
//                ids += o.toString() + ",";
//            }
//            if (ids.length() > 0)
//                ids = ids.substring(0, ids.length() - 1);
//            return ids;
        }
        if (component instanceof BaseCheckbox) {
            BaseCheckbox _db = (BaseCheckbox) component;
            if (_db.isChecked())
                return 1;
            else
                return 0;
        }
        if (component instanceof Base2CheckboxDecimal) {
            Base2CheckboxDecimal _db = (Base2CheckboxDecimal) component;
            return _db.getDefaultVal();
        }
        if (component instanceof BaseFileUpload) {
            BaseFileUpload _db = (BaseFileUpload) component;
            return _db.getFilePpt();
        }
        //获取选中的节点
        if (component instanceof BaseMultipleTree) {
            BaseMultipleTree _db = (BaseMultipleTree) component;
            return _db.getSelNodes();
        }
        if (component instanceof BaseChosenbox) {
            BaseChosenbox chosenbox = (BaseChosenbox) component;
            return chosenbox.getSelectedObjs();
        }
        if (component instanceof BaseImagebox) {
            BaseImagebox chosenbox = (BaseImagebox) component;
            return chosenbox.getPageValue();
        }

        return null;
    }

    /**
     * <p>方法:component2Label 获取控件的label </p>
     * <ul>
     * <li> @param component 空间</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/18 21:47  </li>
     * </ul>
     */
    public static String component2Label(Component component) {
        if (component instanceof BaseListbox) {
            BaseListbox _db = (BaseListbox) component;
            if (null != _db.getSelectedItem())
                return _db.getSelectedItem().getLabel();
        }
        if (component instanceof BaseBandbox) {
            BaseBandbox _db = (BaseBandbox) component;
            return _db.getText();
        }
        if (component instanceof BaseCheckbox) {
            BaseCheckbox _db = (BaseCheckbox) component;
            if (_db.isChecked())
                return _db.getLabel();
        }
        return "";
    }

    public static FilterModel doFilterFromComponent(Component component) {
        String filterName = "";
        if (component instanceof ComponentSer) {
            ComponentSer componentSer = (ComponentSer) component;
            if (!TextUtils.isEmpty(componentSer.getPgvo().getKjQueryAttribute()))
                filterName = componentSer.getPgvo().getKjQueryAttribute();
            else if (!TextUtils.isEmpty(componentSer.getPgvo().getKjBindKey()))
                filterName = componentSer.getPgvo().getKjBindKey();
            else
                filterName = componentSer.getPgvo().getKjAttribute();
        }
        if (component instanceof BaseTextbox) {
            BaseTextbox textbox = (BaseTextbox) component;
            if (!TextUtils.isEmpty(textbox.getText())) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(textbox.getText());
                return FilterModel.instance(filterName, textbox.getPgvo().getKjFilterType(), datas);
            }
        }
        if (component instanceof BaseIntbox) {
            BaseIntbox comp = (BaseIntbox) component;
            if (null != comp.getValue()) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(comp.getValue());
                return FilterModel.instance(filterName, comp.getPgvo().getKjFilterType(), datas);
            }
        }
        if (component instanceof BaseCheckbox) {
            BaseCheckbox comp = (BaseCheckbox) component;
            if (comp.isChecked()) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(1);
                return FilterModel.instance(filterName, comp.getPgvo().getKjFilterType(), datas);
            }
        }
        if (component instanceof BaseLabel) {
            BaseLabel comp = (BaseLabel) component;
            if (!TextUtils.isEmpty(comp.getValue())) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(comp.getValue());
                return FilterModel.instance(filterName, comp.getPgvo().getKjFilterType(), datas);
            }
        }
        if (component instanceof BaseDoublebox) {
            BaseDoublebox comp = (BaseDoublebox) component;
            if (null != comp.getValue()) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(comp.getValue());
                return FilterModel.instance(filterName, comp.getPgvo().getKjFilterType(), datas);
            }
        }
        if (component instanceof BaseLongbox) {
            BaseLongbox comp = (BaseLongbox) component;
            if (null != comp.getValue()) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(comp.getValue());
                return FilterModel.instance(filterName, comp.getPgvo().getKjFilterType(), datas);
            }
        }
        if (component instanceof BaseDecimalbox) {
            BaseDecimalbox comp = (BaseDecimalbox) component;
            if (null != comp.getValue()) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(comp.getValue());
                return FilterModel.instance(filterName, comp.getPgvo().getKjFilterType(), datas);
            }
        }

        if (component instanceof BaseDatebox) {
            BaseDatebox comp = (BaseDatebox) component;
            String _format = comp.getFormat();
            if (null != comp.getText()) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(comp.getText());
                return FilterModel.instance(filterName, comp.getPgvo().getKjFilterType(), datas);
            }

        }
        if (component instanceof BaseTimebox) {
            BaseTimebox _db = (BaseTimebox) component;
            String _format = _db.getFormat();
            if (null != _db.getText()) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(_db.getText());
                return FilterModel.instance(filterName, _db.getPgvo().getKjFilterType(), datas);
            }

        }
        if (component instanceof BaseListbox) {
            BaseListbox _db = (BaseListbox) component;
            if (null != _db.getSelectedItem() && !TextUtils.isEmpty(_db.getSelectedItem().getValue())) {
                List<Object> datas = new ArrayList<Object>(1);
                datas.add(_db.getSelectedItem().getValue());
                return FilterModel.instance(filterName, _db.getPgvo().getKjFilterType(), datas);
            }
        }
        //需要进行处理
        if (component instanceof BaseBandbox) {
            BaseBandbox _db = (BaseBandbox) component;
            if (null != _db.getSelVals() && _db.getSelVals().size() > 0) {
                List<Object> _result = new ArrayList<Object>(_db.getSelVals().size());
                if (null != _db.getBandboxVo() && !TextUtils.isEmpty(_db.getBandboxVo().getControlInt())) {
                    //表示自定义的bandbox
                    for (Map<String, Object> map : _db.getSelVals()) {
                        _result.add(map.get(_db.getBandboxVo().getControlInt()));
                    }
                } else {
                    for (Map<String, Object> map : _db.getSelVals()) {
                        _result.add(map);
                    }
                }

                return FilterModel.instance(filterName, _db.getPgvo().getKjFilterType(), _result);
            }
        }
        if (component instanceof Base2Datebox) {
            Base2Datebox _db = (Base2Datebox) component;
            if (null != _db.getSelVals()) {
                if (null != _db.getSelVals())
                    return FilterModel.instance(filterName, _db.getPgvo().getKjFilterType(), _db.getSelVals());
            }
        }
        if (component instanceof BaseTree) {
            BaseTree tree = (BaseTree) component;
            Treeitem treeitem = tree.getSelectedItem();
            if (TextUtils.isEmpty(tree.getProperty()))
                return null;
            Object _obj = null;
            if (null != treeitem) {
                TreeVO treeVO = treeitem.getValue();
                if (null != treeVO) {
                    if (!TextUtils.isEmpty(tree.getValProperty())) {
                        try {
                            _obj = BeanUtil.getProperty(treeVO, tree.getValProperty());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        _obj = treeVO.getOwid();
                    }
                }
            }
            if (null != _obj) {
                if (TextUtils.isEmpty(tree.getFilterType()))
                    tree.setFilterType("0");
                List<Object> objs = new ArrayList<Object>(1);
                objs.add(_obj);
                return FilterModel.instance(tree.getProperty(), Integer.parseInt(tree.getFilterType()), objs);
            }
        }
        return null;
    }


    /**
     * <p>
     * 绑定控件根据不同的控件类型绑定控件
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DataBinder.java,v 0.1 Nov 3, 2010 10:55:48 PM Jack Exp $
     */
    @SuppressWarnings("unchecked")
    public static void bind(String id, Object value, Object source, Component comp, int target) {

        if (null != comp) {
            if (target == DataBinder.OBJ_TO_PAGE)
                binderToPage(id, value, comp);
            if (target == DataBinder.PAGE_TO_OBJ)
                binderToObj(id, source, comp);
            if (target == DataBinder.PAGE_TO_MAP)
                binderToMap(id, source, comp);
        }
    }

    @SuppressWarnings("unchecked")
    public static void binderToPage(String id, Object value, Component comp) {
        if (null != comp) {
            //if (!TextUtils.isEmpty(value)) {
            if (comp instanceof Label) {
                Label lb = (Label) comp;
                lb.setValue(value.toString());
            }
            if (comp instanceof Textbox) {
                Textbox tb = (Textbox) comp;
                tb.clearErrorMessage();//防止校验
                tb.setText(value.toString());
                tb.setValue(value.toString());
            }
            if (comp instanceof Intbox) {
                Intbox ib = (Intbox) comp;
                ib.clearErrorMessage();
                ib.setText(value.toString());
                ib.setValue(Integer.parseInt(value.toString()));
            }
            if (comp instanceof Decimalbox) {
                Decimalbox db = (Decimalbox) comp;
                db.clearErrorMessage();
                db.setText(value.toString());
                db.setValue(value.toString());
            }
            if (comp instanceof Longbox) {
                Longbox ib = (Longbox) comp;
                ib.clearErrorMessage();
                ib.setText(value.toString());
                ib.setValue(Long.parseLong(value.toString()));
            }
            if (comp instanceof Doublebox) {
                Doublebox db = (Doublebox) comp;
                db.clearErrorMessage();
                db.setText(value.toString());
                db.setValue((Double) value);
            }
            if (comp instanceof Datebox) {
                Datebox tb = (Datebox) comp;
                tb.clearErrorMessage();
                if (value instanceof Date) {
                    tb.setText(DateUtil.getDateString((Date) value, "yyyy-MM-dd"));
                } else
                    tb.setText(value.toString());
            }
            if (comp instanceof Timebox) {
                Timebox tb = (Timebox) comp;
                tb.clearErrorMessage();
                tb.setText(value.toString());
            }
            if (comp instanceof Listbox) {
                Listbox lb = (Listbox) comp;
                List items = lb.getItems();
                for (Iterator iterator = items.iterator(); iterator.hasNext(); ) {
                    Listitem item = (Listitem) iterator.next();
                    if (null != item.getValue() && item.getValue().toString().equals(value.toString())) {
                        item.setSelected(true);
                        break;
                    }
                }
            }
            if (comp instanceof Combobox) {
                Combobox cb = (Combobox) comp;
                List items = cb.getItems();
                for (Iterator iterator = items.iterator(); iterator.hasNext(); ) {
                    Comboitem item = (Comboitem) iterator.next();
                    if (null != item.getValue() && item.getValue().toString().equals(value.toString())) {
                        cb.setSelectedItem(item);
                        break;
                    }
                }
            }
            for (Iterator iterator1 = ZKConstants.SYSTEM_BANDBOX.iterator(); iterator1.hasNext(); ) {
                String c = (String) iterator1.next();
                // System.out.println(comp.getClass().getName());
                if (comp.getClass().getName().equals(c))
                    try {
                        Class cls = Class.forName(c);
                        Method method = cls.getMethod("showDefault", String.class);
                        method.invoke(comp, value);
                    } catch (Exception e) {
                        continue;
                    }
            }
                /*
                 * if (comp instanceof TermBandBox) { TermBandBox b =
				 * (TermBandBox) comp; b.showDefault(value.toString()); } if
				 * (comp instanceof AppBandBox) { AppBandBox b = (AppBandBox)
				 * comp; b.showDefault(value.toString()); }
				 */
            if (comp instanceof Base2CheckboxDecimal) {
                Base2CheckboxDecimal _db = (Base2CheckboxDecimal) comp;
                _db.setDefault(value.toString());
            }
            if (comp instanceof BaseMultipFileUpload) {
                BaseMultipFileUpload _db = (BaseMultipFileUpload) comp;
                _db.setFileNum(value.toString());
            }

            //}

        }
    }

    /**
     * <p>
     * 把界面的值绑定到具体的对象
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DataBinder.java,v 0.1 Nov 4, 2010 11:05:01 AM Jack Exp $
     */
    @SuppressWarnings("unchecked")
    public static void binderToObj(String id, Object source, Component comp) throws WrongValueException {
        try {
            if (null != comp) {
                if (comp instanceof Textbox) {
                    BeanUtils.setProperty(source, id, ((Textbox) comp).getValue());
                }
                if (comp instanceof Intbox)
                    BeanUtils.setProperty(source, id, ((Intbox) comp).getValue());
                if (comp instanceof Longbox)
                    BeanUtils.setProperty(source, id, ((Longbox) comp).getValue());
                if (comp instanceof Doublebox)
                    BeanUtils.setProperty(source, id, ((Doublebox) comp).getValue());
                if (comp instanceof Datebox) {
                    ((Datebox) comp).getValue(); // getValue 是zk做校验用的
                    if (id.indexOf("Str") > 0) {
                        BeanUtils.setProperty(source, id, ((Datebox) comp).getText());
                    } else {
                        String _text = ((Datebox) comp).getText();
                        Date _d = DateUtil.getDate(_text, "yyyy-MM-dd");
                        BeanUtils.setProperty(source, id, _d);
                    }
                }
                if (comp instanceof Timebox) {
                    ((Timebox) comp).getValue();
                    BeanUtils.setProperty(source, id, ((Timebox) comp).getText());
                }
                if (comp instanceof Listbox) {
                    Listbox lb = (Listbox) comp;
                    Listitem it = lb.getSelectedItem();
                    if (null != it && !TextUtils.isEmpty(it.getValue())) {
                        BeanUtils.setProperty(source, id, it.getValue());
                    }
                }
                if (comp instanceof Combobox) {
                    Combobox cb = (Combobox) comp;
                    Comboitem item = cb.getSelectedItem();
                    if (null != item && !TextUtils.isEmpty(item.getValue())) {
                        BeanUtils.setProperty(source, id, item.getValue());
                    }
                }
                if (comp instanceof Base2CheckboxDecimal) {
                    Base2CheckboxDecimal _db = (Base2CheckboxDecimal) comp;
                    BeanUtils.setProperty(source, id, _db.getDefaultVal());
                }
                /*
                 * if (comp instanceof TermBandBox) { TermBandBox b =
				 * (TermBandBox) comp; BeanUtils.setProperty(source, id,
				 * b.getObjValue()); } if (comp instanceof AppBandBox) {
				 * AppBandBox b = (AppBandBox) comp;
				 * BeanUtils.setProperty(source, id, b.getObjValue()); }
				 */
                for (Iterator iterator = ZKConstants.SYSTEM_BANDBOX.iterator(); iterator.hasNext(); ) {
                    String c = (String) iterator.next();
                    if (comp.getClass().getName().equals(c))
                        try {
                            Class cls = Class.forName(c);
                            Method method = cls.getMethod("getObjValue", (Class[]) null);
                            Object obj = method.invoke(comp, (Object[]) null);
                            BeanUtils.setProperty(source, id, obj);
                        } catch (Exception exception) {
                            continue;
                        }
                }

            }
        } catch (WrongValueException e1) {
            throw e1;// 如果是校验异常，则抛出
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * 页面绑定到Map
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DataBinder.java,v 0.1 Nov 19, 2010 1:16:49 PM Jack Exp $
     */
    @SuppressWarnings("unchecked")
    public static void binderToMap(String id, Object source, Component comp) {
        try {
            if (null != comp) {
                Map map = (Map) source;
                if (comp instanceof Textbox)
                    map.put(id, ((Textbox) comp).getValue());
                if (comp instanceof Intbox)
                    map.put(id, ((Intbox) comp).getValue());
                if (comp instanceof Longbox)
                    map.put(id, ((Longbox) comp).getValue());
                if (comp instanceof Doublebox)
                    map.put(id, ((Doublebox) comp).getValue());
                if (comp instanceof Datebox) {
                    ((Datebox) comp).getValue();
                    map.put(id, ((Datebox) comp).getText());
                }
                if (comp instanceof Timebox) {
                    ((Timebox) comp).getValue();
                    map.put(id, ((Timebox) comp).getText());
                }
                if (comp instanceof Listbox) {
                    Listbox lb = (Listbox) comp;
                    Listitem it = lb.getSelectedItem();
                    if (null != it && !TextUtils.isEmpty(it.getValue())) {
                        map.put(id, it.getValue());
                    }
                }
                if (comp instanceof Combobox) {
                    Combobox cb = (Combobox) comp;
                    Comboitem item = cb.getSelectedItem();
                    if (null != item && !TextUtils.isEmpty(item.getValue())) {
                        map.put(id, item.getValue());
                    }
                }
                if (comp instanceof Base2CheckboxDecimal) {
                    Base2CheckboxDecimal _db = (Base2CheckboxDecimal) comp;
                    map.put(id, _db.getDefaultVal());
                }
                /*
                 * if (comp instanceof TermBandBox) { TermBandBox b =
				 * (TermBandBox) comp; map.put(id, b.getObjValue()); } if (comp
				 * instanceof AppBandBox) { AppBandBox b = (AppBandBox) comp;
				 * map.put(id, b.getObjValue()); }
				 */
                for (Iterator iterator = ZKConstants.SYSTEM_BANDBOX.iterator(); iterator.hasNext(); ) {
                    String c = (String) iterator.next();
                    if (comp.getClass().getName().equals(c))
                        try {
                            Class cls = Class.forName(c);
                            Method method = cls.getMethod("getObjValue", (Class[]) null);
                            Object obj = method.invoke(comp, (Object[]) null);
                            map.put(id, obj);
                        } catch (Exception exception) {
                            continue;
                        }
                }

            }
        } catch (WrongValueException e1) {
            throw e1;// 如果是校验异常，则抛出
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //重置输入框的内容
    public static void resetAllcomponents(BaseWindow window) {
        Collection<Component> comps = window.getFellows();
        for (Component comp : comps) {
            if (comp instanceof ComponentSer) {
                if (comp instanceof BaseButton)
                    continue;
                else {
                    ComponentSer componentSer = (ComponentSer) comp;
                    componentSer.reset();
                }
            }
        }
    }

    /**
     * <p>
     * <p>
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DataBinder.java,v 0.1 Nov 3, 2010 10:40:31 PM Jack Exp $
     */
    public static void main(String[] args) {

    }

}
