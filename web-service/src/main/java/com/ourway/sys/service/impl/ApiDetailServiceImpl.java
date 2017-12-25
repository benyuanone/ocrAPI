package com.ourway.sys.service.impl;


import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysApiDetailDao;
import com.ourway.sys.model.OurwaySysApiDetail;
import com.ourway.sys.service.ApiDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 ApiDetailService.java : <p>
 * <p>说明：接口详细类</p>
 * <pre>
 * @author cc
 * @date 14:35 2017/4/12
 * </pre>
 */
@Service("apiDetailService")
public class ApiDetailServiceImpl implements ApiDetailService {

    @Autowired
    SysApiDetailDao sysApiDetailDao;

    @Override
    public void removeApiDetail(HqlStatement hqlStatement) {
        List<OurwaySysApiDetail> list = sysApiDetailDao.listAllByHql(hqlStatement.getHql(),
                hqlStatement.getParams());
        if ((null != list) && (list.size() > 0)) {
            for (OurwaySysApiDetail apiDetail : list) {
                sysApiDetailDao.removeEntity(apiDetail);
            }
        }
    }


    @Override
    public void saveOrUpdateApiDetail(OurwaySysApiDetail ourwaySysApiDetail) {
        sysApiDetailDao.saveOrUpdate(ourwaySysApiDetail);

    }



    @Override
    public List<OurwaySysApiDetail> listAllApiDetailByOwid(String owid,byte type) {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("apiRefOwid",owid);
        params.put("type",type);
        List<OurwaySysApiDetail> list = sysApiDetailDao.listAllByParam(params,"indexno");
        return list;
    }
}
