package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpSetChargeway;
import com.ourway.erpbasedata.model.ErpSetChargewayList;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;

import java.util.List;

/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-28 16:57  </li>
*</ul>
*/
public interface ErpSetChargewayListDao extends BaseService<ErpSetChargewayList> {

    void removeErpSetChargewayList(String owid);

    List<ErpSetChargewayList> listAllByErpSetChargewayList(ErpSetChargeway oapi);
    PageInfo<ErpSetChargewayList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

}
