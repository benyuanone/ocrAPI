package com.ourway.base.zk.models;

import java.io.Serializable;

/**
 * Created by D.chen.g on 2017/6/16.
 */
public class AreaVO implements Serializable {
    private Integer owid;
    private Integer fid;
    private String path;
    private String areaCode;
    private String areaName;
    private String areaAlais;
    private Byte visiable;
    private String language;
    private String memo;

    public Integer getOwid() {
        return owid;
    }

    public void setOwid(Integer owid) {
        this.owid = owid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Byte getVisiable() {
        return visiable;
    }

    public void setVisiable(Byte visiable) {
        this.visiable = visiable;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
