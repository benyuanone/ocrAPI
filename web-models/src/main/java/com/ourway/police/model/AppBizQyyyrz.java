package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_qyyyrz", schema = "public_manage", catalog = "")
public class AppBizQyyyrz {
    private String owid;
    private String appOwid;
    private String hylbdm;
    private String hylb;
    private Timestamp riqi;
    private Timestamp ksyysj;
    private Timestamp jsyysj;
    private String csfzrbh;
    private String csfe;
    private String zafzrbh;
    private String zafzrxm;
    private Byte sffsaj;
    private Byte sfglbmjc;
    private String dryysj;
    private String txrxm;
    private Integer cyryzs;
    private Integer dtsbrs;
    private Integer dtxzrys;
    private Integer dtlzrs;
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
    @Column(name = "RIQI", nullable = true)
    public Timestamp getRiqi() {
        return riqi;
    }

    public void setRiqi(Timestamp riqi) {
        this.riqi = riqi;
    }

    @Basic
    @Column(name = "KSYYSJ", nullable = true)
    public Timestamp getKsyysj() {
        return ksyysj;
    }

    public void setKsyysj(Timestamp ksyysj) {
        this.ksyysj = ksyysj;
    }

    @Basic
    @Column(name = "JSYYSJ", nullable = true)
    public Timestamp getJsyysj() {
        return jsyysj;
    }

    public void setJsyysj(Timestamp jsyysj) {
        this.jsyysj = jsyysj;
    }

    @Basic
    @Column(name = "CSFZRBH", nullable = true, length = 30)
    public String getCsfzrbh() {
        return csfzrbh;
    }

    public void setCsfzrbh(String csfzrbh) {
        this.csfzrbh = csfzrbh;
    }

    @Basic
    @Column(name = "CSFE", nullable = true, length = 30)
    public String getCsfe() {
        return csfe;
    }

    public void setCsfe(String csfe) {
        this.csfe = csfe;
    }

    @Basic
    @Column(name = "ZAFZRBH", nullable = true, length = 30)
    public String getZafzrbh() {
        return zafzrbh;
    }

    public void setZafzrbh(String zafzrbh) {
        this.zafzrbh = zafzrbh;
    }

    @Basic
    @Column(name = "ZAFZRXM", nullable = true, length = 30)
    public String getZafzrxm() {
        return zafzrxm;
    }

    public void setZafzrxm(String zafzrxm) {
        this.zafzrxm = zafzrxm;
    }

    @Basic
    @Column(name = "SFFSAJ", nullable = true)
    public Byte getSffsaj() {
        return sffsaj;
    }

    public void setSffsaj(Byte sffsaj) {
        this.sffsaj = sffsaj;
    }

    @Basic
    @Column(name = "SFGLBMJC", nullable = true)
    public Byte getSfglbmjc() {
        return sfglbmjc;
    }

    public void setSfglbmjc(Byte sfglbmjc) {
        this.sfglbmjc = sfglbmjc;
    }

    @Basic
    @Column(name = "DRYYSJ", nullable = true, length = 2000)
    public String getDryysj() {
        return dryysj;
    }

    public void setDryysj(String dryysj) {
        this.dryysj = dryysj;
    }

    @Basic
    @Column(name = "TXRXM", nullable = true, length = 30)
    public String getTxrxm() {
        return txrxm;
    }

    public void setTxrxm(String txrxm) {
        this.txrxm = txrxm;
    }

    @Basic
    @Column(name = "CYRYZS", nullable = true, precision = 0)
    public Integer getCyryzs() {
        return cyryzs;
    }

    public void setCyryzs(Integer cyryzs) {
        this.cyryzs = cyryzs;
    }

    @Basic
    @Column(name = "DTSBRS", nullable = true, precision = 0)
    public Integer getDtsbrs() {
        return dtsbrs;
    }

    public void setDtsbrs(Integer dtsbrs) {
        this.dtsbrs = dtsbrs;
    }

    @Basic
    @Column(name = "DTXZRYS", nullable = true, precision = 0)
    public Integer getDtxzrys() {
        return dtxzrys;
    }

    public void setDtxzrys(Integer dtxzrys) {
        this.dtxzrys = dtxzrys;
    }

    @Basic
    @Column(name = "DTLZRS", nullable = true, precision = 0)
    public Integer getDtlzrs() {
        return dtlzrs;
    }

    public void setDtlzrs(Integer dtlzrs) {
        this.dtlzrs = dtlzrs;
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

        AppBizQyyyrz that = (AppBizQyyyrz) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (hylbdm != null ? !hylbdm.equals(that.hylbdm) : that.hylbdm != null) return false;
        if (hylb != null ? !hylb.equals(that.hylb) : that.hylb != null) return false;
        if (riqi != null ? !riqi.equals(that.riqi) : that.riqi != null) return false;
        if (ksyysj != null ? !ksyysj.equals(that.ksyysj) : that.ksyysj != null) return false;
        if (jsyysj != null ? !jsyysj.equals(that.jsyysj) : that.jsyysj != null) return false;
        if (csfzrbh != null ? !csfzrbh.equals(that.csfzrbh) : that.csfzrbh != null) return false;
        if (csfe != null ? !csfe.equals(that.csfe) : that.csfe != null) return false;
        if (zafzrbh != null ? !zafzrbh.equals(that.zafzrbh) : that.zafzrbh != null) return false;
        if (zafzrxm != null ? !zafzrxm.equals(that.zafzrxm) : that.zafzrxm != null) return false;
        if (sffsaj != null ? !sffsaj.equals(that.sffsaj) : that.sffsaj != null) return false;
        if (sfglbmjc != null ? !sfglbmjc.equals(that.sfglbmjc) : that.sfglbmjc != null) return false;
        if (dryysj != null ? !dryysj.equals(that.dryysj) : that.dryysj != null) return false;
        if (txrxm != null ? !txrxm.equals(that.txrxm) : that.txrxm != null) return false;
        if (cyryzs != null ? !cyryzs.equals(that.cyryzs) : that.cyryzs != null) return false;
        if (dtsbrs != null ? !dtsbrs.equals(that.dtsbrs) : that.dtsbrs != null) return false;
        if (dtxzrys != null ? !dtxzrys.equals(that.dtxzrys) : that.dtxzrys != null) return false;
        if (dtlzrs != null ? !dtlzrs.equals(that.dtlzrs) : that.dtlzrs != null) return false;
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
        result = 31 * result + (hylbdm != null ? hylbdm.hashCode() : 0);
        result = 31 * result + (hylb != null ? hylb.hashCode() : 0);
        result = 31 * result + (riqi != null ? riqi.hashCode() : 0);
        result = 31 * result + (ksyysj != null ? ksyysj.hashCode() : 0);
        result = 31 * result + (jsyysj != null ? jsyysj.hashCode() : 0);
        result = 31 * result + (csfzrbh != null ? csfzrbh.hashCode() : 0);
        result = 31 * result + (csfe != null ? csfe.hashCode() : 0);
        result = 31 * result + (zafzrbh != null ? zafzrbh.hashCode() : 0);
        result = 31 * result + (zafzrxm != null ? zafzrxm.hashCode() : 0);
        result = 31 * result + (sffsaj != null ? sffsaj.hashCode() : 0);
        result = 31 * result + (sfglbmjc != null ? sfglbmjc.hashCode() : 0);
        result = 31 * result + (dryysj != null ? dryysj.hashCode() : 0);
        result = 31 * result + (txrxm != null ? txrxm.hashCode() : 0);
        result = 31 * result + (cyryzs != null ? cyryzs.hashCode() : 0);
        result = 31 * result + (dtsbrs != null ? dtsbrs.hashCode() : 0);
        result = 31 * result + (dtxzrys != null ? dtxzrys.hashCode() : 0);
        result = 31 * result + (dtlzrs != null ? dtlzrs.hashCode() : 0);
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
