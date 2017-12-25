package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysLogSetting;

/**
 * Created by jackson on 17-4-28.
 */
public interface SysLogSettingDao extends BaseService<OurwaySysLogSetting> {

    /**
    *<p>功能描述：listLogSettingByHql  连表查询业务日志和业务类</p>
    *<ul>
    *<li>@param []</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysLogSetting></li>
    *<li>@throws </li>
    *<li>@author jackson</li>
    *<li>@date 17-5-30 上午9:50</li>
    *</ul>
    */
    PageInfo<Object[]> listLogSettingByHql(int pageNo, int pageSize);
}
