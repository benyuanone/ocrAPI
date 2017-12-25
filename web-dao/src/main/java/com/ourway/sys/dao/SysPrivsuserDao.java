package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysPrivsuser;

import java.util.List;

/**<p>接口 SysPrivsuserDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysPrivsuserDao extends BaseService<OurwaySysPrivsuser> {
    /**
    *<p>方法:saveUserPrivs TODO 保存角色用户</p>
    *<ul>
     *<li> @param roleId TODO</li>
     *<li> @param selEmps TODO</li>
    *<li>@return boolean  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2017/6/3 0:03  </li>
    *</ul>
    */
    boolean saveUserPrivs(String roleId, List<String> selEmps);

    void removeByUserOwid(String owid);
}
