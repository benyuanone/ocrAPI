package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpTank;

import java.util.List;

/**<p>接口 ErpTankDao.java : <p>
*<p>说明：TODO</p>
*<pre>
*@author zhangxinyi
*@date 2017-05-31 11:17
</pre>
*/
public interface ErpTankDao extends BaseService<ErpTank> {


    Boolean doCheckUniqueUrl(ErpTank erpTank);

    PageInfo<ErpTank> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<Object> listErpTankByLibraryArea(List<FilterModel> filters);



}
