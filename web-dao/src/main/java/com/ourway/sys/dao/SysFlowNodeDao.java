package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysFlow;
import com.ourway.sys.model.OurwaySysFlownode;

import java.util.List;

/**
 * <p>接口 SysFlowDao.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
public interface SysFlowNodeDao extends BaseService<OurwaySysFlownode> {

    PageInfo<OurwaySysFlownode> listAllNodeByFlowPages(List<FilterModel> filters, Integer pageNo, Integer pageSize);
}
