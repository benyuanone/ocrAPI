package com.ourway.base.zk.models;

import org.apache.commons.collections.map.HashedMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 TreeVO : <p>
 * <p>说明:树形基础类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/6 15:11
 * </pre>
 */
public class TreeVO implements Serializable {
    /*树的主键*/
    private Integer owid;
    /*父节点id*/
    private Integer fid;
    /*树的路径*/
    private String path;
    /*树的名称*/
    private String name;
    /*显示标签名字*/
    private String label;
    /*树的链接*/
    private String link;
    /*树节点的icon图标*/
    private String icon;

    /*是否有子节点*/
    private Boolean isLeaf;
    /*下级节点数量*/
    public Integer childLen;
    private Object bean;
    private String disableLabel;
    private Double px;//同层次内的排序
    private Integer cc;//树节点层次

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCc() {
        if (null == cc)
            cc = 1;
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public String getDisableLabel() {
        return disableLabel;
    }

    public void setDisableLabel(String disableLabel) {
        this.disableLabel = disableLabel;
    }

    public Double getPx() {
        return px;
    }

    public void setPx(Double px) {
        this.px = px;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    /*树的下级节点*/
    private List<TreeVO> subTrees;

    public Integer getOwid() {
        return owid;
    }

    public void setOwid(Integer owid) {
        this.owid = owid;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getChildLen() {
        return childLen;
    }

    public void setChildLen(Integer childLen) {
        this.childLen = childLen;
    }

    public List<TreeVO> getSubTrees() {
        return subTrees;
    }

    public void setSubTrees(List<TreeVO> subTrees) {
        this.subTrees = subTrees;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("owid", owid);
        map.put("fid", fid);
        map.put("path", path);
        map.put("name", name);
        map.put("link", link);
        map.put("icon", icon);
        map.put("isLeaf", isLeaf);
        map.put("childLen", childLen);
        map.put("bean", bean);
        map.put("px", px);
        map.put("disableLabel", disableLabel);
        return map;
    }
}
