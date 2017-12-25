package com.ourway.erpsystem.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.model.ErpCurrencyType;
import com.ourway.erpsystem.dao.ErpCurrencyTypeDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-04-27.
 */
@Repository("erpCurrencyTypeDao")
public class ErpCurrencyTypeDaoImpl extends BaseServiceImpl<ErpCurrencyType> implements ErpCurrencyTypeDao {

    @Override
    public boolean doCheckUnique(ErpCurrencyType erpCurrencyType) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from ErpCurrencyType where currencyTypeId=:currencyTypeId ";
        params.put("currencyTypeId", erpCurrencyType.getCurrencyTypeId());
        if (!TextUtils.isEmpty(erpCurrencyType.getOwid())) {
            hql += " and owid<>:owid";
            params.put("owid", erpCurrencyType.getOwid());
        }
        ErpCurrencyType temp = getOneByHql(hql, params);
        if (null == temp)
            return true;
        else
            return false;
    }

    //listCT sql语句
    @Override
    public List<ErpCurrencyType> listHqlForPage(Map<String,Object> params) {
        HqlStatement hqlStatement = new HqlStatement(ErpCurrencyType.class,params);
        return listAllByHql(hqlStatement.getHql(),params);
    }

    @Override
    public PageInfo<ErpCurrencyType> listErpSetOperatewayForPage(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpCurrencyType.class,filterModels,sortStr);
        return listHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
    }
}
