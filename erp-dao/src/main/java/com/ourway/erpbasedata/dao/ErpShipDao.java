package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpShip;

import java.util.List;

/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-08 9:19  </li>
*</ul>
*/
public interface ErpShipDao extends BaseService<ErpShip> {


    Boolean doCheckUniqueUrl(ErpShip erpShip);

    PageInfo<ErpShip> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);


    List<ErpShip> listErpShipByFuzzyQuery(String key);

}
