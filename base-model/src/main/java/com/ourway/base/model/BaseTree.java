package com.ourway.base.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/9.
 */
public class BaseTree implements Serializable {
    private Integer owid;
    private Integer fid;
    private String path;
    private String name;
    private String icon;
    private Double px;
    private Integer cc;
    private Integer isLeaf;//1是 0 不是
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getPx() {
        return px;
    }

    public void setPx(Double px) {
        this.px = px;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }
}
