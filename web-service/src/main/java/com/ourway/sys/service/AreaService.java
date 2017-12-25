package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.OurwaySysArea;

import java.util.List;
import java.util.Map;

/**<p>接口 AreaService.java : <p>
 *<p>说明：地区</p>
 *<pre>
 *@author cc
 *@date 14:35 2017/4/12
</pre>
 */
public interface AreaService {
    void saveOrUpdate(OurwaySysArea area);

    void removeMenu(Map<String, Object> map);

    PageInfo<OurwaySysArea> listHqlForPage(List<FilterModel> filters, Integer pageNo, Integer pageSize);
}
