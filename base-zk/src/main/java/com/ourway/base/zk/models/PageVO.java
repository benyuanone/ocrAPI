package com.ourway.base.zk.models;

import com.ourway.base.utils.JsonUtil;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 PageVO : <p>
 * <p>说明:页面的vod</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/25 11:18
 * </pre>
 */
public class PageVO implements Serializable {
    private String pageNo;
    private String pageName;
    private String pageCa;
    private Integer pageMenu;
    private String pageTemplate;
    private String pageTemplateLabel;
    private String pageTemplatePath;
    private Integer pageStatus;
    private String pageStatusStr;
    private Integer pageReport;
    private Integer pageCustomer;
    private String memo;
    private String pageInit;
    private String pageInitAfter;
    private String language;
    private String pageParams;
    private Integer pageType;
    private String pagePath;
    private String pageTypeName;

    public String getPageInitAfter() {
        return pageInitAfter;
    }

    public void setPageInitAfter(String pageInitAfter) {
        this.pageInitAfter = pageInitAfter;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getPageTypeName() {
        return pageTypeName;
    }

    public void setPageTypeName(String pageTypeName) {
        this.pageTypeName = pageTypeName;
    }

    public String getPageParams() {
        return pageParams;
    }

    public void setPageParams(String pageParams) {

        this.pageParams = pageParams;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageCa() {
        return pageCa;
    }

    public void setPageCa(String pageCa) {
        this.pageCa = pageCa;
    }

    public Integer getPageMenu() {
        return pageMenu;
    }

    public void setPageMenu(Integer pageMenu) {
        this.pageMenu = pageMenu;
    }

    public String getPageTemplate() {
        return pageTemplate;
    }

    public void setPageTemplate(String pageTemplate) {
        this.pageTemplate = pageTemplate;
    }

    public String getPageTemplateLabel() {
        return pageTemplateLabel;
    }

    public void setPageTemplateLabel(String pageTemplateLabel) {
        this.pageTemplateLabel = pageTemplateLabel;
    }

    public String getPageTemplatePath() {
        return pageTemplatePath;
    }

    public void setPageTemplatePath(String pageTemplatePath) {
        this.pageTemplatePath = pageTemplatePath;
    }

    public Integer getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Integer pageStatus) {
        this.pageStatus = pageStatus;
    }

    public String getPageStatusStr() {
        return pageStatusStr;
    }

    public void setPageStatusStr(String pageStatusStr) {
        this.pageStatusStr = pageStatusStr;
    }

    public Integer getPageReport() {
        return pageReport;
    }

    public void setPageReport(Integer pageReport) {
        this.pageReport = pageReport;
    }

    public Integer getPageCustomer() {
        return pageCustomer;
    }

    public void setPageCustomer(Integer pageCustomer) {
        this.pageCustomer = pageCustomer;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPageInit() {
        return pageInit;
    }

    public void setPageInit(String pageInit) {
        this.pageInit = pageInit;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
