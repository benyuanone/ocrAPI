package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;

/**
 * Created by Kevin on 2017-05-25.
 */
@Entity
@Table(name = "erp_currency_type", schema = "owboss", catalog = "")
public class ErpCurrencyType extends UUidEntity{
    @Basic
    @Column(name = "CURRENCY_TYPE_ID", nullable = false, length = 16)
    private String currencyTypeId;


    @Basic
    @Column(name = "NAME", nullable = true, length = 64)
    private String name;

    @Basic
    @Column(name = "SYMBOL", nullable = true, length = 64)
    private String symbol;

    @Basic
    @Column(name = "STATE", nullable = true)
    private Integer state;

    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    @Transient
    private Integer updateFlag;   //1:修改/新增  2：删除
    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(String currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
