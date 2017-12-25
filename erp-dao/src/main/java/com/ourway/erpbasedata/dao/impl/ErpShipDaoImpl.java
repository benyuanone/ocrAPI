package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpShipDao;
import com.ourway.erpbasedata.model.ErpShip;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-08 9:19  </li>
*</ul>
*/
@Repository("erpShipDao")
public class ErpShipDaoImpl extends BaseServiceImpl<ErpShip> implements ErpShipDao {
    @Override
    public Boolean doCheckUniqueUrl(ErpShip erpShip) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from ErpShip where shipId=:shipId ";
        params.put("shipId", erpShip.getShipId());
        if (!TextUtils.isEmpty(erpShip.getOwid())) {
            hql += " and owid<>:owid";
            params.put("owid", erpShip.getOwid());
        }
        ErpShip ship = getOneByHql(hql, params);
        if (null == ship)
            return true;
        else
            return false;
    }

    @Override
    public PageInfo<ErpShip> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpShip.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public List<ErpShip> listErpShipByFuzzyQuery(String key) {
        String hql = "";
        Map<String, Object> params = null;
        if (TextUtils.isEmpty(key)) {
            hql = "from ErpShip  order by shipId";
        } else {
            hql = "from ErpShip where engShipName like :engShipName  or shipId like :shipId or chnShipName like :chnShipName";
            params = new HashMap<String, Object>(3);
            params.put("engShipName", "%" + key + "%");
            params.put("shipId", "%" + key + "%");
            params.put("chnShipName", "%" + key + "%");
        }
        return listAllByHql(hql, params);
    }
}
