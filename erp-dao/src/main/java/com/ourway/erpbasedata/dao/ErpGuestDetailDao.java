package com.ourway.erpbasedata.dao;

import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpGuestLinklist;

/**
 * Created by Kevin on 2017-11-15.
 */
public interface ErpGuestDetailDao extends BaseService<ErpGuestLinklist>{
    void removeErpShipList(String owid);
    Boolean doCheckFirstChoiceUnique(String owId);
}
