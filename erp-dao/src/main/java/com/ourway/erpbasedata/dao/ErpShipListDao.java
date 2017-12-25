package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;

import java.util.List;

public interface ErpShipListDao extends BaseService<ErpShipList> {

    void removeErpShipList(String owid);


    List<ErpShipList> listAllByErpShip(ErpShip oapi);
    PageInfo<ErpShipList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);
}
