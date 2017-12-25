package com.ourway.monitor.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cancer on 2017-10-23.
 */
@Entity
@Table(name = "c_tank_guest_and_operate", schema = "owboss", catalog = "")
public class CTankGuestAndOperate extends UUidEntity {
    @Basic
    @Column(name = "TANK_ID", nullable = true, length = 64)
    private String tankId;
    @Basic
    @Column(name = "GUEST_NAME", nullable = true, length = 64)
    private String guestName;
    @Basic
    @Column(name = "NUMBER", nullable = true, precision = 6)
    private BigDecimal number;
    @Basic
    @Column(name = "NUM_PERCENT", nullable = true, precision = 6)
    private BigDecimal numPercent;
    @Temporal(TemporalType.DATE)
    @Column(name = "OPERATE_DATE", nullable = true)
    private Date operateDate;
    @Basic
    @Column(name = "OPERATEWAY_NAME", nullable = true, length = 64)
    private String operatewayName;
    @Basic
    @Column(name = "OPERATE_NUM", nullable = true, precision = 6)
    private BigDecimal operateNum;
    @Temporal(TemporalType.DATE)
    @Column(name = "EXPECT_OPEATE_DATE", nullable = true)
    private Date expectOpeateDate;
    @Basic
    @Column(name = "EXPECT_OPEATEWAY_NAME", nullable = true, length = 64)
    private String expectOpeatewayName;
    @Basic
    @Column(name = "EXPECT_OPEATE_NUM", nullable = true, precision = 6)
    private BigDecimal expectOpeateNum;
    @Basic
    @Column(name = "DELFLG", nullable = true)
    private Integer delflg;

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public void setExpectOpeateDate(Date expectOpeateDate) {
        this.expectOpeateDate = expectOpeateDate;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public Date getExpectOpeateDate() {
        return expectOpeateDate;
    }

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }


    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }


    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }


    public BigDecimal getNumPercent() {
        return numPercent;
    }

    public void setNumPercent(BigDecimal numPercent) {
        this.numPercent = numPercent;
    }



    public String getOperatewayName() {
        return operatewayName;
    }

    public void setOperatewayName(String operatewayName) {
        this.operatewayName = operatewayName;
    }


    public BigDecimal getOperateNum() {
        return operateNum;
    }

    public void setOperateNum(BigDecimal operateNum) {
        this.operateNum = operateNum;
    }


    public String getExpectOpeatewayName() {
        return expectOpeatewayName;
    }

    public void setExpectOpeatewayName(String expectOpeatewayName) {
        this.expectOpeatewayName = expectOpeatewayName;
    }


    public BigDecimal getExpectOpeateNum() {
        return expectOpeateNum;
    }

    public void setExpectOpeateNum(BigDecimal expectOpeateNum) {
        this.expectOpeateNum = expectOpeateNum;
    }


    public Integer getDelflg() {
        return delflg;
    }

    public void setDelflg(Integer delflg) {
        this.delflg = delflg;
    }

}
