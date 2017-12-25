package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.sys.dao.SysFlowClassDao;
import com.ourway.sys.model.OurwaySysFlowClass;
import com.ourway.sys.model.OurwaySysObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysFlowClassDaoImpl.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysFlowClassDao")
public class SysFlowClassDaoImpl extends BaseServiceImpl<OurwaySysFlowClass> implements SysFlowClassDao {

    @Override
    public List<OurwaySysFlowClass> listFowClassByOwid(String owid) {
        String hql = " from OurwaySysFlowClass a,OurwaySysObject b where a.classRefOwid=b.owid and a.flowRefOwid=:flowRefOwid order by b.className";
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("flowRefOwid", owid);
        List<Object[]> objs = listObjsAllByHql(hql, map);
        List<OurwaySysFlowClass> result = new ArrayList<OurwaySysFlowClass>(objs.size());
        for (Object[] objects : objs) {
            OurwaySysFlowClass flowClass = (OurwaySysFlowClass) objects[0];
            OurwaySysObject sysObject = (OurwaySysObject) objects[1];
            flowClass.setSysObject(sysObject);
            result.add(flowClass);
        }
        return result;
    }
}
