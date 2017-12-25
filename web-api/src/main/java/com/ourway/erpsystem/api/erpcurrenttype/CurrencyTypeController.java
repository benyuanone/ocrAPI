package com.ourway.erpsystem.api.erpcurrenttype;

import com.ourway.ERPTipsConstants;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.erpbasedata.model.ErpCurrencyType;
import com.ourway.erpsystem.service.ErpCurrencyTypeService;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysDicValue;
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
import java.util.List;
import java.util.Map;

/*
*<p>方法: TODO 货币管理</p>
*<ul>
 *<li> @param null TODO 货币资料管理</li>
*<li>@return   </li>
*<li>@author kevin </li>
*<li>@date 2017-05-09 11:26  </li>
*</ul>
*/
@Controller
@RequestMapping("currencyTypeApi")
public class CurrencyTypeController {

    @Autowired
    ErpCurrencyTypeService erpCurrencyTypeService;
    @Autowired
    FilesService filesService;

    @RequestMapping(value = "saveCurrencyType" , method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveCurrencyType(HttpServletRequest request, PublicDataVO data){
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());

        ErpCurrencyType tmpCT = JsonUtil.map2Bean(dataMap, ErpCurrencyType.class);

        List<ErpCurrencyType> dicValues = new ArrayList<>();
        if(null!=dataMap.get("dataList")){
            List<Map<String, Object>> components = (List<Map<String, Object>>) dataMap.get("dataList");
            dicValues = JsonUtil.map2List(components, ErpCurrencyType.class);

//            if (!erpCurrencyTypeService.doCheckUniqueUrl(tmpCT)) {
//                return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(ERPTipsConstants.CURRENCYTYPEID_UNIQUE));
//            }
        }
        erpCurrencyTypeService.saveOrUpdateCurrencyType(dicValues);
        return ResponseMessage.sendOK(dicValues);
    }


    @RequestMapping(value = "removeCurrencyType", method = RequestMethod.POST)
    public ResponseMessage removeCurrencyType(HttpServletRequest request, PublicDataVO dataVo) {

        JSONArray jsonArray = JSONArray.fromObject(dataVo.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }

        List<Map<String, Object>> data = erpCurrencyTypeService.removeCTByIds(owids);

        return ResponseMessage.sendOK(data);
    }

    @RequestMapping(value = "listCurrencyType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listGuest(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        return ResponseMessage.sendOK( erpCurrencyTypeService.listCurrencyType(dataMap));
    }

    @RequestMapping(value = "listCurrencyType1", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listCurrencyType(HttpServletRequest request, HttpServletResponse response,
                                            PublicDataVO data) {
        List<FilterModel> filterModels = JsonUtil.jsonToList(data.getData(),FilterModel.class);
        return ResponseMessage.sendOK(erpCurrencyTypeService.listCurrencyType(
                filterModels,data.getPageNo(),data.getPageSize(),""));
    }
}
