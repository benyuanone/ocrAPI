package com.ourway.base.model;

import com.ourway.base.utils.TextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //组装的语句
    private Map<String, Object> map = new HashMap<String, Object>();

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
        this.datas = datas;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("key:" + key);
        sb.append("type:" + type);
        int index = 1;
        for (Object data : datas) {
            sb.append(index + ":" + type);
            index++;
        }
        return sb.toString();
    }

    /**
     * <p>方法:hqlString 自动组装，返回hql </p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/25 1:15  </li>
     * </ul>
     */
    public String hqlString() {
        switch (this.type) {
            case 0:
                this.map.put(doRemoveDot(key), datas.get(0));
                return key+"=:"+doRemoveDot(key);
            case 1:
                this.map.put(doRemoveDot(key), "%"+datas.get(0)+"%");
                return key+" like :"+doRemoveDot(key);
            case 2:
                this.map.put(doRemoveDot(key) + "1", datas.get(0));
                this.map.put(doRemoveDot(key) + "2", datas.get(1));
                return key+" between :"+doRemoveDot(key)+"1 and :"+doRemoveDot(key)+"2";
            case 3://用in来实现
                String _incondition = "";
                for (Object object : datas) {
                    if (object instanceof String)
                        _incondition += "'" + object.toString() + "',";
                    else
                        _incondition += object.toString() + ",";
                }
                if(_incondition.length()>0)
                    _incondition.substring(0,_incondition.length()-1);
                return key+" in ("+_incondition+")";
            case 4:
                if(null==datas||datas.size()<=0)
                    return "";
                if(datas.size()==1){
                    if(!TextUtils.isEmpty(datas.get(0))) {
                        this.map.put(doRemoveDot(key), datas.get(0));
                        return key + " >=:" + doRemoveDot(key);
                    }
                }else{
                    String _bk = "";
                    if(!TextUtils.isEmpty(datas.get(0))){
                        this.map.put(doRemoveDot(key)+"1", datas.get(0));
                        _bk += key+">=:"+doRemoveDot(key)+"1";
                    }
                    if(!TextUtils.isEmpty(datas.get(1))){
                        this.map.put(doRemoveDot(key)+"2", datas.get(1));
                        if(_bk.length()>0)
                        _bk += " and "+key+"<=:"+doRemoveDot(key)+"2";
                    }
                    return _bk;
                }
        }
        return "";
    }

    //去掉查询条件中的。号
    private String doRemoveDot(String key){
        if(key.contains(".")){
            String[] keys = key.split("\\.");
            return keys[keys.length-1];
        }else
            return key;
    }

    public static FilterModel instance(String key, Integer type, List<Object> datas) {
        FilterModel model = new FilterModel();
        model.setKey(key);
        model.setType(type);
        model.setDatas(datas);
        return model;
    }
}
