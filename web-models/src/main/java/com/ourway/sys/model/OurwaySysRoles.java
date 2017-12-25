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
@Table(name = "ourway_sys_roles")
public class OurwaySysRoles extends UUidEntity {

    @Column(name = "ROLE_NAME", nullable = true, length = 240)
    private String roleName;
    @Column(name = "ROLE_TYPE", nullable = true)
    private Integer roleType;
    @Column(name = "ROLE_MEMO", nullable = true, length = 240)
    private String roleMemo;
    @Column(name = "ISDEFAULT", nullable = true)
    private Byte isdefault;
    @Column(name = "LANGUAGE", nullable = true, length = 16)
    private String language;
    @Transient
    private String roles;//该角色所选中的功能节点

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setIsdefault(Byte isdefault) {
        this.isdefault = isdefault;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
    }

    public Byte getIsdefault() {
        return isdefault;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
