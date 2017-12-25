package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;

import java.util.List;

public interface ErpTankListDao extends BaseService<ErpTankList> {

    void removeErpTankList(String owid);


    List<ErpTankList> listAllByErpTank(ErpTank oapi);
    PageInfo<ErpTankList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);
}
