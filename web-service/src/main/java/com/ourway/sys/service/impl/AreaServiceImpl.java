package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.dao.SysAreaDao;
import com.ourway.sys.model.OurwaySysArea;
import com.ourway.sys.model.OurwaySysMenus;
import com.ourway.sys.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**<p>接口 AreaService.java : <p>
 *<p>说明：地区</p>
 *<pre>
 *@author cc
 *@date 14:35 2017/4/12
</pre>
 */
@Service("area")
public class AreaServiceImpl implements AreaService {
    @Autowired
    private SysAreaDao sysAreaDao;
    @Override
    public void saveOrUpdate(OurwaySysArea area) {
        sysAreaDao.saveOrUpdate(area);
    }

    @Override
    public void removeMenu(Map<String, Object> map) {
        sysAreaDao.removeMenu(map);
    }

    @Override
    public PageInfo<OurwaySysArea> listHqlForPage(List<FilterModel> filters, Integer pageNo, Integer pageSize) {

           return sysAreaDao.listHqlForPage(filters, pageNo, pageSize);

    }
}
