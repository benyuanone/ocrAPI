package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysDepatDao;
import com.ourway.sys.model.OurwaySysDepat;
import com.ourway.sys.model.OurwaySysDepatemp;
import com.ourway.sys.model.OurwaySysMenus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysDepatDaoImpl.java : <p>
 * <p>说明：部门</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysDepatDao")
public class SysDepatDaoImpl extends BaseServiceImpl<OurwaySysDepat> implements SysDepatDao {
    @Override
    public boolean doCheckUniqueLabelKey(OurwaySysDepat depat) {
        String hql = " from OurwaySysDepat where owid=:owid";
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("owid", depat.getOwid());
        List<OurwaySysDepat> result = listAllByHql(hql, _params);
        if (null != result && result.size() > 0)
            return false;
        return true;
    }

    @Override
    public PageInfo<OurwaySysDepat> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysDepat.class, models, null);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public void removeDepart(Map<String, Object> map) {
        String hql = "";
        HqlStatement hqlStatement = new HqlStatement(OurwaySysDepat.class, map);
        List<OurwaySysDepat> departs = listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
        for (OurwaySysDepat depart : departs) {
            hql = "from OurwaySysDepat where path like '" + depart.getPath() + "/%' ";
            List<OurwaySysDepat> depatList = listAllByHql(hql, null);
            for (OurwaySysDepat depat : depatList) {
                hql = "delete from OurwaySysDepatemp where deptRefOwid=" + depat.getOwid();
                updateBulk(hql);
                removeEntity(depat);
            }
            removeEntity(depart);
        }
    }


    /*depat*/
    @Override
    public OurwaySysDepat getDept(Integer owid) {
        String hql = "from OurwaySysDepat where 1=1 and owid =:owid";
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("owid", owid);
        return getOneByHql(hql, _params);
    }


    /*depat*/
    @Override
    public List<OurwaySysDepat> getDeptList(Integer depCode) {
        String hql = "from OurwaySysDepat where fid =:depCode";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("depCode", depCode);

        return listAllByHql(hql, params);
    }

    /*depat*/
    @Override
    public List<OurwaySysDepat> listAllDepat(Integer depCode, String vcName, String nextDepName) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer("from OurwaySysDepat where fid=:depCode");
        params.put("vcName", vcName);

        if (null != nextDepName && nextDepName.length() > 0) {
            sb.append(" and depName like :depName ");
            params.put("depName", "%" + nextDepName + "%");

        }
        sb.append(" and owid in(select depCode from BckjBizStockEntity where vcName=:vcName and stockNumber >0)");
        params.put("depCode", depCode);
        return listAllByHql(sb.toString(), params);
    }


    /*depat*/
    @Override
    public List<OurwaySysDepat> listAllNextDepat(Integer depCode) {

        Map<String, Object> param = new HashMap<String, Object>(1);

        String hql = "from OurwaySysDepat where fid =:depCode";

        param.put("depCode", depCode);

        List<OurwaySysDepat> list = listAllByHql(hql, param);

        return list;
    }

    @Override
    public List<OurwaySysDepat> listDeptByFilters(List<FilterModel> models) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysDepat.class, models, "cc,px");
        return listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
    }

    @Override
    public List<OurwaySysDepat> listSubDepats(OurwaySysDepat menus) {
        String hql = "from OurwaySysDepat where path like ? order by cc,px";
        List<OurwaySysDepat> subMenus = listAllByParams(hql, "%/" + menus.getOwid() + "/%");
        return subMenus;
    }
}
