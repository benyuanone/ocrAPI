package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysTempcontrol;

import java.util.List;

/**<p>接口 SysTempcontrolDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:44
</pre>
 */
public interface SysTempcontrolDao extends BaseService<OurwaySysTempcontrol> {
    /**
    *<p>方法:listAllByTemplateId 获取指定template下面的布局控件 </p>
    *<ul>
     *<li> @param templateId </li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysTempcontrol>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/18 17:30  </li>
    *</ul>
    */
    List<OurwaySysTempcontrol> listAllByTemplateId(String templateId);
}
