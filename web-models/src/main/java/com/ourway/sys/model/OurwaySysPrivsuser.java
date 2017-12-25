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
@Table(name = "ourway_sys_privsuser")
public class OurwaySysPrivsuser extends UUidEntity {

    @Column(name = "ROLE_REF_OWID", nullable = true, length = 64)
    private String roleRefOwid;
    @Column(name = "USER_REF_OWID", nullable = true, length = 64)
    private String userRefOwid;
    @Column(name = "LANGUAGE", nullable = true, length = 16)
    private String language;
    @Transient
    private Integer updateFlag;
    @Transient
    private OurwaySysRoles ourwaySysRoles;

    public OurwaySysRoles getOurwaySysRoles() {
        return ourwaySysRoles;
    }

    public void setOurwaySysRoles(OurwaySysRoles ourwaySysRoles) {
        this.ourwaySysRoles = ourwaySysRoles;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getRoleRefOwid() {
        return roleRefOwid;
    }

    public void setRoleRefOwid(String roleRefOwid) {
        this.roleRefOwid = roleRefOwid;
    }

    public String getUserRefOwid() {
        return userRefOwid;
    }

    public void setUserRefOwid(String userRefOwid) {
        this.userRefOwid = userRefOwid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
