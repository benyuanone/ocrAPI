package com.ourway.erpsystem.api.erpberth;



import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.erpbasedata.model.ErpBerth;
import com.ourway.erpsystem.service.ErpBerthService;
import com.ourway.sys.service.FilesService;
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
@RequestMapping("berthApi")
public class ErpBerthController {

    @Autowired
    ErpBerthService erpBerthService;
    @Autowired
    FilesService filesService;


    @RequestMapping(value = "saveBerth" , method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveBerth(HttpServletRequest request, PublicDataVO data){
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());

        List<ErpBerth> dicValues = new ArrayList<>();
        if(null!=dataMap.get("dataList")){
            List<Map<String, Object>> components = (List<Map<String, Object>>) dataMap.get("dataList");
            dicValues = JsonUtil.map2List(components, ErpBerth.class);
        }
//        if (!erpBerthService.doCheckUnique(basedataCurrencyType)) {
//            return ResponseMessage.sendError(ResponseMessage.FAIL, "主键重复");
//        }
        erpBerthService.saveOrUpdateBerth(dicValues);
        return ResponseMessage.sendOK(dicValues);
    }


    @RequestMapping(value = "removeBerth", method = RequestMethod.POST)
    public ResponseMessage removeBerth(HttpServletRequest request, PublicDataVO dataVo) {
//        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
//        HqlStatement hql = new HqlStatement(" from erpCurrencyType", filters);
//        erpBerthService.removeBasedataCurrencyTypeEntity(hql);
//        return ResponseMessage.sendOK(null);

        JSONArray jsonArray = JSONArray.fromObject(dataVo.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }

        List<Map<String, Object>> data = erpBerthService.removeBerthByIds(owids);

        return ResponseMessage.sendOK(data);
    }

    @RequestMapping(value = "listBerth", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listBerth(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
//        List<Map<String,Object>>
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        return ResponseMessage.sendOK(erpBerthService.listBerth(dataMap));
    }
}
