package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by cancer on 2017-09-08.
 */
@Entity
@Table(name = "erp_ship")
public class ErpShip extends UUidEntity {
    @Basic
    @Column(name = "SHIP_ID", nullable = false, length = 32)
    private String shipId;
    @Basic
    @Column(name = "CHN_SHIP_NAME", nullable = false, length = 64)
    private String chnShipName;
    @Basic
    @Column(name = "ENG_SHIP_NAME", nullable = true, length = 64)
    private String engShipName;
    @Basic
    @Column(name = "SHIP_TYPE", nullable = false)
    private Integer shipType;
    @Basic
    @Column(name = "NATIONALITY", nullable = true, length = 64)
    private String nationality;
    @Basic
    @Column(name = "YEARS", nullable = true, length = 32)
    private String years;
    @Basic
    @Column(name = "CALL_SIGN", nullable = true, length = 128)
    private String callSign;
    @Basic
    @Column(name = "MMSI", nullable = true, length = 128)
    private String mmsi;
    @Basic
    @Column(name = "IMO", nullable = true, length = 128)
    private String imo;
    @Basic
    @Column(name = "LONGNESS", nullable = true, precision = 6)
    private BigDecimal longness;
    @Basic
    @Column(name = "WIDTH", nullable = true, precision = 6)
    private BigDecimal width;
    @Basic
    @Column(name = "DRAFT", nullable = true, precision = 6)
    private BigDecimal draft;
    @Basic
    @Column(name = "TOTAL_WEIGHT", nullable = true, precision = 6)
    private BigDecimal totalWeight;
    @Basic
    @Column(name = "NET_WEIGHT", nullable = true, precision = 6)
    private BigDecimal netWeight;
    @Basic
    @Column(name = "LOAD_WEIGTH", nullable = true, precision = 6)
    private BigDecimal loadWeigth;
    @Basic
    @Column(name = "CABIN_NUM", nullable = true)
    private Integer cabinNum;
    @Basic
    @Column(name = "SHIP_CAPACITY", nullable = true, precision = 6)
    private BigDecimal shipCapacity;
    @Basic
    @Column(name = "COMPANY", nullable = true, length = 128)
    private String company;
    @Basic
    @Column(name = "COMPANY_MAN", nullable = true, length = 64)
    private String companyMan;
    @Basic
    @Column(name = "COMPANY_PHONE", nullable = true, length = 32)
    private String companyPhone;
    @Basic
    @Column(name = "COMPANY_ADDRESS", nullable = true, length = 256)
    private String companyAddress;
    @Basic
    @Column(name = "TAXPAYER_NUMBER", nullable = true, length = 64)
    private String taxpayerNumber;
    @Basic
    @Column(name = "OPEN_BANK", nullable = true, length = 128)
    private String openBank;
    @Basic
    @Column(name = "BANK_ACCOUNT", nullable = true, length = 128)
    private String bankAccount;
    @Basic
    @Column(name = "SHIP_CHARGE", nullable = true, length = 32)
    private String shipCharge;
    @Basic
    @Column(name = "CHARGE_PHONE", nullable = true, length = 32)
    private String chargePhone;
    @Basic
    @Column(name = "SHIP_PROPERTY", nullable = true)
    private Integer shipProperty;
    @Basic
    @Column(name = "SHIP_REQUIRE", nullable = true, length = 256)
    private String shipRequire;
    @Basic
    @Column(name = "LIMITS", nullable = true)
    private Byte limits;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    @Basic
    @Column(name = "DELFLG", nullable = false)
    private Integer delflg;


    public String getOwid() {
        return owid;
    }

    public void setOwid(String owid) {
        this.owid = owid;
    }


    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }


    public String getChnShipName() {
        return chnShipName;
    }

    public void setChnShipName(String chnShipName) {
        this.chnShipName = chnShipName;
    }


    public String getEngShipName() {
        return engShipName;
    }

    public void setEngShipName(String engShipName) {
        this.engShipName = engShipName;
    }


    public Integer getShipType() {
        return shipType;
    }

    public void setShipType(Integer shipType) {
        this.shipType = shipType;
    }


    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }


    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }


    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }


    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }


    public BigDecimal getLongness() {
        return longness;
    }

    public void setLongness(BigDecimal longness) {
        this.longness = longness;
    }


    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }


    public BigDecimal getDraft() {
        return draft;
    }

    public void setDraft(BigDecimal draft) {
        this.draft = draft;
    }


    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }


    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }


    public BigDecimal getLoadWeigth() {
        return loadWeigth;
    }

    public void setLoadWeigth(BigDecimal loadWeigth) {
        this.loadWeigth = loadWeigth;
    }


    public Integer getCabinNum() {
        return cabinNum;
    }

    public void setCabinNum(Integer cabinNum) {
        this.cabinNum = cabinNum;
    }


    public BigDecimal getShipCapacity() {
        return shipCapacity;
    }

    public void setShipCapacity(BigDecimal shipCapacity) {
        this.shipCapacity = shipCapacity;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getCompanyMan() {
        return companyMan;
    }

    public void setCompanyMan(String companyMan) {
        this.companyMan = companyMan;
    }


    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }


    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public void setTaxpayerNumber(String taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }


    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }


    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }


    public String getShipCharge() {
        return shipCharge;
    }

    public void setShipCharge(String shipCharge) {
        this.shipCharge = shipCharge;
    }


    public String getChargePhone() {
        return chargePhone;
    }

    public void setChargePhone(String chargePhone) {
        this.chargePhone = chargePhone;
    }


    public Integer getShipProperty() {
        return shipProperty;
    }

    public void setShipProperty(Integer shipProperty) {
        this.shipProperty = shipProperty;
    }


    public String getShipRequire() {
        return shipRequire;
    }

    public void setShipRequire(String shipRequire) {
        this.shipRequire = shipRequire;
    }


    public Byte getLimits() {
        return limits;
    }

    public void setLimits(Byte limits) {
        this.limits = limits;
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

    public void setDelflg(Integer delflg) {
        this.delflg = delflg;
    }

}
