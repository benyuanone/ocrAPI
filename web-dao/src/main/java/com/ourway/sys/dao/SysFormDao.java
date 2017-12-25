package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysFlow;
import com.ourway.sys.model.OurwaySysForm;
import com.ourway.sys.model.OurwaySysPage;

import java.util.List;

/**<p>接口 SysPageDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysFormDao extends BaseService<OurwaySysForm> {

    PageInfo<OurwaySysForm> listFowByPage(List<FilterModel> filterModels, int pageNo, int pageSize);

}
