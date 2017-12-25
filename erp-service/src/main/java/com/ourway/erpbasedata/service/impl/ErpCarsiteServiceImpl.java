package com.ourway.erpbasedata.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.model.ErpCarsite;
import com.ourway.erpbasedata.dao.ErpCarsiteDao;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.service.ErpCarsiteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-05-08.
 */
@Service("ErpCarsiteService")
public class ErpCarsiteServiceImpl implements ErpCarsiteService{

    @Autowired
    private ErpCarsiteDao erpCarsiteDao;

    @Override
    public boolean doUniqueCheck(ErpCarsite erpCarsite) {
        return erpCarsiteDao.doUniqueCheck(erpCarsite);
    }

    @Override
    public void saveOrUpdate(ErpCarsite erpCarsite) {
        erpCarsiteDao.saveOrUpdate(erpCarsite);
    }

    @Override
    public List<Map<String, Object>> removeErpCarsiteByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            ErpCarsite oapi = erpCarsiteDao.getOneByParams(params, null);
            if (null != oapi) {
                erpCarsiteDao.removeEntity(oapi);
                objs.add(params);
            }
        }
        return objs;
    }

    @Override
    public void removeErpCarsite(ErpCarsite erpCarsite) {
        erpCarsiteDao.removeEntity(erpCarsite);
    }

    @Override
    public PageInfo<ErpCarsite> listErpCarsite(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr) {
        return erpCarsiteDao.listErpCarsite(filterModels,pageNo,pageSize,sortStr);
    }

    @Override
    public List<ErpCarsite> listAllErpCarsite() {
        return erpCarsiteDao.listAll();
    }

    @Override
    //暂时不用
    public List<ErpCarsite> listAllErpCarsite(String[] sortIdArray, String sortMethod) {
        //拼接sql查询语句
        String hql = " from ErpCarsite";
        if (!TextUtils.isEmpty(sortIdArray)) {
            hql += " order by "+ StringUtils.join(sortIdArray,",")+" "+sortMethod;
        }
        Map<String, Object> map= new HashMap<String, Object>();
        //得到hqlstatement对象
        HqlStatement hqlStatement = new HqlStatement(hql,map);
        //通过util方法调用基础方法获取数据
        return erpCarsiteDao.listAllByHql(hqlStatement.getHql(), map);
    }

    @Override
    public ErpCarsite detailErpCarsite(Map<String, Object> params) {
        //通过util方法调用基础方法获取数据
        return erpCarsiteDao.detailErpCarsite(params);
    }
}
