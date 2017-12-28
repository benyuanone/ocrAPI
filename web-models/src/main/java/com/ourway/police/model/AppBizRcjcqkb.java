package com.ourway.police.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_rcjcqkb", schema = "public_manage", catalog = "")
public class AppBizRcjcqkb {
    private String owid;
    private String appOwid;
    private String hylbmc;
    private String hylbdm;
    private Timestamp jcrq;
    private Byte jcjg;
    private String zgyj;
    private String jcjgmc;
    private String jcjgdm;
    private String jcjglbmc;
    private String jcjglbdm;
    private String jcrybh;
    private String jcrxm;
    private String jcrzw;
    private String zwdm;
    private String bcdwfzr;
    private String tpdm1;
    private String tpdm2;
    private String zgtp1;
    private String zgtp2;
    private String zgjg;
    private BigInteger zgzt;
    private BigInteger jcfs;
    private Byte nwwbz;
    private Byte sbzt;
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
    @Column(name = "HYLBMC", nullable = true, length = 200)
    public String getHylbmc() {
        return hylbmc;
    }

    public void setHylbmc(String hylbmc) {
        this.hylbmc = hylbmc;
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
    @Column(name = "JCRQ", nullable = true)
    public Timestamp getJcrq() {
        return jcrq;
    }

    public void setJcrq(Timestamp jcrq) {
        this.jcrq = jcrq;
    }

    @Basic
    @Column(name = "JCJG", nullable = true)
    public Byte getJcjg() {
        return jcjg;
    }

    public void setJcjg(Byte jcjg) {
        this.jcjg = jcjg;
    }

    @Basic
    @Column(name = "ZGYJ", nullable = true, length = 1000)
    public String getZgyj() {
        return zgyj;
    }

    public void setZgyj(String zgyj) {
        this.zgyj = zgyj;
    }

    @Basic
    @Column(name = "JCJGMC", nullable = true, length = 200)
    public String getJcjgmc() {
        return jcjgmc;
    }

    public void setJcjgmc(String jcjgmc) {
        this.jcjgmc = jcjgmc;
    }

    @Basic
    @Column(name = "JCJGDM", nullable = true, length = 64)
    public String getJcjgdm() {
        return jcjgdm;
    }

    public void setJcjgdm(String jcjgdm) {
        this.jcjgdm = jcjgdm;
    }

    @Basic
    @Column(name = "JCJGLBMC", nullable = true, length = 200)
    public String getJcjglbmc() {
        return jcjglbmc;
    }

    public void setJcjglbmc(String jcjglbmc) {
        this.jcjglbmc = jcjglbmc;
    }

    @Basic
    @Column(name = "JCJGLBDM", nullable = true, length = 64)
    public String getJcjglbdm() {
        return jcjglbdm;
    }

    public void setJcjglbdm(String jcjglbdm) {
        this.jcjglbdm = jcjglbdm;
    }

    @Basic
    @Column(name = "JCRYBH", nullable = true, length = 20)
    public String getJcrybh() {
        return jcrybh;
    }

    public void setJcrybh(String jcrybh) {
        this.jcrybh = jcrybh;
    }

    @Basic
    @Column(name = "JCRXM", nullable = true, length = 30)
    public String getJcrxm() {
        return jcrxm;
    }

    public void setJcrxm(String jcrxm) {
        this.jcrxm = jcrxm;
    }

    @Basic
    @Column(name = "JCRZW", nullable = true, length = 50)
    public String getJcrzw() {
        return jcrzw;
    }

    public void setJcrzw(String jcrzw) {
        this.jcrzw = jcrzw;
    }

    @Basic
    @Column(name = "ZWDM", nullable = true, length = 64)
    public String getZwdm() {
        return zwdm;
    }

    public void setZwdm(String zwdm) {
        this.zwdm = zwdm;
    }

    @Basic
    @Column(name = "BCDWFZR", nullable = true, length = 30)
    public String getBcdwfzr() {
        return bcdwfzr;
    }

    public void setBcdwfzr(String bcdwfzr) {
        this.bcdwfzr = bcdwfzr;
    }

    @Basic
    @Column(name = "TPDM1", nullable = true, length = 64)
    public String getTpdm1() {
        return tpdm1;
    }

    public void setTpdm1(String tpdm1) {
        this.tpdm1 = tpdm1;
    }

    @Basic
    @Column(name = "TPDM2", nullable = true, length = 64)
    public String getTpdm2() {
        return tpdm2;
    }

    public void setTpdm2(String tpdm2) {
        this.tpdm2 = tpdm2;
    }

    @Basic
    @Column(name = "ZGTP1", nullable = true, length = 64)
    public String getZgtp1() {
        return zgtp1;
    }

    public void setZgtp1(String zgtp1) {
        this.zgtp1 = zgtp1;
    }

    @Basic
    @Column(name = "ZGTP2", nullable = true, length = 64)
    public String getZgtp2() {
        return zgtp2;
    }

    public void setZgtp2(String zgtp2) {
        this.zgtp2 = zgtp2;
    }

    @Basic
    @Column(name = "ZGJG", nullable = true, length = 1000)
    public String getZgjg() {
        return zgjg;
    }

    public void setZgjg(String zgjg) {
        this.zgjg = zgjg;
    }

    @Basic
    @Column(name = "ZGZT", nullable = true, precision = 0)
    public BigInteger getZgzt() {
        return zgzt;
    }

    public void setZgzt(BigInteger zgzt) {
        this.zgzt = zgzt;
    }

    @Basic
    @Column(name = "JCFS", nullable = true, precision = 0)
    public BigInteger getJcfs() {
        return jcfs;
    }

    public void setJcfs(BigInteger jcfs) {
        this.jcfs = jcfs;
    }

    @Basic
    @Column(name = "NWWBZ", nullable = true)
    public Byte getNwwbz() {
        return nwwbz;
    }

    public void setNwwbz(Byte nwwbz) {
        this.nwwbz = nwwbz;
    }

    @Basic
    @Column(name = "SBZT", nullable = true)
    public Byte getSbzt() {
        return sbzt;
    }

    public void setSbzt(Byte sbzt) {
        this.sbzt = sbzt;
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

        AppBizRcjcqkb that = (AppBizRcjcqkb) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (hylbmc != null ? !hylbmc.equals(that.hylbmc) : that.hylbmc != null) return false;
        if (hylbdm != null ? !hylbdm.equals(that.hylbdm) : that.hylbdm != null) return false;
        if (jcrq != null ? !jcrq.equals(that.jcrq) : that.jcrq != null) return false;
        if (jcjg != null ? !jcjg.equals(that.jcjg) : that.jcjg != null) return false;
        if (zgyj != null ? !zgyj.equals(that.zgyj) : that.zgyj != null) return false;
        if (jcjgmc != null ? !jcjgmc.equals(that.jcjgmc) : that.jcjgmc != null) return false;
        if (jcjgdm != null ? !jcjgdm.equals(that.jcjgdm) : that.jcjgdm != null) return false;
        if (jcjglbmc != null ? !jcjglbmc.equals(that.jcjglbmc) : that.jcjglbmc != null) return false;
        if (jcjglbdm != null ? !jcjglbdm.equals(that.jcjglbdm) : that.jcjglbdm != null) return false;
        if (jcrybh != null ? !jcrybh.equals(that.jcrybh) : that.jcrybh != null) return false;
        if (jcrxm != null ? !jcrxm.equals(that.jcrxm) : that.jcrxm != null) return false;
        if (jcrzw != null ? !jcrzw.equals(that.jcrzw) : that.jcrzw != null) return false;
        if (zwdm != null ? !zwdm.equals(that.zwdm) : that.zwdm != null) return false;
        if (bcdwfzr != null ? !bcdwfzr.equals(that.bcdwfzr) : that.bcdwfzr != null) return false;
        if (tpdm1 != null ? !tpdm1.equals(that.tpdm1) : that.tpdm1 != null) return false;
        if (tpdm2 != null ? !tpdm2.equals(that.tpdm2) : that.tpdm2 != null) return false;
        if (zgtp1 != null ? !zgtp1.equals(that.zgtp1) : that.zgtp1 != null) return false;
        if (zgtp2 != null ? !zgtp2.equals(that.zgtp2) : that.zgtp2 != null) return false;
        if (zgjg != null ? !zgjg.equals(that.zgjg) : that.zgjg != null) return false;
        if (zgzt != null ? !zgzt.equals(that.zgzt) : that.zgzt != null) return false;
        if (jcfs != null ? !jcfs.equals(that.jcfs) : that.jcfs != null) return false;
        if (nwwbz != null ? !nwwbz.equals(that.nwwbz) : that.nwwbz != null) return false;
        if (sbzt != null ? !sbzt.equals(that.sbzt) : that.sbzt != null) return false;
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
        result = 31 * result + (hylbmc != null ? hylbmc.hashCode() : 0);
        result = 31 * result + (hylbdm != null ? hylbdm.hashCode() : 0);
        result = 31 * result + (jcrq != null ? jcrq.hashCode() : 0);
        result = 31 * result + (jcjg != null ? jcjg.hashCode() : 0);
        result = 31 * result + (zgyj != null ? zgyj.hashCode() : 0);
        result = 31 * result + (jcjgmc != null ? jcjgmc.hashCode() : 0);
        result = 31 * result + (jcjgdm != null ? jcjgdm.hashCode() : 0);
        result = 31 * result + (jcjglbmc != null ? jcjglbmc.hashCode() : 0);
        result = 31 * result + (jcjglbdm != null ? jcjglbdm.hashCode() : 0);
        result = 31 * result + (jcrybh != null ? jcrybh.hashCode() : 0);
        result = 31 * result + (jcrxm != null ? jcrxm.hashCode() : 0);
        result = 31 * result + (jcrzw != null ? jcrzw.hashCode() : 0);
        result = 31 * result + (zwdm != null ? zwdm.hashCode() : 0);
        result = 31 * result + (bcdwfzr != null ? bcdwfzr.hashCode() : 0);
        result = 31 * result + (tpdm1 != null ? tpdm1.hashCode() : 0);
        result = 31 * result + (tpdm2 != null ? tpdm2.hashCode() : 0);
        result = 31 * result + (zgtp1 != null ? zgtp1.hashCode() : 0);
        result = 31 * result + (zgtp2 != null ? zgtp2.hashCode() : 0);
        result = 31 * result + (zgjg != null ? zgjg.hashCode() : 0);
        result = 31 * result + (zgzt != null ? zgzt.hashCode() : 0);
        result = 31 * result + (jcfs != null ? jcfs.hashCode() : 0);
        result = 31 * result + (nwwbz != null ? nwwbz.hashCode() : 0);
        result = 31 * result + (sbzt != null ? sbzt.hashCode() : 0);
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
