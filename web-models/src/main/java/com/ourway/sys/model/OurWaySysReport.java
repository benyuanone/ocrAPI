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
@Table(name = "ourway_sys_report")
public class OurWaySysReport extends UUidEntity {
    @Column(name = "PAGE_REF_OWID", nullable = true, length = 64)
    private String pageRefOwid;
    @Column(name = "REPORT_CODE", nullable = true, length = 64)
    private String reportCode;
    @Column(name = "PAGE_CA", nullable = true, length = 64)
    private String pageCa;
    @Column(name = "REPORT_NAME", nullable = true, length = 240)
    private String reportName;
    @Column(name = "REPORT_DESC", nullable = true, length = 2400)
    private String reportDesc;
    @Column(name = "REPORT_TITLE", nullable = true, length = 240)
    private String reportTitle;
    @Column(name = "REPORT_DEFAULT", nullable = true)
    private Byte reportDefault;
    @Column(name = "REPORT_CLASS", nullable = true, length = 160)
    private String reportClass;
    @Column(name = "JRXML_PATH", nullable = true, length = 240)
    private String jrxmlPath;
    @Column(name = "JASPER_PATH", nullable = true, length = 240)
    private String jasperPath;
    @Column(name = "EXPORT_TIMES", nullable = true)
    private Integer exportTimes;
    @Transient
    private List<OurWaySysSubreport> subreportList;

    public List<OurWaySysSubreport> getSubreportList() {
        return subreportList;
    }

    public void setSubreportList(List<OurWaySysSubreport> subreportList) {
        this.subreportList = subreportList;
    }

    public String getPageCa() {
        return pageCa;
    }

    public void setPageCa(String pageCa) {
        this.pageCa = pageCa;
    }

    public String getPageRefOwid() {
        return pageRefOwid;
    }

    public void setPageRefOwid(String pageRefOwid) {
        this.pageRefOwid = pageRefOwid;
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

    public Byte getReportDefault() {
        return reportDefault;
    }

    public void setReportDefault(Byte reportDefault) {
        this.reportDefault = reportDefault;
    }

    public String getReportClass() {
        return reportClass;
    }

    public void setReportClass(String reportClass) {
        this.reportClass = reportClass;
    }

    public String getJrxmlPath() {
        return jrxmlPath;
    }

    public void setJrxmlPath(String jrxmlPath) {
        this.jrxmlPath = jrxmlPath;
    }

    public String getJasperPath() {
        return jasperPath;
    }

    public void setJasperPath(String jasperPath) {
        this.jasperPath = jasperPath;
    }

    public Integer getExportTimes() {
        return exportTimes;
    }

    public void setExportTimes(Integer exportTimes) {
        this.exportTimes = exportTimes;
    }
}
