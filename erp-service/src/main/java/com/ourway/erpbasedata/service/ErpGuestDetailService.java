package com.ourway.erpbasedata.service;

import com.ourway.erpbasedata.model.ErpGuest;
import com.ourway.erpbasedata.model.ErpGuestLinklist;

import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-11-15.
 */
public interface ErpGuestDetailService {
    List<Map<String,Object>> listAllGuestListById(String guestId);

}
