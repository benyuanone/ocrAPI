package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysPage;

import java.util.List;

/**<p>接口 LayoutService.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:37
</pre>
 */
public interface LayoutService {
    /**
    *<p>方法:queryOneByPageCaAndControlId 根据paca和控件id来获取相关的控件和布局 </p>
    *<ul>
     *<li> @param pageCa TODO</li>
     *<li> @param controlId TODO</li>
    *<li>@return com.ourway.sys.model.OurwaySysLayout  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/8 23:55  </li>
    *</ul>
    */
    OurwaySysLayout queryOneByPageCaAndControlId(String pageCa, String controlId);

    /**
    *<p>方法:queryAllTabPanelLayouts 获取tab的配置信息 </p>
    *<ul>
     *<li> @param pageCa TODO</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysLayout>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/9/1 14:00  </li>
    *</ul>
    */
    List<OurwaySysLayout> queryAllTabPanelLayouts(String pageCa);

    List<OurwaySysLayout> queryAllDataListLayouts(String pageCa,String startComp);

    /**
    *<p>方法:listAllByPage 获取当前页面中所有的布局控件 </p>
    *<ul>
     *<li> @param page 页面对象</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysLayout>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/22 13:17  </li>
    *</ul>
    */
    List<OurwaySysLayout> listAllByPage(OurwaySysPage page);
}
