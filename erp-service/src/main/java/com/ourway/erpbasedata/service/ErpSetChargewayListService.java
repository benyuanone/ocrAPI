package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.model.ErpSetChargewayList;
import com.ourway.erpbasedata.model.ErpShipList;

import java.util.List;


/**
 * <p>接口 ErpTankService.java : <p>
 * <p>说明：TODO</p>
 * <pre>
 * @author zhangxinyi
 * @date 2017-05-31 11:20
 * </pre>
 */
public interface ErpSetChargewayListService {

    void removeErpSetChargewayList(HqlStatement hqlStatement);


    List<ErpSetChargewayList> listErpSetChargewayListByStatement(HqlStatement hqlStatement);

    PageInfo<ErpSetChargewayList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    void saveOrUpdateErpSetChargewayList(ErpSetChargewayList erpSetChargewayList);

    ErpSetChargewayList detailErpSetChargewayList(String owid);

    List<ErpSetChargewayList> listAllSetChargewayListByChargewayId(String chargewayId);

}
