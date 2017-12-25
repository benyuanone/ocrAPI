package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysTemplate;

import java.util.List;

/**<p>接口 SysTemplateDao.java : <p>
 *<p>说明：模板类</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:44
</pre>
 */
public interface SysTemplateDao extends BaseService<OurwaySysTemplate> {

    PageInfo<OurwaySysTemplate> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);
}
