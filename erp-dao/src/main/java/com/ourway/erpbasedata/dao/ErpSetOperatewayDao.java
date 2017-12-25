package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpSetOperateway;

import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-09-22.
 */

public interface ErpSetOperatewayDao extends BaseService<ErpSetOperateway>{

    PageInfo<ErpSetOperateway> listErpSetOperatewayForPage(List<FilterModel> filterModels,int pageNo,int pageSize,String sortStr);

    ErpSetOperateway detailErpSetOperateway(Map<String,Object> dataMap);

    String checkUnique(Map dataMap,PublicDataVO data);
}
