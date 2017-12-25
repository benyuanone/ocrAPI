package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.dao.SysPageDao;
import com.ourway.sys.model.OurwaySysLanguage;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysPageDaoImpl.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:43
 * </pre>
 */
@Repository("sysPageDao")
public class SysPageDaoImpl extends BaseServiceImpl<OurwaySysPage> implements SysPageDao {

    @Override
    public Boolean doCheckUniquePageCA(OurwaySysPage ourwaySysPage) {
        String hql = " from OurwaySysPage where pageCa=:pageCa";
        if (!ValidateUtils.isEmpty(ourwaySysPage.getOwid()))
            hql = " from OurwaySysPage where pageCa=:pageCa and owid<>:owid";
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("pageCa", ourwaySysPage.getPageCa());
        _params.put("owid", ourwaySysPage.getOwid());
        List<OurwaySysPage> result = listAllByHql(hql, _params);
        if (null != result && result.size() > 0)
            return false;
        return true;
    }

    @Override
    public PageInfo<OurwaySysPage> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
//        HqlStatement hqlStatement = new HqlStatement(OurwaySysPage.class,filters,sortStr);
        String hql = " from OurwaySysPage  ";
        HqlStatement hqlStatement = new HqlStatement(hql, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public List<OurwaySysPage> listAllPages(List<FilterModel> filters, String sortStr) {
        String hql = " from OurwaySysPage  ";
        HqlStatement hqlStatement = new HqlStatement(hql, filters, sortStr);
        return listAllByHql(hqlStatement.getHql(),hqlStatement.getParams());
    }
}
