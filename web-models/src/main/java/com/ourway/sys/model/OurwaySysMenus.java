package com.ourway.sys.model;

import com.ourway.base.model.IncrementIdEntity;
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
@Table(name = "ourway_sys_menus")
public class OurwaySysMenus extends IncrementIdEntity {
    @Column(name = "FID", nullable = true)
    private Integer fid;
    @Column(name = "PATH", nullable = true, length = 240)
    private String path;
    @Column(name = "TYPE", nullable = true)
    private Integer type;
    @Column(name = "NAME", nullable = true, length = 240)
    private String name;
    @Column(name = "ICON", nullable = true, length = 240)
    private String icon;
    @Column(name = "PAGE_CA", nullable = true, length = 64)
    private String pageCa;
    @Column(name = "LINK", nullable = true, length = 240)
    private String link;
    @Column(name = "TARGET", nullable = true)
    private Byte target;
    @Column(name = "ISSHOW", nullable = true)
    private Byte isshow;
    @Column(name = "PX", nullable = true)
    private Double px;
    @Column(name = "CC", nullable = true)
    private Integer cc;
    @Column(name = "PRIVS_ALLOCATE", nullable = true)
    private Byte privsAllocate;
    @Column(name = "PRIVS_DEFAULT", nullable = true)
    private Byte privsDefault;
    @Column(name = "LANGUAGE", nullable = true, length = 16)
    private String language;
    @Column(name = "PAGE_TYPE", nullable = true)
    private Byte pageType;
    @Column(name = "ICON_CLASS", nullable = true)
    private String iconClass;
    @Column(name = "PAGE_ID", nullable = true)
    private String pageId;
    @Transient
    private List<OurwaySysPrivsallocate> prilocateList;
    @Transient
    private List<OurwaySysMenus> menusList;

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
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

    public Byte getTarget() {
        return target;
    }

    public void setTarget(Byte target) {
        this.target = target;
    }

    public Byte getIsshow() {
        return isshow;
    }

    public void setIsshow(Byte isshow) {
        this.isshow = isshow;
    }

    public Double getPx() {
        return px;
    }

    public void setPx(Double px) {
        this.px = px;
    }

    public Byte getPrivsAllocate() {
        return privsAllocate;
    }

    public void setPrivsAllocate(Byte privsAllocate) {
        this.privsAllocate = privsAllocate;
    }

    public Byte getPrivsDefault() {
        return privsDefault;
    }

    public void setPrivsDefault(Byte privsDefault) {
        this.privsDefault = privsDefault;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Byte getPageType() {
        return pageType;
    }

    public void setPageType(Byte pageType) {
        this.pageType = pageType;
    }

    public List<OurwaySysPrivsallocate> getPrilocateList() {
        return prilocateList;
    }

    public void setPrilocateList(List<OurwaySysPrivsallocate> prilocateList) {
        this.prilocateList = prilocateList;
    }

    public List<OurwaySysMenus> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<OurwaySysMenus> menusList) {
        this.menusList = menusList;
    }
}
