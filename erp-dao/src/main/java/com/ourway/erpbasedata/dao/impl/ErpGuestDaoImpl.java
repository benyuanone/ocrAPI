package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpGuestDao;
import com.ourway.erpbasedata.model.ErpGuest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*<p>方法 ErpGuestDaoImpl : <p>
*<p>说明:客户资料Dao</p>
*<pre>
*@author Kevin
*@date 2017-11-10 14:56
</pre>
*/
@Repository("erpGuestDao")
public class ErpGuestDaoImpl extends BaseServiceImpl<ErpGuest> implements ErpGuestDao {
    @Override
    public PageInfo<ErpGuest> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpGuest.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }
}
