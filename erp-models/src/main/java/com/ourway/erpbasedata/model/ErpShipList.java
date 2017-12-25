package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;

/**
 * Created by cancer on 2017-09-08.
 */
@Entity
@Table(name = "erp_ship_list")
public class ErpShipList extends UUidEntity {
    @Basic
    @Column(name = "ERPSHIP_REF_OWID", nullable = true, length = 64)
    private String erpshipRefOwid;
    @Basic
    @Column(name = "ERPGOODS_REF_OWID", nullable = true, length = 64)
    private String erpgoodsRefOwid;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    @Basic
    @Column(name = "DELFLG", nullable = false)
    private Integer delflg;

    @Transient
    private ErpGoods erpGoods;
    @Transient
    private Integer updateFlag;   //1:修改/新增  2：删除

    public void setErpGoods(ErpGoods erpGoods) {
        this.erpGoods = erpGoods;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public ErpGoods getErpGoods() {
        return erpGoods;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public String getErpshipRefOwid() {
        return erpshipRefOwid;
    }

    public void setErpshipRefOwid(String erpshipRefOwid) {
        this.erpshipRefOwid = erpshipRefOwid;
    }


    public String getErpgoodsRefOwid() {
        return erpgoodsRefOwid;
    }

    public void setErpgoodsRefOwid(String erpgoodsRefOwid) {
        this.erpgoodsRefOwid = erpgoodsRefOwid;
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
