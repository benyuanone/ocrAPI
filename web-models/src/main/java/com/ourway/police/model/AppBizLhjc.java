package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_lhjc", schema = "public_manage", catalog = "")
public class AppBizLhjc {
    private String owid;
    private String appOwid;
    private String glbmlhjcdjxh;
    private String lcjgjbdm;
    private String lcjgjb;
    private String lcjglxdm;
    private String lcjglx;
    private String lcjgdm;
    private String lcjgmc;
    private Timestamp lcsj;
    private String lcryxm;
    private String lcsx;
    private String fxwt;
    private String lcjg;
    private Byte scbz;
    private Byte dcbs;
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
    @Column(name = "GLBMLHJCDJXH", nullable = true, length = 30)
    public String getGlbmlhjcdjxh() {
        return glbmlhjcdjxh;
    }

    public void setGlbmlhjcdjxh(String glbmlhjcdjxh) {
        this.glbmlhjcdjxh = glbmlhjcdjxh;
    }

    @Basic
    @Column(name = "LCJGJBDM", nullable = true, length = 64)
    public String getLcjgjbdm() {
        return lcjgjbdm;
    }

    public void setLcjgjbdm(String lcjgjbdm) {
        this.lcjgjbdm = lcjgjbdm;
    }

    @Basic
    @Column(name = "LCJGJB", nullable = true, length = 30)
    public String getLcjgjb() {
        return lcjgjb;
    }

    public void setLcjgjb(String lcjgjb) {
        this.lcjgjb = lcjgjb;
    }

    @Basic
    @Column(name = "LCJGLXDM", nullable = true, length = 64)
    public String getLcjglxdm() {
        return lcjglxdm;
    }

    public void setLcjglxdm(String lcjglxdm) {
        this.lcjglxdm = lcjglxdm;
    }

    @Basic
    @Column(name = "LCJGLX", nullable = true, length = 30)
    public String getLcjglx() {
        return lcjglx;
    }

    public void setLcjglx(String lcjglx) {
        this.lcjglx = lcjglx;
    }

    @Basic
    @Column(name = "LCJGDM", nullable = true, length = 64)
    public String getLcjgdm() {
        return lcjgdm;
    }

    public void setLcjgdm(String lcjgdm) {
        this.lcjgdm = lcjgdm;
    }

    @Basic
    @Column(name = "LCJGMC", nullable = true, length = 100)
    public String getLcjgmc() {
        return lcjgmc;
    }

    public void setLcjgmc(String lcjgmc) {
        this.lcjgmc = lcjgmc;
    }

    @Basic
    @Column(name = "LCSJ", nullable = true)
    public Timestamp getLcsj() {
        return lcsj;
    }

    public void setLcsj(Timestamp lcsj) {
        this.lcsj = lcsj;
    }

    @Basic
    @Column(name = "LCRYXM", nullable = true, length = 300)
    public String getLcryxm() {
        return lcryxm;
    }

    public void setLcryxm(String lcryxm) {
        this.lcryxm = lcryxm;
    }

    @Basic
    @Column(name = "LCSX", nullable = true, length = 200)
    public String getLcsx() {
        return lcsx;
    }

    public void setLcsx(String lcsx) {
        this.lcsx = lcsx;
    }

    @Basic
    @Column(name = "FXWT", nullable = true, length = 300)
    public String getFxwt() {
        return fxwt;
    }

    public void setFxwt(String fxwt) {
        this.fxwt = fxwt;
    }

    @Basic
    @Column(name = "LCJG", nullable = true, length = 200)
    public String getLcjg() {
        return lcjg;
    }

    public void setLcjg(String lcjg) {
        this.lcjg = lcjg;
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

        AppBizLhjc that = (AppBizLhjc) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (glbmlhjcdjxh != null ? !glbmlhjcdjxh.equals(that.glbmlhjcdjxh) : that.glbmlhjcdjxh != null) return false;
        if (lcjgjbdm != null ? !lcjgjbdm.equals(that.lcjgjbdm) : that.lcjgjbdm != null) return false;
        if (lcjgjb != null ? !lcjgjb.equals(that.lcjgjb) : that.lcjgjb != null) return false;
        if (lcjglxdm != null ? !lcjglxdm.equals(that.lcjglxdm) : that.lcjglxdm != null) return false;
        if (lcjglx != null ? !lcjglx.equals(that.lcjglx) : that.lcjglx != null) return false;
        if (lcjgdm != null ? !lcjgdm.equals(that.lcjgdm) : that.lcjgdm != null) return false;
        if (lcjgmc != null ? !lcjgmc.equals(that.lcjgmc) : that.lcjgmc != null) return false;
        if (lcsj != null ? !lcsj.equals(that.lcsj) : that.lcsj != null) return false;
        if (lcryxm != null ? !lcryxm.equals(that.lcryxm) : that.lcryxm != null) return false;
        if (lcsx != null ? !lcsx.equals(that.lcsx) : that.lcsx != null) return false;
        if (fxwt != null ? !fxwt.equals(that.fxwt) : that.fxwt != null) return false;
        if (lcjg != null ? !lcjg.equals(that.lcjg) : that.lcjg != null) return false;
        if (scbz != null ? !scbz.equals(that.scbz) : that.scbz != null) return false;
        if (dcbs != null ? !dcbs.equals(that.dcbs) : that.dcbs != null) return false;
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
        result = 31 * result + (glbmlhjcdjxh != null ? glbmlhjcdjxh.hashCode() : 0);
        result = 31 * result + (lcjgjbdm != null ? lcjgjbdm.hashCode() : 0);
        result = 31 * result + (lcjgjb != null ? lcjgjb.hashCode() : 0);
        result = 31 * result + (lcjglxdm != null ? lcjglxdm.hashCode() : 0);
        result = 31 * result + (lcjglx != null ? lcjglx.hashCode() : 0);
        result = 31 * result + (lcjgdm != null ? lcjgdm.hashCode() : 0);
        result = 31 * result + (lcjgmc != null ? lcjgmc.hashCode() : 0);
        result = 31 * result + (lcsj != null ? lcsj.hashCode() : 0);
        result = 31 * result + (lcryxm != null ? lcryxm.hashCode() : 0);
        result = 31 * result + (lcsx != null ? lcsx.hashCode() : 0);
        result = 31 * result + (fxwt != null ? fxwt.hashCode() : 0);
        result = 31 * result + (lcjg != null ? lcjg.hashCode() : 0);
        result = 31 * result + (scbz != null ? scbz.hashCode() : 0);
        result = 31 * result + (dcbs != null ? dcbs.hashCode() : 0);
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
