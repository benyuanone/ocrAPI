package com.ourway.sys.service.impl;

import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysPrivsuserDao;
import com.ourway.sys.dao.SysRolesDao;
import com.ourway.sys.model.OurwaySysPrivsuser;
import com.ourway.sys.model.OurwaySysRoles;
import com.ourway.sys.service.PrivsuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 PrivsuserService.java : <p>
 * <p>说明：权限分配</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:38
 * </pre>
 */
@Service("privsuserService")
public class PrivsuserServiceImpl implements PrivsuserService {
    @Autowired
    SysPrivsuserDao sysPrivsuserDao;
    @Autowired
    SysRolesDao rolesDao;

    @Override
    public boolean saveUserPrivs(String roleId, List<String> selEmps) {
        if (!TextUtils.isEmpty(roleId)) {
            return sysPrivsuserDao.saveUserPrivs(roleId, selEmps);
        }
        return false;
    }

    @Override
    public List<OurwaySysPrivsuser> listByUserOwid(String owid) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userRefOwid", owid);
        List<OurwaySysPrivsuser> dataList = sysPrivsuserDao.listAllByParam(params, "indexno");
        for(OurwaySysPrivsuser privsuser:dataList){
            OurwaySysRoles role = rolesDao.getOneById(privsuser.getRoleRefOwid());
            privsuser.setOurwaySysRoles(role);
        }
        return dataList;
    }
}
