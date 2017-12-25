package com.ourway.erpbasedata.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpGoodsDao;
import com.ourway.erpbasedata.dao.ErpTankListDao;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;
import com.ourway.erpbasedata.service.ErpTankListService;
import com.ourway.sys.dao.SysApiDetailDao;
import com.ourway.sys.model.OurwaySysApiDetail;
import com.ourway.sys.service.ApiDetailService;
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
@Service("erpTankListService")
public class ErpTankListServiceImpl implements ErpTankListService {

    @Autowired
    ErpTankListDao erpTankListDao;

    @Autowired
    ErpGoodsDao erpGoodsDao;

    @Override
    public void removeErpTankList(HqlStatement hqlStatement) {
        List<ErpTankList> list = erpTankListDao.listAllByHql(hqlStatement.getHql(),
                hqlStatement.getParams());
        if ((null != list) && (list.size() > 0)) {
            for (ErpTankList erpTankList : list) {
                erpTankListDao.removeEntity(erpTankList);
            }
        }
    }


    @Override
    public void saveOrUpdateErpTankList(ErpTankList erpTankList) {
        erpTankListDao.saveOrUpdate(erpTankList);

    }

    @Override
    public List<ErpTankList> listErpTankByStatement(HqlStatement hqlStatement) {
        //先根据 条件 查询ourwaySysApi
        List<ErpTankList> erpTankGoodsList = erpTankListDao.listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
        Map<String, Object> _params = new HashMap<String, Object>();
        String hql1 = "from ErpGoods where owid=:owid";
        if ((null != erpTankGoodsList) && (erpTankGoodsList.size() > 0)) {
            for (ErpTankList erpTankList1 : erpTankGoodsList) {
                _params.put("owid", erpTankList1.getErpgoodsRefOwid());
//                erpTankList1.setDataList(erpGoodsDao.listAllByHql(hql1,_params));
            }
        }
        return erpTankGoodsList;
    }

    @Override
    public PageInfo<ErpTankList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return erpTankListDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public ErpTankList detailErpTankList(String owid) {
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("owid", owid);
        HqlStatement hql = new HqlStatement(ErpTankList.class, _params);
        /* 根据owid 查询记录*/
        ErpTankList oapi = erpTankListDao.getOneByHql(hql.getHql(), hql.getParams());

        //判断是否为空
        if (null == oapi) {
            return null;
        }
        //连带查询字表信息
        List<ErpGoods> dataList = erpGoodsDao.listAllByErpTankList(oapi);
//        oapi.setDataList(dataList);
        return oapi;
    }

    @Override
    public List<ErpTankList> listAllTankListByTankId(String tankOwid) {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("erptankRefOwid", tankOwid);
        List<ErpTankList> tankLists = erpTankListDao.listAllByParam(map, "indexno");
        for (ErpTankList tankList : tankLists) {
            ErpGoods good = erpGoodsDao.getOneById(tankList.getErpgoodsRefOwid());
            if (null != good)
                tankList.setErpGoods(good);
        }
        return tankLists;
    }
}
