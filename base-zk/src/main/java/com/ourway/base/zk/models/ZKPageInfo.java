package com.ourway.base.zk.models;

/**
 * zk的页面基本信息，包括页面的基本袁术
 * Created by jack on 2017/1/30.
 */
public class ZKPageInfo implements java.io.Serializable {

    private Integer screenWidth;
    private Integer screenHeight;

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(Integer screenHeight) {
        this.screenHeight = screenHeight;
    }
}
