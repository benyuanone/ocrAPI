package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.erpbasedata.model.ErpGoods;

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
public interface ErpGoodsService{
    
    boolean doCheckUniqueByGoodsId(ErpGoods erpGoods);

    void saveOrUpdate(ErpGoods erpGoods);

    ErpGoods getOneById(String id);

    void removeByIds(Serializable... ids);

    List<Map<String, Object>> removeErpGoodsByIds(List<String> list);

    void removeByFilters(List<FilterModel> filterModels);

    boolean doCheckRemove(List<FilterModel> filterModels);

    //PageInfo<ErpGoods> listAllGoodsByParams(List<FilterModel> filters) ;

    List<ErpGoods> listPopGoodsByParams(List<FilterModel> filters) ;

    List<ErpGoods> listPopGoodsByParams(String key) ;

    String getDbMsg(String msg,PublicDataVO dataVO);

    ValidateMsg Validate(PublicDataVO dataVO,Map<String, Object> map, String... keys);

    PageInfo<ErpGoods> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    ErpGoods detailErpGoods(String owid);

    List<ErpGoods> listErpGoodsByFuzzyQuery(String key);
}
