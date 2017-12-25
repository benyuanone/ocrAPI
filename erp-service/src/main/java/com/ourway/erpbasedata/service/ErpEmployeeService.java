package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.model.ErpEmployee;

import java.util.List;
import java.util.Map;

/**<p>接口 ErpEmployeeService: <p>
*<p>说明：员工数据service接口</p>
*<pre>
*@author zhou_xtian
*@date 2017-06-08 14:54
</pre>
*/
public interface ErpEmployeeService {

    /*<p>方法: 查询（不分页） </p>
    *<ul>
     *<li> @param filterModelList 过滤条件</li>
    *<li>@return 员工数据list </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-07-26 10:23  </li>
    *</ul>
    */
    PageInfo<ErpEmployee> listAllObject(List<FilterModel> filterModelList);
}
