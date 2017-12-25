package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysMessageDao;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysMessage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysMessageDaoImpl.java : <p>
 * <p>说明：消息类</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:43
 * </pre>
 */
@Repository("sysMessageDao")
public class SysMessageDaoImpl extends BaseServiceImpl<OurwaySysMessage> implements SysMessageDao {
    @Override
    public List<OurwaySysMessage> listAllMessageByEmpId(String empId) {
        String hql = " from OurwaySysMessage where messPerId=:messPerId and state in (0,1) order by messTime desc ";
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("messPerId", empId);
        return listAllByHql(hql,params);
    }

    @Override
    public PageInfo<OurwaySysMessage> listMessagePageByEmpId(List<FilterModel> filterModelList, Integer pageNo, Integer pageSize,String sortStr) {
        HqlStatement hql = new HqlStatement(OurwaySysMessage.class, filterModelList,sortStr);
        return listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }
}
