package com.ourway.base.zk.models;

import com.ourway.base.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 PageControl : <p>
 * <p>说明:页面相应控件</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/25 23:54
 * </pre>
 */
public class ControlEventVO {
    private String eventRefOwid;
    private String compId;
    private Integer indexNo;
    private Integer status;
    private Integer eventType;
    private String eventClass;
    private Object eventParam;

    public String getEventRefOwid() {
        return eventRefOwid;
    }

    public void setEventRefOwid(String eventRefOwid) {
        this.eventRefOwid = eventRefOwid;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getEventClass() {
        return eventClass;
    }

    public void setEventClass(String eventClass) {
        this.eventClass = eventClass;
    }

    public Object getEventParam() {
        if (eventParam instanceof ArrayList) {
            return JsonUtil.toJson(((List) eventParam).toArray());
        }
        if (eventParam instanceof Map) {
            return JsonUtil.toJson(eventParam);
        }
        return eventParam;
    }

    public void setEventParam(Object eventParam) {
        this.eventParam = eventParam;
    }
}
