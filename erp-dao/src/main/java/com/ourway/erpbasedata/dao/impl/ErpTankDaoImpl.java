package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.erpbasedata.dao.ErpTankDao;
import com.ourway.erpbasedata.model.ErpTank;

import com.ourway.sys.model.OurwaySysApi;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 ErpTankDaoImpl.java : <p>
 * <p>说明：TODO</p>
 * <pre>
 * @author zhangxinyi
 * @date 2017-05-31 11:17
 * </pre>
 */
@Repository("erpTankDao")
public class ErpTankDaoImpl extends BaseServiceImpl<ErpTank> implements ErpTankDao {

    @Override
    public Boolean doCheckUniqueUrl(ErpTank erpTank) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from ErpTank where tankId=:tankId ";
        params.put("tankId", erpTank.getTankId());
        if (!TextUtils.isEmpty(erpTank.getOwid())) {
            hql += " and owid<>:owid";
            params.put("owid", erpTank.getOwid());
        }
        ErpTank tank = getOneByHql(hql, params);
        if (null == tank)
            return true;
        else
            return false;
    }

    @Override
    public PageInfo<ErpTank> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpTank.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public List<Object> listErpTankByLibraryArea(List<FilterModel> filters) {
        String hql = "select distinct safeCapacity from ErpTank";
        HqlStatement hqlStatement = new HqlStatement(hql, filters, "createtime");
        return listObjAllByHql(hqlStatement.getHql(),hqlStatement.getParams());
    }
}
