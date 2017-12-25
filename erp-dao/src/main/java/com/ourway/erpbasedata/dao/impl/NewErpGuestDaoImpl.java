package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.NewErpGuestDao;
import com.ourway.erpbasedata.model.ErpGuest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("newErpGuestDao")
public class NewErpGuestDaoImpl extends BaseServiceImpl<ErpGuest> implements NewErpGuestDao {

    @Override
    public ErpGuest detailErpGuest(String owid) {
        return getOneById(owid);

    }

    @Override
    public List<ErpGuest> listPopByParams(List<FilterModel> filters) {
        HqlStatement hqlStatement = new HqlStatement(" from ErpGuest ", filters);
        return listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
    }

    @Override
    public List<ErpGuest> listPopByParams(String key) {
        String hql = "";
        Map<String, Object> params = null;
        if (TextUtils.isEmpty(key)) {
            hql = "from ErpGuest  order by guestId";
        } else {
            hql = " from ErpGuest where guestId like :guestId or name like :name ";
            params = new HashMap<String, Object>(3);
            params.put("guestId", "%" + key + "%");
            params.put("name", "%" + key + "%");
        }
        return listAllByHql(hql, params);
    }

}
