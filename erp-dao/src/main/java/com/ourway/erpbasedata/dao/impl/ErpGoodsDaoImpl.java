package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpGoodsDao;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;
import javafx.beans.binding.ObjectExpression;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 ErpGoodsDaoImpl.java : <p>
 * <p>说明：TODO</p>
 * <pre>
 * @author CuiLiang
 * @date 2017-05-08 15:12
 * </pre>
 */
@Repository("erpGoodsDao")
public class ErpGoodsDaoImpl extends BaseServiceImpl<ErpGoods> implements ErpGoodsDao {
    @Override
    public boolean doCheckUniqueByGoodsId(ErpGoods erpGoods) {
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("goodsId", erpGoods.getGoodsId());
        String hql = " from ErpGoods where goodsId=:goodsId";
        if (!TextUtils.isEmpty(erpGoods.getOwid())) {
            hql = hql + " and owid!=:owid";
            _params.put("owid", erpGoods.getOwid());
        }
        ErpGoods goods = getOneByHql(hql, _params);
        if (null == goods)
            return true;
        return false;
    }

    @Override
    public List<ErpGoods> listAllByParams(List<FilterModel> filterModels) {
        HqlStatement hqlStatement = new HqlStatement("from ErpGoods ", filterModels);
        return listAllByParams(hqlStatement.getHql(), hqlStatement.getParams());
    }

    @Override
    public List<ErpGoods> listPopByParams(List<FilterModel> filterModels) {
        HqlStatement hqlStatement = new HqlStatement(" from ErpGoods ", filterModels);
        return listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
    }

    @Override
    public boolean doCheckRemove(List<FilterModel> filterModels) {
        HqlStatement hqlStatement = new HqlStatement("select 1 from view_doChekcRemove", filterModels);
        if (null != listAllByParams(hqlStatement.getHql(), hqlStatement.getParams())) {
            return true;
        }
        ;
        return false;

    }

    @Override
    public PageInfo<ErpGoods> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpGoods.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override

    public List<ErpGoods> listAllByErpTankList(ErpTankList oapi) {

        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("owid", oapi.getErpgoodsRefOwid());

        HqlStatement hql = new HqlStatement(ErpGoods.class, _params);

        return listAllByHql(hql.getHql(), hql.getParams());
    }

    @Override
    public List<ErpGoods> listAllByErpShipList(ErpShipList oapi) {

        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("owid", oapi.getErpgoodsRefOwid());

        HqlStatement hql = new HqlStatement(ErpGoods.class, _params);

        return listAllByHql(hql.getHql(), hql.getParams());
    }

    public ErpGoods detailErpGoods(String owid) {
        //Map<String, Object> params = new HashMap<String, Object>(1);
        //params.put("owid", owid);
        //return getOneByParams(ErpGoods.class,params,"");
        return getOneById(owid);

    }

    @Override
    public List<ErpGoods> listAllByParams(String key) {
        String hql = "";
        Map<String, Object> params = null;
        if (TextUtils.isEmpty(key)) {
            hql = "from ErpGoods  order by goodsId";
        } else {
            hql = " from ErpGoods where goodsId like :goodsId or name like :name or engName like :engName ";
            params = new HashMap<String, Object>(3);
            params.put("goodsId", "%" + key + "%");
            params.put("name", "%" + key + "%");
            params.put("engName", "%" + key + "%");
        }
        return listAllByHql(hql, params);
    }

    //储罐资料细表，输入bandbox然后进行模糊查询
    @Override
    public List<ErpGoods> listErpGoodsByFuzzyQuery(String key) {
        String hql = "";
        Map<String, Object> params = null;
        if (TextUtils.isEmpty(key)) {
            hql = "from ErpGoods  order by goodsId";
        } else {
            hql = "from ErpGoods where goodsId like :goodsId  or name like :name";
            params = new HashMap<String, Object>(2);
            params.put("goodsId", "%" + key + "%");
            params.put("name", "%" + key + "%");
        }
        return listAllByHql(hql, params);
    }
}
