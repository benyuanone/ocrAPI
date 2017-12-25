package com.ourway.sys.dao;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysArea;

import java.util.List;
import java.util.Map;

/**<p>接口 SysAreaDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:42
</pre>
 */
public interface SysAreaDao extends BaseService<OurwaySysArea> {

    void removeMenu(Map<String, Object> map);

    PageInfo<OurwaySysArea> listHqlForPage(List<FilterModel> filters, Integer pageNo, Integer pageSize);
}
