package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysObjectAttributeDao;
import com.ourway.sys.model.OurwaySysObjectAttribute;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysObjectAttributeDaoImpl.java : <p>
 *<p>说明：对象属性</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
@Repository("objectAttDao")
public class SysObjectAttributeDaoImpl extends BaseServiceImpl<OurwaySysObjectAttribute> implements SysObjectAttributeDao {
    @Override
    public List<OurwaySysObjectAttribute> listAllByObjectId(String objId) {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("objectRefOwid",objId);
        HqlStatement hql = new HqlStatement(OurwaySysObjectAttribute.class,params,"");
        return listAllByHql(hql.getHql(),hql.getParams());
    }
}
