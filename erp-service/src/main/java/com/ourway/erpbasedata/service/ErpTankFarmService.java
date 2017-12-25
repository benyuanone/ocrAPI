package com.ourway.erpbasedata.service;

import com.ourway.erpbasedata.model.ErpTankFarm;

import java.util.Map;

/**
 * Created by Kevin on 2017-09-27.
 */
public interface ErpTankFarmService {
    ErpTankFarm detailErpCarsite(Map<String, Object> params);
    void saveOrUpdate(ErpTankFarm tankFarm);
}
