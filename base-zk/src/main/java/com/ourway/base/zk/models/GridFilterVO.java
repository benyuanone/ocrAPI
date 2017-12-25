package com.ourway.base.zk.models;

import java.io.Serializable;

/**
 * Created by D.chen.g on 2017/6/16.
 */
public class GridFilterVO implements Serializable {
    private String key;
    private String rel;
    private String val;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public static GridFilterVO instance(String key, String rel, String val, String type) {
        GridFilterVO gridFilterVO = new GridFilterVO();
        gridFilterVO.setKey(key);
        gridFilterVO.setRel(rel);
        gridFilterVO.setVal(val);
        gridFilterVO.setType(type);
        return gridFilterVO;
    }
}
