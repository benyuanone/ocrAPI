package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_bafwht", schema = "public_manage", catalog = "")
public class AppBizBafwht {
    private String owid;
    private String appOwid;
    private String bafwgsmc;
    private String baryzgzsmc;
    private Timestamp barypxjsrq;
    private String htbh;
    private Timestamp qdrq;
    private Timestamp jsrq;
    private String barypxnr;
    private String htyxq;
    private String balxdm;
    private String balxmc;
    private String fzjgmc;
    private String exp1;
    private String exp2;
    private String exp3;
    private String exp4;
    private String exp5;
    private String exp6;
    private String exp7;
    private String exp8;
    private String exp9;
    private String exp10;
    private Timestamp createtime;
    private String creator;
    private String creatorName;
    private Timestamp lastupdate;
    private String updator;
    private String updatorName;
    private Integer ver;
    private Timestamp vertime;
    private Integer deptId;
    private String deptPath;
    private Integer delflg;
    private Integer state;

    @Id
    @Column(name = "OWID", nullable = false, length = 64)
    public String getOwid() {
        return owid;
    }

    public void setOwid(String owid) {
        this.owid = owid;
    }

    @Basic
    @Column(name = "APP_OWID", nullable = true, length = 64)
    public String getAppOwid() {
        return appOwid;
    }

    public void setAppOwid(String appOwid) {
        this.appOwid = appOwid;
    }

    @Basic
    @Column(name = "BAFWGSMC", nullable = true, length = 200)
    public String getBafwgsmc() {
        return bafwgsmc;
    }

    public void setBafwgsmc(String bafwgsmc) {
        this.bafwgsmc = bafwgsmc;
    }

    @Basic
    @Column(name = "BARYZGZSMC", nullable = true, length = 100)
    public String getBaryzgzsmc() {
        return baryzgzsmc;
    }

    public void setBaryzgzsmc(String baryzgzsmc) {
        this.baryzgzsmc = baryzgzsmc;
    }

    @Basic
    @Column(name = "BARYPXJSRQ", nullable = true)
    public Timestamp getBarypxjsrq() {
        return barypxjsrq;
    }

    public void setBarypxjsrq(Timestamp barypxjsrq) {
        this.barypxjsrq = barypxjsrq;
    }

    @Basic
    @Column(name = "HTBH", nullable = true, length = 30)
    public String getHtbh() {
        return htbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    @Basic
    @Column(name = "QDRQ", nullable = true)
    public Timestamp getQdrq() {
        return qdrq;
    }

    public void setQdrq(Timestamp qdrq) {
        this.qdrq = qdrq;
    }

    @Basic
    @Column(name = "JSRQ", nullable = true)
    public Timestamp getJsrq() {
        return jsrq;
    }

    public void setJsrq(Timestamp jsrq) {
        this.jsrq = jsrq;
    }

    @Basic
    @Column(name = "BARYPXNR", nullable = true, length = 50)
    public String getBarypxnr() {
        return barypxnr;
    }

    public void setBarypxnr(String barypxnr) {
        this.barypxnr = barypxnr;
    }

    @Basic
    @Column(name = "HTYXQ", nullable = true, length = 500)
    public String getHtyxq() {
        return htyxq;
    }

    public void setHtyxq(String htyxq) {
        this.htyxq = htyxq;
    }

    @Basic
    @Column(name = "BALXDM", nullable = true, length = 32)
    public String getBalxdm() {
        return balxdm;
    }

    public void setBalxdm(String balxdm) {
        this.balxdm = balxdm;
    }

    @Basic
    @Column(name = "BALXMC", nullable = true, length = 64)
    public String getBalxmc() {
        return balxmc;
    }

    public void setBalxmc(String balxmc) {
        this.balxmc = balxmc;
    }

    @Basic
    @Column(name = "FZJGMC", nullable = true, length = 64)
    public String getFzjgmc() {
        return fzjgmc;
    }

    public void setFzjgmc(String fzjgmc) {
        this.fzjgmc = fzjgmc;
    }

    @Basic
    @Column(name = "EXP1", nullable = true, length = 100)
    public String getExp1() {
        return exp1;
    }

    public void setExp1(String exp1) {
        this.exp1 = exp1;
    }

    @Basic
    @Column(name = "EXP2", nullable = true, length = 100)
    public String getExp2() {
        return exp2;
    }

    public void setExp2(String exp2) {
        this.exp2 = exp2;
    }

    @Basic
    @Column(name = "EXP3", nullable = true, length = 100)
    public String getExp3() {
        return exp3;
    }

    public void setExp3(String exp3) {
        this.exp3 = exp3;
    }

    @Basic
    @Column(name = "EXP4", nullable = true, length = 100)
    public String getExp4() {
        return exp4;
    }

    public void setExp4(String exp4) {
        this.exp4 = exp4;
    }

    @Basic
    @Column(name = "EXP5", nullable = true, length = 100)
    public String getExp5() {
        return exp5;
    }

    public void setExp5(String exp5) {
        this.exp5 = exp5;
    }

    @Basic
    @Column(name = "EXP6", nullable = true, length = 100)
    public String getExp6() {
        return exp6;
    }

    public void setExp6(String exp6) {
        this.exp6 = exp6;
    }

    @Basic
    @Column(name = "EXP7", nullable = true, length = 100)
    public String getExp7() {
        return exp7;
    }

    public void setExp7(String exp7) {
        this.exp7 = exp7;
    }

    @Basic
    @Column(name = "EXP8", nullable = true, length = 100)
    public String getExp8() {
        return exp8;
    }

    public void setExp8(String exp8) {
        this.exp8 = exp8;
    }

    @Basic
    @Column(name = "EXP9", nullable = true, length = 100)
    public String getExp9() {
        return exp9;
    }

    public void setExp9(String exp9) {
        this.exp9 = exp9;
    }

    @Basic
    @Column(name = "EXP10", nullable = true, length = 100)
    public String getExp10() {
        return exp10;
    }

    public void setExp10(String exp10) {
        this.exp10 = exp10;
    }

    @Basic
    @Column(name = "CREATETIME", nullable = true)
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "CREATOR", nullable = true, length = 64)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "CREATOR_NAME", nullable = true, length = 16)
    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Basic
    @Column(name = "LASTUPDATE", nullable = true)
    public Timestamp getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Timestamp lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Basic
    @Column(name = "UPDATOR", nullable = true, length = 64)
    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    @Basic
    @Column(name = "UPDATOR_NAME", nullable = true, length = 16)
    public String getUpdatorName() {
        return updatorName;
    }

    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }

    @Basic
    @Column(name = "VER", nullable = true)
    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    @Basic
    @Column(name = "VERTIME", nullable = true)
    public Timestamp getVertime() {
        return vertime;
    }

    public void setVertime(Timestamp vertime) {
        this.vertime = vertime;
    }

    @Basic
    @Column(name = "DEPT_ID", nullable = true)
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "DEPT_PATH", nullable = true, length = 240)
    public String getDeptPath() {
        return deptPath;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    @Basic
    @Column(name = "DELFLG", nullable = true)
    public Integer getDelflg() {
        return delflg;
    }

    public void setDelflg(Integer delflg) {
        this.delflg = delflg;
    }

    @Basic
    @Column(name = "STATE", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppBizBafwht that = (AppBizBafwht) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (bafwgsmc != null ? !bafwgsmc.equals(that.bafwgsmc) : that.bafwgsmc != null) return false;
        if (baryzgzsmc != null ? !baryzgzsmc.equals(that.baryzgzsmc) : that.baryzgzsmc != null) return false;
        if (barypxjsrq != null ? !barypxjsrq.equals(that.barypxjsrq) : that.barypxjsrq != null) return false;
        if (htbh != null ? !htbh.equals(that.htbh) : that.htbh != null) return false;
        if (qdrq != null ? !qdrq.equals(that.qdrq) : that.qdrq != null) return false;
        if (jsrq != null ? !jsrq.equals(that.jsrq) : that.jsrq != null) return false;
        if (barypxnr != null ? !barypxnr.equals(that.barypxnr) : that.barypxnr != null) return false;
        if (htyxq != null ? !htyxq.equals(that.htyxq) : that.htyxq != null) return false;
        if (balxdm != null ? !balxdm.equals(that.balxdm) : that.balxdm != null) return false;
        if (balxmc != null ? !balxmc.equals(that.balxmc) : that.balxmc != null) return false;
        if (fzjgmc != null ? !fzjgmc.equals(that.fzjgmc) : that.fzjgmc != null) return false;
        if (exp1 != null ? !exp1.equals(that.exp1) : that.exp1 != null) return false;
        if (exp2 != null ? !exp2.equals(that.exp2) : that.exp2 != null) return false;
        if (exp3 != null ? !exp3.equals(that.exp3) : that.exp3 != null) return false;
        if (exp4 != null ? !exp4.equals(that.exp4) : that.exp4 != null) return false;
        if (exp5 != null ? !exp5.equals(that.exp5) : that.exp5 != null) return false;
        if (exp6 != null ? !exp6.equals(that.exp6) : that.exp6 != null) return false;
        if (exp7 != null ? !exp7.equals(that.exp7) : that.exp7 != null) return false;
        if (exp8 != null ? !exp8.equals(that.exp8) : that.exp8 != null) return false;
        if (exp9 != null ? !exp9.equals(that.exp9) : that.exp9 != null) return false;
        if (exp10 != null ? !exp10.equals(that.exp10) : that.exp10 != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (creatorName != null ? !creatorName.equals(that.creatorName) : that.creatorName != null) return false;
        if (lastupdate != null ? !lastupdate.equals(that.lastupdate) : that.lastupdate != null) return false;
        if (updator != null ? !updator.equals(that.updator) : that.updator != null) return false;
        if (updatorName != null ? !updatorName.equals(that.updatorName) : that.updatorName != null) return false;
        if (ver != null ? !ver.equals(that.ver) : that.ver != null) return false;
        if (vertime != null ? !vertime.equals(that.vertime) : that.vertime != null) return false;
        if (deptId != null ? !deptId.equals(that.deptId) : that.deptId != null) return false;
        if (deptPath != null ? !deptPath.equals(that.deptPath) : that.deptPath != null) return false;
        if (delflg != null ? !delflg.equals(that.delflg) : that.delflg != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = owid != null ? owid.hashCode() : 0;
        result = 31 * result + (appOwid != null ? appOwid.hashCode() : 0);
        result = 31 * result + (bafwgsmc != null ? bafwgsmc.hashCode() : 0);
        result = 31 * result + (baryzgzsmc != null ? baryzgzsmc.hashCode() : 0);
        result = 31 * result + (barypxjsrq != null ? barypxjsrq.hashCode() : 0);
        result = 31 * result + (htbh != null ? htbh.hashCode() : 0);
        result = 31 * result + (qdrq != null ? qdrq.hashCode() : 0);
        result = 31 * result + (jsrq != null ? jsrq.hashCode() : 0);
        result = 31 * result + (barypxnr != null ? barypxnr.hashCode() : 0);
        result = 31 * result + (htyxq != null ? htyxq.hashCode() : 0);
        result = 31 * result + (balxdm != null ? balxdm.hashCode() : 0);
        result = 31 * result + (balxmc != null ? balxmc.hashCode() : 0);
        result = 31 * result + (fzjgmc != null ? fzjgmc.hashCode() : 0);
        result = 31 * result + (exp1 != null ? exp1.hashCode() : 0);
        result = 31 * result + (exp2 != null ? exp2.hashCode() : 0);
        result = 31 * result + (exp3 != null ? exp3.hashCode() : 0);
        result = 31 * result + (exp4 != null ? exp4.hashCode() : 0);
        result = 31 * result + (exp5 != null ? exp5.hashCode() : 0);
        result = 31 * result + (exp6 != null ? exp6.hashCode() : 0);
        result = 31 * result + (exp7 != null ? exp7.hashCode() : 0);
        result = 31 * result + (exp8 != null ? exp8.hashCode() : 0);
        result = 31 * result + (exp9 != null ? exp9.hashCode() : 0);
        result = 31 * result + (exp10 != null ? exp10.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (lastupdate != null ? lastupdate.hashCode() : 0);
        result = 31 * result + (updator != null ? updator.hashCode() : 0);
        result = 31 * result + (updatorName != null ? updatorName.hashCode() : 0);
        result = 31 * result + (ver != null ? ver.hashCode() : 0);
        result = 31 * result + (vertime != null ? vertime.hashCode() : 0);
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (deptPath != null ? deptPath.hashCode() : 0);
        result = 31 * result + (delflg != null ? delflg.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
