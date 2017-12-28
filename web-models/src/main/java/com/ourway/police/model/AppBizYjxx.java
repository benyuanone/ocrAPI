package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_yjxx", schema = "public_manage", catalog = "")
public class AppBizYjxx {
    private String owid;
    private String yjlx;
    private String yjnr;
    private String yjxgbm;
    private String yjxgbid;
    private String yjfsmc;
    private String yjfsdm;
    private String yjjbmc;
    private String yjjbdm;
    private String yjgz;
    private String bdtj;
    private String gxdwmc;
    private String gxdwdm;
    private String qymc;
    private String qydz;
    private Byte yjzt;
    private String zy;
    private String gjz;
    private String bt;
    private String tp;
    private String bz;
    private Timestamp ztxgsj;
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
    @Column(name = "YJLX", nullable = true, length = 20)
    public String getYjlx() {
        return yjlx;
    }

    public void setYjlx(String yjlx) {
        this.yjlx = yjlx;
    }

    @Basic
    @Column(name = "YJNR", nullable = true, length = 500)
    public String getYjnr() {
        return yjnr;
    }

    public void setYjnr(String yjnr) {
        this.yjnr = yjnr;
    }

    @Basic
    @Column(name = "YJXGBM", nullable = true, length = 30)
    public String getYjxgbm() {
        return yjxgbm;
    }

    public void setYjxgbm(String yjxgbm) {
        this.yjxgbm = yjxgbm;
    }

    @Basic
    @Column(name = "YJXGBID", nullable = true, length = 64)
    public String getYjxgbid() {
        return yjxgbid;
    }

    public void setYjxgbid(String yjxgbid) {
        this.yjxgbid = yjxgbid;
    }

    @Basic
    @Column(name = "YJFSMC", nullable = true, length = 200)
    public String getYjfsmc() {
        return yjfsmc;
    }

    public void setYjfsmc(String yjfsmc) {
        this.yjfsmc = yjfsmc;
    }

    @Basic
    @Column(name = "YJFSDM", nullable = true, length = 64)
    public String getYjfsdm() {
        return yjfsdm;
    }

    public void setYjfsdm(String yjfsdm) {
        this.yjfsdm = yjfsdm;
    }

    @Basic
    @Column(name = "YJJBMC", nullable = true, length = 200)
    public String getYjjbmc() {
        return yjjbmc;
    }

    public void setYjjbmc(String yjjbmc) {
        this.yjjbmc = yjjbmc;
    }

    @Basic
    @Column(name = "YJJBDM", nullable = true, length = 64)
    public String getYjjbdm() {
        return yjjbdm;
    }

    public void setYjjbdm(String yjjbdm) {
        this.yjjbdm = yjjbdm;
    }

    @Basic
    @Column(name = "YJGZ", nullable = true, length = 200)
    public String getYjgz() {
        return yjgz;
    }

    public void setYjgz(String yjgz) {
        this.yjgz = yjgz;
    }

    @Basic
    @Column(name = "BDTJ", nullable = true, length = 200)
    public String getBdtj() {
        return bdtj;
    }

    public void setBdtj(String bdtj) {
        this.bdtj = bdtj;
    }

    @Basic
    @Column(name = "GXDWMC", nullable = true, length = 200)
    public String getGxdwmc() {
        return gxdwmc;
    }

    public void setGxdwmc(String gxdwmc) {
        this.gxdwmc = gxdwmc;
    }

    @Basic
    @Column(name = "GXDWDM", nullable = true, length = 64)
    public String getGxdwdm() {
        return gxdwdm;
    }

    public void setGxdwdm(String gxdwdm) {
        this.gxdwdm = gxdwdm;
    }

    @Basic
    @Column(name = "QYMC", nullable = true, length = 50)
    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    @Basic
    @Column(name = "QYDZ", nullable = true, length = 200)
    public String getQydz() {
        return qydz;
    }

    public void setQydz(String qydz) {
        this.qydz = qydz;
    }

    @Basic
    @Column(name = "YJZT", nullable = true)
    public Byte getYjzt() {
        return yjzt;
    }

    public void setYjzt(Byte yjzt) {
        this.yjzt = yjzt;
    }

    @Basic
    @Column(name = "ZY", nullable = true, length = 500)
    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    @Basic
    @Column(name = "GJZ", nullable = true, length = 20)
    public String getGjz() {
        return gjz;
    }

    public void setGjz(String gjz) {
        this.gjz = gjz;
    }

    @Basic
    @Column(name = "BT", nullable = true, length = 200)
    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    @Basic
    @Column(name = "TP", nullable = true, length = 64)
    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    @Basic
    @Column(name = "BZ", nullable = true, length = 1000)
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Basic
    @Column(name = "ZTXGSJ", nullable = true)
    public Timestamp getZtxgsj() {
        return ztxgsj;
    }

    public void setZtxgsj(Timestamp ztxgsj) {
        this.ztxgsj = ztxgsj;
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

        AppBizYjxx that = (AppBizYjxx) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (yjlx != null ? !yjlx.equals(that.yjlx) : that.yjlx != null) return false;
        if (yjnr != null ? !yjnr.equals(that.yjnr) : that.yjnr != null) return false;
        if (yjxgbm != null ? !yjxgbm.equals(that.yjxgbm) : that.yjxgbm != null) return false;
        if (yjxgbid != null ? !yjxgbid.equals(that.yjxgbid) : that.yjxgbid != null) return false;
        if (yjfsmc != null ? !yjfsmc.equals(that.yjfsmc) : that.yjfsmc != null) return false;
        if (yjfsdm != null ? !yjfsdm.equals(that.yjfsdm) : that.yjfsdm != null) return false;
        if (yjjbmc != null ? !yjjbmc.equals(that.yjjbmc) : that.yjjbmc != null) return false;
        if (yjjbdm != null ? !yjjbdm.equals(that.yjjbdm) : that.yjjbdm != null) return false;
        if (yjgz != null ? !yjgz.equals(that.yjgz) : that.yjgz != null) return false;
        if (bdtj != null ? !bdtj.equals(that.bdtj) : that.bdtj != null) return false;
        if (gxdwmc != null ? !gxdwmc.equals(that.gxdwmc) : that.gxdwmc != null) return false;
        if (gxdwdm != null ? !gxdwdm.equals(that.gxdwdm) : that.gxdwdm != null) return false;
        if (qymc != null ? !qymc.equals(that.qymc) : that.qymc != null) return false;
        if (qydz != null ? !qydz.equals(that.qydz) : that.qydz != null) return false;
        if (yjzt != null ? !yjzt.equals(that.yjzt) : that.yjzt != null) return false;
        if (zy != null ? !zy.equals(that.zy) : that.zy != null) return false;
        if (gjz != null ? !gjz.equals(that.gjz) : that.gjz != null) return false;
        if (bt != null ? !bt.equals(that.bt) : that.bt != null) return false;
        if (tp != null ? !tp.equals(that.tp) : that.tp != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (ztxgsj != null ? !ztxgsj.equals(that.ztxgsj) : that.ztxgsj != null) return false;
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
        result = 31 * result + (yjlx != null ? yjlx.hashCode() : 0);
        result = 31 * result + (yjnr != null ? yjnr.hashCode() : 0);
        result = 31 * result + (yjxgbm != null ? yjxgbm.hashCode() : 0);
        result = 31 * result + (yjxgbid != null ? yjxgbid.hashCode() : 0);
        result = 31 * result + (yjfsmc != null ? yjfsmc.hashCode() : 0);
        result = 31 * result + (yjfsdm != null ? yjfsdm.hashCode() : 0);
        result = 31 * result + (yjjbmc != null ? yjjbmc.hashCode() : 0);
        result = 31 * result + (yjjbdm != null ? yjjbdm.hashCode() : 0);
        result = 31 * result + (yjgz != null ? yjgz.hashCode() : 0);
        result = 31 * result + (bdtj != null ? bdtj.hashCode() : 0);
        result = 31 * result + (gxdwmc != null ? gxdwmc.hashCode() : 0);
        result = 31 * result + (gxdwdm != null ? gxdwdm.hashCode() : 0);
        result = 31 * result + (qymc != null ? qymc.hashCode() : 0);
        result = 31 * result + (qydz != null ? qydz.hashCode() : 0);
        result = 31 * result + (yjzt != null ? yjzt.hashCode() : 0);
        result = 31 * result + (zy != null ? zy.hashCode() : 0);
        result = 31 * result + (gjz != null ? gjz.hashCode() : 0);
        result = 31 * result + (bt != null ? bt.hashCode() : 0);
        result = 31 * result + (tp != null ? tp.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (ztxgsj != null ? ztxgsj.hashCode() : 0);
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
