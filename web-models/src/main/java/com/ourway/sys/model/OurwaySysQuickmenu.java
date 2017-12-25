package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_quickmenu")
public class OurwaySysQuickmenu extends UUidEntity {

    @Column(name = "USER_REF_OW_ID", nullable = true, length = 64)
    private String userRefOwId;
    @Column(name = "MENU_REF_ID", nullable = true)
    private Integer menuRefId;
    @Column(name = "ORDER_NO", nullable = true)
    private Integer orderNo;

    public String getUserRefOwId() {
        return userRefOwId;
    }

    public void setUserRefOwId(String userRefOwId) {
        this.userRefOwId = userRefOwId;
    }


    public Integer getMenuRefId() {
        return menuRefId;
    }

    public void setMenuRefId(Integer menuRefId) {
        this.menuRefId = menuRefId;
    }


    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }



}
