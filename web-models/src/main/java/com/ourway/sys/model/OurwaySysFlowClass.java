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
@Table(name = "ourway_sys_flow_class")
public class OurwaySysFlowClass extends UUidEntity {

    @Column(name = "CLASS_REF_OWID", nullable = true, length = 64)
    private String classRefOwid;
    @Column(name = "FLOW_REF_OWID", nullable = true, length = 64)
    private String flowRefOwid;
    @Column(name = "VARS", nullable = true, length = 240)
    private String vars;
    @Column(name = "CONDITIONS", nullable = true, length = 640)
    private String conditions;
    @Transient
    private Integer updateFlag;   //1:修改/新增  2：删除
    @Transient
    private OurwaySysObject sysObject;

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public OurwaySysObject getSysObject() {
        return sysObject;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public void setSysObject(OurwaySysObject sysObject) {
        this.sysObject = sysObject;
    }

    public String getClassRefOwid() {
        return classRefOwid;
    }

    public void setClassRefOwid(String classRefOwid) {
        this.classRefOwid = classRefOwid;
    }

    public String getVars() {
        return vars;
    }

    public void setVars(String vars) {
        this.vars = vars;
    }

    public String getFlowRefOwid() {
        return flowRefOwid;
    }

    public void setFlowRefOwid(String flowRefOwid) {
        this.flowRefOwid = flowRefOwid;
    }


}
