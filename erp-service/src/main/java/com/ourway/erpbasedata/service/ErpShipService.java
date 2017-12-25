package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.model.*;
import com.ourway.sys.model.OurwaySysWorkflow;

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
public interface ErpShipService {

    Boolean doCheckUniqueUrl(ErpShip erpShip);

    ErpShip saveOrUpdateErpShip(ErpShip erpShip, List<ErpShipList> erpShipListList);

    List<Map<String, Object>> removeErpShipByIds(List<String> list);

    PageInfo<ErpShip> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    ErpShip detailErpShip(String owid);

    List<ErpShip> listErpShipByFuzzyQuery(String key);

    OurwaySysWorkflow updateStartFlow(String owid);

}
