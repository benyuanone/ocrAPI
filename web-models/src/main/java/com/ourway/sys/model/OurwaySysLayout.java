package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_layout")
public class OurwaySysLayout extends UUidEntity {
    @Column(name = "PAGE_REF_OWID", nullable = true, length = 64)
    private String pageRefOwid;
    @Column(name = "ORDER_NO", nullable = true)
    private Integer orderNo;
    @Column(name = "CONTROL_ID", nullable = true, length = 64)
    private String controlId;
    @Column(name = "CONTROL_PARENT_ID", nullable = true, length = 64)
    private String controlParentId;
    @Column(name = "CONTROL_PATH", nullable = true, length = 160)
    private String controlPath;
    @Column(name = "CONTROL_WIDTH", nullable = true)
    private Integer controlWidth;
    @Column(name = "CONTROL_HEIGHT", nullable = true)
    private Integer controlHeight;
    @Column(name = "CONTROL_SIZEBYCONTENT", nullable = true)
    private Integer controlSizebycontent;
    @Column(name = "CONTROL_CSS", nullable = true, length = 64)
    private String controlCss;
    @Column(name = "CONTROL_TYPE", nullable = true)
    private Byte controlType;
    @Column(name = "CONTROL_CHECK", nullable = true)
    private Byte controlCheck;
    @Column(name = "CONTROL_ISDB", nullable = true)
    private Byte controlIsdb;
    @Column(name = "CONTROL_CLASS", nullable = true, length = 64)
    private String controlClass;
    @Column(name = "CONTROL_INT", nullable = true, length = 240)
    private String controlInt;
    @Column(name = "CONTROL_DETAIL_INT", nullable = true, length = 240)
    private String controlDetailInt;
    @Column(name = "CONTROL_DB_INT", nullable = true, length = 240)
    private String controlDbInt;
    @Column(name = "CONTROL_INT_GRID", nullable = true, length = 240)
    private String controlIntGrid;
    @Column(name = "CONTROL_LOAD", nullable = true)
    private Byte controlLoad;
    @Column(name = "CONTROL_WAY", nullable = true)
    private Byte controlWay;
    @Column(name = "CONTROL_ISSHOW", nullable = true)
    private Byte controlIsshow;
    @Column(name = "CONTROL_LABEL", nullable = true, length = 240)
    private String controlLabel;
    @Column(name = "CONTROL_SCLASS", nullable = true, length = 240)
    private String controlSclass;
    @Column(name = "CONTROL_PAGESIZE", nullable = true)
    private Integer controlPagesize;
    @Column(name = "CONTROL_SPLITPAGE", nullable = true)
    private Integer controlSplitpage;
    @Column(name = "CONTROL_DBCLICK_EVENT", nullable = true, length = 240)
    private String controlDbclickEvent;
    @Column(name = "CONTROL_DBCLICK_EVENT_PARAM", nullable = true, length = 240)
    private String controlDbclickEventParam;
    @Column(name = "CONTROL_ROW_CSS", nullable = true, length = 240)
    private String controlRowCss;
    @Column(name = "CONTROL_BANDBOX_INT", nullable = true, length = 480)
    private String controlBandboxInt;

    /*获取某个布局控件下的空间列表*/
    @Transient
    private Map<Integer, List<OurwaySysPageComponent>> layOutComponents;
    @Transient
    private List<OurwaySysLayoutComponent> components;

    public Byte getControlWay() {
        return controlWay;
    }

    public void setControlWay(Byte controlWay) {
        this.controlWay = controlWay;
    }

    public String getControlBandboxInt() {
        return controlBandboxInt;
    }

    public void setControlBandboxInt(String controlBandboxInt) {
        this.controlBandboxInt = controlBandboxInt;
    }

    public String getControlRowCss() {
        return controlRowCss;
    }

    public void setControlRowCss(String controlRowCss) {
        this.controlRowCss = controlRowCss;
    }

    public String getControlDbclickEvent() {
        return controlDbclickEvent;
    }

    public void setControlDbclickEvent(String controlDbclickEvent) {
        this.controlDbclickEvent = controlDbclickEvent;
    }

    public String getControlDbclickEventParam() {
        return controlDbclickEventParam;
    }

    public void setControlDbclickEventParam(String controlDbclickEventParam) {
        this.controlDbclickEventParam = controlDbclickEventParam;
    }

    public Integer getControlSplitpage() {
        return controlSplitpage;
    }

    public void setControlSplitpage(Integer controlSplitpage) {
        this.controlSplitpage = controlSplitpage;
    }

    public Integer getControlPagesize() {
        return controlPagesize;
    }

    public void setControlPagesize(Integer controlPagesize) {
        this.controlPagesize = controlPagesize;
    }

    public Byte getControlIsshow() {
        return controlIsshow;
    }

    public void setControlIsshow(Byte controlIsshow) {
        this.controlIsshow = controlIsshow;
    }

    public String getControlLabel() {
        return controlLabel;
    }

    public void setControlLabel(String controlLabel) {
        this.controlLabel = controlLabel;
    }

    public String getControlSclass() {
        return controlSclass;
    }

    public void setControlSclass(String controlSclass) {
        this.controlSclass = controlSclass;
    }

    public List<OurwaySysLayoutComponent> getComponents() {
        return components;
    }

    public void setComponents(List<OurwaySysLayoutComponent> components) {
        this.components = components;
    }

    public String getControlIntGrid() {
        return controlIntGrid;
    }

    public void setControlIntGrid(String controlIntGrid) {
        this.controlIntGrid = controlIntGrid;
    }

    public Byte getControlLoad() {
        return controlLoad;
    }

    public void setControlLoad(Byte controlLoad) {
        this.controlLoad = controlLoad;
    }

    public Byte getControlIsdb() {
        return controlIsdb;
    }

    public void setControlIsdb(Byte controlIsdb) {
        this.controlIsdb = controlIsdb;
    }

    public String getControlDbInt() {
        return controlDbInt;
    }

    public void setControlDbInt(String controlDbInt) {
        this.controlDbInt = controlDbInt;
    }

    public String getControlDetailInt() {
        return controlDetailInt;
    }

    public void setControlDetailInt(String controlDetailInt) {
        this.controlDetailInt = controlDetailInt;
    }

    public Byte getControlCheck() {
        return controlCheck;
    }

    public void setControlCheck(Byte controlCheck) {
        this.controlCheck = controlCheck;
    }

    public String getControlInt() {
        return controlInt;
    }

    public void setControlInt(String controlInt) {
        this.controlInt = controlInt;
    }

    public Integer getControlSizebycontent() {
        return controlSizebycontent;
    }

    public void setControlSizebycontent(Integer controlSizebycontent) {
        this.controlSizebycontent = controlSizebycontent;
    }

    public Map<Integer, List<OurwaySysPageComponent>> getLayOutComponents() {
        return layOutComponents;
    }

    public void setLayOutComponents(Map<Integer, List<OurwaySysPageComponent>> layOutComponents) {
        this.layOutComponents = layOutComponents;
    }

    public String getPageRefOwid() {
        return pageRefOwid;
    }

    public void setPageRefOwid(String pageRefOwid) {
        this.pageRefOwid = pageRefOwid;
    }


    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }


    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }


    public String getControlParentId() {
        return controlParentId;
    }

    public void setControlParentId(String controlParentId) {
        this.controlParentId = controlParentId;
    }


    public String getControlPath() {
        return controlPath;
    }

    public void setControlPath(String controlPath) {
        this.controlPath = controlPath;
    }


    public Integer getControlWidth() {
        return controlWidth;
    }

    public void setControlWidth(Integer controlWidth) {
        this.controlWidth = controlWidth;
    }


    public Integer getControlHeight() {
        return controlHeight;
    }

    public void setControlHeight(Integer controlHeight) {
        this.controlHeight = controlHeight;
    }


    public String getControlCss() {
        return controlCss;
    }

    public void setControlCss(String controlCss) {
        this.controlCss = controlCss;
    }


    public Byte getControlType() {
        return controlType;
    }

    public void setControlType(Byte controlType) {
        this.controlType = controlType;
    }


    public String getControlClass() {
        return controlClass;
    }

    public void setControlClass(String controlClass) {
        this.controlClass = controlClass;
    }


}
