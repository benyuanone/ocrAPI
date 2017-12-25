package com.ourway.sys.api.workflow;

import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysFlow;
import com.ourway.sys.model.OurwaySysFlownode;
import com.ourway.sys.model.OurwaySysFlownodePer;
import com.ourway.sys.service.FlowService;
import com.ourway.sys.service.FlownodePerService;
import com.ourway.sys.service.FlownodeService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sysFlowNodeApi")
public class WorkFlowNodeController {
    @Autowired
    private FlowService flowService;
    @Autowired
    private FlownodeService flownodeService;
    @Autowired
    private FlownodePerService flownodePerService;


    @RequestMapping(value = "listTreeWorkFlow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTreeWorkFlow(HttpServletRequest request, PublicDataVO data) {
        List<OurwaySysFlow> flowList = flowService.listAllWorkFlow();
        List<BaseTree> treeList = new ArrayList<BaseTree>();
        int index = 1;
        for (OurwaySysFlow map : flowList) {
            BaseTree bt = new BaseTree();
            bt.setOwid(index);
            bt.setFid(-1);
            bt.setName(I18nUtils.getLanguageContent(map.getFlowLabel(),data.getCurrLanguage()));
            bt.setId(index);
            bt.setPath(map.getOwid());
            treeList.add(bt);
            index++;
        }
        return ResponseMessage.sendOK(treeList);
    }


    @RequestMapping(value = "listFlowNodes", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listFlowNodes(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        boolean flag = false;
        for (FilterModel filter : filters) {
            if (filter.getKey().equalsIgnoreCase("flownodeRefOwid")) {
                flag = true;
                List<Object> objs = filter.getDatas();
                List<Object> newObjs = new ArrayList<Object>(1);
                newObjs.add(objs.get(0).toString());
                filter.setDatas(newObjs);
                break;
            }
        }
        //若没有type，则加一个默认的
        if (!flag) {
            FilterModel model = new FilterModel();
            model.setKey("flownodeRefOwid");
            List<Object> valueList = new ArrayList<Object>(1);
            valueList.add("-1");
            model.setDatas(valueList);
            model.setType(FilterModel.EQUALS);
            if (null == filters)
                filters = new ArrayList<FilterModel>(1);
            filters.add(model);
        }
        return ResponseMessage.sendOK(flownodeService.listHqlForPage(filters, data.getPageNo(), data.getPageSize()));
    }


    @RequestMapping(value = "listOneNodePer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listOneNodePer(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (ValidateUtils.isEmpty(dataMap.get("owid"))) {
            return ResponseMessage.sendError(-1, "owid不能为空");
        }
        OurwaySysFlownode node = flownodeService.listOneNodeById(dataMap.get("owid").toString());
        if (null == node)
            return ResponseMessage.sendOK(null);
        else
            return ResponseMessage.sendOK(flownodePerService.listAllPerByNode(node));
    }


    @RequestMapping(value = "detailOneNode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailOneNode(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (ValidateUtils.isEmpty(dataMap.get("owid"))) {
            return ResponseMessage.sendError(-1, "owid不能为空");
        }
        OurwaySysFlownode node = flownodeService.listOneNodeById(dataMap.get("owid").toString());
        return ResponseMessage.sendOK(node);
    }


    @RequestMapping(value = "saveNodePer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveNodePer(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        String refOwid = "";
        if (!TextUtils.isEmpty(dataMap.get("tree"))) {
            Map<String, Object> treeMap = (Map<String, Object>) dataMap.get("tree");
            if (!TextUtils.isEmpty(treeMap.get("path")))
                refOwid = treeMap.get("path").toString();
        }
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "nodeName", "nodeLabel", "nodePerCode", "nodeCode");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //组装Object及其字表
        List<OurwaySysFlownodePer> attributeList = new ArrayList<OurwaySysFlownodePer>();
        OurwaySysFlownode obj = JsonUtil.map2Bean(dataMap, OurwaySysFlownode.class);
        if (TextUtils.isEmpty(obj.getFlownodeRefOwid()) && TextUtils.isEmpty(refOwid)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        if (TextUtils.isEmpty(obj.getFlownodeRefOwid()))
            obj.setFlownodeRefOwid(refOwid);
        if (!TextUtils.isEmpty(dataMap.get("dataList1"))) {
            List<Map<String, Object>> perList = (List<Map<String, Object>>) dataMap.get("dataList1");
            for (Map<String, Object> map : perList) {
                OurwaySysFlownodePer per = JsonUtil.map2Bean(map, OurwaySysFlownodePer.class);
                if (!TextUtils.isEmpty(map.get("perEmpid"))&&map.get("perEmpid") instanceof Map) {
                    Map<String, Object> perMap = (Map<String, Object>) map.get("perEmpid");
                    per.setPerOwid(perMap.get("owid").toString());
                    per.setPerEmpid(TextUtils.isEmpty(perMap.get("empId")) ? "" : perMap.get("empId").toString());
                    per.setPerName(TextUtils.isEmpty(perMap.get("empName")) ? "" : perMap.get("empName").toString());
                }
                attributeList.add(per);
            }
        }
        flownodeService.saveOrUpdateNodePer(obj, attributeList);
        return ResponseMessage.sendOK(obj);
    }

    @RequestMapping(value = "removeNodePer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeNodePer(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> datas = flownodeService.removeNodePerByIds(owids);
        return ResponseMessage.sendOK(datas);
    }
}
