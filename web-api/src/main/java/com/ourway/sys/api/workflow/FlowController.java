package com.ourway.sys.api.workflow;

import com.ourway.base.CommonConstants;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysWorkflow;
import com.ourway.sys.service.BaseProcessService;
import com.ourway.sys.service.FlowService;
import com.ourway.sys.service.MessageService;
import com.ourway.sys.utils.ShiroUtilsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10033 on 2017/5/28.
 */
@Controller
@RequestMapping("sysFlowControlCenter")
public class FlowController {
    @Autowired
    FlowService flowService;
    @Autowired
    BaseProcessService baseProcessService;
    @Autowired
    MessageService messageService;


    //传入owid和className，判断是否可以打开审批界面
    @RequestMapping(value = "checkAuditPrivs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage checkAuditPrivs(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid", "flowClassName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        boolean flag = flowService.doCheckAuditPrivs(dataMap.get("owid").toString(), dataMap.get("flowClassName").toString());
        return ResponseMessage.sendOK(flag);
    }

    //传入owid和className，判断是否可以打开审批界面
    @RequestMapping(value = "listFlowHistory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listFlowHistory(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "flowBusinessOwid", "flowClassName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(flowService.listFlowHistory(dataMap.get("flowBusinessOwid").toString(), dataMap.get("flowClassName").toString()));
    }

    //传入owid和className，判断是否可以打开审批界面
    @RequestMapping(value = "detailCurrNode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailCurrNode(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "flowBusinessOwid", "flowClassName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysEmploys employs = ShiroUtilsClient.getUserEntity();
        OurwaySysWorkflow flow = new OurwaySysWorkflow();
        boolean flag = flowService.doCheckAuditPrivs(dataMap.get("flowBusinessOwid").toString(), dataMap.get("flowClassName").toString());
        if (flag) {
            flow = flowService.listCurrNode(dataMap.get("flowBusinessOwid").toString(), dataMap.get("flowClassName").toString());
            //获取当前节点
            if (flow.getTaskCatagory().equalsIgnoreCase("99")) {
                flow.setAudit(0);
            } else {
                baseProcessService.updateProcessWorkFlow(flow, employs, CommonConstants.SYSTEM_FILE_PATH);
                flow.setAudit(1);
            }
        } else {
            flow = flowService.listCurrNode(dataMap.get("flowBusinessOwid").toString(), dataMap.get("flowClassName").toString());
            flow.setAudit(0);
        }
        String filePath = baseProcessService.displayFlowDiagram(flow, CommonConstants.SYSTEM_FILE_PATH);
        flow.setFilePath(filePath);
        //一定要进行转换，作为参数流转到下一层
        Map<String, Object> result = JsonUtil.jsonToMap(JsonUtil.toJson(flow));
        result.put("flowBusinessOwid", dataMap.get("flowBusinessOwid").toString());
        result.put("flowClassName", dataMap.get("flowClassName").toString());
        result.remove("lasupdate");
        result.remove("createtime");
        result.remove("taskTime");
        result.remove("vertime");

        //获取流程照片
        return ResponseMessage.sendOK(result);
    }


    //审核操作
    @RequestMapping(value = "auditPass", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage auditPassNode(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "taskId", "flowBusinessOwid", "flowClassName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        String flowBusinessOwid = dataMap.get("flowBusinessOwid").toString();
        String flowClassName = dataMap.get("flowClassName").toString();
        String taskId = dataMap.get("taskId").toString();
        String comment = "";
        if (!TextUtils.isEmpty(dataMap.get("memo")))
            comment = dataMap.get("memo").toString();
        OurwaySysEmploys employs = ShiroUtilsClient.getUserEntity();
        OurwaySysWorkflow flow = flowService.listCurrNode(flowBusinessOwid, flowClassName, taskId);
        if (null == flow) {
            return ResponseMessage.sendError(-1, "审批失败");
        }

        //进行审批
        OurwaySysWorkflow nextFlow = baseProcessService.updatePassTask(flow, comment);
        if (null == nextFlow) {
            return ResponseMessage.sendError(-1, "审批失败");
        }
        //一定要进行转换，作为参数流转到下一层
        Map<String, Object> result = JsonUtil.jsonToMap(JsonUtil.toJson(nextFlow));
        result.put("flowBusinessOwid", dataMap.get("flowBusinessOwid").toString());
        result.put("flowClassName", dataMap.get("flowClassName").toString());
        result.remove("lasupdate");
        result.remove("createtime");
        result.remove("taskTime");
        result.remove("vertime");
        //发送消息
//        messageService.updateWorkFlowMsgs(nextFlow);
        return ResponseMessage.sendOK(result);
    }

    //审核操作
    @RequestMapping(value = "auditReject", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage auditRejectNode(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "taskId", "flowBusinessOwid", "flowClassName", "memo");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        String flowBusinessOwid = dataMap.get("flowBusinessOwid").toString();
        String flowClassName = dataMap.get("flowClassName").toString();
        String taskId = dataMap.get("taskId").toString();
        String comment = "";
        if (!TextUtils.isEmpty(dataMap.get("memo")))
            comment = dataMap.get("memo").toString();
        OurwaySysEmploys employs = ShiroUtilsClient.getUserEntity();
        OurwaySysWorkflow flow = flowService.listCurrNode(flowBusinessOwid, flowClassName, taskId);
        if (null == flow) {
            return ResponseMessage.sendError(-1, "审批失败");
        }

        //进行审批
        OurwaySysWorkflow nextFlow = baseProcessService.updateRejectTask(flow, comment);
        if (null == nextFlow) {
            return ResponseMessage.sendError(-1, "审批失败");
        }
        //一定要进行转换，作为参数流转到下一层
        Map<String, Object> result = JsonUtil.jsonToMap(JsonUtil.toJson(nextFlow));
        result.put("flowBusinessOwid", dataMap.get("flowBusinessOwid").toString());
        result.put("flowClassName", dataMap.get("flowClassName").toString());
        result.remove("lasupdate");
        result.remove("createtime");
        result.remove("taskTime");
        result.remove("vertime");
        //发送消息
//        messageService.updateWorkFlowMsgs(nextFlow);
        return ResponseMessage.sendOK(result);
    }
}
