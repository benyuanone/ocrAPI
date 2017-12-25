package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_language_mult")
public class OurwaySysLanguageMult extends UUidEntity {

    @Column(name = "LANGUAGE_REF_OWID", nullable = true, length = 64)
    private String languageRefOwid;
    @Column(name = "LANGUAGE", nullable = true, length = 16)
    private String language;
    @Column(name = "LABEL_CONTENT", nullable = true, length = 64)
    private String labelContent;
    @Column(name = "LABEL_TIPS", nullable = true, length = 640)
    private String labelTips;
    @Column(name = "LABEL_FLOAT", nullable = true)
    private Byte labelFloat;
    @Column(name = "INDEX_NO", nullable = true)
    private Integer indexNo;
    @Transient
    private Integer updateFlag;//表示子表操作的标志，0 新增，1 修改 2 删除 4 不动

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getLanguageRefOwid() {
        return languageRefOwid;
    }

    public void setLanguageRefOwid(String languageRefOwid) {
        this.languageRefOwid = languageRefOwid;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public String getLabelContent() {
        return labelContent;
    }

    public void setLabelContent(String labelContent) {
        this.labelContent = labelContent;
    }


    public String getLabelTips() {
        return labelTips;
    }

    public void setLabelTips(String labelTips) {
        this.labelTips = labelTips;
    }


    public Byte getLabelFloat() {
        return labelFloat;
    }

    public void setLabelFloat(Byte labelFloat) {
        this.labelFloat = labelFloat;
    }


}
