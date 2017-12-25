package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by jack on 2017/5/4.
 */
@Entity
@Table(name = "erp_berth")
public class ErpBerth extends UUidEntity {


    @Basic
    @Column(name = "ERPEMPLOYEE_REF_OWID", nullable = true, length = 64)
    private String erpemployeeRefOwid;
    public String getErpemployeeRefOwid() {
        return erpemployeeRefOwid;
    }

    public void setErpemployeeRefOwid(String erpemployeeRefOwid) {
        this.erpemployeeRefOwid = erpemployeeRefOwid;
    }

    @Basic
    @Column(name = "BERTH_ID", nullable = false, length = 64)
    private String berthId;
    public String getBerthId() {
        return berthId;
    }

    public void setBerthId(String berthId) {
        this.berthId = berthId;
    }

    @Basic
    @Column(name = "TON_LEVEL", nullable = true, precision = 6)
    private BigDecimal tonLevel;
    public BigDecimal getTonLevel() {
        return tonLevel;
    }

    public void setTonLevel(BigDecimal tonLevel) {
        this.tonLevel = tonLevel;
    }

    @Basic
    @Column(name = "PRINCIPAL", nullable = true, length = 16)
    private String principal;
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Basic
    @Column(name = "EXPLAIN", nullable = true, length = 800)
    private String explain;
    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Basic
    @Column(name = "COMPANY_BERTH", nullable = true)
    private Byte companyBerth;
    public Byte getCompanyBerth() {
        return companyBerth;
    }

    public void setCompanyBerth(Byte companyBerth) {
        this.companyBerth = companyBerth;
    }

    @Basic
    @Column(name = "BERTH_DRAUGHT", nullable = true, precision = 6)
    private BigDecimal berthDraught;
    public BigDecimal getBerthDraught() {
        return berthDraught;
    }

    public void setBerthDraught(BigDecimal berthDraught) {
        this.berthDraught = berthDraught;
    }

    @Basic
    @Column(name = "BERTH_LENGTH", nullable = true, precision = 6)
    private BigDecimal berthLength;
    public BigDecimal getBerthLength() {
        return berthLength;
    }

    public void setBerthLength(BigDecimal berthLength) {
        this.berthLength = berthLength;
    }

    @Basic
    @Column(name = "BERTH_WIDTH", nullable = true, precision = 6)
    private BigDecimal berthWidth;
    public BigDecimal getBerthWidth() {
        return berthWidth;
    }

    public void setBerthWidth(BigDecimal berthWidth) {
        this.berthWidth = berthWidth;
    }

    @Basic
    @Column(name = "STATE", nullable = true)
    private Integer state;
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}