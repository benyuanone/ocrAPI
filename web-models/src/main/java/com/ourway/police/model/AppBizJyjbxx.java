package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_jyjbxx", schema = "public_manage", catalog = "")
public class AppBizJyjbxx {
    private String owid;
    private Integer appOwid;
    private String xm;
    private Byte xb;
    private String sfz;
    private String jyh;
    private String sjh;
    private String tx;
    private String ssjgmc;
    private String ssjgdm;
    private String gxcsid;
    private String gxcsmc;
    private String gxcslx;
    private String gxcslxdm;
    private String jyzh;
    private Byte jyzhzt;
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
    @Column(name = "APP_OWID", nullable = true)
    public Integer getAppOwid() {
        return appOwid;
    }

    public void setAppOwid(Integer appOwid) {
        this.appOwid = appOwid;
    }

    @Basic
    @Column(name = "XM", nullable = true, length = 50)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Basic
    @Column(name = "XB", nullable = true)
    public Byte getXb() {
        return xb;
    }

    public void setXb(Byte xb) {
        this.xb = xb;
    }

    @Basic
    @Column(name = "SFZ", nullable = true, length = 18)
    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    @Basic
    @Column(name = "JYH", nullable = true, length = 20)
    public String getJyh() {
        return jyh;
    }

    public void setJyh(String jyh) {
        this.jyh = jyh;
    }

    @Basic
    @Column(name = "SJH", nullable = true, length = 11)
    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    @Basic
    @Column(name = "TX", nullable = true, length = 64)
    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    @Basic
    @Column(name = "SSJGMC", nullable = true, length = 200)
    public String getSsjgmc() {
        return ssjgmc;
    }

    public void setSsjgmc(String ssjgmc) {
        this.ssjgmc = ssjgmc;
    }

    @Basic
    @Column(name = "SSJGDM", nullable = true, length = 64)
    public String getSsjgdm() {
        return ssjgdm;
    }

    public void setSsjgdm(String ssjgdm) {
        this.ssjgdm = ssjgdm;
    }

    @Basic
    @Column(name = "GXCSID", nullable = true, length = 64)
    public String getGxcsid() {
        return gxcsid;
    }

    public void setGxcsid(String gxcsid) {
        this.gxcsid = gxcsid;
    }

    @Basic
    @Column(name = "GXCSMC", nullable = true, length = 200)
    public String getGxcsmc() {
        return gxcsmc;
    }

    public void setGxcsmc(String gxcsmc) {
        this.gxcsmc = gxcsmc;
    }

    @Basic
    @Column(name = "GXCSLX", nullable = true, length = 200)
    public String getGxcslx() {
        return gxcslx;
    }

    public void setGxcslx(String gxcslx) {
        this.gxcslx = gxcslx;
    }

    @Basic
    @Column(name = "GXCSLXDM", nullable = true, length = 64)
    public String getGxcslxdm() {
        return gxcslxdm;
    }

    public void setGxcslxdm(String gxcslxdm) {
        this.gxcslxdm = gxcslxdm;
    }

    @Basic
    @Column(name = "JYZH", nullable = true, length = 50)
    public String getJyzh() {
        return jyzh;
    }

    public void setJyzh(String jyzh) {
        this.jyzh = jyzh;
    }

    @Basic
    @Column(name = "JYZHZT", nullable = true)
    public Byte getJyzhzt() {
        return jyzhzt;
    }

    public void setJyzhzt(Byte jyzhzt) {
        this.jyzhzt = jyzhzt;
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

        AppBizJyjbxx that = (AppBizJyjbxx) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (xm != null ? !xm.equals(that.xm) : that.xm != null) return false;
        if (xb != null ? !xb.equals(that.xb) : that.xb != null) return false;
        if (sfz != null ? !sfz.equals(that.sfz) : that.sfz != null) return false;
        if (jyh != null ? !jyh.equals(that.jyh) : that.jyh != null) return false;
        if (sjh != null ? !sjh.equals(that.sjh) : that.sjh != null) return false;
        if (tx != null ? !tx.equals(that.tx) : that.tx != null) return false;
        if (ssjgmc != null ? !ssjgmc.equals(that.ssjgmc) : that.ssjgmc != null) return false;
        if (ssjgdm != null ? !ssjgdm.equals(that.ssjgdm) : that.ssjgdm != null) return false;
        if (gxcsid != null ? !gxcsid.equals(that.gxcsid) : that.gxcsid != null) return false;
        if (gxcsmc != null ? !gxcsmc.equals(that.gxcsmc) : that.gxcsmc != null) return false;
        if (gxcslx != null ? !gxcslx.equals(that.gxcslx) : that.gxcslx != null) return false;
        if (gxcslxdm != null ? !gxcslxdm.equals(that.gxcslxdm) : that.gxcslxdm != null) return false;
        if (jyzh != null ? !jyzh.equals(that.jyzh) : that.jyzh != null) return false;
        if (jyzhzt != null ? !jyzhzt.equals(that.jyzhzt) : that.jyzhzt != null) return false;
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
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (xb != null ? xb.hashCode() : 0);
        result = 31 * result + (sfz != null ? sfz.hashCode() : 0);
        result = 31 * result + (jyh != null ? jyh.hashCode() : 0);
        result = 31 * result + (sjh != null ? sjh.hashCode() : 0);
        result = 31 * result + (tx != null ? tx.hashCode() : 0);
        result = 31 * result + (ssjgmc != null ? ssjgmc.hashCode() : 0);
        result = 31 * result + (ssjgdm != null ? ssjgdm.hashCode() : 0);
        result = 31 * result + (gxcsid != null ? gxcsid.hashCode() : 0);
        result = 31 * result + (gxcsmc != null ? gxcsmc.hashCode() : 0);
        result = 31 * result + (gxcslx != null ? gxcslx.hashCode() : 0);
        result = 31 * result + (gxcslxdm != null ? gxcslxdm.hashCode() : 0);
        result = 31 * result + (jyzh != null ? jyzh.hashCode() : 0);
        result = 31 * result + (jyzhzt != null ? jyzhzt.hashCode() : 0);
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
