package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;
import com.ourway.sys.model.OurwaySysApiDetail;

import java.util.List;


/**
 * <p>接口 ErpTankService.java : <p>
 * <p>说明：TODO</p>
 * <pre>
 * @author zhangxinyi
 * @date 2017-05-31 11:20
 * </pre>
 */
public interface ErpTankListService {

    void removeErpTankList(HqlStatement hqlStatement);


    List<ErpTankList> listErpTankByStatement(HqlStatement hqlStatement);

    PageInfo<ErpTankList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    void saveOrUpdateErpTankList(ErpTankList erpTankList);

    ErpTankList detailErpTankList(String owid);


    /**
    *<p>方法:listAllTankListByTankId 根据父表列出字表的所有数据 </p>
    *<ul>
     *<li> @param tankOwid 父表的主键</li>
    *<li>@return java.util.List<com.ourway.erpbasedata.model.ErpTankList>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/7/4 15:45  </li>
    *</ul>
    */
    List<ErpTankList> listAllTankListByTankId(String tankOwid);

}
