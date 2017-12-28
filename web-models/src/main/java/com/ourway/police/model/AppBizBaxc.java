package com.ourway.police.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_baxc", schema = "public_manage", catalog = "")
public class AppBizBaxc {
    private String owid;
    private String appOwid;
    private String baid;
    private String baryxm;
    private String szqymc;
    private String taskDetail;
    private String taskName;
    private BigInteger rwzt;
    private String sjd;
    private String sjddm;
    private Byte sfczyc;
    private String ycxq;
    private Date signtime;
    private Date jcsjd;
    private String fromwho;
    private Byte fromtype;
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
    @Column(name = "BAID", nullable = true, length = 64)
    public String getBaid() {
        return baid;
    }

    public void setBaid(String baid) {
        this.baid = baid;
    }

    @Basic
    @Column(name = "BARYXM", nullable = true, length = 64)
    public String getBaryxm() {
        return baryxm;
    }

    public void setBaryxm(String baryxm) {
        this.baryxm = baryxm;
    }

    @Basic
    @Column(name = "SZQYMC", nullable = true, length = 120)
    public String getSzqymc() {
        return szqymc;
    }

    public void setSzqymc(String szqymc) {
        this.szqymc = szqymc;
    }

    @Basic
    @Column(name = "TASK_DETAIL", nullable = true, length = 1000)
    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    @Basic
    @Column(name = "TASK_NAME", nullable = true, length = 100)
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "RWZT", nullable = true, precision = 0)
    public BigInteger getRwzt() {
        return rwzt;
    }

    public void setRwzt(BigInteger rwzt) {
        this.rwzt = rwzt;
    }

    @Basic
    @Column(name = "SJD", nullable = true, length = 30)
    public String getSjd() {
        return sjd;
    }

    public void setSjd(String sjd) {
        this.sjd = sjd;
    }

    @Basic
    @Column(name = "SJDDM", nullable = true, length = 64)
    public String getSjddm() {
        return sjddm;
    }

    public void setSjddm(String sjddm) {
        this.sjddm = sjddm;
    }

    @Basic
    @Column(name = "SFCZYC", nullable = true)
    public Byte getSfczyc() {
        return sfczyc;
    }

    public void setSfczyc(Byte sfczyc) {
        this.sfczyc = sfczyc;
    }

    @Basic
    @Column(name = "YCXQ", nullable = true, length = 1000)
    public String getYcxq() {
        return ycxq;
    }

    public void setYcxq(String ycxq) {
        this.ycxq = ycxq;
    }

    @Basic
    @Column(name = "SIGNTIME", nullable = true)
    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    @Basic
    @Column(name = "JCSJD", nullable = true)
    public Date getJcsjd() {
        return jcsjd;
    }

    public void setJcsjd(Date jcsjd) {
        this.jcsjd = jcsjd;
    }

    @Basic
    @Column(name = "FROMWHO", nullable = true, length = 50)
    public String getFromwho() {
        return fromwho;
    }

    public void setFromwho(String fromwho) {
        this.fromwho = fromwho;
    }

    @Basic
    @Column(name = "FROMTYPE", nullable = true)
    public Byte getFromtype() {
        return fromtype;
    }

    public void setFromtype(Byte fromtype) {
        this.fromtype = fromtype;
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

        AppBizBaxc that = (AppBizBaxc) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (baid != null ? !baid.equals(that.baid) : that.baid != null) return false;
        if (baryxm != null ? !baryxm.equals(that.baryxm) : that.baryxm != null) return false;
        if (szqymc != null ? !szqymc.equals(that.szqymc) : that.szqymc != null) return false;
        if (taskDetail != null ? !taskDetail.equals(that.taskDetail) : that.taskDetail != null) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (rwzt != null ? !rwzt.equals(that.rwzt) : that.rwzt != null) return false;
        if (sjd != null ? !sjd.equals(that.sjd) : that.sjd != null) return false;
        if (sjddm != null ? !sjddm.equals(that.sjddm) : that.sjddm != null) return false;
        if (sfczyc != null ? !sfczyc.equals(that.sfczyc) : that.sfczyc != null) return false;
        if (ycxq != null ? !ycxq.equals(that.ycxq) : that.ycxq != null) return false;
        if (signtime != null ? !signtime.equals(that.signtime) : that.signtime != null) return false;
        if (jcsjd != null ? !jcsjd.equals(that.jcsjd) : that.jcsjd != null) return false;
        if (fromwho != null ? !fromwho.equals(that.fromwho) : that.fromwho != null) return false;
        if (fromtype != null ? !fromtype.equals(that.fromtype) : that.fromtype != null) return false;
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
        result = 31 * result + (baid != null ? baid.hashCode() : 0);
        result = 31 * result + (baryxm != null ? baryxm.hashCode() : 0);
        result = 31 * result + (szqymc != null ? szqymc.hashCode() : 0);
        result = 31 * result + (taskDetail != null ? taskDetail.hashCode() : 0);
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (rwzt != null ? rwzt.hashCode() : 0);
        result = 31 * result + (sjd != null ? sjd.hashCode() : 0);
        result = 31 * result + (sjddm != null ? sjddm.hashCode() : 0);
        result = 31 * result + (sfczyc != null ? sfczyc.hashCode() : 0);
        result = 31 * result + (ycxq != null ? ycxq.hashCode() : 0);
        result = 31 * result + (signtime != null ? signtime.hashCode() : 0);
        result = 31 * result + (jcsjd != null ? jcsjd.hashCode() : 0);
        result = 31 * result + (fromwho != null ? fromwho.hashCode() : 0);
        result = 31 * result + (fromtype != null ? fromtype.hashCode() : 0);
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
