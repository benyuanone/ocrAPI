package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.erpbasedata.model.ErpSetOperateway;

import java.util.List;
import java.util.Map;

/*<p>方法 ErpSetOperatewayService : <p>
*<p>说明:基础资料-作业方式service接口</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-22 10:22
</pre>
*/
public interface ErpSetOperatewayService {

    PageInfo<ErpSetOperateway> listErpSetOperateway(List<FilterModel> filterModels,int pageNo,int pageSize,String sortStr);

    ErpSetOperateway detailErpSetOperateway(Map<String,Object> dataMap);

    String checkUnique(Map dataMap,PublicDataVO data);

    void saveErpSetOperateway(List<ErpSetOperateway> erpSetOperatewayList);

    void removeErpSetOperateway(ErpSetOperateway erpSetOperateway);
}
