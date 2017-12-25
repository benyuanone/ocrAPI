package com.ourway.erpcustoms.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.erpcustoms.model.ErpShipBlacklist;
import com.ourway.sys.model.OurwaySysEmploys;

import java.util.List;
import java.util.Map;

/*<p>接口 ErpShipBlacklistService : <p>
*<p>说明：船舶黑名单service接口</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-08 14:30
</pre>
*/
public interface ErpShipBlacklistService {

    /*
    *<p>方法: 查询（分页）</p>
    *<ul>
     *<li> @param filterModels 过滤条件</li>
     *<li> @param pageNo 起始页</li>
     *<li> @param pageSize 每页条数</li>
     *<li> @param sortStr 排序规则</li>
    *<li>@return 船舶黑名单分页对象 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-08 14:31  </li>
    *</ul>
    */
    PageInfo<ErpShipBlacklist> listErpShipBlacklist(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr);

    /*
    *<p>方法: 查询（单条） </p>
    *<ul>
     *<li> @param params 过滤参数</li>
    *<li>@return ErpShipBlacklist实例 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-08 14:33  </li>
    *</ul>
    */
    Map<String,Object> detailErpShipBlacklist(Map<String, Object> params);

    /*
    *<p>方法: 船舶黑名单历史情况 </p>
    *<ul>
     *<li> @param params 过滤参数</li>
    *<li>@return ErpShipBlacklist列表 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-11 9:38  </li>
    *</ul>
    */
    List<Map<String,Object>> historyErpShipBlacklist(Map<String, Object> params);


    /*
    *<p>方法: 保存或更新 </p>
    *<ul>
     *<li> @param ErpShipBlacklist 实体对象</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-08 14:34  </li>
    *</ul>
    */
    void saveErpShipBlacklist(ErpShipBlacklist erpShipBlacklist);

    /*
    *<p>方法: 删除船舶黑名单单条数据 </p>
    *<ul>
     *<li> @param ErpShipBlacklist 实体对象</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-13 9:53  </li>
    *</ul>
    */
    void removeErpShipBlacklist(Map<String,Object> erpShipBlacklist);

    /*
    *<p>方法: 员工数据列表 </p>
    *<ul>
     *<li> @param filterModels 过滤条件</li>
     *<li> @param pageNo 起始页</li>
     *<li> @param pageSize 每页条数</li>
     *<li> @param sortStr 排序规则</li>
    *<li>@return 员工数据PageInfo对象 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-09-11 8:50  </li>
    *</ul>
    */
    PageInfo<OurwaySysEmploys> listSysEmploys(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr);

}
