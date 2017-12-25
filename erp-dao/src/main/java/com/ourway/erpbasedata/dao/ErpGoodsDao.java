package com.ourway.erpbasedata.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;

import java.util.List;

/**
 * Created by CuiL on 2017-05-08.
 */
public interface ErpGoodsDao extends BaseService<ErpGoods> {
    boolean doCheckUniqueByGoodsId(ErpGoods erpGoods);

    List<ErpGoods> listAllByParams(List<FilterModel> filterModels);

    List<ErpGoods> listAllByParams(String key);

    List<ErpGoods> listPopByParams(List<FilterModel> filterModels);

    List<ErpGoods> listErpGoodsByFuzzyQuery(String key);


    boolean doCheckRemove(List<FilterModel> filterModels);

    PageInfo<ErpGoods> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<ErpGoods> listAllByErpTankList(ErpTankList oapi);
    ErpGoods detailErpGoods(String owid);

    //产品表关联船舶表
    List<ErpGoods> listAllByErpShipList(ErpShipList oapi);



}
