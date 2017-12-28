package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_qycf", schema = "public_manage", catalog = "")
public class AppBizQycf {
    private String owid;
    private String appOwid;
    private String hylb;
    private String qybm;
    private String qymc;
    private String ssdwbm;
    private String ssdwmc;
    private String wgqkms;
    private String cfyj;
    private String cflx;
    private String cfjg;
    private String pzjgbm;
    private String pzjgmc;
    private String pzrxm;
    private String cfzxrxm;
    private Timestamp cfrq;
    private Timestamp djsj;
    private String djr;
    private String bz;
    private String hylbdm;
    private String cfjgdm;
    private String cflb;
    private String cflbbm;
    private String cflxbm;
    private String xzcfpzwh;
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
    @Column(name = "HYLB", nullable = true, length = 100)
    public String getHylb() {
        return hylb;
    }

    public void setHylb(String hylb) {
        this.hylb = hylb;
    }

    @Basic
    @Column(name = "QYBM", nullable = true, length = 64)
    public String getQybm() {
        return qybm;
    }

    public void setQybm(String qybm) {
        this.qybm = qybm;
    }

    @Basic
    @Column(name = "QYMC", nullable = true, length = 200)
    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    @Basic
    @Column(name = "SSDWBM", nullable = true, length = 64)
    public String getSsdwbm() {
        return ssdwbm;
    }

    public void setSsdwbm(String ssdwbm) {
        this.ssdwbm = ssdwbm;
    }

    @Basic
    @Column(name = "SSDWMC", nullable = true, length = 100)
    public String getSsdwmc() {
        return ssdwmc;
    }

    public void setSsdwmc(String ssdwmc) {
        this.ssdwmc = ssdwmc;
    }

    @Basic
    @Column(name = "WGQKMS", nullable = true, length = 2000)
    public String getWgqkms() {
        return wgqkms;
    }

    public void setWgqkms(String wgqkms) {
        this.wgqkms = wgqkms;
    }

    @Basic
    @Column(name = "CFYJ", nullable = true, length = 2000)
    public String getCfyj() {
        return cfyj;
    }

    public void setCfyj(String cfyj) {
        this.cfyj = cfyj;
    }

    @Basic
    @Column(name = "CFLX", nullable = true, length = 60)
    public String getCflx() {
        return cflx;
    }

    public void setCflx(String cflx) {
        this.cflx = cflx;
    }

    @Basic
    @Column(name = "CFJG", nullable = true, length = 2000)
    public String getCfjg() {
        return cfjg;
    }

    public void setCfjg(String cfjg) {
        this.cfjg = cfjg;
    }

    @Basic
    @Column(name = "PZJGBM", nullable = true, length = 64)
    public String getPzjgbm() {
        return pzjgbm;
    }

    public void setPzjgbm(String pzjgbm) {
        this.pzjgbm = pzjgbm;
    }

    @Basic
    @Column(name = "PZJGMC", nullable = true, length = 100)
    public String getPzjgmc() {
        return pzjgmc;
    }

    public void setPzjgmc(String pzjgmc) {
        this.pzjgmc = pzjgmc;
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
    @Column(name = "CFZXRXM", nullable = true, length = 100)
    public String getCfzxrxm() {
        return cfzxrxm;
    }

    public void setCfzxrxm(String cfzxrxm) {
        this.cfzxrxm = cfzxrxm;
    }

    @Basic
    @Column(name = "CFRQ", nullable = true)
    public Timestamp getCfrq() {
        return cfrq;
    }

    public void setCfrq(Timestamp cfrq) {
        this.cfrq = cfrq;
    }

    @Basic
    @Column(name = "DJSJ", nullable = true)
    public Timestamp getDjsj() {
        return djsj;
    }

    public void setDjsj(Timestamp djsj) {
        this.djsj = djsj;
    }

    @Basic
    @Column(name = "DJR", nullable = true, length = 30)
    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    @Basic
    @Column(name = "BZ", nullable = true, length = 2000)
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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
    @Column(name = "CFJGDM", nullable = true, length = 64)
    public String getCfjgdm() {
        return cfjgdm;
    }

    public void setCfjgdm(String cfjgdm) {
        this.cfjgdm = cfjgdm;
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
    @Column(name = "CFLBBM", nullable = true, length = 64)
    public String getCflbbm() {
        return cflbbm;
    }

    public void setCflbbm(String cflbbm) {
        this.cflbbm = cflbbm;
    }

    @Basic
    @Column(name = "CFLXBM", nullable = true, length = 64)
    public String getCflxbm() {
        return cflxbm;
    }

    public void setCflxbm(String cflxbm) {
        this.cflxbm = cflxbm;
    }

    @Basic
    @Column(name = "XZCFPZWH", nullable = true, length = 100)
    public String getXzcfpzwh() {
        return xzcfpzwh;
    }

    public void setXzcfpzwh(String xzcfpzwh) {
        this.xzcfpzwh = xzcfpzwh;
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

        AppBizQycf that = (AppBizQycf) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (hylb != null ? !hylb.equals(that.hylb) : that.hylb != null) return false;
        if (qybm != null ? !qybm.equals(that.qybm) : that.qybm != null) return false;
        if (qymc != null ? !qymc.equals(that.qymc) : that.qymc != null) return false;
        if (ssdwbm != null ? !ssdwbm.equals(that.ssdwbm) : that.ssdwbm != null) return false;
        if (ssdwmc != null ? !ssdwmc.equals(that.ssdwmc) : that.ssdwmc != null) return false;
        if (wgqkms != null ? !wgqkms.equals(that.wgqkms) : that.wgqkms != null) return false;
        if (cfyj != null ? !cfyj.equals(that.cfyj) : that.cfyj != null) return false;
        if (cflx != null ? !cflx.equals(that.cflx) : that.cflx != null) return false;
        if (cfjg != null ? !cfjg.equals(that.cfjg) : that.cfjg != null) return false;
        if (pzjgbm != null ? !pzjgbm.equals(that.pzjgbm) : that.pzjgbm != null) return false;
        if (pzjgmc != null ? !pzjgmc.equals(that.pzjgmc) : that.pzjgmc != null) return false;
        if (pzrxm != null ? !pzrxm.equals(that.pzrxm) : that.pzrxm != null) return false;
        if (cfzxrxm != null ? !cfzxrxm.equals(that.cfzxrxm) : that.cfzxrxm != null) return false;
        if (cfrq != null ? !cfrq.equals(that.cfrq) : that.cfrq != null) return false;
        if (djsj != null ? !djsj.equals(that.djsj) : that.djsj != null) return false;
        if (djr != null ? !djr.equals(that.djr) : that.djr != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (hylbdm != null ? !hylbdm.equals(that.hylbdm) : that.hylbdm != null) return false;
        if (cfjgdm != null ? !cfjgdm.equals(that.cfjgdm) : that.cfjgdm != null) return false;
        if (cflb != null ? !cflb.equals(that.cflb) : that.cflb != null) return false;
        if (cflbbm != null ? !cflbbm.equals(that.cflbbm) : that.cflbbm != null) return false;
        if (cflxbm != null ? !cflxbm.equals(that.cflxbm) : that.cflxbm != null) return false;
        if (xzcfpzwh != null ? !xzcfpzwh.equals(that.xzcfpzwh) : that.xzcfpzwh != null) return false;
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
        result = 31 * result + (hylb != null ? hylb.hashCode() : 0);
        result = 31 * result + (qybm != null ? qybm.hashCode() : 0);
        result = 31 * result + (qymc != null ? qymc.hashCode() : 0);
        result = 31 * result + (ssdwbm != null ? ssdwbm.hashCode() : 0);
        result = 31 * result + (ssdwmc != null ? ssdwmc.hashCode() : 0);
        result = 31 * result + (wgqkms != null ? wgqkms.hashCode() : 0);
        result = 31 * result + (cfyj != null ? cfyj.hashCode() : 0);
        result = 31 * result + (cflx != null ? cflx.hashCode() : 0);
        result = 31 * result + (cfjg != null ? cfjg.hashCode() : 0);
        result = 31 * result + (pzjgbm != null ? pzjgbm.hashCode() : 0);
        result = 31 * result + (pzjgmc != null ? pzjgmc.hashCode() : 0);
        result = 31 * result + (pzrxm != null ? pzrxm.hashCode() : 0);
        result = 31 * result + (cfzxrxm != null ? cfzxrxm.hashCode() : 0);
        result = 31 * result + (cfrq != null ? cfrq.hashCode() : 0);
        result = 31 * result + (djsj != null ? djsj.hashCode() : 0);
        result = 31 * result + (djr != null ? djr.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (hylbdm != null ? hylbdm.hashCode() : 0);
        result = 31 * result + (cfjgdm != null ? cfjgdm.hashCode() : 0);
        result = 31 * result + (cflb != null ? cflb.hashCode() : 0);
        result = 31 * result + (cflbbm != null ? cflbbm.hashCode() : 0);
        result = 31 * result + (cflxbm != null ? cflxbm.hashCode() : 0);
        result = 31 * result + (xzcfpzwh != null ? xzcfpzwh.hashCode() : 0);
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
