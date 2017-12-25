package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.sys.dao.SysPageCompDao;
import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysLayoutComponent;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysPageComponent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysPageCompDaoImpl.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
@Repository("sysPageCompDao")
public class SysPageCompDaoImpl extends BaseServiceImpl<OurwaySysPageComponent> implements SysPageCompDao {

    @Override
    public List<OurwaySysPageComponent> listComponentByControl(OurwaySysLayout layout) {
        String hql = " from OurwaySysPageComponent a,OurwaySysLayoutComponent b where a.owid=b.pageRefOwid and b.layoutRefOwid=:layoutRefOwid order by b.compStartRow,b.compOrder";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("layoutRefOwid",layout.getOwid());
        List<Object[]> objs = listObjsAllByHql(hql,map);
        List<OurwaySysPageComponent> comps = new ArrayList<OurwaySysPageComponent>(objs.size());
        for(Object[] obj:objs){
            OurwaySysPageComponent component = (OurwaySysPageComponent)obj[0];
            OurwaySysLayoutComponent layoutComponent = (OurwaySysLayoutComponent)obj[1];
            component.setLayoutComponent(layoutComponent);
            comps.add(component);
        }
        List<OurwaySysPageComponent> noComponentList =  listLayoutComponentsByRefOwid(layout.getOwid());
        for(OurwaySysPageComponent ourwaySysLayoutComponent:noComponentList){
            comps.add(ourwaySysLayoutComponent);
        }
        return comps;
    }

    private List<OurwaySysPageComponent> listLayoutComponentsByRefOwid(String owid){
        List<OurwaySysPageComponent> components = new ArrayList<OurwaySysPageComponent>();
        String hql = " from OurwaySysLayoutComponent  where pageRefOwid is null and  layoutRefOwid=:layoutRefOwid order by compStartRow,compOrder";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("layoutRefOwid",owid);
        List objs = listObjsAllByHql(hql,map);
        for(Object obj:objs){
            OurwaySysLayoutComponent layoutComponent = (OurwaySysLayoutComponent)obj;
            OurwaySysPageComponent component = new OurwaySysPageComponent();
            component.setKjAttribute(layoutComponent.getCompId());
            component.setKjTypeLabel("String");
            component.setKjType((byte)1);
            component.setLayoutComponent(layoutComponent);
            components.add(component);
        }
        return components;
    }

    @Override
    public void saveListComponents(List<OurwaySysPageComponent> components) {
        for(OurwaySysPageComponent  component:components){
           save(component);
        }
    }

    @Override
    public void removeComponents(OurwaySysPage page) {
       Map<String,Object> params = new HashMap<String,Object>(1);
       params.put("pageRefOwid",page.getOwid());
       removeByParams(params);
    }
}
