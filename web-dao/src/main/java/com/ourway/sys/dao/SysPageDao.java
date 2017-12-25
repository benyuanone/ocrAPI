package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysLanguage;
import com.ourway.sys.model.OurwaySysPage;

import java.util.List;

/**<p>接口 SysPageDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysPageDao extends BaseService<OurwaySysPage> {

    Boolean doCheckUniquePageCA(OurwaySysPage ourwaySysPage);

    PageInfo<OurwaySysPage> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize,String sortStr);

    List<OurwaySysPage> listAllPages(List<FilterModel> filters,String sortStr);

}
