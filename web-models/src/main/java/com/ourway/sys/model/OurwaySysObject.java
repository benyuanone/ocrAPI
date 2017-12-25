package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_object")
public class OurwaySysObject extends UUidEntity<OurwaySysObject> {

    @Column(name = "CLASS_NAME", nullable = true, length = 240)
    private String className;
    @Column(name = "CLASS_CHNNAME", nullable = true, length = 240)
    private String classChnname;
    @Column(name = "CLASS_LABEL_ID", nullable = true, length = 64)
    private String classLabelId;
    @Column(name = "CLASS_DESC", nullable = true, length = 240)
    private String classDesc;
    @Column(name = "CLASS_ID", nullable = true, length = 64)
    private String classId;
    @Column(name = "CLASS_PRE", nullable = true, length = 240)
    private String classPre;
    @Column(name = "CLASS_TYPE", nullable = true)
    private Byte classType;
    @Column(name = "CLASS_LOG", nullable = true)
    private Byte classLog;
    @Transient
    private List<OurwaySysLogSetting> logSettingsList;
    @Transient
    private List<OurwaySysObjectAttribute> ourwaySysObjectAttributeList;

    public Byte getClassLog() {
        return classLog;
    }

    public void setClassLog(Byte classLog) {
        this.classLog = classLog;
    }

    public String getClassPre() {
        return classPre;
    }

    public void setClassPre(String classPre) {
        this.classPre = classPre;
    }

    public List<OurwaySysObjectAttribute> getOurwaySysObjectAttributeList() {
        return ourwaySysObjectAttributeList;
    }

    public void setOurwaySysObjectAttributeList(List<OurwaySysObjectAttribute> ourwaySysObjectAttributeList) {
        this.ourwaySysObjectAttributeList = ourwaySysObjectAttributeList;
    }

    public String getClassChnname() {
        return classChnname;
    }

    public void setClassChnname(String classChnname) {
        this.classChnname = classChnname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public String getClassLabelId() {
        return classLabelId;
    }

    public void setClassLabelId(String classLabelId) {
        this.classLabelId = classLabelId;
    }


    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }


    public Byte getClassType() {
        return classType;
    }

    public void setClassType(Byte classType) {
        this.classType = classType;
    }

    public List<OurwaySysLogSetting> getLogSettingsList() {
        return logSettingsList;
    }

    public void setLogSettingsList(List<OurwaySysLogSetting> logSettingsList) {
        this.logSettingsList = logSettingsList;
    }
}
