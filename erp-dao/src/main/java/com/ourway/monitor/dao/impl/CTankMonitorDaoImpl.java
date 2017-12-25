package com.ourway.monitor.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.monitor.dao.CTankMonitorDao;
import com.ourway.monitor.model.CTankMonitor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysApiDetailDaoImpl.java : <p>
 *<p>说明：接口详情</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:41
</pre>
 */
@Repository("cTankMonitorDao")
public class CTankMonitorDaoImpl extends BaseServiceImpl<CTankMonitor> implements CTankMonitorDao {


    @Override
    public PageInfo<CTankMonitor> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(CTankMonitor.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public List<CTankMonitor> listTankMonitorByParam(Map<String,Object> map) {
        String hql = "from CTankMonitor ct,CErptankType ce where 1=1 and ct.tankTypeId =ce.tankTypeId  ";
//        map.put("tankTypeId","3");
//        map.put("tankId","");
//        map.put("sortType","1");
        Map<String, Object> params = null;
        params = new HashMap<String, Object>(2);
        if(!TextUtils.isEmpty(map.get("tankTypeId").toString())){
            hql += "  and  ct.tankTypeId = :tankTypeId ";
            params.put("tankTypeId",  map.get("tankTypeId") );
        }
        if(!TextUtils.isEmpty(map.get("tankId").toString())){
            hql += " and ct.tankId like :tankId  or ct.goodsName like :tankId ";
            params.put("tankId","%" + map.get("tankId") + "%");
        }
        if(!TextUtils.isEmpty(map.get("sortType").toString())){
            String sortType1 = map.get("sortType").toString();
            if(sortType1.equals("0")){
                hql += "order by ct.tankId ";
            }else if(sortType1.equals("1")){
                hql += "order by ct.realWeight ";
            }else{
                hql += "order by ct.tankState ";
            }
        }
        return listAllByHql(hql, params);
    }
    @Override
    public List<CTankMonitor> listTankMonitorOrderByParam(String key) {
        String hql = "";
        Map<String, Object> params = null;
        if (TextUtils.isEmpty(key)) {
            hql = "from CTankMonitor  order by tankId";
        } else {
            if(key.equals(0)){
                hql = "from CTankMonitor  order by tankId";
            }else if(key.equals(1)){
                hql = "from CTankMonitor  order by realWeight";
            }else {
                hql = "from CTankMonitor  order by tankState";
            }
        }
        return listAllByHql(hql, params);
    }

}
