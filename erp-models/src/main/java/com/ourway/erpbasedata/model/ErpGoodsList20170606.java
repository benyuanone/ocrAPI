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
@Table(name = "erp_goods_list", schema = "ourway", catalog = "")
public class ErpGoodsList20170606 extends UUidEntity{
    private String erpguestRefOwid;
    private String erpgoodsRefOwid;
    private String otherName;
    private String remark;
    private String annex;
    private String annexName;

    @Basic
    @Column(name = "ERPGUEST_REF_OWID", nullable = true, length = 64)
    public String getErpguestRefOwid() {
        return erpguestRefOwid;
    }

    public void setErpguestRefOwid(String erpguestRefOwid) {
        this.erpguestRefOwid = erpguestRefOwid;
    }

    @Basic
    @Column(name = "ERPGOODS_REF_OWID", nullable = true, length = 64)
    public String getErpgoodsRefOwid() {
        return erpgoodsRefOwid;
    }

    public void setErpgoodsRefOwid(String erpgoodsRefOwid) {
        this.erpgoodsRefOwid = erpgoodsRefOwid;
    }

    @Basic
    @Column(name = "OTHER_NAME", nullable = true, length = 255)
    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "ANNEX", nullable = true, length = 255)
    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    @Basic
    @Column(name = "ANNEX_NAME", nullable = true, length = 255)
    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErpGoodsList20170606 that = (ErpGoodsList20170606) o;

        if (erpguestRefOwid != null ? !erpguestRefOwid.equals(that.erpguestRefOwid) : that.erpguestRefOwid != null)
            return false;
        if (erpgoodsRefOwid != null ? !erpgoodsRefOwid.equals(that.erpgoodsRefOwid) : that.erpgoodsRefOwid != null)
            return false;
        if (otherName != null ? !otherName.equals(that.otherName) : that.otherName != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (annex != null ? !annex.equals(that.annex) : that.annex != null) return false;
        if (annexName != null ? !annexName.equals(that.annexName) : that.annexName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = erpguestRefOwid != null ? erpguestRefOwid.hashCode() : 0;
        result = 31 * result + (erpgoodsRefOwid != null ? erpgoodsRefOwid.hashCode() : 0);
        result = 31 * result + (otherName != null ? otherName.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (annex != null ? annex.hashCode() : 0);
        result = 31 * result + (annexName != null ? annexName.hashCode() : 0);
        return result;
    }
}
