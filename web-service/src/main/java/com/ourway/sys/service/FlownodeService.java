package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.*;

import java.util.List;
import java.util.Map;

/**<p>接口 FlowService.java : <p>
 *<p>说明：功能流</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:36
</pre>
 */
public interface FlownodeService {

    PageInfo<OurwaySysFlownode> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize);

    OurwaySysFlownode listOneNodeById(String id);

    boolean saveOrUpdateNodePer(OurwaySysFlownode flownode,List<OurwaySysFlownodePer> pers);

    List<Map<String, Object>> removeNodePerByIds(List<String> owids);
}
