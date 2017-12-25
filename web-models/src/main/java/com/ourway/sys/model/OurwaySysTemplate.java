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
@Table(name = "ourway_sys_template")
public class OurwaySysTemplate extends UUidEntity {

    @Column(name = "TEMPLATE_NAME", nullable = true, length = 240)
    private String templateName;
    @Column(name = "TEMPLATE_NO", nullable = true, length = 64)
    private String templateNo;
    @Column(name = "TEMPLATE_DESC", nullable = true, length = 400)
    private String templateDesc;
    @Column(name = "TEMPLATE_PATH", nullable = true, length = 400)
    private String templatePath;
    @Column(name = "TEMPLATE_VISIBLE", nullable = true)
    private Byte templateVisible;
    @Transient
    private String templateVisibleLabel;
    @Transient
    private List<OurwaySysTempcontrol> dataList;

    public void setTemplateVisibleLabel(String templateVisibleLabel) {
        this.templateVisibleLabel = templateVisibleLabel;
    }

    public String getTemplateVisibleLabel() {
        if(null!=templateVisible&&this.templateVisible==1)
            return "有效";
        return templateVisibleLabel;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public List<OurwaySysTempcontrol> getDataList() {
        return dataList;
    }

    public void setDataList(List<OurwaySysTempcontrol> dataList) {
        this.dataList = dataList;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }


    public String getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }


    public String getTemplateDesc() {
        return templateDesc;
    }

    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc;
    }


    public Byte getTemplateVisible() {
        return templateVisible;
    }

    public void setTemplateVisible(Byte templateVisible) {
        this.templateVisible = templateVisible;
    }


}
