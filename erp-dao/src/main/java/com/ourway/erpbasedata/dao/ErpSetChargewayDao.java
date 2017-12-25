package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpSetChargeway;

import java.util.List;

/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-25 13:50  </li>
*</ul>
*/
public interface ErpSetChargewayDao extends BaseService<ErpSetChargeway> {


    Boolean doCheckUniqueUrl(ErpSetChargeway erpSetChargeway);

    PageInfo<ErpSetChargeway> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);


}
