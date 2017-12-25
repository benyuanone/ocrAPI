package com.ourway.monitor.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.monitor.model.CTankGuestAndOperate;
import com.ourway.monitor.model.CTankMonitor;
import com.ourway.monitor.model.CTankMonitorHis;

import java.util.List;
import java.util.Map;


/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-08 9:34  </li>
*</ul>
*/
public interface CTankMonitorHisService {

    PageInfo<CTankMonitorHis> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);
    List<CTankMonitorHis> listTankMonitorHisByParam(Map<String,Object> map);
}
