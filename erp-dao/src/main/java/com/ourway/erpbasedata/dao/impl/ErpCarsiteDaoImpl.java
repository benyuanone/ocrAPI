package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpEmployeeDao;
import com.ourway.erpbasedata.model.ErpCarsite;
import com.ourway.erpbasedata.model.ErpEmployee;
import com.ourway.erpbasedata.dao.ErpCarsiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*<p>说明:方法 ErpCarsite实现dao</p>
*<pre>
*@author zhou_xtian
*@date 2017-05-08 17:33
</pre>
*/
@Repository("erpCarsiteDao")
public class ErpCarsiteDaoImpl extends BaseServiceImpl<ErpCarsite> implements ErpCarsiteDao {
    @Autowired
    private ErpEmployeeDao erpEmployeeDao;

    private static final String ERPCARSITE_REF_ERPEMPLOYEE = " from ErpCarsite er,ErpEmployee ee where er.erpemployeeRefOwid=ee.owid ";

    @Override
    public boolean doUniqueCheck(ErpCarsite erpCarsite) {
        return false;
    }

    @Override
    public PageInfo<ErpCarsite> listErpCarsite(List<FilterModel> filters,int pageNo,int pageSize,String sortStr){

/*        String sql = "select er.*,ee.name from Erp_Carsite er left join Erp_Employee ee on er.erpemployee_Ref_Owid=ee.owid ";
        PageInfo<ErpCarsite> pageInfo2 = listSqlForPage(sql,null,new HashMap<String,Object>(),pageNo,pageSize);*/

        HqlStatement hql = new HqlStatement(" from ErpCarsite er ",filters);

        PageInfo<ErpCarsite> pageInfo = listHqlForPage(hql.getHql(),hql.getCountHql(),hql.getParams(),pageNo,pageSize);
        for (ErpCarsite erpCarsite:pageInfo.getRecords()) {
            Map<String,Object> _map = new HashMap<String,Object>();
            _map.put("owid",erpCarsite.getErpemployeeRefOwid());
            erpCarsite.setEmployee(erpEmployeeDao.detailErpEmployee(_map));
        }
        return pageInfo;
    }

    @Override
    public ErpCarsite detailErpCarsite(Map<String, Object> params) {
        HqlStatement hqlStatement = new HqlStatement(ERPCARSITE_REF_ERPEMPLOYEE,params);

        PageInfo<Object[]> pageInfo = listObjectHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),1,1000);
        List<Object[]> erpCarsiteList = pageInfo.getRecords();

        ErpCarsite erpCarsite = (ErpCarsite)erpCarsiteList.get(0)[0];
        if (erpCarsiteList.get(0).length > 1) {
            ErpEmployee employee = (ErpEmployee)erpCarsiteList.get(0)[1];
            erpCarsite.setEmployee(employee);
        }

        return erpCarsite;
    }
}
