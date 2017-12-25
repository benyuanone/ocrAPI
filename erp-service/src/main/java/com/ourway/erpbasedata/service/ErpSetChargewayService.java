package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.model.*;

import java.util.List;
import java.util.Map;


/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-25 16:11  </li>
*</ul>
*/
public interface ErpSetChargewayService {

    Boolean doCheckUniqueUrl(ErpSetChargeway erpSetChargeway);

    ErpSetChargeway saveOrUpdateErpSetChargeway(ErpSetChargeway erpSetChargeway, List<ErpSetChargewayList> erpSetChargewayLists);

    List<Map<String, Object>> removeErpSetChargewayByIds(List<String> list);

    PageInfo<ErpSetChargeway> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<ErpSetChargeway> listErpSetChargewayList(Map<String, Object> dataMap, String sortStr);

    ErpSetChargeway detailErpSetChargeway(String owid);


}
