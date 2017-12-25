package com.ourway.monitor.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.monitor.dao.CErptankTypeDao;
import com.ourway.monitor.dao.CTankMonitorDao;
import com.ourway.monitor.model.CErptankType;
import com.ourway.monitor.model.CTankMonitor;
import com.ourway.monitor.service.CErptankTypeService;
import com.ourway.monitor.service.CTankMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-08 9:36  </li>
*</ul>
*/
@Service("cTankMonitorService")
public class CTankMonitorServiceImpl implements CTankMonitorService {


    @Autowired
    CTankMonitorDao cTankMonitorDao;

    @Override
    public PageInfo<CTankMonitor> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return cTankMonitorDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public List<CTankMonitor> listTankMonitorByParam(Map<String,Object> map) {
        return cTankMonitorDao.listTankMonitorByParam(map);
    }

    @Override
    public List<CTankMonitor> listTankMonitorOrderByParam(String key) {
        return cTankMonitorDao.listTankMonitorOrderByParam(key);
    }
}
