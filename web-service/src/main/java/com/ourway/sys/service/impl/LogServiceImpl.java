package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.OurwaySysLog;
import com.ourway.sys.dao.SysLogDao;
import com.ourway.sys.dao.SysObjectAttributeDao;
import com.ourway.sys.dao.SysObjectDao;
import com.ourway.sys.model.OurwaySysObject;
import com.ourway.sys.model.OurwaySysObjectAttribute;
import com.ourway.sys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 LogService.java : <p>
 * <p>说明：日志</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
@Service("logSer")
public class LogServiceImpl implements LogService {

    @Autowired
    private SysLogDao logDao;
    @Autowired
    SysObjectDao sysObjectDao;
    @Autowired
    SysObjectAttributeDao sysObjectAttributeDao;

    @Override
    public void saveOrUpdateLog(OurwaySysLog object) {
        logDao.saveOrUpdate(object);
    }


    @Override
    public PageInfo<OurwaySysLog> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize, String sortStr) {
        return logDao.listHqlForPage(models, pageNo, pageSize, sortStr);
    }

    @Override
    public List<Map<String, Object>> removeObjects(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //判断该业务类是否被业务日志类使用
            params.put("owid", owid);
            logDao.removeByParams(params);
            objs.add(params);
            //删除对象属性
        }
        return objs;
    }

    @Override
    public void removeAll() {
        logDao.removeAllLogs();
    }

    @Override
    public List<OurwaySysObjectAttribute> listLogAttrByClassName(String className) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("className", className);
        params.put("classLog", (byte) 1);
        OurwaySysObject sysObject = sysObjectDao.getOneByParams(params, "");
        if (null == sysObject)
            return null;
        params.clear();
        params.put("objectRefOwid", sysObject.getOwid());
        params.put("attributeDisplay", (byte) 1);
        List<OurwaySysObjectAttribute> objectAttributes = sysObjectAttributeDao.listAllByParam(params, "");
        if (null == objectAttributes || objectAttributes.size() <= 0)
            return null;
        return objectAttributes;
    }

    @Override
    public Map<String, List<OurwaySysObjectAttribute>> listRefLogAttributeByOwid(String owid) {
        List<String> classNames = logDao.listAllDistinctClassName(owid);
        if (null == classNames)
            return null;
        Map<String, List<OurwaySysObjectAttribute>> result = new HashMap<String, List<OurwaySysObjectAttribute>>();
        for (String className : classNames) {
            List<OurwaySysObjectAttribute> attributeList = listLogAttrByClassName(className);
            if (null != attributeList && attributeList.size() > 0) {
                result.put(className, attributeList);
            }
        }
        if (result.size() > 0)
            return result;
        else
            return null;
    }

    @Override
    public List<OurwaySysLog> listLogsByOwid(String owid) {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("logBuzFlag",owid);
        List<OurwaySysLog> logList = logDao.listAllByParam(params," logOperateTime asc");
        //判断是否每个都有后续的子表
        for(OurwaySysLog log:logList){
            log.setSubLogs(listLogsByOwidAndLabel(owid,log.getLogLabel()));
        }
        return logList;
    }

    @Override
    public Map<String, List<OurwaySysLog>> listRefLogsByOwid(String owid) {

        return logDao.listRefLogsByOwid(owid);
    }

    @Override
    public Map<String, List<OurwaySysLog>> listLogsByOwidAndLabel(String owid, String label) {
        return logDao.listRefLogsByOwid(owid,label);
    }
}
