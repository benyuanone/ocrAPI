package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysFlow;

import java.util.List;

/**
 * <p>接口 SysFlowDao.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
public interface SysFlowDao extends BaseService<OurwaySysFlow> {
    public void removeComponents(OurwaySysFlow flow);

    PageInfo<OurwaySysFlow> listFowByPage(List<FilterModel> filterModels, int pageNo, int pageSize);

    OurwaySysFlow listSysFlowByClass(String className);
}
