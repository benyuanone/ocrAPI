package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.sys.dao.SysQuickMenuDao;
import com.ourway.sys.model.OurwaySysQuickmenu;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysQuickMenuDaoImpl.java : <p>
 *<p>说明：快速菜单</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
@Repository("sysQuickMenuDao")
public class SysQuickMenuDaoImpl extends BaseServiceImpl<OurwaySysQuickmenu> implements SysQuickMenuDao {
    @Override
    public void removeByUserOwid(String owid) {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("userRefOwId",owid);
        List<OurwaySysQuickmenu> menuList = listAllByParam(params,"");
        if(null!=menuList&&menuList.size()>0){
            for(OurwaySysQuickmenu ourwaySysQuickmenu:menuList){
               removeEntity(ourwaySysQuickmenu);
            }
        }
    }
}
