package com.ourway.police.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_qyryxx", schema = "public_manage", catalog = "")
public class AppBizQyryxx {
    private String owid;
    private String appOwid;
    private String tx;
    private Byte sfkdl;
    private String hylbdm;
    private String hylb;
    private String cyrybh;
    private String cyrylbdm;
    private String cyrylbmc;
    private String xm;
    private String qm;
    private String bm;
    private String xmpy;
    private String gjdm;
    private String gj;
    private String mzdm;
    private String minzu;
    private String xbdm;
    private Byte xb;
    private Timestamp csrq;
    private String hyzkdm;
    private String hyzk;
    private BigDecimal shengao;
    private BigDecimal tizh;
    private String hjdxzqh;
    private String hjdxzqhdm;
    private String hjdxz;
    private String cyzjdm;
    private String cyzj;
    private String zjhm;
    private String zzzhm;
    private String zzdz;
    private String gwbh;
    private String gwmc;
    private String gwzrms;
    private String shouji;
    private String lxdh;
    private String jjlxr;
    private String jjlxrdh;
    private String gzdm;
    private String gz;
    private String zhiwu;
    private Timestamp rzrq;
    private String cyryztdm;
    private BigInteger cyryzt;
    private String zxr;
    private Byte zxbz;
    private Timestamp zxsj;
    private Timestamp lrsj;
    private String lrr;
    private String lrdwbm;
    private String lrdwmc;
    private String hcdw;
    private String hcr;
    private Timestamp hcsj;
    private String zxyy;
    private Byte zkzt;
    private Timestamp bcsj;
    private Byte dcbs;
    private String bz;
    private Byte scbz;
    private Byte biduiflag;
    private String zpwtgyy;
    private Byte zt;
    private Timestamp lzrq;
    private String xxdm;
    private String xx;
    private String cylbdm;
    private String cylb;
    private String whcddm;
    private String whcd;
    private Byte yjbz;
    private Timestamp yjsj;
    private String xzdxzqhdm;
    private String xzdxzqh;
    private Timestamp djrq;
    private String hjlbdm;
    private String hjlbmc;
    private String zzmmdm;
    private String zzmmmc;
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
    @Column(name = "TX", nullable = true, length = 64)
    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    @Basic
    @Column(name = "SFKDL", nullable = true)
    public Byte getSfkdl() {
        return sfkdl;
    }

    public void setSfkdl(Byte sfkdl) {
        this.sfkdl = sfkdl;
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
    @Column(name = "CYRYBH", nullable = true, length = 30)
    public String getCyrybh() {
        return cyrybh;
    }

    public void setCyrybh(String cyrybh) {
        this.cyrybh = cyrybh;
    }

    @Basic
    @Column(name = "CYRYLBDM", nullable = true, length = 64)
    public String getCyrylbdm() {
        return cyrylbdm;
    }

    public void setCyrylbdm(String cyrylbdm) {
        this.cyrylbdm = cyrylbdm;
    }

    @Basic
    @Column(name = "CYRYLBMC", nullable = true, length = 30)
    public String getCyrylbmc() {
        return cyrylbmc;
    }

    public void setCyrylbmc(String cyrylbmc) {
        this.cyrylbmc = cyrylbmc;
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
    @Column(name = "QM", nullable = true, length = 100)
    public String getQm() {
        return qm;
    }

    public void setQm(String qm) {
        this.qm = qm;
    }

    @Basic
    @Column(name = "BM", nullable = true, length = 30)
    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    @Basic
    @Column(name = "XMPY", nullable = true, length = 60)
    public String getXmpy() {
        return xmpy;
    }

    public void setXmpy(String xmpy) {
        this.xmpy = xmpy;
    }

    @Basic
    @Column(name = "GJDM", nullable = true, length = 64)
    public String getGjdm() {
        return gjdm;
    }

    public void setGjdm(String gjdm) {
        this.gjdm = gjdm;
    }

    @Basic
    @Column(name = "GJ", nullable = true, length = 100)
    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }

    @Basic
    @Column(name = "MZDM", nullable = true, length = 64)
    public String getMzdm() {
        return mzdm;
    }

    public void setMzdm(String mzdm) {
        this.mzdm = mzdm;
    }

    @Basic
    @Column(name = "MINZU", nullable = true, length = 20)
    public String getMinzu() {
        return minzu;
    }

    public void setMinzu(String minzu) {
        this.minzu = minzu;
    }

    @Basic
    @Column(name = "XBDM", nullable = true, length = 64)
    public String getXbdm() {
        return xbdm;
    }

    public void setXbdm(String xbdm) {
        this.xbdm = xbdm;
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
    @Column(name = "HYZKDM", nullable = true, length = 64)
    public String getHyzkdm() {
        return hyzkdm;
    }

    public void setHyzkdm(String hyzkdm) {
        this.hyzkdm = hyzkdm;
    }

    @Basic
    @Column(name = "HYZK", nullable = true, length = 4)
    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    @Basic
    @Column(name = "SHENGAO", nullable = true, precision = 2)
    public BigDecimal getShengao() {
        return shengao;
    }

    public void setShengao(BigDecimal shengao) {
        this.shengao = shengao;
    }

    @Basic
    @Column(name = "TIZH", nullable = true, precision = 2)
    public BigDecimal getTizh() {
        return tizh;
    }

    public void setTizh(BigDecimal tizh) {
        this.tizh = tizh;
    }

    @Basic
    @Column(name = "HJDXZQH", nullable = true, length = 200)
    public String getHjdxzqh() {
        return hjdxzqh;
    }

    public void setHjdxzqh(String hjdxzqh) {
        this.hjdxzqh = hjdxzqh;
    }

    @Basic
    @Column(name = "HJDXZQHDM", nullable = true, length = 64)
    public String getHjdxzqhdm() {
        return hjdxzqhdm;
    }

    public void setHjdxzqhdm(String hjdxzqhdm) {
        this.hjdxzqhdm = hjdxzqhdm;
    }

    @Basic
    @Column(name = "HJDXZ", nullable = true, length = 200)
    public String getHjdxz() {
        return hjdxz;
    }

    public void setHjdxz(String hjdxz) {
        this.hjdxz = hjdxz;
    }

    @Basic
    @Column(name = "CYZJDM", nullable = true, length = 64)
    public String getCyzjdm() {
        return cyzjdm;
    }

    public void setCyzjdm(String cyzjdm) {
        this.cyzjdm = cyzjdm;
    }

    @Basic
    @Column(name = "CYZJ", nullable = true, length = 100)
    public String getCyzj() {
        return cyzj;
    }

    public void setCyzj(String cyzj) {
        this.cyzj = cyzj;
    }

    @Basic
    @Column(name = "ZJHM", nullable = true, length = 20)
    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    @Basic
    @Column(name = "ZZZHM", nullable = true, length = 20)
    public String getZzzhm() {
        return zzzhm;
    }

    public void setZzzhm(String zzzhm) {
        this.zzzhm = zzzhm;
    }

    @Basic
    @Column(name = "ZZDZ", nullable = true, length = 200)
    public String getZzdz() {
        return zzdz;
    }

    public void setZzdz(String zzdz) {
        this.zzdz = zzdz;
    }

    @Basic
    @Column(name = "GWBH", nullable = true, length = 4)
    public String getGwbh() {
        return gwbh;
    }

    public void setGwbh(String gwbh) {
        this.gwbh = gwbh;
    }

    @Basic
    @Column(name = "GWMC", nullable = true, length = 60)
    public String getGwmc() {
        return gwmc;
    }

    public void setGwmc(String gwmc) {
        this.gwmc = gwmc;
    }

    @Basic
    @Column(name = "GWZRMS", nullable = true, length = 2000)
    public String getGwzrms() {
        return gwzrms;
    }

    public void setGwzrms(String gwzrms) {
        this.gwzrms = gwzrms;
    }

    @Basic
    @Column(name = "SHOUJI", nullable = true, length = 20)
    public String getShouji() {
        return shouji;
    }

    public void setShouji(String shouji) {
        this.shouji = shouji;
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
    @Column(name = "JJLXR", nullable = true, length = 30)
    public String getJjlxr() {
        return jjlxr;
    }

    public void setJjlxr(String jjlxr) {
        this.jjlxr = jjlxr;
    }

    @Basic
    @Column(name = "JJLXRDH", nullable = true, length = 20)
    public String getJjlxrdh() {
        return jjlxrdh;
    }

    public void setJjlxrdh(String jjlxrdh) {
        this.jjlxrdh = jjlxrdh;
    }

    @Basic
    @Column(name = "GZDM", nullable = true, length = 4)
    public String getGzdm() {
        return gzdm;
    }

    public void setGzdm(String gzdm) {
        this.gzdm = gzdm;
    }

    @Basic
    @Column(name = "GZ", nullable = true, length = 60)
    public String getGz() {
        return gz;
    }

    public void setGz(String gz) {
        this.gz = gz;
    }

    @Basic
    @Column(name = "ZHIWU", nullable = true, length = 20)
    public String getZhiwu() {
        return zhiwu;
    }

    public void setZhiwu(String zhiwu) {
        this.zhiwu = zhiwu;
    }

    @Basic
    @Column(name = "RZRQ", nullable = true)
    public Timestamp getRzrq() {
        return rzrq;
    }

    public void setRzrq(Timestamp rzrq) {
        this.rzrq = rzrq;
    }

    @Basic
    @Column(name = "CYRYZTDM", nullable = true, length = 64)
    public String getCyryztdm() {
        return cyryztdm;
    }

    public void setCyryztdm(String cyryztdm) {
        this.cyryztdm = cyryztdm;
    }

    @Basic
    @Column(name = "CYRYZT", nullable = true, precision = 0)
    public BigInteger getCyryzt() {
        return cyryzt;
    }

    public void setCyryzt(BigInteger cyryzt) {
        this.cyryzt = cyryzt;
    }

    @Basic
    @Column(name = "ZXR", nullable = true, length = 30)
    public String getZxr() {
        return zxr;
    }

    public void setZxr(String zxr) {
        this.zxr = zxr;
    }

    @Basic
    @Column(name = "ZXBZ", nullable = true)
    public Byte getZxbz() {
        return zxbz;
    }

    public void setZxbz(Byte zxbz) {
        this.zxbz = zxbz;
    }

    @Basic
    @Column(name = "ZXSJ", nullable = true)
    public Timestamp getZxsj() {
        return zxsj;
    }

    public void setZxsj(Timestamp zxsj) {
        this.zxsj = zxsj;
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
    @Column(name = "LRR", nullable = true, length = 100)
    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
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
    @Column(name = "HCDW", nullable = true, length = 100)
    public String getHcdw() {
        return hcdw;
    }

    public void setHcdw(String hcdw) {
        this.hcdw = hcdw;
    }

    @Basic
    @Column(name = "HCR", nullable = true, length = 30)
    public String getHcr() {
        return hcr;
    }

    public void setHcr(String hcr) {
        this.hcr = hcr;
    }

    @Basic
    @Column(name = "HCSJ", nullable = true)
    public Timestamp getHcsj() {
        return hcsj;
    }

    public void setHcsj(Timestamp hcsj) {
        this.hcsj = hcsj;
    }

    @Basic
    @Column(name = "ZXYY", nullable = true, length = 200)
    public String getZxyy() {
        return zxyy;
    }

    public void setZxyy(String zxyy) {
        this.zxyy = zxyy;
    }

    @Basic
    @Column(name = "ZKZT", nullable = true)
    public Byte getZkzt() {
        return zkzt;
    }

    public void setZkzt(Byte zkzt) {
        this.zkzt = zkzt;
    }

    @Basic
    @Column(name = "BCSJ", nullable = true)
    public Timestamp getBcsj() {
        return bcsj;
    }

    public void setBcsj(Timestamp bcsj) {
        this.bcsj = bcsj;
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
    @Column(name = "BZ", nullable = true, length = 2000)
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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
    @Column(name = "BIDUIFLAG", nullable = true)
    public Byte getBiduiflag() {
        return biduiflag;
    }

    public void setBiduiflag(Byte biduiflag) {
        this.biduiflag = biduiflag;
    }

    @Basic
    @Column(name = "ZPWTGYY", nullable = true, length = 2000)
    public String getZpwtgyy() {
        return zpwtgyy;
    }

    public void setZpwtgyy(String zpwtgyy) {
        this.zpwtgyy = zpwtgyy;
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
    @Column(name = "LZRQ", nullable = true)
    public Timestamp getLzrq() {
        return lzrq;
    }

    public void setLzrq(Timestamp lzrq) {
        this.lzrq = lzrq;
    }

    @Basic
    @Column(name = "XXDM", nullable = true, length = 2)
    public String getXxdm() {
        return xxdm;
    }

    public void setXxdm(String xxdm) {
        this.xxdm = xxdm;
    }

    @Basic
    @Column(name = "XX", nullable = true, length = 10)
    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }

    @Basic
    @Column(name = "CYLBDM", nullable = true, length = 64)
    public String getCylbdm() {
        return cylbdm;
    }

    public void setCylbdm(String cylbdm) {
        this.cylbdm = cylbdm;
    }

    @Basic
    @Column(name = "CYLB", nullable = true, length = 60)
    public String getCylb() {
        return cylb;
    }

    public void setCylb(String cylb) {
        this.cylb = cylb;
    }

    @Basic
    @Column(name = "WHCDDM", nullable = true, length = 64)
    public String getWhcddm() {
        return whcddm;
    }

    public void setWhcddm(String whcddm) {
        this.whcddm = whcddm;
    }

    @Basic
    @Column(name = "WHCD", nullable = true, length = 60)
    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
    }

    @Basic
    @Column(name = "YJBZ", nullable = true)
    public Byte getYjbz() {
        return yjbz;
    }

    public void setYjbz(Byte yjbz) {
        this.yjbz = yjbz;
    }

    @Basic
    @Column(name = "YJSJ", nullable = true)
    public Timestamp getYjsj() {
        return yjsj;
    }

    public void setYjsj(Timestamp yjsj) {
        this.yjsj = yjsj;
    }

    @Basic
    @Column(name = "XZDXZQHDM", nullable = true, length = 64)
    public String getXzdxzqhdm() {
        return xzdxzqhdm;
    }

    public void setXzdxzqhdm(String xzdxzqhdm) {
        this.xzdxzqhdm = xzdxzqhdm;
    }

    @Basic
    @Column(name = "XZDXZQH", nullable = true, length = 200)
    public String getXzdxzqh() {
        return xzdxzqh;
    }

    public void setXzdxzqh(String xzdxzqh) {
        this.xzdxzqh = xzdxzqh;
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
    @Column(name = "HJLBDM", nullable = true, length = 64)
    public String getHjlbdm() {
        return hjlbdm;
    }

    public void setHjlbdm(String hjlbdm) {
        this.hjlbdm = hjlbdm;
    }

    @Basic
    @Column(name = "HJLBMC", nullable = true, length = 60)
    public String getHjlbmc() {
        return hjlbmc;
    }

    public void setHjlbmc(String hjlbmc) {
        this.hjlbmc = hjlbmc;
    }

    @Basic
    @Column(name = "ZZMMDM", nullable = true, length = 64)
    public String getZzmmdm() {
        return zzmmdm;
    }

    public void setZzmmdm(String zzmmdm) {
        this.zzmmdm = zzmmdm;
    }

    @Basic
    @Column(name = "ZZMMMC", nullable = true, length = 60)
    public String getZzmmmc() {
        return zzmmmc;
    }

    public void setZzmmmc(String zzmmmc) {
        this.zzmmmc = zzmmmc;
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

        AppBizQyryxx that = (AppBizQyryxx) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (appOwid != null ? !appOwid.equals(that.appOwid) : that.appOwid != null) return false;
        if (tx != null ? !tx.equals(that.tx) : that.tx != null) return false;
        if (sfkdl != null ? !sfkdl.equals(that.sfkdl) : that.sfkdl != null) return false;
        if (hylbdm != null ? !hylbdm.equals(that.hylbdm) : that.hylbdm != null) return false;
        if (hylb != null ? !hylb.equals(that.hylb) : that.hylb != null) return false;
        if (cyrybh != null ? !cyrybh.equals(that.cyrybh) : that.cyrybh != null) return false;
        if (cyrylbdm != null ? !cyrylbdm.equals(that.cyrylbdm) : that.cyrylbdm != null) return false;
        if (cyrylbmc != null ? !cyrylbmc.equals(that.cyrylbmc) : that.cyrylbmc != null) return false;
        if (xm != null ? !xm.equals(that.xm) : that.xm != null) return false;
        if (qm != null ? !qm.equals(that.qm) : that.qm != null) return false;
        if (bm != null ? !bm.equals(that.bm) : that.bm != null) return false;
        if (xmpy != null ? !xmpy.equals(that.xmpy) : that.xmpy != null) return false;
        if (gjdm != null ? !gjdm.equals(that.gjdm) : that.gjdm != null) return false;
        if (gj != null ? !gj.equals(that.gj) : that.gj != null) return false;
        if (mzdm != null ? !mzdm.equals(that.mzdm) : that.mzdm != null) return false;
        if (minzu != null ? !minzu.equals(that.minzu) : that.minzu != null) return false;
        if (xbdm != null ? !xbdm.equals(that.xbdm) : that.xbdm != null) return false;
        if (xb != null ? !xb.equals(that.xb) : that.xb != null) return false;
        if (csrq != null ? !csrq.equals(that.csrq) : that.csrq != null) return false;
        if (hyzkdm != null ? !hyzkdm.equals(that.hyzkdm) : that.hyzkdm != null) return false;
        if (hyzk != null ? !hyzk.equals(that.hyzk) : that.hyzk != null) return false;
        if (shengao != null ? !shengao.equals(that.shengao) : that.shengao != null) return false;
        if (tizh != null ? !tizh.equals(that.tizh) : that.tizh != null) return false;
        if (hjdxzqh != null ? !hjdxzqh.equals(that.hjdxzqh) : that.hjdxzqh != null) return false;
        if (hjdxzqhdm != null ? !hjdxzqhdm.equals(that.hjdxzqhdm) : that.hjdxzqhdm != null) return false;
        if (hjdxz != null ? !hjdxz.equals(that.hjdxz) : that.hjdxz != null) return false;
        if (cyzjdm != null ? !cyzjdm.equals(that.cyzjdm) : that.cyzjdm != null) return false;
        if (cyzj != null ? !cyzj.equals(that.cyzj) : that.cyzj != null) return false;
        if (zjhm != null ? !zjhm.equals(that.zjhm) : that.zjhm != null) return false;
        if (zzzhm != null ? !zzzhm.equals(that.zzzhm) : that.zzzhm != null) return false;
        if (zzdz != null ? !zzdz.equals(that.zzdz) : that.zzdz != null) return false;
        if (gwbh != null ? !gwbh.equals(that.gwbh) : that.gwbh != null) return false;
        if (gwmc != null ? !gwmc.equals(that.gwmc) : that.gwmc != null) return false;
        if (gwzrms != null ? !gwzrms.equals(that.gwzrms) : that.gwzrms != null) return false;
        if (shouji != null ? !shouji.equals(that.shouji) : that.shouji != null) return false;
        if (lxdh != null ? !lxdh.equals(that.lxdh) : that.lxdh != null) return false;
        if (jjlxr != null ? !jjlxr.equals(that.jjlxr) : that.jjlxr != null) return false;
        if (jjlxrdh != null ? !jjlxrdh.equals(that.jjlxrdh) : that.jjlxrdh != null) return false;
        if (gzdm != null ? !gzdm.equals(that.gzdm) : that.gzdm != null) return false;
        if (gz != null ? !gz.equals(that.gz) : that.gz != null) return false;
        if (zhiwu != null ? !zhiwu.equals(that.zhiwu) : that.zhiwu != null) return false;
        if (rzrq != null ? !rzrq.equals(that.rzrq) : that.rzrq != null) return false;
        if (cyryztdm != null ? !cyryztdm.equals(that.cyryztdm) : that.cyryztdm != null) return false;
        if (cyryzt != null ? !cyryzt.equals(that.cyryzt) : that.cyryzt != null) return false;
        if (zxr != null ? !zxr.equals(that.zxr) : that.zxr != null) return false;
        if (zxbz != null ? !zxbz.equals(that.zxbz) : that.zxbz != null) return false;
        if (zxsj != null ? !zxsj.equals(that.zxsj) : that.zxsj != null) return false;
        if (lrsj != null ? !lrsj.equals(that.lrsj) : that.lrsj != null) return false;
        if (lrr != null ? !lrr.equals(that.lrr) : that.lrr != null) return false;
        if (lrdwbm != null ? !lrdwbm.equals(that.lrdwbm) : that.lrdwbm != null) return false;
        if (lrdwmc != null ? !lrdwmc.equals(that.lrdwmc) : that.lrdwmc != null) return false;
        if (hcdw != null ? !hcdw.equals(that.hcdw) : that.hcdw != null) return false;
        if (hcr != null ? !hcr.equals(that.hcr) : that.hcr != null) return false;
        if (hcsj != null ? !hcsj.equals(that.hcsj) : that.hcsj != null) return false;
        if (zxyy != null ? !zxyy.equals(that.zxyy) : that.zxyy != null) return false;
        if (zkzt != null ? !zkzt.equals(that.zkzt) : that.zkzt != null) return false;
        if (bcsj != null ? !bcsj.equals(that.bcsj) : that.bcsj != null) return false;
        if (dcbs != null ? !dcbs.equals(that.dcbs) : that.dcbs != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (scbz != null ? !scbz.equals(that.scbz) : that.scbz != null) return false;
        if (biduiflag != null ? !biduiflag.equals(that.biduiflag) : that.biduiflag != null) return false;
        if (zpwtgyy != null ? !zpwtgyy.equals(that.zpwtgyy) : that.zpwtgyy != null) return false;
        if (zt != null ? !zt.equals(that.zt) : that.zt != null) return false;
        if (lzrq != null ? !lzrq.equals(that.lzrq) : that.lzrq != null) return false;
        if (xxdm != null ? !xxdm.equals(that.xxdm) : that.xxdm != null) return false;
        if (xx != null ? !xx.equals(that.xx) : that.xx != null) return false;
        if (cylbdm != null ? !cylbdm.equals(that.cylbdm) : that.cylbdm != null) return false;
        if (cylb != null ? !cylb.equals(that.cylb) : that.cylb != null) return false;
        if (whcddm != null ? !whcddm.equals(that.whcddm) : that.whcddm != null) return false;
        if (whcd != null ? !whcd.equals(that.whcd) : that.whcd != null) return false;
        if (yjbz != null ? !yjbz.equals(that.yjbz) : that.yjbz != null) return false;
        if (yjsj != null ? !yjsj.equals(that.yjsj) : that.yjsj != null) return false;
        if (xzdxzqhdm != null ? !xzdxzqhdm.equals(that.xzdxzqhdm) : that.xzdxzqhdm != null) return false;
        if (xzdxzqh != null ? !xzdxzqh.equals(that.xzdxzqh) : that.xzdxzqh != null) return false;
        if (djrq != null ? !djrq.equals(that.djrq) : that.djrq != null) return false;
        if (hjlbdm != null ? !hjlbdm.equals(that.hjlbdm) : that.hjlbdm != null) return false;
        if (hjlbmc != null ? !hjlbmc.equals(that.hjlbmc) : that.hjlbmc != null) return false;
        if (zzmmdm != null ? !zzmmdm.equals(that.zzmmdm) : that.zzmmdm != null) return false;
        if (zzmmmc != null ? !zzmmmc.equals(that.zzmmmc) : that.zzmmmc != null) return false;
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
        result = 31 * result + (tx != null ? tx.hashCode() : 0);
        result = 31 * result + (sfkdl != null ? sfkdl.hashCode() : 0);
        result = 31 * result + (hylbdm != null ? hylbdm.hashCode() : 0);
        result = 31 * result + (hylb != null ? hylb.hashCode() : 0);
        result = 31 * result + (cyrybh != null ? cyrybh.hashCode() : 0);
        result = 31 * result + (cyrylbdm != null ? cyrylbdm.hashCode() : 0);
        result = 31 * result + (cyrylbmc != null ? cyrylbmc.hashCode() : 0);
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (qm != null ? qm.hashCode() : 0);
        result = 31 * result + (bm != null ? bm.hashCode() : 0);
        result = 31 * result + (xmpy != null ? xmpy.hashCode() : 0);
        result = 31 * result + (gjdm != null ? gjdm.hashCode() : 0);
        result = 31 * result + (gj != null ? gj.hashCode() : 0);
        result = 31 * result + (mzdm != null ? mzdm.hashCode() : 0);
        result = 31 * result + (minzu != null ? minzu.hashCode() : 0);
        result = 31 * result + (xbdm != null ? xbdm.hashCode() : 0);
        result = 31 * result + (xb != null ? xb.hashCode() : 0);
        result = 31 * result + (csrq != null ? csrq.hashCode() : 0);
        result = 31 * result + (hyzkdm != null ? hyzkdm.hashCode() : 0);
        result = 31 * result + (hyzk != null ? hyzk.hashCode() : 0);
        result = 31 * result + (shengao != null ? shengao.hashCode() : 0);
        result = 31 * result + (tizh != null ? tizh.hashCode() : 0);
        result = 31 * result + (hjdxzqh != null ? hjdxzqh.hashCode() : 0);
        result = 31 * result + (hjdxzqhdm != null ? hjdxzqhdm.hashCode() : 0);
        result = 31 * result + (hjdxz != null ? hjdxz.hashCode() : 0);
        result = 31 * result + (cyzjdm != null ? cyzjdm.hashCode() : 0);
        result = 31 * result + (cyzj != null ? cyzj.hashCode() : 0);
        result = 31 * result + (zjhm != null ? zjhm.hashCode() : 0);
        result = 31 * result + (zzzhm != null ? zzzhm.hashCode() : 0);
        result = 31 * result + (zzdz != null ? zzdz.hashCode() : 0);
        result = 31 * result + (gwbh != null ? gwbh.hashCode() : 0);
        result = 31 * result + (gwmc != null ? gwmc.hashCode() : 0);
        result = 31 * result + (gwzrms != null ? gwzrms.hashCode() : 0);
        result = 31 * result + (shouji != null ? shouji.hashCode() : 0);
        result = 31 * result + (lxdh != null ? lxdh.hashCode() : 0);
        result = 31 * result + (jjlxr != null ? jjlxr.hashCode() : 0);
        result = 31 * result + (jjlxrdh != null ? jjlxrdh.hashCode() : 0);
        result = 31 * result + (gzdm != null ? gzdm.hashCode() : 0);
        result = 31 * result + (gz != null ? gz.hashCode() : 0);
        result = 31 * result + (zhiwu != null ? zhiwu.hashCode() : 0);
        result = 31 * result + (rzrq != null ? rzrq.hashCode() : 0);
        result = 31 * result + (cyryztdm != null ? cyryztdm.hashCode() : 0);
        result = 31 * result + (cyryzt != null ? cyryzt.hashCode() : 0);
        result = 31 * result + (zxr != null ? zxr.hashCode() : 0);
        result = 31 * result + (zxbz != null ? zxbz.hashCode() : 0);
        result = 31 * result + (zxsj != null ? zxsj.hashCode() : 0);
        result = 31 * result + (lrsj != null ? lrsj.hashCode() : 0);
        result = 31 * result + (lrr != null ? lrr.hashCode() : 0);
        result = 31 * result + (lrdwbm != null ? lrdwbm.hashCode() : 0);
        result = 31 * result + (lrdwmc != null ? lrdwmc.hashCode() : 0);
        result = 31 * result + (hcdw != null ? hcdw.hashCode() : 0);
        result = 31 * result + (hcr != null ? hcr.hashCode() : 0);
        result = 31 * result + (hcsj != null ? hcsj.hashCode() : 0);
        result = 31 * result + (zxyy != null ? zxyy.hashCode() : 0);
        result = 31 * result + (zkzt != null ? zkzt.hashCode() : 0);
        result = 31 * result + (bcsj != null ? bcsj.hashCode() : 0);
        result = 31 * result + (dcbs != null ? dcbs.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (scbz != null ? scbz.hashCode() : 0);
        result = 31 * result + (biduiflag != null ? biduiflag.hashCode() : 0);
        result = 31 * result + (zpwtgyy != null ? zpwtgyy.hashCode() : 0);
        result = 31 * result + (zt != null ? zt.hashCode() : 0);
        result = 31 * result + (lzrq != null ? lzrq.hashCode() : 0);
        result = 31 * result + (xxdm != null ? xxdm.hashCode() : 0);
        result = 31 * result + (xx != null ? xx.hashCode() : 0);
        result = 31 * result + (cylbdm != null ? cylbdm.hashCode() : 0);
        result = 31 * result + (cylb != null ? cylb.hashCode() : 0);
        result = 31 * result + (whcddm != null ? whcddm.hashCode() : 0);
        result = 31 * result + (whcd != null ? whcd.hashCode() : 0);
        result = 31 * result + (yjbz != null ? yjbz.hashCode() : 0);
        result = 31 * result + (yjsj != null ? yjsj.hashCode() : 0);
        result = 31 * result + (xzdxzqhdm != null ? xzdxzqhdm.hashCode() : 0);
        result = 31 * result + (xzdxzqh != null ? xzdxzqh.hashCode() : 0);
        result = 31 * result + (djrq != null ? djrq.hashCode() : 0);
        result = 31 * result + (hjlbdm != null ? hjlbdm.hashCode() : 0);
        result = 31 * result + (hjlbmc != null ? hjlbmc.hashCode() : 0);
        result = 31 * result + (zzmmdm != null ? zzmmdm.hashCode() : 0);
        result = 31 * result + (zzmmmc != null ? zzmmmc.hashCode() : 0);
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
