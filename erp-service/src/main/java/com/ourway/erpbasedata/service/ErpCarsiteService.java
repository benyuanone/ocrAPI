package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.model.ErpCarsite;

import java.util.List;
import java.util.Map;

/*<p>接口 ErpCarsiteService: <p>
*<p>说明：装车台service层接口</p>
*<pre>
*@author zhou_xtian
*@date 2017-05-08 13:35
</pre>
*/
public interface ErpCarsiteService {

    /*<p>方法: 唯一性校验 </p>
    *<ul>
     *<li> @param erpCarsite erpcarsite实体对象</li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-29 17:38  </li>
    *</ul>
    */
    boolean doUniqueCheck(ErpCarsite erpCarsite);

    /*
    *<p>方法: 保存或更新 </p>
    *<ul>
     *<li> @param erpCarsite 实体对象</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 19:03  </li>
    *</ul>
    */
    void saveOrUpdate(ErpCarsite erpCarsite);


    /*
    *<p>方法: 批量选择删除(未启用) </p>
    *<ul>
     *<li> @param list 需要删除数据的owid的list</li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-07-24 17:33  </li>
    *</ul>
    */
    List<Map<String, Object>> removeErpCarsiteByIds(List<String> list);

    /*
    *<p>方法: 根据单个实例删除数据 </p>
    *<ul>
     *<li> @param erpCarsite 要删除的ErpCarsite实例</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-08 17:40  </li>
    *</ul>
    */
    void removeErpCarsite(ErpCarsite erpCarsite);

    /*
    *<p>方法: 查询（分页）</p>
    *<ul>
     *<li> @param filterModels 过滤条件</li>
     *<li> @param pageNo 起始页</li>
     *<li> @param pageSize 每页条数</li>
     *<li> @param sortStr 排序规则</li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-08 17:40  </li>
    *</ul>
    */
    PageInfo<ErpCarsite> listErpCarsite(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr);

    /*
    *<p>方法: 查询所有数据（无分页） </p>
    *<ul>
    *<li>@return ErpCarsite实例list </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 14:37  </li>
    *</ul>
    */
    List<ErpCarsite> listAllErpCarsite();

    /*
    *<p>方法: 查询（无分页）带排序 </p>
    *<ul>
     *<li> @param sortIdArray 排序字段</li>
     *<li> @param sortMethod 排序规则</li>
    *<li>@return ErpCarsite实例list  </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 14:37  </li>
    *</ul>
    */
    List<ErpCarsite> listAllErpCarsite(String[] sortIdArray, String sortMethod);

    /*
    *<p>方法: 查询（单条） </p>
    *<ul>
     *<li> @param params 过滤参数</li>
    *<li>@return ErpCarsite实例 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-05-09 9:59  </li>
    *</ul>
    */
    ErpCarsite detailErpCarsite(Map<String, Object> params);
}
