package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Kevin on 2017-11-08.
 */
@Entity
@Table(name = "erp_guest_linklist")
public class ErpGuestLinklist extends UUidEntity{

    @Transient
    private ErpGoods erpGoods;
    public ErpGoods getErpGoods() {
        return erpGoods;
    }
    public void setErpGoods(ErpGoods erpGoods) {
        this.erpGoods = erpGoods;
    }

    @Transient
    private Integer updateFlag;
    public Integer getUpdateFlag() {
        return updateFlag;
    }
    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    @Basic
    @Column(name = "ERPGOODS_REF_OWID", nullable = true, length = 64)
    private String erpgoodsRefOwid;
    public String getErpgoodsRefOwid() {
        return erpgoodsRefOwid;
    }
    public void setErpgoodsRefOwid(String erpgoodsRefOwid) {
        this.erpgoodsRefOwid = erpgoodsRefOwid;
    }

    @Basic
    @Column(name = "ERPGUEST_REF_OWID", nullable = true, length = 64)
    private String erpguestRefOwid;
    public String getErpguestRefOwid() {
        return erpguestRefOwid;
    }
    public void setErpguestRefOwid(String erpguestRefOwid) {
        this.erpguestRefOwid = erpguestRefOwid;
    }

    @Basic
    @Column(name = "LINKMAN", nullable = false, length = 32)
    private String linkman;
    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    @Basic
    @Column(name = "POSITION", nullable = true, length = 64)
    private String position;
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
    @Column(name = "MOBILE_PHONE", nullable = true, length = 64)
    private String mobilePhone;
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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
    @Column(name = "QQ", nullable = true, length = 64)
    private String qq;
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "WECHAT", nullable = true, length = 64)
    private String wechat;
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Basic
    @Column(name = "BIRTHDAY", nullable = true, length = 8)
    private String birthday;
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "FIRST_CHOICE", nullable = true)
    private Integer firstChoice;
    public Integer getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(Integer firstChoice) {
        this.firstChoice = firstChoice;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 256)
    private String remark;
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
