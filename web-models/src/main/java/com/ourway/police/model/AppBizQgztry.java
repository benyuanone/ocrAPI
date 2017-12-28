package com.ourway.police.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_qgztry", schema = "public_manage", catalog = "")
public class AppBizQgztry {
    private String owid;
    private String tbbh;
    private String xzqhbm;
    private String xzqh;
    private String xmpy;
    private String xm;
    private String bm;
    private Byte xb;
    private Timestamp csrq;
    private String gmsfhm;
    private String hjdbm;
    private String hjd;
    private String hjxxdz;
    private String xzdqbm;
    private String xzdq;
    private String xzdxxdz;
    private String qtzjlx;
    private String qtzjhm;
    private String ajjbbm;
    private String ajjb;
    private String ajlbbm;
    private String ajlb;
    private String jyaq;
    private Timestamp djrq;
    private String djr;
    private String ladqbm;
    private String ladq;
    private String badwbm;
    private String badw;
    private String tbr;
    private String lxr;
    private String lxfs;
    private String shengao;
    private String rwxx;
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
    @Column(name = "TBBH", nullable = true, length = 64)
    public String getTbbh() {
        return tbbh;
    }

    public void setTbbh(String tbbh) {
        this.tbbh = tbbh;
    }

    @Basic
    @Column(name = "XZQHBM", nullable = true, length = 64)
    public String getXzqhbm() {
        return xzqhbm;
    }

    public void setXzqhbm(String xzqhbm) {
        this.xzqhbm = xzqhbm;
    }

    @Basic
    @Column(name = "XZQH", nullable = true, length = 100)
    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    @Basic
    @Column(name = "XMPY", nullable = true, length = 200)
    public String getXmpy() {
        return xmpy;
    }

    public void setXmpy(String xmpy) {
        this.xmpy = xmpy;
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
    @Column(name = "BM", nullable = true, length = 100)
    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
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
    @Column(name = "CSRQ", nullable = true)
    public Timestamp getCsrq() {
        return csrq;
    }

    public void setCsrq(Timestamp csrq) {
        this.csrq = csrq;
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
    @Column(name = "HJDBM", nullable = true, length = 64)
    public String getHjdbm() {
        return hjdbm;
    }

    public void setHjdbm(String hjdbm) {
        this.hjdbm = hjdbm;
    }

    @Basic
    @Column(name = "HJD", nullable = true, length = 300)
    public String getHjd() {
        return hjd;
    }

    public void setHjd(String hjd) {
        this.hjd = hjd;
    }

    @Basic
    @Column(name = "HJXXDZ", nullable = true, length = 300)
    public String getHjxxdz() {
        return hjxxdz;
    }

    public void setHjxxdz(String hjxxdz) {
        this.hjxxdz = hjxxdz;
    }

    @Basic
    @Column(name = "XZDQBM", nullable = true, length = 64)
    public String getXzdqbm() {
        return xzdqbm;
    }

    public void setXzdqbm(String xzdqbm) {
        this.xzdqbm = xzdqbm;
    }

    @Basic
    @Column(name = "XZDQ", nullable = true, length = 300)
    public String getXzdq() {
        return xzdq;
    }

    public void setXzdq(String xzdq) {
        this.xzdq = xzdq;
    }

    @Basic
    @Column(name = "XZDXXDZ", nullable = true, length = 300)
    public String getXzdxxdz() {
        return xzdxxdz;
    }

    public void setXzdxxdz(String xzdxxdz) {
        this.xzdxxdz = xzdxxdz;
    }

    @Basic
    @Column(name = "QTZJLX", nullable = true, length = 120)
    public String getQtzjlx() {
        return qtzjlx;
    }

    public void setQtzjlx(String qtzjlx) {
        this.qtzjlx = qtzjlx;
    }

    @Basic
    @Column(name = "QTZJHM", nullable = true, length = 200)
    public String getQtzjhm() {
        return qtzjhm;
    }

    public void setQtzjhm(String qtzjhm) {
        this.qtzjhm = qtzjhm;
    }

    @Basic
    @Column(name = "AJJBBM", nullable = true, length = 64)
    public String getAjjbbm() {
        return ajjbbm;
    }

    public void setAjjbbm(String ajjbbm) {
        this.ajjbbm = ajjbbm;
    }

    @Basic
    @Column(name = "AJJB", nullable = true, length = 20)
    public String getAjjb() {
        return ajjb;
    }

    public void setAjjb(String ajjb) {
        this.ajjb = ajjb;
    }

    @Basic
    @Column(name = "AJLBBM", nullable = true, length = 64)
    public String getAjlbbm() {
        return ajlbbm;
    }

    public void setAjlbbm(String ajlbbm) {
        this.ajlbbm = ajlbbm;
    }

    @Basic
    @Column(name = "AJLB", nullable = true, length = 60)
    public String getAjlb() {
        return ajlb;
    }

    public void setAjlb(String ajlb) {
        this.ajlb = ajlb;
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
    @Column(name = "DJRQ", nullable = true)
    public Timestamp getDjrq() {
        return djrq;
    }

    public void setDjrq(Timestamp djrq) {
        this.djrq = djrq;
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
    @Column(name = "LADQBM", nullable = true, length = 64)
    public String getLadqbm() {
        return ladqbm;
    }

    public void setLadqbm(String ladqbm) {
        this.ladqbm = ladqbm;
    }

    @Basic
    @Column(name = "LADQ", nullable = true, length = 300)
    public String getLadq() {
        return ladq;
    }

    public void setLadq(String ladq) {
        this.ladq = ladq;
    }

    @Basic
    @Column(name = "BADWBM", nullable = true, length = 64)
    public String getBadwbm() {
        return badwbm;
    }

    public void setBadwbm(String badwbm) {
        this.badwbm = badwbm;
    }

    @Basic
    @Column(name = "BADW", nullable = true, length = 100)
    public String getBadw() {
        return badw;
    }

    public void setBadw(String badw) {
        this.badw = badw;
    }

    @Basic
    @Column(name = "TBR", nullable = true, length = 100)
    public String getTbr() {
        return tbr;
    }

    public void setTbr(String tbr) {
        this.tbr = tbr;
    }

    @Basic
    @Column(name = "LXR", nullable = true, length = 100)
    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    @Basic
    @Column(name = "LXFS", nullable = true, length = 300)
    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    @Basic
    @Column(name = "SHENGAO", nullable = true, length = 30)
    public String getShengao() {
        return shengao;
    }

    public void setShengao(String shengao) {
        this.shengao = shengao;
    }

    @Basic
    @Column(name = "RWXX", nullable = true, length = 64)
    public String getRwxx() {
        return rwxx;
    }

    public void setRwxx(String rwxx) {
        this.rwxx = rwxx;
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

        AppBizQgztry that = (AppBizQgztry) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (tbbh != null ? !tbbh.equals(that.tbbh) : that.tbbh != null) return false;
        if (xzqhbm != null ? !xzqhbm.equals(that.xzqhbm) : that.xzqhbm != null) return false;
        if (xzqh != null ? !xzqh.equals(that.xzqh) : that.xzqh != null) return false;
        if (xmpy != null ? !xmpy.equals(that.xmpy) : that.xmpy != null) return false;
        if (xm != null ? !xm.equals(that.xm) : that.xm != null) return false;
        if (bm != null ? !bm.equals(that.bm) : that.bm != null) return false;
        if (xb != null ? !xb.equals(that.xb) : that.xb != null) return false;
        if (csrq != null ? !csrq.equals(that.csrq) : that.csrq != null) return false;
        if (gmsfhm != null ? !gmsfhm.equals(that.gmsfhm) : that.gmsfhm != null) return false;
        if (hjdbm != null ? !hjdbm.equals(that.hjdbm) : that.hjdbm != null) return false;
        if (hjd != null ? !hjd.equals(that.hjd) : that.hjd != null) return false;
        if (hjxxdz != null ? !hjxxdz.equals(that.hjxxdz) : that.hjxxdz != null) return false;
        if (xzdqbm != null ? !xzdqbm.equals(that.xzdqbm) : that.xzdqbm != null) return false;
        if (xzdq != null ? !xzdq.equals(that.xzdq) : that.xzdq != null) return false;
        if (xzdxxdz != null ? !xzdxxdz.equals(that.xzdxxdz) : that.xzdxxdz != null) return false;
        if (qtzjlx != null ? !qtzjlx.equals(that.qtzjlx) : that.qtzjlx != null) return false;
        if (qtzjhm != null ? !qtzjhm.equals(that.qtzjhm) : that.qtzjhm != null) return false;
        if (ajjbbm != null ? !ajjbbm.equals(that.ajjbbm) : that.ajjbbm != null) return false;
        if (ajjb != null ? !ajjb.equals(that.ajjb) : that.ajjb != null) return false;
        if (ajlbbm != null ? !ajlbbm.equals(that.ajlbbm) : that.ajlbbm != null) return false;
        if (ajlb != null ? !ajlb.equals(that.ajlb) : that.ajlb != null) return false;
        if (jyaq != null ? !jyaq.equals(that.jyaq) : that.jyaq != null) return false;
        if (djrq != null ? !djrq.equals(that.djrq) : that.djrq != null) return false;
        if (djr != null ? !djr.equals(that.djr) : that.djr != null) return false;
        if (ladqbm != null ? !ladqbm.equals(that.ladqbm) : that.ladqbm != null) return false;
        if (ladq != null ? !ladq.equals(that.ladq) : that.ladq != null) return false;
        if (badwbm != null ? !badwbm.equals(that.badwbm) : that.badwbm != null) return false;
        if (badw != null ? !badw.equals(that.badw) : that.badw != null) return false;
        if (tbr != null ? !tbr.equals(that.tbr) : that.tbr != null) return false;
        if (lxr != null ? !lxr.equals(that.lxr) : that.lxr != null) return false;
        if (lxfs != null ? !lxfs.equals(that.lxfs) : that.lxfs != null) return false;
        if (shengao != null ? !shengao.equals(that.shengao) : that.shengao != null) return false;
        if (rwxx != null ? !rwxx.equals(that.rwxx) : that.rwxx != null) return false;
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
        result = 31 * result + (tbbh != null ? tbbh.hashCode() : 0);
        result = 31 * result + (xzqhbm != null ? xzqhbm.hashCode() : 0);
        result = 31 * result + (xzqh != null ? xzqh.hashCode() : 0);
        result = 31 * result + (xmpy != null ? xmpy.hashCode() : 0);
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (bm != null ? bm.hashCode() : 0);
        result = 31 * result + (xb != null ? xb.hashCode() : 0);
        result = 31 * result + (csrq != null ? csrq.hashCode() : 0);
        result = 31 * result + (gmsfhm != null ? gmsfhm.hashCode() : 0);
        result = 31 * result + (hjdbm != null ? hjdbm.hashCode() : 0);
        result = 31 * result + (hjd != null ? hjd.hashCode() : 0);
        result = 31 * result + (hjxxdz != null ? hjxxdz.hashCode() : 0);
        result = 31 * result + (xzdqbm != null ? xzdqbm.hashCode() : 0);
        result = 31 * result + (xzdq != null ? xzdq.hashCode() : 0);
        result = 31 * result + (xzdxxdz != null ? xzdxxdz.hashCode() : 0);
        result = 31 * result + (qtzjlx != null ? qtzjlx.hashCode() : 0);
        result = 31 * result + (qtzjhm != null ? qtzjhm.hashCode() : 0);
        result = 31 * result + (ajjbbm != null ? ajjbbm.hashCode() : 0);
        result = 31 * result + (ajjb != null ? ajjb.hashCode() : 0);
        result = 31 * result + (ajlbbm != null ? ajlbbm.hashCode() : 0);
        result = 31 * result + (ajlb != null ? ajlb.hashCode() : 0);
        result = 31 * result + (jyaq != null ? jyaq.hashCode() : 0);
        result = 31 * result + (djrq != null ? djrq.hashCode() : 0);
        result = 31 * result + (djr != null ? djr.hashCode() : 0);
        result = 31 * result + (ladqbm != null ? ladqbm.hashCode() : 0);
        result = 31 * result + (ladq != null ? ladq.hashCode() : 0);
        result = 31 * result + (badwbm != null ? badwbm.hashCode() : 0);
        result = 31 * result + (badw != null ? badw.hashCode() : 0);
        result = 31 * result + (tbr != null ? tbr.hashCode() : 0);
        result = 31 * result + (lxr != null ? lxr.hashCode() : 0);
        result = 31 * result + (lxfs != null ? lxfs.hashCode() : 0);
        result = 31 * result + (shengao != null ? shengao.hashCode() : 0);
        result = 31 * result + (rwxx != null ? rwxx.hashCode() : 0);
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
