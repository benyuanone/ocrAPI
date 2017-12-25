package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysPrivsallocate;
import com.ourway.sys.model.OurwaySysPrivsuser;
import com.ourway.sys.model.OurwaySysRoles;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 RolesService.java : <p>
 * <p>说明：系统预置角色</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:39
 * </pre>
 */
public interface RolesService {
    void saveOrUpdateRoles(OurwaySysRoles depat);

    PageInfo<OurwaySysRoles> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);


    List<OurwaySysRoles> listRoleByParams(List<FilterModel> filters);

    boolean doCheckUniqueLabel(OurwaySysRoles roles);

    List<Map<String, Object>> removeItems(List<String> owids);

    List<OurwaySysEmploys> listEmpRoles(Map param);

    OurwaySysRoles listOneByOwid(String owid);

}
