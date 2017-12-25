package com.ourway.sys.service.impl;

import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysReportDao;
import com.ourway.sys.dao.SysSubreportDao;
import com.ourway.sys.model.OurWaySysReport;
import com.ourway.sys.model.OurWaySysSubreport;
import com.ourway.sys.model.OurwaySysObjectAttribute;
import com.ourway.sys.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    SysReportDao sysReportDao;

    @Autowired
    SysSubreportDao sysSubreportDao;

    @Override
    public OurWaySysReport saveOrUpdateObject(OurWaySysReport object, List<OurWaySysSubreport> subreportList) {
        sysReportDao.saveOrUpdate(object);
        if (null != subreportList && subreportList.size() > 0) {
            for (OurWaySysSubreport subreport : subreportList) {
                switch (subreport.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        //什么也不做
                        break;
                    //修改
                    case 1:
                        subreport.setReportRefOwid(object.getOwid());
                        // 判断 owid
                        if (TextUtils.isEmpty(subreport.getOwid())) {
                            //为空 新增
                            sysSubreportDao.save(subreport);
                        } else {
                            sysSubreportDao.update(subreport);
                        }
                        break;
                    //删除
                    case 2:
                        sysSubreportDao.removeByIds(subreport.getOwid());
                        break;
                }
            }
        }
        return object;
    }

    @Override
    public OurWaySysReport listReportByParams(Map<String, Object> params) {
        List<OurWaySysReport> reportList = sysReportDao.listAllByParam(params, "");
        if (null != reportList && reportList.size() > 0)
            return reportList.get(0);
        return null;
    }

    @Override
    public void removeByParams(Map<String, Object> params) {
        List<OurWaySysReport> reportList = sysReportDao.listAllByParam(params, "");
        for (OurWaySysReport report : reportList) {
            sysSubreportDao.removeByRefOwid(report.getOwid());
            sysReportDao.removeEntity(report);
        }
    }
}
