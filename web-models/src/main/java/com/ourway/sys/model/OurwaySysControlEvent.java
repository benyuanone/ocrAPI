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
@Table(name = "OURWAY_SYS_CONTROL_EVENT")
public class OurwaySysControlEvent extends UUidEntity {
    @Column(name = "EVENT_REF_OWID", nullable = true, length = 64)
    private String eventRefOwid;
    @Column(name = "COMP_ID", nullable = true)
    private String compId;
    @Column(name = "INDEXNO", nullable = true, length = 64)
    private Integer indexno;
    @Column(name = "EVENT_NO", nullable = true, length = 64)
    private String eventNo;
    @Column(name = "CHN_NAME", nullable = true, length = 160)
    private String chnName;
    @Column(name = "LAN_LABELID", nullable = true, length = 160)
    private String lanLabelid;
    @Column(name = "EVENT_TYPE")
    private Byte eventType;
    @Column(name = "EVENT_CLASS", nullable = true)
    private String eventClass;
    @Column(name = "EVENT_PARAM", nullable = true)
    private String eventParam;

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

    public Integer getIndexno() {
        return indexno;
    }

    public void setIndexno(Integer indexno) {
        this.indexno = indexno;
    }

    public String getEventNo() {
        return eventNo;
    }

    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

    public String getLanLabelid() {
        return lanLabelid;
    }

    public void setLanLabelid(String lanLabelid) {
        this.lanLabelid = lanLabelid;
    }

    public Byte getEventType() {
        return eventType;
    }

    public void setEventType(Byte eventType) {
        this.eventType = eventType;
    }

    public String getEventClass() {
        return eventClass;
    }

    public void setEventClass(String eventClass) {
        this.eventClass = eventClass;
    }

    public String getEventParam() {
        return eventParam;
    }

    public void setEventParam(String eventParam) {
        this.eventParam = eventParam;
    }
}
