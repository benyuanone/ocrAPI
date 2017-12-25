package com.ourway.base.zk.models;

import java.io.Serializable;

/**
 * Created by CC on 2017/6/15.
 */
public class DepatTreeVo  implements Serializable {
    /*树的主键*/
    private Integer id;
    /*父节点id*/
    private Integer fid;
    /*树的路径*/
    private String path;
    /*树的名称*/
    private String depName;
    /*是否有子节点*/
    private Boolean isLeaf;
    /*下级节点数量*/
    public Integer childLen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public Integer getChildLen() {
        return childLen;
    }

    public void setChildLen(Integer childLen) {
        this.childLen = childLen;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    private Object bean;
}
