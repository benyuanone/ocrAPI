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
@Table(name = "ourway_sys_dfilter")
public class OurwaySysDfilter extends UUidEntity {

    @Column(name = "API_REF_OWID", nullable = true, length = 64)
    private String apiRefOwid;
    @Column(name = "USER_REF_OWID", nullable = true, length = 64)
    private String userRefOwid;
    @Column(name = "PAGE_CA", nullable = true, length = 64)
    private String pageCa;
    @Column(name = "TYPE", nullable = true)
    private Byte type;
    @Column(name = "PRIORITY", nullable = true)
    private Integer priority;
    @Column(name = "VIEW_AREA", nullable = true)
    private Byte viewArea;
    @Column(name = "VIEW_FLAG", nullable = true)
    private Byte viewFlag;
    @Column(name = "VIEW_CONDITION", nullable = true, length = 240)
    private String viewCondition;
    @Column(name = "DEPT_ID", nullable = true)
    private Integer deptId;
    @Column(name = "DEPT_PATH", nullable = true)
    private String deptPath;
    @Column(name = "INDEX_NO", nullable = true)
    private Integer indexNo;
    @Transient
    private Integer updateFlag;
    @Transient
    private OurwaySysEmploys ourwaySysEmploys;

    public OurwaySysEmploys getOurwaySysEmploys() {
        return ourwaySysEmploys;
    }

    public void setOurwaySysEmploys(OurwaySysEmploys ourwaySysEmploys) {
        this.ourwaySysEmploys = ourwaySysEmploys;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getApiRefOwid() {
        return apiRefOwid;
    }

    public void setApiRefOwid(String apiRefOwid) {
        this.apiRefOwid = apiRefOwid;
    }

    public String getUserRefOwid() {
        return userRefOwid;
    }

    public void setUserRefOwid(String userRefOwid) {
        this.userRefOwid = userRefOwid;
    }

    public String getPageCa() {
        return pageCa;
    }

    public void setPageCa(String pageCa) {
        this.pageCa = pageCa;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Byte getViewArea() {
        return viewArea;
    }

    public void setViewArea(Byte viewArea) {
        this.viewArea = viewArea;
    }

    public String getViewCondition() {
        return viewCondition;
    }

    public void setViewCondition(String viewCondition) {
        this.viewCondition = viewCondition;
    }

    public Byte getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(Byte viewFlag) {
        this.viewFlag = viewFlag;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptPath() {
        return deptPath;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }


}
