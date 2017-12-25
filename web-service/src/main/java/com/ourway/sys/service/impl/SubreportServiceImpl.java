package com.ourway.sys.service.impl;

import com.ourway.sys.dao.SysSubreportDao;
import com.ourway.sys.model.OurWaySysSubreport;
import com.ourway.sys.service.SubreportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 ReportService.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:39
 * </pre>
 */
@Service("subreportService")
public class SubreportServiceImpl implements SubreportService {

    @Autowired
    SysSubreportDao sysSubreportDao;

    @Override
    public List<OurWaySysSubreport> listAllSubReportByRefOwid(String refOwid) {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("reportRefOwid", refOwid);
        List<OurWaySysSubreport> subreportList = sysSubreportDao.listAllByParam(map, " indexno");
        return subreportList;
    }
}
