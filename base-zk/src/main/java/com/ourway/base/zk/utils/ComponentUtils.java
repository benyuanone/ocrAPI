package com.ourway.base.zk.utils;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;
import org.zkoss.zul.Image;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ComponentUtils {

    /**
     * <p>方法:getMaxOrder 获取最大序号 </p>
     * <ul>
     * <li> @param pn 控件</li>
     * <li>@return int  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/29 9:52  </li>
     * </ul>
     */
    public static int getMaxOrder(Panelchildren pn) {
        int maxOrder = 0;
        double _tmp = 0;
        List<Component> components = pn.getChildren();
        for (Component component : components) {
            if (component instanceof BaseButton) {
                BaseButton button = (BaseButton) component;
                if (null != button.getExternObject() && !TextUtils.isEmpty(button.getExternObject().get("compOrder"))) {
                    _tmp = Double.parseDouble(button.getExternObject().get("compOrder").toString());
                    if (maxOrder < _tmp)
                        maxOrder = (int) _tmp;
                }
            }
        }
        maxOrder++;
        return maxOrder;
    }

    public static BaseLabel doCreateLabel(String val, boolean required) {
        BaseLabel label = new BaseLabel();
        label.setValue(val);
        label.displayWithI18n(val);
        if (required) {
            label.setValue(label.getValue() + "*");
            label.setStyle(ZKConstants.DIV_COMP_REQUIRED_LABEL);
        }
        return label;
    }

    public static BaseLabelTip doCreateTipsLabel(String val, String txtLabel, boolean required) {
        BaseLabelTip label = new BaseLabelTip();
        label.onCreate(val, txtLabel, required);
        return label;
    }

    public static BaseTextbox doCreateHidden(PageControlVO comp, String compId, BaseWindow window) {
        BaseTextbox textbox = doCreateTextbox(comp, compId, window);
        textbox.setVisible(false);
        return textbox;
    }

    public static BaseTextbox doCreateTextbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseTextbox textbox = new BaseTextbox();
        if (!TextUtils.isEmpty(compId))
            textbox.setId(compId);
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            textbox.setClass(comp.getLayoutComponent().getCompCss());
        else
            textbox.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
        textbox.setProperty(comp.getKjAttribute());
//        textbox.setPlaceholder(I18nUtil.getLabelContent(comp.getKjLabelid()));
        if (null != comp.getLayoutComponent().getCompRowsnum() && comp.getLayoutComponent().getCompRowsnum() > 1)
            textbox.setRows(comp.getLayoutComponent().getCompRowsnum());
        //控件的扩展属性
        setComponentExpandPropeties(comp, textbox);

        //控件校验
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            textbox.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        //是否只读
        if (window.getWindowType() == 2) {
            textbox.setDisabled(true);
            textbox.setAttribute("windowType", 2);
        }


        //判断是否要初始化
        textbox.setPgvo(comp);
        textbox.init(comp, window);
        textbox.addEventListiner(comp, window);
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            textbox.setDisabled(true);
        return textbox;
    }

    public static BaseTextbox doCreateInlineTextbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseTextbox textbox = new BaseTextbox();
        if (!TextUtils.isEmpty(compId))
            textbox.setId(compId);
        textbox.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        textbox.setProperty(comp.getKjAttribute());
//        textbox.setPlaceholder(I18nUtil.getLabelContent(comp.getKjLabelid()));
        //控件的扩展属性
        setComponentExpandPropeties(comp, textbox);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            textbox.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        //是否只读
        if (window.getWindowType() == 2) {
            textbox.setDisabled(true);
            textbox.setAttribute("windowType", 2);
        }
        textbox.setPgvo(comp);
        textbox.init(comp, window);
        textbox.addEventListiner(comp, window);
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            textbox.setDisabled(true);
        return textbox;
    }

    //    创建有前缀或者后缀或者都有的输入框
    public static Div doCreatePreAfterTextbox(PageControlVO comp, String compId, BaseWindow window) {
        Div div = new Div();
        div.setClass("input-group");
        if (!TextUtils.isEmpty(comp.getKjPre())) {
            Span span = new Span();
            span.setParent(div);
            span.setClass("input-group-addon");
            BaseLabel label = new BaseLabel();
            label.setValue(I18nUtil.getLabelContent(comp.getKjPre()));
            label.setParent(span);
        }
        BaseTextbox textbox = doCreateTextbox(comp, compId, window);
        textbox.setParent(div);
        if (!TextUtils.isEmpty(comp.getKjAfter())) {
            Span span = new Span();
            span.setParent(div);
            span.setClass("input-group-addon");
            BaseLabel label = new BaseLabel();
            label.setValue(I18nUtil.getLabelContent(comp.getKjAfter()));
            label.setParent(span);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            textbox.setDisabled(true);
        return div;
    }


    public static BaseListbox doCreateListbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseListbox listbox = new BaseListbox();
        if (!TextUtils.isEmpty(compId))
            listbox.setId(compId);
        listbox.setMold("select");
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            listbox.setClass(comp.getLayoutComponent().getCompCss());
        else
            listbox.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
        listbox.setProperty(comp.getKjAttribute());
        //控件的扩展属性
        setComponentExpandPropeties(comp, listbox);
        listbox.init(comp, window);
        listbox.setPgvo(comp);
        listbox.addEventListiner(comp, window);
        //是否只读
        if (!listbox.isDisabled())
            if (window.getWindowType() == 2) {
                listbox.setDisabled(true);
                listbox.setAttribute("windowType", 2);
            }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            listbox.setDisabled(true);
        return listbox;
    }

    public static BaseListbox doCreateInlineListbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseListbox listbox = new BaseListbox();
        if (!TextUtils.isEmpty(compId))
            listbox.setId(compId);
        listbox.setMold("select");
        listbox.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        listbox.setProperty(comp.getKjAttribute());
        //控件的扩展属性
        setComponentExpandPropeties(comp, listbox);
        listbox.init(comp, window);
        listbox.setPgvo(comp);
        listbox.addEventListiner(comp, window);
        //是否只读
        if (window.getWindowType() == 2) {
            listbox.setDisabled(true);
            listbox.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            listbox.setDisabled(true);
        return listbox;
    }

    public static BaseCombobox doCreateCombobox(PageControlVO comp, String compId, BaseWindow window) {
        BaseCombobox listbox = new BaseCombobox();
        if (!TextUtils.isEmpty(compId))
            listbox.setId(compId);
        listbox.setAutodrop(true);
        listbox.setButtonVisible(true);
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            listbox.setClass(comp.getLayoutComponent().getCompCss());
        else
            listbox.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
        listbox.setProperty(comp.getKjAttribute());
        //控件的扩展属性
        setComponentExpandPropeties(comp, listbox);
        listbox.init(comp, window);
        listbox.setPgvo(comp);
        listbox.addEventListiner(comp, window);
        //是否只读
        if (!listbox.isDisabled())
            if (window.getWindowType() == 2) {
                listbox.setDisabled(true);
                listbox.setAttribute("windowType", 2);
            }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            listbox.setDisabled(true);
        return listbox;
    }

    public static BaseCombobox doCreateInlineCombobox(PageControlVO comp, String compId, BaseWindow window) {
        BaseCombobox listbox = new BaseCombobox();
        if (!TextUtils.isEmpty(compId))
            listbox.setId(compId);
        listbox.setAutodrop(true);
        listbox.setButtonVisible(true);
        listbox.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        listbox.setProperty(comp.getKjAttribute());
        //控件的扩展属性
        setComponentExpandPropeties(comp, listbox);
        listbox.init(comp, window);
        listbox.setPgvo(comp);
        listbox.addEventListiner(comp, window);
        //是否只读
        if (window.getWindowType() == 2) {
            listbox.setDisabled(true);
            listbox.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            listbox.setDisabled(true);
        return listbox;
    }

    public static BaseChosenbox doCreateChosenbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseChosenbox listbox = new BaseChosenbox();
        if (!TextUtils.isEmpty(compId))
            listbox.setId(compId);

        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            listbox.setClass(comp.getLayoutComponent().getCompCss());
        else
            listbox.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
        if (null != comp.getLayoutComponent().getCompLength() && comp.getLayoutComponent().getCompLength() > 0)
            listbox.setStyle("width:" + comp.getLayoutComponent().getCompLength() + "px");
        listbox.setProperty(comp.getKjAttribute());
        //控件的扩展属性
        setComponentExpandPropeties(comp, listbox);
        listbox.init(comp, window);
        listbox.setPgvo(comp);
        listbox.addEventListiner(comp, window);
        //是否只读
        if (!listbox.isDisabled())
            if (window.getWindowType() == 2) {
                listbox.setDisabled(true);
                listbox.setAttribute("windowType", 2);
            }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            listbox.setDisabled(true);
        return listbox;
    }

    public static BaseChosenbox doCreateInlineChosenbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseChosenbox listbox = new BaseChosenbox();
        if (!TextUtils.isEmpty(compId))
            listbox.setId(compId);
        listbox.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        listbox.setProperty(comp.getKjAttribute());
        //控件的扩展属性
        setComponentExpandPropeties(comp, listbox);
        if (null != comp.getLayoutComponent().getCompLength() && comp.getLayoutComponent().getCompLength() > 0)
            listbox.setStyle("width:" + comp.getLayoutComponent().getCompLength() + "px");
        listbox.init(comp, window);
        listbox.setPgvo(comp);
        listbox.addEventListiner(comp, window);
        //是否只读
        if (window.getWindowType() == 2) {
            listbox.setDisabled(true);
            listbox.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            listbox.setDisabled(true);
        return listbox;
    }

    public static BaseImagebox doCreateBaseImageBox(PageControlVO comp, String compId, BaseWindow window) {
        BaseImagebox listbox = new BaseImagebox();
        if (!TextUtils.isEmpty(compId))
            listbox.setId(compId);

        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            listbox.setClass(comp.getLayoutComponent().getCompCss());
//        else
//            listbox.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
        if (null != comp.getLayoutComponent().getCompLength() && comp.getLayoutComponent().getCompLength() > 0)
            listbox.setStyle("width:" + comp.getLayoutComponent().getCompLength() + "px");
        listbox.setProperty(comp.getKjAttribute());
        //控件的扩展属性
        setComponentExpandPropeties(comp, listbox);
        listbox.init(comp, window);
        listbox.setPgvo(comp);
        listbox.addEventListiner(comp, window);
        listbox.onCreate();
        //是否只读
        if (window.getWindowType() == 2) {
            listbox.setComponentDisable(true);
            listbox.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            listbox.setComponentDisable(true);
        return listbox;
    }

    public static BaseButton doCreateButton(PageControlVO comp, String compId, BaseWindow window) {
        BaseButton button = new BaseButton();
        button.setMold("bs");
        if (!TextUtils.isEmpty(compId))
            button.setId(compId);
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            button.setClass(comp.getLayoutComponent().getCompCss());
        else {
            if (!TextUtils.isEmpty(comp.getKjDefaultcss()))
                button.setSclass(comp.getKjDefaultcss());
        }
        if (TextUtils.isEmpty(button.getSclass()))
            button.setSclass(ZKConstants.DIV_BUTTON_DEFAULT_CSS);
        if (!TextUtils.isEmpty(comp.getKjIconclass()))
            button.setIconSclass(comp.getKjIconclass());
        button.setProperty(comp.getKjAttribute());
        button.setLabel(I18nUtil.getLabelContent(comp.getKjLabelid()));
        button.init(comp, window);
        button.setPgvo(comp);
        button.addEventListiner(comp, window);
        //是否只读
//        if (window.getWindowType() == 2) {
//            if (comp.getKjAttribute().equals("updateBtn") || comp.getKjAttribute().equals("refreshBtn") || comp.getKjAttribute().equals("exportBtn") || comp.getKjAttribute().equals("closeBtn") || comp.getKjAttribute().equals("attachBtn"))
//                button.setDisabled(false);
//            else {
//                button.setDisabled(true);
//                button.setAttribute("windowType", 2);
//            }
//        }
        //处理下拉框的
        if (!TextUtils.isEmpty(comp.getKjDefaultData())) {
            if (!TextUtils.isEmpty(button.getClass())) {
                button.setClass(button.getClass() + " dropdown-toggle");
            } else
                button.setClass("dropdown-toggle");
//            if(!TextUtils.isEmpty(button.getIconSclass())){
//                button.setIconSclass(button.getIconSclass()+" caret");
//            }else
            button.setIconSclass("caret");
            button.setDir("reverse");
            handleRightMenuOfButton(comp.getKjDefaultData().toString(), window, button);
        }
        return button;
    }

    private static void handleRightMenuOfButton(String defaultData, BaseWindow window, BaseButton button) {
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(defaultData);
        Menupopup menupopup = new Menupopup();
        menupopup.setId(button.getId() + "_menupopup");
        menupopup.setParent(window);
        for (Map<String, Object> map : paramsList) {
            if (map.get("type").toString().equals("1")) {
                BaseMenuitem menuitem = new BaseMenuitem();
                menuitem.setAttribute("param", map);
                menuitem.setLabel(I18nUtil.getLabelContent(map.get("label").toString()));
//                menuitem.addEventListener(Events.ON_CLICK, window);
                menuitem.setHref(map.get("link").toString());
                menuitem.setParent(menupopup);
            }
            if (map.get("type").toString().equals("2") || map.get("type").toString().equals("3")) {
                BaseMenuitem menuitem = new BaseMenuitem();
                menuitem.setAttribute("param", map);
                menuitem.setLabel(I18nUtil.getLabelContent(map.get("label").toString()));
                menuitem.setParent(menupopup);
                menuitem.addEventListener(Events.ON_CLICK, window);
            }
        }
        button.setPopup(button.getId() + "_menupopup, after_start");
    }


    public static BaseButton doCreateInlineButton(PageControlVO comp, String compId, BaseWindow window) {
        BaseButton button = new BaseButton();
        button.setMold("bs");
        if (!TextUtils.isEmpty(compId))
            button.setId(compId);
        button.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        button.setProperty(comp.getKjAttribute());
        button.setLabel(I18nUtil.getLabelContent(comp.getKjLabelid()));
        button.init(comp, window);
        button.setPgvo(comp);
        button.addEventListiner(comp, window);
        if (window.getWindowType() == 2) {
            button.setDisabled(true);
            button.setAttribute("windowType", 2);
        }
        return button;
    }


    public static BaseBandbox doCreateBandbox(PageControlVO comp, String compId, BaseWindow window) {
        try {
            String className = "";
            String filePath = "";
            if (comp.getKjClass().indexOf("#") > 0) {
                //表示动态的bandbox
                className = comp.getKjClass().split("\\#")[0];
                filePath = comp.getKjClass().split("\\#")[1];
            } else {
                className = comp.getKjClass();
                filePath = "";
            }
            BaseBandbox obj = (BaseBandbox) Class.forName(className).newInstance();
            if (TextUtils.isEmpty(filePath))
                obj.onCreate();
            else
                obj.onCreate(filePath);
            if (!TextUtils.isEmpty(compId))
                obj.setId(compId);
            obj.setProperty(comp.getKjAttribute());
            if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
                obj.setClass(comp.getLayoutComponent().getCompCss());
            else {
//                obj.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
            }
            //控件的扩展属性
            setComponentExpandPropeties(comp, obj);
            obj.init(comp, window);
            obj.setPgvo(comp);
            obj.addEventListiner(comp, window);
            if (window.getWindowType() == 2) {
                obj.setDisabled(true);
                obj.setAttribute("windowType", 2);
            }
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BaseBandbox doCreateInlineBandbox(PageControlVO comp, String compId, BaseWindow window) {
        try {
            String className = "";
            String filePath = "";
            if (comp.getKjClass().indexOf("#") > 0) {
                //表示动态的bandbox
                className = comp.getKjClass().split("\\#")[0];
                filePath = comp.getKjClass().split("\\#")[1];
            } else {
                className = comp.getKjClass();
                filePath = "";
            }
            BaseBandbox obj = (BaseBandbox) Class.forName(className).newInstance();
            if (TextUtils.isEmpty(filePath))
                obj.onCreate();
            else
                obj.onCreate(filePath);
            if (!TextUtils.isEmpty(compId))
                obj.setId(compId);
            obj.setProperty(comp.getKjAttribute());
//            obj.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
            //控件的扩展属性
//            setComponentExpandPropeties(comp, obj);
            obj.init(comp, window);
            obj.setPgvo(comp);
            obj.addEventListiner(comp, window);
            if (window.getWindowType() == 2) {
                obj.setDisabled(true);
                obj.setAttribute("windowType", 2);
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static BaseDatebox doCreateDatebox(PageControlVO comp, String compId, BaseWindow window) {
        BaseDatebox obj = new BaseDatebox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setReadonly(true);
        if (!TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
//        else
//            obj.setClass(ZKConstants.DIV_COMP_DATEBOX_CSS);
        setComponentExpandPropeties(comp, obj);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        return obj;
    }

    public static BaseDatebox doCreateInlineDatebox(PageControlVO comp, String compId, BaseWindow window) {
        BaseDatebox obj = new BaseDatebox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setReadonly(true);
        if (!TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        obj.setProperty(comp.getKjAttribute());
        obj.setClass(ZKConstants.GRID_INLINE_EDITING_DATEBOX_CSS);
        setComponentExpandPropeties(comp, obj);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        return obj;
    }

    public static BaseIntbox doCreateIntbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseIntbox obj = new BaseIntbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        else
            obj.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static BaseIntbox doCreateInlineIntbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseIntbox obj = new BaseIntbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static BaseDecimalbox doCreateDecimalbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseDecimalbox obj = new BaseDecimalbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        else
            obj.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);

        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        return obj;
    }


    public static Component doCreatePreAfterDecimalbox(PageControlVO comp, String compId, BaseWindow window) {
        if (TextUtils.isEmpty(comp.getKjPre()) && TextUtils.isEmpty(comp.getKjAfter()))
            return doCreateDecimalbox(comp, compId, window);
        else {
            Div div = new Div();
            div.setClass("input-group");
            if (!TextUtils.isEmpty(comp.getKjPre())) {
                Span span = new Span();
                span.setParent(div);
                span.setClass("input-group-addon");
                BaseLabel label = new BaseLabel();
                label.setValue(I18nUtil.getLabelContent(comp.getKjPre()));
                label.setParent(span);
            }
            BaseDecimalbox obj = doCreateDecimalbox(comp, compId, window);
            obj.setParent(div);
            if (!TextUtils.isEmpty(comp.getKjAfter())) {
                Span span = new Span();
                span.setParent(div);
                span.setClass("input-group-addon");
                BaseLabel label = new BaseLabel();
                label.setValue(I18nUtil.getLabelContent(comp.getKjAfter()));
                label.setParent(span);
            }
            if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
                obj.setDisabled(true);
            return div;
        }
    }


    public static Component doCreateInlineDecimalbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseDecimalbox obj = doCreateDecimalbox(comp, compId, window);
        obj.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }


    public static BaseLongbox doCreateLongbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseLongbox obj = new BaseLongbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        else
            obj.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static BaseLongbox doCreateInlineLongbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseLongbox obj = new BaseLongbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static BaseCheckbox doCreateSingleCheckBox(PageControlVO comp, String compId, BaseWindow window) {
        BaseCheckbox obj = new BaseCheckbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss() + " checkbox");
        else
            obj.setClass("checkbox");
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static BaseCheckbox doCreateInlineSingleCheckBox(PageControlVO comp, String compId, BaseWindow window) {
        BaseCheckbox obj = new BaseCheckbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.setClass("checkbox");
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static Base2Intbox doCreate2Intbox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Intbox obj = new Base2Intbox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setControlCss(comp.getLayoutComponent().getCompCss());
        else
            obj.setControlCss(ZKConstants.DIV_COMP_COMPONENT_CSS);
        obj.init(comp, window);
        obj.setPgvo(comp);

        return obj;
    }

    public static Base2Intbox doCreateInline2Intbox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Intbox obj = new Base2Intbox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }

    public static BaseTimebox doCreateTimeBox(PageControlVO comp, String compId, BaseWindow window) {
        BaseTimebox obj = new BaseTimebox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setReadonly(true);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        else
            obj.setClass(ZKConstants.DIV_COMP_DATEBOX_CSS);

        if (TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        setComponentExpandPropeties(comp, obj);

        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        return obj;
    }

    public static BaseTimebox doCreateInlineTimeBox(PageControlVO comp, String compId, BaseWindow window) {
        BaseTimebox obj = new BaseTimebox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setReadonly(true);
        obj.setProperty(comp.getKjAttribute());
        obj.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        if (TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        setComponentExpandPropeties(comp, obj);

        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        return obj;
    }

    public static BaseDoublespinner doCreateDoubleSpinner(PageControlVO comp, String compId, BaseWindow window) {
        BaseDoublespinner obj = new BaseDoublespinner();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        else
            obj.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
        if (TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static BaseDoublespinner doCreateInlineDoubleSpinner(PageControlVO comp, String compId, BaseWindow window) {
        BaseDoublespinner obj = new BaseDoublespinner();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        if (TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static BaseSpinner doCreateSpinner(PageControlVO comp, String compId, BaseWindow window) {
        BaseSpinner obj = new BaseSpinner();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        else
            obj.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
        if (TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        setComponentExpandPropeties(comp, obj);

        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static BaseSpinner doCreateInlineSpinner(PageControlVO comp, String compId, BaseWindow window) {
        BaseSpinner obj = new BaseSpinner();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.setClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        if (TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        setComponentExpandPropeties(comp, obj);

        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
//        if (!TextUtils.isEmpty(comp.getLayoutComponent().getKjConstraint())) {
//            obj.setConstraint(comp.getLayoutComponent().getKjConstraint() + ":" + I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
////            textbox.setErrorMessage(I18nUtil.getLabelContent(comp.getLayoutComponent().getKjConstraintKey()));
//        }
        if (window.getWindowType() == 2) {
            obj.setDisabled(true);
            obj.setAttribute("windowType", 2);
        }
        if (null != comp.getLayoutComponent().getCompEditable() && comp.getLayoutComponent().getCompEditable() == 1)
            obj.setDisabled(true);
        return obj;
    }

    public static Base2Datebox doCreate2Datebox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Datebox obj = new Base2Datebox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setControlCss(comp.getLayoutComponent().getCompCss());
        else
            obj.setControlCss(ZKConstants.DIV_COMP_DATEBOX_CSS);
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }

    public static Base2Datebox doCreateInline2Datebox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Datebox obj = new Base2Datebox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }

    public static Base2Doublebox doCreate2Doublebox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Doublebox obj = new Base2Doublebox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setControlCss(comp.getLayoutComponent().getCompCss());
        else
            obj.setControlCss(ZKConstants.DIV_COMP_COMPONENT_CSS);
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }

    public static Base2Doublebox doCreateInline2Doublebox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Doublebox obj = new Base2Doublebox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }


    public static Base2Timebox doCreate2Timebox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Timebox obj = new Base2Timebox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setControlCss(comp.getLayoutComponent().getCompCss());
        else
            obj.setControlCss(ZKConstants.DIV_COMP_DATEBOX_CSS);
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }

    public static Base2Timebox doCreateInline2Timebox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Timebox obj = new Base2Timebox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }


    public static Div doCreateWells(PageControlVO comp, String compId, BaseWindow window) {
        Div div = new Div();
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            div.setClass("well " + comp.getLayoutComponent().getCompCss());
        else
            div.setClass("well");
        Html html = new Html();
        html.setParent(div);
        String defaultValue = "";
        switch (comp.getKjDatasource()) {
            case 0://用户输入
                break;
            case 1://默认值
                defaultValue = (null == comp.getKjDefaultData() ? "" : I18nUtil.getLabelContent(comp.getKjDefaultData().toString()));
                html.setContent(defaultValue);
                break;
            case 3://调用接口
                try {
                    ComponentInitSer ser = (ComponentInitSer) Class.forName(comp.getKjClassImpl()).newInstance();
                    ser.doAction(window, html, comp);
                } catch (Exception e) {
                    AlterDialog.alert("控件初始化类失败");
                }
                break;
        }

        return div;
    }

    public static Div doCreateCheckBoxDecimal(PageControlVO comp, String compId, BaseWindow window) {
        Base2CheckboxDecimal div = new Base2CheckboxDecimal();
        div.onCreate();
        if (!TextUtils.isEmpty(compId))
            div.setId(compId);
        div.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            div.setControlCss(comp.getLayoutComponent().getCompCss());
        else
            div.setControlCss(ZKConstants.DIV_COMP_COMPONENT_CSS);
        div.init(comp, window);
        div.setPgvo(comp);
        if (window.getWindowType() == 2) {
            div.setDisable(true);
            div.setAttribute("windowType", 2);
        }
        return div;
    }

    public static BaseFileUpload doCreateFileUpload(PageControlVO comp, String compId, BaseWindow window) {
        BaseFileUpload obj = new BaseFileUpload();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.onCreate();
        return obj;
    }

    public static BaseFileUpload doCreateInlineFileUpload(PageControlVO comp, String compId, BaseWindow window) {
        BaseFileUpload obj = new BaseFileUpload();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.onCreate();
        obj.setCustClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        return obj;
    }

    public static BaseMultipFileUpload doCreateMultipFileUpload(PageControlVO comp, String compId, BaseWindow window) {
        BaseMultipFileUpload obj = new BaseMultipFileUpload();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.onCreate();
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static BaseMultipFileUpload doCreateMultipInlineFileUpload(PageControlVO comp, String compId, BaseWindow window) {
        BaseMultipFileUpload obj = new BaseMultipFileUpload();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.onCreate();
        obj.setCustClass(ZKConstants.GRID_INLINE_EDITING_COMPONENT_CSS);
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static Image doCreateImage(PageControlVO comp, String compId, BaseWindow window) {
        Image obj = new Image();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());

        return obj;
    }

    public static Div doCreateRowDiv(String css) {
        Div div = new Div();
        div.setClass(css);
        return div;
    }

    /*创建button的分组*/
    public static Div doCreateGroupDiv() {
        Div buttonGroup = new Div();
        buttonGroup.setClass(ZKConstants.DIV_BUTTON_GROUP_DEFAULT_CSS);
        return buttonGroup;
    }


    //添加控件扩展属性
    private static void setComponentExpandPropeties(PageControlVO compSetting, Component component) {
        if (!TextUtils.isEmpty(compSetting.getKjKzsx())) {
            Map<String, Object> sxMap = JsonUtil.jsonToMap(compSetting.getKjKzsx().toString());
            Set<String> keys = sxMap.keySet();
            for (String s : keys) {
                try {
                    BeanUtil.setProperty(component, s, sxMap.get(s));
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    /*获取控件的window*/
    public static BaseWindow getWindow(Component component) {
        if (component instanceof BaseWindow)
            return (BaseWindow) component;
        else
            return getWindow(component.getParent());
    }

    public static void setEditable(BaseWindow window) {
        Collection<Component> comps = window.getFellows();
        for (Component comp : comps) {
            if (null != comp.getAttribute("windowType") && comp.getAttribute("windowType").toString().equals("2")) {
                publicSetEditState(comp, false);
            }
        }
    }

    public static void setDisableEdit(BaseWindow window) {
        Collection<Component> comps = window.getFellows();
        for (Component comp : comps) {
//            if (null != comp.getAttribute("windowType") && comp.getAttribute("windowType").toString().equals("2")) {
            publicSetEditState(comp, true);
//            }
        }
    }

    /*<p>方法: 根据传参（页面控件id）设置是否可编辑 </p>
    *<ul>
     *<li> @param window window</li>
     *<li> @param List 参数map</li>
     *<li> @param state 要变更的状态（true:不可编辑  false:可编辑）</li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-15 17:10  </li>
    *</ul>
    */
    public static void setEditState(BaseWindow window, List<String> paramsList, Boolean state) {
        for (String param : paramsList) {
            Component comp = window.getFellowIfAny(param);
            publicSetEditState(comp, state);
        }
    }

    private static void publicSetEditState(Component comp, Boolean state) {
        if(comp instanceof ComponentSer){
            ComponentSer componentSer = (ComponentSer)comp;
            componentSer.setComponentDisable(state);
        }
    }

    public static void valid(BaseWindow window) {
        Collection<Component> comps = window.getFellows();
        for (Component comp : comps) {
            if (comp instanceof BaseTextbox) {
                if (!TextUtils.isEmpty(((BaseTextbox) comp).getConstraint())) {
                    ((BaseTextbox) comp).invalidate();
                }
            }
//
//            if (comp instanceof BaseListbox)
//                ((BaseListbox) comp).setDisabled(false);
//            if (comp instanceof BaseButton)
//                ((BaseButton) comp).setDisabled(false);
//            if (comp instanceof BaseBandbox)
//                ((BaseBandbox) comp).setDisabled(false);
//            if (comp instanceof BaseDatebox)
//                ((BaseDatebox) comp).setDisabled(false);
//            if (comp instanceof BaseIntbox)
//                ((BaseIntbox) comp).setDisabled(false);
            if (comp instanceof BaseDecimalbox) {
                if (!TextUtils.isEmpty(((BaseDecimalbox) comp).getConstraint())) {
                    ((BaseDecimalbox) comp).invalidate();
                }
            }
//            if (comp instanceof BaseLongbox)
//                ((BaseLongbox) comp).setDisabled(false);
//            if (comp instanceof BaseCheckbox)
//                ((BaseCheckbox) comp).setDisabled(false);
//            if (comp instanceof BaseTimebox)
//                ((BaseTimebox) comp).setDisabled(false);
//            if (comp instanceof BaseDoublespinner)
//                ((BaseDoublespinner) comp).setDisabled(false);
//            if (comp instanceof BaseSpinner)
//                ((BaseSpinner) comp).setDisabled(false);
//            if (comp instanceof Base2CheckboxDecimal)
//                ((Base2CheckboxDecimal) comp).setDisable(false);
        }
    }

    /**
     * <p>方法:doGetObjByLabel 获取多级的值 </p>
     * <ul>
     * <li> @param labelKey TODO</li>
     * <li> @param data TODO</li>
     * <li>@return java.lang.Object  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/8/7 17:18  </li>
     * </ul>
     */
    public static Object doGetObjByLabel(String labelKey, Map<String, Object> data) {
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


    public static BaseMultipleTree doCreateMultipleTree(PageControlVO comp, String compId, BaseWindow window) {
        BaseMultipleTree tree = new BaseMultipleTree();
        if (!TextUtils.isEmpty(compId))
            tree.setId(compId);
        tree.setSizedByContent(true);

        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            tree.setClass(comp.getLayoutComponent().getCompCss());
        else
            tree.setClass(ZKConstants.DIV_COMP_COMPONENT_CSS);
        tree.setProperty(comp.getKjAttribute());
        //控件的扩展属性
        setComponentExpandPropeties(comp, tree);
        tree.init(comp, window);
        tree.setPgvo(comp);
        tree.addEventListiner(comp, window);
        if (window.getWindowType() == 2) {
            tree.setComponentDisable(true);
            tree.setAttribute("windowType", 2);
        }

        //是否只读
        return tree;
    }


    //统一处理和刷新界面中的button状态，根据按钮是否设置不显示条件进行控制
    public static void doCheckButtonStatus(BaseWindow window) {
        String pageCa = window.getPageCA();
        Collection<Component> componentList = window.getFellows();
        List<Map<String, Object>> _datas = listMenusByCA(pageCa);
        List<String> privsBtn = ZKSessionUtils.getPrivsSession(pageCa + "_btns");
        List<String> privsComp = ZKSessionUtils.getPrivsSession(pageCa + "_comps");
        privsBtn = doGetNotInBtn(privsBtn, _datas);
        privsComp = doGetNotInControl(privsComp, _datas);
        for (Component component : componentList) {
            if (component instanceof ComponentSer) {
                ComponentSer btn = (ComponentSer) component;
                //1.先判断定义的内容
                doCheckFormula(window, btn);
                //2.判断权限中定义的不可操作的按钮
                doCheckPrivsBtns(privsBtn, btn, component);
                //3.判断权限中定义的不可操作的控件
                doCheckPrivsComps(privsComp, btn, component);
            }
        }
    }

    private static List<String> doGetNotInBtn(List<String> inAttrs, List<Map<String, Object>> allAttris) {
        List<String> _allBtns = new ArrayList<String>();
        if (null == inAttrs)
            inAttrs = new ArrayList<String>();
        if (null != allAttris && allAttris.size() > 0)
            for (Map<String, Object> map : allAttris) {
                if (!TextUtils.isEmpty(map.get("type")) && map.get("type").toString().equalsIgnoreCase("3")) {
                    if (!inAttrs.contains(map.get("attribute").toString().trim()))
                        _allBtns.add(map.get("attribute").toString());
                }
            }
        return _allBtns;

    }

    private static List<String> doGetNotInControl(List<String> inAttrs, List<Map<String, Object>> allAttris) {
        List<String> _allBtns = new ArrayList<String>();
        if (null == inAttrs)
            inAttrs = new ArrayList<String>();
        if (null != allAttris && allAttris.size() > 0)
            for (Map<String, Object> map : allAttris) {
                if (!TextUtils.isEmpty(map.get("type")) && map.get("type").toString().equalsIgnoreCase("1")) {
                    if (!inAttrs.contains(map.get("attribute").toString().trim()))
                        _allBtns.add(map.get("attribute").toString());
                }
            }
        return _allBtns;

    }

    //获取指定pageca的空间和按钮项目
    private static List<Map<String, Object>> listMenusByCA(String pageCa) {
        PublicData data = PublicData.instantce();
        data.setMethod("sysMenusApi/listMenusByPageCA");
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("pageCa", pageCa);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && !TextUtils.isEmpty(mess.getBean())) {
                return (List<Map<String, Object>>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void doCheckPrivsBtns(List<String> privsBtn, ComponentSer btn, Component component) {
        if (null == privsBtn || privsBtn.size() <= 0)
            return;
        PageControlVO pgvo = btn.getPgvo();
        if (component instanceof BaseButton) {
            String attribute = pgvo.getKjAttribute();
            if (privsBtn.contains(attribute)) {
                btn.setComponentDisable(true);
            }
        }
    }

    private static void doCheckPrivsComps(List<String> privsComp, ComponentSer btn, Component component) {
        if (null == privsComp || privsComp.size() <= 0)
            return;
        PageControlVO pgvo = btn.getPgvo();

        if (component instanceof BaseButton) {
        } else {
            String attribute = pgvo.getKjAttribute();
            if (privsComp.contains(attribute)) {
                btn.setComponentDisable(true);
            }
        }
    }


    private static void doCheckFormula(BaseWindow window, ComponentSer btn) {
        String noActiveStr;
        PageControlVO pgvo = btn.getPgvo();
        if (null == pgvo)
            return;
        if (null == pgvo.getLayoutComponent())
            return;
        if (TextUtils.isEmpty(pgvo.getLayoutComponent().getColumnDisable()))
            return;
        noActiveStr = pgvo.getLayoutComponent().getColumnDisable();
        if (doCheckJsFormula(window, noActiveStr))
            btn.setComponentDisable(false);
        else
            btn.setComponentDisable(true);
    }

    //panduan
    private static boolean doCheckJsFormula(BaseWindow window, String formula) {
        int pageType = window.getWindowType();
        Map<String, Object> ppt = window.getPpt();
        if (null == ppt)
            ppt = new HashMap<String, Object>(1);
        ppt.put("pageType", pageType);
        Set<String> set = ppt.keySet();
        for (String s : set) {
            if (formula.contains(s)) {
                if (!TextUtils.isEmpty(ppt.get(s)))
                    formula = formula.replaceAll(s, ppt.get(s).toString());
//                else
//                    formula = formula.replaceAll(s, "");
            }
        }
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            return jse.eval(formula).toString().equals("true");
        } catch (Exception t) {
            return false;
        }

    }

}
