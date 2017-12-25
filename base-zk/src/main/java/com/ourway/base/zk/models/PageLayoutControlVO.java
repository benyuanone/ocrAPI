package com.ourway.base.zk.models;

import com.ourway.base.utils.JsonUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PageLayoutControlVO implements Serializable {

    private String owid;
    private String compId;
    private String layoutRefOwid;
    private String pageRefOwid;
    private Integer compOrder;
    private Integer compStartRow;
    private Integer compStartCol;
    private Integer compCols;
    private Integer compRows;
    private Integer compDataOrder;
    private Integer compIsorder;
    private Integer compEditable;
    private String compColor;
    private Integer compPosition;
    private Integer compHbox;
    private Integer compVbox;
    private Integer compLable;
    private String compCss;
    private Integer compLength;
    private Integer compWidth;
    private Integer compInit;
    private Integer eventType;
    private Integer eventData;
    //    private Integer eventForward;
    private String evnetFormula;
    private String eventColor;
    //    private String eventInt;
    private String eventBackContent;
    private Integer eventBackType;
    private String kjConstraint;
    private String kjConstraintKey;
    private Integer compGroup;
    private Object compFontCss;//字体样式
    private Object eventDataContent;
    private Integer compRowsnum;
    private Integer compTjlx;
    private String compTjwb;
    private String columCss;
    private Integer columType;
    private String columnDisable;
//    private String compFormula;
//
//    public String getCompFormula() {
//        return compFormula;
//    }
//
//    public void setCompFormula(String compFormula) {
//        this.compFormula = compFormula;
//    }

    public String getColumnDisable() {
        return columnDisable;
    }

    public void setColumnDisable(String columnDisable) {
        this.columnDisable = columnDisable;
    }

    public String getColumCss() {
        return columCss;
    }

    public void setColumCss(String columCss) {
        this.columCss = columCss;
    }

    public Integer getColumType() {
        return columType;
    }

    public void setColumType(Integer columType) {
        this.columType = columType;
    }

    public Integer getCompTjlx() {
        return compTjlx;
    }

    public void setCompTjlx(Integer compTjlx) {
        this.compTjlx = compTjlx;
    }

    public String getCompTjwb() {
        return compTjwb;
    }

    public void setCompTjwb(String compTjwb) {
        this.compTjwb = compTjwb;
    }

    public Object getEventDataContent() {
        if (eventDataContent instanceof ArrayList) {
            return JsonUtil.toJson(((List) eventDataContent).toArray());
        }
        if (eventDataContent instanceof Map) {
            return JsonUtil.toJson(eventDataContent);
        }
        return eventDataContent;
    }

    public void setEventDataContent(Object eventDataContent) {
        if (eventDataContent instanceof ArrayList) {
            this.eventDataContent = JsonUtil.toJson(((List) eventDataContent).toArray());
        } else
            this.eventDataContent = eventDataContent;
    }

    public Integer getCompRowsnum() {
        return compRowsnum;
    }

    public void setCompRowsnum(Integer compRowsnum) {
        this.compRowsnum = compRowsnum;
    }

    public Integer getCompGroup() {
        return compGroup;
    }

    public void setCompGroup(Integer compGroup) {
        this.compGroup = compGroup;
    }

    public String getKjConstraint() {
        return kjConstraint;
    }

    public void setKjConstraint(String kjConstraint) {
        this.kjConstraint = kjConstraint;
    }

    public String getKjConstraintKey() {
        return kjConstraintKey;
    }

    public void setKjConstraintKey(String kjConstraintKey) {
        this.kjConstraintKey = kjConstraintKey;
    }

    public Integer getCompInit() {
        return compInit;
    }

    public void setCompInit(Integer compInit) {
        this.compInit = compInit;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getEventData() {
        return eventData;
    }

    public void setEventData(Integer eventData) {
        this.eventData = eventData;
    }

//    public Integer getEventForward() {
//        return eventForward;
//    }
//
//    public void setEventForward(Integer eventForward) {
//        this.eventForward = eventForward;
//    }

    public String getEvnetFormula() {
        return evnetFormula;
    }

    public void setEvnetFormula(String evnetFormula) {
        this.evnetFormula = evnetFormula;
    }

    public String getEventColor() {
        return eventColor;
    }

    public void setEventColor(String eventColor) {
        this.eventColor = eventColor;
    }

//    public String getEventInt() {
//        return eventInt;
//    }
//
//    public void setEventInt(String eventInt) {
//        this.eventInt = eventInt;
//    }

    public String getEventBackContent() {
        return eventBackContent;
    }

    public void setEventBackContent(String eventBackContent) {
        this.eventBackContent = eventBackContent;
    }

    public String getOwid() {
        return owid;
    }

    public void setOwid(String owid) {
        this.owid = owid;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getLayoutRefOwid() {
        return layoutRefOwid;
    }

    public void setLayoutRefOwid(String layoutRefOwid) {
        this.layoutRefOwid = layoutRefOwid;
    }

    public String getPageRefOwid() {
        return pageRefOwid;
    }

    public void setPageRefOwid(String pageRefOwid) {
        this.pageRefOwid = pageRefOwid;
    }

    public Integer getCompOrder() {
        return compOrder;
    }

    public void setCompOrder(Integer compOrder) {
        this.compOrder = compOrder;
    }

    public Integer getCompStartRow() {
        return compStartRow;
    }

    public void setCompStartRow(Integer compStartRow) {
        this.compStartRow = compStartRow;
    }

    public Integer getCompStartCol() {
        return compStartCol;
    }

    public void setCompStartCol(Integer compStartCol) {
        this.compStartCol = compStartCol;
    }

    public Integer getCompCols() {
        return compCols;
    }

    public void setCompCols(Integer compCols) {
        this.compCols = compCols;
    }

    public Integer getCompRows() {
        return compRows;
    }

    public void setCompRows(Integer compRows) {
        this.compRows = compRows;
    }

    public Integer getCompDataOrder() {
        return compDataOrder;
    }

    public void setCompDataOrder(Integer compDataOrder) {
        this.compDataOrder = compDataOrder;
    }

    public Integer getCompIsorder() {
        return compIsorder;
    }

    public void setCompIsorder(Integer compIsorder) {
        this.compIsorder = compIsorder;
    }

    public Integer getCompEditable() {
        return compEditable;
    }

    public void setCompEditable(Integer compEditable) {
        this.compEditable = compEditable;
    }

    public String getCompColor() {
        return compColor;
    }

    public void setCompColor(String compColor) {
        this.compColor = compColor;
    }

    public Integer getCompPosition() {
        return compPosition;
    }

    public void setCompPosition(Integer compPosition) {
        this.compPosition = compPosition;
    }

    public Integer getCompHbox() {
        return compHbox;
    }

    public void setCompHbox(Integer compHbox) {
        this.compHbox = compHbox;
    }

    public Integer getCompVbox() {
        return compVbox;
    }

    public void setCompVbox(Integer compVbox) {
        this.compVbox = compVbox;
    }

    public Integer getCompLable() {
        return compLable;
    }

    public void setCompLable(Integer compLable) {
        this.compLable = compLable;
    }

    public String getCompCss() {
        return compCss;
    }

    public void setCompCss(String compCss) {
        this.compCss = compCss;
    }

    public Integer getCompLength() {
        return compLength;
    }

    public void setCompLength(Integer compLength) {
        this.compLength = compLength;
    }

    public Integer getCompWidth() {
        return compWidth;
    }

    public void setCompWidth(Integer compWidth) {
        this.compWidth = compWidth;
    }

    public Integer getEventBackType() {
        return eventBackType;
    }

    public void setEventBackType(Integer eventBackType) {
        this.eventBackType = eventBackType;
    }

    public Object getCompFontCss() {
        if (compFontCss instanceof ArrayList) {
            return JsonUtil.toJson(((List) compFontCss).toArray());
        }
        if (compFontCss instanceof Map) {
            return JsonUtil.toJson(compFontCss);
        }
        return compFontCss;
    }

    public void setCompFontCss(Object compFontCss) {
        this.compFontCss = compFontCss;
    }
}
