package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysLogSettingAttDao;
import com.ourway.sys.model.OurwaySysLogSettingAttribute;
import com.ourway.sys.service.LogSettingAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 LogSettingAttributeService.java : <p>
 *<p>说明：日志参数</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:37
</pre>
 */
@Service("logSettingAttrSer")
public class LogSettingAttributeServiceImpl implements LogSettingAttributeService {

    @Autowired
    private SysLogSettingAttDao logSetAttDao;

    @Override
    public void saveOrUpdateLogSettingAttr(OurwaySysLogSettingAttribute object) {
        logSetAttDao.saveOrUpdate(object);
    }

    @Override
    public void removeententLogSettingAttrByIds(HqlStatement hql) {
        List<OurwaySysLogSettingAttribute> objList = logSetAttDao.listAllByHql(hql.getHql(), hql.getParams());
        for (OurwaySysLogSettingAttribute obj : objList) {
            logSetAttDao.removeEntity(obj);
        }
    }

    @Override
    public PageInfo<OurwaySysLogSettingAttribute> listHqlForPage(HqlStatement hql, int pageNo, int pageSize) {
        return logSetAttDao.listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }

    @Override
    public List<OurwaySysLogSettingAttribute> listLogSettingAttrByStatement(HqlStatement hql) {
        List<OurwaySysLogSettingAttribute> objList = logSetAttDao.listAllByHql(hql.getHql(), hql.getParams());
        Map<String, Object> _params = new HashMap<String, Object>();
        return objList;
    }
}
