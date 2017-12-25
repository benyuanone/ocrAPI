package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jack on 2017/5/4.
 */
@Entity
@Table(name = "erp_tank_list")
public class ErpTankList extends UUidEntity {
    @Basic
    @Column(name = "ERPTANK_REF_OWID", nullable = true, length = 64)
    private String erptankRefOwid;
    @Basic
    @Column(name = "ERPGOODS_REF_OWID", nullable = true, length = 64)
    private String erpgoodsRefOwid;
    @Basic
    @Column(name = "SAFE_CAPACITY", nullable = false, precision = 6)
    private BigDecimal safeCapacity;
    @Basic
    @Column(name = "LOW_HIGHLY", nullable = true, precision = 6)
    private BigDecimal lowHighly;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    @Basic
    @Column(name = "DELFLG", nullable = true)
    private Integer delflg;
    @Column(name = "INDEXNO")
    private Integer indexno;

    public Integer getIndexno() {
        return indexno;
    }

    public void setIndexno(Integer indexno) {
        this.indexno = indexno;
    }

    public Integer getDelflg() {
        return delflg;
    }

    public void setDelflg(Integer delflg) {
        this.delflg = delflg;
    }
    @Transient
    private ErpGoods erpGoods;
    @Transient
    private Integer updateFlag;   //1:修改/新增  2：删除

    public ErpGoods getErpGoods() {
        return erpGoods;
    }

    public void setErpGoods(ErpGoods erpGoods) {
        this.erpGoods = erpGoods;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }


    public String getErptankRefOwid() {
        return erptankRefOwid;
    }

    public void setErptankRefOwid(String erptankRefOwid) {
        this.erptankRefOwid = erptankRefOwid;
    }

    public String getErpgoodsRefOwid() {
        return erpgoodsRefOwid;
    }

    public void setErpgoodsRefOwid(String erpgoodsRefOwid) {
        this.erpgoodsRefOwid = erpgoodsRefOwid;
    }


    public BigDecimal getSafeCapacity() {
        return safeCapacity;
    }

    public void setSafeCapacity(BigDecimal safeCapacity) {
        this.safeCapacity = safeCapacity;
    }


    public BigDecimal getLowHighly() {
        return lowHighly;
    }

    public void setLowHighly(BigDecimal lowHighly) {
        this.lowHighly = lowHighly;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
