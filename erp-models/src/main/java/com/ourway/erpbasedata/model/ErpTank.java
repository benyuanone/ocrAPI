package com.ourway.erpbasedata.model;

import com.ourway.base.model.UUidEntity;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.model.OurwaySysApiDetail;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.utils.DicUtils;
import com.ourway.sys.utils.I18nUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/5/4.
 */
@Entity
@Table(name = "erp_tank")
public class ErpTank extends UUidEntity {
    @Basic
    @Column(name = "TANK_ID", nullable = false, length = 16)
    private String tankId;
    @Basic
    @Column(name = "MAX_CAPACITY", nullable = true, precision = 6)
    private BigDecimal maxCapacity;
    @Basic
    @Column(name = "SAFE_CAPACITY", nullable = false, precision = 6)
    private BigDecimal safeCapacity;
    @Basic
    @Column(name = "MAX_HEIGHT", nullable = true, precision = 6)
    private BigDecimal maxHeight;
    @Basic
    @Column(name = "SAFE_HEIGHT", nullable = false, precision = 6)
    private BigDecimal safeHeight;
    @Basic
    @Column(name = "LOW_HIGHLY", nullable = true, precision = 6)
    private BigDecimal lowHighly;
    @Basic
    @Column(name = "AHEAD_HIGHLY", nullable = true, precision = 6)
    private BigDecimal aheadHighly;
    @Basic
    @Column(name = "TANK_TYPE", nullable = true, length = 64)
    private String tankType;
    @Column(name = "TANK_STRUCTURE", nullable = true, length = 64)
    private String tankStructure;
    @Column(name = "LIBRARY_AREA", nullable = true, length = 64)
    private String libraryArea;
    @Transient
    private String tankTypeLabel;
    @Basic
    @Column(name = "MAX_FLOAT_HEIGHT", nullable = true, precision = 6)
    private BigDecimal maxFloatHeight;
    @Basic
    @Column(name = "MIN_FLOAT_HEIGHT", nullable = true, precision = 6)
    private BigDecimal minFloatHeight;
    @Basic
    @Column(name = "FLOAT_TOP", nullable = true)
    private Byte floatTop;
    @Transient
    private String floatTopStr;
    @Basic
    @Column(name = "FLOAT_WEIGHT", nullable = true, precision = 6)
    private BigDecimal floatWeight;
    @Basic
    @Column(name = "SPHERICAL_TANK", nullable = true)
    private Byte sphericalTank;
    @Basic
    @Column(name = "MATERIAL", nullable = true, length = 64)
    private String material;
    @Transient
    private String materialLabel;
    @Basic
    @Column(name = "COAT", nullable = true, length = 255)
    private String coat;
    @Basic
    @Column(name = "TANK_PROPERTY", nullable = true, length = 64)
    private String tankProperty;
    @Transient
    private String tankPropertyLabel;
    @Basic
    @Column(name = "TECHNIQUE", nullable = true, length = 800)
    private String technique;
    @Transient
    private String techniqueLabel;
    @Basic
    @Column(name = "HEAT_TYEP", nullable = true, length = 64)
    private String heatTyep;
    @Transient
    private String heatTyepLabel;
    @Basic
    @Column(name = "GOODS_REQUIRE", nullable = true, length = 255)
    private String goodsRequire;
    @Basic
    @Column(name = "LIMITS", nullable = true)
    private Byte limits;
    @Basic
    @Column(name = "ANNEX", nullable = true, length = 255)
    private String annex;
    @Basic
    @Column(name = "ANNEX_NAME", nullable = true, length = 255)
    private String annexName;
    @Basic
    @Column(name = "REMARK", nullable = true, length = 800)
    private String remark;

    @Basic
    @Column(name = "DELFLG", nullable = true)
    private Integer delflg;
    public Integer getDelflg() {
        return delflg;
    }

    public void setDelflg(Integer delflg) {
        this.delflg = delflg;
    }

    public String getTankStructure() {
        return tankStructure;
    }

    public String getLibraryArea() {
        return libraryArea;
    }

    public void setTankStructure(String tankStructure) {
        this.tankStructure = tankStructure;
    }

    public void setLibraryArea(String libraryArea) {
        this.libraryArea = libraryArea;
    }

    public String getFloatTopStr() {
        String content = "";
        if(null!=this.floatTop&&floatTop.intValue()==1)
            content = "1-";
        else
            content = "0-";
        if(null!=floatWeight)
            content += floatWeight;
        else
            content +="0";
        return content;
    }

    public void setFloatTopStr(String floatTopStr) {
        if(floatTopStr.indexOf("-")>=0){
            String[] tmp = floatTopStr.split("\\-");
            if("1".equals(tmp[0]))
                this.floatTop =1;
            else
                this.floatTop = 0;
            if(!TextUtils.isEmpty(tmp[1]))
                this.floatWeight = new BigDecimal(tmp[1]);
        }else{
            this.floatTop = 0;
        }
        this.floatTopStr = floatTopStr;
    }

//    public String getTankTypeLabel() {
//        if (!TextUtils.isEmpty(tankType)) {
//            OurwaySysDicValue dicValue = DicUtils.getDicVal(tankType);
//            if (null == dicValue)
//                return "";
//            else
//                return I18nUtils.getLanguageContent(dicValue.getDicVal2());
//        }
//
//        return tankTypeLabel;
//    }

    public void setTankTypeLabel(String tankTypeLabel) {
        this.tankTypeLabel = tankTypeLabel;
    }

//    public String getMaterialLabel() {
//        if (!TextUtils.isEmpty(material)) {
//            OurwaySysDicValue dicValue = DicUtils.getDicVal(material);
//            if (null == dicValue)
//                return "";
//            else
//                return I18nUtils.getLanguageContent(dicValue.getDicVal2());
//        }
//        return materialLabel;
//    }

    public void setMaterialLabel(String materialLabel) {
        this.materialLabel = materialLabel;
    }

//    public String getTankPropertyLabel() {
//        if (!TextUtils.isEmpty(tankProperty)) {
//            OurwaySysDicValue dicValue = DicUtils.getDicVal(tankProperty);
//            if (null == dicValue)
//                return "";
//            else
//                return I18nUtils.getLanguageContent(dicValue.getDicVal2());
//        }
//        return tankPropertyLabel;
//    }

    public void setTankPropertyLabel(String tankPropertyLabel) {
        this.tankPropertyLabel = tankPropertyLabel;
    }

//    public String getTechniqueLabel() {
//        if (!TextUtils.isEmpty(technique)) {
//            return DicUtils.getDicVals(technique,"dicVal2");
//        }
//        return techniqueLabel;
//    }

    public void setTechniqueLabel(String techniqueLabel) {
        this.techniqueLabel = techniqueLabel;
    }

//    public String getHeatTyepLabel() {
//        if (!TextUtils.isEmpty(heatTyep)) {
//            OurwaySysDicValue dicValue = DicUtils.getDicVal(heatTyep);
//            if (null == dicValue)
//                return "";
//            else
//                return I18nUtils.getLanguageContent(dicValue.getDicVal2());
//        }
//        return heatTyepLabel;
//    }

    public void setHeatTyepLabel(String heatTyepLabel) {
        this.heatTyepLabel = heatTyepLabel;
    }

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }

    public BigDecimal getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(BigDecimal maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public BigDecimal getSafeCapacity() {
        return safeCapacity;
    }

    public void setSafeCapacity(BigDecimal safeCapacity) {
        this.safeCapacity = safeCapacity;
    }

    public BigDecimal getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(BigDecimal maxHeight) {
        this.maxHeight = maxHeight;
    }

    public BigDecimal getSafeHeight() {
        return safeHeight;
    }

    public void setSafeHeight(BigDecimal safeHeight) {
        this.safeHeight = safeHeight;
    }

    public BigDecimal getLowHighly() {
        return lowHighly;
    }

    public void setLowHighly(BigDecimal lowHighly) {
        this.lowHighly = lowHighly;
    }

    public BigDecimal getAheadHighly() {
        return aheadHighly;
    }

    public void setAheadHighly(BigDecimal aheadHighly) {
        this.aheadHighly = aheadHighly;
    }

    public String getTankType() {
        return tankType;
    }

    public void setTankType(String tankType) {
        this.tankType = tankType;
    }

    public BigDecimal getMaxFloatHeight() {
        return maxFloatHeight;
    }

    public void setMaxFloatHeight(BigDecimal maxFloatHeight) {
        this.maxFloatHeight = maxFloatHeight;
    }

    public BigDecimal getMinFloatHeight() {
        return minFloatHeight;
    }

    public void setMinFloatHeight(BigDecimal minFloatHeight) {
        this.minFloatHeight = minFloatHeight;
    }

    public Byte getFloatTop() {
        return floatTop;
    }

    public void setFloatTop(Byte floatTop) {
        this.floatTop = floatTop;
    }

    public BigDecimal getFloatWeight() {
        return floatWeight;
    }

    public void setFloatWeight(BigDecimal floatWeight) {
        this.floatWeight = floatWeight;
    }

    public Byte getSphericalTank() {
        return sphericalTank;
    }

    public void setSphericalTank(Byte sphericalTank) {
        this.sphericalTank = sphericalTank;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }


    public String getCoat() {
        return coat;
    }

    public void setCoat(String coat) {
        this.coat = coat;
    }


    public String getTankProperty() {
        return tankProperty;
    }

    public void setTankProperty(String tankProperty) {
        this.tankProperty = tankProperty;
    }


    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }


    public String getHeatTyep() {
        return heatTyep;
    }

    public void setHeatTyep(String heatTyep) {
        this.heatTyep = heatTyep;
    }


    public String getGoodsRequire() {
        return goodsRequire;
    }

    public void setGoodsRequire(String goodsRequire) {
        this.goodsRequire = goodsRequire;
    }


    public Byte getLimits() {
        return limits;
    }

    public void setLimits(Byte limits) {
        this.limits = limits;
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

}
