package com.ourway.erpsystem.api.erpguest;

import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.erpbasedata.model.ErpGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <dl>
 * <dt>ApiController 系统接Api controller</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 2017/4/24 0024</dd>
 * </dl>
 *
 * @author xby
 */

@Controller
@RequestMapping("oguestApi")
public class GuestController {
    /*
    @Autowired
    ErpGuestService erpGuestService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listGuest(HttpServletRequest request, HttpServletResponse response,
                                   PublicDataVO data) {
        // json 转map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ErpGoods good = new ErpGoods();
        good.setGoodsProperty("nihao");
        System.out.println(good.getGoodsPropertyStr());
        return null;
        //return ResponseMessage.sendOK(erpGuestService.listByParams(mapData));
    }


*/

}
