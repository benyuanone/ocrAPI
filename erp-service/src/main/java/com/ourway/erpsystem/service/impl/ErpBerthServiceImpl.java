package com.ourway.erpsystem.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.model.ErpBerth;
import com.ourway.erpsystem.dao.ErpBerthDao;
import com.ourway.erpsystem.service.ErpBerthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-05-27.
 */
@Service("erpBerthService")
public class ErpBerthServiceImpl implements ErpBerthService{

    @Autowired
    ErpBerthDao erpBerthDao;

    @Override
    public void saveOrUpdateBerth(List<ErpBerth> val) {
        List<ErpBerth> saveOrUpdateList = new ArrayList<ErpBerth>();
        for (ErpBerth one:val) {
            saveOrUpdateList.add(one);
        }
        erpBerthDao.saveOrUpdate(saveOrUpdateList);
    }


    @Override
    public List<ErpBerth> listBerth(Map<String,Object> params) {
        return erpBerthDao.listHqlForPage(params);
    }

    @Override
    public List<Map<String, Object>> removeBerthByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            ErpBerth oapi = erpBerthDao.getOneByParams(params, "");
//            if (null != oapi) {
//                //先删子表信息
//                erpTankListDao.removeErpTankList(owid);
            erpBerthDao.removeEntity(oapi);
            objs.add(params);
//            }
        }
        return objs;
    }
}
