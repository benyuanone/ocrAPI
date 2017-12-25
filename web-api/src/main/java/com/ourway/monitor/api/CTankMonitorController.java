package com.ourway.monitor.api;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.SessionUtils;
import com.ourway.monitor.model.CTankMonitor;
import com.ourway.monitor.service.CTankMonitorService;
import com.ourway.sys.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/***<p>接口 ErpShipController.java : <p>
*<p>说明：TODO</p>
*<pre>
*@author zhangxinyi
*@date 2017-09-11 9:56
</pre>
*/

@Controller
@RequestMapping("monitor")
public class CTankMonitorController {
    @Autowired
    CTankMonitorService cTankMonitorService;

    @Autowired
    private DicService dicService;


    @RequestMapping(value = "listTankMonitor", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTankMonitor(HttpServletRequest request, HttpServletResponse response,
                                           PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(cTankMonitorService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), ""));
    }


    //前台传递的Key，列表显示
    @RequestMapping(value = "listTankMonitorByParam", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTankMonitorByParam(HttpServletRequest request, HttpServletResponse response,
                                                  PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        List<CTankMonitor> cTankMonitors = cTankMonitorService.listTankMonitorByParam(mapData);
        return ResponseMessage.sendOK(cTankMonitors);
    }

    //获取指定的
    @RequestMapping(value = "getDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTankMonitorOrderByParam(HttpServletRequest request, HttpServletResponse response,
                                                       PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        Integer type = Integer.parseInt(mapData.get("type").toString());
        String code = mapData.get("code").toString();
        String language = data.getCurrLanguage();
        List<Map<String,Object>> dicLanguageValueList = dicService.listLanguageDicByType(type,code,language,"");
        return ResponseMessage.sendOK(dicLanguageValueList);
    }


}
