package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.*;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysPrivsallocate;
import com.ourway.sys.model.OurwaySysPrivsuser;
import com.ourway.sys.model.OurwaySysRoles;
import com.ourway.sys.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * <p>接口 RolesService.java : <p>
 * <p>说明：系统预置角色</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:39
 * </pre>
 */
@Service("rolesService")
public class RolesServiceImpl implements RolesService {
    @Autowired
    SysRolesDao sysRolesDao;
    @Autowired
    SysPrivsallocateDao sysPrivsallocateDao;
    @Autowired
    SysPrivsuserDao sysPrivsuserDao;
    @Autowired
    SysEmploysDao sysEmploysDao;
    @Autowired
    SysDicDao sysDicDao;

    @Override
    public void saveOrUpdateRoles(OurwaySysRoles depat) {
        sysRolesDao.saveOrUpdate(depat);
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("roleRefOwid", depat.getOwid());
        sysPrivsallocateDao.removeByParams(params);
        if (!TextUtils.isEmpty(depat.getRoles())) {
            String[] menuids = depat.getRoles().split("\\,");
            List<OurwaySysPrivsallocate> _saveLists = new ArrayList<OurwaySysPrivsallocate>();
            for (String menuid : menuids) {
                if (!TextUtils.isEmpty(menuid)) {
                    OurwaySysPrivsallocate privsUser = new OurwaySysPrivsallocate();
                    privsUser.setMenuRefOwid(Integer.parseInt(menuid));
                    privsUser.setRoleRefOwid(depat.getOwid());
                    _saveLists.add(privsUser);
                }
            }
            if (null != _saveLists && _saveLists.size() > 0)
                sysPrivsallocateDao.saveOrUpdate((Collection) _saveLists);
        }

    }

    public PageInfo<OurwaySysRoles> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        PageInfo<OurwaySysRoles> list = sysRolesDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
//        for (int i = 0; i < list.getRecords().size(); i++) {
//            list.getRecords().get(i).setRoleTypeLabel(sysDicDao.getOneRecoredName(list.getRecords().get(i).getRoleType()));
//        }
        return list;
    }

    @Override
    public List<OurwaySysRoles> listRoleByParams(List<FilterModel> filters) {
        return sysRolesDao.listRolesByParams(filters,"");
    }

    @Override
    public boolean doCheckUniqueLabel(OurwaySysRoles roles) {
        return sysRolesDao.doCheckUniqueLabelKey(roles);
    }

    @Override
    public List<Map<String, Object>> removeItems(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", owid);
            OurwaySysRoles roles = sysRolesDao.getOneByParams(params, "");
            if (null != roles) {
                Map<String, Object> param = new HashMap<String, Object>(1);
                param.put("roleRefOwid", owid);
                sysPrivsallocateDao.removeByParams(param);
                sysPrivsuserDao.removeByParams(param);
                objs.add(params);
            }
            sysRolesDao.removeByIds(owid);
        }
        return objs;
    }

    @Override
    public List<OurwaySysEmploys> listEmpRoles(Map param) {
        return sysEmploysDao.listEmpRols(param);
    }

    @Override
    public OurwaySysRoles listOneByOwid(String owid) {
        OurwaySysRoles role = sysRolesDao.getOneById(owid);
        //获取下面所有的选中节点，放入主表的role中区。
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("roleRefOwid", role.getOwid());
        List<OurwaySysPrivsallocate> allroles = sysPrivsallocateDao.listAllByParam(params,"");
        StringBuilder sb = new StringBuilder();
        for(OurwaySysPrivsallocate allrole:allroles){
           sb.append(allrole.getMenuRefOwid()+",");
        }
        if(null!=sb)
            role.setRoles(sb.toString());
        return role;
    }
}
