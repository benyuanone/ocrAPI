package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_bldsjksb", schema = "public_manage", catalog = "")
public class AppBizBldsjksb {
    private String owid;
    private String appOwid;
    private String wlcslxbm;
    private String wlcslxmc;
    private String sbjcjgmc;
    private Integer azsxtsl;
    private String sbxh;
    private String sccsmc;
    private String ggxh;
    private Integer azsl;
    private String sbmc;
    private Byte sflx;
    private String ipdz;
    private String anwz;
    private Byte zt;
    private String tyyy;
    private Timestamp tysj;
    private String jibr;
    private String bz;
    private String lrr;
    private Timestamp lrsj;
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
    @Column(name = "WLCSLXBM", nullable = true, length = 64)
    public String getWlcslxbm() {
        return wlcslxbm;
    }

    public void setWlcslxbm(String wlcslxbm) {
        this.wlcslxbm = wlcslxbm;
    }

    @Basic
    @Column(name = "WLCSLXMC", nullable = true, length = 100)
    public String getWlcslxmc() {
        return wlcslxmc;
    }

    public void setWlcslxmc(String wlcslxmc) {
        this.wlcslxmc = wlcslxmc;
    }

    @Basic
    @Column(name = "SBJCJGMC", nullable = true, length = 200)
    public String getSbjcjgmc() {
        return sbjcjgmc;
    }

    public void setSbjcjgmc(String sbjcjgmc) {
        this.sbjcjgmc = sbjcjgmc;
    }

    @Basic
    @Column(name = "AZSXTSL", nullable = true, precision = 0)
    public Integer getAzsxtsl() {
        return azsxtsl;
    }

    public void setAzsxtsl(Integer azsxtsl) {
        this.azsxtsl = azsxtsl;
    }

    @Basic
    @Column(name = "SBXH", nullable = true, length = 22)
    public String getSbxh() {
        return sbxh;
    }

    public void setSbxh(String sbxh) {
        this.sbxh = sbxh;
    }

    @Basic
    @Column(name = "SCCSMC", nullable = true, length = 200)
    public String getSccsmc() {
        return sccsmc;
    }

    public void setSccsmc(String sccsmc) {
        this.sccsmc = sccsmc;
    }

    @Basic
    @Column(name = "GGXH", nullable = true, length = 100)
    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    @Basic
    @Column(name = "AZSL", nullable = true, precision = 0)
    public Integer getAzsl() {
        return azsl;
    }

    public void setAzsl(Integer azsl) {
        this.azsl = azsl;
    }

    @Basic
    @Column(name = "SBMC", nullable = true, length = 100)
    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    @Basic
    @Column(name = "SFLX", nullable = true)
    public Byte getSflx() {
        return sflx;
    }

    public void setSflx(Byte sflx) {
        this.sflx = sflx;
    }

    @Basic
    @Column(name = "IPDZ", nullable = true, length = 20)
    public String getIpdz() {
        return ipdz;
    }

    public void setIpdz(String ipdz) {
        this.ipdz = ipdz;
    }

    @Basic
    @Column(name = "ANWZ", nullable = true, length = 2000)
    public String getAnwz() {
        return anwz;
    }

    public void setAnwz(String anwz) {
        this.anwz = anwz;
    }

    @Basic
    @Column(name = "ZT", nullable = true)
    public Byte getZt() {
        return zt;
    }

    public void setZt(Byte zt) {
        this.zt = zt;
    }

    @Basic
    @Column(name = "TYYY", nullable = true, length = 200)
    public String getTyyy() {
        return tyyy;
    }

    public void setTyyy(String tyyy) {
        this.tyyy = tyyy;
    }

    @Basic
    @Column(name = "TYSJ", nullable = true)
    public Timestamp getTysj() {
        return tysj;
    }

    public void setTysj(Timestamp tysj) {
        this.tysj = tysj;
    }

    @Basic
    @Column(name = "JIBR", nullable = true, length = 30)
    public String getJibr() {
        return jibr;
    }

    public void setJibr(String jibr) {
        this.jibr = jibr;
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

        AppBizBldsjksb that = (AppBizBldsjksb) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (wlcslxbm != null ? !wlcslxbm.equals(that.wlcslxbm) : that.wlcslxbm != null) return false;
        if (wlcslxmc != null ? !wlcslxmc.equals(that.wlcslxmc) : that.wlcslxmc != null) return false;
        if (sbjcjgmc != null ? !sbjcjgmc.equals(that.sbjcjgmc) : that.sbjcjgmc != null) return false;
        if (azsxtsl != null ? !azsxtsl.equals(that.azsxtsl) : that.azsxtsl != null) return false;
        if (sbxh != null ? !sbxh.equals(that.sbxh) : that.sbxh != null) return false;
        if (sccsmc != null ? !sccsmc.equals(that.sccsmc) : that.sccsmc != null) return false;
        if (ggxh != null ? !ggxh.equals(that.ggxh) : that.ggxh != null) return false;
        if (azsl != null ? !azsl.equals(that.azsl) : that.azsl != null) return false;
        if (sbmc != null ? !sbmc.equals(that.sbmc) : that.sbmc != null) return false;
        if (sflx != null ? !sflx.equals(that.sflx) : that.sflx != null) return false;
        if (ipdz != null ? !ipdz.equals(that.ipdz) : that.ipdz != null) return false;
        if (anwz != null ? !anwz.equals(that.anwz) : that.anwz != null) return false;
        if (zt != null ? !zt.equals(that.zt) : that.zt != null) return false;
        if (tyyy != null ? !tyyy.equals(that.tyyy) : that.tyyy != null) return false;
        if (tysj != null ? !tysj.equals(that.tysj) : that.tysj != null) return false;
        if (jibr != null ? !jibr.equals(that.jibr) : that.jibr != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (lrr != null ? !lrr.equals(that.lrr) : that.lrr != null) return false;
        if (lrsj != null ? !lrsj.equals(that.lrsj) : that.lrsj != null) return false;
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
        result = 31 * result + (wlcslxbm != null ? wlcslxbm.hashCode() : 0);
        result = 31 * result + (wlcslxmc != null ? wlcslxmc.hashCode() : 0);
        result = 31 * result + (sbjcjgmc != null ? sbjcjgmc.hashCode() : 0);
        result = 31 * result + (azsxtsl != null ? azsxtsl.hashCode() : 0);
        result = 31 * result + (sbxh != null ? sbxh.hashCode() : 0);
        result = 31 * result + (sccsmc != null ? sccsmc.hashCode() : 0);
        result = 31 * result + (ggxh != null ? ggxh.hashCode() : 0);
        result = 31 * result + (azsl != null ? azsl.hashCode() : 0);
        result = 31 * result + (sbmc != null ? sbmc.hashCode() : 0);
        result = 31 * result + (sflx != null ? sflx.hashCode() : 0);
        result = 31 * result + (ipdz != null ? ipdz.hashCode() : 0);
        result = 31 * result + (anwz != null ? anwz.hashCode() : 0);
        result = 31 * result + (zt != null ? zt.hashCode() : 0);
        result = 31 * result + (tyyy != null ? tyyy.hashCode() : 0);
        result = 31 * result + (tysj != null ? tysj.hashCode() : 0);
        result = 31 * result + (jibr != null ? jibr.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (lrr != null ? lrr.hashCode() : 0);
        result = 31 * result + (lrsj != null ? lrsj.hashCode() : 0);
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
