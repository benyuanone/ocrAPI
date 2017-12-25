package com.ourway.sys.api.LogController;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.OurwaySysLog;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysObjectAttribute;
import com.ourway.sys.service.LogService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jackson on 17-4-28.
 */
@Controller
@RequestMapping("sysLogApi")
public class LogController {

    @Autowired
    private LogService logSer;


    @RequestMapping(value = "saveOrUpdateLog", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveOrUpdateLog(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "settingRefOwid", "logBuzFlag", "logClassName", "logType", "logOldValue", "logNewValue", "logOperateId", "logOperateName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysLog object = JsonUtil.map2Bean(dataMap, OurwaySysLog.class);
        logSer.saveOrUpdateLog(object);
        return ResponseMessage.sendOK(object);
    }

    @RequestMapping(value = "removeLog", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeLog(HttpServletRequest request, PublicDataVO data) {
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        return ResponseMessage.sendOK(logSer.removeObjects(owids));
    }

    @RequestMapping(value = "removeAll", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeAll(HttpServletRequest request, PublicDataVO data) {
        logSer.removeAll();
        return ResponseMessage.sendOK("ok");
    }

    @RequestMapping(value = "listLog", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLog(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(logSer.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), " logOperateTime desc"));
    }

    @RequestMapping(value = "detailLog", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailLog(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "className", "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //根据传入的className获取日志是否记录
        List<OurwaySysObjectAttribute> attributeList = logSer.listLogAttrByClassName(dataMap.get("className").toString());
        if (null == attributeList || attributeList.size() <= 0)
            return ResponseMessage.sendError(-1, I18nUtils.getLanguageContent(Constants.NO_MESS, data.getCurrLanguage()));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("mainTable", attributeList);
        Map<String, List<OurwaySysObjectAttribute>> subMap = logSer.listRefLogAttributeByOwid(dataMap.get("owid").toString());
        result.put("subTable", subMap);
        //获取主表数据
        List<OurwaySysLog> mainLogs = logSer.listLogsByOwid(dataMap.get("owid").toString());
        //Map<String, List<OurwaySysLog>> refLogs = logSer.listRefLogsByOwid(dataMap.get("owid").toString());
        result.put("mainData", mainLogs);
       // result.put("refData", refLogs);


        return ResponseMessage.sendOK(result);
    }


}
