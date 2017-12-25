package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpShipListDao;
import com.ourway.erpbasedata.dao.ErpTankListDao;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysApiDetailDaoImpl.java : <p>
 *<p>说明：接口详情</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:41
</pre>
 */
@Repository("erpShipListDao")
public class ErpShipListDaoImpl extends BaseServiceImpl<ErpShipList> implements ErpShipListDao{

    @Override
    public void removeErpShipList(String owid) {

    }

    @Override
    public List<ErpShipList> listAllByErpShip(ErpShip oapi) {
        return null;
    }

    @Override
    public PageInfo<ErpShipList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return null;
    }
}
