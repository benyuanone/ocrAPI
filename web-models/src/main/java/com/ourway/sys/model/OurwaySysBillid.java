package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_billid")
public class OurwaySysBillid extends UUidEntity {

    @Column(name = "BILL_TYPE", nullable = true)
    private String billType;
    @Column(name = "BILL_NAME", nullable = true, length = 240)
    private String billName;
    @Column(name = "BILL_YMD", nullable = true, length = 2400)
    private String billYmd;
    @Column(name = "BILL_XH")
    private Integer billXh;
    @Column(name = "BILL_XHLABEL", nullable = true)
    private String billXhlabel;
    @Column(name = "BILL_STATUS", nullable = true)
    private Byte billStatus;

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillYmd() {
        return billYmd;
    }

    public void setBillYmd(String billYmd) {
        this.billYmd = billYmd;
    }

    public Integer getBillXh() {
        return billXh;
    }

    public void setBillXh(Integer billXh) {
        this.billXh = billXh;
    }

    public String getBillXhlabel() {
        return billXhlabel;
    }

    public void setBillXhlabel(String billXhlabel) {
        this.billXhlabel = billXhlabel;
    }

    public Byte getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Byte billStatus) {
        this.billStatus = billStatus;
    }
}
