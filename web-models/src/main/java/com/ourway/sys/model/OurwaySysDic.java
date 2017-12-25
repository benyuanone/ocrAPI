package com.ourway.sys.model;

import com.ourway.base.model.IncrementIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_dic")
public class OurwaySysDic extends IncrementIdEntity {

    @Column(name = "FID", nullable = true)
    private Integer fid;
    @Column(name = "PATH", nullable = true, length = 240)
    private String path;
    @Column(name = "TYPE", nullable = true)
    private Integer type;
    @Column(name = "CODE", nullable = true, length = 16)
    private String code;
    @Column(name = "NAME", nullable = true, length = 64)
    private String name;
    @Column(name = "DESCPT", nullable = true, length = 2000)
    private String descpt;
    @Column(name = "VISIABLE", nullable = true)
    private Byte visiable;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;
    @Column(name = "PX")
    private Integer px;
    @Transient
    private List<OurwaySysDicValue> dicValueList;
    @Transient


    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
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


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescpt() {
        return descpt;
    }

    public void setDescpt(String descpt) {
        this.descpt = descpt;
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

    public List<OurwaySysDicValue> getDicValueList() {
        return dicValueList;
    }

    public void setDicValueList(List<OurwaySysDicValue> dicValueList) {
        this.dicValueList = dicValueList;
    }

}
