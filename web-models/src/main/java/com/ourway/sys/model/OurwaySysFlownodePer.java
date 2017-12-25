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
@Table(name = "OURWAY_SYS_FLOWNODE_PER")
public class OurwaySysFlownodePer extends UUidEntity {

    @Column(name = "FLOWPER_REF_OWID", nullable = true, length = 64)
    private String flowperRefOwid;
    @Column(name = "PER_OWID", nullable = true, length = 64)
    private String perOwid;
    @Column(name = "PER_EMPID", nullable = true, length = 64)
    private String perEmpid;
    @Column(name = "PER_NAME", nullable = true, length = 160)
    private String perName;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;
    @Column(name = "PER_FILTER", nullable = true, length = 240)
    private String perFilter;
    @Column(name = "PER_FILTER_TYPE")
    private Short perFilterType;
    @Transient
    private Integer updateFlag;

    public Short getPerFilterType() {
        return perFilterType;
    }

    public void setPerFilterType(Short perFilterType) {
        this.perFilterType = perFilterType;
    }

    public String getPerFilter() {
        return perFilter;
    }

    public void setPerFilter(String perFilter) {
        this.perFilter = perFilter;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getFlowperRefOwid() {
        return flowperRefOwid;
    }

    public void setFlowperRefOwid(String flowperRefOwid) {
        this.flowperRefOwid = flowperRefOwid;
    }

    public String getPerOwid() {
        return perOwid;
    }

    public void setPerOwid(String perOwid) {
        this.perOwid = perOwid;
    }

    public String getPerEmpid() {
        return perEmpid;
    }

    public void setPerEmpid(String perEmpid) {
        this.perEmpid = perEmpid;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
