package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysPrivsallocate;

import java.util.List;
import java.util.Map;

/**<p>接口 SysPrivsallocateDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysPrivsallocateDao extends BaseService<OurwaySysPrivsallocate> {
    /**
    *<p>方法:getSelMenus TODO 查询某角色下的功能列表</p>
    *<ul>
     *<li> @param dataMap TODO</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysPrivsallocate>  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2017/6/1 16:42  </li>
    *</ul>
    */
    List<OurwaySysPrivsallocate> getSelMenus(Map<String, Object> dataMap);

    /**
    *<p>方法:saveAllocatePrivs TODO 保存功能权限</p>
    *<ul>
     *<li> @param roleId TODO</li>
     *<li> @param selEmps TODO</li>
    *<li>@return boolean  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2017/6/2 23:46  </li>
    *</ul>
    */
    boolean saveAllocatePrivs(String roleId, List selEmps);
}
