package com.ourway.erpbasedata.api;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpGuest;
import com.ourway.erpbasedata.service.ErpGoodsListService;
import com.ourway.erpbasedata.service.ErpGoodsService;
import com.ourway.erpbasedata.service.NewErpGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("newErpGuestApi")
public class NewErpGuestController {

    @Autowired
    NewErpGuestService erpGoodsService;


    @RequestMapping(value = "listErpGuest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpGoods(HttpServletRequest request, PublicDataVO dataVO) {
        List<FilterModel> filterModels = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
        List<ErpGuest> erpGuests = erpGoodsService.listPopGoodsByParams(filterModels);
        if (null == erpGuests) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, erpGoodsService.getDbMsg("0004"));
        }
        return ResponseMessage.sendOK(erpGuests);
    }
    @RequestMapping(value = "listErpOrGuest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpGoodsByIdOrName(HttpServletRequest request, PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        String key = TextUtils.isEmpty(dataMap.get("key"))?"":dataMap.get("key").toString();
        List<ErpGuest> erpGuests = erpGoodsService.listPopGoodsByParams(key);
        if (null == erpGuests) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, erpGoodsService.getDbMsg("0004"));
        }
        return ResponseMessage.sendOK(erpGuests);
    }
}
