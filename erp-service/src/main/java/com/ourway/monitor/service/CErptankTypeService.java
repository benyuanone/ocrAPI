package com.ourway.monitor.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.monitor.model.CErptankType;

import java.util.List;
import java.util.Map;


/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-08 9:34  </li>
*</ul>
*/
public interface CErptankTypeService {

    PageInfo<CErptankType> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<CErptankType> listErpTankType(String key);
}
