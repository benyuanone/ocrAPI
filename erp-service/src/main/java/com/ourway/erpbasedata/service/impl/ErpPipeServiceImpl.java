package com.ourway.erpbasedata.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpPipeDao;
import com.ourway.erpbasedata.model.ErpPipe;
import com.ourway.erpbasedata.service.ErpPipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-09-11.
 */
@Service("erpPipeService")
public class ErpPipeServiceImpl implements ErpPipeService {

    @Autowired
    ErpPipeDao erpPipeDao;

    @Override
    public Boolean doCheckUniqueUrl(ErpPipe erpPipe) {
        return erpPipeDao.doCheckUnique(erpPipe);
    }

    @Override
    public void saveOrUpdatePipe(List<ErpPipe> val) {
        if (null != val && val.size() > 0)
            for (ErpPipe curVal : val) {
                if (TextUtils.isEmpty(curVal.getOwid())) {
                    ErpPipe cur = new ErpPipe();
                    erpPipeDao.save(curVal);
                }
                erpPipeDao.saveOrUpdate(val);
            }
    }

    @Override
    public List<ErpPipe> listPipe(Map<String, Object> params) {
        return erpPipeDao.listHqlForPage(params);
    }

    @Override
    public List<Map<String, Object>> removePipeByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            ErpPipe oapi = erpPipeDao.getOneByParams(params, "");
//            if (null != oapi) {
//                //先删子表信息
//                erpTankListDao.removeErpTankList(owid);
            erpPipeDao.removeEntity(oapi);
            objs.add(params);
//            }
        }
        return objs;
    }

    @Override
    public PageInfo<ErpPipe> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return erpPipeDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public List<ErpPipe> NewlistHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr){
        return null;
    }

    @Override
    public List<Map<String, Object>> removePipe(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", owid);

            erpPipeDao.removeByParams(params);
            objs.add(params);
//            //删除对象属性
//            params = new HashedMap();
//            params.put("objectRefOwid", owid);
//            erpPipeDao.removeByParams(params);
        }
        return objs;
    }

    @Override
    public List<ErpPipe> listAllPipeType() {
        return erpPipeDao.listAll();
    }

    @Override
    public List<Map<String, Object>> listDicByType(Integer type, String orderBy) {
        return erpPipeDao.listAllDataByType(type, orderBy);
    }
}
