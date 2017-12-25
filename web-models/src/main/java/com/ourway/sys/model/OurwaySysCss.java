package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_css")
public class OurwaySysCss extends UUidEntity {

    @Column(name = "CSS_NAME", nullable = true, length = 64)
    private String cssName;
    @Column(name = "CSS_OBJECT", nullable = true, length = 64)
    private String cssObject;
    @Column(name = "CSS_CONTENT", nullable = true, length = 4000)
    private String cssContent;
    @Column(name = "CSS_DESC", nullable = true, length = 240)
    private String cssDesc;


    public String getCssName() {
        return cssName;
    }

    public void setCssName(String cssName) {
        this.cssName = cssName;
    }


    public String getCssObject() {
        return cssObject;
    }

    public void setCssObject(String cssObject) {
        this.cssObject = cssObject;
    }

    public String getCssContent() {
        return cssContent;
    }

    public void setCssContent(String cssContent) {
        this.cssContent = cssContent;
    }


    public String getCssDesc() {
        return cssDesc;
    }

    public void setCssDesc(String cssDesc) {
        this.cssDesc = cssDesc;
    }

}
