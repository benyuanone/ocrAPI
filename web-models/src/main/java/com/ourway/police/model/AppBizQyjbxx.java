package com.ourway.police.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by D.chen.g on 2017/12/28.
 */
@Entity
@Table(name = "app_biz_qyjbxx", schema = "public_manage", catalog = "")
public class AppBizQyjbxx {
    private String owid;
    private String hylbdm;
    private String hylb;
    private String qybm;
    private String qymc;
    private String qyqp;
    private String qyjp;
    private String qyzflbm;
    private String qyzflmc;
    private String qyfflbm;
    private String qyfflmc;
    private String gxdwbm;
    private String gxdwmc;
    private String ssdxjgajgdm;
    private String ssdxjgajgmc;
    private String ssddsjgdm;
    private String ssddsjgmc;
    private String qyzzjgdm;
    private String lxdh;
    private String yzbm;
    private String chzh;
    private String jwdzb;
    private String jjlxbm;
    private String jjlxmc;
    private String jyfwzy;
    private String jyfwjy;
    private BigDecimal zczj;
    private BigDecimal jymj;
    private String zabdm;
    private String zajbmc;
    private String yyztdm;
    private String yyztmc;
    private String frdb;
    private String frdbzjlb;
    private String frdbzjhm;
    private String frdblxfs;
    private Timestamp kyrq;
    private String yysj;
    private String babh;
    private Timestamp barq;
    private Timestamp tyrq;
    private String jyfs;
    private String jydz;
    private String dwfzrzjlb;
    private String dwfzrzjhm;
    private String dwfzr;
    private String dwfzrlxfs;
    private String xfshdw;
    private String xfhgzh;
    private String hcdw;
    private String hcr;
    private Timestamp hcsj;
    private String gdxx;
    private Integer zrs;
    private Integer hdrs;
    private String bagsmc;
    private String bafzrgmsfhm;
    private String bafzrxm;
    private String bafzrdh;
    private Integer bars;
    private Integer jgpxrs;
    private String bagsyj;
    private Integer cksl;
    private Integer bxbjsl;
    private String bz;
    private Byte dcbs;
    private Timestamp dcsj;
    private Byte zt;
    private Timestamp ztgbrq;
    private Byte scbz;
    private String jqbm;
    private Byte zxbz;
    private String zxyy;
    private BigDecimal zdskb;
    private Timestamp bcsj;
    private Timestamp lrsj;
    private String lrr;
    private String lrdwbm;
    private String lrdwmc;
    private String thbajgdm;
    private String thbajg;
    private String bwbmdh;
    private Byte sfazsxt;
    private Integer azsxtsl;
    private String zjztdm;
    private String zjztmc;
    private Timestamp djrq;
    private Timestamp nsrq;
    private String qyjc;
    private String qyjcqp;
    private String qyjcjp;
    private String sgscxfjjs;
    private String jhltlxdm;
    private String jhltlxmc;
    private String sbzt;
    private String wxywfl;
    private String wxywfldm;
    private String xfshyjsbh;
    private String xfshyjsfzjg;
    private String xfshyjsfzjgdm;
    private Timestamp xfshyjsfzrq;
    private Timestamp yyzzyxqz;
    private Timestamp yyzzfzrq;
    private Timestamp thxkzyxqz;
    private Timestamp thxkzfzrq;
    private Timestamp jyxkzyxqz;
    private Timestamp jyxkzfzrq;
    private Byte jyfzrxb;
    private Byte frxb;
    private Integer jzgd;
    private Integer jzcs;
    private Integer crksl;
    private String jyxkzfzjg;
    private String xmnr;
    private String jyxkzfzjgdm;
    private String mtzpzp;
    private String gdjycscqbh;
    private String gdjycssyqbh;
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
    @Column(name = "QYBM", nullable = true, length = 64)
    public String getQybm() {
        return qybm;
    }

    public void setQybm(String qybm) {
        this.qybm = qybm;
    }

    @Basic
    @Column(name = "QYMC", nullable = true, length = 120)
    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    @Basic
    @Column(name = "QYQP", nullable = true, length = 100)
    public String getQyqp() {
        return qyqp;
    }

    public void setQyqp(String qyqp) {
        this.qyqp = qyqp;
    }

    @Basic
    @Column(name = "QYJP", nullable = true, length = 30)
    public String getQyjp() {
        return qyjp;
    }

    public void setQyjp(String qyjp) {
        this.qyjp = qyjp;
    }

    @Basic
    @Column(name = "QYZFLBM", nullable = true, length = 64)
    public String getQyzflbm() {
        return qyzflbm;
    }

    public void setQyzflbm(String qyzflbm) {
        this.qyzflbm = qyzflbm;
    }

    @Basic
    @Column(name = "QYZFLMC", nullable = true, length = 300)
    public String getQyzflmc() {
        return qyzflmc;
    }

    public void setQyzflmc(String qyzflmc) {
        this.qyzflmc = qyzflmc;
    }

    @Basic
    @Column(name = "QYFFLBM", nullable = true, length = 64)
    public String getQyfflbm() {
        return qyfflbm;
    }

    public void setQyfflbm(String qyfflbm) {
        this.qyfflbm = qyfflbm;
    }

    @Basic
    @Column(name = "QYFFLMC", nullable = true, length = 300)
    public String getQyfflmc() {
        return qyfflmc;
    }

    public void setQyfflmc(String qyfflmc) {
        this.qyfflmc = qyfflmc;
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
    @Column(name = "SSDXJGAJGDM", nullable = true, length = 64)
    public String getSsdxjgajgdm() {
        return ssdxjgajgdm;
    }

    public void setSsdxjgajgdm(String ssdxjgajgdm) {
        this.ssdxjgajgdm = ssdxjgajgdm;
    }

    @Basic
    @Column(name = "SSDXJGAJGMC", nullable = true, length = 200)
    public String getSsdxjgajgmc() {
        return ssdxjgajgmc;
    }

    public void setSsdxjgajgmc(String ssdxjgajgmc) {
        this.ssdxjgajgmc = ssdxjgajgmc;
    }

    @Basic
    @Column(name = "SSDDSJGDM", nullable = true, length = 64)
    public String getSsddsjgdm() {
        return ssddsjgdm;
    }

    public void setSsddsjgdm(String ssddsjgdm) {
        this.ssddsjgdm = ssddsjgdm;
    }

    @Basic
    @Column(name = "SSDDSJGMC", nullable = true, length = 200)
    public String getSsddsjgmc() {
        return ssddsjgmc;
    }

    public void setSsddsjgmc(String ssddsjgmc) {
        this.ssddsjgmc = ssddsjgmc;
    }

    @Basic
    @Column(name = "QYZZJGDM", nullable = true, length = 64)
    public String getQyzzjgdm() {
        return qyzzjgdm;
    }

    public void setQyzzjgdm(String qyzzjgdm) {
        this.qyzzjgdm = qyzzjgdm;
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
    @Column(name = "YZBM", nullable = true, length = 20)
    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    @Basic
    @Column(name = "CHZH", nullable = true, length = 30)
    public String getChzh() {
        return chzh;
    }

    public void setChzh(String chzh) {
        this.chzh = chzh;
    }

    @Basic
    @Column(name = "JWDZB", nullable = true, length = 100)
    public String getJwdzb() {
        return jwdzb;
    }

    public void setJwdzb(String jwdzb) {
        this.jwdzb = jwdzb;
    }

    @Basic
    @Column(name = "JJLXBM", nullable = true, length = 64)
    public String getJjlxbm() {
        return jjlxbm;
    }

    public void setJjlxbm(String jjlxbm) {
        this.jjlxbm = jjlxbm;
    }

    @Basic
    @Column(name = "JJLXMC", nullable = true, length = 60)
    public String getJjlxmc() {
        return jjlxmc;
    }

    public void setJjlxmc(String jjlxmc) {
        this.jjlxmc = jjlxmc;
    }

    @Basic
    @Column(name = "JYFWZY", nullable = true, length = 200)
    public String getJyfwzy() {
        return jyfwzy;
    }

    public void setJyfwzy(String jyfwzy) {
        this.jyfwzy = jyfwzy;
    }

    @Basic
    @Column(name = "JYFWJY", nullable = true, length = 200)
    public String getJyfwjy() {
        return jyfwjy;
    }

    public void setJyfwjy(String jyfwjy) {
        this.jyfwjy = jyfwjy;
    }

    @Basic
    @Column(name = "ZCZJ", nullable = true, precision = 2)
    public BigDecimal getZczj() {
        return zczj;
    }

    public void setZczj(BigDecimal zczj) {
        this.zczj = zczj;
    }

    @Basic
    @Column(name = "JYMJ", nullable = true, precision = 2)
    public BigDecimal getJymj() {
        return jymj;
    }

    public void setJymj(BigDecimal jymj) {
        this.jymj = jymj;
    }

    @Basic
    @Column(name = "ZABDM", nullable = true, length = 64)
    public String getZabdm() {
        return zabdm;
    }

    public void setZabdm(String zabdm) {
        this.zabdm = zabdm;
    }

    @Basic
    @Column(name = "ZAJBMC", nullable = true, length = 20)
    public String getZajbmc() {
        return zajbmc;
    }

    public void setZajbmc(String zajbmc) {
        this.zajbmc = zajbmc;
    }

    @Basic
    @Column(name = "YYZTDM", nullable = true, length = 64)
    public String getYyztdm() {
        return yyztdm;
    }

    public void setYyztdm(String yyztdm) {
        this.yyztdm = yyztdm;
    }

    @Basic
    @Column(name = "YYZTMC", nullable = true, length = 20)
    public String getYyztmc() {
        return yyztmc;
    }

    public void setYyztmc(String yyztmc) {
        this.yyztmc = yyztmc;
    }

    @Basic
    @Column(name = "FRDB", nullable = true, length = 30)
    public String getFrdb() {
        return frdb;
    }

    public void setFrdb(String frdb) {
        this.frdb = frdb;
    }

    @Basic
    @Column(name = "FRDBZJLB", nullable = true, length = 40)
    public String getFrdbzjlb() {
        return frdbzjlb;
    }

    public void setFrdbzjlb(String frdbzjlb) {
        this.frdbzjlb = frdbzjlb;
    }

    @Basic
    @Column(name = "FRDBZJHM", nullable = true, length = 30)
    public String getFrdbzjhm() {
        return frdbzjhm;
    }

    public void setFrdbzjhm(String frdbzjhm) {
        this.frdbzjhm = frdbzjhm;
    }

    @Basic
    @Column(name = "FRDBLXFS", nullable = true, length = 30)
    public String getFrdblxfs() {
        return frdblxfs;
    }

    public void setFrdblxfs(String frdblxfs) {
        this.frdblxfs = frdblxfs;
    }

    @Basic
    @Column(name = "KYRQ", nullable = true)
    public Timestamp getKyrq() {
        return kyrq;
    }

    public void setKyrq(Timestamp kyrq) {
        this.kyrq = kyrq;
    }

    @Basic
    @Column(name = "YYSJ", nullable = true, length = 20)
    public String getYysj() {
        return yysj;
    }

    public void setYysj(String yysj) {
        this.yysj = yysj;
    }

    @Basic
    @Column(name = "BABH", nullable = true, length = 30)
    public String getBabh() {
        return babh;
    }

    public void setBabh(String babh) {
        this.babh = babh;
    }

    @Basic
    @Column(name = "BARQ", nullable = true)
    public Timestamp getBarq() {
        return barq;
    }

    public void setBarq(Timestamp barq) {
        this.barq = barq;
    }

    @Basic
    @Column(name = "TYRQ", nullable = true)
    public Timestamp getTyrq() {
        return tyrq;
    }

    public void setTyrq(Timestamp tyrq) {
        this.tyrq = tyrq;
    }

    @Basic
    @Column(name = "JYFS", nullable = true, length = 20)
    public String getJyfs() {
        return jyfs;
    }

    public void setJyfs(String jyfs) {
        this.jyfs = jyfs;
    }

    @Basic
    @Column(name = "JYDZ", nullable = true, length = 200)
    public String getJydz() {
        return jydz;
    }

    public void setJydz(String jydz) {
        this.jydz = jydz;
    }

    @Basic
    @Column(name = "DWFZRZJLB", nullable = true, length = 40)
    public String getDwfzrzjlb() {
        return dwfzrzjlb;
    }

    public void setDwfzrzjlb(String dwfzrzjlb) {
        this.dwfzrzjlb = dwfzrzjlb;
    }

    @Basic
    @Column(name = "DWFZRZJHM", nullable = true, length = 30)
    public String getDwfzrzjhm() {
        return dwfzrzjhm;
    }

    public void setDwfzrzjhm(String dwfzrzjhm) {
        this.dwfzrzjhm = dwfzrzjhm;
    }

    @Basic
    @Column(name = "DWFZR", nullable = true, length = 30)
    public String getDwfzr() {
        return dwfzr;
    }

    public void setDwfzr(String dwfzr) {
        this.dwfzr = dwfzr;
    }

    @Basic
    @Column(name = "DWFZRLXFS", nullable = true, length = 30)
    public String getDwfzrlxfs() {
        return dwfzrlxfs;
    }

    public void setDwfzrlxfs(String dwfzrlxfs) {
        this.dwfzrlxfs = dwfzrlxfs;
    }

    @Basic
    @Column(name = "XFSHDW", nullable = true, length = 200)
    public String getXfshdw() {
        return xfshdw;
    }

    public void setXfshdw(String xfshdw) {
        this.xfshdw = xfshdw;
    }

    @Basic
    @Column(name = "XFHGZH", nullable = true, length = 60)
    public String getXfhgzh() {
        return xfhgzh;
    }

    public void setXfhgzh(String xfhgzh) {
        this.xfhgzh = xfhgzh;
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
    @Column(name = "GDXX", nullable = true, length = 2000)
    public String getGdxx() {
        return gdxx;
    }

    public void setGdxx(String gdxx) {
        this.gdxx = gdxx;
    }

    @Basic
    @Column(name = "ZRS", nullable = true, precision = 0)
    public Integer getZrs() {
        return zrs;
    }

    public void setZrs(Integer zrs) {
        this.zrs = zrs;
    }

    @Basic
    @Column(name = "HDRS", nullable = true, precision = 0)
    public Integer getHdrs() {
        return hdrs;
    }

    public void setHdrs(Integer hdrs) {
        this.hdrs = hdrs;
    }

    @Basic
    @Column(name = "BAGSMC", nullable = true, length = 60)
    public String getBagsmc() {
        return bagsmc;
    }

    public void setBagsmc(String bagsmc) {
        this.bagsmc = bagsmc;
    }

    @Basic
    @Column(name = "BAFZRGMSFHM", nullable = true, length = 20)
    public String getBafzrgmsfhm() {
        return bafzrgmsfhm;
    }

    public void setBafzrgmsfhm(String bafzrgmsfhm) {
        this.bafzrgmsfhm = bafzrgmsfhm;
    }

    @Basic
    @Column(name = "BAFZRXM", nullable = true, length = 30)
    public String getBafzrxm() {
        return bafzrxm;
    }

    public void setBafzrxm(String bafzrxm) {
        this.bafzrxm = bafzrxm;
    }

    @Basic
    @Column(name = "BAFZRDH", nullable = true, length = 30)
    public String getBafzrdh() {
        return bafzrdh;
    }

    public void setBafzrdh(String bafzrdh) {
        this.bafzrdh = bafzrdh;
    }

    @Basic
    @Column(name = "BARS", nullable = true, precision = 0)
    public Integer getBars() {
        return bars;
    }

    public void setBars(Integer bars) {
        this.bars = bars;
    }

    @Basic
    @Column(name = "JGPXRS", nullable = true, precision = 0)
    public Integer getJgpxrs() {
        return jgpxrs;
    }

    public void setJgpxrs(Integer jgpxrs) {
        this.jgpxrs = jgpxrs;
    }

    @Basic
    @Column(name = "BAGSYJ", nullable = true, length = 2000)
    public String getBagsyj() {
        return bagsyj;
    }

    public void setBagsyj(String bagsyj) {
        this.bagsyj = bagsyj;
    }

    @Basic
    @Column(name = "CKSL", nullable = true, precision = 0)
    public Integer getCksl() {
        return cksl;
    }

    public void setCksl(Integer cksl) {
        this.cksl = cksl;
    }

    @Basic
    @Column(name = "BXBJSL", nullable = true, precision = 0)
    public Integer getBxbjsl() {
        return bxbjsl;
    }

    public void setBxbjsl(Integer bxbjsl) {
        this.bxbjsl = bxbjsl;
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
    @Column(name = "DCBS", nullable = true)
    public Byte getDcbs() {
        return dcbs;
    }

    public void setDcbs(Byte dcbs) {
        this.dcbs = dcbs;
    }

    @Basic
    @Column(name = "DCSJ", nullable = true)
    public Timestamp getDcsj() {
        return dcsj;
    }

    public void setDcsj(Timestamp dcsj) {
        this.dcsj = dcsj;
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
    @Column(name = "ZTGBRQ", nullable = true)
    public Timestamp getZtgbrq() {
        return ztgbrq;
    }

    public void setZtgbrq(Timestamp ztgbrq) {
        this.ztgbrq = ztgbrq;
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
    @Column(name = "JQBM", nullable = true, length = 10)
    public String getJqbm() {
        return jqbm;
    }

    public void setJqbm(String jqbm) {
        this.jqbm = jqbm;
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
    @Column(name = "ZXYY", nullable = true, length = 200)
    public String getZxyy() {
        return zxyy;
    }

    public void setZxyy(String zxyy) {
        this.zxyy = zxyy;
    }

    @Basic
    @Column(name = "ZDSKB", nullable = true, precision = 2)
    public BigDecimal getZdskb() {
        return zdskb;
    }

    public void setZdskb(BigDecimal zdskb) {
        this.zdskb = zdskb;
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
    @Column(name = "THBAJGDM", nullable = true, length = 64)
    public String getThbajgdm() {
        return thbajgdm;
    }

    public void setThbajgdm(String thbajgdm) {
        this.thbajgdm = thbajgdm;
    }

    @Basic
    @Column(name = "THBAJG", nullable = true, length = 60)
    public String getThbajg() {
        return thbajg;
    }

    public void setThbajg(String thbajg) {
        this.thbajg = thbajg;
    }

    @Basic
    @Column(name = "BWBMDH", nullable = true, length = 30)
    public String getBwbmdh() {
        return bwbmdh;
    }

    public void setBwbmdh(String bwbmdh) {
        this.bwbmdh = bwbmdh;
    }

    @Basic
    @Column(name = "SFAZSXT", nullable = true)
    public Byte getSfazsxt() {
        return sfazsxt;
    }

    public void setSfazsxt(Byte sfazsxt) {
        this.sfazsxt = sfazsxt;
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
    @Column(name = "ZJZTDM", nullable = true, length = 64)
    public String getZjztdm() {
        return zjztdm;
    }

    public void setZjztdm(String zjztdm) {
        this.zjztdm = zjztdm;
    }

    @Basic
    @Column(name = "ZJZTMC", nullable = true, length = 20)
    public String getZjztmc() {
        return zjztmc;
    }

    public void setZjztmc(String zjztmc) {
        this.zjztmc = zjztmc;
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
    @Column(name = "NSRQ", nullable = true)
    public Timestamp getNsrq() {
        return nsrq;
    }

    public void setNsrq(Timestamp nsrq) {
        this.nsrq = nsrq;
    }

    @Basic
    @Column(name = "QYJC", nullable = true, length = 120)
    public String getQyjc() {
        return qyjc;
    }

    public void setQyjc(String qyjc) {
        this.qyjc = qyjc;
    }

    @Basic
    @Column(name = "QYJCQP", nullable = true, length = 512)
    public String getQyjcqp() {
        return qyjcqp;
    }

    public void setQyjcqp(String qyjcqp) {
        this.qyjcqp = qyjcqp;
    }

    @Basic
    @Column(name = "QYJCJP", nullable = true, length = 120)
    public String getQyjcjp() {
        return qyjcjp;
    }

    public void setQyjcjp(String qyjcjp) {
        this.qyjcjp = qyjcjp;
    }

    @Basic
    @Column(name = "SGSCXFJJS", nullable = true, length = 4)
    public String getSgscxfjjs() {
        return sgscxfjjs;
    }

    public void setSgscxfjjs(String sgscxfjjs) {
        this.sgscxfjjs = sgscxfjjs;
    }

    @Basic
    @Column(name = "JHLTLXDM", nullable = true, length = 64)
    public String getJhltlxdm() {
        return jhltlxdm;
    }

    public void setJhltlxdm(String jhltlxdm) {
        this.jhltlxdm = jhltlxdm;
    }

    @Basic
    @Column(name = "JHLTLXMC", nullable = true, length = 32)
    public String getJhltlxmc() {
        return jhltlxmc;
    }

    public void setJhltlxmc(String jhltlxmc) {
        this.jhltlxmc = jhltlxmc;
    }

    @Basic
    @Column(name = "SBZT", nullable = true, length = 2)
    public String getSbzt() {
        return sbzt;
    }

    public void setSbzt(String sbzt) {
        this.sbzt = sbzt;
    }

    @Basic
    @Column(name = "WXYWFL", nullable = true, length = 32)
    public String getWxywfl() {
        return wxywfl;
    }

    public void setWxywfl(String wxywfl) {
        this.wxywfl = wxywfl;
    }

    @Basic
    @Column(name = "WXYWFLDM", nullable = true, length = 64)
    public String getWxywfldm() {
        return wxywfldm;
    }

    public void setWxywfldm(String wxywfldm) {
        this.wxywfldm = wxywfldm;
    }

    @Basic
    @Column(name = "XFSHYJSBH", nullable = true, length = 100)
    public String getXfshyjsbh() {
        return xfshyjsbh;
    }

    public void setXfshyjsbh(String xfshyjsbh) {
        this.xfshyjsbh = xfshyjsbh;
    }

    @Basic
    @Column(name = "XFSHYJSFZJG", nullable = true, length = 40)
    public String getXfshyjsfzjg() {
        return xfshyjsfzjg;
    }

    public void setXfshyjsfzjg(String xfshyjsfzjg) {
        this.xfshyjsfzjg = xfshyjsfzjg;
    }

    @Basic
    @Column(name = "XFSHYJSFZJGDM", nullable = true, length = 64)
    public String getXfshyjsfzjgdm() {
        return xfshyjsfzjgdm;
    }

    public void setXfshyjsfzjgdm(String xfshyjsfzjgdm) {
        this.xfshyjsfzjgdm = xfshyjsfzjgdm;
    }

    @Basic
    @Column(name = "XFSHYJSFZRQ", nullable = true)
    public Timestamp getXfshyjsfzrq() {
        return xfshyjsfzrq;
    }

    public void setXfshyjsfzrq(Timestamp xfshyjsfzrq) {
        this.xfshyjsfzrq = xfshyjsfzrq;
    }

    @Basic
    @Column(name = "YYZZYXQZ", nullable = true)
    public Timestamp getYyzzyxqz() {
        return yyzzyxqz;
    }

    public void setYyzzyxqz(Timestamp yyzzyxqz) {
        this.yyzzyxqz = yyzzyxqz;
    }

    @Basic
    @Column(name = "YYZZFZRQ", nullable = true)
    public Timestamp getYyzzfzrq() {
        return yyzzfzrq;
    }

    public void setYyzzfzrq(Timestamp yyzzfzrq) {
        this.yyzzfzrq = yyzzfzrq;
    }

    @Basic
    @Column(name = "THXKZYXQZ", nullable = true)
    public Timestamp getThxkzyxqz() {
        return thxkzyxqz;
    }

    public void setThxkzyxqz(Timestamp thxkzyxqz) {
        this.thxkzyxqz = thxkzyxqz;
    }

    @Basic
    @Column(name = "THXKZFZRQ", nullable = true)
    public Timestamp getThxkzfzrq() {
        return thxkzfzrq;
    }

    public void setThxkzfzrq(Timestamp thxkzfzrq) {
        this.thxkzfzrq = thxkzfzrq;
    }

    @Basic
    @Column(name = "JYXKZYXQZ", nullable = true)
    public Timestamp getJyxkzyxqz() {
        return jyxkzyxqz;
    }

    public void setJyxkzyxqz(Timestamp jyxkzyxqz) {
        this.jyxkzyxqz = jyxkzyxqz;
    }

    @Basic
    @Column(name = "JYXKZFZRQ", nullable = true)
    public Timestamp getJyxkzfzrq() {
        return jyxkzfzrq;
    }

    public void setJyxkzfzrq(Timestamp jyxkzfzrq) {
        this.jyxkzfzrq = jyxkzfzrq;
    }

    @Basic
    @Column(name = "JYFZRXB", nullable = true)
    public Byte getJyfzrxb() {
        return jyfzrxb;
    }

    public void setJyfzrxb(Byte jyfzrxb) {
        this.jyfzrxb = jyfzrxb;
    }

    @Basic
    @Column(name = "FRXB", nullable = true)
    public Byte getFrxb() {
        return frxb;
    }

    public void setFrxb(Byte frxb) {
        this.frxb = frxb;
    }

    @Basic
    @Column(name = "JZGD", nullable = true, precision = 0)
    public Integer getJzgd() {
        return jzgd;
    }

    public void setJzgd(Integer jzgd) {
        this.jzgd = jzgd;
    }

    @Basic
    @Column(name = "JZCS", nullable = true, precision = 0)
    public Integer getJzcs() {
        return jzcs;
    }

    public void setJzcs(Integer jzcs) {
        this.jzcs = jzcs;
    }

    @Basic
    @Column(name = "CRKSL", nullable = true, precision = 0)
    public Integer getCrksl() {
        return crksl;
    }

    public void setCrksl(Integer crksl) {
        this.crksl = crksl;
    }

    @Basic
    @Column(name = "JYXKZFZJG", nullable = true, length = 200)
    public String getJyxkzfzjg() {
        return jyxkzfzjg;
    }

    public void setJyxkzfzjg(String jyxkzfzjg) {
        this.jyxkzfzjg = jyxkzfzjg;
    }

    @Basic
    @Column(name = "XMNR", nullable = true, length = 500)
    public String getXmnr() {
        return xmnr;
    }

    public void setXmnr(String xmnr) {
        this.xmnr = xmnr;
    }

    @Basic
    @Column(name = "JYXKZFZJGDM", nullable = true, length = 64)
    public String getJyxkzfzjgdm() {
        return jyxkzfzjgdm;
    }

    public void setJyxkzfzjgdm(String jyxkzfzjgdm) {
        this.jyxkzfzjgdm = jyxkzfzjgdm;
    }

    @Basic
    @Column(name = "MTZPZP", nullable = true, length = 64)
    public String getMtzpzp() {
        return mtzpzp;
    }

    public void setMtzpzp(String mtzpzp) {
        this.mtzpzp = mtzpzp;
    }

    @Basic
    @Column(name = "GDJYCSCQBH", nullable = true, length = 100)
    public String getGdjycscqbh() {
        return gdjycscqbh;
    }

    public void setGdjycscqbh(String gdjycscqbh) {
        this.gdjycscqbh = gdjycscqbh;
    }

    @Basic
    @Column(name = "GDJYCSSYQBH", nullable = true, length = 100)
    public String getGdjycssyqbh() {
        return gdjycssyqbh;
    }

    public void setGdjycssyqbh(String gdjycssyqbh) {
        this.gdjycssyqbh = gdjycssyqbh;
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

        AppBizQyjbxx that = (AppBizQyjbxx) o;

        if (owid != null ? !owid.equals(that.owid) : that.owid != null) return false;
        if (hylbdm != null ? !hylbdm.equals(that.hylbdm) : that.hylbdm != null) return false;
        if (hylb != null ? !hylb.equals(that.hylb) : that.hylb != null) return false;
        if (qybm != null ? !qybm.equals(that.qybm) : that.qybm != null) return false;
        if (qymc != null ? !qymc.equals(that.qymc) : that.qymc != null) return false;
        if (qyqp != null ? !qyqp.equals(that.qyqp) : that.qyqp != null) return false;
        if (qyjp != null ? !qyjp.equals(that.qyjp) : that.qyjp != null) return false;
        if (qyzflbm != null ? !qyzflbm.equals(that.qyzflbm) : that.qyzflbm != null) return false;
        if (qyzflmc != null ? !qyzflmc.equals(that.qyzflmc) : that.qyzflmc != null) return false;
        if (qyfflbm != null ? !qyfflbm.equals(that.qyfflbm) : that.qyfflbm != null) return false;
        if (qyfflmc != null ? !qyfflmc.equals(that.qyfflmc) : that.qyfflmc != null) return false;
        if (gxdwbm != null ? !gxdwbm.equals(that.gxdwbm) : that.gxdwbm != null) return false;
        if (gxdwmc != null ? !gxdwmc.equals(that.gxdwmc) : that.gxdwmc != null) return false;
        if (ssdxjgajgdm != null ? !ssdxjgajgdm.equals(that.ssdxjgajgdm) : that.ssdxjgajgdm != null) return false;
        if (ssdxjgajgmc != null ? !ssdxjgajgmc.equals(that.ssdxjgajgmc) : that.ssdxjgajgmc != null) return false;
        if (ssddsjgdm != null ? !ssddsjgdm.equals(that.ssddsjgdm) : that.ssddsjgdm != null) return false;
        if (ssddsjgmc != null ? !ssddsjgmc.equals(that.ssddsjgmc) : that.ssddsjgmc != null) return false;
        if (qyzzjgdm != null ? !qyzzjgdm.equals(that.qyzzjgdm) : that.qyzzjgdm != null) return false;
        if (lxdh != null ? !lxdh.equals(that.lxdh) : that.lxdh != null) return false;
        if (yzbm != null ? !yzbm.equals(that.yzbm) : that.yzbm != null) return false;
        if (chzh != null ? !chzh.equals(that.chzh) : that.chzh != null) return false;
        if (jwdzb != null ? !jwdzb.equals(that.jwdzb) : that.jwdzb != null) return false;
        if (jjlxbm != null ? !jjlxbm.equals(that.jjlxbm) : that.jjlxbm != null) return false;
        if (jjlxmc != null ? !jjlxmc.equals(that.jjlxmc) : that.jjlxmc != null) return false;
        if (jyfwzy != null ? !jyfwzy.equals(that.jyfwzy) : that.jyfwzy != null) return false;
        if (jyfwjy != null ? !jyfwjy.equals(that.jyfwjy) : that.jyfwjy != null) return false;
        if (zczj != null ? !zczj.equals(that.zczj) : that.zczj != null) return false;
        if (jymj != null ? !jymj.equals(that.jymj) : that.jymj != null) return false;
        if (zabdm != null ? !zabdm.equals(that.zabdm) : that.zabdm != null) return false;
        if (zajbmc != null ? !zajbmc.equals(that.zajbmc) : that.zajbmc != null) return false;
        if (yyztdm != null ? !yyztdm.equals(that.yyztdm) : that.yyztdm != null) return false;
        if (yyztmc != null ? !yyztmc.equals(that.yyztmc) : that.yyztmc != null) return false;
        if (frdb != null ? !frdb.equals(that.frdb) : that.frdb != null) return false;
        if (frdbzjlb != null ? !frdbzjlb.equals(that.frdbzjlb) : that.frdbzjlb != null) return false;
        if (frdbzjhm != null ? !frdbzjhm.equals(that.frdbzjhm) : that.frdbzjhm != null) return false;
        if (frdblxfs != null ? !frdblxfs.equals(that.frdblxfs) : that.frdblxfs != null) return false;
        if (kyrq != null ? !kyrq.equals(that.kyrq) : that.kyrq != null) return false;
        if (yysj != null ? !yysj.equals(that.yysj) : that.yysj != null) return false;
        if (babh != null ? !babh.equals(that.babh) : that.babh != null) return false;
        if (barq != null ? !barq.equals(that.barq) : that.barq != null) return false;
        if (tyrq != null ? !tyrq.equals(that.tyrq) : that.tyrq != null) return false;
        if (jyfs != null ? !jyfs.equals(that.jyfs) : that.jyfs != null) return false;
        if (jydz != null ? !jydz.equals(that.jydz) : that.jydz != null) return false;
        if (dwfzrzjlb != null ? !dwfzrzjlb.equals(that.dwfzrzjlb) : that.dwfzrzjlb != null) return false;
        if (dwfzrzjhm != null ? !dwfzrzjhm.equals(that.dwfzrzjhm) : that.dwfzrzjhm != null) return false;
        if (dwfzr != null ? !dwfzr.equals(that.dwfzr) : that.dwfzr != null) return false;
        if (dwfzrlxfs != null ? !dwfzrlxfs.equals(that.dwfzrlxfs) : that.dwfzrlxfs != null) return false;
        if (xfshdw != null ? !xfshdw.equals(that.xfshdw) : that.xfshdw != null) return false;
        if (xfhgzh != null ? !xfhgzh.equals(that.xfhgzh) : that.xfhgzh != null) return false;
        if (hcdw != null ? !hcdw.equals(that.hcdw) : that.hcdw != null) return false;
        if (hcr != null ? !hcr.equals(that.hcr) : that.hcr != null) return false;
        if (hcsj != null ? !hcsj.equals(that.hcsj) : that.hcsj != null) return false;
        if (gdxx != null ? !gdxx.equals(that.gdxx) : that.gdxx != null) return false;
        if (zrs != null ? !zrs.equals(that.zrs) : that.zrs != null) return false;
        if (hdrs != null ? !hdrs.equals(that.hdrs) : that.hdrs != null) return false;
        if (bagsmc != null ? !bagsmc.equals(that.bagsmc) : that.bagsmc != null) return false;
        if (bafzrgmsfhm != null ? !bafzrgmsfhm.equals(that.bafzrgmsfhm) : that.bafzrgmsfhm != null) return false;
        if (bafzrxm != null ? !bafzrxm.equals(that.bafzrxm) : that.bafzrxm != null) return false;
        if (bafzrdh != null ? !bafzrdh.equals(that.bafzrdh) : that.bafzrdh != null) return false;
        if (bars != null ? !bars.equals(that.bars) : that.bars != null) return false;
        if (jgpxrs != null ? !jgpxrs.equals(that.jgpxrs) : that.jgpxrs != null) return false;
        if (bagsyj != null ? !bagsyj.equals(that.bagsyj) : that.bagsyj != null) return false;
        if (cksl != null ? !cksl.equals(that.cksl) : that.cksl != null) return false;
        if (bxbjsl != null ? !bxbjsl.equals(that.bxbjsl) : that.bxbjsl != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (dcbs != null ? !dcbs.equals(that.dcbs) : that.dcbs != null) return false;
        if (dcsj != null ? !dcsj.equals(that.dcsj) : that.dcsj != null) return false;
        if (zt != null ? !zt.equals(that.zt) : that.zt != null) return false;
        if (ztgbrq != null ? !ztgbrq.equals(that.ztgbrq) : that.ztgbrq != null) return false;
        if (scbz != null ? !scbz.equals(that.scbz) : that.scbz != null) return false;
        if (jqbm != null ? !jqbm.equals(that.jqbm) : that.jqbm != null) return false;
        if (zxbz != null ? !zxbz.equals(that.zxbz) : that.zxbz != null) return false;
        if (zxyy != null ? !zxyy.equals(that.zxyy) : that.zxyy != null) return false;
        if (zdskb != null ? !zdskb.equals(that.zdskb) : that.zdskb != null) return false;
        if (bcsj != null ? !bcsj.equals(that.bcsj) : that.bcsj != null) return false;
        if (lrsj != null ? !lrsj.equals(that.lrsj) : that.lrsj != null) return false;
        if (lrr != null ? !lrr.equals(that.lrr) : that.lrr != null) return false;
        if (lrdwbm != null ? !lrdwbm.equals(that.lrdwbm) : that.lrdwbm != null) return false;
        if (lrdwmc != null ? !lrdwmc.equals(that.lrdwmc) : that.lrdwmc != null) return false;
        if (thbajgdm != null ? !thbajgdm.equals(that.thbajgdm) : that.thbajgdm != null) return false;
        if (thbajg != null ? !thbajg.equals(that.thbajg) : that.thbajg != null) return false;
        if (bwbmdh != null ? !bwbmdh.equals(that.bwbmdh) : that.bwbmdh != null) return false;
        if (sfazsxt != null ? !sfazsxt.equals(that.sfazsxt) : that.sfazsxt != null) return false;
        if (azsxtsl != null ? !azsxtsl.equals(that.azsxtsl) : that.azsxtsl != null) return false;
        if (zjztdm != null ? !zjztdm.equals(that.zjztdm) : that.zjztdm != null) return false;
        if (zjztmc != null ? !zjztmc.equals(that.zjztmc) : that.zjztmc != null) return false;
        if (djrq != null ? !djrq.equals(that.djrq) : that.djrq != null) return false;
        if (nsrq != null ? !nsrq.equals(that.nsrq) : that.nsrq != null) return false;
        if (qyjc != null ? !qyjc.equals(that.qyjc) : that.qyjc != null) return false;
        if (qyjcqp != null ? !qyjcqp.equals(that.qyjcqp) : that.qyjcqp != null) return false;
        if (qyjcjp != null ? !qyjcjp.equals(that.qyjcjp) : that.qyjcjp != null) return false;
        if (sgscxfjjs != null ? !sgscxfjjs.equals(that.sgscxfjjs) : that.sgscxfjjs != null) return false;
        if (jhltlxdm != null ? !jhltlxdm.equals(that.jhltlxdm) : that.jhltlxdm != null) return false;
        if (jhltlxmc != null ? !jhltlxmc.equals(that.jhltlxmc) : that.jhltlxmc != null) return false;
        if (sbzt != null ? !sbzt.equals(that.sbzt) : that.sbzt != null) return false;
        if (wxywfl != null ? !wxywfl.equals(that.wxywfl) : that.wxywfl != null) return false;
        if (wxywfldm != null ? !wxywfldm.equals(that.wxywfldm) : that.wxywfldm != null) return false;
        if (xfshyjsbh != null ? !xfshyjsbh.equals(that.xfshyjsbh) : that.xfshyjsbh != null) return false;
        if (xfshyjsfzjg != null ? !xfshyjsfzjg.equals(that.xfshyjsfzjg) : that.xfshyjsfzjg != null) return false;
        if (xfshyjsfzjgdm != null ? !xfshyjsfzjgdm.equals(that.xfshyjsfzjgdm) : that.xfshyjsfzjgdm != null)
            return false;
        if (xfshyjsfzrq != null ? !xfshyjsfzrq.equals(that.xfshyjsfzrq) : that.xfshyjsfzrq != null) return false;
        if (yyzzyxqz != null ? !yyzzyxqz.equals(that.yyzzyxqz) : that.yyzzyxqz != null) return false;
        if (yyzzfzrq != null ? !yyzzfzrq.equals(that.yyzzfzrq) : that.yyzzfzrq != null) return false;
        if (thxkzyxqz != null ? !thxkzyxqz.equals(that.thxkzyxqz) : that.thxkzyxqz != null) return false;
        if (thxkzfzrq != null ? !thxkzfzrq.equals(that.thxkzfzrq) : that.thxkzfzrq != null) return false;
        if (jyxkzyxqz != null ? !jyxkzyxqz.equals(that.jyxkzyxqz) : that.jyxkzyxqz != null) return false;
        if (jyxkzfzrq != null ? !jyxkzfzrq.equals(that.jyxkzfzrq) : that.jyxkzfzrq != null) return false;
        if (jyfzrxb != null ? !jyfzrxb.equals(that.jyfzrxb) : that.jyfzrxb != null) return false;
        if (frxb != null ? !frxb.equals(that.frxb) : that.frxb != null) return false;
        if (jzgd != null ? !jzgd.equals(that.jzgd) : that.jzgd != null) return false;
        if (jzcs != null ? !jzcs.equals(that.jzcs) : that.jzcs != null) return false;
        if (crksl != null ? !crksl.equals(that.crksl) : that.crksl != null) return false;
        if (jyxkzfzjg != null ? !jyxkzfzjg.equals(that.jyxkzfzjg) : that.jyxkzfzjg != null) return false;
        if (xmnr != null ? !xmnr.equals(that.xmnr) : that.xmnr != null) return false;
        if (jyxkzfzjgdm != null ? !jyxkzfzjgdm.equals(that.jyxkzfzjgdm) : that.jyxkzfzjgdm != null) return false;
        if (mtzpzp != null ? !mtzpzp.equals(that.mtzpzp) : that.mtzpzp != null) return false;
        if (gdjycscqbh != null ? !gdjycscqbh.equals(that.gdjycscqbh) : that.gdjycscqbh != null) return false;
        if (gdjycssyqbh != null ? !gdjycssyqbh.equals(that.gdjycssyqbh) : that.gdjycssyqbh != null) return false;
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
        result = 31 * result + (hylbdm != null ? hylbdm.hashCode() : 0);
        result = 31 * result + (hylb != null ? hylb.hashCode() : 0);
        result = 31 * result + (qybm != null ? qybm.hashCode() : 0);
        result = 31 * result + (qymc != null ? qymc.hashCode() : 0);
        result = 31 * result + (qyqp != null ? qyqp.hashCode() : 0);
        result = 31 * result + (qyjp != null ? qyjp.hashCode() : 0);
        result = 31 * result + (qyzflbm != null ? qyzflbm.hashCode() : 0);
        result = 31 * result + (qyzflmc != null ? qyzflmc.hashCode() : 0);
        result = 31 * result + (qyfflbm != null ? qyfflbm.hashCode() : 0);
        result = 31 * result + (qyfflmc != null ? qyfflmc.hashCode() : 0);
        result = 31 * result + (gxdwbm != null ? gxdwbm.hashCode() : 0);
        result = 31 * result + (gxdwmc != null ? gxdwmc.hashCode() : 0);
        result = 31 * result + (ssdxjgajgdm != null ? ssdxjgajgdm.hashCode() : 0);
        result = 31 * result + (ssdxjgajgmc != null ? ssdxjgajgmc.hashCode() : 0);
        result = 31 * result + (ssddsjgdm != null ? ssddsjgdm.hashCode() : 0);
        result = 31 * result + (ssddsjgmc != null ? ssddsjgmc.hashCode() : 0);
        result = 31 * result + (qyzzjgdm != null ? qyzzjgdm.hashCode() : 0);
        result = 31 * result + (lxdh != null ? lxdh.hashCode() : 0);
        result = 31 * result + (yzbm != null ? yzbm.hashCode() : 0);
        result = 31 * result + (chzh != null ? chzh.hashCode() : 0);
        result = 31 * result + (jwdzb != null ? jwdzb.hashCode() : 0);
        result = 31 * result + (jjlxbm != null ? jjlxbm.hashCode() : 0);
        result = 31 * result + (jjlxmc != null ? jjlxmc.hashCode() : 0);
        result = 31 * result + (jyfwzy != null ? jyfwzy.hashCode() : 0);
        result = 31 * result + (jyfwjy != null ? jyfwjy.hashCode() : 0);
        result = 31 * result + (zczj != null ? zczj.hashCode() : 0);
        result = 31 * result + (jymj != null ? jymj.hashCode() : 0);
        result = 31 * result + (zabdm != null ? zabdm.hashCode() : 0);
        result = 31 * result + (zajbmc != null ? zajbmc.hashCode() : 0);
        result = 31 * result + (yyztdm != null ? yyztdm.hashCode() : 0);
        result = 31 * result + (yyztmc != null ? yyztmc.hashCode() : 0);
        result = 31 * result + (frdb != null ? frdb.hashCode() : 0);
        result = 31 * result + (frdbzjlb != null ? frdbzjlb.hashCode() : 0);
        result = 31 * result + (frdbzjhm != null ? frdbzjhm.hashCode() : 0);
        result = 31 * result + (frdblxfs != null ? frdblxfs.hashCode() : 0);
        result = 31 * result + (kyrq != null ? kyrq.hashCode() : 0);
        result = 31 * result + (yysj != null ? yysj.hashCode() : 0);
        result = 31 * result + (babh != null ? babh.hashCode() : 0);
        result = 31 * result + (barq != null ? barq.hashCode() : 0);
        result = 31 * result + (tyrq != null ? tyrq.hashCode() : 0);
        result = 31 * result + (jyfs != null ? jyfs.hashCode() : 0);
        result = 31 * result + (jydz != null ? jydz.hashCode() : 0);
        result = 31 * result + (dwfzrzjlb != null ? dwfzrzjlb.hashCode() : 0);
        result = 31 * result + (dwfzrzjhm != null ? dwfzrzjhm.hashCode() : 0);
        result = 31 * result + (dwfzr != null ? dwfzr.hashCode() : 0);
        result = 31 * result + (dwfzrlxfs != null ? dwfzrlxfs.hashCode() : 0);
        result = 31 * result + (xfshdw != null ? xfshdw.hashCode() : 0);
        result = 31 * result + (xfhgzh != null ? xfhgzh.hashCode() : 0);
        result = 31 * result + (hcdw != null ? hcdw.hashCode() : 0);
        result = 31 * result + (hcr != null ? hcr.hashCode() : 0);
        result = 31 * result + (hcsj != null ? hcsj.hashCode() : 0);
        result = 31 * result + (gdxx != null ? gdxx.hashCode() : 0);
        result = 31 * result + (zrs != null ? zrs.hashCode() : 0);
        result = 31 * result + (hdrs != null ? hdrs.hashCode() : 0);
        result = 31 * result + (bagsmc != null ? bagsmc.hashCode() : 0);
        result = 31 * result + (bafzrgmsfhm != null ? bafzrgmsfhm.hashCode() : 0);
        result = 31 * result + (bafzrxm != null ? bafzrxm.hashCode() : 0);
        result = 31 * result + (bafzrdh != null ? bafzrdh.hashCode() : 0);
        result = 31 * result + (bars != null ? bars.hashCode() : 0);
        result = 31 * result + (jgpxrs != null ? jgpxrs.hashCode() : 0);
        result = 31 * result + (bagsyj != null ? bagsyj.hashCode() : 0);
        result = 31 * result + (cksl != null ? cksl.hashCode() : 0);
        result = 31 * result + (bxbjsl != null ? bxbjsl.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (dcbs != null ? dcbs.hashCode() : 0);
        result = 31 * result + (dcsj != null ? dcsj.hashCode() : 0);
        result = 31 * result + (zt != null ? zt.hashCode() : 0);
        result = 31 * result + (ztgbrq != null ? ztgbrq.hashCode() : 0);
        result = 31 * result + (scbz != null ? scbz.hashCode() : 0);
        result = 31 * result + (jqbm != null ? jqbm.hashCode() : 0);
        result = 31 * result + (zxbz != null ? zxbz.hashCode() : 0);
        result = 31 * result + (zxyy != null ? zxyy.hashCode() : 0);
        result = 31 * result + (zdskb != null ? zdskb.hashCode() : 0);
        result = 31 * result + (bcsj != null ? bcsj.hashCode() : 0);
        result = 31 * result + (lrsj != null ? lrsj.hashCode() : 0);
        result = 31 * result + (lrr != null ? lrr.hashCode() : 0);
        result = 31 * result + (lrdwbm != null ? lrdwbm.hashCode() : 0);
        result = 31 * result + (lrdwmc != null ? lrdwmc.hashCode() : 0);
        result = 31 * result + (thbajgdm != null ? thbajgdm.hashCode() : 0);
        result = 31 * result + (thbajg != null ? thbajg.hashCode() : 0);
        result = 31 * result + (bwbmdh != null ? bwbmdh.hashCode() : 0);
        result = 31 * result + (sfazsxt != null ? sfazsxt.hashCode() : 0);
        result = 31 * result + (azsxtsl != null ? azsxtsl.hashCode() : 0);
        result = 31 * result + (zjztdm != null ? zjztdm.hashCode() : 0);
        result = 31 * result + (zjztmc != null ? zjztmc.hashCode() : 0);
        result = 31 * result + (djrq != null ? djrq.hashCode() : 0);
        result = 31 * result + (nsrq != null ? nsrq.hashCode() : 0);
        result = 31 * result + (qyjc != null ? qyjc.hashCode() : 0);
        result = 31 * result + (qyjcqp != null ? qyjcqp.hashCode() : 0);
        result = 31 * result + (qyjcjp != null ? qyjcjp.hashCode() : 0);
        result = 31 * result + (sgscxfjjs != null ? sgscxfjjs.hashCode() : 0);
        result = 31 * result + (jhltlxdm != null ? jhltlxdm.hashCode() : 0);
        result = 31 * result + (jhltlxmc != null ? jhltlxmc.hashCode() : 0);
        result = 31 * result + (sbzt != null ? sbzt.hashCode() : 0);
        result = 31 * result + (wxywfl != null ? wxywfl.hashCode() : 0);
        result = 31 * result + (wxywfldm != null ? wxywfldm.hashCode() : 0);
        result = 31 * result + (xfshyjsbh != null ? xfshyjsbh.hashCode() : 0);
        result = 31 * result + (xfshyjsfzjg != null ? xfshyjsfzjg.hashCode() : 0);
        result = 31 * result + (xfshyjsfzjgdm != null ? xfshyjsfzjgdm.hashCode() : 0);
        result = 31 * result + (xfshyjsfzrq != null ? xfshyjsfzrq.hashCode() : 0);
        result = 31 * result + (yyzzyxqz != null ? yyzzyxqz.hashCode() : 0);
        result = 31 * result + (yyzzfzrq != null ? yyzzfzrq.hashCode() : 0);
        result = 31 * result + (thxkzyxqz != null ? thxkzyxqz.hashCode() : 0);
        result = 31 * result + (thxkzfzrq != null ? thxkzfzrq.hashCode() : 0);
        result = 31 * result + (jyxkzyxqz != null ? jyxkzyxqz.hashCode() : 0);
        result = 31 * result + (jyxkzfzrq != null ? jyxkzfzrq.hashCode() : 0);
        result = 31 * result + (jyfzrxb != null ? jyfzrxb.hashCode() : 0);
        result = 31 * result + (frxb != null ? frxb.hashCode() : 0);
        result = 31 * result + (jzgd != null ? jzgd.hashCode() : 0);
        result = 31 * result + (jzcs != null ? jzcs.hashCode() : 0);
        result = 31 * result + (crksl != null ? crksl.hashCode() : 0);
        result = 31 * result + (jyxkzfzjg != null ? jyxkzfzjg.hashCode() : 0);
        result = 31 * result + (xmnr != null ? xmnr.hashCode() : 0);
        result = 31 * result + (jyxkzfzjgdm != null ? jyxkzfzjgdm.hashCode() : 0);
        result = 31 * result + (mtzpzp != null ? mtzpzp.hashCode() : 0);
        result = 31 * result + (gdjycscqbh != null ? gdjycscqbh.hashCode() : 0);
        result = 31 * result + (gdjycssyqbh != null ? gdjycssyqbh.hashCode() : 0);
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
