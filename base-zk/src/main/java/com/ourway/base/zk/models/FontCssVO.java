package com.ourway.base.zk.models;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/16.
 */
public class FontCssVO implements Serializable {
    private Double min;
    private Double max;
    private String css;


    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
}
