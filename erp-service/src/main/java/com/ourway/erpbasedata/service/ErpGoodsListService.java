package com.ourway.erpbasedata.service;

import com.ourway.base.model.FilterModel;
import com.ourway.erpbasedata.model.ErpGoodsList;

import java.util.List;

/**<p>接口 ErpGoodsListService.java : <p>
*<p>说明：TODO</p>
*<pre>
*@author CuiLiang
*@date 2017-05-09 17:09
</pre>
*/
public interface ErpGoodsListService {

    List<ErpGoodsList> listAllByParams(List<FilterModel> filterModelList);

    List<ErpGoodsList> listAllByRefId(String refId);
}
