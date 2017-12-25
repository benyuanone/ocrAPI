package com.ourway.base.zk.models;

/**
 * Created by CC on 2017/6/7.
 */
public class DepartVo {
    private Integer owid;

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

    private Integer fid;
    private String path;
    private Byte depType;
    private String depLogo;
    private String depNo;
    private String depName;
    private String depAlais;
    private String depLabel;
    private String depAddr;
    private String depLxr;
    private String depPhone;
    private String depTel;
    private String depMail;
    private String depDesc;
    private String depIndex;
    private String language;

    /*树的名称*/
    private String name;
    /*树的链接*/

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    private String link;
    /*树节点的icon图标*/
    private String icon;
}
