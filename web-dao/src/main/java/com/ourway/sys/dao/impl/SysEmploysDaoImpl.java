package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.dao.SysEmploysDao;
import com.ourway.sys.model.OurwaySysDepat;
import com.ourway.sys.model.OurwaySysDepatemp;
import com.ourway.sys.model.OurwaySysEmploys;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysEmploysDaoImpl.java : <p>
 * <p>说明：员工</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysEmploysDao")
public class SysEmploysDaoImpl extends BaseServiceImpl<OurwaySysEmploys> implements SysEmploysDao {
    @Override
    public Boolean doCheckUniqueLabelKey(OurwaySysEmploys employs) {
        String hql = "";
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("empId", employs.getEmpId());
        if (!ValidateUtils.isEmpty(employs.getOwid())) {
            hql = "from OurwaySysEmploys where empId=:empId and owid<>:owid";
            _params.put("owid", employs.getOwid());
        } else {
            hql = "from OurwaySysEmploys where empId=:empId";
        }
        List<OurwaySysEmploys> result = listAllByHql(hql, _params);
        if (null != result && result.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<OurwaySysEmploys> listHqlForPage(List<FilterModel> filters, Integer pageNo, Integer pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysEmploys.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public List<OurwaySysEmploys> listEmpRols(Map map) {
        String inOrNot = "";
        int isuse = (Integer) map.get("isUse");
        if (isuse == 1) {
            inOrNot = "in";
        } else {
            inOrNot = "not in";
        }
        String hql = " from OurwaySysEmploys a where a.owid " + inOrNot + " (select userRefOwId from OurwaySysPrivsuser b where b.roleRefOwId='" +
                map.get("owid").toString() + "' )";
        if (!TextUtils.isEmpty(map.get("empName"))) {
            hql += " and a.empName like '%" + map.get("empName").toString() + "%'";
        }
        return listAllByHql(hql, map);
    }


    @Override
    public String listPositionDepart(OurwaySysEmploys employs) {
        String hql = " from OurwaySysDepat a,OurwaySysDepatemp b where a.owid = b.deptRefOwid and b.userRefOwId=:owid";
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("owid", employs.getOwid());
        List<Object[]> objs = listObjsAllByHql(hql, map);
        String result = "";
        for (Object[] obj : objs) {
            OurwaySysDepat depat = (OurwaySysDepat) obj[0];
            OurwaySysDepatemp depatemp = (OurwaySysDepatemp) obj[1];
            result += depat.getDepNo() + "-" + depatemp.getEmpPositionId() + ",";
        }
        return result;
    }

    @Override
    public OurwaySysEmploys listOneEmploy(String id) {
        return getOneById(id);
    }
}
