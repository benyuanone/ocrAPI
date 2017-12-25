package com.ourway.erpbasedata.service;

import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.model.ErpGoodsList;
import com.ourway.erpbasedata.model.ErpGoodsType;

import java.util.List;

/***<p>接口 ErpGoodsTypeService.java : <p>
*<p>说明：TODO</p>
*<pre>
*@author CuiLiang
*@date 2017-11-02 15:59
</pre>
*/
public interface ErpGoodsTypeService {

    List<ErpGoodsType> listAllByParams(List<FilterModel> filterModelList);

    List<ErpGoodsType> listAllByRefId(String refId);
}
