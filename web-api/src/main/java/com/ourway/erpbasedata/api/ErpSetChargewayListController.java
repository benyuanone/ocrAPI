package com.ourway.erpbasedata.api;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.erpbasedata.service.ErpGoodsService;
import com.ourway.erpbasedata.service.ErpSetChargewayListService;
import com.ourway.erpbasedata.service.ErpTankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**<p>接口 TankController.java : <p>
*<p>说明：TODO</p>
*<pre>
*@author zhangxinyi
*@date 2017-05-31 11:34
</pre>
*/

@Controller
@RequestMapping("erpSetChargewayListApi")
public class ErpSetChargewayListController {
    @Autowired
    ErpSetChargewayListService erpSetChargewayListService;




    @RequestMapping(value = "listPages", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listPages(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(erpSetChargewayListService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(),""));
    }


    @RequestMapping(value = "detailErpSetChargewayList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailErpSetChargewayList(HttpServletRequest request, HttpServletResponse response,
                                         PublicDataVO dataVO) {

        Map<String,Object> mapData = JsonUtil.jsonToMap(dataVO.getData());

        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData,"owid");

        if(!validateMsg.getSuccess()){
            return ResponseMessage.sendError(ResponseMessage.FAIL,validateMsg.toString());
        }

        return ResponseMessage.sendOK(erpSetChargewayListService.detailErpSetChargewayList(mapData.get("owid").toString()));

    }


}
