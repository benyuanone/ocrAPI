package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by cancer on 2017-09-25.
 */
@Entity
@Table(name = "erp_set_chargeway", schema = "owboss", catalog = "")
public class ErpSetChargeway extends UUidEntity {
    @Basic
    @Column(name = "CHARGEWAY_ID", nullable = false, length = 32)
    private String chargewayId;
    @Basic
    @Column(name = "CHARGEWAY_NAME", nullable = false, length = 64)
    private String chargewayName;
    @Basic
    @Column(name = "BELONG_TO", nullable = true)
    private Integer belongTo;
    @Basic
    @Column(name = "CHARGE_DESCRIBE", nullable = true, length = 1024)
    private String chargeDescribe;
    @Basic
    @Column(name = "PRICE", nullable = true, precision = 6)
    private BigDecimal price;
    @Basic
    @Column(name = "PRICE_UNIT", nullable = true, length = 64)
    private String priceUnit;
    @Basic
    @Column(name = "CHARGETYPE_NAME", nullable = false)
    private int chargetypeName;
    @Basic
    @Column(name = "CHARGE_TYPE", nullable = false)
    private int chargeType;
    @Basic
    @Column(name = "GENERATE_TIME", nullable = false)
    private int generateTime;
    @Basic
    @Column(name = "CAL_DESCRIBE", nullable = true, length = 1024)
    private String calDescribe;
    @Basic
    @Column(name = "IS_DEFAULT", nullable = true)
    private Integer isDefault;
    @Basic
    @Column(name = "CHARGE_SPLIT", nullable = true)
    private Integer chargeSplit;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    @Basic
    @Column(name = "DELFLG", nullable = false)
    private Integer delflg;

    public int getChargetypeName() {
        return chargetypeName;
    }

    public void setChargetypeName(int chargetypeName) {
        this.chargetypeName = chargetypeName;
    }

    @Override
    public void setDelflg(Integer delflg) {
        this.delflg = delflg;
    }

    public String getChargewayId() {
        return chargewayId;
    }

    public void setChargewayId(String chargewayId) {
        this.chargewayId = chargewayId;
    }


    public String getChargewayName() {
        return chargewayName;
    }

    public void setChargewayName(String chargewayName) {
        this.chargewayName = chargewayName;
    }


    public Integer getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(Integer belongTo) {
        this.belongTo = belongTo;
    }


    public String getChargeDescribe() {
        return chargeDescribe;
    }

    public void setChargeDescribe(String chargeDescribe) {
        this.chargeDescribe = chargeDescribe;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }


    public int getChargeType() {
        return chargeType;
    }

    public void setChargeType(int chargeType) {
        this.chargeType = chargeType;
    }


    public int getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(int generateTime) {
        this.generateTime = generateTime;
    }


    public String getCalDescribe() {
        return calDescribe;
    }

    public void setCalDescribe(String calDescribe) {
        this.calDescribe = calDescribe;
    }


    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }


    public Integer getChargeSplit() {
        return chargeSplit;
    }

    public void setChargeSplit(Integer chargeSplit) {
        this.chargeSplit = chargeSplit;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getDelflg() {
        return delflg;
    }

}
