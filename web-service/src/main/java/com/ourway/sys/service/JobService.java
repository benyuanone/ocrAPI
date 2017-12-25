package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.model.OurwaySysJob;

import java.util.List;
import java.util.Map;

/**<p>接口 ApiService.java : <p>
 *<p>说明：接口类</p>
 *<pre>
 *@author cc
 *@date 14:35 2017/4/12
</pre>
 */
public interface JobService {

    void saveOrUpdateAll(List<OurwaySysJob> values);

    List<OurwaySysJob> listJobs( String orderBy);
}
