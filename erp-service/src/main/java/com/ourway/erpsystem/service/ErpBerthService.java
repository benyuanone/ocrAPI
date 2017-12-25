package com.ourway.erpsystem.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.model.ErpBerth;

import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-05-27.
 */
public interface ErpBerthService {

    void saveOrUpdateBerth(List<ErpBerth> erpCurrencyType);//List<OurwaySysDicValue> values

    List<ErpBerth> listBerth(Map<String,Object> params);
    
    List<Map<String, Object>> removeBerthByIds(List<String> list);

}
