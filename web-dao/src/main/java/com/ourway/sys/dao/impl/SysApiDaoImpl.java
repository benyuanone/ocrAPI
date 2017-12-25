package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.dao.SysApiDao;
import com.ourway.sys.model.OurwaySysApi;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysApiDaoImpl.java : <p>
 * <p>说明：API接口</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:41
 * </pre>
 */
@Repository("sysApiDao")
public class SysApiDaoImpl extends BaseServiceImpl<OurwaySysApi> implements SysApiDao {

    @Override
    public Boolean doCheckUniqueUrl(OurwaySysApi ourwaySysApi) {
        String hql = "";
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("intUrl", ourwaySysApi.getIntUrl());
        if (!ValidateUtils.isEmpty(ourwaySysApi.getOwid())) {
            hql = "from OurwaySysApi where intUrl=:intUrl and owid<>:owid";
            _params.put("owid", ourwaySysApi.getOwid());

        } else {
            hql = "from OurwaySysApi where intUrl=:intUrl";
        }

        List<OurwaySysApi> result = listAllByHql(hql, _params);
        if (null != result && result.size() > 0) {
            return false;
        }
        return true;
    }


    @Override
    public PageInfo<OurwaySysApi> listHqlForPage(List<FilterModel> filterModelList, Integer pageNo, Integer pageSize) {
        HqlStatement hql = new HqlStatement(OurwaySysApi.class, filterModelList);
        return listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }

    @Override
    public List<BaseTree> listApiTree(List<FilterModel> models) {
        HqlStatement hql = new HqlStatement(OurwaySysApi.class, models);
        List<OurwaySysApi> objs = listAllByHql("select distinct intFunc " + hql.getHql(), hql.getParams());
        List<BaseTree> treeList = new ArrayList<BaseTree>();
        int index = 1;
        if (null != objs && objs.size() > 0) {
            for (Object o : objs) {
                BaseTree tree = new BaseTree();
                tree.setId(index);
                tree.setOwid(index);
                tree.setName(o.toString());
                tree.setPath(o.toString());
                tree.setFid(-1);
                treeList.add(tree);
                index++;
            }
        }
        return treeList;
    }
}
