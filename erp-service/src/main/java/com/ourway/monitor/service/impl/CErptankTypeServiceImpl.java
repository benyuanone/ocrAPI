package com.ourway.monitor.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.monitor.dao.CErptankTypeDao;
import com.ourway.monitor.model.CErptankType;
import com.ourway.monitor.service.CErptankTypeService;
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
@Service("cErptankTypeService")
public class CErptankTypeServiceImpl implements CErptankTypeService {


    @Autowired
    CErptankTypeDao cErptankTypeDao;

    @Override
    public PageInfo<CErptankType> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return cErptankTypeDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public List<CErptankType> listErpTankType(String key) {
        return cErptankTypeDao.listErpTankType(key);
    }
}
