package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.erpbasedata.dao.ErpGoodsTypeDao;
import com.ourway.erpbasedata.model.ErpGoodsType;
import org.springframework.stereotype.Repository;

import java.util.List;

/***<p>方法 ErpGoodsTypeDaoImpl : <p>
*<p>说明:TODO</p>
*<pre>
*@author CuiLiang
*@date 2017-11-02 14:57
</pre>
*/
@Repository("ErpGoodsTypeDao")
public class ErpGoodsTypeDaoImpl extends BaseServiceImpl<ErpGoodsType> implements ErpGoodsTypeDao{
    @Override
    public PageInfo<ErpGoodsType> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return null;
    }

    @Override
    public ErpGoodsType detailErpGoodsType(String owid) {
        return null;
    }
}
