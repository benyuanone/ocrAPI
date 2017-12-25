package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;

/*<p>方法 ErpSetOperateway : <p>
*<p>说明:基础资料-作业方式实体类</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-22 11:07
</pre>
*/
@Entity
@Table(name = "erp_set_operateway")
public class ErpSetOperateway extends UUidEntity{
    @Basic
    @Column(name = "OPERATEWAY_ID", nullable = true, length = 32)
    private String operatewayId;
    @Basic
    @Column(name = "OPERATEWAY_NAME", nullable = true, length = 64)
    private String operatewayName;
    @Basic
    @Column(name = "OPERATEWAY_TYPE", nullable = true)
    private Integer operatewayType;
    @Basic
    @Column(name = "INOUT_WAY", nullable = true)
    private Integer inoutWay;
    @Basic
    @Column(name = "OPERATE_COMPLETE_TYPE", nullable = true)
    private Integer operateCompleteType;
    @Basic
    @Column(name = "OPERATE_TIME_STANDARD", nullable = true)
    private Integer operateTimeStandard;
    @Basic
    @Column(name = "STOCK_UPDATE_TIME_STANDARD", nullable = true)
    private Integer stockUpdateTimeStandard;
    @Basic
    @Column(name = "STOCK_UPDATE_NUM_STANDARD", nullable = true)
    private Integer stockUpdateNumStandard;
    @Basic
    @Column(name = "CHARGE_TIME_STANDARD", nullable = true)
    private Integer chargeTimeStandard;
    @Basic
    @Column(name = "CHARGE_NUM_STANDARD", nullable = true)
    private Integer chargeNumStandard;
    @Basic
    @Column(name = "IS_DEFAULT", nullable = true)
    private Integer isDefault;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 256)
    private String remark;
    @Transient
    private Integer updateFlag;   //1:修改/新增  2：删除

    public String getOperatewayId() {
        return operatewayId;
    }

    public void setOperatewayId(String operatewayId) {
        this.operatewayId = operatewayId;
    }

    public String getOperatewayName() {
        return operatewayName;
    }

    public void setOperatewayName(String operatewayName) {
        this.operatewayName = operatewayName;
    }

    public Integer getOperatewayType() {
        return operatewayType;
    }

    public void setOperatewayType(Integer operatewayType) {
        this.operatewayType = operatewayType;
    }

    public Integer getInoutWay() {
        return inoutWay;
    }

    public void setInoutWay(Integer inoutWay) {
        this.inoutWay = inoutWay;
    }

    public Integer getOperateCompleteType() {
        return operateCompleteType;
    }

    public void setOperateCompleteType(Integer operateCompleteType) {
        this.operateCompleteType = operateCompleteType;
    }

    public Integer getOperateTimeStandard() {
        return operateTimeStandard;
    }

    public void setOperateTimeStandard(Integer operateTimeStandard) {
        this.operateTimeStandard = operateTimeStandard;
    }

    public Integer getStockUpdateTimeStandard() {
        return stockUpdateTimeStandard;
    }

    public void setStockUpdateTimeStandard(Integer stockUpdateTimeStandard) {
        this.stockUpdateTimeStandard = stockUpdateTimeStandard;
    }

    public Integer getStockUpdateNumStandard() {
        return stockUpdateNumStandard;
    }

    public void setStockUpdateNumStandard(Integer stockUpdateNumStandard) {
        this.stockUpdateNumStandard = stockUpdateNumStandard;
    }

    public Integer getChargeTimeStandard() {
        return chargeTimeStandard;
    }

    public void setChargeTimeStandard(Integer chargeTimeStandard) {
        this.chargeTimeStandard = chargeTimeStandard;
    }

    public Integer getChargeNumStandard() {
        return chargeNumStandard;
    }

    public void setChargeNumStandard(Integer chargeNumStandard) {
        this.chargeNumStandard = chargeNumStandard;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }
}
