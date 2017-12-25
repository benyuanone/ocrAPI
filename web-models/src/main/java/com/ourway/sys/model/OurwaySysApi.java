package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_api")
public class OurwaySysApi extends UUidEntity {

    @Column(name = "INT_NO", nullable = true)
    private Integer intNo;
    @Column(name = "INT_NAME", nullable = true, length = 240)
    private String intName;
    @Column(name = "INT_DESC", nullable = true, length = 2400)
    private String intDesc;
    @Column(name = "INT_IMAGE", nullable = true, length = 240)
    private String intImage;
    @Column(name = "INT_VER", nullable = true)
    private Byte intVer;
    @Column(name = "INT_REPLACE", nullable = true)
    private Integer intReplace;
    @Column(name = "INT_URL", nullable = true, length = 1200)
    private String intUrl;
    @Column(name = "INT_FUNC", nullable = true, length = 2400)
    private String intFunc;
    @Column(name = "INT_ALC_FLAG", nullable = true)
    private Byte intAlcFlag;
   public Integer getIntNo() {
        return intNo;
    }

    public void setIntNo(Integer intNo) {
        this.intNo = intNo;
    }

    public String getIntName() {
        return intName;
    }

    public void setIntName(String intName) {
        this.intName = intName;
    }

    public String getIntUrl() {
        return intUrl;
    }

    public void setIntUrl(String intUrl) {
        this.intUrl = intUrl;
    }

    public String getIntDesc() {
        return intDesc;
    }

    public void setIntDesc(String intDesc) {
        this.intDesc = intDesc;
    }


    public String getIntFunc() {
        return intFunc;
    }

    public void setIntFunc(String intFunc) {
        this.intFunc = intFunc;
    }

    public String getIntImage() {
        return intImage;
    }

    public void setIntImage(String intImage) {
        this.intImage = intImage;
    }

    public Byte getIntAlcFlag() {
        return intAlcFlag;
    }

    public void setIntAlcFlag(Byte intAlcFlag) {
        this.intAlcFlag = intAlcFlag;
    }

    public Byte getIntVer() {
        return intVer;
    }

    public void setIntVer(Byte intVer) {
        this.intVer = intVer;
    }

    public Integer getIntReplace() {
        return intReplace;
    }

    public void setIntReplace(Integer intReplace) {
        this.intReplace = intReplace;
    }


}
