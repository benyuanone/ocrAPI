package com.ourway.erpbasedata.service.impl;

import com.ourway.erpbasedata.dao.ErpTankFarmDao;
import com.ourway.erpbasedata.model.ErpTankFarm;
import com.ourway.erpbasedata.service.ErpTankFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Kevin on 2017-09-27.
 */
@Service("ErpTankFarmService")
public class ErpTankFarmServiceImpl implements ErpTankFarmService{

    @Autowired
    private ErpTankFarmDao erpTankFarmDao;

    @Override
    public ErpTankFarm detailErpCarsite(Map<String, Object> params) {
        //通过util方法调用基础方法获取数据
        return erpTankFarmDao.detailErpCarsite(params);
    }

    @Override
    public  void saveOrUpdate(ErpTankFarm tankFarm){
        erpTankFarmDao.saveOrUpdate(tankFarm);
    }
}
