package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_ryjcxx", schema = "public_manage", catalog = "")
public class AppBizRyjcxx {
    private String owid;
    private String appOwid;
    private String hylbdm;
    private String hylb;
    private Timestamp ryjcrq;
    private String cfdjxh;
    private String cflbbm;
    private String cflb;
    private String jcmxlbdm;
    private String jcmxlb;
    private String xzcfpzwh;
    private String pzjgdm;
    private String pzjg;
    private String pzrbh;
    private String pzrxm;
    private Timestamp pzrq;
    private String zxrbh;
    private String zxrxm;
    private Timestamp zxrq;
    private String qkms;
    private String yjly;
    private String jcfd;
    private Byte scbz;
    private Byte dcbs;
    private String lrr;
    private Timestamp lrsj;
    private String lrdwbm;
    private String lrdwmc;
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
    @Column(name = "HYLBDM", nullable = true, length = 64)
    public String getHylbdm() {
        return hylbdm;
    }

    public void setHylbdm(String hylbdm) {
        this.hylbdm = hylbdm;
    }

    @Basic
    @Column(name = "HYLB", nullable = true, length = 40)
    public String getHylb() {
        return hylb;
    }

    public void setHylb(String hylb) {
        this.hylb = hylb;
    }

    @Basic
    @Column(name = "RYJCRQ", nullable = true)
    public Timestamp getRyjcrq() {
        return ryjcrq;
    }

    public void setRyjcrq(Timestamp ryjcrq) {
        this.ryjcrq = ryjcrq;
    }

    @Basic
    @Column(name = "CFDJXH", nullable = true, length = 30)
    public String getCfdjxh() {
        return cfdjxh;
    }

    public void setCfdjxh(String cfdjxh) {
        this.cfdjxh = cfdjxh;
    }

    @Basic
    @Column(name = "CFLBBM", nullable = true, length = 64)
    public String getCflbbm() {
        return cflbbm;
    }

    public void setCflbbm(String cflbbm) {
        this.cflbbm = cflbbm;
    }

    @Basic
    @Column(name = "CFLB", nullable = true, length = 100)
    public String getCflb() {
        return cflb;
    }

    public void setCflb(String cflb) {
        this.cflb = cflb;
    }

    @Basic
    @Column(name = "JCMXLBDM", nullable = true, length = 64)
    public String getJcmxlbdm() {
        return jcmxlbdm;
    }

    public void setJcmxlbdm(String jcmxlbdm) {
        this.jcmxlbdm = jcmxlbdm;
    }

    @Basic
    @Column(name = "JCMXLB", nullable = true, length = 100)
    public String getJcmxlb() {
        return jcmxlb;
    }

    public void setJcmxlb(String jcmxlb) {
        this.jcmxlb = jcmxlb;
    }

    @Basic
    @Column(name = "XZCFPZWH", nullable = true, length = 20)
    public String getXzcfpzwh() {
        return xzcfpzwh;
    }

    public void setXzcfpzwh(String xzcfpzwh) {
        this.xzcfpzwh = xzcfpzwh;
    }

    @Basic
    @Column(name = "PZJGDM", nullable = true, length = 64)
    public String getPzjgdm() {
        return pzjgdm;
    }

    public void setPzjgdm(String pzjgdm) {
        this.pzjgdm = pzjgdm;
    }

    @Basic
    @Column(name = "PZJG", nullable = true, length = 100)
    public String getPzjg() {
        return pzjg;
    }

    public void setPzjg(String pzjg) {
        this.pzjg = pzjg;
    }

    @Basic
    @Column(name = "PZRBH", nullable = true, length = 30)
    public String getPzrbh() {
        return pzrbh;
    }

    public void setPzrbh(String pzrbh) {
        this.pzrbh = pzrbh;
    }

    @Basic
    @Column(name = "PZRXM", nullable = true, length = 100)
    public String getPzrxm() {
        return pzrxm;
    }

    public void setPzrxm(String pzrxm) {
        this.pzrxm = pzrxm;
    }

    @Basic
    @Column(name = "PZRQ", nullable = true)
    public Timestamp getPzrq() {
        return pzrq;
    }

    public void setPzrq(Timestamp pzrq) {
        this.pzrq = pzrq;
    }

    @Basic
    @Column(name = "ZXRBH", nullable = true, length = 30)
    public String getZxrbh() {
        return zxrbh;
    }

    public void setZxrbh(String zxrbh) {
        this.zxrbh = zxrbh;
    }

    @Basic
    @Column(name = "ZXRXM", nullable = true, length = 100)
    public String getZxrxm() {
        return zxrxm;
    }

    public void setZxrxm(String zxrxm) {
        this.zxrxm = zxrxm;
    }

    @Basic
    @Column(name = "ZXRQ", nullable = true)
    public Timestamp getZxrq() {
        return zxrq;
    }

    public void setZxrq(Timestamp zxrq) {
        this.zxrq = zxrq;
    }

    @Basic
    @Column(name = "QKMS", nullable = true, length = 2000)
    public String getQkms() {
        return qkms;
    }

    public void setQkms(String qkms) {
        this.qkms = qkms;
    }

    @Basic
    @Column(name = "YJLY", nullable = true, length = 2000)
    public String getYjly() {
        return yjly;
    }

    public void setYjly(String yjly) {
        this.yjly = yjly;
    }

    @Basic
    @Column(name = "JCFD", nullable = true, length = 100)
    public String getJcfd() {
        return jcfd;
    }

    public void setJcfd(String jcfd) {
        this.jcfd = jcfd;
    }

    @Basic
    @Column(name = "SCBZ", nullable = true)
    public Byte getScbz() {
        return scbz;
    }

    public void setScbz(Byte scbz) {
        this.scbz = scbz;
    }

    @Basic
    @Column(name = "DCBS", nullable = true)
    public Byte getDcbs() {
        return dcbs;
    }

    public void setDcbs(Byte dcbs) {
        this.dcbs = dcbs;
    }

    @Basic
    @Column(name = "LRR", nullable = true, length = 100)
    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    @Basic
    @Column(name = "LRSJ", nullable = true)
    public Timestamp getLrsj() {
        return lrsj;
    }

    public void setLrsj(Timestamp lrsj) {
        this.lrsj = lrsj;
    }

    @Basic
    @Column(name = "LRDWBM", nullable = true, length = 64)
    public String getLrdwbm() {
        return lrdwbm;
    }

    public void setLrdwbm(String lrdwbm) {
        this.lrdwbm = lrdwbm;
    }

    @Basic
    @Column(name = "LRDWMC", nullable = true, length = 60)
    public String getLrdwmc() {
        return lrdwmc;
    }

    public void setLrdwmc(String lrdwmc) {
        this.lrdwmc = lrdwmc;
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

        AppBizRyjcxx that = (AppBizRyjcxx) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (hylbdm != null ? !hylbdm.equals(that.hylbdm) : that.hylbdm != null) return false;
        if (hylb != null ? !hylb.equals(that.hylb) : that.hylb != null) return false;
        if (ryjcrq != null ? !ryjcrq.equals(that.ryjcrq) : that.ryjcrq != null) return false;
        if (cfdjxh != null ? !cfdjxh.equals(that.cfdjxh) : that.cfdjxh != null) return false;
        if (cflbbm != null ? !cflbbm.equals(that.cflbbm) : that.cflbbm != null) return false;
        if (cflb != null ? !cflb.equals(that.cflb) : that.cflb != null) return false;
        if (jcmxlbdm != null ? !jcmxlbdm.equals(that.jcmxlbdm) : that.jcmxlbdm != null) return false;
        if (jcmxlb != null ? !jcmxlb.equals(that.jcmxlb) : that.jcmxlb != null) return false;
        if (xzcfpzwh != null ? !xzcfpzwh.equals(that.xzcfpzwh) : that.xzcfpzwh != null) return false;
        if (pzjgdm != null ? !pzjgdm.equals(that.pzjgdm) : that.pzjgdm != null) return false;
        if (pzjg != null ? !pzjg.equals(that.pzjg) : that.pzjg != null) return false;
        if (pzrbh != null ? !pzrbh.equals(that.pzrbh) : that.pzrbh != null) return false;
        if (pzrxm != null ? !pzrxm.equals(that.pzrxm) : that.pzrxm != null) return false;
        if (pzrq != null ? !pzrq.equals(that.pzrq) : that.pzrq != null) return false;
        if (zxrbh != null ? !zxrbh.equals(that.zxrbh) : that.zxrbh != null) return false;
        if (zxrxm != null ? !zxrxm.equals(that.zxrxm) : that.zxrxm != null) return false;
        if (zxrq != null ? !zxrq.equals(that.zxrq) : that.zxrq != null) return false;
        if (qkms != null ? !qkms.equals(that.qkms) : that.qkms != null) return false;
        if (yjly != null ? !yjly.equals(that.yjly) : that.yjly != null) return false;
        if (jcfd != null ? !jcfd.equals(that.jcfd) : that.jcfd != null) return false;
        if (scbz != null ? !scbz.equals(that.scbz) : that.scbz != null) return false;
        if (dcbs != null ? !dcbs.equals(that.dcbs) : that.dcbs != null) return false;
        if (lrr != null ? !lrr.equals(that.lrr) : that.lrr != null) return false;
        if (lrsj != null ? !lrsj.equals(that.lrsj) : that.lrsj != null) return false;
        if (lrdwbm != null ? !lrdwbm.equals(that.lrdwbm) : that.lrdwbm != null) return false;
        if (lrdwmc != null ? !lrdwmc.equals(that.lrdwmc) : that.lrdwmc != null) return false;
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
        result = 31 * result + (hylbdm != null ? hylbdm.hashCode() : 0);
        result = 31 * result + (hylb != null ? hylb.hashCode() : 0);
        result = 31 * result + (ryjcrq != null ? ryjcrq.hashCode() : 0);
        result = 31 * result + (cfdjxh != null ? cfdjxh.hashCode() : 0);
        result = 31 * result + (cflbbm != null ? cflbbm.hashCode() : 0);
        result = 31 * result + (cflb != null ? cflb.hashCode() : 0);
        result = 31 * result + (jcmxlbdm != null ? jcmxlbdm.hashCode() : 0);
        result = 31 * result + (jcmxlb != null ? jcmxlb.hashCode() : 0);
        result = 31 * result + (xzcfpzwh != null ? xzcfpzwh.hashCode() : 0);
        result = 31 * result + (pzjgdm != null ? pzjgdm.hashCode() : 0);
        result = 31 * result + (pzjg != null ? pzjg.hashCode() : 0);
        result = 31 * result + (pzrbh != null ? pzrbh.hashCode() : 0);
        result = 31 * result + (pzrxm != null ? pzrxm.hashCode() : 0);
        result = 31 * result + (pzrq != null ? pzrq.hashCode() : 0);
        result = 31 * result + (zxrbh != null ? zxrbh.hashCode() : 0);
        result = 31 * result + (zxrxm != null ? zxrxm.hashCode() : 0);
        result = 31 * result + (zxrq != null ? zxrq.hashCode() : 0);
        result = 31 * result + (qkms != null ? qkms.hashCode() : 0);
        result = 31 * result + (yjly != null ? yjly.hashCode() : 0);
        result = 31 * result + (jcfd != null ? jcfd.hashCode() : 0);
        result = 31 * result + (scbz != null ? scbz.hashCode() : 0);
        result = 31 * result + (dcbs != null ? dcbs.hashCode() : 0);
        result = 31 * result + (lrr != null ? lrr.hashCode() : 0);
        result = 31 * result + (lrsj != null ? lrsj.hashCode() : 0);
        result = 31 * result + (lrdwbm != null ? lrdwbm.hashCode() : 0);
        result = 31 * result + (lrdwmc != null ? lrdwmc.hashCode() : 0);
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
