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
@Table(name = "erp_goods_type")
public class ErpGoodsType extends UUidEntity {
    private String typeId;
    private String typeName;
    private String pid;
    private int orderNo;
    private String remark;
    @Basic
    @Column(name = "DELFLG", nullable = true)
    private Integer delflg;
    public Integer getDelflg() {
        return delflg;
    }

    @Basic
    @Column(name = "TYPE_ID", nullable = false, length = 64)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "TYPE_NAME", nullable = false, length = 64)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "PID", nullable = false, length = 64)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "ORDER_NO", nullable = false)
    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErpGoodsType that = (ErpGoodsType) o;

        if (orderNo != that.orderNo) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeId != null ? typeId.hashCode() : 0;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + orderNo;
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
