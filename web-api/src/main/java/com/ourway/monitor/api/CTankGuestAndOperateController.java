package com.ourway.monitor.api;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.monitor.model.CErptankType;
import com.ourway.monitor.model.CTankGuestAndOperate;
import com.ourway.monitor.service.CErptankTypeService;
import com.ourway.monitor.service.CTankGuestAndOperateService;
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
public class CTankGuestAndOperateController {
    @Autowired
    CTankGuestAndOperateService cTankGuestAndOperateService;


    @RequestMapping(value = "ListDetailTankGuestAndOperate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailTankGuestAndOperate(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(cTankGuestAndOperateService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), ""));
    }

    @RequestMapping(value = "detailTankGuestAndOperate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailTankGuestAndOperate2(HttpServletRequest request, HttpServletResponse response,
                                                     PublicDataVO data) {

        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        String key = mapData.get("tankId").toString();
        List<CTankGuestAndOperate> cTankGuestAndOperates = cTankGuestAndOperateService.detailTankGuestAndOperate(key);
        return ResponseMessage.sendOK(cTankGuestAndOperates);
    }


}
