package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysFlowDao;
import com.ourway.sys.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysFlowDaoImpl.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysFlowDao")
public class SysFlowDaoImpl extends BaseServiceImpl<OurwaySysFlow> implements SysFlowDao {

    @Override
    public void removeComponents(OurwaySysFlow flow) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("pageRefOwid", flow.getOwid());
        removeByParams(params);
    }

    public PageInfo<OurwaySysFlow> listFowByPage(List<FilterModel> filterModels, int pageNo, int pageSize) {
        HqlStatement hql = new HqlStatement("from OurwaySysFlow ", filterModels);
        return listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }

    @Override
    public OurwaySysFlow listSysFlowByClass(String className) {
        String hql = " from OurwaySysFlow a,OurwaySysFlowClass b,OurwaySysObject c where a.owid=b.flowRefOwid and b.classRefOwid=c.owid and c.className=:className ";
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("className", className);
        List<Object[]> objs = listObjsAllByHql(hql, map);
        if (null != objs&&objs.size()>0) {
            OurwaySysFlow flow = (OurwaySysFlow) objs.get(0)[0];
            OurwaySysFlowClass flowClass = (OurwaySysFlowClass)objs.get(0)[1];
            flow.setOurwaySysFlowClass(flowClass);
            return flow;
        }
        return null;
    }
}
