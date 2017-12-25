package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.ExceptionUtil;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpGoodsListDao;
import com.ourway.erpbasedata.model.ErpGoodsList;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CuiL on 2017-05-09.
 */
@Repository("erpGoodsListDao")
public class ErpGoodsListDaoImpl extends BaseServiceImpl<ErpGoodsList> implements ErpGoodsListDao {

    @Override
    public void saveOrUpdateAll(List<ErpGoodsList> erpGoodsLists) {
        for(ErpGoodsList erpGoodsList :erpGoodsLists){
            switch (erpGoodsList.getUpdateFlag()){
            //0 新增
            case 0:
                //什么也不做
                break;
            //修改
            case 1:
                // 判断 owid
                if (TextUtils.isEmpty(erpGoodsList.getOwid())) {
                    //为空 新增
                    save(erpGoodsList);
                } else {
                    update(erpGoodsList);
                }
                break;
            //删除
            case 2:
                removeByIds(erpGoodsList.getOwid());
                break;
        }
        }
    }

    @Override
    public List<ErpGoodsList> listAllByParams(List<FilterModel> filterModelList) {
        HqlStatement hqlStatement = new HqlStatement(" from GoodsList ",filterModelList);
        return listAllByParams(hqlStatement.getHql(),hqlStatement.getParams());
    }

    @Override
    public List<ErpGoodsList> listAllByRefId(String refOwid) {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("erpgoodsRefOwid",refOwid);
        HqlStatement hql = new HqlStatement(ErpGoodsList.class,params,"");
        return listAllByHql(hql.getHql(),hql.getParams());
    }

    @Override
    public void removeByRefIds(Serializable... ids) {
        Map<String,Object> params = new HashMap<String,Object>(1);
        for (Serializable id : ids) {
            params.put("erpgoodsRefOwid",id);
        }
        removeByParams(params);
    }
}
