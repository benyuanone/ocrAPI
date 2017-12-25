package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysFlowClass;

import java.util.List;

/**<p>接口 SysFlowClassDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:42
</pre>
 */
public interface SysFlowClassDao extends BaseService<OurwaySysFlowClass> {

    List<OurwaySysFlowClass> listFowClassByOwid(String owid);
}
