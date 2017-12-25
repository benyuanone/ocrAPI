package com.ourway.erpbasedata.dao;

import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpGoodsList;

import java.io.Serializable;
import java.util.List;


/***<p>接口 ErpGoodsListDao.java : <p>
*<p>说明：TODO</p>
*<pre>
*@author CuiLiang
*@date 2017-05-09 15:41
</pre>
*/
public interface ErpGoodsListDao extends BaseService<ErpGoodsList> {
    void saveOrUpdateAll(List<ErpGoodsList> erpGoodsLists);

    List<ErpGoodsList> listAllByParams(List<FilterModel> filterModelList);

    List<ErpGoodsList> listAllByRefId(String refOwid);

    void removeByRefIds(Serializable... ids);


}
