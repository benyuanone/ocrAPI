package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_bjgz", schema = "public_manage", catalog = "")
public class AppBizBjgz {
    private String owid;
    private String ywbm;
    private String ywbzdm;
    private String bdbm;
    private String bdbzdm;
    private String bdlxdm;
    private String bdlx;
    private Byte sfyx;
    private String fbcl;
    private String fbfs;
    private String txdwzd;
    private String gxdwzd;
    private String bjjb;
    private String bjfs;
    private String bjlxdm;
    private String bjlx;
    private String hylbdm;
    private String hylb;
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
    @Column(name = "YWBM", nullable = true, length = 100)
    public String getYwbm() {
        return ywbm;
    }

    public void setYwbm(String ywbm) {
        this.ywbm = ywbm;
    }

    @Basic
    @Column(name = "YWBZDM", nullable = true, length = 60)
    public String getYwbzdm() {
        return ywbzdm;
    }

    public void setYwbzdm(String ywbzdm) {
        this.ywbzdm = ywbzdm;
    }

    @Basic
    @Column(name = "BDBM", nullable = true, length = 100)
    public String getBdbm() {
        return bdbm;
    }

    public void setBdbm(String bdbm) {
        this.bdbm = bdbm;
    }

    @Basic
    @Column(name = "BDBZDM", nullable = true, length = 60)
    public String getBdbzdm() {
        return bdbzdm;
    }

    public void setBdbzdm(String bdbzdm) {
        this.bdbzdm = bdbzdm;
    }

    @Basic
    @Column(name = "BDLXDM", nullable = true, length = 64)
    public String getBdlxdm() {
        return bdlxdm;
    }

    public void setBdlxdm(String bdlxdm) {
        this.bdlxdm = bdlxdm;
    }

    @Basic
    @Column(name = "BDLX", nullable = true, length = 4)
    public String getBdlx() {
        return bdlx;
    }

    public void setBdlx(String bdlx) {
        this.bdlx = bdlx;
    }

    @Basic
    @Column(name = "SFYX", nullable = true)
    public Byte getSfyx() {
        return sfyx;
    }

    public void setSfyx(Byte sfyx) {
        this.sfyx = sfyx;
    }

    @Basic
    @Column(name = "FBCL", nullable = true, length = 300)
    public String getFbcl() {
        return fbcl;
    }

    public void setFbcl(String fbcl) {
        this.fbcl = fbcl;
    }

    @Basic
    @Column(name = "FBFS", nullable = true, length = 2)
    public String getFbfs() {
        return fbfs;
    }

    public void setFbfs(String fbfs) {
        this.fbfs = fbfs;
    }

    @Basic
    @Column(name = "TXDWZD", nullable = true, length = 60)
    public String getTxdwzd() {
        return txdwzd;
    }

    public void setTxdwzd(String txdwzd) {
        this.txdwzd = txdwzd;
    }

    @Basic
    @Column(name = "GXDWZD", nullable = true, length = 60)
    public String getGxdwzd() {
        return gxdwzd;
    }

    public void setGxdwzd(String gxdwzd) {
        this.gxdwzd = gxdwzd;
    }

    @Basic
    @Column(name = "BJJB", nullable = true, length = 2)
    public String getBjjb() {
        return bjjb;
    }

    public void setBjjb(String bjjb) {
        this.bjjb = bjjb;
    }

    @Basic
    @Column(name = "BJFS", nullable = true, length = 60)
    public String getBjfs() {
        return bjfs;
    }

    public void setBjfs(String bjfs) {
        this.bjfs = bjfs;
    }

    @Basic
    @Column(name = "BJLXDM", nullable = true, length = 64)
    public String getBjlxdm() {
        return bjlxdm;
    }

    public void setBjlxdm(String bjlxdm) {
        this.bjlxdm = bjlxdm;
    }

    @Basic
    @Column(name = "BJLX", nullable = true, length = 60)
    public String getBjlx() {
        return bjlx;
    }

    public void setBjlx(String bjlx) {
        this.bjlx = bjlx;
    }

    @Basic
    @Column(name = "HYLBDM", nullable = true, length = 64)
    public String getHylbdm() {
        return hylbdm;
    }

    public void setHylbdm(String hylbdm) {
        this.hylbdm = hylbdm;
    }

    @Basic
    @Column(name = "HYLB", nullable = true, length = 100)
    public String getHylb() {
        return hylb;
    }

    public void setHylb(String hylb) {
        this.hylb = hylb;
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

        AppBizBjgz that = (AppBizBjgz) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (ywbm != null ? !ywbm.equals(that.ywbm) : that.ywbm != null) return false;
        if (ywbzdm != null ? !ywbzdm.equals(that.ywbzdm) : that.ywbzdm != null) return false;
        if (bdbm != null ? !bdbm.equals(that.bdbm) : that.bdbm != null) return false;
        if (bdbzdm != null ? !bdbzdm.equals(that.bdbzdm) : that.bdbzdm != null) return false;
        if (bdlxdm != null ? !bdlxdm.equals(that.bdlxdm) : that.bdlxdm != null) return false;
        if (bdlx != null ? !bdlx.equals(that.bdlx) : that.bdlx != null) return false;
        if (sfyx != null ? !sfyx.equals(that.sfyx) : that.sfyx != null) return false;
        if (fbcl != null ? !fbcl.equals(that.fbcl) : that.fbcl != null) return false;
        if (fbfs != null ? !fbfs.equals(that.fbfs) : that.fbfs != null) return false;
        if (txdwzd != null ? !txdwzd.equals(that.txdwzd) : that.txdwzd != null) return false;
        if (gxdwzd != null ? !gxdwzd.equals(that.gxdwzd) : that.gxdwzd != null) return false;
        if (bjjb != null ? !bjjb.equals(that.bjjb) : that.bjjb != null) return false;
        if (bjfs != null ? !bjfs.equals(that.bjfs) : that.bjfs != null) return false;
        if (bjlxdm != null ? !bjlxdm.equals(that.bjlxdm) : that.bjlxdm != null) return false;
        if (bjlx != null ? !bjlx.equals(that.bjlx) : that.bjlx != null) return false;
        if (hylbdm != null ? !hylbdm.equals(that.hylbdm) : that.hylbdm != null) return false;
        if (hylb != null ? !hylb.equals(that.hylb) : that.hylb != null) return false;
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
        result = 31 * result + (ywbm != null ? ywbm.hashCode() : 0);
        result = 31 * result + (ywbzdm != null ? ywbzdm.hashCode() : 0);
        result = 31 * result + (bdbm != null ? bdbm.hashCode() : 0);
        result = 31 * result + (bdbzdm != null ? bdbzdm.hashCode() : 0);
        result = 31 * result + (bdlxdm != null ? bdlxdm.hashCode() : 0);
        result = 31 * result + (bdlx != null ? bdlx.hashCode() : 0);
        result = 31 * result + (sfyx != null ? sfyx.hashCode() : 0);
        result = 31 * result + (fbcl != null ? fbcl.hashCode() : 0);
        result = 31 * result + (fbfs != null ? fbfs.hashCode() : 0);
        result = 31 * result + (txdwzd != null ? txdwzd.hashCode() : 0);
        result = 31 * result + (gxdwzd != null ? gxdwzd.hashCode() : 0);
        result = 31 * result + (bjjb != null ? bjjb.hashCode() : 0);
        result = 31 * result + (bjfs != null ? bjfs.hashCode() : 0);
        result = 31 * result + (bjlxdm != null ? bjlxdm.hashCode() : 0);
        result = 31 * result + (bjlx != null ? bjlx.hashCode() : 0);
        result = 31 * result + (hylbdm != null ? hylbdm.hashCode() : 0);
        result = 31 * result + (hylb != null ? hylb.hashCode() : 0);
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
