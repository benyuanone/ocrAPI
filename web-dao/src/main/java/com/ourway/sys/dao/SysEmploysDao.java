package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysEmploys;

import java.util.List;
import java.util.Map;

/**<p>接口 SysEmploysDao.java : <p>
 *<p>说明：员工</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:42
</pre>
 */
public interface SysEmploysDao extends BaseService<OurwaySysEmploys> {
    /**<p>接口 SysEmploysDao.java : <p>
     *<p>说明：</p>
     *<pre>
     *@author cc
     *@date  2017/4/18 9:27
    </pre>
     */
    Boolean doCheckUniqueLabelKey(OurwaySysEmploys employs);

    PageInfo<OurwaySysEmploys> listHqlForPage(List<FilterModel> filters, Integer pageNo, Integer pageSize, String sortStr);

    /**
    *<p>方法:listEmpRols 查询用户角色分配情况 </p>
    *<ul>
     *<li> @param map TODO</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysEmploys>  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2017/5/31 14:14  </li>
    *</ul>
    */
    List<OurwaySysEmploys> listEmpRols(Map map);


    String listPositionDepart(OurwaySysEmploys employs);

    OurwaySysEmploys listOneEmploy(String id);


}
