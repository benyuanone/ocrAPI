package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysApi;

import java.util.List;

/**<p>接口 SysApiDao.java : <p>
 *<p>说明：API接口</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:41
</pre>
 */
public interface SysApiDao extends BaseService<OurwaySysApi> {

    /*
    *<p>功能描述：doCheckUniqueUrl</p >
    *<ul>
    *<li>@param [ourwaySysApi]</li>
    *<li>@return java.lang.Boolean</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 2:05</li>
    *</ul>
    */
    Boolean doCheckUniqueUrl(OurwaySysApi ourwaySysApi);

    /**
    *<p>功能描述：listHqlForPage  分页查询 api信息</p >
    *<ul>
    *<li>@param [filterModelList, pageNo, pageSize]</li>
    *<li>@return com.ourway.base.dataobject.PageInfo<com.ourway.sys.model.OurwaySysApi></li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/31 0031 下午 7:00</li>
    *</ul>
    */
    PageInfo<OurwaySysApi> listHqlForPage(List<FilterModel> filterModelList, Integer pageNo, Integer pageSize);


    List<BaseTree> listApiTree(List<FilterModel> models);
}
