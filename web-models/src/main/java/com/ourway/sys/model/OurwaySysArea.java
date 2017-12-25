package com.ourway.sys.model;

import com.ourway.base.model.IncrementIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_area")
public class OurwaySysArea extends IncrementIdEntity {

    @Column(name = "PATH", nullable = true, length = 240)
    private String path;
    @Column(name = "FID", nullable = true)
    private Integer fid;
    @Column(name = "AREA_CODE", nullable = true, length = 64)
    private String areaCode;
    @Column(name = "AREA_NAME", nullable = true, length = 240)
    private String areaName;
    @Column(name = "AREA_ALAIS", nullable = true, length = 240)
    private String areaAlais;
    @Column(name = "LANGUAGE", nullable = true, length = 16)
    private String language;
    @Column(name = "VISIABLE", nullable = true)
    private Byte visiable;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }


    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    public String getAreaAlais() {
        return areaAlais;
    }

    public void setAreaAlais(String areaAlais) {
        this.areaAlais = areaAlais;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public Byte getVisiable() {
        return visiable;
    }

    public void setVisiable(Byte visiable) {
        this.visiable = visiable;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}
