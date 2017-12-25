package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysForm;
import com.ourway.sys.model.OurwaySysWorkflow;

import java.util.List;

/**
 * <p>接口 PageService.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
public interface FlowTestService {

    PageInfo<OurwaySysForm> listHqlForPage(List<FilterModel> filterModels, int pageNo, int pageSize);

    OurwaySysForm listOneFormByOwid(String owid);

    OurwaySysWorkflow updateStartFlow(String owid);

    void updateFlow(OurwaySysEmploys employs, OurwaySysForm form);

    void removeForm(OurwaySysForm form);
}
