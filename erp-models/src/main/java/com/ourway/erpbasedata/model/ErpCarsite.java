package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.utils.DicUtils;
import com.ourway.sys.utils.I18nUtils;

import javax.persistence.*;

/**<p>方法 ErpCarsite : <p>
 *<p>说明:装车台实例</p>
 *<pre>
 *@author zhou_xtian
 *@date 2017-05-04 10:28
</pre>
 */
@Entity
@Table(name = "erp_carsite")
public class ErpCarsite  extends UUidEntity {
    @Basic
    @Column(name = "ERPEMPLOYEE_REF_OWID", nullable = true, length = 64)
    private String erpemployeeRefOwid;
    @Basic
    @Column(name = "CARSITE_ID", nullable = false, length = 64)
    private String carsiteId;
    @Basic
    @Column(name = "CARNE_PLACE", nullable = false, length = 128)
    private String carnePlace;
    @Basic
    @Column(name = "TANK_LIST", nullable = false, length = 800)
    private String tankList;
    @Basic
    @Column(name = "ROAD_NAME", nullable = true, length = 256)
    private String roadName;
    @Basic
    @Column(name = "MAX_CAR", nullable = true)
    private Integer maxCar;
    @Basic
    @Column(name = "METER_TYPE", nullable = true, length = 64)
    private String meterType;
    @Basic
    @Column(name = "EXPLAINS", nullable = true, length = 256)
    private String explains;
    @Basic
    @Column(name = "ADDRESS", nullable = true, length = 256)
    private String address;
    @Basic
    @Column(name = "AUTO_CAR", nullable = true)
    private Byte autoCar;
    @Basic
    @Column(name = "ROUTE", nullable = true, length = 256)
    private String route;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    @Transient
    //装车台树结构相关定值id
    private Integer carsiteIdRefId = 1;
    @Transient
    //装车台树结构相关定值fid
    private Integer carsiteIdRefFId = -1;
    @Transient
    private ErpEmployee employee;

    public String getErpemployeeRefOwid() {
        return erpemployeeRefOwid;
    }

    public void setErpemployeeRefOwid(String erpemployeeRefOwid) {
        this.erpemployeeRefOwid = erpemployeeRefOwid;
    }

    public String getCarsiteId() {
        return carsiteId;
    }

    public void setCarsiteId(String carsiteId) {
        this.carsiteId = carsiteId;
    }

    public String getCarnePlace() {
        return carnePlace;
    }

    public void setCarnePlace(String carnePlace) {
        this.carnePlace = carnePlace;
    }

    public String getTankList() {
        return tankList;
    }

    public void setTankList(String tankList) {
        this.tankList = tankList;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Integer getMaxCar() {
        return maxCar;
    }

    public void setMaxCar(Integer maxCar) {
        this.maxCar = maxCar;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getAutoCar() {
        return autoCar;
    }

    public void setAutoCar(Byte autoCar) {
        this.autoCar = autoCar;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCarsiteIdRefId() {
        return carsiteIdRefId;
    }

    public void setCarsiteIdRefId(Integer carsiteIdRefId) {
        this.carsiteIdRefId = carsiteIdRefId;
    }

    public Integer getCarsiteIdRefFId() {
        return carsiteIdRefFId;
    }

    public void setCarsiteIdRefFId(Integer carsiteIdRefFId) {
        this.carsiteIdRefFId = carsiteIdRefFId;
    }

    public ErpEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(ErpEmployee employee) {
        this.employee = employee;
    }
}