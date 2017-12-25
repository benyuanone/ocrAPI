package com.ourway.base.zk.models;

import com.ourway.base.utils.JsonUtil;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2017/4/1.
 */
public class PageLayoutVO implements Serializable {
    private String owid;
    private String pageRefOwid;
    private Integer orderNo;
    private String controlId;
    private String controlParentId;
    private String controlPath;
    private Integer controlWidth;
    private Integer controlHeight;
    private String controlCss;
    private Integer controlType;
    private String controlClass;
    private Integer controlSizebycontent;
    private Integer controlCheck;
    private Integer controlWay;
    private String controlInt;
    private String controlDetailInt;
    private String controlBandboxInt;
    private Map<String, List<PageControlVO>> layOutComponents;
    private Object controlDbInt;
    private Integer controlIsdb;
    private String controlIntGrid;
    private Integer controlLoad;
    private Integer controlIsshow;
    private String controlLabel;
    private String controlSclass;
    private Integer controlPagesize;
    private Integer controlSplitpage;
    private String controlDbclickEvent;
    private Object controlDbclickEventParam;
    private Object controlRowCss;

    public Integer getControlWay() {
        return controlWay;
    }

    public void setControlWay(Integer controlWay) {
        this.controlWay = controlWay;
    }

    public String getControlBandboxInt() {
        return controlBandboxInt;
    }

    public void setControlBandboxInt(String controlBandboxInt) {
        this.controlBandboxInt = controlBandboxInt;
    }

    public String getControlDbclickEvent() {
        return controlDbclickEvent;
    }

    public void setControlDbclickEvent(String controlDbclickEvent) {
        this.controlDbclickEvent = controlDbclickEvent;
    }

    public Object getControlRowCss() {
        if (controlRowCss instanceof ArrayList) {
            return JsonUtil.toJson(((List) controlRowCss).toArray());
        }
        if (controlRowCss instanceof Map) {
            return JsonUtil.toJson(controlRowCss);
        }
        return controlRowCss;
    }

    public void setControlRowCss(Object controlRowCss) {
        this.controlRowCss = controlRowCss;
    }

    public Object getControlDbclickEventParam() {
        if (controlDbclickEventParam instanceof ArrayList) {
            return JsonUtil.toJson(((List) controlDbclickEventParam).toArray());
        }
        if (controlDbclickEventParam instanceof Map) {
            return JsonUtil.toJson(controlDbclickEventParam);
        }
        return controlDbclickEventParam;
    }

    public void setControlDbclickEventParam(Object controlDbclickEventParam) {
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

    public Integer getControlIsshow() {
        return controlIsshow;
    }

    public void setControlIsshow(Integer controlIsshow) {
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

    public String getControlIntGrid() {
        return controlIntGrid;
    }

    public void setControlIntGrid(String controlIntGrid) {
        this.controlIntGrid = controlIntGrid;
    }

    public Integer getControlLoad() {
        return controlLoad;
    }

    public void setControlLoad(Integer controlLoad) {
        this.controlLoad = controlLoad;
    }

    public Integer getControlIsdb() {
        return controlIsdb;
    }

    public void setControlIsdb(Integer controlIsdb) {
        this.controlIsdb = controlIsdb;
    }

    public Object getControlDbInt() {
        if (controlDbInt instanceof ArrayList) {
            return JsonUtil.toJson(((List) controlDbInt).toArray());
        }
        if (controlDbInt instanceof Map) {
            return JsonUtil.toJson(controlDbInt);
        }
        return controlDbInt;
    }

    public void setControlDbInt(Object controlDbInt) {
        this.controlDbInt = controlDbInt;
    }

    public String getControlDetailInt() {
        return controlDetailInt;
    }

    public void setControlDetailInt(String controlDetailInt) {
        this.controlDetailInt = controlDetailInt;
    }

    public Integer getControlCheck() {
        return controlCheck;
    }

    public void setControlCheck(Integer controlCheck) {
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

    public String getOwid() {
        return owid;
    }

    public void setOwid(String owid) {
        this.owid = owid;
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

    public Integer getControlType() {
        return controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public String getControlClass() {
        return controlClass;
    }

    public void setControlClass(String controlClass) {
        this.controlClass = controlClass;
    }

    public Map<String, List<PageControlVO>> getLayOutComponents() {
        return layOutComponents;
    }

    public void setLayOutComponents(Map<String, List<PageControlVO>> layOutComponents) {
        this.layOutComponents = layOutComponents;
    }
}
