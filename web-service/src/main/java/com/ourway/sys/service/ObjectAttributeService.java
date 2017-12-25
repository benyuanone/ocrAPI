package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysObjectAttribute;

import java.util.List;

/**<p>接口 ObjectAttributeService.java : <p>
 *<p>说明：对象属性</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:37
</pre>
 */
public interface ObjectAttributeService {

    /**
    *<p>功能描述：saveOrUpdateObject 保存更新对象</p>
    *<ul>
    *<li>@param [objectAttribute]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author jackson</li>
    *<li>@date 17-4-27 下午5:12</li>
    *</ul>
    */
    void saveOrUpdateObject(OurwaySysObjectAttribute objectAttribute);

    /**
     *<p>功能描述：removeententObjByIds 根据ids删除对象</p>
     *<ul>
     *<li>@param [hql]</li>
     *<li>@return void</li>
     *<li>@throws </li>
     *<li>@author jackson</li>
     *<li>@date 17-4-27 下午3:20</li>
     *</ul>
     */
    void removeententObjByIds(HqlStatement hql);

    /**
     *<p>功能描述：listHqlForPage 分页获取对象数据</p>
     *<ul>
     *<li>@param [hql, pageNo, pageSize]</li>
     *<li>@return com.ourway.base.dataobject.PageInfo<com.ourway.sys.model.OurwaySysObjectAttribute></li>
     *<li>@throws </li>
     *<li>@author jackson</li>
     *<li>@date 17-4-27 下午3:25</li>
     *</ul>
     */
    PageInfo<OurwaySysObjectAttribute> listHqlForPage(HqlStatement hql, int pageNo, int pageSize);

    /**
     *<p>功能描述：listObjByStatement 查找记录对象详情</p>
     *<ul>
     *<li>@param [hql]</li>
     *<li>@return java.util.List<com.ourway.sys.model.OurwaySysObjectAttribute></li>
     *<li>@throws </li>
     *<li>@author jackson</li>
     *<li>@date 17-4-27 下午3:32</li>
     *</ul>
     */
    List<OurwaySysObjectAttribute> listObjByStatement(HqlStatement hql);

}
