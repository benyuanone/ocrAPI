package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.dao.SysDepatempDao;
import com.ourway.sys.model.OurwaySysDepatemp;
import com.ourway.sys.model.OurwaySysEmploys;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysDepatempDaoImpl.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysDepatempDao")
public class SysDepatempDaoImpl extends BaseServiceImpl<OurwaySysDepatemp> implements SysDepatempDao {
    @Override
    public boolean doCheckUniqueLabelKey(OurwaySysDepatemp depatemp) {
        String hql = " from OurwaySysDepatemp where empPositionId=:empPositionId";
        if (!ValidateUtils.isEmpty(depatemp.getOwid()))
            hql = " from OurwaySysDepatemp where empPositionId=:empPositionId and owid<>:owid";
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("empPositionId", depatemp.getEmpPositionId());
        _params.put("owid", depatemp.getOwid());
        List<OurwaySysDepatemp> result = listAllByHql(hql, _params);
        if (null != result && result.size() > 0)
            return false;
        return true;
    }

    @Override
    public PageInfo<OurwaySysDepatemp> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysDepatemp.class, filters, sortStr);
        PageInfo<OurwaySysDepatemp> list = listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
        return list;
    }

    @Override
    public List<OurwaySysDepatemp> listDepatEmp(String userRefOwId) {
        String hql = "from OurwaySysDepatemp where userRefOwId=:userRefOwId";
        Map params = new HashMap();
        params.put("userRefOwId", userRefOwId);
        return listAllByHql(hql, params);
    }

    @Override
    public List<OurwaySysDepatemp> listAllByObjectId(String owid) {
        String hql = "from OurwaySysDepatemp where userRefOwId =:owid";
        Map params = new HashMap();
        params.put("owid", owid);
        return listAllByHql(hql, params);
    }

    @Override
    public List<OurwaySysDepatemp> listDepatemp(String owid) {
        String hql = "from OurwaySysDepatemp where userRefOwId=:owid";
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("owid", owid);
        return listAllByHql(hql, map);

    }


    @Override
    public OurwaySysDepatemp getOneDepatemp(String owid) {
        String hql = "from OurwaySysDepatemp where 1=1 and userRefOwId =:owid";
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("owid", owid);
        return getOneByHql(hql, _params);
    }

    @Override
    public void removeByUserOwid(String owid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userRefOwid", owid);
        List<OurwaySysDepatemp> depatempList = listAllByParam(params, "");
        if (null != depatempList && depatempList.size() > 0) {
            for (OurwaySysDepatemp depatemp : depatempList) {
                removeEntity(depatemp);
            }
        }
    }

    @Override
    public OurwaySysDepatemp listDefaultDepart(OurwaySysEmploys employs) {
        String hql = " from OurwaySysDepatemp where userRefOwId=:userRefOwId and empDefault=1 order by lasupdate desc";
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("userRefOwId",employs.getOwid());
        List<OurwaySysDepatemp> depatempList = listAllByHql(hql,params);
        if(null!=depatempList&&depatempList.size()>0)
            return depatempList.get(0);
        return null;
    }
}
