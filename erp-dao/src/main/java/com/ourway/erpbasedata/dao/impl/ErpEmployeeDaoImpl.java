package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpEmployeeDao;
import com.ourway.erpbasedata.model.ErpEmployee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**<p>方法 ErpEmployeeDaoImpl : <p>
*<p>说明:员工资料dao实现</p>
*<pre>
*@author zhou_xtian
*@date 2017-06-08 14:48
</pre>
*/
@Repository("erpEmployeeDao")
public class ErpEmployeeDaoImpl extends BaseServiceImpl<ErpEmployee> implements ErpEmployeeDao {

    @Override
    public PageInfo<ErpEmployee> listAllErpEmployee(List<FilterModel> filterModel) {
        HqlStatement hql = new HqlStatement(ErpEmployee.class,filterModel);
        PageInfo<ErpEmployee> pageInfo = listHqlForPage(hql.getHql(),hql.getCountHql(),hql.getParams(),1,9999);
        return pageInfo;
    }

    @Override
    public ErpEmployee detailErpEmployee(Map<String, Object> params) {
        HqlStatement hqlStatement = new HqlStatement(ErpEmployee.class,params);
        return getOneByHql(hqlStatement.getHql(),params);
    }
}
