package com.ourway.sys.dao;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysLanguage;

import java.util.List;

/**<p>接口 SysLanguageDao.java : <p>
*<p>说明：多语言主表业务层类</p>
*<pre>
*@author JackZhou
*@date 2017/4/12 13:31
</pre>
*/
public interface SysLanguageDao extends BaseService<OurwaySysLanguage> {

    /**
    *<p>方法:doCheckUniqueLabelKey 判断labelKey是否唯一 </p>
    *<ul>
     *<li> @param language </li>
    *<li>@return java.lang.Boolean  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/12 21:00  </li>
    *</ul>
    */
    Boolean doCheckUniqueLabelKey(OurwaySysLanguage language);


    PageInfo<OurwaySysLanguage> listLanguageForPage(List<FilterModel> models, Integer pageNo, Integer pageSize);
}
