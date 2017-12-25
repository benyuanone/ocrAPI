package com.ourway.base.zk.models;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/2.
 */
public class LanguageVO implements Serializable {
    private String language;
    private String labelContent;
    private String labelTips;
    private Integer labelFloat;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLabelContent() {
        return labelContent;
    }

    public void setLabelContent(String labelContent) {
        this.labelContent = labelContent;
    }


    public String getLabelTips() {
        return labelTips;
    }

    public void setLabelTips(String labelTips) {
        this.labelTips = labelTips;
    }

    public Integer getLabelFloat() {
        return labelFloat;
    }

    public void setLabelFloat(Integer labelFloat) {
        this.labelFloat = labelFloat;
    }

    public static LanguageVO instance(String labelContent){
        LanguageVO vo = new LanguageVO();
        vo.setLabelContent(labelContent);
        return vo;
    }
}
