package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysTempcontrolDao;
import com.ourway.sys.model.OurwaySysTempcontrol;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysTempcontrolDaoImpl.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:44
</pre>
 */
@Repository("sysTempcontrolDao")
public class SysTempcontrolDaoImpl extends BaseServiceImpl<OurwaySysTempcontrol> implements SysTempcontrolDao {

    @Override
    public List<OurwaySysTempcontrol> listAllByTemplateId(String templateId) {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("tempRefOwid",templateId);
        HqlStatement hql = new HqlStatement(OurwaySysTempcontrol.class,params,"controlId");
        return listAllByHql(hql.getHql(),hql.getParams());
    }
}
