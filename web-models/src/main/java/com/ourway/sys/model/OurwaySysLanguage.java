package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_language")
public class OurwaySysLanguage extends UUidEntity {

    @Column(name = "LABEL_KEY", nullable = true, length = 64)
    private String labelKey;
    @Column(name = "LABEL_NAME", nullable = true, length = 240)
    private String labelName;
    @Column(name = "LABEL_DESC", nullable = true, length = 240)
    private String labelDesc;
    @Column(name = "LABEL_TYPE", nullable = true)
    private String labelType;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;
    @Transient
    private List<OurwaySysLanguageMult> dataList = new ArrayList<OurwaySysLanguageMult>();

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }


    public String getLabelDesc() {
        return labelDesc;
    }

    public void setLabelDesc(String labelDesc) {
        this.labelDesc = labelDesc;
    }


    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<OurwaySysLanguageMult> getDataList() {
        return dataList;
    }

    public void setDataList(List<OurwaySysLanguageMult> dataList) {
        this.dataList = dataList;
    }
}
