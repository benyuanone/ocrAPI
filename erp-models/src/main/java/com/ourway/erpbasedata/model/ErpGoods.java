package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.utils.DicUtils;
import com.ourway.sys.utils.I18nUtils;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by CuiL on 2017-05-08.
 */
@Entity
@Table(name = "erp_goods")
public class ErpGoods extends UUidEntity {
    @Basic
    @Column(name = "ERPGOODSTYPE_REF_OWID", nullable = true, length = 64)
    private String erpgoodstypeRefOwid;
    @Basic
    @Column(name = "GOODS_ID", nullable = false, length = 16)
    private String goodsId;
    @Basic
    @Column(name = "NAME", nullable = false, length = 128)
    private String name;
    @Basic
    @Column(name = "ENG_NAME", nullable = true, length = 255)
    private String engName;
    @Basic
    @Column(name = "OTHER_NAME_ENG", nullable = true, length = 255)
    private String otherNameEng;
    @Basic
    @Column(name = "OTHER_NAME", nullable = true, length = 255)
    private String otherName;
    @Basic
    @Column(name = "MOLECULAR", nullable = true, length = 255)
    private String molecular;
    @Basic
    @Column(name = "GOODS_TYPE", nullable = false, length = 64)
    private String goodsType;
    @Basic
    @Column(name = "GATE_CODE", nullable = true, length = 255)
    private String gateCode;
    @Basic
    @Column(name = "TEMPERATURE", nullable = true, precision = 6)
    private BigDecimal temperature;
    @Basic
    @Column(name = "DENSITY", nullable = true, precision = 6)
    private BigDecimal density;
    @Basic
    @Column(name = "SOLIDIFY", nullable = true, precision = 6)
    private BigDecimal solidify;
    @Basic
    @Column(name = "FLASH", nullable = true, precision = 6)
    private BigDecimal flash;
    @Basic
    @Column(name = "FREEZE", nullable = true, precision = 6)
    private BigDecimal freeze;
    @Basic
    @Column(name = "MELT", nullable = true, precision = 6)
    private BigDecimal melt;
    @Basic
    @Column(name = "BOIL", nullable = true, precision = 6)
    private BigDecimal boil;
    @Basic
    @Column(name = "GOODS_PROPERTY", nullable = true, length = 128)
    private String goodsProperty;
    @Basic
    @Column(name = "DANGER_LEVEL", nullable = true, length = 64)
    private String dangerLevel;
    @Basic
    @Column(name = "FATALNESS", nullable = true, length = 16777215)
    private String fatalness;
    @Basic
    @Column(name = "CREDENTIALS", nullable = true, length = 64)
    private String credentials;
    @Basic
    @Column(name = "UNNO", nullable = true, length = 255)
    private String unno;
    @Basic
    @Column(name = "CASNO", nullable = true, length = 255)
    private String casno;
    @Basic
    @Column(name = "CNNO", nullable = true, length = 255)
    private String cnno;
    @Basic
    @Column(name = "SPECIALITY", nullable = true, length = 16777215)
    private String speciality;
    @Basic
    @Column(name = "PREVENT", nullable = true, length = 16777215)
    private String prevent;
    @Basic
    @Column(name = "FIRSTAID", nullable = true, length = 16777215)
    private String firstaid;
    @Basic
    @Column(name = "FIRECONTROL", nullable = true, length = 16777215)
    private String firecontrol;
    @Basic
    @Column(name = "PROJECT_LEVEL", nullable = true, length = 16777215)
    private String projectLevel;
    @Basic
    @Column(name = "STORE", nullable = true, length = 16777215)
    private String store;
    @Basic
    @Column(name = "DECLARES", nullable = true, length = 16777215)
    private String declaresText;
    @Basic
    @Column(name = "SUPERVISE", nullable = true, length = 16777215)
    private String supervise;
    @Basic
    @Column(name = "CAN_MAKE", nullable = true)
    private Byte canMake;
    @Basic
    @Column(name = "ANNEX", nullable = true, length = 255)
    private String annex;
    @Basic
    @Column(name = "ANNEX_NAME", nullable = true, length = 255)
    private String annexName;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;

    @Transient
    private List<ErpGoodsList> erpGoodsList;
//    @Transient
//    private String erpGoodsTypeStr;
//    @Transient
//    private String dangerLevelStr;
//    @Transient
//    private String credentialsStr;
//    @Transient
//    private String goodsPropertyStr;
    @Transient
    private List<ErpGoodsList> dataList;


//    public String getDangerLevelStr() {
//        if (!TextUtils.isEmpty(dangerLevel)) {
//            OurwaySysDicValue dicValue = DicUtils.getDicVal(dangerLevel);
//            if (null == dicValue)
//                return "";
//            else
//                return I18nUtils.getLanguageContent(dicValue.getDicVal2());
//        }
//        return dangerLevelStr;
//    }
//
//    public void setDangerLevelStr(String dangerLevelStr) {
//        this.dangerLevelStr = dangerLevelStr;
//    }
//
//    public String getCredentialsStr() {
//        if (!TextUtils.isEmpty(credentials)) {
//            OurwaySysDicValue dicValue = DicUtils.getDicVal(credentials);
//            if (null == dicValue)
//                return "";
//            else
//                return I18nUtils.getLanguageContent(dicValue.getDicVal2());
//        }
//        return credentialsStr;
//    }
//
//    public void setCredentialsStr(String credentialsStr) {
//        this.credentialsStr = credentialsStr;
//    }
//
//
//    public String getGoodsPropertyStr() {
//        goodsPropertyStr = "";
//        if (!TextUtils.isEmpty(goodsProperty)) {
//            String[] propertys = goodsProperty.split(",");
//            for (String pros : propertys) {
//                OurwaySysDicValue dicValue = DicUtils.getDicVal(pros);
//                if (null == dicValue)
//                    continue;
//                goodsPropertyStr = goodsPropertyStr  + I18nUtils.getLanguageContent(dicValue.getDicVal2())+ ",";
//            }
//                //return I18nUtils.getLanguageContent(dicValue.getDicVal2());
//        }
////        if (!TextUtils.isEmpty(goodsProperty)) {
////            String[] propertys = goodsProperty.split(";");
////            for (String pros : propertys) {
////                goodsPropertyStr = goodsPropertyStr + ";" + I18nUtils.getLanguageContent(pros);
////            }
////        }
//        if (goodsPropertyStr!="")
//            goodsPropertyStr = goodsPropertyStr.substring(0,goodsPropertyStr.length()-1);
//        return goodsPropertyStr;
//    }
//
//    public void setGoodsPropertyStr(String goodsPropertyStr) {
//        this.goodsPropertyStr = goodsPropertyStr;
//    }
//
//    public String getErpGoodsTypeStr() {
//        return erpGoodsTypeStr;
//    }
//
//    public void setErpGoodsTypeStr(String erpGoodsTypeStr) {
//        this.erpGoodsTypeStr = erpGoodsTypeStr;
//    }

    public List<ErpGoodsList> getErpGoodsList() {
        return erpGoodsList;
    }

    public void setErpGoodsList(List<ErpGoodsList> erpGoodsList) {
        this.erpGoodsList = erpGoodsList;
    }



    public String getErpgoodstypeRefOwid() {
        return erpgoodstypeRefOwid;
    }

    public void setErpgoodstypeRefOwid(String erpgoodstypeRefOwid) {
        this.erpgoodstypeRefOwid = erpgoodstypeRefOwid;
    }


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }


    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }


    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }


    public String getGateCode() {
        return gateCode;
    }

    public void setGateCode(String gateCode) {
        this.gateCode = gateCode;
    }


    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature==null?new BigDecimal(0):temperature;
    }


    public BigDecimal getDensity() {
        return density;
    }

    public void setDensity(BigDecimal density) {
        this.density = density==null?new BigDecimal(0):density;
    }


    public BigDecimal getSolidify() {
        return solidify;
    }

    public void setSolidify(BigDecimal solidify) {
        this.solidify =solidify==null?new BigDecimal(0):solidify;
    }


    public BigDecimal getFlash() {
        return flash;
    }

    public void setFlash(BigDecimal flash) {
        this.flash = flash==null?new BigDecimal(0):flash;
    }


    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze==null?new BigDecimal(0):freeze;
    }


    public BigDecimal getMelt() {
        return melt;
    }

    public void setMelt(BigDecimal melt) {
        this.melt = melt==null?new BigDecimal(0):melt;
    }


    public BigDecimal getBoil() {
        return boil;
    }

    public void setBoil(BigDecimal boil) {
        this.boil = boil==null?new BigDecimal(0):boil;
    }


    public String getGoodsProperty() {
        return goodsProperty;
    }

    public void setGoodsProperty(String goodsProperty) {
        this.goodsProperty = goodsProperty;
    }


    public String getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(String dangerLevel) {
        this.dangerLevel = dangerLevel;
    }


    public String getFatalness() {
        return fatalness;
    }

    public void setFatalness(String fatalness) {
        this.fatalness = fatalness;
    }


    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }


    public String getUnno() {
        return unno;
    }

    public void setUnno(String unno) {
        this.unno = unno;
    }


    public String getCnno() {
        return cnno;
    }

    public void setCnno(String cnno) {
        this.cnno = cnno;
    }


    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }


    public String getPrevent() {
        return prevent;
    }

    public void setPrevent(String prevent) {
        this.prevent = prevent;
    }


    public String getFirstaid() {
        return firstaid;
    }

    public void setFirstaid(String firstaid) {
        this.firstaid = firstaid;
    }


    public String getFirecontrol() {
        return firecontrol;
    }

    public void setFirecontrol(String firecontrol) {
        this.firecontrol = firecontrol;
    }


    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }


    public String getSupervise() {
        return supervise;
    }

    public void setSupervise(String supervise) {
        this.supervise = supervise;
    }


    public Byte getCanMake() {
        return canMake;
    }

    public void setCanMake(Byte canMake) {
        this.canMake = canMake;
    }


    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }


    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getDeclaresText() {
        return declaresText;
    }

    public void setDeclaresText(String declaresText) {
        this.declaresText = declaresText;
    }

    public List<ErpGoodsList> getDataList() {
        return dataList;
    }

    public void setDataList(List<ErpGoodsList> dataList) {
        this.dataList = dataList;
    }

    public String getOtherNameEng() {
        return otherNameEng;
    }

    public String getMolecular() {
        return molecular;
    }

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setOtherNameEng(String otherNameEng) {
        this.otherNameEng = otherNameEng;
    }

    public void setMolecular(String molecular) {
        this.molecular = molecular;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }


    public String getCasno() {
        return casno;
    }

    public void setCasno(String casno) {
        this.casno = casno;
    }
}
