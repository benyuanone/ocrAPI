package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.sys.dao.SysPrivsuserDao;
import com.ourway.sys.model.OurwaySysPrivsuser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysPrivsuserDaoImpl.java : <p>
 * <p>说明：权限分配</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:43
 * </pre>
 */
@Repository("sysPrivsuserDao")
public class SysPrivsuserDaoImpl extends BaseServiceImpl<OurwaySysPrivsuser> implements SysPrivsuserDao {
    @Override
    public boolean saveUserPrivs(String roleId, List<String> selEmps) {
        String hql = " delete from OurwaySysPrivsuser where roleRefOwId='" + roleId + "'";
        updateBulk(hql);
        List<OurwaySysPrivsuser> list = new ArrayList<>();
        for (String one : selEmps) {
            OurwaySysPrivsuser ourwaySysPrivsuser = new OurwaySysPrivsuser();
            ourwaySysPrivsuser.setUserRefOwid(one);
            ourwaySysPrivsuser.setRoleRefOwid(roleId);
            list.add(ourwaySysPrivsuser);
        }
        saveOrUpdate(list);
        return true;
    }

    @Override
    public void removeByUserOwid(String owid) {
        Map<String, Object> param = new HashMap<String, Object>(1);
        param.put("userRefOwId", owid);
        List<OurwaySysPrivsuser> ourwaySysPrivsuserList = listAllByParam(param, "");
        if (null != ourwaySysPrivsuserList && ourwaySysPrivsuserList.size() > 0) {
            for (OurwaySysPrivsuser ourwaySysPrivsuser : ourwaySysPrivsuserList) {
                removeEntity(ourwaySysPrivsuser);
            }
        }
    }
}
