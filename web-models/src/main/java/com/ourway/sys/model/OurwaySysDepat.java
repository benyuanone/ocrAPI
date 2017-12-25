package com.ourway.sys.model;

import com.ourway.base.model.IncrementIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_depat")
public class OurwaySysDepat extends IncrementIdEntity {

    @Column(name = "FID", nullable = true)
    private Integer fid;
    @Column(name = "PATH", nullable = true, length = 240)
    private String path;
    @Column(name = "DEP_TYPE", nullable = true)
    private Byte depType;
    @Column(name = "DEP_LOGO", nullable = true, length = 240)
    private String depLogo;
    @Column(name = "DEP_NO", nullable = true, length = 24)
    private String depNo;
    @Column(name = "DEP_NAME", nullable = true, length = 240)
    private String depName;
    @Column(name = "DEP_ALAIS", nullable = true, length = 240)
    private String depAlais;
    @Column(name = "DEP_LABEL", nullable = true, length = 240)
    private String depLabel;
    @Column(name = "DEP_ADDR", nullable = true, length = 240)
    private String depAddr;
    @Column(name = "DEP_LXR", nullable = true, length = 16)
    private String depLxr;
    @Column(name = "DEP_PHONE", nullable = true, length = 24)
    private String depPhone;
    @Column(name = "DEP_TEL", nullable = true, length = 24)
    private String depTel;
    @Column(name = "DEP_MAIL", nullable = true, length = 240)
    private String depMail;
    @Column(name = "DEP_DESC", nullable = true, length = 320)
    private String depDesc;
    @Column(name = "DEP_INDEX", nullable = true, length = 240)
    private String depIndex;
    @Column(name = "LANGUAGE", nullable = true, length = 16)
    private String language;
    @Column(name = "PX", nullable = true)
    private Double px;
    @Column(name = "CC", nullable = true)
    private Integer cc;

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


    public Byte getDepType() {
        return depType;
    }

    public void setDepType(Byte depType) {
        this.depType = depType;
    }


    public String getDepLogo() {
        return depLogo;
    }

    public void setDepLogo(String depLogo) {
        this.depLogo = depLogo;
    }

    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }


    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }


    public String getDepAlais() {
        return depAlais;
    }

    public void setDepAlais(String depAlais) {
        this.depAlais = depAlais;
    }


    public String getDepLabel() {
        return depLabel;
    }

    public void setDepLabel(String depLabel) {
        this.depLabel = depLabel;
    }


    public String getDepAddr() {
        return depAddr;
    }

    public void setDepAddr(String depAddr) {
        this.depAddr = depAddr;
    }


    public String getDepLxr() {
        return depLxr;
    }

    public void setDepLxr(String depLxr) {
        this.depLxr = depLxr;
    }


    public String getDepPhone() {
        return depPhone;
    }

    public void setDepPhone(String depPhone) {
        this.depPhone = depPhone;
    }


    public String getDepTel() {
        return depTel;
    }

    public void setDepTel(String depTel) {
        this.depTel = depTel;
    }


    public String getDepMail() {
        return depMail;
    }

    public void setDepMail(String depMail) {
        this.depMail = depMail;
    }


    public String getDepDesc() {
        return depDesc;
    }

    public void setDepDesc(String depDesc) {
        this.depDesc = depDesc;
    }


    public String getDepIndex() {
        return depIndex;
    }

    public void setDepIndex(String depIndex) {
        this.depIndex = depIndex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
