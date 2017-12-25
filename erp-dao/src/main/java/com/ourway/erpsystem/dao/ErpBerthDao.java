package com.ourway.erpsystem.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpBerth;

import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-05-27.
 */
public interface ErpBerthDao extends BaseService<ErpBerth>{
    boolean doCheckUnique(ErpBerth basedataBerth);
    List<ErpBerth> listHqlForPage(Map<String,Object> params);
}
