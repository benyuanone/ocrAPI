package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysPageComponent;

import java.util.List;

/**<p>接口 PageComponentService.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:37
</pre>
 */
public interface PageComponentService {
    /**
    *<p>方法:listComponenetsByPage 根据page获取所有的控件 </p>
    *<ul>
     *<li> @param page pageCA</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysPageComponent>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/25 23:40  </li>
    *</ul>
    */
    List<OurwaySysPageComponent> listComponenetsByPage(OurwaySysPage page);
}
