package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysCssDao;
import com.ourway.sys.dao.SysFormDao;
import com.ourway.sys.model.OurwaySysCss;
import com.ourway.sys.model.OurwaySysForm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>接口 SysCssDaoImpl.java : <p>
 * <p>说明：样式</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysFormDao")
public class SysFormDaoImpl extends BaseServiceImpl<OurwaySysForm> implements SysFormDao {

    @Override
    public PageInfo<OurwaySysForm> listFowByPage(List<FilterModel> filterModels, int pageNo, int pageSize) {
        HqlStatement hql = new HqlStatement("from OurwaySysForm ", filterModels);
        return listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }
}
