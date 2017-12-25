package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpEmployee;

import java.util.List;
import java.util.Map;

/**<p>接口 ErpEmployeeDao : <p>
*<p>说明：TODO</p>
*<pre>
*@author zhou_xtian
*@date 2017-06-08 14:46
</pre>
*/
public interface ErpEmployeeDao extends BaseService<ErpEmployee> {

    /*<p>方法: 查询所有员工资料 </p>
    *<ul>
     *<li> @param filterModel 过滤条件</li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-07-26 10:25  </li>
    *</ul>
    */
    PageInfo<ErpEmployee> listAllErpEmployee(List<FilterModel> filterModel);

    ErpEmployee detailErpEmployee(Map<String,Object> params);
}
