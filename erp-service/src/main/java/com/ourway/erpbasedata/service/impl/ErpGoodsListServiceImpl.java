package com.ourway.erpbasedata.service.impl;

import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.dao.ErpGoodsListDao;
import com.ourway.erpbasedata.dao.NewErpGuestDao;
import com.ourway.erpbasedata.service.ErpGoodsListService;
import com.ourway.erpbasedata.model.ErpGoodsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***<p>方法 ErpGoodsListServiceImpl : <p>
*<p>说明:TODO</p>
*<pre>
*@author CuiLiang
*@date 2017-05-09 17:07
</pre>
*/
@Service("erpGoodsListService")
public class ErpGoodsListServiceImpl implements ErpGoodsListService {

    @Autowired
    ErpGoodsListDao erpGoodsListDao;
    @Autowired
    NewErpGuestDao erpGuestDao;

    @Override
    public List<ErpGoodsList> listAllByParams(List<FilterModel> filterModelList) {
        return erpGoodsListDao.listAllByParams(filterModelList);
    }

    @Override
    public List<ErpGoodsList> listAllByRefId(String refId) {
        List<ErpGoodsList> erpGoodsLists = erpGoodsListDao.listAllByRefId(refId);
        for(ErpGoodsList erpGoodsList:erpGoodsLists){
            erpGoodsList.setErpGuest(erpGuestDao.detailErpGuest(erpGoodsList.getErpguestRefOwid()));
        }
        return erpGoodsLists;
    }
}
