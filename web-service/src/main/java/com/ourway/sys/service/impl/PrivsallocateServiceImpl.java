package com.ourway.sys.service.impl;

import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysMenusDao;
import com.ourway.sys.dao.SysPrivsallocateDao;
import com.ourway.sys.model.OurwaySysMenus;
import com.ourway.sys.model.OurwaySysPrivsallocate;
import com.ourway.sys.model.OurwaySysPrivsuser;
import com.ourway.sys.model.OurwaySysRoles;
import com.ourway.sys.service.PrivsallocateService;
import net.sf.json.JSONNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 PrivsallocateService.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:38
 * </pre>
 */
@Service("privsallocate")
public class PrivsallocateServiceImpl implements PrivsallocateService {
    @Autowired
    SysPrivsallocateDao sysPrivsallocateDao;
    @Autowired
    SysMenusDao sysMenusDao;

    @Override
    public List<OurwaySysPrivsallocate> getSelMenus(Map<String, Object> dataMap) {
        if (null == dataMap.get("roleRefOwid") || (dataMap.get("roleRefOwid") instanceof JSONNull)) {
            return null;
        }
        return sysPrivsallocateDao.getSelMenus(dataMap);
    }

    @Override
    public boolean saveAllocatePrivs(String roleId, List selEmps) {
        if (!TextUtils.isEmpty(roleId)) {
            return sysPrivsallocateDao.saveAllocatePrivs(roleId, selEmps);
        }
        return false;
    }

    @Override
    public List<OurwaySysPrivsallocate> listMenuAllocateByRoleOwid(String owid) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("roleRefOwid", owid);
        List<OurwaySysPrivsallocate> dataList = sysPrivsallocateDao.listAllByParam(params, "indexno");
        for (OurwaySysPrivsallocate privsuser : dataList) {
            OurwaySysMenus role = sysMenusDao.getOneById(privsuser.getMenuRefOwid());
            privsuser.setMenu(role);
        }
        return dataList;
    }
}
