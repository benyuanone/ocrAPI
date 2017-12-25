package com.ourway.erpsystem.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.model.ErpBerth;
import com.ourway.erpsystem.dao.ErpBerthDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-05-27.
 */
@Repository("erpBerthDao")
public class ErpBerthDaoImpl extends BaseServiceImpl<ErpBerth> implements ErpBerthDao{
    @Override
    public boolean doCheckUnique(ErpBerth basedataBerth) {
        return false;
    }

    //list Berth sql语句
    @Override
    public List<ErpBerth> listHqlForPage(Map<String,Object> params) {
        HqlStatement hqlStatement = new HqlStatement(ErpBerth.class,params);
        return listAllByHql(hqlStatement.getHql(),params);
    }

}
