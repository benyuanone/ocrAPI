package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysLanguage;

import java.util.List;
import java.util.Map;

/**<p>接口 LanguageSer.java : <p>
*<p>说明：国际化语言业务类</p>
*<pre>
*@author JackZhou
*@date 2017/4/12 13:26
</pre>
*/

public interface LanguageSer {

    /**
    *<p>方法:doCheckUniqueLabel TODO </p>
    *<ul>
     *<li> @param language TODO</li>
    *<li>@return java.lang.Boolean  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/12 15:20  </li>
    *</ul>
    */
    Boolean doCheckUniqueLabel(OurwaySysLanguage language);

    /**
    *<p>方法:saveOrUpdate 保存或者更新language对象 </p>
    *<ul>
     *<li> @param language language对象</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/12 21:17  </li>
    *</ul>
    */
    void saveOrUpdateLanguage(OurwaySysLanguage language);

    /**
    *<p>方法:listLanguageByPage 分页查询 </p>
    *<ul>
     *<li> @param hqlStatement 分页自组装对象</li>
     *<li> @param pageNo 当前页面</li>
     *<li> @param pageSize 条数</li>
    *<li>@return PageInfo<OurwaySysLanguage>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/13 1:02  </li>
    *</ul>
    */
    PageInfo<OurwaySysLanguage> listLanguageByPage(List<FilterModel> models, int pageNo, int pageSize);

    List<Map<String,Object>> removeItems(List<String> owids);

    OurwaySysLanguage detailLanguage(String owid);

    /*从类中生成多语言的标签*/
    boolean updateNewLanguageForObject(String labelId,String chnName);
}
