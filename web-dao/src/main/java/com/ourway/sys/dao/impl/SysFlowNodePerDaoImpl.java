package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.sys.dao.SysFlowNodePerDao;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysFlownode;
import com.ourway.sys.model.OurwaySysFlownodePer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
@Repository("sysFlowNodePerDao")
public class SysFlowNodePerDaoImpl extends BaseServiceImpl<OurwaySysFlownodePer> implements SysFlowNodePerDao {
    @Override
    public List<OurwaySysFlownodePer> listAllPerByNode(OurwaySysFlownode flownode) {
        String hql = " from OurwaySysFlownodePer where flowperRefOwid=:flowperRefOwid order by perName ";
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("flowperRefOwid", flownode.getOwid());
        return listAllByHql(hql, map);
    }

    @Override
    public OurwaySysFlownodePer listOneFlowPer(String empId, String flowId, String nodeId) {
        String hql = "";
        Map<String, Object> params = new HashMap<String, Object>();
        hql = " from OurwaySysFlownodePer c,OurwaySysFlownode a,OurwaySysFlow b where c.flowperRefOwid=a.owid and a.flownodeRefOwid=b.owid and a.nodeCode=:nodeCode and b.flowId=:flowId and c.perOwid=:perOwid ";
        params.put("flowId", flowId);
        params.put("nodeCode", nodeId);
        params.put("perOwid", empId);
        List<Object> objs = listObjAllByHql(hql, params);
        if (null == objs || objs.size() <= 0)
            return null;
        else {
            Object[] _obj = (Object[]) objs.get(0);
            OurwaySysFlownodePer nodePer = (OurwaySysFlownodePer) _obj[0];
            return nodePer;
        }
    }

    @Override
    public OurwaySysFlownode listOneFlowNode(String flowId, String nodeId) {
        String hql = "";
        Map<String, Object> params = new HashMap<String, Object>();
        hql = " from OurwaySysFlownode a,OurwaySysFlow b where a.flownodeRefOwid=b.owid and a.nodeCode=:nodeCode and b.flowId=:flowId  ";
        params.put("flowId", flowId);
        params.put("nodeCode", nodeId);
        List<Object> objs = listObjAllByHql(hql, params);
        if (null == objs || objs.size() <= 0)
            return null;
        else {
            Object[] _obj = (Object[]) objs.get(0);
            OurwaySysFlownode node = (OurwaySysFlownode) _obj[0];
            return node;
        }
    }

    @Override
    public boolean doCheckPrivs(OurwaySysEmploys employs, OurwaySysFlownodePer per, String owid, String className) {
        if (null == per.getPerFilterType())
            return true;
        //0:不限,1:本人,2:本部门,3:下属部门,4:本部门及下属部门,5:其它
        String hql = "";
        Map<String, Object> params = new HashMap<String, Object>();
        List<Object> objs = null;
        switch (per.getPerFilterType()) {
            case 0:
                return true;
            case 1:
                params.clear();
                hql = " from " + className + " where owid=:owid and creator=:creator";
                params.put("owid", owid);
                params.put("creator", employs.getOwid());
                objs = listObjAllByHql(hql, params);
                if (null != objs && objs.size() > 0)
                    return true;
                else
                    return false;
            case 2:
                params.clear();
                if (null == employs.getEmpDefaultDept())
                    return false;
                hql = " from " + className + " where owid=:owid and deptId=:deptId";
                params.put("owid", owid);
                params.put("deptId", employs.getEmpDefaultDept());
                objs = listObjAllByHql(hql, params);
                if (null != objs && objs.size() > 0)
                    return true;
                else
                    return false;
            case 3:
                params.clear();
                if (null == employs.getEmpDefaultDept())
                    return false;
                hql = " from " + className + " where owid=:owid and deptId like :deptId and deptId<>:deptId1";
                params.put("owid", owid);
                params.put("deptId", "%/" + employs.getEmpDefaultDept() + "/%");
                params.put("deptId1", employs.getEmpDefaultDept());
                objs = listObjAllByHql(hql, params);
                if (null != objs && objs.size() > 0)
                    return true;
                else
                    return false;
            case 4:
                params.clear();
                if (null == employs.getEmpDefaultDept())
                    return false;
                hql = " from " + className + " where owid=:owid and deptId like :deptId";
                params.put("owid", owid);
                params.put("deptId", "%/" + employs.getEmpDefaultDept() + "/%");
                objs = listObjAllByHql(hql, params);
                if (null != objs && objs.size() > 0)
                    return true;
                else
                    return false;
            case 5:
                params.clear();
                if (null == employs.getEmpDefaultDept())
                    return false;
                hql = " from " + className + " where owid=:owid and  " + per.getPerFilter();
                params.put("owid", owid);
                objs = listObjAllByHql(hql, params);
                if (null != objs && objs.size() > 0)
                    return true;
                else
                    return false;
        }
        return false;
    }

    @Override
    public boolean doCheckSelfPrivs(OurwaySysEmploys employs, String owid, String className) {
        String hql = " from " + className + " where owid=:owid and creator=:creator";
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("owid", owid);
        map.put("creator", employs.getOwid());
        List<Object> objs = listObjAllByHql(hql, map);
        if (null != objs && objs.size() > 0)
            return true;
        return false;
    }

    @Override
    public List<OurwaySysEmploys> listAllEmploys(String flowid, String nodeId) {
        String hql = " from OurwaySysFlow a,OurwaySysFlownode b,OurwaySysFlownodePer c,OurwaySysEmploys d  where a.owid=b.flownodeRefOwid and b.owid=c.flowperRefOwid and b.nodeCode=:nodeCode and c.perOwid=d.owid";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flowId", flowid);
        map.put("nodeCode", nodeId);
        List<Object[]> objs = listObjsAllByHql(hql, map);
        List<OurwaySysEmploys> result = new ArrayList<OurwaySysEmploys>();
        if (null != objs && objs.size() > 0) {
            for (Object[] o : objs) {
                OurwaySysEmploys per = (OurwaySysEmploys) o[3];
                result.add(per);
            }
        }
        return result;
    }
}
