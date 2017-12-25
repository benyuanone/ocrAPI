package com.ourway.base.zk.models;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>方法 FilterModel : <p>
 * <p>说明:通用的查询条件类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/13 0:33
 * </pre>
 */
public class FilterModel implements java.io.Serializable {
    public static final Integer EQUALS = 0;//==
    public static final Integer LIKE = 1;//like
    public static final Integer BERWEEN = 2;//between and
    public static final Integer MULT_EQUALS = 3;//多个值，in
    public static final Integer AREA = 4;//>= <=

    /*关键字，跟model中的属性对应*/
    private String key;
    /*查询类型*/
    private Integer type;
    /*查询的数据*/
    private List<Object> datas;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = new ArrayList<Object>(datas);
    }

    public static FilterModel instance(String key, Integer type, List<Object> datas) {
        FilterModel model = new FilterModel();
        model.setKey(key);
        model.setType(type);
        model.setDatas(datas);
        return model;
    }
}
