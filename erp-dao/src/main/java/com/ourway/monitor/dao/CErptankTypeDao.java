package com.ourway.monitor.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.monitor.model.CErptankType;

import java.util.List;

public interface CErptankTypeDao extends BaseService<CErptankType> {


    PageInfo<CErptankType> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<CErptankType> listErpTankType(String key);
}
