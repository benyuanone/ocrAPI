package com.ourway.monitor.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cancer on 2017-10-23.
 */
@Entity
@Table(name = "c_tank_monitor_his", schema = "owboss", catalog = "")
public class CTankMonitorHis extends UUidEntity {
    @Basic
    @Column(name = "TANK_ID", nullable = true, length = 64)
    private String tankId;
    @Temporal(TemporalType.DATE)
    @Column(name = "SYNCHRO_TIME", nullable = true)
    private Date synchroTime;
    @Basic
    @Column(name = "LEVEL", nullable = true, precision = 6)
    private BigDecimal level;
    @Basic
    @Column(name = "TEMPERATURE", nullable = true, precision = 6)
    private BigDecimal temperature;
    @Basic
    @Column(name = "REAL_VOLUME", nullable = true, precision = 6)
    private BigDecimal realVolume;
    @Basic
    @Column(name = "WEIGHT", nullable = true, precision = 6)
    private BigDecimal weight;
    @Basic
    @Column(name = "FLOW_SPEED", nullable = true, precision = 6)
    private BigDecimal flowSpeed;
    @Temporal(TemporalType.DATE)
    @Column(name = "BEGIN_TIME", nullable = true)
    private Date beginTime;
    @Temporal(TemporalType.DATE)
    @Column(name = "END_TIME", nullable = true)
    private Date endTime;
    @Basic
    @Column(name = "DELFLG", nullable = true)
    private Integer delflg;


    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
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


    public BigDecimal getRealVolume() {
        return realVolume;
    }

    public void setRealVolume(BigDecimal realVolume) {
        this.realVolume = realVolume;
    }


    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }


    public BigDecimal getFlowSpeed() {
        return flowSpeed;
    }

    public void setFlowSpeed(BigDecimal flowSpeed) {
        this.flowSpeed = flowSpeed;
    }



    public Integer getDelflg() {
        return delflg;
    }

    public void setDelflg(Integer delflg) {
        this.delflg = delflg;
    }

    public void setSynchroTime(Date synchroTime) {
        this.synchroTime = synchroTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getSynchroTime() {
        return synchroTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
