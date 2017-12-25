package com.ourway.erpbasedata.api;

import com.ourway.ERPTipsConstants;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;
import com.ourway.erpbasedata.service.ErpShipListService;
import com.ourway.erpbasedata.service.ErpShipService;
import com.ourway.erpbasedata.service.ErpTankListService;
import com.ourway.erpbasedata.service.ErpTankService;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysWorkflow;
import com.ourway.sys.service.BaseProcessService;
import com.ourway.sys.service.FilesService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
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
@RequestMapping("erpShipApi")
public class ErpShipController {
    @Autowired
    ErpShipService erpShipService;

    @Autowired
    ErpShipListService erpShipListService;
    @Autowired
    FilesService filesService;

    @Autowired
    BaseProcessService baseProcessService;

    /***
    *<p>方法:listPages 列表 </p>
    *<ul>
     *<li> @param request TODO</li>
     *<li> @param response TODO</li>
     *<li> @param data TODO</li>
    *<li>@return com.ourway.base.model.ResponseMessage  </li>
    *<li>@author zhangxinyi </li>
    *<li>@date 2017-09-11 14:09  </li>
    *</ul>
    */
    @RequestMapping(value = "listShip", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listPages(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(erpShipService.listHqlForPage(filters, data.getPageNo(), data.getPageSize(), ""));
    }


    /***
    *<p>方法:saveApi 保存修改 </p>
    *<ul>
     *<li> @param request TODO</li>
     *<li> @param response TODO</li>
     *<li> @param data TODO</li>
    *<li>@return com.ourway.base.model.ResponseMessage  </li>
    *<li>@author zhangxinyi </li>
    *<li>@date 2017-09-11 14:09  </li>
    *</ul>
    */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveApi(HttpServletRequest request, HttpServletResponse response,
                                   PublicDataVO data) {
        // json 转 map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "shipId", "chnShipName", "shipType");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        if (!TextUtils.isEmpty(mapData.get("dataList1")) && ((List)mapData.get("dataList1")).size()>0) {
            for(Map<String,Object> _map:(List<Map<String,Object>>) mapData.get("dataList1")){
                _map.put("erpgoodsRefOwid",((Map<String,Object>)_map.get("erpgoodsRefOwid")).get("owid"));
            }
        }

        //解析 api 和子表信息
        ErpShip erpShip = JsonUtil.map2Bean(mapData, ErpShip.class);

        List<ErpShipList> erpShipListList = null;

        if (null != mapData.get("dataList1")) {
            List<Map<String, Object>> temp = (List<Map<String, Object>>) mapData.get("dataList1");
            erpShipListList = JsonUtil.map2List(temp, ErpShipList.class);
        }

        //判断iintUrl是否唯一
        if (!erpShipService.doCheckUniqueUrl(erpShip)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(ERPTipsConstants.TANKID_UNIQUE,data.getCurrLanguage()));
        }

        //保存或者修改
        erpShip = erpShipService.saveOrUpdateErpShip(erpShip, erpShipListList);
        if(!TextUtils.isEmpty(mapData.get("fileExtId"))&&erpShip.getOwid().equalsIgnoreCase(mapData.get("fileExtId").toString()))
            filesService.saveOrUpdate(erpShip.getOwid(),mapData.get("fileExtId").toString());
        return ResponseMessage.sendOK(erpShip);
    }


    @RequestMapping(value = "detailErpShip", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailErpShip(HttpServletRequest request, HttpServletResponse response,
                                         PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(erpShipService.detailErpShip(mapData.get("owid").toString()));
    }

    @RequestMapping(value = "removeErpShip", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeErpShip(HttpServletRequest request, HttpServletResponse response, PublicDataVO dataVo) {

        if (TextUtils.isEmpty(dataVo.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,dataVo.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(dataVo.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }

        List<Map<String, Object>> data = erpShipService.removeErpShipByIds(owids);

        return ResponseMessage.sendOK(data);
    }

    @RequestMapping(value = "listErpShipByFuzzyQuery", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpShipByFuzzyQuery(HttpServletRequest request, PublicDataVO dataVO) {
        List<FilterModel> filterModels = JsonUtil.jsonToList(dataVO.getData(), FilterModel.class);
        String key2 = filterModels.get(0).getDatas().get(0).toString();
        List<ErpShip> erpShips = erpShipService.listErpShipByFuzzyQuery(key2);
        return ResponseMessage.sendOK(erpShips);
    }


    @RequestMapping(value = "listErpShipList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpTankList(HttpServletRequest request, HttpServletResponse response,
                                           PublicDataVO dataVO) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
        //判断owid是否为空
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }
        return ResponseMessage.sendOK(erpShipListService.listAllShipListByShipId(mapData.get("owid").toString()));
    }

    @RequestMapping(value = "save1", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage save1(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysWorkflow workflow = erpShipService.updateStartFlow(dataMap.get("owid").toString());
//        //发送消息
//        if (null != workflow)
//            erpShipService.updateWorkFlowMsgs(workflow,"/test/flow/test1.do");
        return ResponseMessage.sendOK(dataMap.get("owid").toString());
    }


}
