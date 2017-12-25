package com.ourway.monitor.api;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.monitor.model.CErptankType;
import com.ourway.monitor.model.CTankMonitor;
import com.ourway.monitor.model.CTankMonitorHis;
import com.ourway.monitor.service.CTankMonitorHisService;
import com.ourway.monitor.service.CTankMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/***<p>接口 CTankMonitorHisController.java : <p>
 *<p>说明：历史信息</p>
 *<pre>
 *@author zhangxinyi
 *@date 2017-10-23 17:58
</pre>
 */

@Controller
@RequestMapping("monitor")
public class CTankMonitorHisController {
    @Autowired
    CTankMonitorHisService cTankMonitorHisService;


    @RequestMapping(value = "detailTankHistoryMonitor", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailTankHistoryMonitor(HttpServletRequest request, HttpServletResponse response,
                                                    PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        List<CTankMonitorHis> cTankMonitorHis = cTankMonitorHisService.listTankMonitorHisByParam(mapData);
        return ResponseMessage.sendOK(cTankMonitorHis);

    }


}
