package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.*;
import com.ourway.sys.dao.SysDfilterDao;
import com.ourway.sys.dao.SysEmploysDao;
import com.ourway.sys.model.OurwaySysDfilter;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.service.DfilterService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 DfilterService.java : <p>
 * <p>说明：数据过滤</p>
 * <pre>
 * @author cc
 * @date 14:36 2017/4/12
 * </pre>
 */
@Service("dfilterService")
public class DfilterServiceImpl implements DfilterService {

    @Autowired
    SysDfilterDao sysDfilterDao;
    @Autowired
    SysEmploysDao sysEmploysDao;

    @Override
    public PageInfo<OurwaySysDfilter> listDfilterForPage(List<FilterModel> flist, Integer pageNo, Integer pageSize, String sortStr) {

        PageInfo<OurwaySysDfilter> pageInfo = sysDfilterDao.listDfilterForPage(flist, pageNo, pageSize, sortStr);
        if (null != pageInfo.getRecords() && pageInfo.getRecords().size() > 0) {
            List<OurwaySysDfilter> list = pageInfo.getRecords();
            for (OurwaySysDfilter dfilter : list) {
                OurwaySysEmploys employs = sysEmploysDao.getOneById(dfilter.getUserRefOwid());
                dfilter.setOurwaySysEmploys(employs);
            }
        }
        return pageInfo;
    }


    @Override
    public List<Map<String, Object>> removeItems(List<String> owids) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (String owid : owids) {
            Map<String, Object> param = new HashMap<>(1);
            param.put("owid", owid);
            sysDfilterDao.removeByParams(param);
            list.add(param);
        }
        return list;
    }


    @Override
    public OurwaySysDfilter detailOneDfilter(String owid) {
        OurwaySysDfilter dfilter = sysDfilterDao.getOneById(owid);
        if (null != dfilter && !TextUtils.isEmpty(dfilter.getUserRefOwid())) {
            OurwaySysEmploys employs = sysEmploysDao.getOneById(dfilter.getUserRefOwid());
            dfilter.setOurwaySysEmploys(employs);
        }
        return dfilter;
    }


    @Override
    public void saveOrUpdate(OurwaySysDfilter ourwaySysDfilter) {
        sysDfilterDao.saveOrUpdate(ourwaySysDfilter);
    }

    @Override
    public List<OurwaySysDfilter> listByUserOwid(String owid) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userRefOwId", owid);
        return sysDfilterDao.listAllByParam(params, "indexno");
    }

    @Override
    public boolean doCheckSameUserApi(OurwaySysDfilter ourwaySysDfilter) {
        return sysDfilterDao.doCheckSameUserApi(ourwaySysDfilter);
    }

    @Override
    public String doComposeFilter(String pageCa, int HqlType) {
        Session session = ShiroUtils.getSession();
        if (null == session)
            return "";
        OurwaySysEmploys employs = (OurwaySysEmploys) session.getAttribute(SessionUtils.USER_KEY);
        if (null == employs)
            return "";
        OurwaySysDfilter dfilter = getOneByPageCa(employs, pageCa);
        if (null == dfilter)
            dfilter = getOneByPageCa(employs, "-1");
        if (null == dfilter)
            return "";
        else {
            if (HqlType == 1)
                return getSqlFromFilter(employs, dfilter);
            else
                return getHqlFromFilter(employs, dfilter);

        }

    }

    String getSqlFromFilter(OurwaySysEmploys employs, OurwaySysDfilter dfilter) {
        switch (dfilter.getViewArea()) {
            case 0:

                return "CREATOR='" + employs.getOwid() + "'";
            case 1:
                if (!TextUtils.isEmpty(employs.getEmpDefaultDept()))
                    return "DEPT_ID=" + employs.getEmpDefaultDept();
                else
                    return "DEPT_ID=-1";
            case 2:
                if (!TextUtils.isEmpty(employs.getEmpDefaultDept()))
                    return "DEPT_PATH like '%/" + employs.getEmpDefaultDept() + "/%";
                else
                    return "DEPT_PATH='-1'";
            case 3:
                if (!TextUtils.isEmpty(employs.getEmpDefaultDept()))
                    return " DEPT_ID=" + employs.getEmpDefaultDept() + " or DEPT_PATH like '%/" + employs.getEmpDefaultDept() + "/% ";
                else
                    return "DEPT_PATH='-1'";
            case 4:
                return "";
            case 5:
                if (!TextUtils.isEmpty(dfilter.getViewCondition()))
                    return dfilter.getViewCondition().replaceAll("#empId#", employs.getOwid()).replaceAll("#empName#", employs.getEmpName()).replaceAll("#deptId#", employs.getEmpDefaultDept() + "");
                break;
        }
        return "";
    }

    String getHqlFromFilter(OurwaySysEmploys employs, OurwaySysDfilter dfilter) {
        switch (dfilter.getViewArea()) {
            case 0:

                return "creator='" + employs.getOwid() + "'";
            case 1:
                if (!TextUtils.isEmpty(employs.getEmpDefaultDept()))
                    return "deptId=" + employs.getEmpDefaultDept();
                else
                    return "deptId=-1";
            case 2:
                if (!TextUtils.isEmpty(employs.getEmpDefaultDept()))
                    return "deptPath like '%/" + employs.getEmpDefaultDept() + "/%";
                else
                    return "deptPath='-1'";
            case 3:
                if (!TextUtils.isEmpty(employs.getEmpDefaultDept()))
                    return " deptId=" + employs.getEmpDefaultDept() + " or deptPath like '%/" + employs.getEmpDefaultDept() + "/% ";
                else
                    return "deptPath='-1'";
            case 4:
                return "";
            case 5:
                if (!TextUtils.isEmpty(dfilter.getViewCondition()))
                    return dfilter.getViewCondition().replaceAll("#empId#", employs.getOwid()).replaceAll("#empName#", employs.getEmpName()).replaceAll("#deptId#", employs.getEmpDefaultDept() + "");
                break;
        }
        return "";
    }

    OurwaySysDfilter getOneByPageCa(OurwaySysEmploys employs, String pageCa) {
        OurwaySysDfilter dfilter = (OurwaySysDfilter) CacheUtil.getVals("filter." + employs.getEmpId() + "." + pageCa, OurwaySysDfilter.class);
        if (null == dfilter) {
            Map<String, Object> params = new HashMap<String, Object>(2);
            params.put("userRefOwid", employs.getOwid());
            params.put("pageCa", pageCa);
            params.put("viewFlag", (byte) 1);
            List<OurwaySysDfilter> dfilterList = sysDfilterDao.listAllByParam(params, "");
            if (null != dfilterList && dfilterList.size() > 0) {
                dfilter = dfilterList.get(0);
                CacheUtil.setVal("filter." + employs.getEmpId() + "." + pageCa, dfilter);
            }
        }
        return dfilter;
    }
}
