package com.ourway.monitor.api;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.monitor.service.CErptankTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/***<p>接口 CErptankTypeController.java : <p>
 *<p>说明：TODO</p>
 *<pre>
 *@author zhangxinyi
 *@date 2017-10-23 18:01
</pre>
 */

@Controller
@RequestMapping("monitor")
public class CErptankTypeController {
    @Autowired
    CErptankTypeService cErptankTypeService;


    @RequestMapping(value = "listErpTankType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpTankTypes(HttpServletRequest request, HttpServletResponse response,
                                            PublicDataVO data) {
//        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
//        String key = mapData.get("tankTypeName").toString();
//        List<CErptankType> cErptankTypes = cErptankTypeService.listErpTankType(key);
//        return ResponseMessage.sendOK(cErptankTypes);
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(cErptankTypeService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), ""));
    }
}
