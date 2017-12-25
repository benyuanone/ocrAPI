package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysFlowDao;
import com.ourway.sys.dao.SysFlowNodeDao;
import com.ourway.sys.model.OurwaySysFlownode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>接口 SysFlowDaoImpl.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysFlowNodeDao")
public class SysFlowNodeDaoImpl extends BaseServiceImpl<OurwaySysFlownode> implements SysFlowNodeDao {

    @Override
    public PageInfo<OurwaySysFlownode> listAllNodeByFlowPages(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        HqlStatement hql = new HqlStatement("from OurwaySysFlownode ", filters);
        return listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }
}
