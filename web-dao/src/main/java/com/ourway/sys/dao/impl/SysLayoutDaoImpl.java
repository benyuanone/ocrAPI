package com.ourway.sys.dao.impl;

import com.ourway.base.model.UUidEntity;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.sys.dao.SysLayoutDao;
import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysLayoutComponent;
import com.ourway.sys.model.OurwaySysPage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysLayoutDaoImpl.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
@Repository("sysLayoutDao")
public class SysLayoutDaoImpl  extends BaseServiceImpl<OurwaySysLayout> implements SysLayoutDao {

    @Override
    public void removeLayoutByPage(UUidEntity page) {  //OurwaySysPage
        Map<String,Object> map = new HashMap<String,Object>(1);
        map.put("pageRefOwid",page.getOwid());
        List<OurwaySysLayout> layouts = listAllByParam(map,"");
        for(OurwaySysLayout layout:layouts){
            map = new HashMap<String,Object>(1);
            map.put("layoutRefOwid",layout.getOwid());

            removeByParams(OurwaySysLayoutComponent.class,map);
            removeEntity(layout);
        }
    }

    @Override
    public List<OurwaySysLayout> listAllDataListByCompid(OurwaySysPage page,String compId) {
        String hql = " from OurwaySysLayout where pageRefOwid=:pageRefOwid and controlId like :controlId";
        Map<String,Object> map = new HashMap<String,Object>(2);
        map.put("pageRefOwid",page.getOwid());
        map.put("controlId",compId+"%");
        return listAllByHql(hql,map);
    }
}
