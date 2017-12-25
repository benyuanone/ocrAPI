package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpGuest;

import java.util.List;

/**
 * Created by Kevin on 2017-11-10.
 */
public interface ErpGuestDao extends BaseService<ErpGuest> {
    PageInfo<ErpGuest> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

}
