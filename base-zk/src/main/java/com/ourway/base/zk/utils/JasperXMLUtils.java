package com.ourway.base.zk.utils;

import com.ourway.base.utils.*;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.data.PageDataUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/12.
 */
public class JasperXMLUtils {
    public static String REPORT_ROOT_PATH = ZKConstants.SYSTEM_FILE_PATH + "report" + File.separator;

    //生成对应的jrxml文件
    public static int generateDefault(String pageCA, String pageNo) {
        String reportFolder = REPORT_ROOT_PATH + pageNo;
        FileUtils.createFolder(reportFolder);
        //先删除以default
        FileUtils.delFilesBySubName(reportFolder, "default");

        //获取主表数据
        PageLayoutVO mainTable = PageDataUtil.deteailControl(pageCA, "mainTableGrid");
        if (null == mainTable)
            return -1;
        if (null == mainTable.getLayOutComponents() || mainTable.getLayOutComponents().size() <= 0) {
            return -1;
        }
        //说明有主表，在判断是否有字表，可以先创建字表
        List<PageLayoutVO> datas = PageDataUtil.deteailDataListControl(pageCA, "dataList");
        List<String> subReports = new ArrayList<String>();
        for (PageLayoutVO vo : datas) {
            subReports.add("default" + vo.getControlId());
        }
        String mainTalbeXml = PDFUtils.doCreateMainTableXML("default", subReports, mainTable.getLayOutComponents());
        FileUtils.createNewFile(reportFolder + File.separator + "default.jrxml", mainTalbeXml);
        for (PageLayoutVO subReport : datas) {
            FileUtils.createNewFile(reportFolder + File.separator + "default" + subReport.getControlId() + ".jrxml", PDFUtils.doCreateSubTableXML("default" + subReport.getControlId(), subReport));
        }
        saveOrUpdateDefault(pageCA,pageNo,subReports);
        return 0;
    }


    //保存或者修改默认的default模板
    private static void saveOrUpdateDefault(String pageCa, String pageNo, List<String> subReports) {
        //组织数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("pageCa", pageCa);
        dataMap.put("reportCode", "default");
        dataMap.put("reportDefault", 1);
        dataMap.put("jrxmlPath", pageNo + File.separator + "default.jrxml");
        List<Map<String, Object>> subreportList = new ArrayList<Map<String, Object>>();
        int index = 1;
        for (String s : subReports) {
            Map<String, Object> subData = new HashMap<String, Object>();
            subData.put("reportCode",  s);
            subData.put("jrxmlPath", pageNo + File.separator +  s + ".jrxml");
            subData.put("indexno", index);
            subData.put("updateFlag", 1);
            index++;
            subreportList.add(subData);
        }
        dataMap.put("dataList", subreportList);
        //上报
        removeByPageCaAndName(pageCa, "default");
        saveReport(dataMap);
    }

    private static void removeByPageCaAndName(String pageCa, String name) {
        PublicData data = PublicData.instantce();
        data.setMethod("/sysReportApi/removeByCaAndName");
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("reportCode", name);
        ppt.put("pageCa", pageCa);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean saveReport(Map<String, Object> dataMap) {
        PublicData data = PublicData.instantce();
        data.setMethod("/sysReportApi/save");
        data.setData(JsonUtil.toJson(dataMap));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }


}
