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
@Table(name = "ourway_sys_privsallocate")
public class OurwaySysPrivsallocate extends UUidEntity {

    @Column(name = "ROLE_REF_OWID", nullable = true, length = 64)
    private String roleRefOwid;
    @Column(name = "MENU_REF_OWID", nullable = true)
    private Integer menuRefOwid;
    @Transient
    private OurwaySysMenus menu; //页面相关信息
    @Transient
    private Integer updateFlag;

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public OurwaySysMenus getMenu() {
        return menu;
    }

    public void setMenu(OurwaySysMenus menu) {
        this.menu = menu;
    }

    public String getRoleRefOwid() {
        return roleRefOwid;
    }

    public void setRoleRefOwid(String roleRefOwid) {
        this.roleRefOwid = roleRefOwid;
    }

    public Integer getMenuRefOwid() {
        return menuRefOwid;
    }

    public void setMenuRefOwid(Integer menuRefOwid) {
        this.menuRefOwid = menuRefOwid;
    }
}
