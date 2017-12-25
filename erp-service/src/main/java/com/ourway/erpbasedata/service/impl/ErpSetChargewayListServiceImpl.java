package com.ourway.erpbasedata.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpGoodsDao;
import com.ourway.erpbasedata.dao.ErpSetChargewayListDao;
import com.ourway.erpbasedata.dao.ErpTankListDao;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpSetChargewayList;
import com.ourway.erpbasedata.model.ErpTankList;
import com.ourway.erpbasedata.service.ErpSetChargewayListService;
import com.ourway.erpbasedata.service.ErpTankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-28 17:35  </li>
*</ul>
*/
@Service("erpSetChargewayListService")
public class ErpSetChargewayListServiceImpl implements ErpSetChargewayListService {

    @Autowired
    ErpSetChargewayListDao erpSetChargewayListDao;


    @Override
    public void removeErpSetChargewayList(HqlStatement hqlStatement) {
        List<ErpSetChargewayList> list = erpSetChargewayListDao.listAllByHql(hqlStatement.getHql(),
                hqlStatement.getParams());
        if ((null != list) && (list.size() > 0)) {
            for (ErpSetChargewayList erpSetChargewayLists : list) {
                erpSetChargewayListDao.removeEntity(erpSetChargewayLists);
            }
        }
    }


    @Override
    public void saveOrUpdateErpSetChargewayList(ErpSetChargewayList erpSetChargewayList) {
        erpSetChargewayListDao.saveOrUpdate(erpSetChargewayList);

    }

    @Override
    public List<ErpSetChargewayList> listErpSetChargewayListByStatement(HqlStatement hqlStatement) {
        //先根据 条件 查询ourwaySysApi
        List<ErpSetChargewayList> erpTankGoodsList = erpSetChargewayListDao.listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
        Map<String, Object> _params = new HashMap<String, Object>();
        String hql1 = "from ErpGoods where owid=:owid";
        if ((null != erpTankGoodsList) && (erpTankGoodsList.size() > 0)) {
            for (ErpSetChargewayList erpSetChargewayLists : erpTankGoodsList) {
                _params.put("owid", erpSetChargewayLists.getErpsetchargewayRefOwid());
            }
        }
        return erpTankGoodsList;
    }

    @Override
    public PageInfo<ErpSetChargewayList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return erpSetChargewayListDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public ErpSetChargewayList detailErpSetChargewayList(String owid) {
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("owid", owid);
        HqlStatement hql = new HqlStatement(ErpSetChargewayList.class, _params);
        /* 根据owid 查询记录*/
        ErpSetChargewayList oapi = erpSetChargewayListDao.getOneByHql(hql.getHql(), hql.getParams());

        //判断是否为空
        if (null == oapi) {
            return null;
        }
        return oapi;
    }

    @Override
    public List<ErpSetChargewayList> listAllSetChargewayListByChargewayId(String chargewayId) {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("erpsetchargewayRefOwid", chargewayId);
        List<ErpSetChargewayList> erpSetChargewayLists = erpSetChargewayListDao.listAllByParam(map, "owid");
        return erpSetChargewayLists;
    }
}
