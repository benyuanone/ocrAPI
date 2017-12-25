package com.ourway.erpbasedata.dao;

import com.ourway.base.dao.BaseDao;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpTankFarm;

import java.util.Map;

/**
 * Created by Kevin on 2017-09-27.
 */
public interface ErpTankFarmDao extends BaseService<ErpTankFarm> {
    ErpTankFarm detailErpCarsite(Map<String, Object> params);
}
