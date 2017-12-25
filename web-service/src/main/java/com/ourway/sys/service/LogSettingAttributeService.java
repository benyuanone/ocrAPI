package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysLogSettingAttribute;

import java.util.List;

/**<p>接口 LogSettingAttributeService.java : <p>
 *<p>说明：日志参数</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:37
</pre>
 */
public interface LogSettingAttributeService {

    /**
     *<p>功能描述：saveOrUpdateObject 保存或者更新业务类</p>
     *<ul>
     *<li>@param [object]</li>
     *<li>@return boolean</li>
     *<li>@throws </li>
     *<li>@author jackson</li>
     *<li>@date 17-4-27 下午1:59</li>
     *</ul>
     */
    void saveOrUpdateLogSettingAttr(OurwaySysLogSettingAttribute object);

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
    void removeententLogSettingAttrByIds(HqlStatement hql);

    /**
     *<p>功能描述：listHqlForPage 分页获取对象数据</p>
     *<ul>
     *<li>@param [hql, pageNo, pageSize]</li>
     *<li>@return com.ourway.base.dataobject.PageInfo<com.ourway.sys.model.OurwaySysObject></li>
     *<li>@throws </li>
     *<li>@author jackson</li>
     *<li>@date 17-4-27 下午3:25</li>
     *</ul>
     */
    PageInfo<OurwaySysLogSettingAttribute> listHqlForPage(HqlStatement hql, int pageNo, int pageSize);

    /**
     *<p>功能描述：listObjByStatement 查找记录对象详情</p>
     *<ul>
     *<li>@param [hql]</li>
     *<li>@return java.util.List<com.ourway.sys.model.OurwaySysObject></li>
     *<li>@throws </li>
     *<li>@author jackson</li>
     *<li>@date 17-4-27 下午3:32</li>
     *</ul>
     */
    List<OurwaySysLogSettingAttribute> listLogSettingAttrByStatement(HqlStatement hql);
}
