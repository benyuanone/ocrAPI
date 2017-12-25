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
@Table(name = "ourway_sys_depatemp")
public class OurwaySysDepatemp extends UUidEntity {

    @Column(name = "DEPT_REF_OWID", nullable = true)
    private Integer deptRefOwid;
    @Column(name = "USER_REF_OW_ID", nullable = true, length = 64)
    private String userRefOwId;
    @Column(name = "EMP_POSITION_ID", nullable = true, length = 64)
    private String empPositionId;
    @Column(name = "EMP_POSITION_NAME", nullable = true, length = 240)
    private String empPositionName;
    @Column(name = "EMP_DEFAULT")
    private Short empDefault;
    @Transient
    private Integer updateFlag;   //1:修改/新增  2：删除
    @Transient
    private OurwaySysDepat ourwaySysDepat;

    public Short getEmpDefault() {
        return empDefault;
    }

    public void setEmpDefault(Short empDefault) {
        this.empDefault = empDefault;
    }

    public OurwaySysDepat getOurwaySysDepat() {
        return ourwaySysDepat;
    }

    public void setOurwaySysDepat(OurwaySysDepat ourwaySysDepat) {
        this.ourwaySysDepat = ourwaySysDepat;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Integer getDeptRefOwid() {
        return deptRefOwid;
    }

    public void setDeptRefOwid(Integer deptRefOwid) {
        this.deptRefOwid = deptRefOwid;
    }

    public String getUserRefOwId() {
        return userRefOwId;
    }

    public void setUserRefOwId(String userRefOwId) {
        this.userRefOwId = userRefOwId;
    }


    public String getEmpPositionId() {
        return empPositionId;
    }

    public void setEmpPositionId(String empPositionId) {
        this.empPositionId = empPositionId;
    }


    public String getEmpPositionName() {
        return empPositionName;
    }

    public void setEmpPositionName(String empPositionName) {
        this.empPositionName = empPositionName;
    }


}
