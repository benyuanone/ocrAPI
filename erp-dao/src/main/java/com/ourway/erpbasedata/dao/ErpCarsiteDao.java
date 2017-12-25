package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpCarsite;

import java.util.List;
import java.util.Map;

/*<p>接口 ErpCarsiteDao <p>
*<p>说明：ErpCarsite接口dao</p>
*<pre>
*@author zhou_xtian
*@date 2017-05-08 13:41
</pre>
*/
public interface ErpCarsiteDao extends BaseService<ErpCarsite> {

    /*<p>方法: 唯一性检查 </p>
    *<ul>
     *<li> @param erpCarsite erpCarsite实体对象</li>
    *<li>@return 是否唯一 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-08 13:42  </li>
    *</ul>
    */
    boolean doUniqueCheck(ErpCarsite erpCarsite);

    /*<p>方法: 查询（分页） </p>
    *<ul>
     *<li> @param filters 过滤条件</li>
     *<li> @param pageNo 开始页数</li>
     *<li> @param pageSize 每页条数</li>
     *<li> @param sortStr 排序字段</li>
    *<li>@return 分页对象</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-08 13:43  </li>
    *</ul>
    */
    PageInfo<ErpCarsite> listErpCarsite(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    /*<p>方法: 装车台详情 </p>
    *<ul>
     *<li> @param params 过滤条件</li>
    *<li>@return erpCarsite实体对象</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-08 13:45  </li>
    *</ul>
    */
    ErpCarsite detailErpCarsite(Map<String, Object> params);
}
