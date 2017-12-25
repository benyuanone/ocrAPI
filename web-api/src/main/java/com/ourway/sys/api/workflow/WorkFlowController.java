package com.ourway.sys.api.workflow;

import com.ourway.base.CommonConstants;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.LanguageConstance;
import com.ourway.sys.model.*;
import com.ourway.sys.service.BaseProcessService;
import com.ourway.sys.service.FilesService;
import com.ourway.sys.service.FlowClassService;
import com.ourway.sys.service.FlowService;
import com.ourway.sys.utils.I18nUtils;
import com.ourway.sys.utils.ShiroUtilsClient;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 10033 on 2017/5/28.
 */
@Controller
@RequestMapping("sysWorkApi")
public class WorkFlowController {

    @Autowired
    FlowService flowService;
    @Autowired
    FlowClassService flowClassService;
    @Autowired
    FilesService filesService;

    @Autowired
    BaseProcessService baseProcessService;


    @RequestMapping(value = "listWorkFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listFlows(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(flowService.listHqlForPage(filters, 1, 20));
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveOrUpdate(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "flowId", "flowName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //组装Object及其字表
        OurwaySysFlow obj = JsonUtil.map2Bean(dataMap, OurwaySysFlow.class);
        List<OurwaySysFlowClass> attributeList = new ArrayList<OurwaySysFlowClass>();
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) dataMap.get("dataList");
            for(Map<String,Object> component:components){
                OurwaySysFlowClass flowClass = JsonUtil.map2Bean(component, OurwaySysFlowClass.class);

               if(!TextUtils.isEmpty(component.get("classRefOwid")) && component.get("classRefOwid") instanceof Map){
                    Map<String,Object> classNameMap = (Map<String,Object>)component.get("classRefOwid");
                    flowClass.setClassRefOwid(classNameMap.get("owid").toString());
               }

               attributeList.add(flowClass);
            }
//            attributeList = JsonUtil.map2List(components, OurwaySysFlowClass.class);
        }
        flowService.saveOrUpdate(obj, attributeList);

        if (!TextUtils.isEmpty(dataMap.get("fileExtId")) && !obj.getOwid().equalsIgnoreCase(dataMap.get("fileExtId").toString()))
            filesService.saveOrUpdate(obj.getOwid(), dataMap.get("fileExtId").toString());

        return ResponseMessage.sendOK(obj);
    }


    @RequestMapping(value = "detailFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailFlow(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", mapData.get("owid").toString());
        return ResponseMessage.sendOK(flowService.listByParams(params));
    }

    @RequestMapping(value = "listSubObjs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listSubObjs(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(flowClassService.listAllFlowClass(mapData.get("owid").toString()));
    }


    @RequestMapping(value = "removeWorkFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removePages(HttpServletRequest request, HttpServletResponse response,
                                       PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> datas = flowService.removeFlow(owids);
        return ResponseMessage.sendOK(datas);
    }


    @RequestMapping(value = "deployWorkFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deployWorkFlow(HttpServletRequest request, HttpServletResponse response,
                                          PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        List<OurwaySysFiles> files = filesService.listAllFilesByOwid(mapData.get("owid").toString());
        if (null == files || files.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, LanguageConstance.WORKFLOW_NOFILE);
        }
        OurwaySysFiles file = files.get(0);
        File _file = new File(CommonConstants.SYSTEM_FILE_PATH + file.getFilePath());
        if (!_file.exists()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, LanguageConstance.WORKFLOW_NOFILE);
        }
        flowService.updateDeployFlow(mapData.get("owid").toString(), CommonConstants.SYSTEM_FILE_PATH + file.getFilePath());

        return ResponseMessage.sendOK(mapData.get("owid").toString());

    }

    @RequestMapping(value = "startWorkFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage startWorkFlow(HttpServletRequest request, HttpServletResponse response,
                                         PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("owid", mapData.get("owid").toString());
        OurwaySysFlow flow = flowService.listByParams(map);
        if (flow.getFlowStatus() == 0 || flow.getFlowStatus() > 1) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, LanguageConstance.WORKFLOW_START_FAIL);
        }
//        baseProcessService.startProcess(flow.getFlowId());

        return ResponseMessage.sendOK(mapData.get("owid").toString());

    }

    @RequestMapping(value = "listAllFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllFlow(HttpServletRequest request, HttpServletResponse response,
                                       PublicDataVO data) {
        try {
//            baseProcessService.listAllTask();
//            ProcessDefinition processDefinition = baseProcessService.findProcessDefinitionByPrcDefId("5005");
//            String resourceName = processDefinition.getResourceName();
//            System.out.println(resourceName);
//            baseProcessService.findTaskDefinition("5005");
//            baseProcessService.findLatestProcessDefinitionByPrcDefKey("testFlow");
//            baseProcessService.removeDeployMent("2505");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseMessage.sendOK("");

    }


    @RequestMapping(value = "listAllTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllTask(HttpServletRequest request, HttpServletResponse response,
                                       PublicDataVO data) {
        OurwaySysEmploys employs = ShiroUtilsClient.getUserEntity();
        List<OurwaySysWorkflow> workflows = flowService.listMyWorkFlow(employs);
        return ResponseMessage.sendOK(workflows);

    }

}
