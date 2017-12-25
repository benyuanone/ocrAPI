package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.sys.dao.SysReportDao;
import com.ourway.sys.dao.SysSubreportDao;
import com.ourway.sys.model.OurWaySysReport;
import com.ourway.sys.model.OurWaySysSubreport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>接口 SysReportDaoImpl.java : <p>
 * <p>说明：报告</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:43
 * </pre>
 */
@Repository("sysSubreportDao")
public class SysSubreportDaoImpl extends BaseServiceImpl<OurWaySysSubreport> implements SysSubreportDao {

    @Override
    public void removeByRefOwid(String refOwid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reportRefOwid", refOwid);
        removeByParams(params);
    }
}
