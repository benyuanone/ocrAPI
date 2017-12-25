package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jack on 2017/5/4.
 */
@Entity
@Table(name = "erp_employee")
public class ErpEmployee  extends UUidEntity {
    @Basic
    @Column(name = "CURRENCY_TYPE_ID", nullable = false, length = 16)
    private String currencyTypeId;
    @Basic
    @Column(name = "NAME", nullable = true, length = 64)
    private String name;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;

    public String getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(String currencytTypeId) {
        this.currencyTypeId = currencytTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
