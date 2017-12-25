package com.ourway.monitor.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.monitor.model.CErptankType;
import com.ourway.monitor.model.CTankGuestAndOperate;

import java.util.List;

public interface CTankGuestAndOperateDao extends BaseService<CTankGuestAndOperate> {


    List<CTankGuestAndOperate> detailTankGuestAndOperate(String key);
    PageInfo<CTankGuestAndOperate> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);
}
