package com.ourway.sys.service;

import com.ourway.sys.model.OurWaySysSubreport;

import java.util.List;

/**<p>接口 ReportService.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:39
</pre>
 */
public interface SubreportService {

    List<OurWaySysSubreport> listAllSubReportByRefOwid(String refOwid);
}
