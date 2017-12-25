package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpGuest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**<p>接口 ErpGoodsService.java : <p>
*<p>说明：产品资料接口类</p>
*<pre>
*@author CuiLiang
*@date 2017-05-08 14:41
</pre>
*/
public interface NewErpGuestService {
    

    List<ErpGuest> listPopGoodsByParams(List<FilterModel> filters) ;

    List<ErpGuest> listPopGoodsByParams(String key) ;

    String getDbMsg(String msg);

}
