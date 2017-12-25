package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;
import com.ourway.base.utils.TextUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_object_attribute")
public class OurwaySysObjectAttribute extends UUidEntity {


    @Column(name = "OBJECT_REF_OWID", nullable = true, length = 64)
    private String objectRefOwid;
    @Column(name = "ATTRIBUTE_NAME", nullable = true, length = 64)
    private String attributeName;
    @Column(name = "ATTRIBUTE_LABEL_ID", nullable = true, length = 64)
    private String attributeLabelId;
    @Column(name = "ATTRIBUTE_DATA_TYPE", nullable = true, length = 16)
    private String attributeDataType;
    @Column(name = "ATTRIBUTE_IS_CHANGE", nullable = true)
    private Byte attributeIsChange;
    @Column(name = "ATTRIBUTE_FORMAT", nullable = true, length = 64)
    private String attributeFormat;
    @Column(name = "ATTRIBUTE_DEFAULT_VALUE", nullable = true, length = 240)
    private String attributeDefaultValue;
    @Column(name = "ATTRIBUTE_KJTYPE", nullable = true, length = 240)
    private String atttributeKjtype;
    @Column(name = "ATTRIBUTE_REQUIRED", nullable = true)
    private Byte attributeRequired;
    @Column(name = "ATTRIBUTE_CHNAME", nullable = true, length = 240)
    private String attributeChname;
    @Column(name = "ATTRIBUTE_DISPLAY", nullable = true)
    private Byte attributeDisplay;
    @Column(name = "ATTRIBUTE_DISPLAYCLASS", nullable = true, length = 240)
    private String attributeDisplayclass;
    @Transient
    private List<OurwaySysLogSettingAttribute> logSettingAttributesList;
    @Transient
    private String className;
    @Transient
    private Integer updateFlag;//表示子表操作的标志，0 新增，1 修改 2 删除 4 不动

    public String getAttributeDisplayclass() {
        return attributeDisplayclass;
    }

    public void setAttributeDisplayclass(String attributeDisplayclass) {
        this.attributeDisplayclass = attributeDisplayclass;
    }

    public Byte getAttributeDisplay() {
        return attributeDisplay;
    }

    public void setAttributeDisplay(Byte attributeDisplay) {
        this.attributeDisplay = attributeDisplay;
    }

    public String getAtttributeKjtype() {
        return atttributeKjtype;
    }

    public void setAtttributeKjtype(String atttributeKjtype) {
        this.atttributeKjtype = atttributeKjtype;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getObjectRefOwid() {
        return objectRefOwid;
    }

    public void setObjectRefOwid(String objectRefOwid) {
        this.objectRefOwid = objectRefOwid;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeLabelId() {
        return attributeLabelId;
    }

    public void setAttributeLabelId(String attributeLabelId) {
        this.attributeLabelId = attributeLabelId;
    }

    public String getAttributeDataType() {
        return attributeDataType;
    }

    public void setAttributeDataType(String attributeDataType) {
        this.attributeDataType = attributeDataType;
    }

    public Byte getAttributeIsChange() {
        return attributeIsChange;
    }

    public void setAttributeIsChange(Byte attributeIsChange) {
        this.attributeIsChange = attributeIsChange;
    }

    public String getAttributeFormat() {
        return attributeFormat;
    }

    public void setAttributeFormat(String attributeFormat) {
        this.attributeFormat = attributeFormat;
    }

    public String getAttributeDefaultValue() {
        return attributeDefaultValue;
    }

    public void setAttributeDefaultValue(String attributeDefaultValue) {
        this.attributeDefaultValue = attributeDefaultValue;
    }

    public Byte getAttributeRequired() {
        return attributeRequired;
    }

    public void setAttributeRequired(Byte attributeRequired) {
        this.attributeRequired = attributeRequired;
    }

    public String getAttributeChname() {
        return attributeChname;
    }

    public void setAttributeChname(String attributeChname) {
        this.attributeChname = attributeChname;
    }

    public List<OurwaySysLogSettingAttribute> getLogSettingAttributesList() {
        return logSettingAttributesList;
    }

    public void setLogSettingAttributesList(List<OurwaySysLogSettingAttribute> logSettingAttributesList) {
        this.logSettingAttributesList = logSettingAttributesList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
