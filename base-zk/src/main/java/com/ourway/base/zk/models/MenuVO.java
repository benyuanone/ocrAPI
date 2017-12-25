package com.ourway.base.zk.models;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;


public class MenuVO implements Serializable {
    private Integer owid;
    private Integer fid;
    private String path;
    private Integer type;
    private String name;
    private String icon;
    private String pageCa;
    private String link;
    private Integer target;
    private Integer isshow;
    private Integer privsAllocate;
    private Integer privsDefault;
    private String language;
    private Integer pageType;
    private String iconClass;
    private String pageId;
    private Double px;//同层次内的排序
    private Integer cc;//树节点层次

    public Integer getCc() {
        if (null == cc)
            return 1;
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public Integer getPrivsAllocate() {
        return privsAllocate;
    }

    public void setPrivsAllocate(Integer privsAllocate) {
        this.privsAllocate = privsAllocate;
    }

    public Integer getPrivsDefault() {
        return privsDefault;
    }

    public void setPrivsDefault(Integer privsDefault) {
        this.privsDefault = privsDefault;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Integer getOwid() {
        return owid;
    }

    public void setOwid(Integer owid) {
        this.owid = owid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPageCa() {
        return pageCa;
    }

    public void setPageCa(String pageCa) {
        this.pageCa = pageCa;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Double getPx() {
        return px;
    }

    public void setPx(Double px) {
        this.px = px;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
