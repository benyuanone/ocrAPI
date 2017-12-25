package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysDicValueDao;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;

/**
 * <p>接口 SysDicValueDaoImpl.java : <p>
 * <p>说明：通用字典表之</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysDicValueDao")
public class SysDicValueDaoImpl extends BaseServiceImpl<OurwaySysDicValue> implements SysDicValueDao {
    @Override
    public PageInfo<OurwaySysDicValue> listEventTypeForPage(Integer owid, List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        List<FilterModel> list = new ArrayList<>();
        for (int i = 0, g = filters.size(); i < g; i++) {
            if (!filters.get(i).getKey().equals("type")) {
                list.add(filters.get(i));
            }
        }
        HqlStatement hql = new HqlStatement("from OurwaySysDicValue where dicRefOwid = '" + owid + "'", list);
        return listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }

    @Override
    public void removeByRefOwid(Object[] owids) {
        if (null != owids && owids.length > 0) {
            for (Object owid : owids) {
                Map<String, Object> map = new HashMap<String, Object>(1);
                map.put("dicRefOwid", owid);
                removeByParams(map);
            }
        }
    }

    @Override
    public OurwaySysDicValue listOneDic(int type, String dicVal1) {
        String  hql  = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type=:type and b.dicVal1=:dicVal1 ";
        Map<String,Object> params = new HashMap<String,Object>(2);
        params.put("type",type);
        params.put("dicVal1",dicVal1);
        List<Object[]> objs = listObjsAllByHql(hql,params);
        if(null==objs||objs.size()<=0)
            return null;
        return (OurwaySysDicValue) objs.get(0)[1];
    }
}
