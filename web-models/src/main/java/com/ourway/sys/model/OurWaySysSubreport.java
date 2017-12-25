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
@Table(name = "ourway_sys_subreport")
public class OurWaySysSubreport extends UUidEntity {
    @Column(name = "REPORT_REF_OWID", nullable = true, length = 64)
    private String reportRefOwid;
    @Column(name = "REPORT_CODE", nullable = true, length = 64)
    private String reportCode;
    @Column(name = "REPORT_NAME", nullable = true, length = 240)
    private String reportName;
    @Column(name = "REPORT_DESC", nullable = true, length = 2400)
    private String reportDesc;
    @Column(name = "REPORT_TITLE", nullable = true, length = 240)
    private String reportTitle;
    @Column(name = "JRXML_PATH", nullable = true, length = 240)
    private String jrxmlPath;
    @Column(name = "INDEXNO", nullable = true, length = 240)
    private Integer indexno;

    @Transient
    private Integer updateFlag;

    public Integer getIndexno() {
        return indexno;
    }

    public void setIndexno(Integer indexno) {
        this.indexno = indexno;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getReportRefOwid() {
        return reportRefOwid;
    }

    public void setReportRefOwid(String reportRefOwid) {
        this.reportRefOwid = reportRefOwid;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getJrxmlPath() {
        return jrxmlPath;
    }

    public void setJrxmlPath(String jrxmlPath) {
        this.jrxmlPath = jrxmlPath;
    }
}
