package com.ourway.monitor.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by cancer on 2017-10-23.
 */
@Entity
@Table(name = "c_tank_monitor", schema = "owboss", catalog = "")
public class CTankMonitor extends UUidEntity {
    @Basic
    @Column(name = "TANK_ID", nullable = true, length = 64)
    private String tankId;
    @Basic
    @Column(name = "TANK_TYPE_ID", nullable = true, length = 64)
    private String tankTypeId;
    @Basic
    @Column(name = "TANK_TYPE", nullable = true, length = 64)
    private String tankType;
    @Basic
    @Column(name = "IS_SPHERE", nullable = true, length = 64)
    private String isSphere;
    @Basic
    @Column(name = "MAX_HEIGHT", nullable = true, precision = 6)
    private BigDecimal maxHeight;
    @Basic
    @Column(name = "SAFE_HEIGHT", nullable = true, precision = 6)
    private BigDecimal safeHeight;
    @Basic
    @Column(name = "LOW_HIGHLY", nullable = true, precision = 6)
    private BigDecimal lowHighly;
    @Basic
    @Column(name = "GOODS_NAME", nullable = true, length = 64)
    private String goodsName;
    @Basic
    @Column(name = "SYNCHRO_TIME", nullable = true)
    private Timestamp synchroTime;
    @Basic
    @Column(name = "LEVEL", nullable = true, precision = 6)
    private BigDecimal level;
    @Basic
    @Column(name = "TEMPERATURE", nullable = true, precision = 6)
    private BigDecimal temperature;
    @Basic
    @Column(name = "PRESSURE", nullable = true, precision = 6)
    private BigDecimal pressure;
    @Basic
    @Column(name = "DENSITY", nullable = true, precision = 6)
    private BigDecimal density;
    @Basic
    @Column(name = "REAL_VOLUME", nullable = true, precision = 6)
    private BigDecimal realVolume;
    @Basic
    @Column(name = "THEORETICAL_WEIGHT", nullable = true, precision = 6)
    private BigDecimal theoreticalWeight;
    @Basic
    @Column(name = "REAL_WEIGHT", nullable = true, precision = 6)
    private BigDecimal realWeight;
    @Basic
    @Column(name = "ABNORMAL_LEVEL", nullable = true, length = 64)
    private String abnormalLevel;
    @Basic
    @Column(name = "ABNORMALVOLUME", nullable = true, length = 64)
    private String abnormalvolume;
    @Basic
    @Column(name = "TANK_STATE", nullable = true, length = 64)
    private String tankState;
    @Basic
    @Column(name = "DIFF_WEIGHT", nullable = true, precision = 6)
    private BigDecimal diffWeight;
    @Basic
    @Column(name = "VOLUME_PERCENT", nullable = true)
    private Integer volumePercent;
    @Basic
    @Column(name = "ETC", nullable = true, precision = 6)
    private BigDecimal etc;
    @Basic
    @Column(name = "FLOW_SPEED", nullable = true, precision = 6)
    private BigDecimal flowSpeed;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;
    @Basic
    @Column(name = "DELFLG", nullable = true)
    private Integer delflg;

    @Transient
    private CErptankType cErptankType;

    public CErptankType getcErptankType() {
        return cErptankType;
    }

    public void setcErptankType(CErptankType cErptankType) {
        this.cErptankType = cErptankType;
    }


    public BigDecimal getTheoreticalWeight() {
        return theoreticalWeight;
    }

    public void setTheoreticalWeight(BigDecimal theoreticalWeight) {
        this.theoreticalWeight = theoreticalWeight;
    }

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }



    public BigDecimal getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(BigDecimal maxHeight) {
        this.maxHeight = maxHeight;
    }


    public BigDecimal getSafeHeight() {
        return safeHeight;
    }

    public void setSafeHeight(BigDecimal safeHeight) {
        this.safeHeight = safeHeight;
    }


    public BigDecimal getLowHighly() {
        return lowHighly;
    }

    public void setLowHighly(BigDecimal lowHighly) {
        this.lowHighly = lowHighly;
    }


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    public Timestamp getSynchroTime() {
        return synchroTime;
    }

    public void setSynchroTime(Timestamp synchroTime) {
        this.synchroTime = synchroTime;
    }


    public BigDecimal getLevel() {
        return level;
    }

    public void setLevel(BigDecimal level) {
        this.level = level;
    }


    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }


    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }


    public BigDecimal getDensity() {
        return density;
    }

    public void setDensity(BigDecimal density) {
        this.density = density;
    }


    public BigDecimal getRealVolume() {
        return realVolume;
    }

    public void setRealVolume(BigDecimal realVolume) {
        this.realVolume = realVolume;
    }


    public BigDecimal getRealWeight() {
        return realWeight;
    }

    public void setRealWeight(BigDecimal realWeight) {
        this.realWeight = realWeight;
    }


    public String getTankState() {
        return tankState;
    }

    public void setTankState(String tankState) {
        this.tankState = tankState;
    }


    public BigDecimal getDiffWeight() {
        return diffWeight;
    }

    public void setDiffWeight(BigDecimal diffWeight) {
        this.diffWeight = diffWeight;
    }


    public Integer getVolumePercent() {
        return volumePercent;
    }

    public void setVolumePercent(Integer volumePercent) {
        this.volumePercent = volumePercent;
    }


    public BigDecimal getEtc() {
        return etc;
    }

    public void setEtc(BigDecimal etc) {
        this.etc = etc;
    }


    public BigDecimal getFlowSpeed() {
        return flowSpeed;
    }

    public void setFlowSpeed(BigDecimal flowSpeed) {
        this.flowSpeed = flowSpeed;
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

    public String getIsSphere() {
        return isSphere;
    }

    public String getAbnormalLevel() {
        return abnormalLevel;
    }

    public String getAbnormalvolume() {
        return abnormalvolume;
    }

    public void setIsSphere(String isSphere) {
        this.isSphere = isSphere;
    }

    public void setAbnormalLevel(String abnormalLevel) {
        this.abnormalLevel = abnormalLevel;
    }

    public void setAbnormalvolume(String abnormalvolume) {
        this.abnormalvolume = abnormalvolume;
    }

    public String getTankTypeId() {
        return tankTypeId;
    }

    public String getTankType() {
        return tankType;
    }

    public void setTankTypeId(String tankTypeId) {
        this.tankTypeId = tankTypeId;
    }

    public void setTankType(String tankType) {
        this.tankType = tankType;
    }
}
