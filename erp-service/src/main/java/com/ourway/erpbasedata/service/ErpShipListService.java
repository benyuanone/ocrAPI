package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.model.ErpShipList;

import java.util.List;


/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-08 20:57  </li>
*</ul>
*/
public interface ErpShipListService {

    void removeErpShipList(HqlStatement hqlStatement);


    List<ErpShipList> listErpShipByStatement(HqlStatement hqlStatement);

    PageInfo<ErpShipList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    void saveOrUpdateErpShipList(ErpShipList erpShipList);

    ErpShipList detailErpShipList(String owid);

    List<ErpShipList> listAllShipListByShipId(String shipId);


}
