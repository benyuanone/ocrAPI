package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_flow")
public class OurwaySysFlow extends UUidEntity {
    @Column(name = "FLOW_CODE", nullable = true, length = 64)
    private String flowCode;
    @Column(name = "FLOW_NAME", nullable = true, length = 160)
    private String flowName;
    @Column(name = "FLOW_LABEL", nullable = true, length = 64)
    private String flowLabel;
    @Column(name = "FLOW_DES", nullable = true, length = 240)
    private String flowDes;
    @Column(name = "FLOW_BPMN", nullable = true, length = 240)
    private String flowBpmn;
    @Column(name = "FLOW_PNG", nullable = true, length = 240)
    private String flowPng;
    @Column(name = "FLOW_ZIP", nullable = true, length = 240)
    private String flowZip;
    @Column(name = "FLOW_STATUS", nullable = true)
    private Byte flowStatus;
    @Column(name = "FLOW_ID", nullable = true, length = 64)
    private String flowId;
    @Column(name = "FLOW_KEY", nullable = true, length = 64)
    private String flowKey;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;
    @Transient
    private OurwaySysFlowClass ourwaySysFlowClass;

    public OurwaySysFlowClass getOurwaySysFlowClass() {
        return ourwaySysFlowClass;
    }

    public void setOurwaySysFlowClass(OurwaySysFlowClass ourwaySysFlowClass) {
        this.ourwaySysFlowClass = ourwaySysFlowClass;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowLabel() {
        return flowLabel;
    }

    public void setFlowLabel(String flowLabel) {
        this.flowLabel = flowLabel;
    }

    public String getFlowDes() {
        return flowDes;
    }

    public void setFlowDes(String flowDes) {
        this.flowDes = flowDes;
    }

    public String getFlowBpmn() {
        return flowBpmn;
    }

    public void setFlowBpmn(String flowBpmn) {
        this.flowBpmn = flowBpmn;
    }

    public String getFlowPng() {
        return flowPng;
    }

    public void setFlowPng(String flowPng) {
        this.flowPng = flowPng;
    }

    public String getFlowZip() {
        return flowZip;
    }

    public void setFlowZip(String flowZip) {
        this.flowZip = flowZip;
    }

    public Byte getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Byte flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
