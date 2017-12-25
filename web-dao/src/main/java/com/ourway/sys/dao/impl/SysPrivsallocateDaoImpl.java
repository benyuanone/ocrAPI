package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.sys.dao.SysPrivsallocateDao;
import com.ourway.sys.model.OurwaySysPrivsallocate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**<p>接口 SysPrivsallocateDaoImpl.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
@Repository("sysPrivsallocateDao")
public class SysPrivsallocateDaoImpl extends BaseServiceImpl<OurwaySysPrivsallocate> implements SysPrivsallocateDao {
    @Override
    public List<OurwaySysPrivsallocate> getSelMenus(Map<String, Object> dataMap) {
        String hql=" from OurwaySysPrivsallocate where roleRefOwid=:roleRefOwid ";
        List list=listAllByHql(hql,dataMap);
        return listAllByHql(hql,dataMap);
    }

    @Override
    public boolean saveAllocatePrivs(String roleId, List selMenus) {
        String hql=" delete from OurwaySysPrivsallocate where roleRefOwid='"+roleId+"'";
        updateBulk(hql);
        List<OurwaySysPrivsallocate> list=new ArrayList<>();
        for(Object one:selMenus){
            OurwaySysPrivsallocate ourwaySysPrivsallocate=new OurwaySysPrivsallocate();
            ourwaySysPrivsallocate.setMenuRefOwid((Integer) one);
            ourwaySysPrivsallocate.setRoleRefOwid(roleId);
            save(ourwaySysPrivsallocate);
            list.add(ourwaySysPrivsallocate);
        }
        saveOrUpdate(list);
        return true;
    }


}
