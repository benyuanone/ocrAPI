package com.ourway.sys.api.report;

import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.model.OurWaySysReport;
import com.ourway.sys.model.OurWaySysSubreport;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.service.DicService;
import com.ourway.sys.service.ReportService;
import com.ourway.sys.service.SubreportService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 MenusController.java : <p>
 * <p>说明：系统菜单</p>
 * <pre>
 * @author cc
 * @date 2017/4/26 10:37
 * </pre>
 */
@Controller
@RequestMapping("sysReportApi")
public class ReportController {

    @Autowired
    ReportService reportService;
    @Autowired
    SubreportService subreportService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveOrUpdate(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "reportCode", "reportDefault", "jrxmlPath");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //组装Object及其字表
        List<OurWaySysSubreport> subreportList = null;
        OurWaySysReport obj = JsonUtil.map2Bean(dataMap, OurWaySysReport.class);
        if (!TextUtils.isEmpty(dataMap.get("dataList"))) {
            subreportList = JsonUtil.map2List((List<Map<String, Object>>) dataMap.get("dataList"), OurWaySysSubreport.class);
        }
        reportService.saveOrUpdateObject(obj, subreportList);
        return ResponseMessage.sendOK(obj);
    }

    @RequestMapping(value = "listReport", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listReportByCaAndName(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageCa", "reportCode");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("reportCode", dataMap.get("reportCode"));
        param.put("pageCa", dataMap.get("pageCa"));
        OurWaySysReport report = reportService.listReportByParams(param);
        //需要获取他的字表
        List<OurWaySysSubreport> subreportList = subreportService.listAllSubReportByRefOwid(report.getOwid());
        report.setSubreportList(subreportList);
        return ResponseMessage.sendOK(report);
    }


    @RequestMapping(value = "removeByCaAndName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeByCaAndName(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "pageCa", "reportCode");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("reportCode", dataMap.get("reportCode"));
        param.put("pageCa", dataMap.get("pageCa"));
        reportService.removeByParams(param);
        return ResponseMessage.sendOK("");
    }


//    @RequestMapping(value = "remove/{type}", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage remove(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
//        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
//        if (null == type)
//            return ResponseMessage.sendError(ResponseMessage.FAIL, "no Type");
//        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "id");
//        if (!msg.getSuccess()) {
//            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
//        }
//        BaseTree tree = JsonUtil.map2Bean(dataMap, BaseTree.class);
//        tree.setOwid(Integer.parseInt(dataMap.get("id").toString()));
//
//        dicService.removeTree(tree, type);
//        return ResponseMessage.sendOK("");
//    }
//
//

}
