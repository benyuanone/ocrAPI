package com.ourway.monitor.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpShipListDao;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.monitor.dao.CErptankTypeDao;
import com.ourway.monitor.model.CErptankType;
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
@Repository("cErptankTypeDao")
public class CErptankTypeDaoImpl extends BaseServiceImpl<CErptankType> implements CErptankTypeDao{


    @Override
    public PageInfo<CErptankType> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(CErptankType.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public List<CErptankType> listErpTankType(String key) {
        String hql = "";
        Map<String, Object> params = null;
        if (key.equals("全部")) {
            hql = "from CErptankType ce, CTankMonitor ct where ce.tankTypeId = ct.tankId";
        } else {
            hql = "from CErptankType ce, CTankMonitor ct where ce.tankTypeId = ct.tankId and ce.tankTypeName = :tankTypeName";
            params = new HashMap<String, Object>(1);
            params.put("tankTypeName",key);
        }
        return listAllByHql(hql, params);
    }
}
