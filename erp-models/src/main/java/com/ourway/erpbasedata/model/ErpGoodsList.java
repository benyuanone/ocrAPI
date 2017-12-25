package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;

/**
 * Created by CuiL on 2017-05-08.
 */
@Entity
@Table(name = "erp_goods_list")
public class ErpGoodsList extends UUidEntity {
    @Basic
    @Column(name = "ERPGUEST_REF_OWID", nullable = true, length = 64)
    private String erpguestRefOwid;
    @Column(name = "INDEXNO")
    private Integer indexno;
    @Basic
    @Column(name = "ERPGOODS_REF_OWID", nullable = true, length = 64)
    private String erpgoodsRefOwid;
    @Basic
    @Column(name = "OTHER_NAME_ENG", nullable = true, length = 255)
    private String otherNameEng;
    @Basic
    @Column(name = "OTHER_NAME", nullable = true, length = 255)
    private String otherName;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    @Basic
    @Column(name = "ANNEX", nullable = true, length = 255)
    private String annex;
    @Basic
    @Column(name = "ANNEX_NAME", nullable = true, length = 255)
    private String annexName;
    @Transient
    private ErpGuest erpGuest;
    @Transient
    private Integer updateFlag;   //1:修改/新增  2：删


    public String getErpguestRefOwid() {
        return erpguestRefOwid;
    }

    public void setErpguestRefOwid(String erpguestRefOwid) {
        this.erpguestRefOwid = erpguestRefOwid;
    }


    public String getErpgoodsRefOwid() {
        return erpgoodsRefOwid;
    }

    public void setErpgoodsRefOwid(String erpgoodsRefOwid) {
        this.erpgoodsRefOwid = erpgoodsRefOwid;
    }


    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }


    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    public ErpGuest getErpGuest() {
        return erpGuest;
    }

    public void setErpGuest(ErpGuest erpGuest) {
        this.erpGuest = erpGuest;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getOtherNameEng() {
        return otherNameEng;
    }

    public void setOtherNameEng(String otherNameEng) {
        this.otherNameEng = otherNameEng;
    }

    public Integer getIndexno() {
        return indexno;
    }

    public void setIndexno(Integer indexno) {
        this.indexno = indexno;
    }
}
