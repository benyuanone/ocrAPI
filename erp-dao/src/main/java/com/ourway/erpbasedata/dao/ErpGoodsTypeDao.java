package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpGoodsType;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.erpbasedata.model.ErpTankList;

import java.util.List;

/***<p>接口 ErpGoodsTypeDao.java : <p>
*<p>说明：TODO</p>
*<pre>
*@author CuiLiang
*@date 2017-11-02 14:54
</pre>
*/
public interface ErpGoodsTypeDao extends BaseService<ErpGoodsType> {

    PageInfo<ErpGoodsType> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    ErpGoodsType detailErpGoodsType(String owid);


}
