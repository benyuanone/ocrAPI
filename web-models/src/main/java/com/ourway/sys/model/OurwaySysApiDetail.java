package com.ourway.sys.model;

import com.ourway.base.model.IncrementIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_api_detail")

public class OurwaySysApiDetail extends IncrementIdEntity{
    @Column(name = "API_REF_OWID", nullable = true, length = 64)
    private String apiRefOwid;
    @Column(name = "TYPE", nullable = true)
    private Byte type;
    @Column(name = "FID", nullable = true)
    private Integer fid;
    @Column(name = "PATH", nullable = true, length = 240)
    private String path;
    @Column(name = "VAR_NAME", nullable = true, length = 240)
    private String varName;
    @Column(name = "VAR_CODE", nullable = true, length = 240)
    private String varCode;
    @Column(name = "VAR_TYPE", nullable = true, length = 24)
    private String varType;
    @Column(name = "VAR_LEN", nullable = true, length = 24)
    private String varLen;
    @Column(name = "VAR_REQ", nullable = true)
    private Byte varReq;
    @Column(name = "VAR_FORMAT", nullable = true, length = 240)
    private String varFormat;
    @Column(name = "VAR_DATA", nullable = true, length = 240)
    private String varData;
    @Column(name = "XH", nullable = true)
    private Integer xh;
    @Column(name = "INDEX_NO", nullable = true)
    private Integer indexNo;

    @Transient
    private Integer updateFlag;   //1:修改/新增  2：删除


    public String getApiRefOwid() {
        return apiRefOwid;
    }

    public void setApiRefOwid(String apiRefOwid) {
        this.apiRefOwid = apiRefOwid;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }


    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }


    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }


    public String getVarCode() {
        return varCode;
    }

    public void setVarCode(String varCode) {
        this.varCode = varCode;
    }


    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }


    public String getVarLen() {
        return varLen;
    }

    public void setVarLen(String varLen) {
        this.varLen = varLen;
    }


    public Byte getVarReq() {
        return varReq;
    }

    public void setVarReq(Byte varReq) {
        this.varReq = varReq;
    }


    public String getVarFormat() {
        return varFormat;
    }

    public void setVarFormat(String varFormat) {
        this.varFormat = varFormat;
    }


    public String getVarData() {
        return varData;
    }

    public void setVarData(String varData) {
        this.varData = varData;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }


    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }


}
