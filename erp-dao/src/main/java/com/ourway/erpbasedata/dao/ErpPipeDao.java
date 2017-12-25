package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpPipe;

import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-09-11.
 */
/*<p>接口 ErpPipeDao.java : <p>
*<p>说明：TODO</p>
*<pre>
*@author Kevin
*@date 2017-09-11 11:42
</pre>
*/
public interface ErpPipeDao extends BaseService<ErpPipe>{
    boolean doCheckUnique(ErpPipe erpPipe);
    List<ErpPipe> listHqlForPage(Map<String,Object> params);
    PageInfo<ErpPipe> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);
     List<Map<String, Object>> listAllDataByType(Integer type,String orderBy);
}
