package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysJobDao;
import com.ourway.sys.model.OurwaySysJob;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>接口 SysApiDaoImpl.java : <p>
 * <p>说明：API接口</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:41
 * </pre>
 */
@Repository("sysJobDao")
public class SysJobDaoImpl extends BaseServiceImpl<OurwaySysJob> implements SysJobDao {
    @Override
    public List<OurwaySysJob> listStartJobs() {
        String hql = " from OurwaySysJob where jobType=0 and state=0";
        return listAllByHql(hql,null);
    }

    @Override
    public List<OurwaySysJob> listQuatzJobs() {
        String hql = " from OurwaySysJob where jobType=1 and state=0";
        return listAllByHql(hql,null);
    }

    @Override
    public List<OurwaySysJob> listJobs(String orderBy) {
        String hql = " from OurwaySysJob";
        if(!TextUtils.isEmpty(orderBy))
            hql +=" order by "+orderBy;
        return listAllByHql(hql,null);
    }
}
