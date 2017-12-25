package com.ourway.erpsystem.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.model.ErpCurrencyType;
import com.ourway.erpsystem.dao.ErpCurrencyTypeDao;
import com.ourway.erpsystem.service.ErpCurrencyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-04-27.
 */
@Service("erpCurrencyTypeService")
public class ErpCurrencyTypeServiceImpl implements ErpCurrencyTypeService {

    @Autowired
    ErpCurrencyTypeDao erpCurrencyTypeDao;

    @Override
    public Boolean doCheckUniqueUrl(ErpCurrencyType erpCurrencyType) {
        return erpCurrencyTypeDao.doCheckUnique(erpCurrencyType);
    }


    @Override
    public void saveOrUpdateCurrencyType(List<ErpCurrencyType> val) {
        for (ErpCurrencyType curVal : val) {
            if (TextUtils.isEmpty(curVal.getOwid())) {
                //新增
                ErpCurrencyType cur = new ErpCurrencyType();
                erpCurrencyTypeDao.save(curVal);
            }
            erpCurrencyTypeDao.saveOrUpdate(val);
        }

        if(null!=val&&val.size()>0)
            for(ErpCurrencyType erpCurrencyType:val){
               if(null==erpCurrencyType.getUpdateFlag()){
                   erpCurrencyType.setUpdateFlag(0);
               }
               switch (erpCurrencyType.getUpdateFlag()){
                   case 0: break;
                   case 1:
                      if(TextUtils.isEmpty( erpCurrencyType.getOwid())){
                          erpCurrencyTypeDao.save(erpCurrencyType);
               }else {
                          erpCurrencyTypeDao.update(erpCurrencyType);
                      }
               }break;
//                case 2:
//                    erpCurrencyTypeDao.removeByIds(erpCurrencyType.getOwid());
//                    break;
            }
    }


    @Override
    public List<ErpCurrencyType> listCurrencyType(Map<String,Object> params) {
        return erpCurrencyTypeDao.listHqlForPage(params);
    }

    @Override
    public PageInfo<ErpCurrencyType> listCurrencyType(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr) {
        return erpCurrencyTypeDao.listErpSetOperatewayForPage(filterModels,pageNo,pageSize,sortStr);
    }

    @Override
    public void removeBasedataCurrencyTypeEntity(HqlStatement hql) {
        List<ErpCurrencyType> basedataCurrencyTypeServiceList = erpCurrencyTypeDao.listAllByHql(hql.getHql(),hql.getParams());
        for (ErpCurrencyType basedataCurrencyType : basedataCurrencyTypeServiceList) {
            erpCurrencyTypeDao.removeEntity(basedataCurrencyType);
        }
    }

    @Override
    public List<ErpCurrencyType> listBasedataCurrencyTypeByStatement(HqlStatement hql) {
        return erpCurrencyTypeDao.listAllByHql(hql.getHql(),hql.getParams());
    }

    @Override
    public List<Map<String, Object>> removeCTByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            ErpCurrencyType oapi = erpCurrencyTypeDao.getOneByParams(params, "");

                erpCurrencyTypeDao.removeEntity(oapi);
                objs.add(params);

        }
        return objs;
    }
}
