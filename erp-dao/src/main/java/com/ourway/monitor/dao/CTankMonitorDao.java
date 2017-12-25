package com.ourway.monitor.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.monitor.model.CErptankType;
import com.ourway.monitor.model.CTankMonitor;
import com.ourway.monitor.model.CTankMonitorHis;

import java.util.List;
import java.util.Map;

public interface CTankMonitorDao extends BaseService<CTankMonitor> {


    List<CTankMonitor> listTankMonitorByParam(Map<String,Object> map);
    List<CTankMonitor> listTankMonitorOrderByParam(String key);
    PageInfo<CTankMonitor> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);
}