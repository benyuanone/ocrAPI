package com.ourway.base.zk.utils;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.bandbox.ListBandbox;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>方法 PageUtils : <p>
 * <p>说明:页面处理相关方法</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/3 20:20
 * </pre>
 */
public class PageUtils {


    //计算单行的row如何分割
    public static String getSpans(List<PageControlVO> vos) {
        String rowspans = "";
        for (PageControlVO vo : vos) {
            if (null != vo.getLayoutComponent().getCompLable() && vo.getLayoutComponent().getCompLable() == 1)
                rowspans += "1,";
            if (null != vo.getLayoutComponent().getCompCols() && vo.getLayoutComponent().getCompCols() > 0)
                rowspans += vo.getLayoutComponent().getCompCols() + ",";
        }
        if (!TextUtils.isEmpty(rowspans))
            return rowspans.substring(0, rowspans.length() - 1);
        else
            return "";
    }

    public static Listhead createListHead(BaseListbox listbox) {
        Listhead head = null;
        if (null == listbox.getListhead()) {
            head = new Listhead();
            head.setParent(listbox);
        } else
            head = listbox.getListhead();
        return head;
    }

    public static Listheader createListheader(BaseListbox listbox, String label) {
        Listhead head = createListHead(listbox);
        Listheader listheader = new Listheader();
        listheader.setParent(head);
        listheader.setLabel(I18nUtil.getLabelContent(label));
        return listheader;
    }

    public static Listitem createListItem(BaseListbox listbox) {
        Listitem head = new Listitem();
        head.setParent(listbox);
        return head;
    }

    public static Listcell createListcell(Listitem listitem, Object object) {
        Listcell head = new Listcell();
        if (TextUtils.isEmpty(object))
            head.setLabel(" ");
        else
            head.setLabel(object.toString());
        head.setParent(listitem);
        return head;
    }

    public static BaseGrid createGrid(Component component) {
        BaseGrid baseGrid = new BaseGrid();
        baseGrid.setParent(component);
        return baseGrid;
    }

    public static Rows createRows(BaseGrid grid) {
        if (null != grid.getRows())
            return grid.getRows();
        else {
            Rows rows = new Rows();
            rows.setParent(grid);
            return rows;
        }
    }

    public static Row createRow(BaseGrid grid, String spans) {
        Rows rows = createRows(grid);
        Row row = new Row();
        row.setParent(rows);
        if (!TextUtils.isEmpty(spans))
            row.setSpans(spans);
        return row;
    }

    public static Div createDiv(Component comp, String align, String cssName) {
        Div dv = new Div();
        dv.setParent(comp);
        if (!TextUtils.isEmpty(align))
            dv.setAlign(align);
        if (!TextUtils.isEmpty(cssName))
            dv.setClass(cssName);
        return dv;
    }

    public static Hbox createHbox(Component comp, String cssName) {
        Hbox dv = new Hbox();
        dv.setParent(comp);
        if (!TextUtils.isEmpty(cssName))
            dv.setClass(cssName);
        return dv;
    }

    public static BaseLabel createLabel(Component comp, String value, String cssName) {
        BaseLabel label = new BaseLabel();
        label.setParent(comp);
        if (!TextUtils.isEmpty(value))
            label.setValue(value);
        if (!TextUtils.isEmpty(cssName))
            label.setClass(cssName);
        return label;
    }

    public static BaseLabel createLabelWithDiv(Component comp, String key, String cssName, String divAlign) {
        Div div = new Div();
        div.setParent(comp);
        div.setAlign(divAlign);
        BaseLabel label = new BaseLabel();
        label.setParent(div);
        label.displayWithI18n(key);
        label.setParent(div);
        label.setClass(cssName);
        return label;
    }

    public static BaseLabel createLabel(PageControlVO comp, String compId, BaseWindow window) {
        BaseLabel label = new BaseLabel();
        if (!TextUtils.isEmpty(compId))
            label.setId(compId);
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            label.setClass(comp.getLayoutComponent().getCompCss());
        label.setProperty(comp.getKjAttribute());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            label.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
//        textbox.setTabbable(true);
        //判断是否要初始化
        label.setPgvo(comp);
        label.init(comp, window);
        label.addEventListiner(comp, window);
        return label;
    }


    public static Component createTextbox(PageControlVO comp, String compId, BaseWindow window) {
        Div div = new Div();
        div.setClass("input-group");
        BaseTextbox textbox = new BaseTextbox();
        textbox.setParent(div);
        if (!TextUtils.isEmpty(compId))
            textbox.setId(compId);
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            textbox.setClass("form-control " + comp.getLayoutComponent().getCompCss());
        else
            textbox.setClass("form-control");

        textbox.setProperty(comp.getKjAttribute());
        textbox.setPlaceholder(I18nUtil.getLabelContent(comp.getKjLabelid()));
        if (null != comp.getLayoutComponent().getCompLength() && comp.getLayoutComponent().getCompLength() != 0)
            textbox.setWidth(comp.getLayoutComponent().getCompLength() + "px");
        if (null != comp.getKjIndex())
            textbox.setTabindex(comp.getKjIndex());
        if (null != comp.getLayoutComponent().getCompRowsnum() && comp.getLayoutComponent().getCompRowsnum() > 1)
            textbox.setRows(comp.getLayoutComponent().getCompRowsnum());

        textbox.setMold("rounded");
        //控件的扩展属性
        setComponentExpandPropeties(comp, textbox);

        //判断是否要初始化
        textbox.setPgvo(comp);
        textbox.init(comp, window);
        textbox.addEventListiner(comp, window);
        return div;
    }

    //    创建有前缀或者后缀或者都有的输入框
    public static Component createPreAfterTextbox(PageControlVO comp, String compId, BaseWindow window) {
        if (TextUtils.isEmpty(comp.getKjPre()) && TextUtils.isEmpty(comp.getKjAfter()))
            return createTextbox(comp, compId, window);
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
            BaseTextbox textbox = new BaseTextbox();
            textbox.setParent(div);
            if (!TextUtils.isEmpty(compId))
                textbox.setId(compId);
            if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
                textbox.setClass("form-control " + comp.getLayoutComponent().getCompCss());
            else
                textbox.setClass("form-control");
            textbox.setProperty(comp.getKjAttribute());
            textbox.setPlaceholder(I18nUtil.getLabelContent(comp.getKjLabelid()));
            if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
                textbox.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
            //控件的扩展属性
            setComponentExpandPropeties(comp, textbox);

            //判断是否要初始化
            textbox.setPgvo(comp);
            textbox.init(comp, window);
            textbox.addEventListiner(comp, window);
            if (!TextUtils.isEmpty(comp.getKjAfter())) {
                Span span = new Span();
                span.setParent(div);
                span.setClass("input-group-addon");
                BaseLabel label = new BaseLabel();
                label.setValue(I18nUtil.getLabelContent(comp.getKjAfter()));
                label.setParent(span);
            }
            return div;
        }
    }

    public static BaseTextbox createTextbox(PageControlVO comp, String compId, BaseWindow window, boolean visiable) {
        BaseTextbox textbox = new BaseTextbox();
        if (!TextUtils.isEmpty(compId))
            textbox.setId(compId);
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            textbox.setClass(comp.getLayoutComponent().getCompCss());
        textbox.setProperty(comp.getKjAttribute());
        textbox.setPlaceholder(I18nUtil.getLabelContent(comp.getKjLabelid()));
        textbox.setPgvo(comp);
        textbox.init(comp, window);
        textbox.addEventListiner(comp, window);
        textbox.setVisible(visiable);
        return textbox;
    }

    public static BaseTextbox createTextbox(Component comp, String compId, String placeHolder) {
        BaseTextbox textbox = new BaseTextbox();
        textbox.setId(compId);
        textbox.setPlaceholder(I18nUtil.getLabelContent(placeHolder));
        textbox.setParent(comp);

//        textbox.setTabbable(true);
        return textbox;
    }

    public static BaseTextbox createTextbox(Component comp, String compId, String placeHolder, String key, Map<String, Object> data, String css) {
        BaseTextbox textbox = new BaseTextbox();
        textbox.setId(compId);
        textbox.setPlaceholder(I18nUtil.getLabelContent(placeHolder));
        textbox.setParent(comp);
//        textbox.setTabbable(true);
        if (!TextUtils.isEmpty(css))
            textbox.setStyle(css);
        if (!TextUtils.isEmpty(data.get(key)))
            textbox.setValue(data.get(key).toString());
        return textbox;
    }


    public static BaseListbox createListbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseListbox listbox = new BaseListbox();
        if (!TextUtils.isEmpty(compId))
            listbox.setId(compId);
        listbox.setMold("select");
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            listbox.setClass(comp.getLayoutComponent().getCompCss());

        listbox.setProperty(comp.getKjAttribute());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            listbox.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
        if (null != comp.getKjIndex())
            listbox.setTabindex(comp.getKjIndex());
        //控件的扩展属性
        setComponentExpandPropeties(comp, listbox);
        listbox.init(comp, window);
        listbox.setPgvo(comp);
        listbox.addEventListiner(comp, window);
        return listbox;
    }

    public static BaseBandbox createBandbox(PageControlVO comp, String compId, BaseWindow window) {
        try {
            BaseBandbox obj = (BaseBandbox) Class.forName(comp.getKjClass()).newInstance();
            obj.onCreate();
            if (!TextUtils.isEmpty(compId))
                obj.setId(compId);
            obj.setProperty(comp.getKjAttribute());
            if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
                obj.setClass(comp.getLayoutComponent().getCompCss());
            if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
                obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
//            if (null != comp.getKjIndex())
//                obj.setTabindex(comp.getKjIndex());
            //控件的扩展属性
            setComponentExpandPropeties(comp, obj);
            obj.init(comp, window);
            obj.setPgvo(comp);
            obj.addEventListiner(comp, window);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BaseIntbox createIntbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseIntbox obj = new BaseIntbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
        if (null != comp.getKjIndex())
            obj.setTabindex(comp.getKjIndex());
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static BaseIntbox createIntbox(Component comp, String compId, String css) {
        BaseIntbox obj = new BaseIntbox();
        obj.setId(compId);
        obj.setParent(comp);
        if (!TextUtils.isEmpty(css))
            obj.setStyle(css);
        return obj;
    }

    public static BaseDecimalbox createDecimalbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseDecimalbox obj = new BaseDecimalbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
        if (null != comp.getKjIndex())
            obj.setTabindex(comp.getKjIndex());
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static Component createPreAfterDecimalbox(PageControlVO comp, String compId, BaseWindow window) {

        if (TextUtils.isEmpty(comp.getKjPre()) && TextUtils.isEmpty(comp.getKjAfter()))
            return createDecimalbox(comp, compId, window);
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
            BaseDecimalbox obj = new BaseDecimalbox();
            obj.setParent(div);
            if (!TextUtils.isEmpty(compId))
                obj.setId(compId);
            if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
                obj.setClass("form-control " + comp.getLayoutComponent().getCompCss());
            else
                obj.setClass("form-control");
            obj.setProperty(comp.getKjAttribute());
            obj.setPlaceholder(I18nUtil.getLabelContent(comp.getKjLabelid()));
            if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
                obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
            //控件的扩展属性
            setComponentExpandPropeties(comp, obj);

            //判断是否要初始化
            obj.setPgvo(comp);
            obj.init(comp, window);
            obj.addEventListiner(comp, window);
            if (!TextUtils.isEmpty(comp.getKjAfter())) {
                Span span = new Span();
                span.setParent(div);
                span.setClass("input-group-addon");
                BaseLabel label = new BaseLabel();
                label.setValue(I18nUtil.getLabelContent(comp.getKjAfter()));
                label.setParent(span);
            }
            return div;
        }


    }

    public static BaseLongbox createLongbox(PageControlVO comp, String compId, BaseWindow window) {
        BaseLongbox obj = new BaseLongbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
//        if (null != comp.getKjIndex())
//            obj.setTabindex(comp.getKjIndex());
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static BaseTimebox createTimeBox(PageControlVO comp, String compId, BaseWindow window) {
        BaseTimebox obj = new BaseTimebox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setReadonly(true);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
        if (null != comp.getKjIndex())
            obj.setTabindex(comp.getKjIndex());
        if (TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        setComponentExpandPropeties(comp, obj);

        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static BaseDoublespinner createDoubleSpinner(PageControlVO comp, String compId, BaseWindow window) {
        BaseDoublespinner obj = new BaseDoublespinner();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());

        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");

        if (TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        setComponentExpandPropeties(comp, obj);

        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static BaseSpinner createSpinner(PageControlVO comp, String compId, BaseWindow window) {
        BaseSpinner obj = new BaseSpinner();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");

        if (TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        setComponentExpandPropeties(comp, obj);

        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static Base2Datebox create2Datebox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Datebox obj = new Base2Datebox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }

    public static Base2Intbox create2Intbox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Intbox obj = new Base2Intbox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
//        setComponentExpandPropeties(comp,obj);

        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }

    public static Base2Doublebox create2Doublebox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Doublebox obj = new Base2Doublebox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }

    public static Base2Timebox create2Timebox(PageControlVO comp, String compId, BaseWindow window) {
        Base2Timebox obj = new Base2Timebox();
        obj.onCreate();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
        obj.init(comp, window);
        obj.setPgvo(comp);
        return obj;
    }

    public static Div createWells(PageControlVO comp, String compId, BaseWindow window) {
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

    public static BaseCheckbox createSingleCheckBox(PageControlVO comp, String compId, BaseWindow window) {
        BaseCheckbox obj = new BaseCheckbox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
        if (null != comp.getKjIndex())
            obj.setTabindex(comp.getKjIndex());
        setComponentExpandPropeties(comp, obj);
//        obj.setValue(1);
//        obj.setLabel(I18nUtil.getLabelContent(comp.getKjLabelid()));
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static BaseCheckbox createSingleCheckBox(Component comp, String compId, Object value, String label, boolean checked) {
        BaseCheckbox obj = new BaseCheckbox();
        obj.setId(compId);
        obj.setParent(comp);
        obj.setValue(value);
        obj.setLabel(I18nUtil.getLabelContent(label));
        obj.setChecked(checked);
        return obj;
    }

    public static BaseCheckbox createCheckBox(Component comp, String css) {
        BaseCheckbox obj = new BaseCheckbox();
        obj.setParent(comp);
        if (!TextUtils.isEmpty(css)) {
            obj.setStyle(css);
        }
        return obj;
    }


    public static BaseRadio createRadio(Component comp, String css) {
        BaseRadio obj = new BaseRadio();
        obj.setParent(comp);
        if (!TextUtils.isEmpty(css))
            obj.setStyle(css);
        return obj;
    }


    public static BaseDatebox createDatebox(PageControlVO comp, String compId, BaseWindow window) {
        BaseDatebox obj = new BaseDatebox();
        if (!TextUtils.isEmpty(compId))
            obj.setId(compId);
        obj.setReadonly(true);
        if (!TextUtils.isEmpty(comp.getKjTranslate()))
            obj.setFormat(comp.getKjTranslate());
        obj.setProperty(comp.getKjAttribute());
        if (!TextUtils.isEmpty(comp.getLayoutComponent().getCompCss()))
            obj.setClass(comp.getLayoutComponent().getCompCss());
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            obj.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
        if (null != comp.getKjIndex())
            obj.setTabindex(comp.getKjIndex());
        setComponentExpandPropeties(comp, obj);
        obj.init(comp, window);
        obj.setPgvo(comp);
        obj.addEventListiner(comp, window);
        return obj;
    }

    public static BaseButton createButton(PageControlVO comp, String compId, BaseWindow window) {
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
            button.setSclass("btn-primary");
        button.setProperty(comp.getKjAttribute());
        button.setLabel(I18nUtil.getLabelContent(comp.getKjLabelid()));
        if (null != comp.getLayoutComponent().getCompWidth() && comp.getLayoutComponent().getCompWidth() != 0)
            button.setWidth(comp.getLayoutComponent().getCompWidth() + "px");
//        if (null != comp.getKjIndex())
//            button.setTabindex(comp.getKjIndex());
        button.init(comp, window);
        button.setPgvo(comp);
        button.addEventListiner(comp, window);

        return button;
    }

    public static BaseButton createButton(Component comp, String compId, String cssName, String label, EventListener clickListener) {
        BaseButton button = new BaseButton();
        button.setId(compId);
        button.setParent(comp);
        if (!TextUtils.isEmpty(cssName))
            button.setClass(cssName);
        button.setLabel(I18nUtil.getLabelContent(label));
        button.addEventListener(Events.ON_CLICK, clickListener);
        return button;
    }

    public static BaseButton createButton(Component comp, String compId, String cssName, String label) {
        BaseButton button = new BaseButton();
        button.setId(compId);
        button.setParent(comp);
        if (!TextUtils.isEmpty(cssName))
            button.setClass(cssName);
        button.setLabel(I18nUtil.getLabelContent(label));
        return button;
    }

    /**
     * <p>方法:doAddComponent 根据类型创建控件 </p>
     * <ul>
     * <li> @param subComp 控件类型</li>
     * <li> @param compId 控件id</li>
     * <li>@return org.zkoss.zk.ui.Component  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/16 20:40  </li>
     * </ul>
     */
//    public static Component doAddComponent(PageControlVO subComp, String compId, BaseWindow window) {
//        Component comp = null;
//        switch (subComp.getKjType()) {
//            case 0:
//                if (TextUtils.isEmpty(subComp.getKjPre()) && TextUtils.isEmpty(subComp.getKjAfter()))
//                    comp = createTextbox(subComp, compId, window);
//                else
//                    comp = createPreAfterTextbox(subComp, compId, window);
//                break;
//            case 1:
//                comp = createListbox(subComp, compId, window);
//                break;
//            case 2:
//                comp = createButton(subComp, compId, window);
//                break;
//            case 3:
//                comp = createBandbox(subComp, compId, window);
//                break;
//            case 4:
//                comp = createDatebox(subComp, compId, window);
//                break;
//            case 5:
//                comp = createIntbox(subComp, compId, window);
//                break;
//            case 6:
//                comp = createPreAfterDecimalbox(subComp, compId, window);
//                break;
//            case 7:
//                comp = createLongbox(subComp, compId, window);
//                break;
//            case 8:
//                comp = createSingleCheckBox(subComp, compId, window);
//                break;
//            case 9:
//                comp = create2Intbox(subComp, compId, window);
//                break;
//            case 10:
//                comp = createTimeBox(subComp, compId, window);
//                break;
//            case 11:
//                comp = createDoubleSpinner(subComp, compId, window);
//                break;
//            case 12:
//                comp = createSpinner(subComp, compId, window);
//                break;
//            case 13://隐藏域
//                comp = createTextbox(subComp, compId, window, false);
//                break;
//            case 14://时间段
//                comp = create2Datebox(subComp, compId, window);
//                break;
//            case 15:
//                comp = create2Doublebox(subComp, compId, window);
//                break;
//            case 16:
//                comp = create2Timebox(subComp, compId, window);
//                break;
//            case 17:
//                comp = createWells(subComp, compId, window);
//                break;
//            case 18:
//                break;
//            case 19:
//                break;
//            case 20:
//                break;
//        }
//        return comp;
//
//    }

    /*设定控件为内部编辑*/
    public static void setInplace(Component comp, boolean inPlaceFlag, boolean readonly) {

        if (comp instanceof BaseTextbox) {
            BaseTextbox bt = (BaseTextbox) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setReadonly(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseListbox) {
            BaseListbox bt = (BaseListbox) comp;
            bt.setWidth("98%");
            bt.setDisabled(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseIntbox) {
            BaseIntbox bt = (BaseIntbox) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setReadonly(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseDecimalbox) {
            BaseDecimalbox bt = (BaseDecimalbox) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setReadonly(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseDoublebox) {
            BaseDoublebox bt = (BaseDoublebox) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setReadonly(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseDatebox) {
            BaseDatebox bt = (BaseDatebox) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setDisabled(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseTimebox) {
            BaseTimebox bt = (BaseTimebox) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setDisabled(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseDoublespinner) {
            BaseDoublespinner bt = (BaseDoublespinner) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setReadonly(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseSpinner) {
            BaseSpinner bt = (BaseSpinner) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setReadonly(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseBandbox) {
            BaseBandbox bt = (BaseBandbox) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setDisabled(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
        if (comp instanceof BaseLongbox) {
            BaseLongbox bt = (BaseLongbox) comp;
//            bt.setInplace(inPlaceFlag);
            bt.setWidth("98%");
            bt.setReadonly(readonly);
            if (!readonly)
                bt.setStyle(ZKConstants.GRID_INLINE_EDIT_CSS);
        }
    }

    /**
     * <p>方法:setDefaultValue 給控件賦值 </p>
     * <ul>
     * <li> @param comp TODO</li>
     * <li> @param object TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/30 13:26  </li>
     * </ul>
     */
    public static void setDefaultValue(Component comp, Object object) {
        if (comp instanceof BaseTextbox) {
            BaseTextbox bt = (BaseTextbox) comp;
            if (!TextUtils.isEmpty(object))
                bt.setText(object.toString());
        }
        if (comp instanceof BaseListbox) {
            BaseListbox bt = (BaseListbox) comp;
            if (!TextUtils.isEmpty(object)) {
                List<Listitem> items = bt.getItems();
                for (Listitem item : items) {
                    if (!TextUtils.isEmpty(item.getValue()) && item.getValue().toString().equals(object.toString())) {
                        item.setSelected(true);
                        break;
                    }
                }
            }
        }
        if (comp instanceof BaseIntbox) {
            BaseIntbox bt = (BaseIntbox) comp;
            if (!TextUtils.isEmpty(object))
                bt.setValue(Integer.parseInt(object.toString()));
        }
        if (comp instanceof BaseDecimalbox) {
            BaseDecimalbox bt = (BaseDecimalbox) comp;
            if (!TextUtils.isEmpty(object))
                bt.setValue(new BigDecimal(object.toString()));
        }
        if (comp instanceof BaseDoublebox) {
            BaseDoublebox bt = (BaseDoublebox) comp;
            if (!TextUtils.isEmpty(object))
                bt.setValue(new Double(object.toString()));
        }
        if (comp instanceof BaseDatebox) {
            BaseDatebox bt = (BaseDatebox) comp;
            if (!TextUtils.isEmpty(object))
                bt.setText(object.toString());
        }
        if (comp instanceof BaseTimebox) {
            BaseTimebox bt = (BaseTimebox) comp;
            if (!TextUtils.isEmpty(object))
                bt.setText(object.toString());
        }
        if (comp instanceof BaseDoublespinner) {
            BaseDoublespinner bt = (BaseDoublespinner) comp;
            if (!TextUtils.isEmpty(object))
                bt.setText(object.toString());
        }
        if (comp instanceof BaseSpinner) {
            BaseSpinner bt = (BaseSpinner) comp;
            if (!TextUtils.isEmpty(object))
                bt.setText(object.toString());
        }
        if (comp instanceof BaseBandbox) {
            BaseBandbox _db = (BaseBandbox) comp;
            _db.clearErrorMessage();
            if (TextUtils.isEmpty(object))
                object = null;
            if (comp instanceof ListBandbox) {
                ListBandbox lbd = (ListBandbox) comp;
                lbd.showDefault(object);
            } else
                _db.showDefault(object);
        }
        if (comp instanceof BaseLongbox) {
            BaseLongbox bt = (BaseLongbox) comp;
            if (!TextUtils.isEmpty(object))
                bt.setText(object.toString());
        }
        if (comp instanceof BaseCheckbox) {
            BaseCheckbox bt = (BaseCheckbox) comp;
            if (!TextUtils.isEmpty(object) && object.toString().equalsIgnoreCase("1"))
                bt.setChecked(true);
        }


    }


    //给控件添加事件
    public static void doAddEvent(Component comp, BaseWindow win, PageControlVO pageControlVO) {
        if (null == pageControlVO.getLayoutComponent().getEventType() || 0 == pageControlVO.getLayoutComponent().getEventType())
            return;
        switch (pageControlVO.getLayoutComponent().getEventType()) {
            case 1:
                comp.addEventListener(Events.ON_CLICK, win);
                break;
            case 2:
                comp.addEventListener(Events.ON_DOUBLE_CLICK, win);
                break;
            case 3:
                comp.addEventListener(Events.ON_OK, win);
                break;
            case 4:
                comp.addEventListener(Events.ON_CHANGE, win);
                break;
            case 5:
                comp.addEventListener(Events.ON_BLUR, win);
                break;
            case 6:
                comp.addEventListener(Events.ON_SELECT, win);
                break;
        }
    }


    /**
     * <p>方法:doRowValue 获取指定控件的row的对象 </p>
     * <ul>
     * <li> @param component TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/16 22:55  </li>
     * </ul>
     */
    public static Map<String, Object> doRowValue(Component component) {
        if (component.getParent() instanceof Row)
            return (Map<String, Object>) (Row) component.getParent();
        else
            return doRowValue(component.getParent());
    }

    /**
     * <p>方法:doComponentGrid 获取离本控件最近的grid </p>
     * <ul>
     * <li> @param component TODO</li>
     * <li>@return com.ourway.base.zk.component.BaseGrid  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/17 23:05  </li>
     * </ul>
     */
    public static BaseGrid doComponentGrid(Component component) {
        if (component.getParent() instanceof BaseGrid)
            return (BaseGrid) component.getParent();
        else
            return doComponentGrid(component.getParent());
    }

    /**
     * <p>方法:doComponentGrid 初始化所有控件 </p>
     * <ul>
     * <li> @param component TODO</li>
     * <li>@return com.ourway.base.zk.component.BaseGrid  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/17 23:05  </li>
     * </ul>
     */
    public static void resetGridComponent(Component grid) {
        Collection<Component> comps = grid.getFellows();
        for (Component comp : comps) {
            //如果是显示的控件，则进行初始化
            if (comp.isVisible())
                if (comp instanceof ComponentSer) {
                    ((ComponentSer) comp).reset();
                }
        }
    }

    public static Row getSelRow(Component component, int times) {
        if (times >= 20)
            return null;
        if (component instanceof Row)
            return (Row) component;
        else
            return getSelRow(component.getParent(), times + 1);
    }


    /**
     * <p>方法:doRowComponent grid中单行控件和label的自动添加 </p>
     * <ul>
     * <li> @param subComps TODO</li>
     * <li> @param row TODO</li>
     * <li> @param data TODO</li>
     * <li> @param divAlign TODO</li>
     * <li> @param window TODO</li>
     * <li> @param hboxMap TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/25 16:54  </li>
     * </ul>
     */
//    public static void doRowComponent(List<PageControlVO> subComps, Row row, PageLayoutVO data, String divAlign, BaseWindow window, Map<String, Component> hboxMap, boolean buttonFlag) {
//        for (PageControlVO subComp : subComps) {
//            if (null != subComp.getLayoutComponent().getCompLable() && subComp.getLayoutComponent().getCompLable() == 1) {
//                BaseLabel label = PageUtils.createLabelWithDiv(row, subComp.getKjLabelid(), subComp.getLayoutComponent().getCompColor(), "right");
//            }
//            //创建控件
//            createComponent(subComp, row, data, divAlign, window, hboxMap, buttonFlag);
//        }
//    }

    /**
     * <p>方法:createComponent 创建指定的控件 </p>
     * <ul>
     * <li> @param subComp TODO</li>
     * <li> @param row TODO</li>
     * <li> @param data TODO</li>
     * <li> @param divAlign TODO</li>
     * <li> @param window TODO</li>
     * <li> @param hboxMap TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/25 16:54  </li>
     * </ul>
     */
//    public static void createComponent(PageControlVO subComp, Row row, PageLayoutVO data, String divAlign, BaseWindow window, Map<String, Component> hboxMap, boolean buttonFlag) {
//        //根据类型创建控件
//        //把控件放到全局的map中
//        String compId = data.getControlId() + BaseWindow.COMP_CONTACT + subComp.getKjAttribute();
//        Component kjComp = PageUtils.doAddComponent(subComp, compId, window);
//        if (null == kjComp) {
//            AlterDialog.alert("无法创建控件" + subComp.getKjType());
//            return;
//        }
//        //处理控件放的位置
//        if (null != subComp.getLayoutComponent().getCompHbox() && subComp.getLayoutComponent().getCompHbox() != 0) {
//            Component parentComponent = null;
//            if (buttonFlag) {
//                parentComponent = getParentDivComponent(subComp, row, data, divAlign, hboxMap);
//            } else {
//               parentComponent = getParentHboxComponent(subComp, row, data, divAlign, hboxMap);
//            }
//
//            kjComp.setParent(parentComponent);
//        } else {
//            kjComp.setParent(row);
//        }
//    }

//    //根据不同的类型来获取不同的父级Component
//    private static Component getParentHboxComponent(PageControlVO subComp, Row row, PageLayoutVO data, String divAlign, Map<String, Component> hboxMap) {
//        String _hboxKey = data.getControlId() + "_hbox_" + subComp.getLayoutComponent().getCompHbox();
//        Hbox hbox = null;
//        if (null == hboxMap.get(_hboxKey)) {
//            Div div = new Div();
//            div.setAlign(divAlign);
//            div.setParent(row);
//            hbox = new Hbox();
//            hbox.setParent(div);
//            hboxMap.put(_hboxKey, hbox);
//        } else
//            hbox = (Hbox) hboxMap.get(_hboxKey);
//        return hbox;
//    }

//    private static Component getParentDivComponent(PageControlVO subComp, Row row, PageLayoutVO data, String divAlign, Map<String, Component> hboxMap) {
//        String _hboxKey = data.getControlId() + "_hbox_" + subComp.getLayoutComponent().getCompHbox();
//        Div hbox = null;
//        if (null == hboxMap.get(_hboxKey)) {
//            Hbox _hbox;
//            if (null != row.getLastChild() && row.getLastChild() instanceof Div && row.getLastChild().getFirstChild() instanceof Hbox) {
//                _hbox = (Hbox) row.getLastChild().getFirstChild();
//            } else {
//                Div div = new Div();
//                div.setAlign(divAlign);
//                div.setParent(row);
//                _hbox = new Hbox();
//                _hbox.setParent(div);
//            }
//
//            hbox = new Div();
//            hbox.setClass("btn-group");
//            hbox.setParent(_hbox);
//            hboxMap.put(_hboxKey, hbox);
//        } else
//            hbox = (Div) hboxMap.get(_hboxKey);
//        return hbox;
//    }
    public static void main(String[] args) {

    }


//    public static BaseIntbox createIntbox(Component parent, String id, String key, Map<String, Object> data, boolean readOnly) {
//        BaseIntbox ib = new BaseIntbox();
//        ib.setParent(parent);
//        ib.setId(id);
//        if (!com.ourway.base.zk.utils.TextUtils.isEmpty(data.get(key)))
//            ib.setValue(Integer.parseInt(data.get(key).toString()));
//        if (readOnly)
//            ib.setReadonly(readOnly);
//        return ib;
//    }

//    public static BaseTextbox createTextbox(Component parent, String id, String key, Map<String, Object> data, boolean readOnly, int size) {
//        BaseTextbox ib = new BaseTextbox();
//        ib.setParent(parent);
//        ib.setId(id);
//        ib.setWidth(size + "px");
//        if (null != data.get(key))
//            ib.setValue(data.get(key).toString());
//        if (readOnly)
//            ib.setReadonly(readOnly);
//        return ib;
//    }


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

    //属性中最后的属性，如a.b.c 获取c
    public static String getLastProperty(String property) {
        if (property.indexOf(".") >= 0) {
            String[] pro = property.split("\\.");
            return pro[pro.length - 1];
        } else
            return property;
    }
    /**
    *<p>方法:changeWindowTitle 更换当前窗体的标题，根据当前窗体中的ppt来进行更换 </p>
    *<ul>
     *<li> @param window TODO</li>
     *<li> @param args TODO</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/12/23 0023 10:15  </li>
    *</ul>
    */
    public static void changeWindowTitle(BaseWindow window, Map args) {
        if (!TextUtils.isEmpty(args.get("title"))) {
            if (!TextUtils.isEmpty(window.getPpt().get(args.get("title").toString()))) {
                Component comp = Path.getComponent("/mainWin");
                MainAction root = null;
                if (comp instanceof MainAction) {
                    root = (MainAction) comp;
                }
                String tabId = window.getTabId().replaceAll("_window", "");
                root.changeTabLable(tabId, window.getPpt().get(args.get("title").toString()).toString());
            }
        }
        changeWindowWidth(window);
    }
    //更改窗体的宽度。
    public static void changeWindowWidth(BaseWindow window){
        int width = ZKSessionUtils.getScreenWidth()-ZKConstants.SYSTEM_WINDOW_WIDTH;
        window.setWidth(width+"px");
    }
    public static void doMinWidth(Tabbox tabbox){

    }
}
