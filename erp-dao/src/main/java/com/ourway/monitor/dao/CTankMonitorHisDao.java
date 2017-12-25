package com.ourway.monitor.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.monitor.model.CTankMonitor;
import com.ourway.monitor.model.CTankMonitorHis;

import java.util.List;
import java.util.Map;

public interface CTankMonitorHisDao extends BaseService<CTankMonitorHis> {


    List<CTankMonitorHis> listTankMonitorHisByParam(Map<String,Object> map);
    PageInfo<CTankMonitorHis> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);
}