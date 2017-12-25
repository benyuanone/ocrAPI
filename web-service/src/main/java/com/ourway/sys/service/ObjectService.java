package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.OurwaySysObject;
import com.ourway.sys.model.OurwaySysObjectAttribute;

import java.util.List;
import java.util.Map;

/**<p>接口 ObjectService.java : <p>
 *<p>说明：系统业务类</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:37
</pre>
 */
public interface ObjectService {

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
    OurwaySysObject saveOrUpdateObject(OurwaySysObject object,  List<OurwaySysObjectAttribute> objAttrList);

    void saveOrUpdateAllObject(List<OurwaySysObject> objs);

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
    List<Map<String,Object>> removeObjects(List<String> owids);

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
    PageInfo<OurwaySysObject> listHqlForPage(List<FilterModel> filter, int pageNo, int pageSize);

    /**
    *<p>功能描述：listObjByStatement 查找记录对象详情信息</p>
    *<ul>
    *<li>@param [hql]</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysObject></li>
    *<li>@throws </li>
    *<li>@author jackson</li>
    *<li>@date 17-4-27 下午3:32</li>
    *</ul>
    */
    OurwaySysObject detailObject(String objId);

    List<OurwaySysObject> listAllObjectByClassName(String className);

    List<OurwaySysObjectAttribute> listAllAttributesByOwid(String owid);

    List<OurwaySysObjectAttribute> listAllAttributesByClassName(String className);

    List<BaseTree> listAllTreeType();
}
