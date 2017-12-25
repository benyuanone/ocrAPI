package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysLogSettingDao;
import com.ourway.sys.model.OurwaySysLogSetting;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 /**<p>接口 SysLogSettingDaoImpl.java : <p>
 *<p>说明：日志设置</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
@Repository("logSetDao")
public class SysLogSettingDaoImpl extends BaseServiceImpl<OurwaySysLogSetting> implements SysLogSettingDao {
    @Override
    public PageInfo<Object[]> listLogSettingByHql(int pageNo, int pageSize) {
        Map params = new HashedMap();
        String hqlStr = " from OurwaySysLogSetting a, OurwaySysObject b where a.objectRefOwid=b.owid";
        HqlStatement hql = new HqlStatement(hqlStr, params);
        return  listObjectHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }
}
