package com.ourway.monitor.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.monitor.dao.CTankMonitorDao;
import com.ourway.monitor.dao.CTankMonitorHisDao;
import com.ourway.monitor.model.CTankMonitor;
import com.ourway.monitor.model.CTankMonitorHis;
import com.ourway.monitor.service.CTankMonitorHisService;
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
@Service("cTankMonitorHisService")
public class CTankMonitorHisServiceImpl implements CTankMonitorHisService {


    @Autowired
    CTankMonitorHisDao cTankMonitorHisDao;

    @Override
    public PageInfo<CTankMonitorHis> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return cTankMonitorHisDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public List<CTankMonitorHis> listTankMonitorHisByParam(Map<String,Object> map) {
        return cTankMonitorHisDao.listTankMonitorHisByParam(map);
    }
}
