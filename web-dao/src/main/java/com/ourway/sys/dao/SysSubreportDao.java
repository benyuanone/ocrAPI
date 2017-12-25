package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurWaySysSubreport;

public interface SysSubreportDao extends BaseService<OurWaySysSubreport> {

    void removeByRefOwid(String refOwid);

}
