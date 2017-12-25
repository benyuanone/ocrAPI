package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysDepatDao;
import com.ourway.sys.dao.SysDepatempDao;
import com.ourway.sys.model.OurwaySysDepat;
import com.ourway.sys.model.OurwaySysDepatemp;
import com.ourway.sys.service.DepatempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>接口 DepatempService.java : <p>
 * <p>说明：用户部门</p>
 * <pre>
 * @author cc
 * @date 14:36 2017/4/12
 * </pre>
 */
@Service("depatempService")
public class DepatempServiceImpl implements DepatempService {

    @Autowired
    SysDepatempDao sysDepatempDao;
    @Autowired
    SysDepatDao sysDepatDao;

    @Override
    public boolean doCheckUniqueLabel(OurwaySysDepatemp depatemp) {
        return sysDepatempDao.doCheckUniqueLabelKey(depatemp);
    }

    @Override
    public void saveOrUpdateDepemp(OurwaySysDepatemp depatemp) {
        sysDepatempDao.saveOrUpdate(depatemp);
    }


    @Override
    public void removeByIds(HqlStatement hql) {
        List<OurwaySysDepatemp> depatEmpList = sysDepatempDao.listAllByHql(hql.getHql(), hql.getParams());
        for (OurwaySysDepatemp depat : depatEmpList) {
            sysDepatempDao.removeEntity(depat);
        }
    }

    @Override
    public PageInfo<OurwaySysDepatemp> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sort) {
        return sysDepatempDao.listHqlForPage(filters, pageNo, pageSize, sort);
    }

    /**
     * 获取详细表
     *
     * @param owid
     * @return
     */
    @Override
    public Object detailOneEmp(String owid) {
        Map<String, Object> param = new HashMap<String, Object>(1);
        param.put("owid", owid);
        HqlStatement hql = new HqlStatement(OurwaySysDepatemp.class, param);
        return sysDepatempDao.getOneByHql(hql.getHql(), hql.getParams());

    }

    @Override
    public List<Map<String, Object>> removeItems(List<String> owids) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            Map<String, Object> param = new HashMap<>(1);
            param.put("owid", owid);
            sysDepatempDao.removeByParams(param);
            list.add(param);
        }
        return list;
    }

    @Override
    public List<OurwaySysDepatemp> listAllDepatByUserId(String owid) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userRefOwId", owid);
        List<OurwaySysDepatemp> depatempList = sysDepatempDao.listAllByParam(params, "indexno");
        for(OurwaySysDepatemp depatemp:depatempList){
           OurwaySysDepat depat = sysDepatDao.getOneById(depatemp.getDeptRefOwid());
           depatemp.setOurwaySysDepat(depat);
        }
        return depatempList;
    }
}
