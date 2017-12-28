package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_bjxxb", schema = "public_manage", catalog = "")
public class AppBizBjxxb {
    private String owid;
    private String appOwid;
    private String ssqymc;
    private String ssqydz;
    private String gxdwbm;
    private String gxdwmc;
    private String bjlxdm;
    private String bjlx;
    private Timestamp bjsj;
    private String bdbm;
    private String ywbm;
    private String hylbdm;
    private String hylb;
    private String xm;
    private String gmsfhm;
    private String jyaq;
    private String ladwbm;
    private String ladwmc;
    private Integer gxbbh;
    private String ywlbdm;
    private String ywlb;
    private String pjbmbm;
    private String pjbmmc;
    private String pjr;
    private Timestamp pjsj;
    private String jqztdm;
    private String jqzt;
    private String cjbmbm;
    private String cjbm;
    private Timestamp chjsj;
    private String cjfzr;
    private String lxdh;
    private String cjfklbdm;
    private String cjfklb;
    private String cjfkbz;
    private String fkdwbm;
    private String fkdw;
    private Timestamp fkrq;
    private String cjjg;
    private String cjjgdm;
    private String chjjg;
    private Timestamp zhsj;
    private String wxyydm;
    private String wxyy;
    private Byte sfyxbj;
    private String fkr;
    private String ywbzj;
    private String bdbzj;
    private String fjh;
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
    @Column(name = "SSQYMC", nullable = true, length = 100)
    public String getSsqymc() {
        return ssqymc;
    }

    public void setSsqymc(String ssqymc) {
        this.ssqymc = ssqymc;
    }

    @Basic
    @Column(name = "SSQYDZ", nullable = true, length = 200)
    public String getSsqydz() {
        return ssqydz;
    }

    public void setSsqydz(String ssqydz) {
        this.ssqydz = ssqydz;
    }

    @Basic
    @Column(name = "GXDWBM", nullable = true, length = 64)
    public String getGxdwbm() {
        return gxdwbm;
    }

    public void setGxdwbm(String gxdwbm) {
        this.gxdwbm = gxdwbm;
    }

    @Basic
    @Column(name = "GXDWMC", nullable = true, length = 60)
    public String getGxdwmc() {
        return gxdwmc;
    }

    public void setGxdwmc(String gxdwmc) {
        this.gxdwmc = gxdwmc;
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
    @Column(name = "BJSJ", nullable = true)
    public Timestamp getBjsj() {
        return bjsj;
    }

    public void setBjsj(Timestamp bjsj) {
        this.bjsj = bjsj;
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
    @Column(name = "YWBM", nullable = true, length = 100)
    public String getYwbm() {
        return ywbm;
    }

    public void setYwbm(String ywbm) {
        this.ywbm = ywbm;
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
    @Column(name = "XM", nullable = true, length = 100)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Basic
    @Column(name = "GMSFHM", nullable = true, length = 20)
    public String getGmsfhm() {
        return gmsfhm;
    }

    public void setGmsfhm(String gmsfhm) {
        this.gmsfhm = gmsfhm;
    }

    @Basic
    @Column(name = "JYAQ", nullable = true, length = 2000)
    public String getJyaq() {
        return jyaq;
    }

    public void setJyaq(String jyaq) {
        this.jyaq = jyaq;
    }

    @Basic
    @Column(name = "LADWBM", nullable = true, length = 64)
    public String getLadwbm() {
        return ladwbm;
    }

    public void setLadwbm(String ladwbm) {
        this.ladwbm = ladwbm;
    }

    @Basic
    @Column(name = "LADWMC", nullable = true, length = 100)
    public String getLadwmc() {
        return ladwmc;
    }

    public void setLadwmc(String ladwmc) {
        this.ladwmc = ladwmc;
    }

    @Basic
    @Column(name = "GXBBH", nullable = true, precision = 0)
    public Integer getGxbbh() {
        return gxbbh;
    }

    public void setGxbbh(Integer gxbbh) {
        this.gxbbh = gxbbh;
    }

    @Basic
    @Column(name = "YWLBDM", nullable = true, length = 64)
    public String getYwlbdm() {
        return ywlbdm;
    }

    public void setYwlbdm(String ywlbdm) {
        this.ywlbdm = ywlbdm;
    }

    @Basic
    @Column(name = "YWLB", nullable = true, length = 30)
    public String getYwlb() {
        return ywlb;
    }

    public void setYwlb(String ywlb) {
        this.ywlb = ywlb;
    }

    @Basic
    @Column(name = "PJBMBM", nullable = true, length = 64)
    public String getPjbmbm() {
        return pjbmbm;
    }

    public void setPjbmbm(String pjbmbm) {
        this.pjbmbm = pjbmbm;
    }

    @Basic
    @Column(name = "PJBMMC", nullable = true, length = 100)
    public String getPjbmmc() {
        return pjbmmc;
    }

    public void setPjbmmc(String pjbmmc) {
        this.pjbmmc = pjbmmc;
    }

    @Basic
    @Column(name = "PJR", nullable = true, length = 100)
    public String getPjr() {
        return pjr;
    }

    public void setPjr(String pjr) {
        this.pjr = pjr;
    }

    @Basic
    @Column(name = "PJSJ", nullable = true)
    public Timestamp getPjsj() {
        return pjsj;
    }

    public void setPjsj(Timestamp pjsj) {
        this.pjsj = pjsj;
    }

    @Basic
    @Column(name = "JQZTDM", nullable = true, length = 64)
    public String getJqztdm() {
        return jqztdm;
    }

    public void setJqztdm(String jqztdm) {
        this.jqztdm = jqztdm;
    }

    @Basic
    @Column(name = "JQZT", nullable = true, length = 10)
    public String getJqzt() {
        return jqzt;
    }

    public void setJqzt(String jqzt) {
        this.jqzt = jqzt;
    }

    @Basic
    @Column(name = "CJBMBM", nullable = true, length = 64)
    public String getCjbmbm() {
        return cjbmbm;
    }

    public void setCjbmbm(String cjbmbm) {
        this.cjbmbm = cjbmbm;
    }

    @Basic
    @Column(name = "CJBM", nullable = true, length = 100)
    public String getCjbm() {
        return cjbm;
    }

    public void setCjbm(String cjbm) {
        this.cjbm = cjbm;
    }

    @Basic
    @Column(name = "CHJSJ", nullable = true)
    public Timestamp getChjsj() {
        return chjsj;
    }

    public void setChjsj(Timestamp chjsj) {
        this.chjsj = chjsj;
    }

    @Basic
    @Column(name = "CJFZR", nullable = true, length = 100)
    public String getCjfzr() {
        return cjfzr;
    }

    public void setCjfzr(String cjfzr) {
        this.cjfzr = cjfzr;
    }

    @Basic
    @Column(name = "LXDH", nullable = true, length = 30)
    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    @Basic
    @Column(name = "CJFKLBDM", nullable = true, length = 64)
    public String getCjfklbdm() {
        return cjfklbdm;
    }

    public void setCjfklbdm(String cjfklbdm) {
        this.cjfklbdm = cjfklbdm;
    }

    @Basic
    @Column(name = "CJFKLB", nullable = true, length = 60)
    public String getCjfklb() {
        return cjfklb;
    }

    public void setCjfklb(String cjfklb) {
        this.cjfklb = cjfklb;
    }

    @Basic
    @Column(name = "CJFKBZ", nullable = true, length = 2000)
    public String getCjfkbz() {
        return cjfkbz;
    }

    public void setCjfkbz(String cjfkbz) {
        this.cjfkbz = cjfkbz;
    }

    @Basic
    @Column(name = "FKDWBM", nullable = true, length = 64)
    public String getFkdwbm() {
        return fkdwbm;
    }

    public void setFkdwbm(String fkdwbm) {
        this.fkdwbm = fkdwbm;
    }

    @Basic
    @Column(name = "FKDW", nullable = true, length = 100)
    public String getFkdw() {
        return fkdw;
    }

    public void setFkdw(String fkdw) {
        this.fkdw = fkdw;
    }

    @Basic
    @Column(name = "FKRQ", nullable = true)
    public Timestamp getFkrq() {
        return fkrq;
    }

    public void setFkrq(Timestamp fkrq) {
        this.fkrq = fkrq;
    }

    @Basic
    @Column(name = "CJJG", nullable = true, length = 2000)
    public String getCjjg() {
        return cjjg;
    }

    public void setCjjg(String cjjg) {
        this.cjjg = cjjg;
    }

    @Basic
    @Column(name = "CJJGDM", nullable = true, length = 64)
    public String getCjjgdm() {
        return cjjgdm;
    }

    public void setCjjgdm(String cjjgdm) {
        this.cjjgdm = cjjgdm;
    }

    @Basic
    @Column(name = "CHJJG", nullable = true, length = 60)
    public String getChjjg() {
        return chjjg;
    }

    public void setChjjg(String chjjg) {
        this.chjjg = chjjg;
    }

    @Basic
    @Column(name = "ZHSJ", nullable = true)
    public Timestamp getZhsj() {
        return zhsj;
    }

    public void setZhsj(Timestamp zhsj) {
        this.zhsj = zhsj;
    }

    @Basic
    @Column(name = "WXYYDM", nullable = true, length = 64)
    public String getWxyydm() {
        return wxyydm;
    }

    public void setWxyydm(String wxyydm) {
        this.wxyydm = wxyydm;
    }

    @Basic
    @Column(name = "WXYY", nullable = true, length = 60)
    public String getWxyy() {
        return wxyy;
    }

    public void setWxyy(String wxyy) {
        this.wxyy = wxyy;
    }

    @Basic
    @Column(name = "SFYXBJ", nullable = true)
    public Byte getSfyxbj() {
        return sfyxbj;
    }

    public void setSfyxbj(Byte sfyxbj) {
        this.sfyxbj = sfyxbj;
    }

    @Basic
    @Column(name = "FKR", nullable = true, length = 60)
    public String getFkr() {
        return fkr;
    }

    public void setFkr(String fkr) {
        this.fkr = fkr;
    }

    @Basic
    @Column(name = "YWBZJ", nullable = true, length = 64)
    public String getYwbzj() {
        return ywbzj;
    }

    public void setYwbzj(String ywbzj) {
        this.ywbzj = ywbzj;
    }

    @Basic
    @Column(name = "BDBZJ", nullable = true, length = 64)
    public String getBdbzj() {
        return bdbzj;
    }

    public void setBdbzj(String bdbzj) {
        this.bdbzj = bdbzj;
    }

    @Basic
    @Column(name = "FJH", nullable = true, length = 20)
    public String getFjh() {
        return fjh;
    }

    public void setFjh(String fjh) {
        this.fjh = fjh;
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

        AppBizBjxxb that = (AppBizBjxxb) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (ssqymc != null ? !ssqymc.equals(that.ssqymc) : that.ssqymc != null) return false;
        if (ssqydz != null ? !ssqydz.equals(that.ssqydz) : that.ssqydz != null) return false;
        if (gxdwbm != null ? !gxdwbm.equals(that.gxdwbm) : that.gxdwbm != null) return false;
        if (gxdwmc != null ? !gxdwmc.equals(that.gxdwmc) : that.gxdwmc != null) return false;
        if (bjlxdm != null ? !bjlxdm.equals(that.bjlxdm) : that.bjlxdm != null) return false;
        if (bjlx != null ? !bjlx.equals(that.bjlx) : that.bjlx != null) return false;
        if (bjsj != null ? !bjsj.equals(that.bjsj) : that.bjsj != null) return false;
        if (bdbm != null ? !bdbm.equals(that.bdbm) : that.bdbm != null) return false;
        if (ywbm != null ? !ywbm.equals(that.ywbm) : that.ywbm != null) return false;
        if (hylbdm != null ? !hylbdm.equals(that.hylbdm) : that.hylbdm != null) return false;
        if (hylb != null ? !hylb.equals(that.hylb) : that.hylb != null) return false;
        if (xm != null ? !xm.equals(that.xm) : that.xm != null) return false;
        if (gmsfhm != null ? !gmsfhm.equals(that.gmsfhm) : that.gmsfhm != null) return false;
        if (jyaq != null ? !jyaq.equals(that.jyaq) : that.jyaq != null) return false;
        if (ladwbm != null ? !ladwbm.equals(that.ladwbm) : that.ladwbm != null) return false;
        if (ladwmc != null ? !ladwmc.equals(that.ladwmc) : that.ladwmc != null) return false;
        if (gxbbh != null ? !gxbbh.equals(that.gxbbh) : that.gxbbh != null) return false;
        if (ywlbdm != null ? !ywlbdm.equals(that.ywlbdm) : that.ywlbdm != null) return false;
        if (ywlb != null ? !ywlb.equals(that.ywlb) : that.ywlb != null) return false;
        if (pjbmbm != null ? !pjbmbm.equals(that.pjbmbm) : that.pjbmbm != null) return false;
        if (pjbmmc != null ? !pjbmmc.equals(that.pjbmmc) : that.pjbmmc != null) return false;
        if (pjr != null ? !pjr.equals(that.pjr) : that.pjr != null) return false;
        if (pjsj != null ? !pjsj.equals(that.pjsj) : that.pjsj != null) return false;
        if (jqztdm != null ? !jqztdm.equals(that.jqztdm) : that.jqztdm != null) return false;
        if (jqzt != null ? !jqzt.equals(that.jqzt) : that.jqzt != null) return false;
        if (cjbmbm != null ? !cjbmbm.equals(that.cjbmbm) : that.cjbmbm != null) return false;
        if (cjbm != null ? !cjbm.equals(that.cjbm) : that.cjbm != null) return false;
        if (chjsj != null ? !chjsj.equals(that.chjsj) : that.chjsj != null) return false;
        if (cjfzr != null ? !cjfzr.equals(that.cjfzr) : that.cjfzr != null) return false;
        if (lxdh != null ? !lxdh.equals(that.lxdh) : that.lxdh != null) return false;
        if (cjfklbdm != null ? !cjfklbdm.equals(that.cjfklbdm) : that.cjfklbdm != null) return false;
        if (cjfklb != null ? !cjfklb.equals(that.cjfklb) : that.cjfklb != null) return false;
        if (cjfkbz != null ? !cjfkbz.equals(that.cjfkbz) : that.cjfkbz != null) return false;
        if (fkdwbm != null ? !fkdwbm.equals(that.fkdwbm) : that.fkdwbm != null) return false;
        if (fkdw != null ? !fkdw.equals(that.fkdw) : that.fkdw != null) return false;
        if (fkrq != null ? !fkrq.equals(that.fkrq) : that.fkrq != null) return false;
        if (cjjg != null ? !cjjg.equals(that.cjjg) : that.cjjg != null) return false;
        if (cjjgdm != null ? !cjjgdm.equals(that.cjjgdm) : that.cjjgdm != null) return false;
        if (chjjg != null ? !chjjg.equals(that.chjjg) : that.chjjg != null) return false;
        if (zhsj != null ? !zhsj.equals(that.zhsj) : that.zhsj != null) return false;
        if (wxyydm != null ? !wxyydm.equals(that.wxyydm) : that.wxyydm != null) return false;
        if (wxyy != null ? !wxyy.equals(that.wxyy) : that.wxyy != null) return false;
        if (sfyxbj != null ? !sfyxbj.equals(that.sfyxbj) : that.sfyxbj != null) return false;
        if (fkr != null ? !fkr.equals(that.fkr) : that.fkr != null) return false;
        if (ywbzj != null ? !ywbzj.equals(that.ywbzj) : that.ywbzj != null) return false;
        if (bdbzj != null ? !bdbzj.equals(that.bdbzj) : that.bdbzj != null) return false;
        if (fjh != null ? !fjh.equals(that.fjh) : that.fjh != null) return false;
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
        result = 31 * result + (ssqymc != null ? ssqymc.hashCode() : 0);
        result = 31 * result + (ssqydz != null ? ssqydz.hashCode() : 0);
        result = 31 * result + (gxdwbm != null ? gxdwbm.hashCode() : 0);
        result = 31 * result + (gxdwmc != null ? gxdwmc.hashCode() : 0);
        result = 31 * result + (bjlxdm != null ? bjlxdm.hashCode() : 0);
        result = 31 * result + (bjlx != null ? bjlx.hashCode() : 0);
        result = 31 * result + (bjsj != null ? bjsj.hashCode() : 0);
        result = 31 * result + (bdbm != null ? bdbm.hashCode() : 0);
        result = 31 * result + (ywbm != null ? ywbm.hashCode() : 0);
        result = 31 * result + (hylbdm != null ? hylbdm.hashCode() : 0);
        result = 31 * result + (hylb != null ? hylb.hashCode() : 0);
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (gmsfhm != null ? gmsfhm.hashCode() : 0);
        result = 31 * result + (jyaq != null ? jyaq.hashCode() : 0);
        result = 31 * result + (ladwbm != null ? ladwbm.hashCode() : 0);
        result = 31 * result + (ladwmc != null ? ladwmc.hashCode() : 0);
        result = 31 * result + (gxbbh != null ? gxbbh.hashCode() : 0);
        result = 31 * result + (ywlbdm != null ? ywlbdm.hashCode() : 0);
        result = 31 * result + (ywlb != null ? ywlb.hashCode() : 0);
        result = 31 * result + (pjbmbm != null ? pjbmbm.hashCode() : 0);
        result = 31 * result + (pjbmmc != null ? pjbmmc.hashCode() : 0);
        result = 31 * result + (pjr != null ? pjr.hashCode() : 0);
        result = 31 * result + (pjsj != null ? pjsj.hashCode() : 0);
        result = 31 * result + (jqztdm != null ? jqztdm.hashCode() : 0);
        result = 31 * result + (jqzt != null ? jqzt.hashCode() : 0);
        result = 31 * result + (cjbmbm != null ? cjbmbm.hashCode() : 0);
        result = 31 * result + (cjbm != null ? cjbm.hashCode() : 0);
        result = 31 * result + (chjsj != null ? chjsj.hashCode() : 0);
        result = 31 * result + (cjfzr != null ? cjfzr.hashCode() : 0);
        result = 31 * result + (lxdh != null ? lxdh.hashCode() : 0);
        result = 31 * result + (cjfklbdm != null ? cjfklbdm.hashCode() : 0);
        result = 31 * result + (cjfklb != null ? cjfklb.hashCode() : 0);
        result = 31 * result + (cjfkbz != null ? cjfkbz.hashCode() : 0);
        result = 31 * result + (fkdwbm != null ? fkdwbm.hashCode() : 0);
        result = 31 * result + (fkdw != null ? fkdw.hashCode() : 0);
        result = 31 * result + (fkrq != null ? fkrq.hashCode() : 0);
        result = 31 * result + (cjjg != null ? cjjg.hashCode() : 0);
        result = 31 * result + (cjjgdm != null ? cjjgdm.hashCode() : 0);
        result = 31 * result + (chjjg != null ? chjjg.hashCode() : 0);
        result = 31 * result + (zhsj != null ? zhsj.hashCode() : 0);
        result = 31 * result + (wxyydm != null ? wxyydm.hashCode() : 0);
        result = 31 * result + (wxyy != null ? wxyy.hashCode() : 0);
        result = 31 * result + (sfyxbj != null ? sfyxbj.hashCode() : 0);
        result = 31 * result + (fkr != null ? fkr.hashCode() : 0);
        result = 31 * result + (ywbzj != null ? ywbzj.hashCode() : 0);
        result = 31 * result + (bdbzj != null ? bdbzj.hashCode() : 0);
        result = 31 * result + (fjh != null ? fjh.hashCode() : 0);
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
