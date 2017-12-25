package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by jack on 2017/5/4.
 */
@Entity
@Table(name = "erp_guest")
public class ErpGuest extends UUidEntity {

    @Basic
    @Column(name = "GUEST_ID", nullable = true, length = 16)
    private String guestId;
    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    @Basic
    @Column(name = "GUEST_NAME", nullable = true, length = 128)
    private String guestName;
    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    @Basic
    @Column(name = "SHORT_NAME", nullable = true, length = 128)
    private String shortName;
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "ENG_NAME", nullable = true, length = 128)
    private String engName;
    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    @Basic
    @Column(name = "ENG_SHORT_NAME", nullable = true, length = 128)
    private String engShortName;
    public String getEngShortName() {
        return engShortName;
    }

    public void setEngShortName(String engShortName) {
        this.engShortName = engShortName;
    }

    @Basic
    @Column(name = "GUEST_TYPE", nullable = true)
    private Integer guestType;
    public Integer getGuestType() {
        return guestType;
    }

    public void setGuestType(Integer guestType) {
        this.guestType = guestType;
    }

    @Basic
    @Column(name = "ENTERPRISE_NATURE", nullable = true)
    private Integer enterpriseNature;
    public Integer getEnterpriseNature() {
        return enterpriseNature;
    }

    public void setEnterpriseNature(Integer enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    @Basic
    @Column(name = "TRADE_TYPE", nullable = true)
    private Integer tradeType;
    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    @Basic
    @Column(name = "BELONGS_TO_AREA", nullable = true)
    private Integer belongsToArea;
    public Integer getBelongsToArea() {
        return belongsToArea;
    }

    public void setBelongsToArea(Integer belongsToArea) {
        this.belongsToArea = belongsToArea;
    }

    @Basic
    @Column(name = "INTRODUCTION", nullable = true, length = 1024)
    private String introduction;
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "BUSINESS_SCOPE", nullable = true, length = 1024)
    private String businessScope;
    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    @Basic
    @Column(name = "ADDRESS", nullable = true, length = 256)
    private String address;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "POSTALCODE", nullable = true, length = 16)
    private String postalcode;
    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    @Basic
    @Column(name = "PHONE", nullable = true, length = 64)
    private String phone;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "FAX", nullable = true, length = 64)
    private String fax;
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 64)
    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "WEBSITE", nullable = true, length = 128)
    private String website;
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Basic
    @Column(name = "LEGAL_PERSON", nullable = true, length = 32)
    private String legalPerson;
    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    @Basic
    @Column(name = "GUEST_GRADE", nullable = true)
    private Integer guestGrade;
    public Integer getGuestGrade() {
        return guestGrade;
    }

    public void setGuestGrade(Integer guestGrade) {
        this.guestGrade = guestGrade;
    }

    @Basic
    @Column(name = "CREDIT_LIMIT", nullable = true, precision = 6)
    private BigDecimal creditLimit;
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Basic
    @Column(name = "INVOICE_UNIT", nullable = true, length = 128)
    private String invoiceUnit;
    public String getInvoiceUnit() {
        return invoiceUnit;
    }

    public void setInvoiceUnit(String invoiceUnit) {
        this.invoiceUnit = invoiceUnit;
    }

    @Basic
    @Column(name = "INVOICE_NO", nullable = true, length = 64)
    private String invoiceNo;
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Basic
    @Column(name = "INVOICE_ADDRESS", nullable = true, length = 256)
    private String invoiceAddress;
    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    @Basic
    @Column(name = "INVOICE_PHONE", nullable = true, length = 64)
    private String invoicePhone;
    public String getInvoicePhone() {
        return invoicePhone;
    }

    public void setInvoicePhone(String invoicePhone) {
        this.invoicePhone = invoicePhone;
    }

    @Basic
    @Column(name = "INVOICE_BANK", nullable = true, length = 64)
    private String invoiceBank;
    public String getInvoiceBank() {
        return invoiceBank;
    }

    public void setInvoiceBank(String invoiceBank) {
        this.invoiceBank = invoiceBank;
    }

    @Basic
    @Column(name = "INVOICE_ACCOUNT", nullable = true, length = 64)
    private String invoiceAccount;
    public String getInvoiceAccount() {
        return invoiceAccount;
    }

    public void setInvoiceAccount(String invoiceAccount) {
        this.invoiceAccount = invoiceAccount;
    }

    @Basic
    @Column(name = "INVOICE_REC_NAME", nullable = true, length = 64)
    private String invoiceRecName;
    public String getInvoiceRecName() {
        return invoiceRecName;
    }

    public void setInvoiceRecName(String invoiceRecName) {
        this.invoiceRecName = invoiceRecName;
    }

    @Basic
    @Column(name = "INVOICE_REC_PHONE", nullable = true, length = 64)
    private String invoiceRecPhone;
    public String getInvoiceRecPhone() {
        return invoiceRecPhone;
    }

    public void setInvoiceRecPhone(String invoiceRecPhone) {
        this.invoiceRecPhone = invoiceRecPhone;
    }

    @Basic
    @Column(name = "INVOICE_REC_MOBILE", nullable = true, length = 64)
    private String invoiceRecMobile;
    public String getInvoiceRecMobile() {
        return invoiceRecMobile;
    }

    public void setInvoiceRecMobile(String invoiceRecMobile) {
        this.invoiceRecMobile = invoiceRecMobile;
    }

    @Basic
    @Column(name = "INVOICE_REC_ADDRESS", nullable = true, length = 256)
    private String invoiceRecAddress;
    public String getInvoiceRecAddress() {
        return invoiceRecAddress;
    }

    public void setInvoiceRecAddress(String invoiceRecAddress) {
        this.invoiceRecAddress = invoiceRecAddress;
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

    @Basic
    @Column(name = "STATE", nullable = true)
    private Integer state;
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Transient
    //客户资料树结构相关定值id
    private Integer guestIdRefId = 1;
    @Transient
    //客户资料树结构相关定值fid
    private Integer guestIdRefFId = -1;

    public Integer getGuestIdRefId() {
        return guestIdRefId;
    }

    public void setGuestIdRefId(Integer guestIdRefId) {
        this.guestIdRefId = guestIdRefId;
    }

    public Integer getGuestIdRefFId() {
        return guestIdRefFId;
    }

    public void setGuestIdRefFId(Integer guestIdRefFId) {
        this.guestIdRefFId = guestIdRefFId;
    }
}
