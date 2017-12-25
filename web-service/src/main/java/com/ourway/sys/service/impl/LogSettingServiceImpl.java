package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysLogDao;
import com.ourway.sys.dao.SysLogSettingAttDao;
import com.ourway.sys.dao.SysLogSettingDao;
import com.ourway.sys.model.OurwaySysLogSetting;
import com.ourway.sys.model.OurwaySysLogSettingAttribute;
import com.ourway.sys.service.LogSettingService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 LogSettingService.java : <p>
 * <p>说明：日志设置</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
@Service("logSetting")
public class LogSettingServiceImpl implements LogSettingService {

    @Autowired
    private SysLogSettingDao logSetDao;
    @Autowired
    private SysLogSettingAttDao logSetAttrDao;
    @Autowired
    private SysLogDao logDao;

    @Override
    public OurwaySysLogSetting saveOrUpdateLogSetting(OurwaySysLogSetting object, List<OurwaySysLogSettingAttribute> attributeList) {
        logSetDao.saveOrUpdate(object);
        for(OurwaySysLogSettingAttribute attribute : attributeList){
            attribute.setLogRefOwid(object.getOwid());
        }
        logSetAttrDao.saveOrUpdate(attributeList);
        object.setDataList(attributeList);
        return object;
    }

    @Override
    public void removeententLogSettingByIds(HqlStatement hql) {
        List<OurwaySysLogSetting> objList = logSetDao.listAllByHql(hql.getHql(), hql.getParams());
        for (OurwaySysLogSetting obj : objList) {
            logSetDao.removeEntity(obj);
        }
    }

    @Override
    public PageInfo<Object[]> listHqlForPage(int pageNo, int pageSize) {
        Map params = new HashedMap();
        return logSetDao.listLogSettingByHql(pageNo, pageSize);
    }

    @Override
    public List<OurwaySysLogSetting> listLogSettingByStatement(HqlStatement hql) {
        List<OurwaySysLogSetting> objList = logSetDao.listAllByHql(hql.getHql(), hql.getParams());
        Map<String, Object> _params = new HashMap<String, Object>();
        for (OurwaySysLogSetting obj : objList) {
            _params.put("settingRefOwid", obj.getOwid());
//            obj.setSysLogsList(logDao.listAllByHql("from OurwaySysLog ", _params));
        }
        return objList;
    }

    @Override
    public int countObject(String owid) {
        return logDao.countObject(owid);
    }
}
