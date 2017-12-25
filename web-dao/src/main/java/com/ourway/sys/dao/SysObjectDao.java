package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysObject;

import java.util.List;

/**<p>接口 SysObjectDao.java : <p>
 *<p>说明：对象</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysObjectDao extends BaseService<OurwaySysObject> {

    /**
    *<p>功能描述：listHqlForPage 分页查询业务类对象</p>
    *<ul>
    *<li>@param [pageNo, pageSize]</li>
    *<li>@return com.ourway.base.dataobject.PageInfo<com.ourway.sys.model.OurwaySysObject></li>
    *<li>@throws </li>
    *<li>@author jackson</li>
    *<li>@date 17-5-28 下午8:18</li>
    *</ul>
    */
    PageInfo<OurwaySysObject> listObjectHqlForPage(List<FilterModel> filter, int pageNo, int pageSize);

    List<OurwaySysObject> listObjectByClassName(String className);

    List<BaseTree> listAllTreeType();

}
