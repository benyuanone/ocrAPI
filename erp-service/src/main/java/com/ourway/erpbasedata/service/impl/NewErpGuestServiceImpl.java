package com.ourway.erpbasedata.service.impl;

import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.dao.NewErpGuestDao;
import com.ourway.erpbasedata.model.ErpGuest;
import com.ourway.erpbasedata.service.NewErpGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CuiL on 2017-07-20.
 */
@Service("newErpGuestService")
public class NewErpGuestServiceImpl implements NewErpGuestService {

    @Autowired
    NewErpGuestDao erpGuestDao;

    @Override
    public List<ErpGuest> listPopGoodsByParams(List<FilterModel> filters) {
        return erpGuestDao.listPopByParams(filters);
    }

    @Override
    public List<ErpGuest> listPopGoodsByParams(String key) {
        return erpGuestDao.listPopByParams(key);
    }

    @Override
    public String getDbMsg(String msg) {
        return "123123123";
    }
}
