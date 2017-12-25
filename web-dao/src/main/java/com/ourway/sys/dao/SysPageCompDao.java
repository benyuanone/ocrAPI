package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysPageComponent;

import java.util.List;

/**<p>接口 SysPageCompDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysPageCompDao extends BaseService<OurwaySysPageComponent> {
    /**
    *<p>方法:listComponentByControl 根据布局获取当前布局下面的控件列表 </p>
    *<ul>
     *<li> @param layout 布局</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysPageComponent>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/9 0:06  </li>
    *</ul>
    */
    List<OurwaySysPageComponent> listComponentByControl(OurwaySysLayout layout);

    /**
    *<p>方法:saveListComponents 批量保存 </p>
    *<ul>
     *<li> @param components TODO</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/19 9:16  </li>
    *</ul>
    */
    void saveListComponents(List<OurwaySysPageComponent> components);

    void removeComponents(OurwaySysPage page);

}
