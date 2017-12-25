package com.ourway.erpsystem.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.model.ErpCurrencyType;

import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-04-27.
 */
public interface ErpCurrencyTypeService {

    public Boolean doCheckUniqueUrl(ErpCurrencyType erpCurrencyType);

    void saveOrUpdateCurrencyType(List<ErpCurrencyType> erpCurrencyType);//List<OurwaySysDicValue> values

    List<ErpCurrencyType> listCurrencyType(Map<String,Object> params);

    PageInfo<ErpCurrencyType> listCurrencyType(List<FilterModel> filterModels,int pageNo,int pageSize,String sortStr);

    void removeBasedataCurrencyTypeEntity(HqlStatement hql);

    List<ErpCurrencyType> listBasedataCurrencyTypeByStatement(HqlStatement hql);

    List<Map<String, Object>> removeCTByIds(List<String> list);
}
