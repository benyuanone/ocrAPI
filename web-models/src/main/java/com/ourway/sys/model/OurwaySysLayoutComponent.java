package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_layout_component")
public class OurwaySysLayoutComponent extends UUidEntity {
    @Column(name = "COMP_ID", nullable = true, length = 160)
    private String compId;
    @Column(name = "LAYOUT_REF_OWID", nullable = true, length = 64)
    private String layoutRefOwid;
    @Column(name = "PAGE_REF_OWID", nullable = true, length = 64)
    private String pageRefOwid;
    @Column(name = "COMP_ORDER", nullable = true)
    private Byte compOrder;
    @Column(name = "COMP_START_ROW", nullable = true)
    private Byte compStartRow;
    @Column(name = "COMP_START_COL", nullable = true)
    private Byte compStartCol;
    @Column(name = "COMP_COLS", nullable = true)
    private Byte compCols;
    @Column(name = "COMP_ROWS", nullable = true)
    private Byte compRows;
    @Column(name = "COMP_DATA_ORDER", nullable = true)
    private Byte compDataOrder;
    @Column(name = "COMP_ISORDER", nullable = true)
    private Byte compIsorder;
    @Column(name = "COMP_EDITABLE", nullable = true)
    private Byte compEditable;
    @Column(name = "COMP_COLOR", nullable = true)
    private String compColor;
    @Column(name = "COMP_POSITION", nullable = true)
    private Byte compPosition;
    @Column(name = "COMP_HBOX", nullable = true)
    private Byte compHbox;
    @Column(name = "COMP_VBOX", nullable = true)
    private Byte compVbox;
    @Column(name = "COMP_LABEL", nullable = true)
    private Byte compLable;
    @Column(name = "COMP_CSS", nullable = true)
    private String compCss;
    @Column(name = "COMP_LENGTH", nullable = true)
    private Integer compLength;
    @Column(name = "COMP_WIDTH", nullable = true)
    private Integer compWidth;
    @Column(name = "COMP_INIT", nullable = true)
    private Byte compInit;
    @Column(name = "EVENT_TYPE", nullable = true)
    private Byte eventType;
    @Column(name = "EVENT_DATA", nullable = true)
    private Byte eventData;
    @Column(name = "EVENT_FORWARD", nullable = true)
    private Byte eventForward;
    @Column(name = "EVENT_FORMULA", nullable = true, length = 240)
    private String evnetFormula;
    @Column(name = "EVENT_COLOR", nullable = true, length = 240)
    private String eventColor;
    @Column(name = "EVENT_INT", nullable = true, length = 240)
    private String eventInt;
    @Column(name = "EVENT_BACK_CONTENT", nullable = true, length = 240)
    private String eventBackContent;
    @Column(name = "EVENT_BACK_TYPE", nullable = true)
    private Byte eventBackType;
    @Column(name = "COMP_ROWSNUM", nullable = true)
    private Byte compRowsnum;
    @Column(name = "COMP_GROUP", nullable = true)
    private Byte compGroup;
    @Column(name = "KJ_CONSTRAINT", nullable = true, length = 240)
    private String kjConstraint;
    @Column(name = "KJ_CONSTRAINT_KEY", nullable = true, length = 64)
    private String kjConstraintKey;
    @Column(name = "COMP_FONT_CSS", length = 1200)
    private String compFontCss;
    @Column(name = "EVENT_DATA_CONTENT", length = 240)
    private String eventDataContent;
    @Column(name = "COMP_TJLX", nullable = true)
    private Byte compTjlx;
    @Column(name = "COMP_TJWB", length = 240)
    private String compTjwb;
    @Column(name = "COLUM_CSS", length = 240)
    private String columCss;
    @Column(name = "COLUM_TYPE", nullable = true)
    private Byte columType;
    @Column(name = "COLUMN_DISABLE", nullable = true)
    private String columnDisable;
//    @Column(name = "COMP_FORMULA", nullable = true)
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

    public Byte getColumType() {
        return columType;
    }

    public void setColumType(Byte columType) {
        this.columType = columType;
    }

    public Byte getCompTjlx() {
        return compTjlx;
    }

    public void setCompTjlx(Byte compTjlx) {
        this.compTjlx = compTjlx;
    }

    public String getCompTjwb() {
        return compTjwb;
    }

    public void setCompTjwb(String compTjwb) {
        this.compTjwb = compTjwb;
    }

    public Byte getCompRowsnum() {
        return compRowsnum;
    }

    public void setCompRowsnum(Byte compRowsnum) {
        this.compRowsnum = compRowsnum;
    }

    public String getEventDataContent() {
        return eventDataContent;
    }

    public void setEventDataContent(String eventDataContent) {
        this.eventDataContent = eventDataContent;
    }

    public String getCompFontCss() {
        return compFontCss;
    }

    public void setCompFontCss(String compFontCss) {
        this.compFontCss = compFontCss;
    }

    public Byte getCompGroup() {
        return compGroup;
    }

    public void setCompGroup(Byte compGroup) {
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

    public Byte getCompInit() {
        return compInit;
    }

    public void setCompInit(Byte compInit) {
        this.compInit = compInit;
    }

    public Byte getEventType() {
        return eventType;
    }

    public void setEventType(Byte eventType) {
        this.eventType = eventType;
    }

    public Byte getEventData() {
        return eventData;
    }

    public void setEventData(Byte eventData) {
        this.eventData = eventData;
    }

    public Byte getEventForward() {
        return eventForward;
    }

    public void setEventForward(Byte eventForward) {
        this.eventForward = eventForward;
    }

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

    public String getEventInt() {
        return eventInt;
    }

    public void setEventInt(String eventInt) {
        this.eventInt = eventInt;
    }

    public String getEventBackContent() {
        return eventBackContent;
    }

    public void setEventBackContent(String eventBackContent) {
        this.eventBackContent = eventBackContent;
    }

    public Byte getEventBackType() {
        return eventBackType;
    }

    public void setEventBackType(Byte eventBackType) {
        this.eventBackType = eventBackType;
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

    public Byte getCompLable() {
        return compLable;
    }

    public void setCompLable(Byte compLable) {
        this.compLable = compLable;
    }

    public Byte getCompPosition() {
        return compPosition;
    }

    public void setCompPosition(Byte compPosition) {
        this.compPosition = compPosition;
    }

    public Byte getCompHbox() {
        return compHbox;
    }

    public void setCompHbox(Byte compHbox) {
        this.compHbox = compHbox;
    }

    public Byte getCompVbox() {
        return compVbox;
    }

    public void setCompVbox(Byte compVbox) {
        this.compVbox = compVbox;
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

    public Byte getCompDataOrder() {
        return compDataOrder;
    }

    public void setCompDataOrder(Byte compDataOrder) {
        this.compDataOrder = compDataOrder;
    }

    public Byte getCompIsorder() {
        return compIsorder;
    }

    public void setCompIsorder(Byte compIsorder) {
        this.compIsorder = compIsorder;
    }

    public Byte getCompEditable() {
        return compEditable;
    }

    public void setCompEditable(Byte compEditable) {
        this.compEditable = compEditable;
    }

    public String getCompColor() {
        return compColor;
    }

    public void setCompColor(String compColor) {
        this.compColor = compColor;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public Byte getCompOrder() {
        return compOrder;
    }

    public void setCompOrder(Byte compOrder) {
        this.compOrder = compOrder;
    }


    public Byte getCompStartRow() {
        return compStartRow;
    }

    public void setCompStartRow(Byte compStartRow) {
        this.compStartRow = compStartRow;
    }


    public Byte getCompStartCol() {
        return compStartCol;
    }

    public void setCompStartCol(Byte compStartCol) {
        this.compStartCol = compStartCol;
    }


    public Byte getCompCols() {
        return compCols;
    }

    public void setCompCols(Byte compCols) {
        this.compCols = compCols;
    }


    public Byte getCompRows() {
        return compRows;
    }

    public void setCompRows(Byte compRows) {
        this.compRows = compRows;
    }


}
