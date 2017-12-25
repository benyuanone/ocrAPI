package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.OurwaySysLog;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysLogDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysLogDaoImpl.java : <p>
 * <p>说明：日志</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:43
 * </pre>
 */
@Repository("logDao")
public class SysLogDaoImpl extends BaseServiceImpl<OurwaySysLog> implements SysLogDao {
    @Override
    public int countObject(String owid) {
        Map params = new HashedMap();
        params.put("objectRefOwid", owid);
        String countHql = "select count(*) from OurwaySysLogSetting where objectRefOwid=:objectRefOwid";
        return countHqlByParams(countHql, params);
    }

    @Override
    public PageInfo<OurwaySysLog> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysLog.class, models, sortStr);

        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public void removeAllLogs() {
        String hql = "delete from OurwaySysLog";
        updateBulk(hql, null);
    }

    @Override
    public List<String> listAllDistinctClassName(String owid) {
        String hql = "select distinct logClassName from OurwaySysLog where logBuzRefFlag like :logBuzRefFlag";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("logBuzRefFlag", "%," + owid + ",%");
        List<Object> objs = listObjAllByHql(hql, params);
        if (null == objs || objs.size() <= 0)
            return null;
        List<String> result = new ArrayList<String>();
        for (Object o : objs) {
            result.add(o.toString());
        }
        return result;
    }

    @Override
    public Map<String, List<OurwaySysLog>> listRefLogsByOwid(String owid) {
        String hql = " from OurwaySysLog where logBuzRefFlag like :logBuzRefFlag order by logOperateTime";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("logBuzRefFlag", "%," + owid + ",%");
        List<OurwaySysLog> objs = listAllByHql(hql, params);
        if (null == objs || objs.size() <= 0)
            return null;
        Map<String,List<OurwaySysLog>> resultMap = new HashMap<String,List<OurwaySysLog>>();
        for(OurwaySysLog ourwaySysLog:objs){
            List<OurwaySysLog> sysLogs = new ArrayList<OurwaySysLog>();
            if(null!=resultMap.get(ourwaySysLog.getLogClassName()))
                sysLogs = resultMap.get(ourwaySysLog.getLogClassName());
            sysLogs.add(ourwaySysLog);
            resultMap.put(ourwaySysLog.getLogClassName(),sysLogs);

        }
        return resultMap;
    }

    @Override
    public Map<String, List<OurwaySysLog>> listRefLogsByOwid(String owid, String label) {
        String hql = " from OurwaySysLog where logBuzRefFlag like :logBuzRefFlag  ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("logBuzRefFlag", "%," + owid + ",%");
        if(!TextUtils.isEmpty(label)){
            hql +=" and logLabel=:logLabel";
            params.put("logLabel",label);
        }
        hql +=" order by logOperateTime";
        List<OurwaySysLog> objs = listAllByHql(hql, params);
        if (null == objs || objs.size() <= 0)
            return null;
        Map<String,List<OurwaySysLog>> resultMap = new HashMap<String,List<OurwaySysLog>>();
        for(OurwaySysLog ourwaySysLog:objs){
            List<OurwaySysLog> sysLogs = new ArrayList<OurwaySysLog>();
            if(null!=resultMap.get(ourwaySysLog.getLogClassName()))
                sysLogs = resultMap.get(ourwaySysLog.getLogClassName());
            sysLogs.add(ourwaySysLog);
            resultMap.put(ourwaySysLog.getLogClassName(),sysLogs);

        }
        return resultMap;
    }
}
