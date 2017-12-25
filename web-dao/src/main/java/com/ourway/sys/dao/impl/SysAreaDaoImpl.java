package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysAreaDao;
import com.ourway.sys.model.OurwaySysArea;
import com.ourway.sys.model.OurwaySysMenus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**<p>接口 SysAreaDaoImpl.java : <p>
 *<p>说明：地区</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:42
</pre>
 */
@Repository("areaDao")
public class SysAreaDaoImpl extends BaseServiceImpl<OurwaySysArea> implements SysAreaDao{

    @Override
    public void removeMenu(Map<String, Object> map) {
        String hql = "";
        HqlStatement hqlStatement = new HqlStatement(OurwaySysMenus.class,map);
        List<OurwaySysArea> areas = listAllByHql(hqlStatement.getHql(),hqlStatement.getParams());
        for(OurwaySysArea area:areas){
            hql = "delete from OurwaySysArea where path like '"+area.getPath()+"/%' ";
            updateBulk(hql);
            removeEntity(area);
        }
    }

    @Override
    public PageInfo<OurwaySysArea> listHqlForPage(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysArea.class,filters,"");
        return listHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
    }
}
