package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysTempcontrol;
import com.ourway.sys.model.OurwaySysTemplate;

import java.util.List;
import java.util.Map;

/**<p>接口 TemplateService.java : <p>
 *<p>说明：页面模板</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:39
</pre>
 */
public interface TemplateService {

    /**
    *<p>方法:listAllTemplate 获取所有template的列表 </p>
    *<ul>
     *<li> @param hqlStatement </li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysTemplate>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/18 9:48  </li>
    *</ul>
    */
    List<OurwaySysTemplate> listAllTemplate(HqlStatement hqlStatement);

    /**
    *<p>方法:detailTemplate 获取模板详细信息 </p>
    *<ul>
     *<li> @param hqlStatement </li>
    *<li>@return com.ourway.sys.model.OurwaySysTemplate  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/18 17:23  </li>
    *</ul>
    */
    OurwaySysTemplate detailTemplate(String templateId);


    PageInfo<OurwaySysTemplate> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<Map<String,Object>> removeItems(List<String> owids);

    /**
    *<p>方法:saveOrUpdate 保存或者更新template </p>
    *<ul>
     *<li> @param template 模板</li>
     *<li> @param tempcontrolList 模板小项</li>
    *<li>@return com.ourway.sys.model.OurwaySysTemplate  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/26 23:57  </li>
    *</ul>
    */
    OurwaySysTemplate saveOrUpdate(OurwaySysTemplate template, List<OurwaySysTempcontrol> tempcontrolList);

}
