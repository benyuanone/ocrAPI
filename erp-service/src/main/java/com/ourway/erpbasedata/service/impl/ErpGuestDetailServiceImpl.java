package com.ourway.erpbasedata.service.impl;

import com.ourway.base.utils.BeanUtil;
import com.ourway.erpbasedata.dao.ErpGoodsDao;
import com.ourway.erpbasedata.dao.ErpGuestDetailDao;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpGuestLinklist;
import com.ourway.erpbasedata.service.ErpGuestDetailService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Kevin on 2017-11-15.
 */
@Repository("erpGuestDetailService")
public class ErpGuestDetailServiceImpl implements ErpGuestDetailService {
    @Autowired
    ErpGoodsDao erpGoodsDao;
    @Autowired
    ErpGuestDetailDao erpGuestDetailDao;
    @Override
    public List<Map<String,Object>> listAllGuestListById(String guestId){
        Map<String, Object> map = new HashMap<String, Object>(1);
        List<Map<String,Object>> copy = new ArrayList<>();
        map.put("erpguestRefOwid", guestId);
        List<ErpGuestLinklist> erpShipLists = erpGuestDetailDao.listAllByParam(map, "");
        for (ErpGuestLinklist shipList : erpShipLists) {
            ErpGoods good = erpGoodsDao.getOneById(shipList.getErpguestRefOwid());
             if (null != good) shipList.setErpGoods(good);
        }
        for (ErpGuestLinklist shipList : erpShipLists) {
            //复制list时，要在for循环里new对象，否则每次指向的都是同一个地址。
            Map<String,Object> map2 = new HashMap<String,Object>();
            BeanUtil.obj2Map(shipList,map2);
            map2.put("state2",shipList.getState());
            copy.add(map2);
        }
        return copy;
    }

}
