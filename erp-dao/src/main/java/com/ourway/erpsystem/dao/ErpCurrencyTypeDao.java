package com.ourway.erpsystem.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpCurrencyType;

import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-04-27.
 */
public interface ErpCurrencyTypeDao extends BaseService<ErpCurrencyType> {
    boolean doCheckUnique(ErpCurrencyType erpCurrencyType);
    List<ErpCurrencyType> listHqlForPage(Map<String,Object> params);
    PageInfo<ErpCurrencyType> listErpSetOperatewayForPage(List<FilterModel> filterModels,int pageNo,int pageSize,String sortStr);

}
