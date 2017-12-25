package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;

import java.util.List;
import java.util.Map;


/**
 * <p>接口 ErpTankService.java : <p>
 * <p>说明：TODO</p>
 * <pre>
 * @author zhangxinyi
 * @date 2017-05-31 11:20
 * </pre>
 */
public interface ErpTankService {

    Boolean doCheckUniqueUrl(ErpTank erpTank);

    ErpTank saveOrUpdateErpTank(ErpTank erpTank, List<ErpTankList> erpTankListList);

    List<Map<String, Object>> removeErpTankByIds(List<String> list);

    PageInfo<ErpTank> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<ErpTank> listTankList(Map<String,Object> dataMap, String sortStr);

    ErpTank detailErpTank(String owid);

    List<Object> listErpTankByLibraryArea(List<FilterModel> filters);

}
