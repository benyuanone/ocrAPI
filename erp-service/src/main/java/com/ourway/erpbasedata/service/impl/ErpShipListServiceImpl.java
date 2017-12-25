package com.ourway.erpbasedata.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpGoodsDao;
import com.ourway.erpbasedata.dao.ErpShipListDao;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.erpbasedata.service.ErpShipListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>接口 ApiDetailService.java : <p>
 * <p>说明：接口详细类</p>
 * <pre>
 * @author cc
 * @date 14:35 2017/4/12
 * </pre>
 */
@Service("erpShipListService")
public class ErpShipListServiceImpl implements ErpShipListService {

    @Autowired
    ErpShipListDao erpShipListDao;

    @Autowired
    ErpGoodsDao erpGoodsDao;

    @Override
    public void removeErpShipList(HqlStatement hqlStatement) {
        List<ErpShipList> list = erpShipListDao.listAllByHql(hqlStatement.getHql(),
                hqlStatement.getParams());
        if ((null != list) && (list.size() > 0)) {
            for (ErpShipList erpShipList : list) {
                erpShipListDao.removeEntity(erpShipList);
            }
        }
    }

    @Override
    public List<ErpShipList> listErpShipByStatement(HqlStatement hqlStatement) {
        return null;
    }

    @Override
    public PageInfo<ErpShipList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return null;
    }

    @Override
    public void saveOrUpdateErpShipList(ErpShipList erpShipList) {
        erpShipListDao.saveOrUpdate(erpShipList);
    }

    @Override
    public ErpShipList detailErpShipList(String owid) {
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("owid", owid);
        HqlStatement hql = new HqlStatement(ErpShipList.class, _params);
        /* 根据owid 查询记录*/
        ErpShipList oapi = erpShipListDao.getOneByHql(hql.getHql(), hql.getParams());

        //判断是否为空
        if (null == oapi) {
            return null;
        }
        //连带查询字表信息
        List<ErpGoods> dataList = erpGoodsDao.listAllByErpShipList(oapi);
//        oapi.setDataList(dataList);
        return oapi;
    }

    @Override
    public List<ErpShipList> listAllShipListByShipId(String shipId) {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("erpshipRefOwid", shipId);
        List<ErpShipList> erpShipLists = erpShipListDao.listAllByParam(map, "indexno");
        for (ErpShipList shipList : erpShipLists) {
            ErpGoods good = erpGoodsDao.getOneById(shipList.getErpgoodsRefOwid());
            if (null != good)
                shipList.setErpGoods(good);
        }
        return erpShipLists;
    }
}
