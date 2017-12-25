package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysLogSettingAttDao;
import com.ourway.sys.dao.SysObjectAttributeDao;
import com.ourway.sys.model.OurwaySysObjectAttribute;
import com.ourway.sys.service.ObjectAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 ObjectAttributeService.java : <p>
 *<p>说明：对象属性</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:37
</pre>
 */
@Service("objectAttributeSer")
public class ObjectAttributeServiceImpl implements ObjectAttributeService {

    @Autowired
    private SysObjectAttributeDao objectAttDao;
    @Autowired
    private SysLogSettingAttDao logSetAttDao;
    @Override
    public void saveOrUpdateObject(OurwaySysObjectAttribute objectAttribute) {
        objectAttDao.saveOrUpdate(objectAttribute);
    }

    @Override
    public void removeententObjByIds(HqlStatement hql) {
        List<OurwaySysObjectAttribute> objList = objectAttDao.listAllByHql(hql.getHql(), hql.getParams());
        for (OurwaySysObjectAttribute obj : objList) {
            objectAttDao.removeEntity(obj);
        }
    }

    @Override
    public PageInfo<OurwaySysObjectAttribute> listHqlForPage(HqlStatement hql, int pageNo, int pageSize) {
        return objectAttDao.listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }

    @Override
    public List<OurwaySysObjectAttribute> listObjByStatement(HqlStatement hql) {
        List<OurwaySysObjectAttribute> objList = objectAttDao.listAllByHql(hql.getHql(), hql.getParams());
        Map<String, Object> _params = new HashMap<String, Object>();
        for (OurwaySysObjectAttribute obj : objList) {
            _params.put("logRefOwid", obj.getOwid());
            obj.setLogSettingAttributesList(logSetAttDao.listAllByHql("from OurwaySysLogSettingAttribute ", _params));
        }
        return objList;
    }

}
