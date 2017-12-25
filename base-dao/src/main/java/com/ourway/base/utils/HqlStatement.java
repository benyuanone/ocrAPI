package com.ourway.base.utils;

import com.ourway.base.model.BaseEntity;
import com.ourway.base.model.FilterModel;

import java.util.*;

/**
 * 功能说明：HqlUtils.getMasterUpdateHql()生成返回对象
 * 使用说明：
 * Created by jack on 2017/1/30.
 */
public class HqlStatement {

    private String hql;
    private String countHql;
    private Map<String, Object> params;
    private String orderBy;

    public HqlStatement() {
    }

    public HqlStatement(String hql, Map<String, Object> params) {
        this.params = params;
        int _index = 0;
        if (null != params) {
            for (String key : params.keySet()) {
                if (_index == 0) {
                    if (!hql.toLowerCase().contains("where"))
                        hql += " where " + key + "='" + params.get(key) + "'";
                    else
                        hql += " and " + key + "='" + params.get(key) + "'";
                } else {
                    hql += " and " + key + "='" + params.get(key) + "'";
                }
                _index++;
            }
        }
        this.hql = hql;
    }

    public HqlStatement(String hql, Map<String, Object> params, String sortStr) {
        this.setOrderBy(sortStr);

        int _index = 0;
        if (null != params) {
            for (String key : params.keySet()) {
                if (_index == 0) {
                    if (!hql.toLowerCase().contains("where"))
                        hql += " where " + key + "='" + params.get(key) + "'";
                    else
                        hql += " and " + key + "='" + params.get(key) + "'";
                } else {
                    hql += " and " + key + "='" + params.get(key) + "'";
                }
                _index++;
            }
        }
        this.hql = hql;
        this.params = params;
        this.countHql = "select count(*) " + hql;
    }

    public HqlStatement(String hql, Class<?> claz, List<FilterModel> models, String sortStr) {
        this.setOrderBy(sortStr);

        Map<String, Class<?>> typeMap = BeanUtil.getPropertiesTypeMap(claz); //获取当前对象的所有属性，以便判断
        //处理models，留下与类相关的属性
        List<FilterModel> _models = new ArrayList<FilterModel>();
    /*处理一下类型*/
        for (FilterModel filterModel : models) {
            if (null != typeMap.get(filterModel.getKey())) {
                List<Object> _datas = new ArrayList<Object>();
                for (Object o : filterModel.getDatas()) {
                    _datas.add(BeanUtil.convert(typeMap.get(filterModel.getKey()), o));
                }
                filterModel.setDatas(_datas);
                _models.add(filterModel);
            }
        }
        composeHql(hql, _models);
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * <p>方法:HqlStatement 根据类名和参数组装hql语句 </p>
     * <ul>
     * <li> @param claz 类名</li>
     * <li> @param params 参数</li>
     * <li>@return   </li>
     * <li>@author JackZhou </li>
     * <li>@date    </li>
     * </ul>
     */
    public HqlStatement(Class<?> claz, Map<String, Object> params, String orderBy) {
        this.orderBy = orderBy;
        doHandleClass(claz, params);
    }

    public HqlStatement(Class<?> claz, Map<String, Object> params) {
        doHandleClass(claz, params);
    }

    public void doHandleClass(Class<?> claz, Map<String, Object> params) {
        String _hql = " from " + claz.getName();
        int _index = 0;
        Map<String, Class<?>> typeMap = BeanUtil.getPropertiesTypeMap(claz); //获取当前对象的所有属性，以便判断
        Map<String, Object> _params = new HashMap<String, Object>();//存放转换后的数据
        Set<String> keys = params.keySet();
        for (String key : keys) {
            if (null == typeMap.get(key))
                continue;
            _params.put(key, BeanUtil.convert(typeMap.get(key), params.get(key)));
            if (_index == 0) {
                _hql += " where " + key + "=:" + key;
            } else
                _hql += " and " + key + "=:" + key;
            _index++;
        }
        this.hql = _hql;
        this.params = _params;
    }

    /**
     * <p>方法:HqlStatement 构造函数，根据对象自动生成hql </p>
     * <ul>
     * <li> @param claz 类</li>
     * <li> @param models 查询列表</li>
     * <li>@return   </li>
     * <li>@author JackZhou </li>
     * <li>@date    </li>
     * </ul>
     */
    public HqlStatement(Class<?> claz, List<FilterModel> models) {
        String _hql = " from " + claz.getName();
        Map<String, Class<?>> typeMap = BeanUtil.getPropertiesTypeMap(claz); //获取当前对象的所有属性，以便判断
        //处理models，留下与类相关的属性
        List<FilterModel> _models = new ArrayList<FilterModel>();
        for (FilterModel filterModel : models) {
            if (null != typeMap.get(filterModel.getKey())) {
                List<Object> _datas = new ArrayList<Object>();
                for (Object o : filterModel.getDatas()) {
                    _datas.add(BeanUtil.convert(typeMap.get(filterModel.getKey()), o));
                }
                filterModel.setDatas(_datas);
                _models.add(filterModel);
            }
        }
        composeHql(_hql, _models);
    }

    public HqlStatement(Class<?> claz, List<FilterModel> models, String sortStr) {
        this.setOrderBy(sortStr);
        String _hql = " from " + claz.getName();
        Map<String, Class<?>> typeMap = BeanUtil.getPropertiesTypeMap(claz); //获取当前对象的所有属性，以便判断
        //处理models，留下与类相关的属性
        List<FilterModel> _models = new ArrayList<FilterModel>();
        if (null != models && models.size() > 0)
            for (FilterModel filterModel : models) {
                if (null != typeMap.get(filterModel.getKey())) {
                    List<Object> _datas = new ArrayList<Object>();
                    for (Object o : filterModel.getDatas()) {
                        _datas.add(BeanUtil.convert(typeMap.get(filterModel.getKey()), o));
                    }
                    filterModel.setDatas(_datas);
                    _models.add(filterModel);
                }
            }
        composeHql(_hql, _models);
    }

    public HqlStatement(String hql, List<FilterModel> models) {
        composeHql(hql, models);
    }

    public HqlStatement(String hql, List<FilterModel> models, String sortStr) {
        this.setOrderBy(sortStr);
        composeHql(hql, models);
    }

    public void composeHql(String hql, List<FilterModel> models) {
        int _index = 0;
        if (null != models)
            for (FilterModel model : models) {
                if (TextUtils.isEmpty(model.hqlString()))
                    continue;
                if (_index == 0) {
                    if (!hql.toLowerCase().contains("where"))
                        hql += " where " + model.hqlString();
                    else
                        hql += " and " + model.hqlString();
                } else {
                    hql += " and " + model.hqlString();
                }
                _index++;
                Set<String> set = model.getMap().keySet();
                if (null == this.params)
                    this.params = new HashMap<String, Object>();
                for (String s : set) {
                    this.params.put(s, model.getMap().get(s));
                }
            }
        //" owid>=:owid1 and owid<=:owid2"

        this.hql = hql;
        this.countHql = "select count(*) " + hql;

        this.params = params;
    }


    public String getHql() {
        if (TextUtils.isEmpty(orderBy))
            return hql;
        else
            return hql + " order by " + orderBy;
    }

    public void setHql(String hql) {
        this.hql = hql;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getCountHql() {
        return countHql;
    }

    public void setCountHql(String countHql) {
        this.countHql = countHql;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("owid", "123");
        HqlStatement hql = new HqlStatement(BaseEntity.class, map, "");
        System.out.println(hql.getHql());
    }
}
