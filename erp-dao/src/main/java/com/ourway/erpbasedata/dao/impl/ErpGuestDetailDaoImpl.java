package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.service.BaseService;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.erpbasedata.dao.ErpGuestDetailDao;
import com.ourway.erpbasedata.model.ErpGuestLinklist;
import org.springframework.stereotype.Repository;

/**
 * Created by Kevin on 2017-11-15.
 */
@Repository("erpGuestDetailDao")
public class ErpGuestDetailDaoImpl extends BaseServiceImpl<ErpGuestLinklist>implements ErpGuestDetailDao{
    @Override
    public void removeErpShipList(String owid) {

    }

    @Override
    public Boolean doCheckFirstChoiceUnique(String owId){
        String hql = "from ErpGuestLinklist where owId=:owId and erp";
        listAllBySql(hql,"");
        return true;
    }
}
