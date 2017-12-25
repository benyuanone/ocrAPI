package com.ourway.sys.api.workflow;

import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysForm;
import com.ourway.sys.model.OurwaySysWorkflow;
import com.ourway.sys.service.FlowService;
import com.ourway.sys.service.FlowTestService;
import com.ourway.sys.service.MessageService;
import com.ourway.sys.utils.I18nUtils;
import com.ourway.sys.utils.ShiroUtilsClient;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 10033 on 2017/5/28.
 */
@Controller
@RequestMapping("sysFlowTest")
public class FlowTestController {

    @Autowired
    FlowTestService flowTestService;
    @Autowired
    FlowService flowService;
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage list(HttpServletRequest request, PublicDataVO data) {
        return ResponseMessage.sendOK(flowTestService.listHqlForPage(null, 1, 0));
    }

    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detail(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(flowTestService.listOneFormByOwid(dataMap.get("owid").toString()));
    }

    @RequestMapping(value = "save1", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage save1(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysWorkflow workflow = flowTestService.updateStartFlow(dataMap.get("owid").toString());
        //发送消息
//        if (null != workflow)
//            messageService.updateWorkFlowMsgs(workflow,"/test/flow/test1.do");
        return ResponseMessage.sendOK(dataMap.get("owid").toString());
    }

    @RequestMapping(value = "save2", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage save2(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "content");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysForm form = new OurwaySysForm();
        if (!TextUtils.isEmpty(dataMap.get("owid"))) {
            form.setOwid(dataMap.get("owid").toString());
        }
        form.setContent(dataMap.get("content").toString());
        form.setDays(Integer.parseInt(dataMap.get("days").toString()));
        OurwaySysEmploys employs = ShiroUtilsClient.getUserEntity();
        flowTestService.updateFlow(employs, form);

        return ResponseMessage.sendOK(form);
    }

    @RequestMapping(value = "removeNode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeNode(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        for (Object o : list) {
            Map<String, Object> map = (Map<String, Object>) o;

            OurwaySysForm form = flowTestService.listOneFormByOwid(map.get("owid").toString());
            flowService.removeWorkFlow(form.getOwid(), "com.ourway.sys.model.OurwaySysForm");
            flowTestService.removeForm(form);
        }
        return ResponseMessage.sendOK(list);
    }


}
