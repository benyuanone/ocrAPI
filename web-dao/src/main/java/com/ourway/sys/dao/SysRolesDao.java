package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysRoles;

import java.util.List;

/**
 * <p>接口 SysRolesDao.java : <p>
 * <p>说明：规则</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:44
 * </pre>
 */
public interface SysRolesDao extends BaseService<OurwaySysRoles> {
    boolean doCheckUniqueLabelKey(OurwaySysRoles roles);

    PageInfo<OurwaySysRoles> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<OurwaySysRoles> listRolesByParams(List<FilterModel> filters, String sortStr);

}
