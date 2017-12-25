package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysApiDao;
import com.ourway.sys.dao.SysApiDetailDao;
import com.ourway.sys.dao.SysDfilterDao;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;
import com.ourway.sys.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 ApiService.java : <p>
 * <p>说明：接口类</p>
 * <pre>
 * @author cc
 * @date 14:35 2017/4/12
 * </pre>
 */
@Service("api")
public class ApiServiceImpl implements ApiService {

    @Autowired
    SysApiDao sysApiDao;

    @Autowired
    SysApiDetailDao sysApiDetailDao;

    @Autowired
    SysDfilterDao sysDfilterDao;

    @Override
    public Boolean doCheckUniqueUrl(OurwaySysApi ourwaySysApi) {
        return sysApiDao.doCheckUniqueUrl(ourwaySysApi);
    }

    @Override
    public OurwaySysApi saveOrUpdateApi(OurwaySysApi ourwaySysApi, List<OurwaySysApiDetail> inList, List<OurwaySysApiDetail> outList) {
        //保存主表信息
        sysApiDao.saveOrUpdate(ourwaySysApi);

        if (null != inList && inList.size() > 0) {
            for (OurwaySysApiDetail detail : inList) {
                detail.setType((byte) 0);
                detail.setApiRefOwid(ourwaySysApi.getOwid());
                switch (detail.getUpdateFlag()) {
                    case 0:
                        break;
                    case 1:
                        if (TextUtils.isEmpty(detail.getOwid())) {
                            sysApiDetailDao.save(detail);
                        } else {
                            sysApiDetailDao.update(detail);
                        }
                        break;
                    case 2:
                        sysApiDetailDao.removeByIds(detail.getOwid());
                        break;
                }
            }
        }
        if (null != outList && outList.size() > 0) {
            for (OurwaySysApiDetail detail : outList) {
                detail.setType((byte) 1);
                detail.setApiRefOwid(ourwaySysApi.getOwid());
                switch (detail.getUpdateFlag()) {
                    case 0:
                        break;
                    case 1:
                        if (TextUtils.isEmpty(detail.getOwid())) {
                            sysApiDetailDao.save(detail);
                        } else {
                            sysApiDetailDao.update(detail);
                        }
                        break;
                    case 2:
                        sysApiDetailDao.removeByIds(detail.getOwid());
                        break;
                }
            }
        }
        return ourwaySysApi;
    }

    @Override
    public List<Map<String, Object>> removeApiByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            OurwaySysApi oapi = sysApiDao.getOneByParams(params, null);
            if (null != oapi) {
                //先删子表信息
                sysApiDetailDao.removeApiDetail(owid);
                sysApiDao.removeEntity(oapi);
                objs.add(params);
            }
        }
        return objs;
    }

    @Override
    public List<OurwaySysApi> listApiByStatement(HqlStatement hqlStatement) {
       /* //先根据 条件 查询ourwaySysApi
        List<OurwaySysApi> apiList = sysApiDao.listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
        Map<String, Object> _params = new HashMap<String, Object>();
        String hql1 = "from OurwaySysApiDetail where apiRefOwid=:apiRefOwid";
        String hql2 = "from OurwaySysDfilter where apiRefOwid=:apiRefOwid";


        //非空 根据 apiRefOwid查询从表ourwaySysApiDetail,ourwaySysDfilter
        if ((null != apiList) && (apiList.size() > 0)) {
            for (OurwaySysApi ourwaySysApi : apiList) {
                _params.put("apiRefOwid", ourwaySysApi.getOwid());
                ourwaySysApi.setDataList(sysApiDetailDao.listAllByHql(hql1, _params));
                ourwaySysApi.setOurwaySysDfilterList(sysDfilterDao.listAllByHql(hql2, _params));
            }
        }
        return apiList;*/
        return null;
    }


    /*@Override
    public PageInfo<OurwaySysApi> listApiForPage(HqlStatement hqlStatement, int pageNo, int pageSize) {

        return sysApiDao.listHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),
                                     hqlStatement.getParams(),pageNo,pageSize);
    }*/

    @Override
    public PageInfo<OurwaySysApi> listApiForPage(List<FilterModel> filterModelList, Integer pageNo, Integer pageSize) {
        return sysApiDao.listHqlForPage(filterModelList, pageNo, pageSize);
    }

    @Override
    public OurwaySysApi detailApi(String owid) {
        return sysApiDao.getOneById(owid);
    }


    @Override
    public List<BaseTree> listApiTree(List<FilterModel> models) {
        return sysApiDao.listApiTree(models);
    }
}

