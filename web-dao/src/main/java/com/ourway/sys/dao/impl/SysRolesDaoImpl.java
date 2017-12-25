package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysRolesDao;
import com.ourway.sys.model.OurwaySysRoles;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysRolesDaoImpl.java : <p>
 *<p>说明：规则</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:44
</pre>
 */
@Repository("rolesDao")
public class SysRolesDaoImpl  extends BaseServiceImpl<OurwaySysRoles> implements SysRolesDao {
    @Override
    public boolean doCheckUniqueLabelKey(OurwaySysRoles roles) {
        String hql = " from OurwaySysRoles where owid=:owid";
        Map<String,Object> _params = new HashMap<String,Object>();
        _params.put("owid",roles.getOwid());
        List<OurwaySysRoles> result =  listAllByHql(hql,_params);
        if(null!=result&&result.size()>0)
            return false;
        return true;
    }

    @Override
    public PageInfo<OurwaySysRoles> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysRoles.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);

    }

    @Override
    public List<OurwaySysRoles> listRolesByParams(List<FilterModel> filters, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysRoles.class, filters, sortStr);
        return listAllByHql(hqlStatement.getHql(),hqlStatement.getParams());
    }
}
