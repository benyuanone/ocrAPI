package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpSetChargewayDao;
import com.ourway.erpbasedata.model.ErpSetChargeway;
import com.ourway.erpbasedata.model.ErpTank;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 ErpTankDaoImpl.java : <p>
 * <p>说明：TODO</p>
 * <pre>
 * @author zhangxinyi
 * @date 2017-05-31 11:17
 * </pre>
 */
@Repository("erpSetChargewayDao")
public class ErpSetChargewayImpl extends BaseServiceImpl<ErpSetChargeway> implements ErpSetChargewayDao {

    @Override
    public Boolean doCheckUniqueUrl(ErpSetChargeway erpSetChargeway) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from ErpSetChargeway where chargewayId=:chargewayId ";
        params.put("chargewayId", erpSetChargeway.getChargewayId());
        if (!TextUtils.isEmpty(erpSetChargeway.getOwid())) {
            hql += " and owid<>:owid";
            params.put("owid", erpSetChargeway.getOwid());
        }
        ErpSetChargeway chargetype = getOneByHql(hql, params);
        if (null == chargetype)
            return true;
        else
            return false;
    }

    @Override
    public PageInfo<ErpSetChargeway> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpSetChargeway.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }


}
