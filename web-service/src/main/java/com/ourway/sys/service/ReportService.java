package com.ourway.sys.service;

import com.ourway.sys.model.OurWaySysReport;
import com.ourway.sys.model.OurWaySysSubreport;
import com.ourway.sys.model.OurwaySysObject;
import com.ourway.sys.model.OurwaySysObjectAttribute;

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
public interface ReportService {
    OurWaySysReport saveOrUpdateObject(OurWaySysReport object, List<OurWaySysSubreport> subreportList);

    OurWaySysReport listReportByParams(Map<String,Object> params);

    void removeByParams(Map<String,Object> params);

}
