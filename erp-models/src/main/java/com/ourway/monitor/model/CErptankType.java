package com.ourway.monitor.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by cancer on 2017-10-23.
 */
@Entity
@Table(name = "c_erptank_type", schema = "owboss", catalog = "")
public class CErptankType extends UUidEntity {
    @Basic
    @Column(name = "TANK_TYPE", nullable = true, length = 64)
    private String tankType;
    @Basic
    @Column(name = "TANK_TYPE_ID", nullable = true, length = 64)
    private String tankTypeId;
    @Basic
    @Column(name = "TANK_TYPE_NAME", nullable = true, length = 64)
    private String tankTypeName;
    @Basic
    @Column(name = "DELFLG", nullable = true)
    private Integer delflg;

    public String getTankType() {
        return tankType;
    }

    public void setTankType(String tankType) {
        this.tankType = tankType;
    }


    public String getTankTypeId() {
        return tankTypeId;
    }

    public void setTankTypeId(String tankTypeId) {
        this.tankTypeId = tankTypeId;
    }


    public String getTankTypeName() {
        return tankTypeName;
    }

    public void setTankTypeName(String tankTypeName) {
        this.tankTypeName = tankTypeName;
    }


    public Integer getDelflg() {
        return delflg;
    }

    public void setDelflg(Integer delflg) {
        this.delflg = delflg;
    }

}
