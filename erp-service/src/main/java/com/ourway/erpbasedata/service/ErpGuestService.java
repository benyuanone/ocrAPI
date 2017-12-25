package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.model.ErpGuest;
import com.ourway.erpbasedata.model.ErpGuestLinklist;

import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-11-10.
 */

public interface ErpGuestService {
    PageInfo<ErpGuest> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    ErpGuest detailErpGuest(String owid);

    List<ErpGuestLinklist> listGuestDetailById(String shipId);

    List<ErpGuest> listAllGuest();

    ErpGuest saveOrUpdateErpShip(ErpGuest erpShip, List<ErpGuestLinklist> erpShipListList);

    List<Map<String, Object>> removeErpShipByIds(List<String> list);

    Boolean doCheckFirstChoiceUnique(String owId);

}
