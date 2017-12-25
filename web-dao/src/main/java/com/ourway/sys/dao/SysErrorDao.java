package com.ourway.sys.dao;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysErrorcode;

import java.util.List;

/**<p>接口 SysErrorDao.java : <p>
 *<p>说明：错误编号</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:42
</pre>
 */
public interface SysErrorDao extends BaseService<OurwaySysErrorcode> {

    /*
    *<p>功能描述：doCheckUniqueCode  判断code唯一性</p >
    *<ul>
    *<li>@param [errorcode]</li>
    *<li>@return boolean</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 上午 9:24</li>
    *</ul>
    */
    Boolean  doCheckUniqueCode(OurwaySysErrorcode errorcode);


    /*
    *<p>功能描述：listHqlForPage</p >
    *<ul>
    *<li>@param [filters, pageNo, pageSize]</li>
    *<li>@return com.ourway.base.dataobject.PageInfo<com.ourway.sys.model.OurwaySysErrorcode></li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/29 0029 下午 1:40</li>
    *</ul>
    */
    PageInfo<OurwaySysErrorcode> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize);
}
