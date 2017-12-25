package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CuiL on 2017-05-08.
 */
@Entity
@Table(name = "erp_goods_list")
public class ErpGoodsList_bak extends UUidEntity {
    @Basic
    @Column(name = "ERPGUEST_REF_OWID", nullable = true, length = 64)
    private String erpguestRefOwid;
    @Basic
    @Column(name = "ERPGOODS_REF_OWID", nullable = true, length = 64)
    private String erpgoodsRefOwid;
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

}
