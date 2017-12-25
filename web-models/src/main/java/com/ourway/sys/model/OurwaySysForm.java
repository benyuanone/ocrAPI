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
@Table(name = "ourway_sys_form")
public class OurwaySysForm extends UUidEntity {

    @Column(name = "CONTENT", nullable = true, length = 240)
    private String content;
    @Column(name = "STATUS", nullable = true, length = 64)
    private Byte status;
    @Column(name = "DAYS", nullable = true)
    private Integer days;
    @Transient
    private Map<String, Object> nodeMap;

    public Map<String, Object> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<String, Object> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
