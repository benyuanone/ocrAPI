package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysDfilterDao;
import com.ourway.sys.model.OurwaySysDfilter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysDfilterDaoImpl.java : <p>
 * <p>说明：过滤</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysDfilterDao")
public class SysDfilterDaoImpl extends BaseServiceImpl<OurwaySysDfilter> implements SysDfilterDao {

    @Override
    public PageInfo<OurwaySysDfilter> listDfilterForPage(List<FilterModel> flist, Integer pageNo, Integer pageSize, String sortStr) {
        HqlStatement hql = new HqlStatement(OurwaySysDfilter.class, flist, sortStr);
        return listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }

    @Override
    public void removeFilterByUserOwid(String owid) {
        Map<String, Object> param = new HashMap<String, Object>(1);
        param.put("userRefOwid", owid);
        List<OurwaySysDfilter> dfilterList = listAllByParam(param, "");
        if (null != dfilterList && dfilterList.size() > 0)
            for (OurwaySysDfilter ourwaySysDfilter : dfilterList) {
                removeEntity(ourwaySysDfilter);
            }
    }

    @Override
    public boolean doCheckSameUserApi(OurwaySysDfilter ourwaySysDfilter) {
        String hql = " from OurwaySysDfilter where userRefOwid=:userRefOwid and pageCa=:pageCa ";
        if (!TextUtils.isEmpty(ourwaySysDfilter.getOwid()))
            hql += " and owid<>'" + ourwaySysDfilter.getOwid() + "'";
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userRefOwid", ourwaySysDfilter.getUserRefOwid());
        params.put("pageCa", ourwaySysDfilter.getPageCa());
        List<OurwaySysDfilter> datas = listAllByHql(hql, params);
        if (null != datas && datas.size() > 0)
            return false;
        else
            return true;
    }
}
