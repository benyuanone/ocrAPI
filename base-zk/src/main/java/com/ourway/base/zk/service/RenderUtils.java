package com.ourway.base.zk.service;


import org.zkoss.zk.ui.Component;

/**<p>接口 RenderUtils.java : <p>
*<p>说明：TODO</p>
*<pre>
*@author JackZhou
*@date 2017/5/16 1:32
</pre>
*/
public interface RenderUtils {
    /**
    *<p>方法:rendar 转换显示类 </p>
    *<ul>
     *<li> @param o TODO</li>
    *<li>@return java.lang.String  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/16 1:34  </li>
    *</ul>
    */
    String rendar(Object o, String formatter, Component component);
}
