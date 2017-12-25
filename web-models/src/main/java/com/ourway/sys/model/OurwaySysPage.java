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
@Table(name = "ourway_sys_page")
public class OurwaySysPage extends UUidEntity {

    @Column(name = "PAGE_NO", nullable = true, length = 16)
    private String pageNo;
    @Column(name = "PAGE_NAME", nullable = true, length = 240)
    private String pageName;
    @Column(name = "PAGE_CA", nullable = true, length = 240)
    private String pageCa;
    @Column(name = "PAGE_MENU", nullable = true)
    private Integer pageMenu;
    @Column(name = "PAGE_TEMPLATE", nullable = true)
    private String pageTemplate;
    @Transient
    private String pageTemplateLabel;
    @Transient
    private String pageTemplatePath;
    @Column(name = "PAGE_STATUS", nullable = true)
    private Byte pageStatus;
    @Transient
    private String pageStatusLabel;
    @Column(name = "PAGE_REPORT", nullable = true)
    private Byte pageReport;
    @Column(name = "PAGE_CUSTOMER", nullable = true)
    private Byte pageCustomer;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;
    @Column(name = "PAGE_INIT", nullable = true, length = 240)
    private String pageInit;
    @Column(name = "PAGE_PARAMS", nullable = true, length = 240)
    private String pageParams;
    @Column(name = "LANGUAGE", nullable = true, length = 16)
    private String language;
    @Column(name = "PAGE_TYPE", nullable = true, length = 16)
    private Integer pageType;
    @Column(name = "PAGE_PATH", nullable = true, length = 16)
    private String pagePath;
    @Column(name = "PAGE_TYPE_NAME", nullable = true, length = 16)
    private String pageTypeName;
    @Column(name = "PAGE_INIT_AFTER", nullable = true, length = 240)
    private String pageInitAfter;
    @Transient
    private List<OurwaySysLayout> layoutList;
    @Transient
    private List<OurwaySysPageComponent> components;
    @Transient
    private List<OurwaySysLayout> tabList;

    public String getPageInitAfter() {
        return pageInitAfter;
    }

    public void setPageInitAfter(String pageInitAfter) {
        this.pageInitAfter = pageInitAfter;
    }

    public List<OurwaySysLayout> getTabList() {
        return tabList;
    }

    public void setTabList(List<OurwaySysLayout> tabList) {
        this.tabList = tabList;
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

    public String getPageTemplatePath() {
        return pageTemplatePath;
    }

    public void setPageTemplatePath(String pageTemplatePath) {
        this.pageTemplatePath = pageTemplatePath;
    }

    public void setPageTemplateLabel(String pageTemplateLabel) {
        this.pageTemplateLabel = pageTemplateLabel;
    }

    public String getPageTemplateLabel() {
//        if (null != this.pageTemplate) {
//            OurwaySysTemplate template = CacheUtil.getVal(CacheUtil.PAGE_TEMPLATE + this.pageTemplate, OurwaySysTemplate.class);
//            if (null != template)
//                return template.getTemplateName();
//        }
        return pageTemplateLabel;
    }

    public String getPageInit() {
        return pageInit;
    }

    public void setPageInit(String pageInit) {
        this.pageInit = pageInit;
    }

    public List<OurwaySysLayout> getLayoutList() {
        return layoutList;
    }

    public void setLayoutList(List<OurwaySysLayout> layoutList) {
        this.layoutList = layoutList;
    }

    public List<OurwaySysPageComponent> getComponents() {
        return components;
    }

    public void setComponents(List<OurwaySysPageComponent> components) {
        this.components = components;
    }

    public Byte getPageCustomer() {
        return pageCustomer;
    }

    public void setPageCustomer(Byte pageCustomer) {
        this.pageCustomer = pageCustomer;
    }

    public String getPageStatusLabel() {
        if (null != this.pageStatus) {
            switch (this.pageStatus) {
                case 0:
                    return "未发布";
                case 1:
                    return "已发布";
                case 2:
                    return "已过期";
            }
        }
        return pageStatusLabel;
    }

    public void setPageStatusLabel(String pageStatusStr) {
        this.pageStatusLabel = pageStatusStr;
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

    public Byte getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Byte pageStatus) {
        this.pageStatus = pageStatus;
    }

    public Byte getPageReport() {
        return pageReport;
    }

    public void setPageReport(Byte pageReport) {
        this.pageReport = pageReport;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
