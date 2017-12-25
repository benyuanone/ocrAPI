package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysLayoutComponent;

import java.util.List;
import java.util.Map;

/**<p>接口 LayoutComponentService.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:37
</pre>
 */
public interface LayoutComponentService {

    /**
    *<p>方法:listAllByParams 获取指定louyout下面的布局控件 </p>
    *<ul>
     *<li> @param params TODO</li>
     *<li> @param sortStr TODO</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysLayoutComponent>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/22 13:37  </li>
    *</ul>
    */
    List<OurwaySysLayoutComponent> listAllByParams(Map<String,Object> params,String sortStr);

    List<OurwaySysLayoutComponent> listAllByParams(OurwaySysLayout layout);


}
