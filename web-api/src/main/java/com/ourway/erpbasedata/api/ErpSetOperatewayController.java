package com.ourway.erpbasedata.api;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.erpbasedata.model.ErpSetOperateway;
import com.ourway.erpbasedata.service.ErpSetOperatewayService;
import com.ourway.sys.utils.I18nUtils;
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

/*<p>方法 ErpSetOperatewayController : <p>
*<p>说明:基础资料-作业方式Api</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-22 10:17
</pre>
*/
@Controller
@RequestMapping("setOperatewayApi")
public class ErpSetOperatewayController {

    @Autowired
    private ErpSetOperatewayService erpSetOperatewayService;

    /*<p>方法: 作业方式列表 </p>
    *<ul>
     *<li> @param request </li>
     *<li> @param data </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-10-17 10:18  </li>
    *</ul>
    */
    @RequestMapping(value = "listErpSetOperateway", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listErpSetOperateway(HttpServletRequest request, PublicDataVO data){
        List<FilterModel> filterModels = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        return ResponseMessage.sendOK(erpSetOperatewayService.listErpSetOperateway(
                filterModels,data.getPageNo(),data.getPageSize(),""));
    }

    /*<p>方法: 作业方式详情（暂未启用）</p>
    *<ul>
     *<li> @param request </li>
     *<li> @param data </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-10-17 10:19  </li>
    *</ul>
    */
    @RequestMapping(value = "detailErpSetOperateway", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailErpSetOperateway(HttpServletRequest request, PublicDataVO data){
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        return ResponseMessage.sendOK(erpSetOperatewayService.detailErpSetOperateway(dataMap));
    }

    /*<p>方法: 作业方式保存 </p>
    *<ul>
     *<li> @param request </li>
     *<li> @param data </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-10-17 10:19  </li>
    *</ul>
    */
    @RequestMapping(value = "saveErpSetOperateway", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveErpSetOperateway(HttpServletRequest request, PublicDataVO data){
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (!TextUtils.isEmpty(dataMap) && !TextUtils.isEmpty(dataMap.get("dataList"))) {
            List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
            List<ErpSetOperateway> erpSetOperatewayList = new ArrayList<ErpSetOperateway>();
            for (Map<String,Object> objectMap:dataList) {
                Map<String, String> validateKeyMap = new HashMap<String, String>();
                //非空校验多语言相关
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.operatewayId.01",data.getCurrLanguage()), "operatewayId");
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.operatewayName.01",data.getCurrLanguage()), "operatewayName");
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.operatewayType.01",data.getCurrLanguage()), "operatewayType");
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.inoutWay.01",data.getCurrLanguage()), "inoutWay");
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.operateCompleteType.01",data.getCurrLanguage()), "operateCompleteType");
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.operateTimeStandard.01",data.getCurrLanguage()), "operateTimeStandard");
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.stockUpdateTimeStandard.01",data.getCurrLanguage()), "stockUpdateTimeStandard");
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.stockUpdateNumStandard.01",data.getCurrLanguage()), "stockUpdateNumStandard");
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.chargeTimeStandard.01",data.getCurrLanguage()), "chargeTimeStandard");
                validateKeyMap.put(I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway.chargeNumStandard.01",data.getCurrLanguage()), "chargeNumStandard");
                //非空校验
                ValidateMsg validateMsg = ValidateUtils.isEmpty2(objectMap, validateKeyMap);
                if (!validateMsg.getSuccess()) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
                }
                if (!TextUtils.isEmpty(objectMap.get("operatewayType")) && objectMap.get("operatewayType") instanceof Map) {
                    objectMap.put("operatewayType",Integer.parseInt(((Map)(objectMap.get("operatewayType"))).get("path").toString()));
                }
                //唯一性校验
                if (null != erpSetOperatewayService.checkUnique(objectMap,data)) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, erpSetOperatewayService.checkUnique(objectMap,data)+"重复");
                }
                erpSetOperatewayList.add(JsonUtil.map2Bean(objectMap, ErpSetOperateway.class));
            }
            erpSetOperatewayService.saveErpSetOperateway(erpSetOperatewayList);
        }
        return ResponseMessage.sendOK(null);
    }
}
