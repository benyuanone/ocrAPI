package com.ourway.erpbasedata.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.dao.ErpEmployeeDao;
import com.ourway.erpbasedata.model.ErpEmployee;
import com.ourway.erpbasedata.service.ErpEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**<p>方法 ErpEmployeeServiceImpl : <p>
*<p>说明: 员工数据service实现</p>
*<pre>
*@author zhou_xtian
*@date 2017-06-08 14:56
</pre>
*/
@Service("erpEmployeeService")
public class ErpEmployeeServiceImpl implements ErpEmployeeService{

    @Autowired
    ErpEmployeeDao erpEmployeeDao;

    @Override
    public PageInfo<ErpEmployee> listAllObject(List<FilterModel> filterModelList) {
        return erpEmployeeDao.listAllErpEmployee(filterModelList);
    }
}
