package com.ourway.monitor.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.monitor.dao.CErptankTypeDao;
import com.ourway.monitor.dao.CTankGuestAndOperateDao;
import com.ourway.monitor.model.CErptankType;
import com.ourway.monitor.model.CTankGuestAndOperate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysApiDetailDaoImpl.java : <p>
 *<p>说明：接口详情</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:41
</pre>
 */
@Repository("cTankGuestAndOperateDao")
public class CTankGuestAndOperateDaoImpl extends BaseServiceImpl<CTankGuestAndOperate> implements CTankGuestAndOperateDao{


    @Override
    public PageInfo<CTankGuestAndOperate> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(CTankGuestAndOperate.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public List<CTankGuestAndOperate> detailTankGuestAndOperate(String key) {
        String hql = "";
        Map<String, Object> params = null;
        hql = "from CTankGuestAndOperate where tankId = :tankId";
        params = new HashMap<String, Object>(1);
        params.put("tankId", key);
        return listAllByHql(hql, params);
    }
}
