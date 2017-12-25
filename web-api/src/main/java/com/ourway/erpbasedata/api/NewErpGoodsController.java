package com.ourway.erpbasedata.api;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpGoodsList;
import com.ourway.erpbasedata.service.ErpGoodsListService;
import com.ourway.erpbasedata.service.ErpGoodsService;
import com.ourway.sys.Constants;
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

/***<p>方法 erpGoodsController : <p>
 *<p>说明:TODO</p>
 *<pre>
 *@author CuiLiang
 *@date 2017-05-08 15:14
</pre>
 */
@Controller
@RequestMapping("newErpGoodsApi")
public class NewErpGoodsController {

    @Autowired
    ErpGoodsService erpGoodsService;

    @Autowired
    ErpGoodsListService erpGoodsListService;


    @RequestMapping(value = "listErpGoods", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpGoods(HttpServletRequest request, PublicDataVO dataVO) {
        List<FilterModel> filterModels = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
        List<ErpGoods> erpGoodss = erpGoodsService.listPopGoodsByParams(filterModels);
        if (null == erpGoodss) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, erpGoodsService.getDbMsg("0004",dataVO));
        }
        return ResponseMessage.sendOK(erpGoodss);
    }

    @RequestMapping(value = "listErpOrGoods", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpGoodsByIdOrName(HttpServletRequest request, PublicDataVO dataVO) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        String key = TextUtils.isEmpty(dataMap.get("key"))?"":dataMap.get("key").toString();
        List<ErpGoods> erpGoodss = erpGoodsService.listPopGoodsByParams(key);
        if (null == erpGoodss) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, erpGoodsService.getDbMsg("0004",dataVO));
        }
        return ResponseMessage.sendOK(erpGoodss);
    }

    /**
    *<p>方法:listErpGoodsByFuzzyQuery 模糊查询产品资料 </p>
    *<ul>
     *<li> @param request TODO</li>
     *<li> @param dataVO TODO</li>
    *<li>@return com.ourway.base.model.ResponseMessage  </li>
    *<li>@author zhangxinyi </li>
    *<li>@date 2017-09-01 11:11  </li>
    *</ul>
    */
    @RequestMapping(value = "listErpGoodsByFuzzyQuery", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpGoodsByFuzzyQuery(HttpServletRequest request, PublicDataVO dataVO) {
//        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        List<FilterModel> filterModels = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
        String key2 = filterModels.get(0).getDatas().get(0).toString();
//        String key = TextUtils.isEmpty(dataMap.get("key"))?"":dataMap.get("key").toString();
        List<ErpGoods> erpGoodss = erpGoodsService.listErpGoodsByFuzzyQuery(key2);
        if (null == erpGoodss) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, erpGoodsService.getDbMsg("0004",dataVO));
        }
        return ResponseMessage.sendOK(erpGoodss);
    }

    /***
    *<p>方法:listAllErpGoods 导入所有产品功能 </p>
    *<ul>
     *<li> @param request TODO</li>
     *<li> @param dataVO TODO</li>
    *<li>@return com.ourway.base.model.ResponseMessage  </li>
    *<li>@author zhangxinyi </li>
    *<li>@date 2017-10-16 10:32  </li>
    *</ul>
    */
    @RequestMapping(value = "listAllErpGoods", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllErpGoods(HttpServletRequest request, PublicDataVO dataVO) {
        List<FilterModel> filterModels = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
        List<Map<String, Object>> paramsList = new ArrayList<Map<String, Object>>();
        List<ErpGoods> erpGoodss = erpGoodsService.listPopGoodsByParams(filterModels);
        if (null == erpGoodss) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, erpGoodsService.getDbMsg("0004",dataVO));
        }
//        for(ErpGoods er:erpGoodss){
//            paramsList.add(JsonUtil.obj2Map(er));
//        }
        return ResponseMessage.sendOK(erpGoodss);
    }
}
