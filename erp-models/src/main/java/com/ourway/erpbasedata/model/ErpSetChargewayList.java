package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by cancer on 2017-09-26.
 */
@Entity
@Table(name = "erp_set_chargeway_list", schema = "owboss", catalog = "")
public class ErpSetChargewayList extends UUidEntity {
    @Basic
    @Column(name = "ERPSETCHARGEWAY_REF_OWID", nullable = true, length = 64)
    private String erpsetchargewayRefOwid;
    @Basic
    @Column(name = "CHARGEWAY_LIST_ID", nullable = false, length = 32)
    private String chargewayListId;
    @Basic
    @Column(name = "CHARGEWAY_LIST_NAME", nullable = false)
    private int chargewayListName;
    @Basic
    @Column(name = "PERCENT", nullable = false, precision = 6)
    private BigDecimal percent;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    @Basic
    @Column(name = "DELFLG", nullable = false)
    private Integer delflg;

    @Transient
    private Integer updateFlag;   //1:修改/新增  2：删除

    public String getErpsetchargewayRefOwid() {
        return erpsetchargewayRefOwid;
    }

    public void setErpsetchargewayRefOwid(String erpsetchargewayRefOwid) {
        this.erpsetchargewayRefOwid = erpsetchargewayRefOwid;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }


    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }


    public String getChargewayListId() {
        return chargewayListId;
    }

    public void setChargewayListId(String chargewayListId) {
        this.chargewayListId = chargewayListId;
    }


    public int getChargewayListName() {
        return chargewayListName;
    }

    public void setChargewayListName(int chargewayListName) {
        this.chargewayListName = chargewayListName;
    }


    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
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
