package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Treeitem;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 根据公式来计算表格中的值，包括+-*,sum </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class FormulaCalAction implements ComponentListinerSer {
    //
//[{"gridId":"需要计算的表格","formula":"多个公式，中间用逗号分隔,行.列.小数位数，如 1.1.0=sum(*.2.0)"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        try {
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert("请定义gridId");
                return;
            }
            if (TextUtils.isEmpty(_params.get("formula"))) {
                AlterDialog.alert("请定义公式");
                return;
            }
            BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            String[] _formula = _params.get("formula").toString().split("\\,");
            String equalLeft = "";
            String equalRight = "";
            String _result = "";
            double _o = 0.0;
            for (String s : _formula) {
                if (!TextUtils.isEmpty(s)) {
                    //根据公式计算
                    equalLeft = s.split("\\=")[0];
                    equalRight = s.split("\\=")[1];
                    if (equalRight.contains("sum")) {
                        //用来做列求和和行求和
                        equalRight = equalRight.replaceAll("sum", "").replaceAll("\\(", "").replaceAll("\\)", "");
                        _o = calSum(baseGrid, equalRight);
                        setVal(_o, baseGrid, equalLeft);
                    } else {
                        //没有sum的
                        String _equalRight = equalRight.replaceAll("\\+", ",").replaceAll("\\-", ",").replaceAll("\\*", ",").replaceAll("\\/", ",");
                        String[] _equalsRight = _equalRight.split("\\,");
                        for (String s1 : _equalsRight) {
                            if (!TextUtils.isEmpty(s1)) {
                                _o = getValue(baseGrid, s1);
                                equalRight = equalRight.replace(s1, _o + "");
                            }
                        }
                        _o = jsCal(equalRight);
                        setVal(_o, baseGrid, equalLeft);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double jsCal(String formula) {
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            return new Double(jse.eval(formula).toString());
        } catch (Exception t) {
            return 0.0;
        }
    }

    //给等式左边的控件赋值
    private void setVal(double _d, BaseGrid grid, String equalLeft) {
        Object o = null;
        String[] gs = equalLeft.split("\\.");
        if (gs[2].equalsIgnoreCase("0")) {
            o = new Double(_d).intValue();
        } else {
            String _format = "#.";
            for (int i = 0; i < Integer.parseInt(gs[2]); i++) {
                _format += "0";
            }
            DecimalFormat format = new DecimalFormat(_format);
            o = format.format(_d);
        }
        Rows rows = grid.getRows();
        if (null == rows)
            return;
        List<Row> rowList = rows.getChildren();
        if (null == rowList.get(Integer.parseInt(gs[0])))
            return;
        Row row = rowList.get(Integer.parseInt(gs[0]));
        if (Integer.parseInt(gs[1]) >= row.getChildren().size())
            return;
        Component component = row.getChildren().get(Integer.parseInt(gs[1]));
        Map<String, Object> mapObj = row.getValue();
        if (component instanceof ComponentSer) {
            ComponentSer componentSer = (ComponentSer) component;
            if (!TextUtils.isEmpty(componentSer.getPgvo()) && !TextUtils.isEmpty(componentSer.getPgvo().getKjBindKey()))
                mapObj.put(componentSer.getPgvo().getKjBindKey(), o);
            componentSer.setPageValue(o);
        }

    }


    private Double calSum(BaseGrid grid, String formula) {
        String[] gs = formula.split("\\.");
        double _d = getValue(grid, gs[0], gs[1], gs[2]);
        return _d;
    }

    private Double getValue(BaseGrid grid, String ss) {
        String[] _ss = ss.split("\\.");
        return getValue(grid, _ss[0], _ss[1], _ss[2]);
    }

    //获取指定行列的值
    private Double getValue(BaseGrid grid, String row, String col, String xsw) {
        Rows rows = grid.getRows();
        List<Row> allRow = rows.getChildren();
        Double _dl = 0.0;
        if ("*".equalsIgnoreCase(row)) {
            //表示整列相加
            for (int i = 0; i < allRow.size(); i++) {
                _dl += getValue(grid, i + "", col, xsw);
            }
        }
        if ("*".equalsIgnoreCase(col)) {
            //表示整行的数据相加
            for (int i = 0; i < allRow.get(0).getChildren().size(); i++) {
                _dl += getValue(grid, row, i + "", xsw);
            }
        }
        if (!"*".equalsIgnoreCase(row) && !"*".equalsIgnoreCase(col)) {
            if (null != allRow.get(Integer.parseInt(row))) {
                Row _curRow = allRow.get(Integer.parseInt(row));
                if ((_curRow.getChildren().size() > Integer.parseInt(col))) {
                    Component component = _curRow.getChildren().get(Integer.parseInt(col));
                    if (component instanceof ComponentSer) {
                        ComponentSer componentSer = (ComponentSer) component;
                        if (null == componentSer.getPageValue())
                            return 0.0;
                        else {
                            try {
                                return (Double) componentSer.getPageValue();
                            } catch (Exception e) {
                                return 0.0;
                            }
                        }
                    }
                }
            }

        }
        return 0.0;
    }
}

