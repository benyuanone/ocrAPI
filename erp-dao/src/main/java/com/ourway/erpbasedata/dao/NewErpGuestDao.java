package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpGuest;
import com.ourway.erpbasedata.model.ErpTankList;

import java.util.List;

/**
 * Created by CuiL on 2017-05-08.
 */
public interface NewErpGuestDao extends BaseService<ErpGuest> {

    ErpGuest detailErpGuest(String owid);

    List<ErpGuest> listPopByParams(List<FilterModel> filters);

    List<ErpGuest> listPopByParams(String key);
}