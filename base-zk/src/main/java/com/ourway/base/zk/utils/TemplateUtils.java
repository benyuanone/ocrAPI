package com.ourway.base.zk.utils;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>方法 PageUtils : <p>
 * <p>说明:页面处理相关方法</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/3 20:20
 * </pre>
 */
public class TemplateUtils implements Serializable {

    //用于创建表单
    public void doRowComponents(List<PageControlVO> subComps, Div div, PageLayoutVO data, BaseWindow window) {
        Map<String, Component> groupMap = new HashMap<String, Component>();
        if (null == subComps || subComps.size() == 0)
            return;
        for (PageControlVO subComp : subComps) {
            //用div创建一行。
            doCreateDiv(div, subComp, data, window, groupMap);
//            createComponent(subComp, row, data, divAlign);
        }
    }

    public void doButtonRowComponents(List<PageControlVO> subComps, Div div, PageLayoutVO data, BaseWindow window) {
        Map<String, Component> groupMap = new HashMap<String, Component>();
        Collections.sort(subComps, new Comparator<PageControlVO>() {
            @Override
            public int compare(PageControlVO o1, PageControlVO o2) {
                int flag = o1.getLayoutComponent().getCompHbox() - o2.getLayoutComponent().getCompHbox();
                if (flag == 0) {
                    return o1.getLayoutComponent().getCompOrder() - o2.getLayoutComponent().getCompOrder();
                } else
                    return flag;
            }
        });
        for (PageControlVO subComp : subComps) {
            //用div创建一行。
            doCreateButtonDiv(div, subComp, data, window, groupMap);
        }
    }

    /*创建按钮控制区*/
    public void doCreateButtonDiv(Div div, PageControlVO comp, PageLayoutVO data, BaseWindow window, Map<String, Component> groupMap) {
        if (null == comp || null == comp.getLayoutComponent() || null == comp.getLayoutComponent().getCompCols()) {
            AlterDialog.alert("控件：" + comp.getKjName() + "未设置列数");
            return;
        }
        /*控件的id*/
        String compId = data.getControlId() + BaseWindow.COMP_CONTACT + comp.getKjAttribute();
        int cols = comp.getLayoutComponent().getCompCols(); //获取列数
        String position = "left";
        //创建控件
        Component component = doAddComponent(comp, compId, window);
        //如果是分组的控件，则放入同一个div
        if (cols == 0 && null != comp.getLayoutComponent() && null != comp.getLayoutComponent().getCompHbox() && comp.getLayoutComponent().getCompHbox() > 0) {
            //说明是组合在一起的控件
            String _hboxKey = data.getControlId() + "_hbox_" + comp.getLayoutComponent().getCompHbox();
            Component component1 = groupMap.get(_hboxKey);
            if (TextUtils.isEmpty(component1)) {
                //如果是第二个分组，则直接创建分组。
                Div _div = ComponentUtils.doCreateGroupDiv();
                //先获取相同的上级div分组
                Component groupDiv = groupMap.get(div.getId() + "_groupDiv");
                if (null != groupDiv)
                    _div.setParent(groupDiv);
                else
                    _div.setParent(div);
                _div.setStyle(ZKConstants.DIV_GROUP_SEPERATE_STYLE);
                groupMap.put(_hboxKey, _div);
                component.setParent(_div);
            } else
                component.setParent(component1);
            return;
        }
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompPosition())) {
            switch (comp.getLayoutComponent().getCompPosition()) {
                case 0:
                    position = "left";
                    break;
                case 1:
                    position = "right";
                    break;
                case 2:
                    position = "center";
                    break;
            }
        }
//            position = comp.getLayoutComponent().getCompPosition();
        //创建内部的div
        Div subDiv = new Div();
        subDiv.setClass("col-sm-" + cols);
        subDiv.setStyle("text-align:" + position);
        subDiv.setParent(div);
        groupMap.put(div.getId() + "_groupDiv", subDiv);
        //创建按钮分组
        Div buttonGroup = ComponentUtils.doCreateGroupDiv();
        buttonGroup.setParent(subDiv);
        component.setParent(buttonGroup);
        if (null != comp.getLayoutComponent().getCompHbox() && comp.getLayoutComponent().getCompHbox() > 0) {
            String _hboxKey = data.getControlId() + "_hbox_" + comp.getLayoutComponent().getCompHbox();
            groupMap.put(_hboxKey, buttonGroup);
        }


    }

    /*创建表单的布局采用此方式*/
    public void doCreateDiv(Div div, PageControlVO comp, PageLayoutVO data, BaseWindow window, Map<String, Component> groupMap) {
        if (null == comp || null == comp.getLayoutComponent() || null == comp.getLayoutComponent().getCompCols()) {
            AlterDialog.alert("控件：" + comp.getKjName() + "未设置列数");
            return;
        }
        /*控件的id*/
        String compId = data.getControlId() + BaseWindow.COMP_CONTACT + comp.getKjAttribute();
        int cols = comp.getLayoutComponent().getCompCols(); //获取列数
        //创建控件
        Component component = doAddComponent(comp, compId, window);
        //如果是分组的控件，则放入同一个div
        if (cols == 0 && null != comp.getLayoutComponent() && null != comp.getLayoutComponent().getCompHbox() && comp.getLayoutComponent().getCompHbox() > 0) {
            //说明是组合在一起的控件
            String _hboxKey = data.getControlId() + "_hbox_" + comp.getLayoutComponent().getCompHbox();
            Component component1 = groupMap.get(_hboxKey);
            if (TextUtils.isEmpty(component1)) {
                AlterDialog.alert("分组控件[" + data.getControlId() + "]设置错误，第一个控件必须有一定的列数");
                return;
            }
            component.setParent(component1);
        }


        //判断是否必填
        boolean required = (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint()) && comp.getLayoutComponent().getKjConstraint().contains("no empty"));


        //判断是否有label
        if (null != comp.getLayoutComponent().getCompLable() && comp.getLayoutComponent().getCompLable() > 0) {
            Div labelDiv = new Div();
            labelDiv.setParent(div);
            labelDiv.setClass("col-sm-" + comp.getLayoutComponent().getCompLable() + " control-label");
            labelDiv.setStyle(ZKConstants.DIV_COMP_STYLE_LABEL);
            //创建label
            //根据情况创建Label
            if (!TextUtils.isEmpty(comp.getKjTipsLabel())) {
                BaseLabelTip labelTip = ComponentUtils.doCreateTipsLabel(comp.getKjLabelid(), comp.getKjTipsLabel(), required);
                labelTip.setParent(labelDiv);
            } else {
                BaseLabel label = ComponentUtils.doCreateLabel(comp.getKjLabelid(), required);
                label.setParent(labelDiv);
            }
        }

        //根据不同的类型创建控件当前控件
        Div controlDiv = new Div();
        controlDiv.setParent(div);
        controlDiv.setClass("col-sm-" + comp.getLayoutComponent().getCompCols());
        controlDiv.setStyle(ZKConstants.DIV_COMP_STYLE_CONTROL);
        component.setParent(controlDiv);
        if (null != comp.getLayoutComponent().getCompHbox() && comp.getLayoutComponent().getCompHbox() > 0) {
            String _hboxKey = data.getControlId() + "_hbox_" + comp.getLayoutComponent().getCompHbox();
            groupMap.put(_hboxKey, controlDiv);
        }

    }

    /*创建内部编辑的控件*/
    public Component doInlineEditComponent(PageControlVO subComp, String compId, BaseWindow window) {
        return doCreateComponent(subComp, compId, window, true);
    }

    public Component doAddComponent(PageControlVO subComp, String compId, BaseWindow window) {
        return doCreateComponent(subComp, compId, window, false);
    }

    //用户创建不同的控件
    public Component doCreateComponent(PageControlVO subComp, String compId, BaseWindow window, Boolean editFlag) {
        Component comp = null;
        switch (subComp.getKjType()) {
            case 0:
                if (!editFlag) {
                    if (!TextUtils.isEmpty(subComp.getKjPre()) || !TextUtils.isEmpty(subComp.getKjAfter()))
                        comp = ComponentUtils.doCreatePreAfterTextbox(subComp, compId, window);
                    else
                        comp = ComponentUtils.doCreateTextbox(subComp, compId, window);
                } else
                    comp = ComponentUtils.doCreateInlineTextbox(subComp, compId, window);
                break;
            case 1:
                if (!editFlag)
                    comp = ComponentUtils.doCreateListbox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineListbox(subComp, compId, window);
                break;
            case 2:
                if (!editFlag)
                    comp = ComponentUtils.doCreateButton(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineButton(subComp, compId, window);
                break;
            case 3:
                if (!editFlag)
                    comp = ComponentUtils.doCreateBandbox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineBandbox(subComp, compId, window);


                break;
            case 4:
                if (!editFlag)
                    comp = ComponentUtils.doCreateDatebox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineDatebox(subComp, compId, window);

                break;
            case 5:
                if (!editFlag)
                    comp = ComponentUtils.doCreateIntbox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineIntbox(subComp, compId, window);
                break;
            case 6:
                if (!editFlag)
                    comp = ComponentUtils.doCreatePreAfterDecimalbox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineDecimalbox(subComp, compId, window);
                break;
            case 7:
                if (!editFlag)
                    comp = ComponentUtils.doCreateLongbox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineLongbox(subComp, compId, window);
                break;
            case 8:
                if (!editFlag)
                    comp = ComponentUtils.doCreateSingleCheckBox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineSingleCheckBox(subComp, compId, window);
                break;
            case 9:
                if (!editFlag)
                    comp = ComponentUtils.doCreate2Intbox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInline2Intbox(subComp, compId, window);

                break;
            case 10:
                if (!editFlag)
                    comp = ComponentUtils.doCreateTimeBox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineTimeBox(subComp, compId, window);
                break;
            case 11:
                if (!editFlag)
                    comp = ComponentUtils.doCreateDoubleSpinner(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineDoubleSpinner(subComp, compId, window);
                break;
            case 12:
                if (!editFlag)
                    comp = ComponentUtils.doCreateSpinner(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineSpinner(subComp, compId, window);
                break;
            case 13://隐藏域
                comp = ComponentUtils.doCreateHidden(subComp, compId, window);
                break;
            case 14://时间段
                if (!editFlag)
                    comp = ComponentUtils.doCreate2Datebox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInline2Datebox(subComp, compId, window);
                break;
            case 15:
                if (!editFlag)
                    comp = ComponentUtils.doCreate2Doublebox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInline2Doublebox(subComp, compId, window);
                break;
            case 16:
                if (!editFlag)
                    comp = ComponentUtils.doCreate2Timebox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInline2Timebox(subComp, compId, window);
                break;
            case 17:
                comp = ComponentUtils.doCreateWells(subComp, compId, window);
                break;
            case 18:
                comp = ComponentUtils.doCreateCheckBoxDecimal(subComp, compId, window);
                break;
            case 19:
                if (!editFlag)
                    comp = ComponentUtils.doCreateFileUpload(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineFileUpload(subComp, compId, window);
                break;
            case 20:
                if (!editFlag)
                    comp = ComponentUtils.doCreateMultipFileUpload(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateMultipInlineFileUpload(subComp, compId, window);
                break;
            case 21:
                comp = ComponentUtils.doCreateImage(subComp, compId, window);
                break;
            case 22:
                if (!editFlag)
                    comp = ComponentUtils.doCreateCombobox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineCombobox(subComp, compId, window);
                break;
            case 23:
                comp = ComponentUtils.doCreateMultipleTree(subComp, compId, window);
                break;
            case 24:
                if (!editFlag)
                    comp = ComponentUtils.doCreateChosenbox(subComp, compId, window);
                else
                    comp = ComponentUtils.doCreateInlineChosenbox(subComp, compId, window);
                break;
            case 25://图片上传控件
                comp = ComponentUtils.doCreateBaseImageBox(subComp, compId, window);
                break;

        }


        return comp;
    }

    //隐藏或者显示查询条件框
    public void showOrHideConditionDiv(boolean showFlag, BaseWindow window, List<String> divIds) {
        for (String divId : divIds) {
            Component component = window.getFellowIfAny(divId);
            if (null != component) {
                component.setVisible(showFlag);
            }
        }
    }


    /*创建表格内部需要用到的输入框，主要用于展现数据的*/
    public static BaseTextbox doCreateInGridTextbox() {
        BaseTextbox label = new BaseTextbox();
        label.setReadonly(true);
        label.setInplace(true);
        label.setWidth("100%");
        label.setStyle(ZKConstants.GRID_INLINE_DEFAULT_TEXTBOX_CSS);
        return label;
    }

    /**
     * <p>方法:doGenOrderForSet 按照每行的大小进行排序，传入map的string可以，key必须是数字类型 </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.List<java.lang.String>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/8/14 15:08  </li>
     * </ul>
     */
    public static List<String> doGenOrderForSet(Map<String, List<PageControlVO>> dataMap) {
        Set<String> set = dataMap.keySet();
        List<String> setList = new ArrayList<String>(set);
        Collections.sort(setList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int _o1 = Integer.parseInt(o1);
                int _o2 = Integer.parseInt(o2);
                return _o1 - _o2;
            }
        });
        return setList;
    }
}
