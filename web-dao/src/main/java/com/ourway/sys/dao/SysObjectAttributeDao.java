package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysObjectAttribute;

import java.util.List;

/**<p>接口 SysObjectAttributeDao.java : <p>
 *<p>说明：对象属性</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysObjectAttributeDao extends BaseService<OurwaySysObjectAttribute> {

    /**
    *<p>功能描述：listAllByObjectId 根据对象id获取对象属性列表</p>
    *<ul>
    *<li>@param []</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysObjectAttribute></li>
    *<li>@throws </li>
    *<li>@author jackson</li>
    *<li>@date 17-5-30 上午12:08</li>
    *</ul>
    */
    List<OurwaySysObjectAttribute> listAllByObjectId(String objId);
}
