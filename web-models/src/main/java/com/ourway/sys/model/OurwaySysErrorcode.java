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
@Table(name = "ourway_sys_errorcode")
public class OurwaySysErrorcode extends UUidEntity {

    @Column(name = "ERROR_CODE", nullable = true, length = 64)
    private String errorCode;
    @Column(name = "ERROR_CHN_NAME", nullable = true, length = 160)
    private String errorChnName;
    @Column(name = "ERROR_CHN_DESC", nullable = true, length = 1600)
    private String errorChnDesc;
    @Column(name = "ERROR_LABEL", nullable = true, length = 64)
    private String errorLabel;
    @Column(name = "ERROR_TYPE", nullable = true)
    private Integer errorType;
    @Column(name = "ERROR_TYPE_PATH", nullable = true, length = 160)
    private String errorTypePath;
    @Column(name = "ERROR_TYPE_NAME", nullable = true, length = 160)
    private String errorTypeName;
    @Column(name = "TEMPLATE_VISIBLE", nullable = true)
    private Byte templateVisible;

    @Transient
    private String visibleLabel;    // 1: 有效  0：失效

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorChnName() {
        return errorChnName;
    }

    public void setErrorChnName(String errorChnName) {
        this.errorChnName = errorChnName;
    }


    public String getErrorChnDesc() {
        return errorChnDesc;
    }

    public void setErrorChnDesc(String errorChnDesc) {
        this.errorChnDesc = errorChnDesc;
    }


    public String getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String errorLabel) {
        this.errorLabel = errorLabel;
    }

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }


    public String getErrorTypePath() {
        return errorTypePath;
    }

    public void setErrorTypePath(String errorTypePath) {
        this.errorTypePath = errorTypePath;
    }


    public String getErrorTypeName() {

        return errorTypeName;
    }

    public void setErrorTypeName(String errorTypeName) {
        this.errorTypeName = errorTypeName;
    }

    public String getVisibleLabel() {
      /*
        visibleLabel = (templateVisible ==1) ? "有效":"失效";*/

        return visibleLabel;
    }

    public void setVisibleLabel(String visibleLabel) {
        this.visibleLabel = visibleLabel;
    }

    public Byte getTemplateVisible() {
        return templateVisible;
    }

    public void setTemplateVisible(Byte templateVisible) {
        this.templateVisible = templateVisible;
    }
}
