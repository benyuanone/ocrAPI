package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpTankFarmDao;
import com.ourway.erpbasedata.model.ErpTankFarm;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-09-27.
 */
@Repository("erpTankFarmDao")
public class ErpTankFarmDaoImpl extends BaseServiceImpl<ErpTankFarm> implements ErpTankFarmDao{
    private static final String ERPC = " from ErpTankFarm";
    @Override
    public ErpTankFarm detailErpCarsite(Map<String, Object> params) {

        List<ErpTankFarm> TfList = listAllByHql(ERPC,params);
        ErpTankFarm Tf = TfList.get(0);
        return Tf;
    }
}
