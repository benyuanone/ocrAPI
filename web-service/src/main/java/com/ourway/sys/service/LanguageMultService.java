package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysLanguageMult;

/**<p>接口 LanguageMultService.java : <p>
 *<p>说明：多语言具体名称</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:36
</pre>
 */
public interface LanguageMultService {
    /**
    *<p>方法:queryOneByKey 根据语言和key来获取对应的多语言 </p>
    *<ul>
     *<li> @param language 当前客户的语言类型</li>
     *<li> @param key 标签key</li>
    *<li>@return com.ourway.sys.model.OurwaySysLanguageMult  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/2 15:50  </li>
    *</ul>
    */
    OurwaySysLanguageMult queryOneByKey(String language,String key);
}
