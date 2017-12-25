package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Kevin on 2017-09-26.
 */
@Entity
@Table(name = "erp_tank_farm")
public class ErpTankFarm extends UUidEntity{

    @Basic
    @Column(name = "TANKFARM_ID", nullable = false, length = 64)
    private String tankfarmId;
    public String getTankfarmId() {
        return tankfarmId;
    }

    public void setTankfarmId(String tankfarmId) {
        this.tankfarmId = tankfarmId;
    }

    @Basic
    @Column(name = "TANKFARM_NAME", nullable = false, length = 128)
    private String tankfarmName;
    public String getTankfarmName() {
        return tankfarmName;
    }

    public void setTankfarmName(String tankfarmName) {
        this.tankfarmName = tankfarmName;
    }

    @Basic
    @Column(name = "CAPACITY", nullable = true, precision = 6)
    private BigDecimal capacity;
    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "TANK_NUM", nullable = true)
    private Integer tankNum;
    public Integer getTankNum() {
        return tankNum;
    }

    public void setTankNum(Integer tankNum) {
        this.tankNum = tankNum;
    }

    @Basic
    @Column(name = "IS_TAX", nullable = true)
    private Integer isTax;
    public Integer getIsTax() {
        return isTax;
    }

    public void setIsTax(Integer isTax) {
        this.isTax = isTax;
    }

    @Basic
    @Column(name = "BEGIN_DATE", nullable = false)
    private int beginDate;
    public int getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(int beginDate) {
        this.beginDate = beginDate;
    }

    @Basic
    @Column(name = "BEGIN_TIME", nullable = false)
    private int beginTime;
    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "CONTROL_SYSTEM_TYPE", nullable = false)
    private int controlSystemType;
    public int getControlSystemType() {
        return controlSystemType;
    }

    public void setControlSystemType(int controlSystemType) {
        this.controlSystemType = controlSystemType;
    }

    @Basic
    @Column(name = "IS_REALTIME_DATA", nullable = true)
    private Integer isRealtimeData;
    public Integer getIsRealtimeData() {
        return isRealtimeData;
    }

    public void setIsRealtimeData(Integer isRealtimeData) {
        this.isRealtimeData = isRealtimeData;
    }

    @Basic
    @Column(name = "COLLECT_TYPE", nullable = true)
    private Integer collectType;
    public Integer getCollectType() {
        return collectType;
    }

    public void setCollectType(Integer collectType) {
        this.collectType = collectType;
    }

    @Basic
    @Column(name = "IS_SHIP", nullable = true)
    private Integer isShip;
    public Integer getIsShip() {
        return isShip;
    }

    public void setIsShip(Integer isShip) {
        this.isShip = isShip;
    }

    @Basic
    @Column(name = "SHIP_PORT", nullable = true, length = 64)
    private String shipPort;
    public String getShipPort() {
        return shipPort;
    }

    public void setShipPort(String shipPort) {
        this.shipPort = shipPort;
    }

    @Basic
    @Column(name = "SHIP_JETTY_NUM", nullable = true)
    private Integer shipJettyNum;
    public Integer getShipJettyNum() {
        return shipJettyNum;
    }

    public void setShipJettyNum(Integer shipJettyNum) {
        this.shipJettyNum = shipJettyNum;
    }

    @Basic
    @Column(name = "SHIP_BERTH_NUM", nullable = true)
    private Integer shipBerthNum;
    public Integer getShipBerthNum() {
        return shipBerthNum;
    }

    public void setShipBerthNum(Integer shipBerthNum) {
        this.shipBerthNum = shipBerthNum;
    }

    @Basic
    @Column(name = "SHIP_IS_BARGE", nullable = true)
    private Integer shipIsBarge;
    public Integer getShipIsBarge() {
        return shipIsBarge;
    }

    public void setShipIsBarge(Integer shipIsBarge) {
        this.shipIsBarge = shipIsBarge;
    }

    @Basic
    @Column(name = "IS_CAR", nullable = true)
    private Integer isCar;
    public Integer getIsCar() {
        return isCar;
    }

    public void setIsCar(Integer isCar) {
        this.isCar = isCar;
    }

    @Basic
    @Column(name = "CAR_SITE_NUM", nullable = true)
    private Integer carSiteNum;
    public Integer getCarSiteNum() {
        return carSiteNum;
    }

    public void setCarSiteNum(Integer carSiteNum) {
        this.carSiteNum = carSiteNum;
    }

    @Basic
    @Column(name = "CAR_IS_CARD", nullable = true)
    private Integer carIsCard;
    public Integer getCarIsCard() {
        return carIsCard;
    }

    public void setCarIsCard(Integer carIsCard) {
        this.carIsCard = carIsCard;
    }

    @Basic
    @Column(name = "CAR_IS_AUTO", nullable = true)
    private Integer carIsAuto;
    public Integer getCarIsAuto() {
        return carIsAuto;
    }

    public void setCarIsAuto(Integer carIsAuto) {
        this.carIsAuto = carIsAuto;
    }

    @Basic
    @Column(name = "CAR_IS_INDEPENDENT", nullable = true)
    private Integer carIsIndependent;
    public Integer getCarIsIndependent() {
        return carIsIndependent;
    }

    public void setCarIsIndependent(Integer carIsIndependent) {
        this.carIsIndependent = carIsIndependent;
    }

    @Basic
    @Column(name = "CAR_LOADOMETER_NUM", nullable = true)
    private Integer carLoadometerNum;
    public Integer getCarLoadometerNum() {
        return carLoadometerNum;
    }

    public void setCarLoadometerNum(Integer carLoadometerNum) {
        this.carLoadometerNum = carLoadometerNum;
    }

    @Basic
    @Column(name = "CAR_INOUT_WAY", nullable = true)
    private Integer carInoutWay;
    public Integer getCarInoutWay() {
        return carInoutWay;
    }

    public void setCarInoutWay(Integer carInoutWay) {
        this.carInoutWay = carInoutWay;
    }

    @Basic
    @Column(name = "IS_TRAIN", nullable = true)
    private Integer isTrain;
    public Integer getIsTrain() {
        return isTrain;
    }

    public void setIsTrain(Integer isTrain) {
        this.isTrain = isTrain;
    }

    @Basic
    @Column(name = "TRAIN_LINE_NUM", nullable = true)
    private Integer trainLineNum;
    public Integer getTrainLineNum() {
        return trainLineNum;
    }

    public void setTrainLineNum(Integer trainLineNum) {
        this.trainLineNum = trainLineNum;
    }

    @Basic
    @Column(name = "IS_PIPE", nullable = true)
    private Integer isPipe;
    public Integer getIsPipe() {
        return isPipe;
    }

    public void setIsPipe(Integer isPipe) {
        this.isPipe = isPipe;
    }

    @Basic
    @Column(name = "IS_PAIL", nullable = true)
    private Integer isPail;
    public Integer getIsPail() {
        return isPail;
    }

    public void setIsPail(Integer isPail) {
        this.isPail = isPail;
    }

    @Basic
    @Column(name = "IS_DIRECT", nullable = true)
    private Integer isDirect;
    public Integer getIsDirect() {
        return isDirect;
    }

    public void setIsDirect(Integer isDirect) {
        this.isDirect = isDirect;
    }

    @Basic
    @Column(name = "DIRECT_CAR_SHIP", nullable = true)
    private Integer directCarShip;
    public Integer getDirectCarShip() {
        return directCarShip;
    }

    public void setDirectCarShip(Integer directCarShip) {
        this.directCarShip = directCarShip;
    }

    @Basic
    @Column(name = "DIRECT_TRAIN_SHIP", nullable = true)
    private Integer directTrainShip;
    public Integer getDirectTrainShip() {
        return directTrainShip;
    }

    public void setDirectTrainShip(Integer directTrainShip) {
        this.directTrainShip = directTrainShip;
    }

}
