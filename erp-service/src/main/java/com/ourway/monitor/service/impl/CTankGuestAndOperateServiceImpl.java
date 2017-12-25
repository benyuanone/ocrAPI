package com.ourway.monitor.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.monitor.dao.CErptankTypeDao;
import com.ourway.monitor.dao.CTankGuestAndOperateDao;
import com.ourway.monitor.model.CErptankType;
import com.ourway.monitor.model.CTankGuestAndOperate;
import com.ourway.monitor.service.CErptankTypeService;
import com.ourway.monitor.service.CTankGuestAndOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-08 9:36  </li>
*</ul>
*/
@Service("cTankGuestAndOperateService")
public class CTankGuestAndOperateServiceImpl implements CTankGuestAndOperateService {


    @Autowired
    CTankGuestAndOperateDao cTankGuestAndOperateDao;

    @Override
    public PageInfo<CTankGuestAndOperate> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return cTankGuestAndOperateDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public List<CTankGuestAndOperate> detailTankGuestAndOperate(String key) {
        return cTankGuestAndOperateDao.detailTankGuestAndOperate(key);
    }
}
